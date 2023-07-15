/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import javax.swing.JOptionPane;

/**
 *
 * @author Abisai
 */
public class Verificar {

    public static String verificarValor(String tipo, String valor) throws NumberFormatException {
       if (tipo.equals("float")) {
            if (!valor.contains(".")) {
                 JOptionPane.showMessageDialog(null, "No soy un numero flotante");
            }
            Float.parseFloat(valor);
            return valor;
        } else if (tipo.equalsIgnoreCase("integer")) {
            if (valor.contains(".")) {
               JOptionPane.showMessageDialog(null, "No soy int");
            }
            Integer.parseInt(valor);
            return valor;
        } else {
            return valor;
        }
    }

    public static void verificarT(String tipo, String valor) {

        if (tipo.equals("float")) {

            if (valor.contains(".")) {
            } else {
                JOptionPane.showMessageDialog(null, "No soy un numero flotante");
            }
            /*

            try {
                float value = Float.parseFloat(valor);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "No soy flotante");
            }
*/
        } else if (tipo.equals("int")) {
            try {
                int value = Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "No soy int");
                // Aquí puedes realizar acciones adicionales en caso de excepción, si es necesario
            }
        }

    }

    
    public static boolean verificarValorT(String tipo, String valor) {
    try {
        if (tipo.equalsIgnoreCase("float")) {
            Float.parseFloat(valor);
        } else if (tipo.equalsIgnoreCase("integer")) {
            Integer.parseInt(valor);
        } else if (tipo.equalsIgnoreCase("char")) {
            if (valor.length() == 1) {
                char c = valor.charAt(0);
            } else {
                return false;
            }
        } else {
            return false;
        }
    } catch (NumberFormatException e) {
        return false;
    }
    return true;
}
 
}
