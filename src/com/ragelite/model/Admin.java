package com.ragelite.model;

// Admin extends User — Inheritance
public class Admin extends User {

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password, Role.ADMIN);
    }

    // Polymorphism — Admin's own version of dashboard title
    @Override
    public String getDashboardTitle() {
        return "Admin Control Panel";
    }

    @Override
    public String[] getPermissions() {
        return new String[] {
            "MANAGE_USERS",
            "MANAGE_RESOURCES",
            "MANAGE_PROJECTS",
            "VIEW_USAGE_REPORTS"
        };
    }
}
