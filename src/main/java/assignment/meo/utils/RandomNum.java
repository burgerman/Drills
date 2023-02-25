package assignment.meo.utils;

import java.util.Random;

public class RandomNum {

    private static Random random = new Random();

    public static int getHealthyKoala() {
        return random.nextInt(10);
    }

    public static int getInjuredKoala() {
        return random.nextInt(3);
    }

    public static int getKoalaAge() {
        return random.nextInt(18)+1;
    }

    public static int getPredator() {
        return random.nextInt(5);
    }

    public static int getRandomTree (int numTree) {
        for(int i =0; i<numTree; i++) {
            if(random.nextInt(20) == i) {
                numTree =  numTree - 1;
            }
        }
        return numTree;
    }

    public static int starvingDeathRate () {
        return random.nextInt(5);
    }

    public static int homelessDeathRate() {
        // [0,5) -> 0 1 2 3 4
        return random.nextInt(5);
    }

    public static int preyedDeathRate () {
        // [0,2) -> 0 / 1
        return random.nextInt(2);
    }

}
