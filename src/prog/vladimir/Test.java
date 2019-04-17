package prog.vladimir;

public class Test {
    public static void main(String[] args) throws Exception {
        String[] testExpressions = {"2+2",
                "5*5",
                "3.5*(45-40)",
                "3.5*(45-40)/2",
                "3.5*(45-40)/2+(5/5)",
                "3.5*(45-40)/2+-1",
                "3.5*(45-40)/2+-(5.5)",
                "3.5*(45-40)/2-1*(-5)/-8",
                "3.5*(45-4*40)/2",
                "-15.44+3.5*(4.5-4.02*-5)/2",
                "5*5"};

        for (int i = 0; i < testExpressions.length; i++) {
            if (OpnCalculator.countExpression(StringParser.parseInput(testExpressions[i])).toString().equals(MyCalculator.countExpression(StringParser.parseInput(testExpressions[i])).toString())) {
                System.out.println(testExpressions[i] + " = " + OpnCalculator.countExpression(StringParser.parseInput(testExpressions[i])).toString() + " _Success!");
            } else {
                System.out.printf("Expression: %s OPN: %s MyAlgorithm: %s _ERROR!\n",
                        testExpressions[i], OpnCalculator.countExpression(StringParser.parseInput(testExpressions[i])).toString(),
                        MyCalculator.countExpression(StringParser.parseInput(testExpressions[i])).toString());
            }
        }

    }
}
