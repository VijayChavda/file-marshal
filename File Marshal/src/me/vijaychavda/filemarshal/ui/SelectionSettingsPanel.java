package me.vijaychavda.filemarshal.ui;

import javax.swing.JCheckBox;
import me.vijaychavda.filemarshal.settings.SelectionSettings;

public class SelectionSettingsPanel extends javax.swing.JPanel {

    private static final long _1GB = 1073741824L;
    private static final long _1MB = 1048576L;
    private static final long _1KB = 1024L;

    public SelectionSettingsPanel() {
        initComponents();
    }

    public final SelectionSettings getSettings() {
        long customLowerSize, customUpperSize, sizeLowerLimit, sizeUpperLimit;

        customLowerSize = (long) SP_GreaterThan.getValue()
            * (CB_GreaterThan.getSelectedIndex() == 0 ? _1GB
            : CB_GreaterThan.getSelectedIndex() == 1 ? _1MB
            : CB_GreaterThan.getSelectedIndex() == 2 ? _1KB : 1L);

        customUpperSize = (long) SP_LessThan.getValue()
            * (CB_LessThan.getSelectedIndex() == 0 ? _1GB
            : CB_LessThan.getSelectedIndex() == 1 ? _1MB
            : CB_LessThan.getSelectedIndex() == 2 ? _1KB : 1L);

        sizeLowerLimit
            = CB_Small.isSelected() || CB_AnySize.isSelected() ? 0L
            : CB_Medium.isSelected() ? 10L * _1MB
            : CB_Large.isSelected() ? 100L * _1MB
            : customLowerSize;

        sizeUpperLimit
            = CB_Small.isSelected() ? 10L * _1MB
            : CB_Medium.isSelected() ? 100L * _1MB
            : CB_Large.isSelected() || CB_AnySize.isSelected() ? Long.MAX_VALUE
            : customUpperSize;

        SelectionSettings settings = new SelectionSettings();
        settings.setExtensionCS(TB_Extensions.getText());
        settings.setSizeLowerLimit(sizeLowerLimit);
        settings.setSizeUpperLimit(sizeUpperLimit);

        return settings;
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RBG_FileSize = new javax.swing.ButtonGroup();
        L_Info2 = new javax.swing.JLabel();
        P_FilterType = new javax.swing.JPanel();
        L_Info21 = new javax.swing.JLabel();
        CB_CustomType = new javax.swing.JCheckBox();
        CB_Pictures = new javax.swing.JCheckBox();
        CB_Others = new javax.swing.JCheckBox();
        CB_Videos = new javax.swing.JCheckBox();
        CB_Audios = new javax.swing.JCheckBox();
        CB_TypeAll = new javax.swing.JCheckBox();
        SP_Extensions = new javax.swing.JScrollPane();
        TB_Extensions = new javax.swing.JTextArea();
        Seperator21 = new javax.swing.JSeparator();
        Seperator22 = new javax.swing.JSeparator();
        Seperator2 = new javax.swing.JSeparator();
        P_FilterSize = new javax.swing.JPanel();
        CB_CustomSize = new javax.swing.JCheckBox();
        CB_Large = new javax.swing.JCheckBox();
        CB_Medium = new javax.swing.JCheckBox();
        CB_Small = new javax.swing.JCheckBox();
        CB_AnySize = new javax.swing.JCheckBox();
        SP_GreaterThan = new javax.swing.JSpinner();
        L_SizeSign = new javax.swing.JLabel();
        SP_LessThan = new javax.swing.JSpinner();
        CB_GreaterThan = new javax.swing.JComboBox<>();
        CB_LessThan = new javax.swing.JComboBox<>();
        L_Info22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        L_Info2.setText("What kind of files should be considered?");

        P_FilterType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        L_Info21.setText("Files of type:");

        CB_CustomType.setText("Custom");
        CB_CustomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileTypeSelectionChanged(evt);
            }
        });

        CB_Pictures.setText("Pictures");
        CB_Pictures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileTypeSelectionChanged(evt);
            }
        });

        CB_Others.setText("Others");
        CB_Others.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileTypeSelectionChanged(evt);
            }
        });

        CB_Videos.setText("Video");
        CB_Videos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileTypeSelectionChanged(evt);
            }
        });

        CB_Audios.setText("Audio");
        CB_Audios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileTypeSelectionChanged(evt);
            }
        });

        CB_TypeAll.setSelected(true);
        CB_TypeAll.setText("Any");
        CB_TypeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileTypeSelectionChanged(evt);
            }
        });

        TB_Extensions.setEditable(false);
        TB_Extensions.setColumns(20);
        TB_Extensions.setLineWrap(true);
        TB_Extensions.setRows(3);
        TB_Extensions.setTabSize(1);
        TB_Extensions.setText(".*");
        TB_Extensions.setWrapStyleWord(true);
        SP_Extensions.setViewportView(TB_Extensions);

        javax.swing.GroupLayout P_FilterTypeLayout = new javax.swing.GroupLayout(P_FilterType);
        P_FilterType.setLayout(P_FilterTypeLayout);
        P_FilterTypeLayout.setHorizontalGroup(
            P_FilterTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_FilterTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_Extensions, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(CB_CustomType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator22)
                    .addComponent(CB_Others, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Pictures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Videos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Audios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator21)
                    .addComponent(CB_TypeAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Info21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        P_FilterTypeLayout.setVerticalGroup(
            P_FilterTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info21)
                .addGap(18, 18, 18)
                .addComponent(CB_TypeAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seperator21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Audios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Videos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Pictures)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Others)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seperator22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_CustomType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SP_Extensions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Seperator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        P_FilterSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RBG_FileSize.add(CB_CustomSize);
        CB_CustomSize.setText("Custom");
        CB_CustomSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileSizeSelectionChanged(evt);
            }
        });

        RBG_FileSize.add(CB_Large);
        CB_Large.setText("Large");
        CB_Large.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileSizeSelectionChanged(evt);
            }
        });

        RBG_FileSize.add(CB_Medium);
        CB_Medium.setText("Medium");
        CB_Medium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileSizeSelectionChanged(evt);
            }
        });

        RBG_FileSize.add(CB_Small);
        CB_Small.setText("Small");
        CB_Small.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileSizeSelectionChanged(evt);
            }
        });

        RBG_FileSize.add(CB_AnySize);
        CB_AnySize.setSelected(true);
        CB_AnySize.setText("Any");
        CB_AnySize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileSizeSelectionChanged(evt);
            }
        });

        SP_GreaterThan.setModel(new javax.swing.SpinnerNumberModel(500L, 1L, null, 1L));
        SP_GreaterThan.setEnabled(false);

        L_SizeSign.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_SizeSign.setText("<   size   < ");
        L_SizeSign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        SP_LessThan.setModel(new javax.swing.SpinnerNumberModel(1L, 1L, null, 1L));
        SP_LessThan.setEnabled(false);

        CB_GreaterThan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GB", "MB", "KB", "B" }));
        CB_GreaterThan.setSelectedIndex(1);
        CB_GreaterThan.setEnabled(false);

        CB_LessThan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GB", "MB", "KB", "B" }));
        CB_LessThan.setEnabled(false);

        L_Info22.setText("Files of size:");

        jLabel1.setText("< 10 MB");

        jLabel2.setText("[ 10, 100 ] MB");

        jLabel3.setText("> 100 MB");

        javax.swing.GroupLayout P_FilterSizeLayout = new javax.swing.GroupLayout(P_FilterSize);
        P_FilterSize.setLayout(P_FilterSizeLayout);
        P_FilterSizeLayout.setHorizontalGroup(
            P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Info22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_CustomSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(P_FilterSizeLayout.createSequentialGroup()
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(L_SizeSign, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_LessThan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_LessThan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(P_FilterSizeLayout.createSequentialGroup()
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_Large, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Medium, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Small, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(CB_AnySize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        P_FilterSizeLayout.setVerticalGroup(
            P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info22)
                .addGap(18, 18, 18)
                .addComponent(CB_AnySize)
                .addGap(18, 18, 18)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Small)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Medium)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Large)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(CB_CustomSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(P_FilterSizeLayout.createSequentialGroup()
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(SP_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_LessThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CB_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CB_LessThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(L_SizeSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(L_Info2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(P_FilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Seperator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P_FilterSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_Info2)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Seperator2)
                    .addComponent(P_FilterType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P_FilterSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void FileTypeSelectionChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileTypeSelectionChanged
        TB_Extensions.setText("");
        TB_Extensions.setEditable(false);

        JCheckBox cb = (JCheckBox) evt.getSource();

        if (cb == CB_Audios || cb == CB_Videos || cb == CB_Pictures || cb == CB_Others) {
            CB_TypeAll.setSelected(false);
            CB_CustomType.setSelected(false);
        }

        if (cb == CB_TypeAll) {
            CB_Audios.setSelected(false);
            CB_Videos.setSelected(false);
            CB_Pictures.setSelected(false);
            CB_Others.setSelected(false);
            CB_CustomType.setSelected(false);
        }

        if (cb == CB_CustomType) {
            CB_Audios.setSelected(false);
            CB_Videos.setSelected(false);
            CB_Pictures.setSelected(false);
            CB_Others.setSelected(false);
            CB_TypeAll.setSelected(false);
        }

        if (!CB_TypeAll.isSelected()
            && !CB_Audios.isSelected()
            && !CB_Videos.isSelected()
            && !CB_Pictures.isSelected()
            && !CB_Others.isSelected()
            && !CB_CustomType.isSelected()) {
            CB_TypeAll.setSelected(true);
        }

        if (CB_Audios.isSelected())
            TB_Extensions.append(".aif .cda .mid .midi .mp3 .mpa .ogg .wav .wma .wpl ");

        if (CB_Videos.isSelected())
            TB_Extensions.append(".3g2 .3gp .avi .flv .m4v .mkv .mov .mp4 .mpg .mpeg .rm .swf .vob .wmv ");

        if (CB_Pictures.isSelected())
            TB_Extensions.append(".ai .bmp .gif .ico .jpeg .jpg .png .ps .psd .svg .tif .tiff ");

        if (CB_Others.isSelected())
            TB_Extensions.append(".doc .docx .odt .pdf .rtf .txt .wks .wps .wpd .ods .xlr .xls .xlsx .key .odp .pps .ppt .pptx .zip .tar.gz .rpm .7z .rar ");

        if (CB_TypeAll.isSelected())
            TB_Extensions.setText(".*");

        if (CB_CustomType.isSelected()) {
            TB_Extensions.setText("");
            TB_Extensions.setEditable(true);
            TB_Extensions.requestFocus();
        }
    }//GEN-LAST:event_FileTypeSelectionChanged

    private void FileSizeSelectionChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileSizeSelectionChanged
        SP_GreaterThan.setEnabled(CB_CustomSize.isSelected());
        SP_LessThan.setEnabled(CB_CustomSize.isSelected());
        CB_GreaterThan.setEnabled(CB_CustomSize.isSelected());
        CB_LessThan.setEnabled(CB_CustomSize.isSelected());
    }//GEN-LAST:event_FileSizeSelectionChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_AnySize;
    private javax.swing.JCheckBox CB_Audios;
    private javax.swing.JCheckBox CB_CustomSize;
    private javax.swing.JCheckBox CB_CustomType;
    private javax.swing.JComboBox<String> CB_GreaterThan;
    private javax.swing.JCheckBox CB_Large;
    private javax.swing.JComboBox<String> CB_LessThan;
    private javax.swing.JCheckBox CB_Medium;
    private javax.swing.JCheckBox CB_Others;
    private javax.swing.JCheckBox CB_Pictures;
    private javax.swing.JCheckBox CB_Small;
    private javax.swing.JCheckBox CB_TypeAll;
    private javax.swing.JCheckBox CB_Videos;
    private javax.swing.JLabel L_Info2;
    private javax.swing.JLabel L_Info21;
    private javax.swing.JLabel L_Info22;
    private javax.swing.JLabel L_SizeSign;
    private javax.swing.JPanel P_FilterSize;
    private javax.swing.JPanel P_FilterType;
    private javax.swing.ButtonGroup RBG_FileSize;
    private javax.swing.JScrollPane SP_Extensions;
    private javax.swing.JSpinner SP_GreaterThan;
    private javax.swing.JSpinner SP_LessThan;
    private javax.swing.JSeparator Seperator2;
    private javax.swing.JSeparator Seperator21;
    private javax.swing.JSeparator Seperator22;
    private javax.swing.JTextArea TB_Extensions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
