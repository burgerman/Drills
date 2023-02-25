package assignment.meo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Point {

    BigDecimal foodSupply;
    ArrayList<Tree> trees;
    int shelterNum;
    int lostTree;
    int predator;
    int death;
    int numHealthyKoalas;
    int numInjuredKoalas;
    ArrayList<Koala> healthyKoalas;
    ArrayList<Koala> injuredKoalas;

    public Point(ArrayList<Tree> trees, int lostTree, int predator, ArrayList<Koala> healthyKoalas, ArrayList<Koala> injuredKoalas) {
        this.trees = trees;
        this.lostTree = lostTree;
        this.predator = predator;
        this.healthyKoalas = healthyKoalas;
        this.injuredKoalas = injuredKoalas;
        this.foodSupply = getVolumeFoodSupply(trees);
        this.death = 0;
        this.shelterNum = trees.get(4).num;
        this.numHealthyKoalas=healthyKoalas.size();
        this.numInjuredKoalas=injuredKoalas.size();
    }

    private static BigDecimal getVolumeFoodSupply(ArrayList<Tree> trees) {
        double f =  trees.get(0).edible * trees.get(0).num
                + trees.get(1).edible*trees.get(1).num
                + trees.get(2).edible*trees.get(2).num
                + trees.get(3).edible*trees.get(3).num;
        BigDecimal food = new BigDecimal(f);
        return food.setScale(2,BigDecimal.ROUND_CEILING);
    }

    public int getSumKoala() {
        return healthyKoalas.size()+injuredKoalas.size();
    }

    public BigDecimal getFoodSupply() {
        return foodSupply;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public int getShelterNum() {
        return shelterNum;
    }

    public void setShelterNum(int shelterNum) {
        this.shelterNum = shelterNum;
    }

    public int getLostTree() {
        return lostTree;
    }

    public void setLostTree(int lostTree) {
        this.lostTree = lostTree;
    }

    public int getPredator() {
        return predator;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public void setPredator(int predator) {
        this.predator = predator;
    }

    public ArrayList<Koala> getHealthyKoalas() {
        return healthyKoalas;
    }

    public void setHealthyKoalas(ArrayList<Koala> healthyKoalas) {
        this.healthyKoalas = healthyKoalas;
    }

    public ArrayList<Koala> getInjuredKoalas() {
        return injuredKoalas;
    }

    public void setInjuredKoalas(ArrayList<Koala> injuredKoalas) {
        this.injuredKoalas = injuredKoalas;
    }

    public int getNumHealthyKoalas() {
        return healthyKoalas.size();
    }

    public int getNumInjuredKoalas() { return injuredKoalas.size(); }

    public void setNumHealthyKoalas(int numHealthyKoalas) {
        this.numHealthyKoalas = numHealthyKoalas;
    }

    public void setNumInjuredKoalas(int numInjuredKoalas) {
        this.numInjuredKoalas = numInjuredKoalas;
    }

    @Override
    public String toString() {
        return "Point: " + "\n"+
                "foodSupply=" + foodSupply +",\n"+
                "trees={" + "Manna Gum "+trees.get(0)+"Swamp Gum "+trees.get(1)+"Blue Gum "+trees.get(2)+"River Red Gum "+trees.get(3)+"Wattle "+trees.get(4)+"},\n"+
                "shelterNum=" + shelterNum +",\n"+
                "lostTree=" + lostTree +",\n"+
                "predator=" + predator +",\n"+
                "death=" + death +",\n"+
                "numHealthyKoalas=" + numHealthyKoalas +",\n"+
                "numInjuredKoalas=" + numInjuredKoalas +",\n"+
                "healthyKoalas=" + healthyKoalas +",\n"+
                "injuredKoalas=" +injuredKoalas+
                ';';
    }
}
