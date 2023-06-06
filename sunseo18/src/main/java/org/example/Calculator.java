package org.example;

import java.util.Stack;

public class Calculator {

    private final Stack<Double> operands = new Stack<>();
    private final Stack<OperatorEnum> operators = new Stack<>();

    public Double calculate(final String input) {
        String[] expression = stripString(input);
        interpret(expression);

        while (operands.size() != 1) {
            OperatorEnum operator = operators.pop();
            Double operand1 = operands.pop();
            Double operand2 = operands.pop();

            operands.push(operator.calculate(operand1, operand2));
        }
        return operands.get(0);
    }

    private String[] stripString(final String input) {
        String[] expression = input.split("\\s+");

        return expression;
    }

    private void interpret(final String[] expression) {
        for (int i = 0; i < expression.length; i++){
            if (i % 2 == 0)
                operands.push(checkOperand(expression[i]));
            else operators.push(checkOperator(expression[i]));
        }
    }

    private Double checkOperand(final String operand) {
        try {
            return Double.parseDouble(operand);
        } catch (NumberFormatException ex) {
            throw new RuntimeException(String.format("%s는 올바르지 않은 숫자입니다.", operand));
        }
    }

    private OperatorEnum checkOperator(final String operator) {
        return OperatorEnum.find(operator).orElseThrow(() -> new RuntimeException(String.format("%s는 지원하지 않는 연산자입니다.", operator)));
    }

}
