package me.vijaychavda;

/**
 *
 * @author Vijay
 */
public class SearchSettings {

    private boolean name;
    private boolean size;
    private boolean content;

    private float nameDelta;
    private float sizeDelta;
    private float contentVolumePercent;

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
