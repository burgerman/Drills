package assignment.meo.entity;

import assignment.meo.utils.FileOperators;
import assignment.meo.utils.RandomNum;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Observation {
    ArrayList<Point> observationPoints;
    int numKoalas;
    int rescueCost;
    int deaths;
    int budget;
    int rescuedKoala;
    int lostTree;
    int healthyKoala;
    int rescuedInjuredKoala;

    private Observation(ArrayList<Point> observationPoints, int budget, int lostTree, int numKoalas) {
        this.rescueCost = 0;
        this.deaths = 0;
        this.rescuedKoala = 0;
        this.rescuedInjuredKoala = 0;
        this.observationPoints = observationPoints;
        this.budget = budget;
        this.lostTree = lostTree;
        this.numKoalas = numKoalas;
    }

    /**
     * Set up those observation points
     * @param path
     * @param budget
     * @return
     */
    public static Observation getObservation(String path, int budget) {
        // Parse data from a plain text file like trees.txt
        List<String> lines = FileOperators.readFrom(path);
        ArrayList<Point> points = new ArrayList<>(10);
        int sumLostTree = 0;
        int sumKoalas = 0;
        int lostTree;
        Point point;
        Tree t;
        String[] trees;
        Tree[] treeArr;
        ArrayList<Koala> healthyKoalaList;
        ArrayList<Koala> injuredKoalaList;
        // Parse each line of the file
        for(String line : lines) {
            // Transform each line into an array
            trees = line.split(",");
            treeArr = new Tree[5];
            lostTree = 0;
            for(int i=0; i<trees.length; i++) {
                switch (i) {
                        case 0:
                            t = new Tree(1, RandomNum.getRandomTree(Integer.valueOf(trees[i])));
                            treeArr[i]=t;
                            break;
                        case 1:
                            t = new Tree(0.34, RandomNum.getRandomTree(Integer.valueOf(trees[i])));
                            treeArr[i]=t;
                            break;
                        case 2:
                            t = new Tree(0.9, RandomNum.getRandomTree(Integer.valueOf(trees[i])));
                            treeArr[i]=t;
                            break;
                        case 3:
                            t = new Tree(0.4, RandomNum.getRandomTree(Integer.valueOf(trees[i])));
                            treeArr[i]=t;
                            break;
                        case 4:
                            t = new Tree(0, RandomNum.getRandomTree(Integer.valueOf(trees[i])));
                            treeArr[i]=t;
                            break;
                        default:
                            break;
                }
                lostTree += Integer.valueOf(trees[i])-treeArr[i].getNum();
                sumLostTree += lostTree;
            }
            healthyKoalaList = getHealthyKoalaListAtPoint();
            injuredKoalaList = getInjuredKoalaListAtPoint();
            sumKoalas += (healthyKoalaList.size()+injuredKoalaList.size());
            point = new Point(new ArrayList<>(Arrays.asList(treeArr)), lostTree, RandomNum.getPredator(), healthyKoalaList, injuredKoalaList);
            points.add(point);
        }
        return new Observation(points,budget,sumLostTree, sumKoalas);
    }


    private static ArrayList<Koala> getHealthyKoalaListAtPoint() {
        int num = RandomNum.getHealthyKoala();
        ArrayList<Koala> healthyKoalas = new ArrayList<>(num);
        for(int i=0; i< num; i++) {
            healthyKoalas.add(new Koala(RandomNum.getKoalaAge(), 0));
        }
        return healthyKoalas;
    }

    private static ArrayList<Koala> getInjuredKoalaListAtPoint() {
        int num = RandomNum.getInjuredKoala();
        Koala[] injuredKoalas = new Koala[num];
        for(int i=0; i< num; i++) {
            injuredKoalas[i]=new Koala(RandomNum.getKoalaAge(), 1);
        }
        return new ArrayList<>(Arrays.asList(injuredKoalas));
    }

    public void countTotalDeath() {
        int currentNum = SafeHaven.getSum();
        for(Point point : observationPoints) {
            currentNum += point.getSumKoala();
        }
        setDeaths(numKoalas - currentNum);
    }

    public void countTotalHealthyKoala() {
        int currentNum = SafeHaven.getNumHealthyKoala();
        for(Point point : observationPoints) {
            currentNum += point.healthyKoalas.size();
        }
        setHealthyKoala(currentNum);
    }

    public ArrayList<Point> getObservationPoints() {
        return observationPoints;
    }


    public int getRescueCost() {
        return rescueCost;
    }

    public void setRescueCost(int rescueCost) {
        this.rescueCost = rescueCost;
    }

    public int getDeaths() {
        return deaths;
    }

    private void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRescuedKoala() {
        return rescuedKoala;
    }

    public void setRescuedKoala(int rescuedKoala) {
        this.rescuedKoala = rescuedKoala;
    }

    public int getLostTree() {
        return lostTree;
    }

    public void setLostTree(int lostTree) {
        this.lostTree = lostTree;
    }

    public int getHealthyKoala() {
        return healthyKoala;
    }

    public void setHealthyKoala(int healthyKoala) {
        this.healthyKoala = healthyKoala;
    }

    public int getRescuedInjuredKoala() {
        return rescuedInjuredKoala;
    }

    public void setRescuedInjuredKoala(int rescuedInjuredKoala) {
        this.rescuedInjuredKoala = rescuedInjuredKoala;
    }

    @Override
    public String toString() {
        return "Observation: " +"\n"+
                "observationPoints=" + observationPoints +",\n"+
                "rescue cost=" + rescueCost +",\n"+
                "deaths=" + deaths +",\n"+
                "rescued koalas=" + rescuedKoala +",\n"+
                "the number of lost trees=" + lostTree +",\n"+
                "healthy Koalas=" + healthyKoala +",\n"+
                "rescued injured koalas=" + rescuedInjuredKoala +
                ';';
    }
}
