package com.projeto.luzvioleta.data.model;

public class Report {
    private int id;
    private int userId;
    private String description;
    private String date;

    public Report() {}

    public Report(int id, int userId, String description, String date) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
