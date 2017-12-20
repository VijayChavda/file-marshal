package me.vijaychavda;

/**
 *
 * @author Vijay
 */
public class AppContext {

    private static SearchSettings settings;

    public AppContext() {
        settings = new SearchSettings();
    }

    public static SearchSettings getSettings() {
        return settings;
    }

}
