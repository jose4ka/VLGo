package com.vlgo.vlgo.component;

public class Entity {

    private String name;
    private String description;
    private double coordX, coordY;
    private int[] imagesIds;

    public Entity(String name, String description, double coordX, double coordY, int[] imagesIds) {
        this.name = name;
        this.description = description;
        this.coordX = coordX;
        this.coordY = coordY;
        this.imagesIds = imagesIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public int[] getImagesIds() {
        return imagesIds;
    }

    public void setImagesIds(int[] imagesIds) {
        this.imagesIds = imagesIds;
    }
}
