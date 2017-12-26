package me.vijaychavda;

/**
 *
 * @author Vijay
 */
public class AppContext {

    private static final CompareSettings compareSettings = new CompareSettings();
    private static final SelectionSettings selectionSettings = new SelectionSettings();

    private AppContext() {

    }

    public static CompareSettings getSettings() {
        return compareSettings;
    }

    public static SelectionSettings getSelectionSettings() {
        return selectionSettings;
    }

}
