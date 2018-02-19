package me.vijaychavda.filemarshal.settings;

public class DeclutterSettings {

    private int minimumGroupCardinality;
    private String outputPath;
    private String grouping;

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

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }
}
