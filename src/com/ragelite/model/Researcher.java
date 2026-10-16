package com.ragelite.model;

// Researcher extends User — Inheritance
public class Researcher extends User {

    public Researcher(int userId, String name, String email, String password) {
        super(userId, name, email, password, Role.RESEARCHER);
    }

    // Polymorphism — Researcher's own version of dashboard title
    @Override
    public String getDashboardTitle() {
        return "Researcher Workspace";
    }

    @Override
    public String[] getPermissions() {
        return new String[] {
            "MANAGE_DATASETS",
            "TRAIN_MODELS",
            "TRACK_EXPERIMENTS",
            "COLLABORATE"
        };
    }
}
