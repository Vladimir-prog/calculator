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
}
