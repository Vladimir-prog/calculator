package prog.vladimir;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    public static List<String> parseInput(String input) throws Exception {
        List<String> parseIn = new LinkedList<String>();
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll(",", ".");
        Pattern pattern = Pattern.compile("[0-9.]+|[()\\-+*/]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            parseIn.add(input.substring(matcher.start(), matcher.end()));
        }
        if (!checkBrackets(parseIn)) throw new MyCalculatorExceptinon("неверно расставлены скобки");
        if (input.length() != totalLengthOfListItems(parseIn))
            throw new MyCalculatorExceptinon("присутствуют некорректные символы");
        parseIn = convertAndCheckUnaryMinus(parseIn);
        return parseIn;
    }

    private static int totalLengthOfListItems(List<String> input) {
        int result = 0;
        for (String s : input) {
            result += s.length();
        }
        return result;
    }

    private static boolean checkBrackets(List<String> input) {
        Stack<String> stack = new Stack<String>();
        for (String s : input) {
            if (isRightBracket(s) && stack.isEmpty()) {
                return false;
            }
            if (isLeftBracket(s)) {
                stack.push(s);
            }
            if (isRightBracket(s)) {
                if (isPair(stack.peek(), s)) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    private static List<String> convertAndCheckUnaryMinus(List<String> input) throws Exception {
        List<String> helper = new ArrayList<String>(input);
        List<String> output = new LinkedList<String>();

        for (int i = 0; i < helper.size(); i++) {
            if (isDivision(helper.get(i)) && isMinus(helper.get(i + 1)) && isLeftBracket(helper.get(i + 2))) {  // num / - (
                output.add("*");
                output.add("-1");
                output.add("/");
                i++;
            } else if (isLeftBracket(helper.get(i)) && i > 0 && !isOperation(helper.get(i - 1))) {
                throw new MyCalculatorExceptinon("нет операции перед скобкой");
            } else if (isMinus(helper.get(i))) {
                if (i == 0) {
                    if (isNumber(helper.get(i + 1))) {
                        output.add(helper.get(i) + helper.get(i + 1));
                        i++;
                    } else if (isLeftBracket(helper.get(i + 1))) {
                        output.add("-1");
                        output.add("*");
                    } else throw new MyCalculatorExceptinon("два символа подряд");
                } else if (isSymbol(helper.get(i - 1))) {
                    if (isRightBracket(helper.get(i - 1))) { // ( -
                        output.add(helper.get(i));
                    } else if (isLeftBracket(helper.get(i + 1))) {
                        output.add("-1");
                        output.add("*");
                    } else if (isLeftBracket(helper.get(i - 1))) {
                        if (isNumber(helper.get(i + 1))) {
                            output.add(helper.get(i) + helper.get(i + 1));
                            i++;
                        } else throw new MyCalculatorExceptinon("два символа подряд после скобки");
                    } else if (isNumber(helper.get(i + 1))) {
                        output.add(helper.get(i) + helper.get(i + 1));
                        i++;
                    } else if (isLeftBracket(helper.get(i - 1))) {
                        if (isNumber(helper.get(i + 1))) {
                            output.add(helper.get(i) + helper.get(i + 1));
                            i++;
                        } else throw new MyCalculatorExceptinon("два символа подряд после скобки");
                    } else throw new MyCalculatorExceptinon("три символа подряд");
                } else output.add(helper.get(i));
            } else output.add(helper.get(i));
        }
        return output;
    }

    private static boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (Exception e) {
            return false;
        }
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
}
