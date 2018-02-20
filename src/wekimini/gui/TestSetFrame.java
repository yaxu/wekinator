/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekimini.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import wekimini.SupervisedLearningManager;
import wekimini.Wekinator;

/**
 *
 * @author louismccallum
 */
public class TestSetFrame extends javax.swing.JFrame implements ChangeListener {

    private Wekinator w;
    private double currentClass = 1;
    private boolean canUndo = false;
    private static final int NUM_CLASSES = 6;
    private static final int EXAMPLES_PER_CLASS = 20;
    private int numExamplesLeftForClass = EXAMPLES_PER_CLASS;
    private static final String PLEASE_RECORD_STRING = "Please record examples of Gesture ";
    private static final String EXAMPLES_LEFT_STRING = " Examples left to record";
    /**
     * Creates new form TestSetFrame
     */
    public TestSetFrame() {
        initComponents();
    }
    
    public void update(Wekinator w)
    {
        this.w = w;
        currentClass = 1;
        numExamplesLeftForClass = EXAMPLES_PER_CLASS;
        w.getOutputManager().setTestValues(new double[]{currentClass});
        w.getDataManager().addChangeListener(this);
        updateUI();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                w.save();
                dispose();
            }
        });
    }
    
    private void updateUI()
    {
        boolean canNext = numExamplesLeftForClass < 1 && currentClass <= NUM_CLASSES;
        boolean isDone = currentClass > NUM_CLASSES;
        boolean hasStartedGesture = numExamplesLeftForClass < EXAMPLES_PER_CLASS;
        if(!isDone)
        {
            exampleIndexLabel.setText(PLEASE_RECORD_STRING + currentClass);
            examplesLeftLabel.setText(numExamplesLeftForClass + EXAMPLES_LEFT_STRING);
        }
        else
        {
            exampleIndexLabel.setText("All done");
            examplesLeftLabel.setText("Thanks!");
        }
        recordButton.setEnabled(!canNext && !isDone);
        deleteButton.setEnabled(!canNext && !isDone && hasStartedGesture && canUndo);
        nextButton.setEnabled(canNext && !isDone);
        redoButton.setEnabled(!isDone && hasStartedGesture);
        prevButton.setEnabled(!isDone && currentClass > 1);
        doneButton.setVisible(isDone);
    }

    private void updateFromModel()
    {
        numExamplesLeftForClass = EXAMPLES_PER_CLASS - w.getDataManager().getNumExamplesOfClassInTestSet(currentClass);
        if(numExamplesLeftForClass <= 0)
        {
            numExamplesLeftForClass = 0;
            recordButton.setText("Start Recording Test Set");
            w.getSupervisedLearningManager().setRecordingState(SupervisedLearningManager.RecordingState.NOT_RECORDING);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deleteButton = new javax.swing.JButton();
        recordButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        exampleIndexLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();
        examplesLeftLabel = new javax.swing.JLabel();
        doneButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        deleteButton.setText("Delete Last Round");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        recordButton.setText("Start Recording Test Set");
        recordButton.setToolTipText("");
        recordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Record Your Test Set");

        exampleIndexLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exampleIndexLabel.setText("Please record examples of Gesture 1");

        nextButton.setText("Next Gesture ->");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        examplesLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        examplesLeftLabel.setText("200 Examples left to record");

        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        redoButton.setText("Redo Gesture");
        redoButton.setEnabled(false);
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoButtonActionPerformed(evt);
            }
        });

        prevButton.setText("<- Prev Gesture");
        prevButton.setEnabled(false);
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exampleIndexLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(examplesLeftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(redoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(doneButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(recordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(prevButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(17, 17, 17))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(exampleIndexLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(examplesLeftLabel)
                .addGap(5, 5, 5)
                .addComponent(recordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doneButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordButtonActionPerformed
        // TODO add your handling code here:
        if(w.getSupervisedLearningManager().getRecordingState() != SupervisedLearningManager.RecordingState.RECORDING_TEST)
       {
           canUndo = true;
           recordButton.setText("Stop Recording Test Set");
           w.getDataManager().setDeleteTestSetUntilIndex();
           w.getSupervisedLearningManager().setRecordingState(SupervisedLearningManager.RecordingState.RECORDING_TEST);
       }
       else
       {
           recordButton.setText("Start Recording Test Set");
           w.getSupervisedLearningManager().setRecordingState(SupervisedLearningManager.RecordingState.NOT_RECORDING);
       }
    }//GEN-LAST:event_recordButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        numExamplesLeftForClass = EXAMPLES_PER_CLASS;
        currentClass++;
        w.getOutputManager().setTestValues(new double[]{currentClass});
        updateUI();
        
    }//GEN-LAST:event_nextButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        w.getDataManager().deleteLastTestSetRound();
        canUndo = false;
        updateFromModel();
        updateUI();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add your handling code here:
        w.save();
        dispose();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        // TODO add your handling code here:
        w.getDataManager().deleteTestDataWithClass(currentClass);
        updateFromModel();
        updateUI();
    }//GEN-LAST:event_redoButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
        currentClass--;
        w.getOutputManager().setTestValues(new double[]{currentClass});
        updateFromModel();
        updateUI();
        
    }//GEN-LAST:event_prevButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TestSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestSetFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestSetFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JLabel exampleIndexLabel;
    private javax.swing.JLabel examplesLeftLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton recordButton;
    private javax.swing.JButton redoButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void stateChanged(ChangeEvent e) {
        //System.out.println("state changed");
        updateFromModel();
        updateUI();
    }
}
