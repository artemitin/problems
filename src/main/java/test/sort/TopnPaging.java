package test.sort;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//Problem: find topN sorted largest/lowest entries
public class TopnPaging {

    public static void main(String[] args) throws InterruptedException {
//        List<Integer> data = List.of(
//                0, 1, 2, 3, 4,
//                5, 6, 7, 8, 9,
//                10, 11, 12, 13, 14,
//                15, 16, 17, 18, 19,
//                20, 21, 22, 23, 24,
//                25, 26, 27, 28, 29,
//                30, 31, 32, 33, 34,
//                35, 36, 37, 38, 39,
//                40, 41, 42, 43, 44,
//                45, 46, 47, 48, 49,
//                50, 51, 52, 53, 54,
//                55, 56, 57, 58, 59,
//                60, 61, 62, 63, 64,
//                65, 66, 67, 68, 69,
//                70, 71, 72, 73, 74, //15
//                75, 76, 77, 78, 79, //16
//                80, 81, 82, 83, 84, //17
//                85, 86, 87, 88, 89, //18
//                90, 91, 92, 93, 94, //19
//                95, 96, 97, 98, 99);//20
        List<Integer> data = new Random().ints(10000000, 0, 10000000).boxed().toList();
//        List<Integer> data = new ArrayList<>(List.of(3, 12, 4, 5, 6, 1, 8, 10, 2, 12, 4, 5, 1, 8, 10));
//                                                   3, 12, 4, 5, 6, 1, 8, 10, 2, 12, 4,  5,  1,  8, 10
//                                                   1,  1, 2, 3, 4, 4, 5,  5, 6,  8, 8, 10, 10, 12, 12
//                                                                                |^ --------|
//                                                                  |              12, 12, 10, 10, 8, 8
//        System.out.println("Data:");
//        System.out.println(data);

        int itemsPerPage = 1000;
        int page = 100;
        float shareQsort = 0.25f;

//naive pages range: 1/4 ...3/4
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);

        Function<Supplier<Collection<Integer>>, Collection<Integer>> measure = runnable -> {
            long start = System.currentTimeMillis();
            Collection<Integer> topNlist = runnable.get();
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - start));
            return topNlist;
        };

        int pages = Math.ceilDiv(data.size(), itemsPerPage);
        int edgePages = (int) Math.floor(pages * shareQsort);
        System.out.println("Total pages: " + pages);
        System.out.println("Edge pages: " + edgePages);
        boolean result = true;
//        for (int i = 1; i <= pages; i++) {
//            int page = i;
            System.out.println("PAGE: " + page);

            System.out.println("topN prioQ asc");
            Collection<Integer> topNprioQasc = measure.apply(() -> topN(
                    page, itemsPerPage, data, comparator, true, shareQsort));
//        System.out.println(topNprioQasc);

            System.out.println("topN prioQ desc");
            Collection<Integer> topNprioQdesc = measure.apply(() -> topN(
                    page, itemsPerPage, data, comparator, false, shareQsort));
//        System.out.println(topNprioQdesc);

            System.out.println("topN simple asc");
            Collection<Integer> topNsimpleAsc = measure.apply(() -> topnFullSort(
                    page, itemsPerPage, new ArrayList<>(data), comparator, true));
//        System.out.println(topNsimpleAsc);

            System.out.println("topN simple desc");
            Collection<Integer> topNsimpleDesc = measure.apply(() -> topnFullSort(
                    page, itemsPerPage, new ArrayList<>(data), comparator, false));
//        System.out.println(topNsimpleDesc);

            boolean equalsAsc = topNprioQasc.equals(topNsimpleAsc);
            System.out.println("Equals asc: " + equalsAsc);
            boolean equalsDesc = topNprioQdesc.equals(topNsimpleDesc);
            System.out.println("Equals desc: " + equalsDesc);
            result = result && equalsAsc && equalsDesc;
//        }
        if (!result) {
            throw new RuntimeException("Method is incorrect!");
        }
    }

    private static <T> Collection<T> topnFullSort(int page, int itemsPerPage, List<T> data, Comparator<T> comparator,
                                                  boolean isAsc) {
        System.out.println("Sorting by fullsort");
        int offset = (page - 1) * itemsPerPage;
//        data = data.stream().parallel().sorted(isAsc ? comparator : comparator.reversed()).collect(Collectors.toList());
        data.sort(isAsc ? comparator : comparator.reversed());
        return data.subList(offset, Math.min(data.size(), offset + itemsPerPage));
    }

    private static <T> List<T> topnQsort(int queueSize, Collection<T> data, Comparator<T> comparator,
                                         boolean isAsc) {
        System.out.println("Sorting by qsort");
        Comparator<T> queueComparator = isAsc ? comparator.reversed() : comparator;
        PriorityQueue<T> queue = new PriorityQueue<>(queueSize, queueComparator);

        for (T item : data) {
            if (queue.size() < queueSize) {
                queue.offer(item);
            } else if (queueComparator.compare(item, queue.peek()) > 0) {
                queue.poll();
                queue.offer(item);
            }
        }

        //collect results
        return queue.stream().parallel().sorted(queueComparator.reversed()).collect(Collectors.toList());
    }

    private static <T> Collection<T> topN(int pageN, int itemsPerPage, Collection<T> data, Comparator<T> comparator,
                                          boolean isAsc, float shareQsort) {
        //total pages
        int pages = Math.ceilDiv(data.size(), itemsPerPage);
        //if last page is partial, items on the last page
        int lastPageItems = data.size() % itemsPerPage;
        //if last page is partial, difference between the regular page size
        int lastPageOffset = lastPageItems == 0 ? 0 : (itemsPerPage - lastPageItems);
        //number of pages on the edge.
        int edgePagesN = (int) Math.floor(pages * shareQsort);
        if (pageN < edgePagesN) {
            //approach the list from the front
            int queueSize = pageN * itemsPerPage;
            List<T> sorted = topnQsort(queueSize, data, comparator, isAsc);
            int offset = (pageN - 1) * itemsPerPage;
            return sorted.subList(offset, offset + itemsPerPage);
        } else if (pageN > pages - edgePagesN) {
            //approach the list from the end
            //last page may contain fewer items than regular pages. Need to size the queue accordingly.
            int queueSize = itemsPerPage * (pages - pageN + 1) - lastPageOffset;
            List<T> sorted = topnQsort(queueSize, data, comparator, !isAsc);
            List<T> sublist;
            if (sorted.size() < itemsPerPage) {
                sublist = sorted;
            } else {
                sublist = sorted.subList(sorted.size() - itemsPerPage, sorted.size());
            }
            Collections.reverse(sublist);
            return sublist;
        } else {
            return topnFullSort(pageN, itemsPerPage, new ArrayList<>(data), comparator, isAsc);
        }
    }
}
