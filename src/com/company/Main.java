package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        InOutHelper.writeMessage("Привет! Введи выражение");
        String text = InOutHelper.readString();
        InOutHelper.writeMessage(Calculator.parseInput(text).toString());
//        InOutHelper.writeMessage(Arrays.toString(Calculator.parseInput(text).toArray()));
//        String text = "12 65 +597 +1";
//        Pattern pattern = Pattern.compile("[0-9]+|[\\(\\)\\-\\+\\*/]");
//        Matcher matcher = pattern.matcher(text);
//        while (matcher.find()) {
//            System.out.println(text.substring(matcher.start(), matcher.end()));
//        }
    }
}
