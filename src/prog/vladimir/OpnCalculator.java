package prog.vladimir;

import java.util.*;

public class OpnCalculator {
    public static Double countExpression(List<String> expression) {

        Queue<String> opnExpression = convertExpression(expression);

        return countExpression(opnExpression);
    }

    private static Double countExpression(Queue<String> expression) {
        Stack<String> stack = new Stack<String>();
        stack.add(expression.remove());
        while (!expression.isEmpty()) {
            if (!isOperation(expression.peek())) {
                stack.add(expression.remove());
            } else {
                stack.add(countSimpleExpression(expression.remove(), stack.pop(), stack.pop()));
            }
        }

        return Double.parseDouble(stack.pop());
    }

    private static Queue<String> convertExpression(List<String> expression) {
        Queue<String> outputQueue = new LinkedList<String>();
        Stack<String> operationStack = new Stack<String>();
        String temp;

        for (String elem : expression) {
            if (isSymbol(elem)) {
                if (isLeftBracket(elem)) {
                    operationStack.add(elem);
                } else if (isRightBracket(elem)) {
                    temp = operationStack.pop();
                    while (!temp.equals("(")) {
                        outputQueue.add(temp);
                        temp = operationStack.pop();
                    }
                } else if (operationStack.isEmpty()) {
                    operationStack.add(elem);
                } else if (getPriority(elem) > getPriority(operationStack.peek())) {
                    operationStack.add(elem);
                } else {
                    while (!operationStack.isEmpty() && !operationStack.peek().equals("(")) {
                        outputQueue.add(operationStack.pop());
                    }
                    operationStack.add(elem);
                }
            } else outputQueue.add(elem);
        }
        while (!operationStack.isEmpty()) {
            outputQueue.add(operationStack.pop());
        }


        return outputQueue;
    }

    private static int getPriority(String symbol) {
        return symbol.equals("*") || symbol.equals("/") ? 2 : 1;
    }

    private static boolean isHighPriority(String symbol) {
        return symbol.equals("/") || symbol.equals("*");
    }

    private static boolean isLowPriority(String symbol) {
        return symbol.equals("-") || symbol.equals("+");
    }

    private static boolean isMinus(String symbol) {
        return symbol.equals("-");
    }

    private static boolean isDivision(String symbol) {
        return symbol.equals("/");
    }

    private static boolean isLeftBracket(String symbol) {
        return symbol.equals("(");
    }

    private static boolean isRightBracket(String symbol) {
        return symbol.equals(")");
    }

    private static boolean isPair(String left, String right) {
        return left.equals("(") && right.equals(")");
    }

    private static boolean isSymbol(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("*") || symbol.equals("(") || symbol.equals(")");
    }

    private static boolean isOperation(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("*");
    }

    private static String countSimpleExpression(String operation, String operand2, String operand1) {
        double result = 0.0;
        if (operation.equals("*")) {
            result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
        } else if (operation.equals("/")) {
            result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
        } else if (operation.equals("-")) {
            result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
        } else if (operation.equals("+")) {
            result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
        }
        return Double.toString(result);
    }
}
