package yacup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileCleaner {

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("input_fc.txt"));
             BufferedWriter writer = Files.newBufferedWriter(Path.of("output.txt"))) {

            List<Path> blackList = readBlock(reader);
            List<Path> content = readBlock(reader);
            List<Path> requests = readBlock(reader);

            long start = System.currentTimeMillis();

            List<Path> blacklistedContent = getBlacklisted(blackList, content);
            for (Path request : requests) {
                processRequest(request, blacklistedContent, writer);
            }

            System.out.println(System.currentTimeMillis() - start);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Path> getBlacklisted(List<Path> blackList, List<Path> content) {
        return content.stream()
                .filter(c -> blackList.stream().anyMatch(c::startsWith))
                .collect(Collectors.toList());
    }

    private static void processRequest(Path request, List<Path> content, BufferedWriter writer)
            throws IOException {
        Map<String, Integer> result = content.stream()
                .filter(c -> c.startsWith(request))
                .map(Path::toString)
                .map(s -> s.substring(s.lastIndexOf('.')))
                .collect(Collectors.groupingBy(key -> key, Collectors.summingInt(e -> 1)));

        writer.write(String.valueOf(result.size()));
        writer.newLine();

        for (Map.Entry<String, Integer> e : result.entrySet()) {
            String s = e.getKey() + ": " + e.getValue();
            writer.write(s);
            writer.newLine();
        }
    }

    private static List<Path> readBlock(BufferedReader reader) throws IOException {
        int lines = Integer.parseInt(reader.readLine());
        return reader.lines().limit(lines).map(Path::of).collect(Collectors.toList());
    }
}
