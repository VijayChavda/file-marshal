package me.vijaychavda.ui;

public class DeclutterSettingsPanel extends javax.swing.JPanel {

    public DeclutterSettingsPanel() {
        initComponents();
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RBG_OrganizeBy = new javax.swing.ButtonGroup();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TA_GroupingInfo = new javax.swing.JTextArea();

        L_Info1.setText("Where should all the files organized in folders go?");

        CB_DontGroup.setSelected(true);
        CB_DontGroup.setText("Don't group files when they are less than:");

        SP_DontGroupCount.setModel(new javax.swing.SpinnerNumberModel(4, 2, 100, 1));

        L_Info2.setText("Organize my files by:");

        B_Browse.setText("Browse");

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

        L_Info3.setText("<html> In each line, enter in quotes the name of folder, followed by list of space seperated extensions of files that go in that folder. </html>");

        TA_GroupingInfo.setColumns(20);
        TA_GroupingInfo.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        TA_GroupingInfo.setRows(5);
        TA_GroupingInfo.setText("'Pics' ai bmp gif ico jpeg jpg png ps psd svg tif tiff\n'Docs' doc docx odt pdf rtf txt wks wps wpd ods xlr xls xlsx key odp pps ppt pptx\n'Videos' 3g2 3gp avi flv m4v mkv mov mp4 mpg mpeg rm swf vob wmv\n'My Songs' aif cda mid midi mp3 mpa ogg wav wma wpl");
        jScrollPane1.setViewportView(TA_GroupingInfo);

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
                    .addComponent(jScrollPane1))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {B_Browse, TB_OutputPath});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_DontGroup, SP_DontGroupCount});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Browse;
    private javax.swing.JCheckBox CB_DontGroup;
    private javax.swing.JLabel L_Info1;
    private javax.swing.JLabel L_Info2;
    private javax.swing.JLabel L_Info3;
    private javax.swing.ButtonGroup RBG_OrganizeBy;
    private javax.swing.JRadioButton RB_Both;
    private javax.swing.JRadioButton RB_Custom;
    private javax.swing.JRadioButton RB_Extension;
    private javax.swing.JRadioButton RB_Type;
    private javax.swing.JSpinner SP_DontGroupCount;
    private javax.swing.JTextArea TA_GroupingInfo;
    private javax.swing.JTextField TB_OutputPath;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
