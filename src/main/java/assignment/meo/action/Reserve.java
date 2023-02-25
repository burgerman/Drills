package assignment.meo.action;

import assignment.meo.entity.Koala;
import assignment.meo.entity.Observation;
import assignment.meo.entity.Point;
import assignment.meo.entity.SafeHaven;
import assignment.meo.utils.PointStatusPostProcessor;

/**
 * Mapping 4 actions here into 4 options: A, B, C, D
 *
 */
public class Reserve {

    private Observation observation;
    /**
     * The cost of action A, B and C
     */
    private static int costA = 20;
    private static int costB = 10;
    private static int costC = 5;

    public void setObservation (String path, int budget) {
        observation = Observation.getObservation(path, budget);
    }

    public Observation getObservation() {
        return observation;
    }

    public void takeAction(String action, Point p) {
        char option = Character.toUpperCase(action.charAt(0));
        switch (option) {
            case 'A':
                if(observation.getBudget()<costA) {
                    System.out.println("Budget is not enough to afford the cost of action A");
                } else {
                    if(actionA(p)) {
                        observation.setBudget(observation.getBudget()-costA);
                        observation.setRescueCost(observation.getRescueCost()+costA);
                        observation.setRescuedInjuredKoala(observation.getRescuedInjuredKoala()+1);
                    } else {
                        System.out.println("Failed to transfer an injured koala to the safe haven");
                    }
                }
                System.out.println("Updated info of the point: ");
                System.out.println(p);
                System.out.println("Available budget: " + observation.getBudget());
                break;
            case 'B':
                if(observation.getBudget()<costB) {
                    System.out.println("Budget is not enough to afford the cost of action B");
                } else {
                    if (actionB(p)) {
                        observation.setBudget(observation.getBudget()-costB);
                        observation.setRescueCost(observation.getRescueCost()+costB);
                    } else {
                        System.out.println("Failed to transfer a healthy koala to the safe haven");
                    }
                }
                System.out.println("Updated info of the point: ");
                System.out.println(p);
                System.out.println("Available budget: " + observation.getBudget());
                break;
            case 'C':
                if(observation.getBudget()<costC) {
                    System.out.println("Budget is not enough to afford the cost of action C");
                } else {
                    if (actionC(p)) {
                        observation.setBudget(observation.getBudget()+costC);
                    } else {
                        System.out.println("Failed to transfer an injured koala to the safe haven");
                    }
                }
                System.out.println("Updated info of the point: ");
                System.out.println(p);
                System.out.println("Available budget: " + observation.getBudget());
                break;
            case 'D':
                actionD(p);
                PointStatusPostProcessor.pointUpdate(p);
                System.out.println("Updated info of the point: ");
                System.out.println(p);
                System.out.println("Available budget: " + observation.getBudget());
                break;
            default:
                System.out.println("Default: Please enter an available action");
                break;
        }

    }




    /**
     * Transfer an injured koala at this point to the safe haven
     * @param point
     * @return
     */
    private static  boolean actionA(Point point) {
        if(point.getInjuredKoalas().size()>0) {
            Koala koala = point.getInjuredKoalas().iterator().next();
            SafeHaven.sendInjured(koala);
            point.getInjuredKoalas().remove(koala);
            point.setNumInjuredKoalas(point.getInjuredKoalas().size());
            return true;
        } else {
            System.out.println("There's no injured koala at this point that needs to be transferred");
            return false;
        }
    }

    /**
     * Transfer a healthy koala at this point to the safe haven
     * @param point
     * @return
     */
    private static boolean actionB(Point point) {
        if(point.getHealthyKoalas().size()>0) {
            Koala koala = point.getHealthyKoalas().iterator().next();
            SafeHaven.sendHeath(koala);
            point.getHealthyKoalas().remove(koala);
            point.setNumHealthyKoalas(point.getHealthyKoalas().size());
            return true;
        } else {
            System.out.println("There's no healthy koala at this point that needs to be transferred");
            return false;
        }
    }

    /**
     * Relocate the oldest koala out of healthy koalas to this point
     * @param point
     * @return
     */
    private static boolean actionC(Point point) {
         return SafeHaven.relocateOldestKoala(point);
    }

    /**
     * Do nothing
     * If there's no any further action to take, this action can serve as a end mark
     * @param point
     */
    private static void actionD(Point point) {
        System.out.println("Do nothing to this point");
    }
}
