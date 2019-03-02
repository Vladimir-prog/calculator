package com.company;

import java.util.LinkedList;
import java.util.List;
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

    public static List<String> countSimpleExpression(List<String> input, int start) {
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
        output.remove(start);
        output.remove(start);
        output.set(start, result.toString());
        return output;
    }

}
