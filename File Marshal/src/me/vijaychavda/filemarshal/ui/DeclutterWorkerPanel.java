package me.vijaychavda.filemarshal.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.FileInfo;
import me.vijaychavda.filemarshal.settings.DecloneSettings;
import me.vijaychavda.filemarshal.workers.DeclutterWorker;
import me.vijaychavda.filemarshal.workers.ScanSourcesWorker;

public class DeclutterWorkerPanel extends javax.swing.JPanel {

    private ArrayList<File> allFiles;

    public DeclutterWorkerPanel() {
        initComponents();
    }

    public void start() {
        step1();
    }

    private void step1() {
        ScanSourcesWorker scanSourcesWorker = new ScanSourcesWorker() {
            @Override
            protected void process(List<String> chunks) {
                chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

                ProgressBar.setValue(getProgress());
            }

            @Override
            protected void done() {
                try {
                    allFiles = get();
                    CB_Step1.setSelected(true);
                    step2();
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(DecloneWorkerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        scanSourcesWorker.execute();
    }

    private void step2() {
        DeclutterWorker declutterWorker = new DeclutterWorker(allFiles) {
            @Override
            protected void process(List<String> chunks) {
                chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

                ProgressBar.setValue(getProgress());
                
                if(chunks.contains("Done!\n")) {
                    CB_Step2.setSelected(true);
                }
            }

            @Override
            protected void done() {
                CB_Step3.setSelected(true);
            }
        };
        declutterWorker.execute();
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L_Info = new javax.swing.JLabel();
        CB_Step1 = new javax.swing.JCheckBox();
        CB_Step2 = new javax.swing.JCheckBox();
        CB_Step3 = new javax.swing.JCheckBox();
        ProgressBar = new javax.swing.JProgressBar();
        SP_Status = new javax.swing.JScrollPane();
        TA_Status = new javax.swing.JTextArea();
        Seperator1 = new javax.swing.JSeparator();
        L_Step1 = new javax.swing.JLabel();
        L_Step2 = new javax.swing.JLabel();
        L_Step3 = new javax.swing.JLabel();

        L_Info.setText("<html> The magical elves are doing their job. Please wait :) </html>");

        CB_Step1.setEnabled(false);

        CB_Step2.setEnabled(false);

        CB_Step3.setEnabled(false);

        ProgressBar.setToolTipText("Estimated progress.");

        TA_Status.setEditable(false);
        TA_Status.setColumns(20);
        TA_Status.setRows(5);
        SP_Status.setViewportView(TA_Status);

        L_Step1.setText("Scanning sources");

        L_Step2.setText("Classifying files");

        L_Step3.setText("Moving your files to a better palce :)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_Status)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(L_Info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_Step2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(L_Step1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(L_Step1)
                    .addComponent(CB_Step1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_Step2)
                    .addComponent(L_Step2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_Step3)
                    .addComponent(L_Step3))
                .addGap(18, 18, 18)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SP_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_Step1;
    private javax.swing.JCheckBox CB_Step2;
    private javax.swing.JCheckBox CB_Step3;
    private javax.swing.JLabel L_Info;
    private javax.swing.JLabel L_Step1;
    private javax.swing.JLabel L_Step2;
    private javax.swing.JLabel L_Step3;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JScrollPane SP_Status;
    private javax.swing.JSeparator Seperator1;
    private javax.swing.JTextArea TA_Status;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
