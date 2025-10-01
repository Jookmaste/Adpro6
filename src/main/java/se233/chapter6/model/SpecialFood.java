package se233.chapter6.model;

import javafx.geometry.Point2D;

public class SpecialFood extends se233.chapter6.model.Food {
    public SpecialFood(Point2D position) {
        super(position);
    }

    public SpecialFood() {
        super();
    }

    public int getGrowthValue() {
        return 5;
    }

    public String getColor() {
        return "green";
    }
}