package jms;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    private int number;
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int calculate(Calculator calculator){
        switch (calculator.getOperation()){
            case ADD:
                total += calculator.getNumber();
                break;
            case SUBTRACT:
                total -= calculator.getNumber();
                break;
            case MULTIPLY:
                total *= calculator.getNumber();
                break;
            case DIVIDE:
                total /= calculator.getNumber();
                break;
        }
        return total;
    }
}
