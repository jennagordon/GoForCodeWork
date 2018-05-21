package lesson04.exerciseshapesandperimeters;

public class App {
    public static void main(String[] args) {
        //We cannot instantiate abstract bas classes
        //Shape myShape = new Shape();

        //Create a new instance of square
        // by calling its constructor and passing
        // in the side length
        Square mySquare = new Square(2);

        System.out.println(mySquare.getArea());

        mySquare.setColor("blue");

        System.out.println("Square is: " + mySquare.getColor());

        // We can do this because of polymorphism
        // A Square is a Square
        // but it also is a Shape
        Shape myShape = new Square(3);
        System.out.println(myShape.getArea());

        // instantiate a new Rectangle
        Rectangle myRectangle = new Rectangle(2, 3);
        System.out.println(myRectangle.getColor());
    }
}
