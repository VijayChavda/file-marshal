package me.vijaychavda;

public class CompareSettings {

    private boolean usingNames;
    private boolean usingSize;
    private boolean usingContent;

    private float nameDelta;
    private float sizeDelta;
    private float contentVolumePercent;

    public boolean isUsingNames() {
        return usingNames;
    }

    public void setUsingNames(boolean usingNames) {
        this.usingNames = usingNames;
    }

    public boolean isUsingSize() {
        return usingSize;
    }

    public void setUsingSize(boolean usingSize) {
        this.usingSize = usingSize;
    }

    public boolean isUsingContent() {
        return usingContent;
    }

    public void setUsingContent(boolean usingContent) {
        this.usingContent = usingContent;
    }

    public float getNameDelta() {
        return nameDelta;
    }

    public void setNameDelta(float nameDelta) {
        this.nameDelta = nameDelta;
    }

    public float getSizeDelta() {
        return sizeDelta;
    }

    public void setSizeDelta(float sizeDelta) {
        this.sizeDelta = sizeDelta;
    }

    public float getContentVolumePercent() {
        return contentVolumePercent;
    }

    public void setContentVolumePercent(float contentVolumePercent) {
        this.contentVolumePercent = contentVolumePercent;
    }

}
