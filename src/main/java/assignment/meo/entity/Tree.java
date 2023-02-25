package assignment.meo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Tree {
    double edible;
    int num;

    public Tree(double edible, int num) {
        this.edible = edible;
        this.num = num;
    }

    public double getEdible() {
        return edible;
    }

    public void setEdible(double edible) {
        this.edible = edible;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Tree: " +
                "edible=" + edible +
                ", num=" + num +
                ';';
    }
}
