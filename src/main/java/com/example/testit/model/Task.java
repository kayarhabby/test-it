package com.example.testit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.OUVERT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id")
    private AppUser assignedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    private AppUser requester;

    public Task() {}

    public Task(String title, String description, AppUser assignedUser) {
        this.title = title;
        this.description = description;
        this.assignedUser = assignedUser;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public AppUser getAssignedUser() { return assignedUser; }
    public void setAssignedUser(AppUser assignedUser) { this.assignedUser = assignedUser; }

    public AppUser getRequester() { return requester; }
    public void setRequester(AppUser requester) { this.requester = requester; }
}
