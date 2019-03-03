package com.company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        InOutHelper.writeMessage("Привет! Введи выражение");
//        String text = InOutHelper.readString();
//        List<String> input = Calculator.parseInput(text);
//        InOutHelper.writeMessage(input.toString());
//        Double answer = Calculator.countExpression(input);
//        InOutHelper.writeMessage("ответ: " + answer);

        InOutHelper.writeMessage("Тестовые прогоны:");
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("5+5"))," = 10.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(5)"))," = 5.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(1)+15"))," = 16.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(5)*(2)"))," = 10.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("5*(2+2)"))," = 20.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(5*(2+2)-5+5)"))," = 20.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(13+2)+(7+6)*3")), " = 54.000000"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(23-5)/(17/8)"))," = 8,470588"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("20*86+(17+11*2+(13/155/2))"))," = 1759,041935"));
        InOutHelper.writeMessage(String.format("%f %s",Calculator.countExpression(Calculator.parseInput("(1-(20*(86-(17+11*2+(13/155/2))+2))/2+15)"))," = −473,580645"));

//        InOutHelper.writeMessage(Arrays.toString(Calculator.parseInput(text).toArray()));
//        String text = "12 65 +597 +1";
//        Pattern pattern = Pattern.compile("[0-9]+|[\\(\\)\\-\\+\\*/]");
//        Matcher matcher = pattern.matcher(text);
//        while (matcher.find()) {
//            System.out.println(text.substring(matcher.start(), matcher.end()));
//        }
    }
}
