package com.company;

import java.util.ArrayList;
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

    public static List<String> countExpression(List<String> input, int start, int finish){
        List<String> helper = new ArrayList<>(input);
        Double result = 0.0;
        int elemIndex = 0;
        int highPriorityAmount = howManyHighPriority(input);
        System.out.println(highPriorityAmount);
        int lowPriorityAmount = howManyLowPriority(input);
        System.out.println(lowPriorityAmount);
        int step = 1;
 //       while (highPriorityAmount > 0 ){
            for (int i = 0; i < helper.size(); i++) {
                if(helper.get(i).equals("*")||helper.get(i).equals("/")){
                    input = countSimpleExpression(input, i-step);
                    step = step + 2;
                }
            }
            helper = new ArrayList<>(input);
            step = 1;
  //          highPriorityAmount--;
 //       }
 //       while (lowPriorityAmount > 0 ){
        for (int i = 0; i < helper.size(); i++) {
            if(helper.get(i).equals("+")||helper.get(i).equals("-")){
                input = countSimpleExpression(input, i-step);
                step = step + 2;
            }
        }
 //           lowPriorityAmount--;
 //       }
        return input;
    }

    public static int howManyHighPriority(List<String> input){
        int highPriorityAmount = 0;
        for (String elem : input) {
            if (elem.equals("*")||elem.equals("/")){
                highPriorityAmount++;
            }
        }
        return highPriorityAmount;
    }

    public static int howManyLowPriority(List<String> input){
        int lowPriorityAmount = 0;
        for (String elem : input) {
            if (elem.equals("+")||elem.equals("-")){
                lowPriorityAmount++;
            }
        }
        return lowPriorityAmount;
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
