package lesson02.exercise.classmodeling;

public class AppHouse {
    public static void main(String[] args) {
        House home = new House();
        home.setStreetNumber(24);
        home.setStreetName("Maple Ave");
        home.setCity("Portsmouth");
        home.setState("NH");
        home.setCountry("USA");

        System.out.println(home.getCity());
    }
}
