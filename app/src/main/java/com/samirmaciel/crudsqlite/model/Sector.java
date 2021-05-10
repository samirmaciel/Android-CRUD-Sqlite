package com.samirmaciel.crudsqlite.model;

import java.util.List;

public class Sector {
    private String name;
    private String progress;
    private List<Collaborator> collaborators;

    public Sector(String name, List<Collaborator> collaborators) {
        this.name = name;
        this.collaborators = collaborators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }
}
