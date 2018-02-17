package me.vijaychavda;

import java.io.File;
import java.util.ArrayList;
import me.vijaychavda.settings.SelectionSettings;
import me.vijaychavda.settings.CompareSettings;

public class AppContext {

    private ArrayList<File> sources;
    private CompareSettings compareSettings;
    private SelectionSettings selectionSettings;

    public static final AppContext Current = new AppContext();

    private AppContext() {
        //
    }

    public ArrayList<File> getSources() {
        return sources;
    }

    public CompareSettings getCompareSettings() {
        return compareSettings;
    }

    public SelectionSettings getSelectionSettings() {
        return selectionSettings;
    }

    public void setSources(ArrayList<File> sources) {
        this.sources = sources;
    }

    public void setCompareSettings(CompareSettings compareSettings) {
        this.compareSettings = compareSettings;
    }

    public void setSelectionSettings(SelectionSettings selectionSettings) {
        this.selectionSettings = selectionSettings;
    }
}
