package lesson02.exercise.classmodeling;

public class AppBook {
    public static void main(String[] args) {
        Book book1 = new Book("Joe", "Schmoe", "The Dog Barks");
        book1.setGenre("Children's Book");

        System.out.println(book1.getGenre());
    }
}
