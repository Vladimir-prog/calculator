package prog.vladimir;

import java.util.*;

public class Calculator {

    public static Double countExpression(List<String> input) {
        return Double.parseDouble(countExpression(input, 0, input.size() - 1));
    }

    private static String countExpression(List<String> input, int start, int finish) {
        List<String> helper = new ArrayList<String>(input);
        String result;
        for (int i = finish; i > start; i--) {
            if (helper.get(i).equals(")")) {
                int temp = 0;
                for (int j = i - 1; j >= start; j--) {
                    if ((helper.get(j).equals(")"))) {
                        temp++;
                    } else if (helper.get(j).equals("(") && temp != 0) {
                        temp--;
                    } else if (helper.get(j).equals("(")) {
                        if (i == j + 2) {
                            result = helper.get(j + 1);
                            input = ListHelper.removeFromTo(input, j, i);
                            input.add(j, result);
                            i -= 2;
                        } else {
                            result = countExpression(ListHelper.copyFromTo(input, j + 1, i - 1)).toString();
                            input = ListHelper.removeFromTo(input, j, i);
                            input.add(j, result);
                            i -= i - j;
                        }
                    }
                }
            }
        }
        result = countExpressionWithoutBrackets(input);
        return result;
    }

    private static String countExpressionWithoutBrackets(List<String> input) {
        List<String> helper = new ArrayList<String>(input);
        String result;
        int step = 1;
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i).equals("*") || helper.get(i).equals("/")) {
                result = countSimpleExpression(input, i - step);
                input = ListHelper.removeFromTo(input, i - step, i - step + 2);
                input.add(i - step, result);
                step = step + 2;
            }
        }
        helper = new ArrayList<String>(input);
        step = 1;
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i).equals("+") || helper.get(i).equals("-")) {
                result = countSimpleExpression(input, i - step);
                input = ListHelper.removeFromTo(input, i - step, i - step + 2);
                input.add(i - step, result);
                step = step + 2;
            }
        }
        return input.get(0);
    }

    private static String countSimpleExpression(List<String> input, int start) {
        double result = 0.0;
        if (input.get(start + 1).equals("*")) {
            result = Double.parseDouble(input.get(start)) * Double.parseDouble(input.get(start + 2));
        } else if (input.get(start + 1).equals("/")) {
            result = Double.parseDouble(input.get(start)) / Double.parseDouble(input.get(start + 2));
        } else if (input.get(start + 1).equals("-")) {
            result = Double.parseDouble(input.get(start)) - Double.parseDouble(input.get(start + 2));
        } else if (input.get(start + 1).equals("+")) {
            result = Double.parseDouble(input.get(start)) + Double.parseDouble(input.get(start + 2));
        }
        return Double.toString(result);
    }


}
