package com.samirmaciel.crudsqlite.model;

public class Collaborator {
    private int id;
    private String name;
    private String photo;
    private String skill;

    public Collaborator(String name, String photo, String skill) {
        this.name = name;
        this.photo = photo;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
