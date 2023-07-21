package codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Quadruple {

    String operator;
    String operand1;
    String operand2;
    String result;

    Quadruple(String operator, String result, String operand1, String operand2) {
        this.operator = operator;
        this.result = result;
        this.operand1 = operand1;
        this.operand2 = operand2;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quadruple quadruple = (Quadruple) obj;
        return Objects.equals(operand1, quadruple.operand1)
                && Objects.equals(operand2, quadruple.operand2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2);
    }

    @Override
    public String toString() {
        return operator + result + operand1 + operand2;
    }
}
