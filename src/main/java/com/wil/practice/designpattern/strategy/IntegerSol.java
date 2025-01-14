package com.wil.practice.designpattern.strategy;

public class IntegerSol extends GeneticSolution<Integer>{

    @Override
    public Integer[] evaluate(Integer[] populatedData) {
        // compute data with Integer type
        if(this.selectSol.select(populatedData).size()<1) {
            Integer[] results = this.updateSol.update(populatedData);
            return results;
        } else {
            return populatedData;
        }
    }
}
