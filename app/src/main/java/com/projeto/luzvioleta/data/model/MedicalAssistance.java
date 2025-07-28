package com.projeto.luzvioleta.data.model;

public class MedicalAssistance {
    private int id;
    private int userId;
    private String type;
    private String date;

    public MedicalAssistance() {}

    public MedicalAssistance(int id, int userId, String type, String date) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}