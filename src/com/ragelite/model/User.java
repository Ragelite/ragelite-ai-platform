package com.ragelite.model;

import java.time.LocalDateTime;

// Abstract class — base for Admin and Researcher (Inheritance + Abstraction)
public abstract class User {

    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    protected Role role;
    protected LocalDateTime createdAt;

    public User(int userId, String name, String email, String password, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    // Abstract method — each subclass implements its own dashboard view (Polymorphism)
    public abstract String getDashboardTitle();

    // Abstract method — each role has different permissions
    public abstract String[] getPermissions();

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
