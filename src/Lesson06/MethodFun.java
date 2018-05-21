package Lesson06;

public class MethodFun {
    public static void main(String[] args) {
        int operand1 = 3;
        int operand2 = 7;
        int sum = add(operand1, operand2);
        System.out.println(sum);

        sum = add(32, 45);
        System.out.println(sum);

        System.out.println(add(3, 4));

    }

    public static int add(int a, int b) {
        return a + b;
    }
}
