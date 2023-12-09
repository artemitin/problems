package yandex;

import java.io.*;
import java.util.Arrays;

public class ShortestLength {

    /*
	Для чтения входных данных необходимо получить их
	из стандартного потока ввода (System.in).
	Данные во входном потоке соответствуют описанному
	в условии формату. Обычно входные данные состоят
	из нескольких строк. Можно использовать более производительные
	и удобные классы BufferedReader, BufferedWriter, Scanner, PrintWriter.

	С помощью BufferedReader можно прочитать из стандартного потока:
	* строку -- reader.readLine()
	* число -- int n = Integer.parseInt(reader.readLine());
	* массив чисел известной длины (во входном потоке каждое число на новой строке) --
	int[] nums = new int[len];
    for (int i = 0; i < len; i++) {
        nums[i] = Integer.parseInt(reader.readLine());
    }
	* последовательность слов в строке --
	String[] parts = reader.readLine().split(" ");

	Чтобы вывести результат в стандартный поток вывода (System.out),
	Через BufferedWriter можно использовать методы
	writer.write("Строка"), writer.write('A') и writer.newLine().

	Возможное решение задачи "Вычислите сумму чисел в строке":
	int sum = 0;
    String[] parts = reader.readLine().split(" ");
    for (int i = 0; i < parts.length; i++) {
        int num = Integer.parseInt(parts[i]);
        sum += num;
    }
    writer.write(String.valueOf(sum));
	*/

    private static int minLength(int[] points) {
        Arrays.sort(points);
        int[] distIJ = new int[points.length - 1];
        distIJ[0] = points[1] - points[0];

        if (points.length == 2) {
            return distIJ[0];
        }
        int sum = 0;

        for (int i = 1; i < points.length - 1; i++) {
            distIJ[i] = points[i + 1] - points[i];
            sum += Math.min(distIJ[i], distIJ[i - 1]);
        }

        if (points.length == 3) {
            sum += distIJ[1];
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        reader.close();
//        writer.close();

//        int[] input = {3, 13, 12, 4, 14, 6};
        int[] input = {2, 4, 8};
//        int[] input = {2, 4};
        int result = minLength(input);
        System.out.println(result);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        String[] parts = reader.readLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        int result = minLength(nums);

        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }
}
