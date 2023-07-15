package codigo;

import java.lang.reflect.Array;
import java.util.Vector;
import javax.swing.JOptionPane;

public class TablaSimbolos {

    private static Vector tabla = new Vector();
    private static boolean mensajeMostrado = false;

    public TablaSimbolos() {

    }

    public static void crearEntradada(String nombre, String tipo) {

        Palabra e = TablaSimbolos.busca(nombre);
        if (e == null) {
            e = new Palabra(nombre, tipo);
            System.out.println("e" + e.getTipo());
            tabla.add(e);

        } else {
            JOptionPane.showMessageDialog(null, "La Variable repetida es: " + e.getNombre());
        }
    }

    public static String getTipo(String nombre) {
        Palabra e = TablaSimbolos.busca(nombre);
        if (e == null) {
        }
        return e.getValor();
    }

    public static String getValor(String nombre) {
        Palabra e = TablaSimbolos.busca(nombre);
        if (e == null) {
        }
        return e.getValor();
    }

    public static Palabra busca(String nombre) {

        Palabra e = null;
        int i = 0;
        while (i < tabla.size()) {
            e = (Palabra) tabla.elementAt(i);
            if (e.getNombre().equals(nombre)) {
                return e;  // Retorna 'e' tan pronto como la encuentra

            }
            i++;
        }
        return null;  // Retorna null si no encuentra la variable

    }

    public static Vector getTabla() {

        return tabla;
    }

    public static void Imprimir() {

        for (int i = 0; i < tabla.size(); i++) {
            System.out.println(tabla.elementAt(i).toString());
        }
    }

    public static void setValor(String pnombre, String pval) {
        Palabra e = null;
        int i = 0;
        while (i < tabla.size()) {
            e = (Palabra) tabla.elementAt(i);
            if (e.getNombre().equals(pnombre)) {
                if (e.getTipo().equals("char")) {
                    String result = pval.replace("'", "");
                    e.setValor(result);
                    tabla.set(i, e);
                } else {
                    e.setValor(pval);
                    tabla.set(i, e);
                }
                break;
            }
            e = null;
            i++;
        }
    }

    public static Object[][] getElementos() {
        Object[][] elemenArreg = new Object[7][7];
        for (int i = 0; i < tabla.size(); i++) {
            Palabra obj = (Palabra) tabla.elementAt(i);
            elemenArreg[i][0] = obj.getNombre();
            elemenArreg[i][1] = (obj.getValor());
            elemenArreg[i][2] = obj.getTipo();
        }
        return elemenArreg;
    }

    public static void limpiarTabla() {
        tabla.clear();
    }

    public static void transformString(String str) {
        try {
            float floatValue = Float.parseFloat(str);
            System.out.println("Float value: " + floatValue);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se puede convertir '" + str + "' a float.");
        }

        try {
            int intValue = Integer.parseInt(str);
            System.out.println("Int value: " + intValue);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se puede convertir '" + str + "' a int.");
        }

        try {
            if (str.length() == 1) {
                char charValue = str.charAt(0);
                System.out.println("Char value: " + charValue);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede convertir '" + str + "' a char.");
        }
    }

    public static boolean verificarValorT(String tipo, String valor) {
        try {
            if (tipo.equalsIgnoreCase("float")) {
                
                  Float.parseFloat(valor);
            
            
            } else if (tipo.equalsIgnoreCase("integer")) {
                Integer.parseInt(valor);
            } else if (tipo.equalsIgnoreCase("char")) {
                 if (valor.length() == 3 && valor.charAt(0) == '\'' && valor.charAt(2) == '\'') {
                char c = valor.charAt(1);
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

    public static void verificarVariableInde(String identificador,String tipo, String valor,TablaSimbolos a) {
        System.out.println("tipo"+identificador);
        System.out.println("valor"+valor);
        if (verificarValorT(tipo, valor)) {
            a.crearEntradada(identificador, tipo);
            a.setValor(identificador,valor);
        } else {
            JOptionPane.showMessageDialog(null, "La variable: " + identificador + " no es de tipo: " + tipo);
        }
    }
     public static void verificarVariableDef(String identificador,String tipo, String valor,TablaSimbolos a) {
        System.out.println("tipo"+identificador);
        System.out.println("valor"+valor);
        if (verificarValorT(tipo, valor)) {
             System.out.println("paseee");
            a.setValor(identificador,valor);
        } else {
            JOptionPane.showMessageDialog(null, "La variable: " + identificador + " no es de tipo: " + tipo);
        }
    }

}
