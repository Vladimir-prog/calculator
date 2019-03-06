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
            if (CheckSymbol.isRightBracket(s) && stack.isEmpty()) {
                return false;
            }
            if (CheckSymbol.isLeftBracket(s)) {
                stack.push(s);
            }
            if (CheckSymbol.isRightBracket(s)) {
                if (CheckSymbol.isPair(stack.peek(), s)) {
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
            if (CheckSymbol.isDivision(helper.get(i)) && CheckSymbol.isMinus(helper.get(i + 1)) && CheckSymbol.isLeftBracket(helper.get(i + 2))) {  // num / - (
                output.add("*");
                output.add("-1");
                output.add("/");
                i++;
            } else if (CheckSymbol.isLeftBracket(helper.get(i)) && i > 0 && !CheckSymbol.isOperation(helper.get(i - 1))) {
                throw new MyCalculatorExceptinon("нет операции перед скобкой");
            } else if (CheckSymbol.isMinus(helper.get(i))) {
                if (i == 0) {
                    if (CheckSymbol.isNumber(helper.get(i + 1))) {
                        output.add(helper.get(i) + helper.get(i + 1));
                        i++;
                    } else if (CheckSymbol.isLeftBracket(helper.get(i + 1))) {
                        output.add("-1");
                        output.add("*");
                    } else throw new MyCalculatorExceptinon("два символа подряд");
                } else if (CheckSymbol.isSymbol(helper.get(i - 1))) {
                    if (CheckSymbol.isRightBracket(helper.get(i - 1))) { // ( -
                        output.add(helper.get(i));
                    } else if (CheckSymbol.isLeftBracket(helper.get(i + 1))) {
                        output.add("-1");
                        output.add("*");
                    } else if (CheckSymbol.isLeftBracket(helper.get(i - 1))) {
                        if (CheckSymbol.isNumber(helper.get(i + 1))) {
                            output.add(helper.get(i) + helper.get(i + 1));
                            i++;
                        } else throw new MyCalculatorExceptinon("два символа подряд после скобки");
                    } else if (CheckSymbol.isNumber(helper.get(i + 1))) {
                        output.add(helper.get(i) + helper.get(i + 1));
                        i++;
                    } else if (CheckSymbol.isLeftBracket(helper.get(i - 1))) {
                        if (CheckSymbol.isNumber(helper.get(i + 1))) {
                            output.add(helper.get(i) + helper.get(i + 1));
                            i++;
                        } else throw new MyCalculatorExceptinon("два символа подряд после скобки");
                    } else throw new MyCalculatorExceptinon("три символа подряд");
                } else output.add(helper.get(i));
            } else output.add(helper.get(i));
        }
        return output;
    }

}
