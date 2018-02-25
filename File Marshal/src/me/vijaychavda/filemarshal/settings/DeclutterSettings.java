package me.vijaychavda.filemarshal.settings;

public class DeclutterSettings {

    private int minimumGroupCardinality;
    private String outputPath;
    private String groupFormatString;

    private boolean groupByType;
    private boolean groupByExtension;
    private boolean groupByBoth;
    private boolean groupByCustom;

    public int getMinimumGroupCardinality() {
        return minimumGroupCardinality;
    }

    public void setMinimumGroupCardinality(int minimumGroupCardinality) {
        this.minimumGroupCardinality = minimumGroupCardinality;
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
