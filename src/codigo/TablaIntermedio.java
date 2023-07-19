
package codigo;

import java.util.Vector;
import javax.swing.JOptionPane;

public class TablaIntermedio {

    private static Vector<Quadruple> tabla = new Vector<>();
    private static boolean mensajeMostrado = false;
    private static int tempCount = 0;
    private static String lastTempResult;

    public TablaIntermedio() {

    }

    public static void crearEntrada(Quadruple quad) {
        tabla.add(quad);
    }

    public static Quadruple busca(String result) {
        for (Quadruple quad : tabla) {
            if (quad.result.equals(result)) {
                return quad;
            }
        }
        return null;
    }

    public static String getNextTempName() {
        return "temp" + (tempCount++);
    }

    public static Vector<Quadruple> getTabla() {
        return tabla;
    }

    public static void imprimir() {
        for (Quadruple quad : tabla) {
            System.out.println(quad.toString());
        }
    }

    public static void setValor(String oldResult, String newOperator, String newResult, String newOperand1, String newOperand2) {
        for (int i = 0; i < tabla.size(); i++) {
            Quadruple quad = tabla.elementAt(i);
            if (quad.result.equals(oldResult)) {
                quad.operator = newOperator;
                quad.result = newResult;
                quad.operand1 = newOperand1;
                quad.operand2 = newOperand2;
                tabla.set(i, quad);
                break;
            }
        }
    }

    public static int getTablaSize() {
        return tabla.size();
    }

    public static Object[][] getElementos() {
        Object[][] elemenArreg = new Object[tabla.size()][4];
        for (int i = 0; i < tabla.size(); i++) {
            Quadruple quad = tabla.elementAt(i);
            elemenArreg[i][0] = quad.operator;
            elemenArreg[i][1] = quad.result;
            elemenArreg[i][2] = quad.operand1;
            elemenArreg[i][3] = quad.operand2;
        }
        return elemenArreg;
    }

    public static void limpiarTabla() {
        tabla.clear();
    }
}
