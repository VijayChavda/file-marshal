package me.vijaychavda;

public class AppContext {

    private static final CompareSettings compareSettings = new CompareSettings();
    private static final SelectionSettings selectionSettings = new SelectionSettings();

    private AppContext() {

    }

    public static CompareSettings getCompareSettings() {
        return compareSettings;
    }

    public static SelectionSettings getSelectionSettings() {
        return selectionSettings;
    }

}
