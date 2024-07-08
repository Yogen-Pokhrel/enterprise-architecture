package jms;


public class Calculator {
    private int number;
    private CalculatorOperation operation;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CalculatorOperation getOperation() {
        return operation;
    }

    public void setOperation(CalculatorOperation operation) {
        this.operation = operation;
    }

    public Calculator(){}
    public Calculator(int number, CalculatorOperation operation) {
        this.number = number;
        this.operation = operation;
    }
}
