package lesson02.initialization;

public class App {

    public static void main(String[] args) {
        int count = 19;
        Person person = new Person();
        person.setAge(35);
        person.setName("Eric");


        System.out.println("Count = " + count);
        System.out.println("Age = " + person.getAge());
        System.out.println("Name = " + person.getName());

    }
}
