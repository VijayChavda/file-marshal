package me.vijaychavda;

/**
 *
 * @author Vijay
 */
public class SearchSettings {

    private boolean name;
    private boolean size;
    private boolean content;

    private int nameDelta;
    private int sizeDelta;
    private int contentVolumePercent;

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isSize() {
        return size;
    }

    public void setSize(boolean size) {
        this.size = size;
    }

    public boolean isContent() {
        return content;
    }

    public void setContent(boolean content) {
        this.content = content;
    }

    public int getNameDelta() {
        return nameDelta;
    }

    public void setNameDelta(int nameDelta) {
        this.nameDelta = nameDelta;
    }

    public int getSizeDelta() {
        return sizeDelta;
    }

    public void setSizeDelta(int sizeDelta) {
        this.sizeDelta = sizeDelta;
    }

    public int getContentVolumePercent() {
        return contentVolumePercent;
    }

    public void setContentVolumePercent(int contentVolumePercent) {
        this.contentVolumePercent = contentVolumePercent;
    }

}
