/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekimini.gui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import wekimini.Wekinator;
import wekimini.WekinatorSupervisedLearningController;
import wekimini.kadenze.FeaturnatorLogger;
import wekimini.kadenze.KadenzeLogging;
import wekimini.modifiers.Feature;

/**
 *
 * @author louismccallum
 */
public class Study2SubmissionFrame extends javax.swing.JFrame {

    private Wekinator w;
    private SubmissionDelegate delegate;
    private Feature[] originalFeatures;
    private static final double MAX_LARGE_SET_SIZE = 300.0;

    public Study2SubmissionFrame() {
        initComponents();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dispose();
                w.save();
            }
        });
    }
    
    public void update(Wekinator w, SubmissionDelegate d)
    {
        this.w = w;
        this.delegate = d;
        originalFeatures = w.getDataManager().featureManager.getFeatureGroups().get(0).getCurrentFeatures();
    }
    
    private void resetFeatures()
    {
        w.getDataManager().featureManager.getFeatureGroups().get(0).clearAdded();
        for(Feature f:originalFeatures)
        {
            w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey(f.name);
        }
        delegate.featureSetUpdated();
    }
    
    private void useRawSet()
    {
        if(KadenzeLogging.getLogger() instanceof FeaturnatorLogger)
        {
            ((FeaturnatorLogger)KadenzeLogging.getLogger()).logEvaluatingRawFeatures(w);
        }
        w.getDataManager().featureManager.getFeatureGroups().get(0).clearAdded();
        w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey("AccX");
        w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey("AccY");
        w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey("AccZ");
        w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey("GyroX");
        w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey("GyroY");
        w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey("GyroZ");
        delegate.featureSetUpdated();
    }
    
    private void useLargeSet()
    {
        if(KadenzeLogging.getLogger() instanceof FeaturnatorLogger)
        {
            ((FeaturnatorLogger)KadenzeLogging.getLogger()).logEvaluatingAllFeatures(w);
        }
        w.getDataManager().featureManager.getFeatureGroups().get(0).clearAdded();
        List<Feature> library = w.getDataManager().featureManager.getFeatureGroups().get(0).getLibrary();
        if(library.size() > MAX_LARGE_SET_SIZE)
        {
            double threshold = (double)(MAX_LARGE_SET_SIZE / (double)(library.size()));
            Feature[] above = w.getDataManager().getInfoGainRankings(0, threshold, true);
            for(Feature f:above)
            {
                w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey(f.name);
            }
        }
        else
        {
            for(Feature f:library)
            {
                w.getDataManager().featureManager.getFeatureGroups().get(0).addFeatureForKey(f.name);
            }
        }
        
        delegate.featureSetUpdated();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JButton testSetButton = new javax.swing.JButton();
        rawFeaturesButton = new javax.swing.JButton();
        largeSetButton = new javax.swing.JButton();
        resetFeatures = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        testSetButton.setText("Record Test Set");
        testSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testSetButtonActionPerformed(evt);
            }
        });

        rawFeaturesButton.setText("Use Raw Features");
        rawFeaturesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rawFeaturesButtonActionPerformed(evt);
            }
        });

        largeSetButton.setText("Use Large Feature Set");
        largeSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                largeSetButtonActionPerformed(evt);
            }
        });

        resetFeatures.setText("Use Your Features");
        resetFeatures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFeaturesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(testSetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rawFeaturesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(largeSetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetFeatures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testSetButton)
                .addGap(18, 18, 18)
                .addComponent(rawFeaturesButton)
                .addGap(18, 18, 18)
                .addComponent(largeSetButton)
                .addGap(18, 18, 18)
                .addComponent(resetFeatures)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rawFeaturesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rawFeaturesButtonActionPerformed
        // TODO add your handling code here:
        useRawSet();
    }//GEN-LAST:event_rawFeaturesButtonActionPerformed

    private void largeSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_largeSetButtonActionPerformed
        // TODO add your handling code here:
       
        useLargeSet();
    }//GEN-LAST:event_largeSetButtonActionPerformed

    private void testSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testSetButtonActionPerformed
        // TODO add your handling code here:
        delegate.recordTestSet();
    }//GEN-LAST:event_testSetButtonActionPerformed

    private void resetFeaturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFeaturesActionPerformed
        // TODO add your handling code here:
        resetFeatures();
    }//GEN-LAST:event_resetFeaturesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Study2SubmissionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Study2SubmissionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Study2SubmissionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Study2SubmissionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Study2SubmissionFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton largeSetButton;
    private javax.swing.JButton rawFeaturesButton;
    private javax.swing.JButton resetFeatures;
    // End of variables declaration//GEN-END:variables
}
