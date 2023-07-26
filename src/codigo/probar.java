/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codigo;

import java.util.Vector;

/**
 *
 * @author Abisai
 */
public class probar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      

        // Agregar las instancias de Quadruple a un Vector
        Vector<Quadruple> quadruples = new Vector<>();
        quadruples.add(new Quadruple("SUB","T","F","K"));
        quadruples.add(new Quadruple("ADD","KL","FA","KI"));
 

        // Crear una instancia de AssemblerGenerator
        AssemblerGenerator generator = new AssemblerGenerator();

        // Generar el código de ensamblador
        //String assemblyCode = generator.generateAssemblyCode(quadruples);

        // Imprimir el código de ensamblador
       // System.out.println(assemblyCode);
    }
    
}
