package org.example.calculate;

import java.util.List;

public class Calculator {
    private static final List<NewArithmeticInterface> arithmeticInterfaces = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplationOperator(), new DivisionOperator());

    public static int calculate(int operand1, String operator, int operand2) {
        return ArithmeticOperator.calculate(operand1, operator, operand2);
    }

    public static int calculateByInterface(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return arithmeticInterfaces.stream()
                .filter(v -> v.supports(operator))
                .map(v -> v.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("옳바른 사칙연산이 아닙니다."));
    }
}
