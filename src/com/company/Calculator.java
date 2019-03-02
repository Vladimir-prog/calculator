package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static List<String> parseInput(String input) {
        List<String> parseIn = new LinkedList<>();
        Pattern pattern = Pattern.compile("[0-9]+|[\\(\\)\\-\\+\\*/]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            parseIn.add(input.substring(matcher.start(), matcher.end()));
        }
        return parseIn;
    }

    public static Double countExpression(List<String> input) {
        return Double.parseDouble(countExpression(input, 0, input.size() - 1));
    }

    private static String countExpression(List<String> input, int start, int finish) {
        List<String> helper = new ArrayList<>(input);
        String result = "";
        rightBracket:
        for (int i = finish; i > start; i--) {
            if (helper.get(i).equals(")")) {
                int temp = 0;
                for (int j = i - 1; j >= start; j--) {
                    if ((helper.get(j).equals(")"))) {
                        temp++;
                    } else if (helper.get(j).equals("(") && temp != 0) {
                        temp--;
                    } else if (helper.get(j).equals("(")) {
//                        brackets--;
                        if (i == j + 2) {
                            result = helper.get(j + 1);
                            input = removeFromTo(input, j, i);
                            input.add(j, result);
                            i-=2;
                        } else {
                            result = countExpression(copyFromTo(input, j + 1, i - 1)).toString();
                            input = removeFromTo(input, j, i);
                            input.add(j, result);
                            i-=i-j;
                        }
                    }
                }
            }
        }
        result = countExpressionWithoutBrackets(input);
        return result;
    }

    public static List<String> removeFromTo(List<String> input, int from, int to) {
        //if (input.size() >= to) {
            List<String> output = new LinkedList<>(input);
            ListIterator listIterator = output.listIterator(from);
            for (int i = from; i <= to; i++) {
                listIterator.next();
                listIterator.remove();
            }
            return output;
//        } else return input;
    }

    public static List<String> copyFromTo(List<String> input, int from, int to) {
        //if (input.size() >= to) {
            ListIterator listIterator = input.listIterator(from);
            List<String> output = new LinkedList<>();
            for (int i = from; i <= to; i++) {
                output.add(listIterator.next().toString());
            }
            return output;
//        } else {
//            return input;
//        }
    }

    public static String countExpressionWithoutBrackets(List<String> input) {
        List<String> helper = new ArrayList<>(input);
        String result = "";
//        int elemIndex = 0;
//        int highPriorityAmount = howManyHighPriority(input);
//        System.out.println(highPriorityAmount);
//        int lowPriorityAmount = howManyLowPriority(input);
//        System.out.println(lowPriorityAmount);
        int step = 1;
        //       while (highPriorityAmount > 0 ){
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i).equals("*") || helper.get(i).equals("/")) {
                result = countSimpleExpression(input, i - step);
                input = removeFromTo(input, i - step, i - step + 2);
                input.add(i - step, result);
                step = step + 2;
            }
        }
        helper = new ArrayList<>(input);
        step = 1;
        //          highPriorityAmount--;
        //       }
        //       while (lowPriorityAmount > 0 ){
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i).equals("+") || helper.get(i).equals("-")) {
                result = countSimpleExpression(input, i - step);
                input = removeFromTo(input, i - step, i - step + 2);
                input.add(i - step, result);
                step = step + 2;
            }
        }
        //           lowPriorityAmount--;
        //       }
        return input.get(0);
    }

    public static int howManyHighPriority(List<String> input) {
        int highPriorityAmount = 0;
        for (String elem : input) {
            if (elem.equals("*") || elem.equals("/")) {
                highPriorityAmount++;
            }
        }
        return highPriorityAmount;
    }

    public static int howManyLowPriority(List<String> input) {
        int lowPriorityAmount = 0;
        for (String elem : input) {
            if (elem.equals("+") || elem.equals("-")) {
                lowPriorityAmount++;
            }
        }
        return lowPriorityAmount;
    }

    public static int howManyBrackets(List<String> input) {
        int bracketsAmount = 0;
        for (String elem : input) {
            if (elem.equals("(")) {
                bracketsAmount++;
            }
        }
        return bracketsAmount;
    }

    public static String countSimpleExpression(List<String> input, int start) {
        List<String> output = new LinkedList<>(input);
        Double result = 0.0;
        switch (input.get(start + 1)) {
            case ("*"):
                result = Double.parseDouble(input.get(start)) * Double.parseDouble(input.get(start + 2));
                break;
            case ("-"):
                result = Double.parseDouble(input.get(start)) - Double.parseDouble(input.get(start + 2));
                break;
            case ("+"):
                result = Double.parseDouble(input.get(start)) + Double.parseDouble(input.get(start + 2));
                break;
            case ("/"):
                result = Double.parseDouble(input.get(start)) / Double.parseDouble(input.get(start + 2));
                break;
        }
//        output.remove(start);
//        output.remove(start);
//        output.set(start, result.toString());
        return result.toString();
    }

}
