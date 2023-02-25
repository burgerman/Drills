package assignment.meo.utils;

import assignment.meo.entity.Koala;
import assignment.meo.entity.Point;
import java.util.ArrayList;

/**
 * If the rescue team choose nothing to do, the post processor will update the point status
 * Update the status of this point and count the number of dead
 */
public class PointStatusPostProcessor {


    public static Point pointUpdate(Point point) {
        point = processInjured(point);
        point = processShortageOfFood(point);
        point = processHomeless(point);
        point = processKilledByPredator(point);
        point.setNumHealthyKoalas(point.getHealthyKoalas().size());
        return point;
    }


    private static Point processInjured(Point point) {
        int n = point.getInjuredKoalas().size();
        if(n>0) {
            // The rest of injured koalas are dead
            point.setDeath(point.getDeath()+n);
            point.setInjuredKoalas(new ArrayList<>());
            point.setNumInjuredKoalas(0);
        }
        return point;
    }

    private static Point processShortageOfFood(Point point) {
        ArrayList<Koala> list = new ArrayList<>(point.getHealthyKoalas());
        int init = list.size();
        int foodSupply = point.getFoodSupply().intValue();
        int index =0;
        // The rest of healthy koalas facing the shortage of food supply only have 20% chance to survive
        while (foodSupply < point.getHealthyKoalas().size() && index < init) {
            Koala k = list.get(index);
            if(RandomNum.starvingDeathRate() < 4) {
                point.getHealthyKoalas().removeIf(e->e.equals(k));
            }
            index++;
        }
        int death=point.getDeath()+(init-point.getHealthyKoalas().size());
        point.setDeath(death);
        list = null;
        return point;
    }


    private static Point processHomeless(Point point) {
        ArrayList<Koala> list = new ArrayList<>(point.getHealthyKoalas());
        int init= list.size();
        int shelter = point.getShelterNum();
        int index = 0;
        // For the rest of homeless koalas, they have 80% chance to survive
        while (shelter < point.getHealthyKoalas().size() && index < init) {
            Koala k = list.get(index);
            if(RandomNum.homelessDeathRate() > 3) {
                point.getHealthyKoalas().removeIf(e->e.equals(k));
            }
            index++;
        }

        int death=point.getDeath()+(init-point.getHealthyKoalas().size());
        point.setDeath(death);
        list = null;
        return point;
    }

    private static Point processKilledByPredator(Point point) {
        ArrayList<Koala> list = new ArrayList<>(point.getHealthyKoalas());

        int init = list.size();
        int predator = point.getPredator();
        int index = 0;
        // If the number of predators is over 3, there's 50% chance to survive
        while (predator > 3 && index < init) {
            Koala k = list.get(index);
            if(RandomNum.preyedDeathRate() < 1) {
                point.getHealthyKoalas().removeIf(e->e.equals(k));
            }
            index++;
        }
        int death = point.getDeath()+(init-point.getHealthyKoalas().size());
        point.setDeath(death);
        list = null;
        return point;
    }

}
