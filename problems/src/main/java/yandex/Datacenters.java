package yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Datacenters {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("started");
//        Thread.sleep(15000);
        long start = System.currentTimeMillis();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("input_dc.txt"));
             BufferedWriter writer = Files.newBufferedWriter(Path.of("output.txt"))) {

            String line = reader.readLine();
            String[] numbers = line.split(" ");
            int n = Integer.parseInt(numbers[0]);
            int m = Integer.parseInt(numbers[1]);
//            final int q = Integer.parseInt(numbers[2]);
            byte[][] dcServers = new byte[n][m];
            int[] restarts = new int[n];
            int[] availableServers = new int[n]; //count of available servers

            for (int i = 0; i < n; i++) {
                dcServers[i] = new byte[m];
                Arrays.fill(dcServers[i], (byte) 1);
                availableServers[i] = m;
            }

            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] parsed = line.split(" ");
                switch (parsed[0]) {
                    case "RESET" -> {
                        int dcI = Integer.parseInt(parsed[1]) - 1;
                        Arrays.fill(dcServers[dcI], (byte) 1);
                        availableServers[dcI] = m;
                        restarts[dcI] += 1;
                    }
                    case "DISABLE" -> {
                        int dcI = Integer.parseInt(parsed[1]) - 1;
                        int srvI = Integer.parseInt(parsed[2]) - 1;
                        byte server = dcServers[dcI][srvI];
                        if (server == 1) {
                            dcServers[dcI][srvI] = 0;
                            availableServers[dcI] -= 1;
                        }
                    }
                    case "GETMAX" -> {
                        int max = findMax(restarts, availableServers);
                        writer.write(String.valueOf(max));
                        writer.newLine();
                    }
                    case "GETMIN" -> {
                        int min = findMin(restarts, availableServers);
                        writer.write(String.valueOf(min));
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(System.currentTimeMillis() - start);
    }

    private static int findMax(int[] restarts, int[] availableServers) {
        int maxI = 0;
        int max = availableServers[maxI] * restarts[maxI];
        for (int i = 0; i < availableServers.length; i++) {
            int current = availableServers[i] * restarts[i];
            if (current > max) {
                max = current;
                maxI = i;
            }
        }
        return maxI + 1;
    }

    private static int findMin(int[] restarts, int[] availableServers) {
        int minI = 0;
        int min = availableServers[minI] * restarts[minI];
        for (int i = 0; i < availableServers.length; i++) {
            int current = availableServers[i] * restarts[i];
            if (current < min) {
                min = current;
                minI = i;
            }
        }
        return minI + 1;
    }
}
//time limit 2500 ms