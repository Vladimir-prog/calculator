package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static Double countExpression(List<String> input) {
        return Double.parseDouble(countExpression(input, 0, input.size() - 1));
    }
	
	public static String countExpression(List<String> input, int start, int finish) {
        List<String> helper = new ArrayList<String>(input);
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
                        if (i == j + 2) {
                            result = helper.get(j + 1);
                            input = removeFromTo(input, j, i);
                            input.add(j, result);
                            i -= 2;
                        } else {
                            result = countExpression(copyFromTo(input, j + 1, i - 1)).toString();
                            input = removeFromTo(input, j, i);
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
	

	
	public static List<String> parseInput(String input) throws Exception {
        List<String> parseIn = new LinkedList<String>();
		input = input.replaceAll("\\s+","");
		input = input.replaceAll(",",".");
        Pattern pattern = Pattern.compile("[0-9\\.]+|[\\(\\)\\-\\+\\*/]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            parseIn.add(input.substring(matcher.start(), matcher.end()));
        }
		if (!checkBrackets(parseIn)) throw new MyCalculatorExceptinon("неверно расставлены скобки");
		if (input.length() != totalLengthOfListItems(parseIn)) throw new MyCalculatorExceptinon("присутствуют некорректные символы");
		parseIn = convertUnaryMinus(parseIn);
        return parseIn;
    }
	
	public static int totalLengthOfListItems(List<String> input) {
		int result = 0;
		for (String s: input) {
			result += s.length();
		}
		return result;
	}
	
	public static boolean checkBrackets(List<String> input) {
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
	
	public static List<String> convertUnaryMinus(List<String> input) throws Exception {
		List<String> helper = new ArrayList<String>(input);
		List<String> output = new LinkedList<String>();
		
		for (int i = 0; i < helper.size(); i++) {
			if (isDivision(helper.get(i)) && isMinus(helper.get(i+1)) && isLeftBracket(helper.get(i+2))) {  // num / - (
				output.add("*");
				output.add("-1");
				output.add("/");
				i++;
			} else if (isLeftBracket(helper.get(i)) && i>0 && !isOperation(helper.get(i-1))) {
				throw new MyCalculatorExceptinon("нет операции перед скобкой");
			} else if (isMinus(helper.get(i))) {
				if (i==0) {
					if (isNumber(helper.get(i+1))) {
						output.add(helper.get(i)+helper.get(i+1));
						i++;
//						System.out.println("1");
					} else if (isLeftBracket(helper.get(i+1))) {
						output.add("-1");
						output.add("*");
//						System.out.println("2");
					} else throw new MyCalculatorExceptinon("два символа подряд");
				} else if (isSymbol(helper.get(i-1))) {
					if (isRightBracket(helper.get(i-1))) {
						output.add(helper.get(i));
//						System.out.println("3");
					} else if (isLeftBracket(helper.get(i+1))) {
						output.add("-1");
						output.add("*");
//						System.out.println("4");
					} else if (isLeftBracket(helper.get(i-1))) {
						if (isNumber(helper.get(i+1))) {
							output.add(helper.get(i)+helper.get(i+1));
							i++;
//							System.out.println("5");
						} else throw new MyCalculatorExceptinon("два символа подряд после скобки");
					} else if (isNumber(helper.get(i+1))) {
						output.add(helper.get(i)+helper.get(i+1));
						i++;
//						System.out.println("6");
					} else if (isLeftBracket(helper.get(i-1))) {
						if (isNumber(helper.get(i+1))) {
							output.add(helper.get(i)+helper.get(i+1));
							i++;
//							System.out.println("7");
						} else throw new MyCalculatorExceptinon("два символа подряд после скобки");
					} else throw new MyCalculatorExceptinon("три символа подряд");
				} else output.add(helper.get(i));
			} else output.add(helper.get(i));
		}
		return output;
	}
	
	public static boolean isNumber(String element) {
        try {
			Double.parseDouble(element);
            return true;
        } catch (Exception e) {
			return false;
		}
	}
	public static boolean isMinus(String symbol) {
        if (symbol.equals("-")) {
            return true;
        } else return false;
    }
	public static boolean isDivision(String symbol) {
        if (symbol.equals("/")) {
            return true;
        } else return false;
    }
    public static boolean isLeftBracket(String symbol) {
        if (symbol.equals("(")) {
            return true;
        } else return false;
    }
    public static boolean isRightBracket(String symbol) {
        if (symbol.equals(")")) {
            return true;
        } else return false;
    }
	public static boolean isPair(String left, String right) {
        return left.equals("(") && right.equals(")");
    }
    public static boolean isSymbol(String symbol) {
        if (symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("*") || symbol.equals("(") || symbol.equals(")")) {
            return true;
        } else return false;
    }
	
	public static boolean isOperation(String symbol) {
        if (symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("*") || symbol.equals("(") || symbol.equals(")")) {
            return true;
        } else return false;
    }
	
	
    public static List<String> removeFromTo(List<String> input, int from, int to) {
        //if (input.size() >= to) {
        List<String> output = new LinkedList<String>(input);
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
        List<String> output = new LinkedList<String>();
        for (int i = from; i <= to; i++) {
            output.add(listIterator.next().toString());
        }
        return output;
//        } else {
//            return input;
//        }
    }

    public static String countExpressionWithoutBrackets(List<String> input) {
        List<String> helper = new ArrayList<String>(input);
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
        helper = new ArrayList<String>(input);
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

    public static String countSimpleExpression(List<String> input, int start) {
        List<String> output = new LinkedList<String>(input);
        Double result = 0.0;
//        switch (input.get(start + 1)) {
//            case ("*"):
//                result = Double.parseDouble(input.get(start)) * Double.parseDouble(input.get(start + 2));
//                break;
//            case ("-"):
//                result = Double.parseDouble(input.get(start)) - Double.parseDouble(input.get(start + 2));
//                break;
//            case ("+"):
//                result = Double.parseDouble(input.get(start)) + Double.parseDouble(input.get(start + 2));
//                break;
//            case ("/"):
//                result = Double.parseDouble(input.get(start)) / Double.parseDouble(input.get(start + 2));
//                break;
//        }

        if (input.get(start + 1).equals("*")) {
            result = Double.parseDouble(input.get(start)) * Double.parseDouble(input.get(start + 2));
        } else if (input.get(start + 1).equals("/")) {
            result = Double.parseDouble(input.get(start)) / Double.parseDouble(input.get(start + 2));
        } else if (input.get(start + 1).equals("-")) {
            result = Double.parseDouble(input.get(start)) - Double.parseDouble(input.get(start + 2));
        } else if (input.get(start + 1).equals("+")) {
            result = Double.parseDouble(input.get(start)) + Double.parseDouble(input.get(start + 2));
        }
//        output.remove(start);
//        output.remove(start);
//        output.set(start, result.toString());
        return result.toString();
    }


}
