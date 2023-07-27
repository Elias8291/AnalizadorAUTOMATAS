/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class AssemblerGenerator {

    boolean operationOccurred = false;
    private StringBuilder dataSection;
    private StringBuilder codeSection;
    private boolean macroDefined = false;
    private Map<String, String> previousOperations;
    private boolean hasExecutedPlus = false;

    public AssemblerGenerator() {
        dataSection = new StringBuilder(".model small\n.data\n");
        codeSection = new StringBuilder(".code\nmov ax,@data\nmov ds,ax\nmov cx, 10 ; divisor\n");
        previousOperations = new HashMap<>();

    }

    public String generateAssemblyCode(Vector<Quadruple> quadruples, TablaSimbolos tablaSimbolos) {
        int messageCount = 0;
        String resultVar = "";
        String s = null;
        boolean bande = false;

        boolean hasExecutedPlus = false;
        boolean hasExecutedMinus = false;

        for (int i = 0; i < quadruples.size(); i++) {
            Quadruple quadruple = quadruples.get(i);
            String opcode = quadruple.operator;
            String arg1 = quadruple.operand1;
            String arg2 = quadruple.operand2;
            String result = quadruple.result;
            System.out.println("opcion" + opcode.toString());
            switch (opcode) {

                case "=":
                case "+":
                case "-":

                    if (arg2.isEmpty()) {

                        String value = arg1;  // El valor es el primer operando
                        int index = dataSection.indexOf(result + " dw ?");
                        if (index != -1) {
                            // Si la variable ya fue declarada, entonces solo actualizamos el valor.
                            dataSection.replace(index, index + result.length() + 5, result + " dw " + value);

                        } else {
                            // Si la variable no ha sido declarada, la declaramos y asignamos el valor.
                            dataSection.append(result + " dw " + value + "\n");

                        }
                    } else if (arg1.startsWith("temp")) {
                        codeSection.append("mov ax, " + arg1 + "\nmov " + result + ", ax\n");

                    }

                    if (opcode.equals("+")) {

                        dataSection.append(result + " dw ?\n");
                        codeSection.append("mov ax, " + arg1 + "\nadd ax, " + arg2 + "\n");
                        codeSection.append("mov " + result + ",ax\n");

                        codeSection.append("mov " + s + ",ax\n");

                    }
                    if (opcode.equals("-")) {

                        System.out.println("Soy este" + s);
                        dataSection.append(result + " dw ?\n");
                        codeSection.append("mov ax, " + arg1 + "\nsub ax, " + arg2 + "\n");
                        codeSection.append("mov " + result + ",ax\n");

                        codeSection.append("mov " + s + ",ax\n");

                    }

                    break;

                case "Print":
                    String messageName = "mensaje" + messageCount++;
                    arg1 = arg1.replace("\"", ""); // quita las comillas de la cadena
                    dataSection.append(messageName + " db " + result + ",10,13, '$'\n");
                    codeSection.append("lea dx, " + messageName + "\nmov ah, 09h\nint 21h\n");
                    break;

                case "PrintV":

                    if (!macroDefined) {
                        codeSection.append("print_num macro num\n");
                        codeSection.append("mov ax, num\n");
                        codeSection.append("xor dx, dx ; limpia dx para la operación div\n");
                        codeSection.append("mov cx, 10\n");
                        codeSection.append("div cx ; ax = ax / cx, dx = ax % cx\n");
                        codeSection.append("add dx, 30h ; convierte el dígito a ASCII\n");
                        codeSection.append("push dx ; guarda el dígito en la pila\n");
                        codeSection.append("add ax, 30h ; convierte el dígito a ASCII\n");
                        codeSection.append("mov dl, al\n");
                        codeSection.append("mov ah, 02h\n");
                        codeSection.append("int 21h\n");
                        codeSection.append("pop dx ; recupera el dígito de la pila\n");
                        codeSection.append("mov dl, dl\n");
                        codeSection.append("mov ah, 02h\n");
                        codeSection.append("int 21h\n");
                        codeSection.append("endm\n");
                        macroDefined = true;
                    }
                    codeSection.append("print_num " + result + "\n"); // changed from resultVar to result
                    codeSection.append("mov dl, 0Dh\n"); // carriage return
                    codeSection.append("mov ah, 02h\n");
                    codeSection.append("int 21h\n");
                    codeSection.append("mov dl, 0Ah\n"); // line feed
                    codeSection.append("mov ah, 02h\n");
                    codeSection.append("int 21h\n");
                    if (!arg1.startsWith("temp") && !bande && (hasExecutedPlus ^ hasExecutedMinus) ) {
                        codeSection.append("mov " + result + ", ax\n"); // se guarda el resultado de la operación en la variable correspondiente
                        bande = true;
                    }
                    break;
                case "Variable": // Handle variable initialization
                    dataSection.append(result + " dw ?\n");
                    s = result;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown opcode: " + opcode);
            }
        }

        codeSection.append("mov ah,4ch\nint 21H\nend\n");

        return dataSection.toString() + codeSection.toString();
    }

    public void writeToFile(String assemblyCode, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));  // false para sobrescribir el archivo
            writer.write(assemblyCode);
            writer.close();

        } catch (IOException e) {
        }
    }
}
