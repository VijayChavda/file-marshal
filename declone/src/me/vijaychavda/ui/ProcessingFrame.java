package me.vijaychavda.ui;

public class ProcessingFrame extends javax.swing.JFrame {

    public ProcessingFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L_Info = new javax.swing.JLabel();
        CB_ScanningFiles = new javax.swing.JCheckBox();
        CB_AnalyzingFiles = new javax.swing.JCheckBox();
        CB_FindingDuplicates = new javax.swing.JCheckBox();
        ProgressBar = new javax.swing.JProgressBar();
        SP_Status = new javax.swing.JScrollPane();
        TA_Status = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        L_Info.setText("<html> The magical elves are doing their job.<br> Why don't you make sure Dobby gets his wages? Hermionie will be pleased :) </html>");

        CB_ScanningFiles.setText("Scanning files");
        CB_ScanningFiles.setEnabled(false);

        CB_AnalyzingFiles.setText("Analizing files");
        CB_AnalyzingFiles.setEnabled(false);

        CB_FindingDuplicates.setText("Finding duplicates");
        CB_FindingDuplicates.setEnabled(false);

        ProgressBar.setToolTipText("Estimated progress.");

        TA_Status.setEditable(false);
        TA_Status.setColumns(20);
        TA_Status.setRows(5);
        SP_Status.setViewportView(TA_Status);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SP_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(L_Info, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_ScanningFiles)
                            .addComponent(CB_AnalyzingFiles)
                            .addComponent(CB_FindingDuplicates))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_ScanningFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CB_AnalyzingFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CB_FindingDuplicates)
                .addGap(18, 18, 18)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SP_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_AnalyzingFiles;
    private javax.swing.JCheckBox CB_FindingDuplicates;
    private javax.swing.JCheckBox CB_ScanningFiles;
    private javax.swing.JLabel L_Info;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JScrollPane SP_Status;
    private javax.swing.JTextArea TA_Status;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
