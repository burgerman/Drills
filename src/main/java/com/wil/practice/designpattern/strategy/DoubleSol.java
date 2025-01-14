package com.wil.practice.designpattern.strategy;

public class DoubleSol extends GeneticSolution<Double>{
    @Override
    public Double[] evaluate(Double[] evaluation) {
        // Compute data with Double type
        if(this.selectSol.select(evaluation).size()<1) {
            Double[] results = this.updateSol.update(evaluation);
            return results;
        } else {
            return evaluation;
        }
    }
}
