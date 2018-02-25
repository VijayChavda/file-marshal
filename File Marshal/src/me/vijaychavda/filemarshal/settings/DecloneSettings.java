package me.vijaychavda.filemarshal.settings;

public class DecloneSettings {

    private boolean usingNames;
    private boolean usingSize;
    private boolean usingContent;

    private boolean nameSimilar;
    private boolean nameCommonWords;
    private boolean nameSimilarCommonWords;
    private boolean nameExactlySame;

    private boolean sizeNoHugeDifference;
    private boolean sizeAlmostSame;
    private boolean sizeExactlySame;

    private boolean content2p;
    private boolean content10p;
    private boolean content20p;
    private boolean content50p;
    private boolean content100p;

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

    public boolean isNameSimilar() {
        return nameSimilar;
    }

    public void setNameSimilar(boolean nameSimilar) {
        this.nameSimilar = nameSimilar;
    }

    public boolean isNameCommonWords() {
        return nameCommonWords;
    }

    public void setNameCommonWords(boolean nameCommonWords) {
        this.nameCommonWords = nameCommonWords;
    }

    public boolean isNameSimilarCommonWords() {
        return nameSimilarCommonWords;
    }

    public void setNameSimilarCommonWords(boolean nameSimilarCommonWords) {
        this.nameSimilarCommonWords = nameSimilarCommonWords;
    }

    public boolean isNameExactlySame() {
        return nameExactlySame;
    }

    public void setNameExactlySame(boolean nameExactlySame) {
        this.nameExactlySame = nameExactlySame;
    }

    public boolean isSizeNoHugeDifference() {
        return sizeNoHugeDifference;
    }

    public void setSizeNoHugeDifference(boolean sizeNoHugeDifference) {
        this.sizeNoHugeDifference = sizeNoHugeDifference;
    }

    public boolean isSizeAlmostSame() {
        return sizeAlmostSame;
    }

    public void setSizeAlmostSame(boolean sizeAlmostSame) {
        this.sizeAlmostSame = sizeAlmostSame;
    }

    public boolean isSizeExactlySame() {
        return sizeExactlySame;
    }

    public void setSizeExactlySame(boolean sizeExactlySame) {
        this.sizeExactlySame = sizeExactlySame;
    }

    public boolean isContent2p() {
        return content2p;
    }

    public void setContent2p(boolean content2p) {
        this.content2p = content2p;
    }

    public boolean isContent10p() {
        return content10p;
    }

    public void setContent10p(boolean content10p) {
        this.content10p = content10p;
    }

    public boolean isContent20p() {
        return content20p;
    }

    public void setContent20p(boolean content20p) {
        this.content20p = content20p;
    }

    public boolean isContent50p() {
        return content50p;
    }

    public void setContent50p(boolean content50p) {
        this.content50p = content50p;
    }

    public boolean isContent100p() {
        return content100p;
    }

    public void setContent100p(boolean content100p) {
        this.content100p = content100p;
    }

}
