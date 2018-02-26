package me.vijaychavda.filemarshal.settings;

public class DeclutterSettings {

    private boolean modeMove;
    private boolean modeCopy;
    private boolean modeSimulation;

    private String outputPath;
    private String groupFormatString;

    private int minimumGroupCardinality;
    private String ungroupedName;
    private String noExtensionName;
    private String smallGroupGroup;

    private boolean groupByType;
    private boolean groupByExtension;
    private boolean groupByBoth;
    private boolean groupByCustom;

    public boolean isModeMove() {
        return modeMove;
    }

    public void setModeMove(boolean modeMove) {
        this.modeMove = modeMove;
    }

    public boolean isModeCopy() {
        return modeCopy;
    }

    public void setModeCopy(boolean modeCopy) {
        this.modeCopy = modeCopy;
    }

    public boolean isModeSimulation() {
        return modeSimulation;
    }

    public void setModeSimulation(boolean modeSimulation) {
        this.modeSimulation = modeSimulation;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getGroupFormatString() {
        return groupFormatString;
    }

    public void setGroupFormatString(String groupFormatString) {
        this.groupFormatString = groupFormatString;
    }

    public int getMinimumGroupCardinality() {
        return minimumGroupCardinality;
    }

    public void setMinimumGroupCardinality(int minimumGroupCardinality) {
        this.minimumGroupCardinality = minimumGroupCardinality;
    }

    public String getUngroupedName() {
        return ungroupedName;
    }

    public void setUngroupedName(String ungroupedName) {
        this.ungroupedName = ungroupedName;
    }

    public String getNoExtensionName() {
        return noExtensionName;
    }

    public void setNoExtensionName(String noExtensionName) {
        this.noExtensionName = noExtensionName;
    }

    public String getSmallGroupGroup() {
        return smallGroupGroup;
    }

    public void setSmallGroupGroup(String smallGroupGroup) {
        this.smallGroupGroup = smallGroupGroup;
    }

    public boolean isGroupByType() {
        return groupByType;
    }

    public void setGroupByType(boolean groupByType) {
        this.groupByType = groupByType;
    }

    public boolean isGroupByExtension() {
        return groupByExtension;
    }

    public void setGroupByExtension(boolean groupByExtension) {
        this.groupByExtension = groupByExtension;
    }

    public boolean isGroupByBoth() {
        return groupByBoth;
    }

    public void setGroupByBoth(boolean groupByBoth) {
        this.groupByBoth = groupByBoth;
    }

    public boolean isGroupByCustom() {
        return groupByCustom;
    }

    public void setGroupByCustom(boolean groupByCustom) {
        this.groupByCustom = groupByCustom;
    }

}
