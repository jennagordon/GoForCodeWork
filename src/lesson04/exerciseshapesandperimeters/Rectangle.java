package lesson04.exerciseshapesandperimeters;

public class Rectangle extends Shape{

    private int width;
    private int length;

    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
        super.color = "orange"; //since color is 'protected' we can call it this way
    }

    @Override
    public int getArea() {
        return width * length;
    }

    @Override
    public int getPerimeter() {
        return (2 * width) + (2* length);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
