package lesson02.exercise.classmodeling;

public class AppAirplane {
    public static void main(String[] args) {
        Airplane delta = new Airplane("A153");
        delta.setOrigin("Boston, MA");
        delta.setDestination("Charleston, SC");

        System.out.println("We are leaving from: " + delta.getOrigin());
        System.out.println("We are arriving at: " + delta.getDestination());
    }
}
