package me.vijaychavda.filemarshal.ui;

import java.io.File;
import javax.swing.JOptionPane;
import me.vijaychavda.filemarshal.settings.DeclutterSettings;
import me.vijaychavda.filemarshal.workers.DeclutterWorker;

public class DeclutterSettingsPanel extends javax.swing.JPanel {

    public DeclutterSettingsPanel() {
        initComponents();
        initOtherThings();
    }

    public final DeclutterSettings getSettings() {
        DeclutterSettings settings = new DeclutterSettings();

        settings.setGroupFormatString(TA_GroupingInfo.getText());
        settings.setOutputPath(TB_OutputPath.getText());
        settings.setMinimumGroupCardinality(CB_DontGroup.isSelected() ? (int) SP_DontGroupCount.getValue() : 0);

        settings.setGroupByType(RB_Type.isSelected());
        settings.setGroupByExtension(RB_Extension.isSelected());
        settings.setGroupByBoth(RB_Both.isSelected());
        settings.setGroupByCustom(RB_Custom.isSelected());

        return settings;
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RBG_OrganizeBy = new javax.swing.ButtonGroup();
        FilePicker = new javax.swing.JFileChooser();
        L_Info1 = new javax.swing.JLabel();
        TB_OutputPath = new javax.swing.JTextField();
        CB_DontGroup = new javax.swing.JCheckBox();
        SP_DontGroupCount = new javax.swing.JSpinner();
        L_Info2 = new javax.swing.JLabel();
        B_Browse = new javax.swing.JButton();
        RB_Type = new javax.swing.JRadioButton();
        RB_Extension = new javax.swing.JRadioButton();
        RB_Both = new javax.swing.JRadioButton();
        RB_Custom = new javax.swing.JRadioButton();
        L_Info3 = new javax.swing.JLabel();
        SP_GroupingInfo = new javax.swing.JScrollPane();
        TA_GroupingInfo = new javax.swing.JTextArea();

        FilePicker.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        L_Info1.setText("Where should all the files organized in folders go?");

        TB_OutputPath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TB_OutputPathFocusLost(evt);
            }
        });

        CB_DontGroup.setSelected(true);
        CB_DontGroup.setText("Don't group files when they are less than:");
        CB_DontGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_DontGroupActionPerformed(evt);
            }
        });

        SP_DontGroupCount.setModel(new javax.swing.SpinnerNumberModel(4, 2, 100, 1));

        L_Info2.setText("Organize my files by:");

        B_Browse.setText("Browse");
        B_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_BrowseActionPerformed(evt);
            }
        });

        RBG_OrganizeBy.add(RB_Type);
        RB_Type.setText("Type");
        RB_Type.setToolTipText("Files will be organized in folders like Documents, Images, Music, etc.");

        RBG_OrganizeBy.add(RB_Extension);
        RB_Extension.setText("Extension");
        RB_Extension.setToolTipText("Files will be organized in folders like JPG, DOC, MP3, etc.");

        RBG_OrganizeBy.add(RB_Both);
        RB_Both.setSelected(true);
        RB_Both.setText("Both");
        RB_Both.setToolTipText("Files will be organized in folders by their types like Documents, Images, Music, and inside them again organized by their extensions like JPG, DOC, MP3, etc.");

        RBG_OrganizeBy.add(RB_Custom);
        RB_Custom.setText("I'll decide");
        RB_Custom.setToolTipText("<html>\n<b>Format example:</b><br>\n'Pics' ai bmp gif ico jpeg jpg png ps psd svg tif tiff<br>\n'Docs' doc docx odt pdf rtf txt wks wps wpd ods xlr xls xlsx key odp pps ppt pptx<br>\n'Videos' 3g2 3gp avi flv m4v mkv mov mp4 mpg mpeg rm swf vob wmv<br>\n'My Songs' aif cda mid midi mp3 mpa ogg wav wma wpl<br>\n</html>");
        RB_Custom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RB_CustomStateChanged(evt);
            }
        });

        L_Info3.setText("<html> In each line, enter in quotes the name of folder, followed by list of space seperated extensions of files that go in that folder. </html>");

        TA_GroupingInfo.setColumns(20);
        TA_GroupingInfo.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        TA_GroupingInfo.setRows(5);
        TA_GroupingInfo.setText("Pics: ai bmp gif ico jpeg jpg png ps psd svg tif tiff\nDocs: doc docx odt pdf rtf txt wks wps wpd ods xlr xls xlsx key odp pps ppt pptx\nVideos: 3g2 3gp avi flv m4v mkv mov mp4 mpg mpeg rm swf vob wmv\nMy Songs: aif cda mid midi mp3 mpa ogg wav wma wpl");
        SP_GroupingInfo.setViewportView(TA_GroupingInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L_Info1)
                            .addComponent(L_Info2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(RB_Both, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(RB_Extension, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(RB_Type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(RB_Custom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CB_DontGroup)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SP_DontGroupCount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 211, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TB_OutputPath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B_Browse)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(L_Info3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(SP_GroupingInfo))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(L_Info1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TB_OutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Browse))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(SP_DontGroupCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB_DontGroup))
                .addGap(18, 18, 18)
                .addComponent(L_Info2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Type)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Extension)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Both)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Custom)
                .addGap(18, 18, 18)
                .addComponent(L_Info3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SP_GroupingInfo)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {B_Browse, TB_OutputPath});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_DontGroup, SP_DontGroupCount});

    }// </editor-fold>//GEN-END:initComponents

    private void RB_CustomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RB_CustomStateChanged
        boolean value = RB_Custom.isSelected();
        L_Info3.setVisible(value);
        SP_GroupingInfo.setVisible(value);
    }//GEN-LAST:event_RB_CustomStateChanged

    private void TB_OutputPathFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_OutputPathFocusLost
        String path = TB_OutputPath.getText();

        if (path.equals(""))
            return;

        File file = new File(path);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "This path does not exist. Please check.");
            TB_OutputPath.requestFocus();
        } else if (!file.isDirectory()) {
            JOptionPane.showMessageDialog(this, "You need to enter a path to a directory.");
            TB_OutputPath.requestFocus();
        }
    }//GEN-LAST:event_TB_OutputPathFocusLost

    private void B_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_BrowseActionPerformed
        FilePicker.showOpenDialog(this);

        TB_OutputPath.setText(FilePicker.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_B_BrowseActionPerformed

    private void CB_DontGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_DontGroupActionPerformed
        SP_DontGroupCount.setEnabled(CB_DontGroup.isSelected());
    }//GEN-LAST:event_CB_DontGroupActionPerformed

    private void initOtherThings() {
        L_Info3.setVisible(false);
        SP_GroupingInfo.setVisible(false);

        String desktopPath = System.getProperty("user.home") + "/Desktop";
        TB_OutputPath.setText(desktopPath);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Browse;
    private javax.swing.JCheckBox CB_DontGroup;
    private javax.swing.JFileChooser FilePicker;
    private javax.swing.JLabel L_Info1;
    private javax.swing.JLabel L_Info2;
    private javax.swing.JLabel L_Info3;
    private javax.swing.ButtonGroup RBG_OrganizeBy;
    private javax.swing.JRadioButton RB_Both;
    private javax.swing.JRadioButton RB_Custom;
    private javax.swing.JRadioButton RB_Extension;
    private javax.swing.JRadioButton RB_Type;
    private javax.swing.JSpinner SP_DontGroupCount;
    private javax.swing.JScrollPane SP_GroupingInfo;
    private javax.swing.JTextArea TA_GroupingInfo;
    private javax.swing.JTextField TB_OutputPath;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
