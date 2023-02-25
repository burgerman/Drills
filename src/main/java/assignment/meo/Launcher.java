package assignment.meo;

import assignment.meo.action.Reserve;
import assignment.meo.entity.Observation;
import assignment.meo.entity.Point;
import assignment.meo.entity.SafeHaven;
import assignment.meo.utils.FileOperators;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Launcher {

    private static Pattern stopFlag = Pattern.compile("(?i)D");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your name: ");
        outer: while(scanner.hasNext()) {
            String usrName = scanner.nextLine();
            System.out.println("Hi "+usrName+", welcome to the Koala Rescue Team");
            if (usrName.length() < 1 || usrName.length() > 15) {
                throw new IllegalArgumentException("Oops! Please enter a name with a length ranging from 1 to 15");
            }

            System.out.println("Please input the path of trees.txt");
            String path = scanner.nextLine();
            System.out.println("Please input the path of updatedTrees.txt");
            String outputPath = scanner.nextLine();
            File output = FileOperators.getFile(outputPath);

            System.out.println("Please input budget for this rescue: ");
            int budget = Integer.valueOf(scanner.nextLine());
            if(budget<100 || budget>200) {
                throw new IllegalArgumentException("The given amount is out of range. The rescue is budgeted from $100 to $200");
            }
            Reserve reserve = new Reserve();
            reserve.setObservation(path, budget);
            Observation observation = reserve.getObservation();
            System.out.println("Info of each observation point: ");
            System.out.println("========================================================================================");
            ArrayList<Point> allPoints = observation.getObservationPoints();
            Point currentPoint;
            // Observe those points one after another
            for(int i=0; i<allPoints.size(); i++) {
                currentPoint = allPoints.get(i);
                System.out.println();
                System.out.println("the latest Info of the observation point "+(i+1)+": ");
                System.out.println(currentPoint);
                System.out.println("------------------------------------------------------------------------------------");
                // Take action on the current point
                inner: for(;;) {
                    System.out.println("Choose one action out of A, B, C and D: ");
                    String choice = scanner.nextLine();
                    reserve.takeAction(choice, currentPoint);
                    if(choice.matches(stopFlag.pattern())) {
                        break inner;
                    }
                }
                System.out.println("Balance: "+observation.getBudget());
                System.out.println("The number of dead: "+currentPoint.getDeath());
                FileOperators.updateTreeFile(output, currentPoint);
            }
            // Count all
            observation.countTotalHealthyKoala();
            observation.countTotalDeath();
            System.out.println("-----------------------------------------Rescue Report-----------------------------------");
            System.out.println("Info of the observation points: ");
            System.out.println("The number of lost trees: "+ observation.getLostTree());
            System.out.println("The number of healthy koalas: " + observation.getHealthyKoala());
            System.out.println("The number of relocated koalas: "+ SafeHaven.getRelocatedKoala());
            System.out.println("The number of saved injured koalas: " + observation.getRescuedInjuredKoala()) ;
            System.out.println("The cost of rescue actions: " + observation.getRescueCost());
            if(observation.getDeaths()>0) {
                System.out.println("There're "+observation.getDeaths()+" koalas dead");
            } else {
                System.out.println("Congratulations! No koala dies.");
            }
            break outer;
        }
    }
}
