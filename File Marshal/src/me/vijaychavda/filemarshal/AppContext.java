package me.vijaychavda.filemarshal;

import java.io.File;
import java.util.ArrayList;
import me.vijaychavda.filemarshal.settings.SelectionSettings;
import me.vijaychavda.filemarshal.settings.DecloneSettings;
import me.vijaychavda.filemarshal.settings.DeclutterSettings;

public class AppContext {

    private ArrayList<File> sources;
    private DeclutterSettings declutterSettings;
    private DecloneSettings decloneSettings;
    private SelectionSettings selectionSettings;

    public static final AppContext Current = new AppContext();

    private AppContext() {
        //
    }

    public ArrayList<File> getSources() {
        return sources;
    }

    public DeclutterSettings getDeclutterSettings() {
        return declutterSettings;
    }

    public DecloneSettings getDecloneSettings() {
        return decloneSettings;
    }

    public SelectionSettings getSelectionSettings() {
        return selectionSettings;
    }

    public void setSources(ArrayList<File> sources) {
        this.sources = sources;
    }

    public void setDeclutterSettings(DeclutterSettings declutterSettings) {
        this.declutterSettings = declutterSettings;
    }

    public void setDecloneSettings(DecloneSettings decloneSettings) {
        this.decloneSettings = decloneSettings;
    }

    public void setSelectionSettings(SelectionSettings selectionSettings) {
        this.selectionSettings = selectionSettings;
    }
}
