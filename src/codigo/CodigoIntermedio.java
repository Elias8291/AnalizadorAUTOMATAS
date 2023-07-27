/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abisai
 */
public class CodigoIntermedio {

    List<Quadruple> quadruples = new ArrayList<>();

    public void addQuadruple(Quadruple quadruple) {
        quadruples.add(quadruple);

    }

    public void printIntermediateCode() {
        for (Quadruple quadruple : quadruples) {
            System.out.println(quadruple);
        }
    }

    public List<Quadruple> getQuadruples() {
        return quadruples;
    }

    public void addQuadruplesToTable(JTable table) {
    
        DefaultTableModel model = (DefaultTableModel) table.getModel();
       
        for (Quadruple quadruple : quadruples) {

            System.out.println(quadruple);
            // Agrega cada atributo en una columna separada
             model.addRow(new Object[]{
                quadruple.operator,
                quadruple.operand1,
                quadruple.operand2,
                quadruple.result
            });
        }
    }
}
