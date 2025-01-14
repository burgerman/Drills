package com.wil.practice.designpattern.strategy;

public abstract class GeneticSolution<T> {

    protected SelectSol<T> selectSol;
    protected UpdateSol<T> updateSol;


    public void setSelectSol(SelectSol<T> selectSol) {
        this.selectSol = selectSol;
    }

    public void setUpdateSol(UpdateSol<T> updateSol) {
        this.updateSol = updateSol;
    }

    public abstract T[] evaluate(T[] evaluation);

}
