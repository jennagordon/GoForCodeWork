package mathoperators;

public class App {
    public static void main(String[] args) {
        int op1 = 11;
        int op2 = 23;

        System.out.println(calculate(MathOperator.PLUS, op1, op2));
        System.out.println(calculate(MathOperator.MINUS, op1, op2));
        System.out.println(calculate(MathOperator.DIVIDE, op1, op2));
        System.out.println(calculate(MathOperator.MULTIPLY, op1, op2));
    }

    public static int calculate(MathOperator operator, int operand1, int operand2) {

        switch (operator) {
            case PLUS:
                return operand1 + operand2;

            case MINUS:
                return operand1 - operand2;

            case DIVIDE:
                return operand1 / operand2;

            case MULTIPLY:
                return operand1 * operand2;

            default:
                throw new UnsupportedOperationException();


        }
    }
}
