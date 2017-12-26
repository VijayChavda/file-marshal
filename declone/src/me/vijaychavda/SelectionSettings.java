package me.vijaychavda;

public class SelectionSettings {

    private String extensions;
    private long sizeLowerLimit, sizeUpperLimit;

    public String getExtensionCS() {
        return extensions;
    }

    public void setExtensionCS(String extensionCS) {
        this.extensions = extensionCS;
    }

    public long getSizeLowerLimit() {
        return sizeLowerLimit;
    }

    public void setSizeLowerLimit(long sizeLowerLimit) {
        this.sizeLowerLimit = sizeLowerLimit;
    }

    public long getSizeUpperLimit() {
        return sizeUpperLimit;
    }

    public void setSizeUpperLimit(long sizeUpperLimit) {
        this.sizeUpperLimit = sizeUpperLimit;
    }

}
