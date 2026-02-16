package com.example.testit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private AppUser manager;

    public AppUser() {}

    public AppUser(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }


    public AppUser getManager() { return manager; }
    public void setManager(AppUser manager) { this.manager = manager; }
}
