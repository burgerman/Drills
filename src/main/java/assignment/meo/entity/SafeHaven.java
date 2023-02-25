package assignment.meo.entity;

import java.util.ArrayList;

public class SafeHaven {

    private static ArrayList<Koala> healthyKoalaList = new ArrayList<>();
    private static ArrayList<Koala> injuredKoalaList = new ArrayList<>();
    private static ArrayList<Koala> relocatedKoalaList = new ArrayList<>();

    public static void sendHeath(Koala koala) {
        healthyKoalaList.add(koala);
    }

    public static void sendInjured(Koala koala) {
        injuredKoalaList.add(koala);
    }

    public static boolean relocateOldestKoala(Point point) {
        int num = point.getSumKoala()+1;
        if (healthyKoalaList.size()<1) {
            System.out.println("There's no healthy koala that needs to be relocated for now");
            return false;
        }
        if(point.getFoodSupply().intValue()>=num && point.getShelterNum()>=num) {
            healthyKoalaList.sort((o1,o2)-> o2.getAge()-o1.getAge());
            Koala oldestKoala = healthyKoalaList.get(0);
            relocatedKoalaList.add(oldestKoala);
            ArrayList<Koala> list = point.getHealthyKoalas();
            list.add(oldestKoala);
            point.setHealthyKoalas(list);
            point.setNumHealthyKoalas(list.size());
            healthyKoalaList.remove(oldestKoala);
            return true;
        } else {
            System.out.println("This point doesn't have enough food supply or shelter");
            return false;
        }
    }

    public static int getNumHealthyKoala() {
        return healthyKoalaList.size();
    }

    public static int getNumInjuredKoala() {
        return injuredKoalaList.size();
    }

    public static int getRelocatedKoala() {
        return relocatedKoalaList.size();
    }

    public static int getSum() {
        return healthyKoalaList.size()+injuredKoalaList.size();
    }
}
