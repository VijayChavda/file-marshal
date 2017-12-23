package me.vijaychavda;

/**
 *
 * @author Vijay
 */
public class AppContext {

    private static final SearchSettings settings = new SearchSettings();

    private AppContext() {

    }

    public static SearchSettings getSettings() {
        return settings;
    }

}
