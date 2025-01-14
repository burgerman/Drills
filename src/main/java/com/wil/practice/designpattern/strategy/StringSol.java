package com.wil.practice.designpattern.strategy;

public class StringSol extends GeneticSolution<String>{
    @Override
    public String[] evaluate(String[] evaluation) {
        // Compute data with String type
        if(this.selectSol.select(evaluation)!=null) {
            String[] results = this.updateSol.update(evaluation);
            return results;
        } else {
            return evaluation;
        }
    }

}
