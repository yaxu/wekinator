/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekimini.gui;

import java.awt.event.ItemEvent;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import org.jdesktop.swingworker.SwingWorker;
import wekimini.DataManager;
import wekimini.DataManager.AutoSelect;
import wekimini.Wekinator;
import java.lang.Integer;
import java.lang.Void;

/**
 *
 * @author louismccallum
 */
public class AutomaticFeaturesEditor extends javax.swing.JFrame {

    Wekinator w;
    
    public AutomaticFeaturesEditor(Wekinator w) {
        initComponents();
        this.w = w;
        setupTable(w.getDataManager().selectedFeatureNames);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void setupTable(String[][] selectedFeatures)
    {
        FeatureTableModel model = new FeatureTableModel(selectedFeatures);
        jTable1.setModel(model);
    }
    
    public class FeatureTableModel extends AbstractTableModel
    {
        private String[][] features;
        
        public FeatureTableModel(String[][] features)
        {
            this.features = features;
        }
        
        @Override
        public int getRowCount() {
            int max = Integer.MIN_VALUE;
            for(String [] f:features)
            {
                if(f.length > max)
                {
                    max = f.length;
                }
            }
            return max;
        }

        @Override
        public int getColumnCount() {
            return features.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return features[columnIndex][rowIndex];
        }
        
        @Override
        public void setValueAt(Object value, int row, int col) {
            features[row][col] = (String)value;
            fireTableCellUpdated(row, col);
        }
        
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
        
        @Override
        public String getColumnName(int col) {
            return "output " + (col + 1);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        wrapperButton = new javax.swing.JButton();
        useAutoButton = new javax.swing.JToggleButton();
        infoGainButton = new javax.swing.JButton();
        randomButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTable1);

        wrapperButton.setText("Wrapper");
        wrapperButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrapperButtonActionPerformed(evt);
            }
        });

        useAutoButton.setText("Use Auto");
        useAutoButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                useAutoButtonStateChanged(evt);
            }
        });
        useAutoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useAutoButtonActionPerformed(evt);
            }
        });

        infoGainButton.setText("InfoGain");
        infoGainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoGainButtonActionPerformed(evt);
            }
        });

        randomButton.setText("Random");
        randomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wrapperButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoGainButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(randomButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(useAutoButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wrapperButton)
                    .addComponent(useAutoButton)
                    .addComponent(infoGainButton)
                    .addComponent(randomButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void select(AutoSelect mode)
    {
        SwingWorker worker = new SwingWorker<AutoSelect,Void>()
        {            
            @Override
            public AutoSelect doInBackground()
            {
                w.getDataManager().selectFeaturesAutomatically(mode, false);
                return mode;
            }
            
            @Override
            public void done()
            {
                setupTable(w.getDataManager().selectedFeatureNames);
            }
        };
        worker.execute();
    }
    
    private void wrapperButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapperButtonActionPerformed
        // TODO add your handling code here:
        select(AutoSelect.WRAPPER);
    }//GEN-LAST:event_wrapperButtonActionPerformed

    
    private void useAutoButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_useAutoButtonStateChanged
        // TODO add your handling code here:
        //w.getDataManager().setUseAutomatic(jToggleButton1.isEnabled());
        ButtonModel buttonModel = useAutoButton.getModel();
        boolean armed = buttonModel.isArmed();
        boolean pressed = buttonModel.isPressed();
        boolean selected = buttonModel.isSelected();
        //System.out.println("Changed: " + armed + "/" + pressed + "/" + selected);
    }//GEN-LAST:event_useAutoButtonStateChanged

    private void useAutoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useAutoButtonActionPerformed
        // TODO add your handling code here:
        useAutoButton.setText(useAutoButton.isSelected() ? "Use Manual" : "Use Auto");
        w.getDataManager().setUseAutomatic((boolean)useAutoButton.isSelected());
        System.out.println("button + " + useAutoButton.isSelected());
    }//GEN-LAST:event_useAutoButtonActionPerformed

    private void infoGainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoGainButtonActionPerformed
        // TODO add your handling code here:
        select(AutoSelect.INFOGAIN);
    }//GEN-LAST:event_infoGainButtonActionPerformed

    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomButtonActionPerformed
        // TODO add your handling code here:
        select(AutoSelect.RANDOM);
    }//GEN-LAST:event_randomButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton infoGainButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton randomButton;
    private javax.swing.JToggleButton useAutoButton;
    private javax.swing.JButton wrapperButton;
    // End of variables declaration//GEN-END:variables
}