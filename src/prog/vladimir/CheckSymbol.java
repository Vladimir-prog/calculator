package prog.vladimir;

public class CheckSymbol {

    public static int getPriority(String symbol) {
        return symbol.equals("*") || symbol.equals("/") ? 2 : 1;
    }

    public static boolean isHighPriority(String symbol) {
        return symbol.equals("/") || symbol.equals("*");
    }

    public static boolean isLowPriority(String symbol) {
        return symbol.equals("-") || symbol.equals("+");
    }

    public static boolean isMinus(String symbol) {
        return symbol.equals("-");
    }

    public static boolean isDivision(String symbol) {
        return symbol.equals("/");
    }

    public static boolean isLeftBracket(String symbol) {
        return symbol.equals("(");
    }

    public static boolean isRightBracket(String symbol) {
        return symbol.equals(")");
    }

    public static boolean isPair(String left, String right) {
        return left.equals("(") && right.equals(")");
    }

    public static boolean isSymbol(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("*") || symbol.equals("(") || symbol.equals(")");
    }

    public static boolean isOperation(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("/") || symbol.equals("*");
    }

    static boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
