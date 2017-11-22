/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekimini.featureanalysis;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.WrapperSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.core.Instances;
import weka.classifiers.Classifier;

/**
 *
 * @author louismccallum
 */
public class WrapperSelector implements FeatureSelector {
    
    public Classifier classifier;
    
    @Override
    public int[] getFeaturesForInstances(Instances instances)
    {
        try {
            AttributeSelection attsel = new AttributeSelection();
            WrapperSubsetEval eval = new WrapperSubsetEval();
            GreedyStepwise search = new GreedyStepwise();
            eval.setClassifier(classifier);
            search.setSearchBackwards(true);
            attsel.setEvaluator(eval);
            attsel.setSearch(search);
            attsel.SelectAttributes(instances);
            int[] indices = attsel.selectedAttributes();
            return indices;
        } catch (Exception ex) {
            Logger.getLogger(CFSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new int[0];
    }
    
}