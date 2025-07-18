package com.pedulilingkungan.model;

import java.util.Date;

/**
 * Model untuk entri sampah yang didaur ulang
 */
public class WasteEntry {
    private WasteType type;
    private double weight;
    private String description;
    private Date date;
    private int points;
    
    public WasteEntry(WasteType type, double weight, String description) {
        this.type = type;
        this.weight = weight;
        this.description = description;
        this.date = new Date();
        this.points = calculatePoints();
    }
    
    private int calculatePoints() {
        // Calculate points based on waste type and weight
        int basePoints = type.getPointsPerKg();
        return (int) (basePoints * weight);
    }
    
    // Getters
    public WasteType getType() {
        return type;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Date getDate() {
        return date;
    }
    
    public int getPoints() {
        return points;
    }
    
    // Setters
    public void setType(WasteType type) {
        this.type = type;
        this.points = calculatePoints(); // Recalculate points
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
        this.points = calculatePoints(); // Recalculate points
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %.2f kg (%d poin)", 
                           type.getDisplayName(), weight, points);
    }
}
