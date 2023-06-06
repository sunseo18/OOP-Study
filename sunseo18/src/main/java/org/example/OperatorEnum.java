package org.example;

import java.util.Arrays;
import java.util.Optional;

public enum OperatorEnum {
    ADD("+") {
        @Override
        Double calculate(Double operand1, Double operand2) {
            return operand1 + operand2;
        }
    },
    SUBTRACT("-") {
        @Override
        Double calculate(Double operand1, Double operand2) {
            return operand1 - operand2;
        }
    },
    MULTIPLY("*") {
        @Override
        Double calculate(Double operand1, Double operand2) {
            return operand1 * operand2;
        }
    },
    DEVIDE("/") {
        @Override
        Double calculate(Double operand1, Double operand2) {
            return operand1 / operand2;
        }
    };

    abstract Double calculate(Double operand1, Double operand2);

    private String operator;

    OperatorEnum(String operator) {
        this.operator = operator;
    }

    static final Optional<OperatorEnum> find(String op) {
        return Arrays.stream(values()).filter(e -> e.operator.equals(op)).findFirst();
    }
}
