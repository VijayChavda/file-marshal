package me.vijaychavda.filemarshal.ui;

import me.vijaychavda.filemarshal.settings.DecloneSettings;

public class DecloneSettingsPanel extends javax.swing.JPanel {

    public DecloneSettingsPanel() {
        initComponents();
    }

    public final DecloneSettings getSettings() {
        DecloneSettings settings = new DecloneSettings();

        settings.setUsingNames(CB_Name.isSelected());
        settings.setUsingSize(CB_Size.isSelected());
        settings.setUsingContent(CB_Content.isSelected());

        settings.setNameSimilar(RB_Name1.isSelected());
        settings.setNameCommonWords(RB_Name2.isSelected());
        settings.setNameSimilarCommonWords(RB_Name3.isSelected());
        settings.setNameExactlySame(RB_Name4.isSelected());

        settings.setSizeNoHugeDifference(RB_Size1.isSelected());
        settings.setSizeAlmostSame(RB_Size2.isSelected());
        settings.setSizeExactlySame(RB_Size3.isSelected());

        settings.setContent2p(RB_Content1.isSelected());
        settings.setContent10p(RB_Content2.isSelected());
        settings.setContent20p(RB_Content3.isSelected());
        settings.setContent50p(RB_Content4.isSelected());
        settings.setContent100p(RB_Content5.isSelected());

        return settings;
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RBG_Name = new javax.swing.ButtonGroup();
        RBG_Size = new javax.swing.ButtonGroup();
        RBG_Content = new javax.swing.ButtonGroup();
        L_Info31 = new javax.swing.JLabel();
        CB_Name = new javax.swing.JCheckBox();
        CB_Size = new javax.swing.JCheckBox();
        CB_Content = new javax.swing.JCheckBox();
        P_AdvanceSettings = new javax.swing.JPanel();
        P_CustomNameSize = new javax.swing.JPanel();
        L_Name = new javax.swing.JLabel();
        L_Size = new javax.swing.JLabel();
        RB_Name1 = new javax.swing.JRadioButton();
        RB_Name2 = new javax.swing.JRadioButton();
        RB_Name3 = new javax.swing.JRadioButton();
        RB_Name4 = new javax.swing.JRadioButton();
        RB_Size1 = new javax.swing.JRadioButton();
        RB_Size2 = new javax.swing.JRadioButton();
        RB_Size3 = new javax.swing.JRadioButton();
        P_CustomContent = new javax.swing.JPanel();
        L_Content = new javax.swing.JLabel();
        RB_Content1 = new javax.swing.JRadioButton();
        RB_Content2 = new javax.swing.JRadioButton();
        RB_Content3 = new javax.swing.JRadioButton();
        RB_Content4 = new javax.swing.JRadioButton();
        RB_Content5 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        L_Info31.setText("Which parameters should be considered to consider files as duplicates?");

        CB_Name.setSelected(true);
        CB_Name.setText("Name");
        CB_Name.setOpaque(false);
        CB_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramChanged(evt);
            }
        });

        CB_Size.setText("Size");
        CB_Size.setOpaque(false);
        CB_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramChanged(evt);
            }
        });

        CB_Content.setText("Content (Slow for large files)");
        CB_Content.setOpaque(false);
        CB_Content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paramChanged(evt);
            }
        });

        P_AdvanceSettings.setOpaque(false);
        P_AdvanceSettings.setLayout(new java.awt.GridLayout(1, 0));

        L_Name.setText("Name");

        L_Size.setText("Size");

        RBG_Name.add(RB_Name1);
        RB_Name1.setText("Similar");

        RBG_Name.add(RB_Name2);
        RB_Name2.setSelected(true);
        RB_Name2.setText("Common words");

        RBG_Name.add(RB_Name3);
        RB_Name3.setText("Similar common words");

        RBG_Name.add(RB_Name4);
        RB_Name4.setText("Exactly same");

        RBG_Size.add(RB_Size1);
        RB_Size1.setSelected(true);
        RB_Size1.setText("No huge difference");
        RB_Size1.setEnabled(false);

        RBG_Size.add(RB_Size2);
        RB_Size2.setText("Almost same");
        RB_Size2.setEnabled(false);

        RBG_Size.add(RB_Size3);
        RB_Size3.setText("Exactly same");
        RB_Size3.setEnabled(false);

        javax.swing.GroupLayout P_CustomNameSizeLayout = new javax.swing.GroupLayout(P_CustomNameSize);
        P_CustomNameSize.setLayout(P_CustomNameSizeLayout);
        P_CustomNameSizeLayout.setHorizontalGroup(
            P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomNameSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Name)
                    .addComponent(L_Size)
                    .addGroup(P_CustomNameSizeLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RB_Size1)
                            .addComponent(RB_Name2)
                            .addComponent(RB_Name4)
                            .addComponent(RB_Size2)
                            .addComponent(RB_Size3)
                            .addComponent(RB_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RB_Name3))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        P_CustomNameSizeLayout.setVerticalGroup(
            P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomNameSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Name1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Name2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Name3)
                .addGap(3, 3, 3)
                .addComponent(RB_Name4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(L_Size)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RB_Size1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Size2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Size3)
                .addContainerGap())
        );

        P_AdvanceSettings.add(P_CustomNameSize);

        L_Content.setText("Content");

        RBG_Content.add(RB_Content1);
        RB_Content1.setSelected(true);
        RB_Content1.setText("2 %");
        RB_Content1.setEnabled(false);

        RBG_Content.add(RB_Content2);
        RB_Content2.setText("10 %");
        RB_Content2.setEnabled(false);

        RBG_Content.add(RB_Content3);
        RB_Content3.setText("20 %");
        RB_Content3.setEnabled(false);

        RBG_Content.add(RB_Content4);
        RB_Content4.setText("50 %");
        RB_Content4.setEnabled(false);

        RBG_Content.add(RB_Content5);
        RB_Content5.setText("100 %");
        RB_Content5.setEnabled(false);

        jLabel4.setText("Least");

        jLabel5.setText("Very less");

        jLabel6.setText("Less");

        jLabel7.setText("Half");

        jLabel8.setText("Complete");

        javax.swing.GroupLayout P_CustomContentLayout = new javax.swing.GroupLayout(P_CustomContent);
        P_CustomContent.setLayout(P_CustomContentLayout);
        P_CustomContentLayout.setHorizontalGroup(
            P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Content)
                    .addGroup(P_CustomContentLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        P_CustomContentLayout.setVerticalGroup(
            P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Content)
                .addGap(24, 24, 24)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content5)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_AdvanceSettings.add(P_CustomContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Size, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L_Info31)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(P_AdvanceSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_Info31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Size)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Content)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_AdvanceSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void paramChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paramChanged
        if (!CB_Name.isSelected() && !CB_Size.isSelected() && !CB_Content.isSelected())
            CB_Size.setSelected(true);

        enableDisableRBs();
    }//GEN-LAST:event_paramChanged

    void enableDisableRBs() {
        boolean isName = CB_Name.isSelected();
        RB_Name1.setEnabled(isName);
        RB_Name2.setEnabled(isName);
        RB_Name3.setEnabled(isName);
        RB_Name4.setEnabled(isName);

        boolean isSize = CB_Size.isSelected();
        RB_Size1.setEnabled(isSize);
        RB_Size2.setEnabled(isSize);
        RB_Size3.setEnabled(isSize);

        boolean isContent = CB_Content.isSelected();
        RB_Content1.setEnabled(isContent);
        RB_Content2.setEnabled(isContent);
        RB_Content3.setEnabled(isContent);
        RB_Content4.setEnabled(isContent);
        RB_Content5.setEnabled(isContent);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_Content;
    private javax.swing.JCheckBox CB_Name;
    private javax.swing.JCheckBox CB_Size;
    private javax.swing.JLabel L_Content;
    private javax.swing.JLabel L_Info31;
    private javax.swing.JLabel L_Name;
    private javax.swing.JLabel L_Size;
    private javax.swing.JPanel P_AdvanceSettings;
    private javax.swing.JPanel P_CustomContent;
    private javax.swing.JPanel P_CustomNameSize;
    private javax.swing.ButtonGroup RBG_Content;
    private javax.swing.ButtonGroup RBG_Name;
    private javax.swing.ButtonGroup RBG_Size;
    private javax.swing.JRadioButton RB_Content1;
    private javax.swing.JRadioButton RB_Content2;
    private javax.swing.JRadioButton RB_Content3;
    private javax.swing.JRadioButton RB_Content4;
    private javax.swing.JRadioButton RB_Content5;
    private javax.swing.JRadioButton RB_Name1;
    private javax.swing.JRadioButton RB_Name2;
    private javax.swing.JRadioButton RB_Name3;
    private javax.swing.JRadioButton RB_Name4;
    private javax.swing.JRadioButton RB_Size1;
    private javax.swing.JRadioButton RB_Size2;
    private javax.swing.JRadioButton RB_Size3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
