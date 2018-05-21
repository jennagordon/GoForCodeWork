package lesson04.exerciseshapesandperimeters;

public abstract class Shape {
    //Every class that inherits from Shape will have a color
    protected String color;

    // Every class that inherits from Shape
    // will be required to implement the following
    // two methods
    public abstract int getArea();

    public abstract int getPerimeter();

    //Every class that inherits from Shape
    //Will have the getter and setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
