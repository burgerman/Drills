package com.wil.practice.algo.cp600.backtracking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TSPTestCaseGenerator {
    public static void main(String[] args) {
        int numCities = 32; // Number of cities
        int minDistance = 1;
        int maxDistance = 15;
        Random random = new Random();

        try (FileWriter writer = new FileWriter("TSP_Simple_Test_Case.txt")) {
            // Generate edges systematically
            for (int i = 0; i < numCities; i++) {
                int numNeighbors = random.nextInt(2) + 2; // 3-4 neighbors
                for (int j = 1; j <= numNeighbors; j++) {
                    if (i + j < numCities) {
                        int distance = random.nextInt(maxDistance - minDistance + 1) + minDistance;
                        writer.write(i + " " + (i + j) + " " + distance + "\n");
                    }
                }
            }

            // Optionally add a few long-range connections
            for (int k = 0; k < numCities / 10; k++) {
                int cityA = random.nextInt(numCities);
                int cityB = random.nextInt(numCities);
                if (cityA != cityB) {
                    int distance = random.nextInt(maxDistance - minDistance + 1) + minDistance;
                    writer.write(cityA + " " + cityB + " " + distance + "\n");
                }
            }

            System.out.println("Test case generated: TSP_Simple_Test_Case.txt");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
