/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class AssemblerGenerator {

    private StringBuilder dataSection;
    private StringBuilder codeSection;

    public AssemblerGenerator() {
        dataSection = new StringBuilder(".model small\n.data\n");
        codeSection = new StringBuilder(".code\nmov ax,@data\nmov ds,ax\n");
    }

    public String generateAssemblyCode(Vector<Quadruple> quadruples, TablaSimbolos tablaSimbolos) {
        int messageCount = 0;
        for (Quadruple quadruple : quadruples) {
            String opcode = quadruple.operator;
            String arg1 = quadruple.operand1;
            String arg2 = quadruple.operand2;
            String result = quadruple.result;

            switch (opcode) {
                case "=":
                    if (arg2.isEmpty()) {
                        String value = arg1;  // El valor es el primer operando
                        dataSection.append(result + " dw " + value + "\n");
                    }
                    break;
                case "+":

                    dataSection.append(result + " dw ?\n");

                    codeSection.append("mov ax, " + arg1 + "\nadd ax, " + arg2 + "\nmov " + result + ", ax\n");
                    break;
                case "-": 
                    dataSection.append(result + " dw ?\n");
         
                    codeSection.append("mov ax, " + arg1 + "\nsub ax, " + arg2 + "\nmov " + result + ", ax\n");
                    break;

                case "Print":
                    String messageName = "mensaje" + messageCount++;
                    arg1 = arg1.replace("\"", ""); // quita las comillas de la cadena
                    dataSection.append(messageName + " db " + result + ",10,13, '$'\n");
                    codeSection.append("lea dx, " + messageName + "\nmov ah, 09h\nint 21h\n");
                    break;

                case "PrintV":
                    // move the value of temp1 to result
                    codeSection.append("mov " + result + ", ax\n");

                    // print newline
                    codeSection.append("mov dl, 0Dh\n"); // carriage return
                    codeSection.append("mov ah, 02h\n");
                    codeSection.append("int 21h\n");
                    codeSection.append("mov dl, 0Ah\n"); // line feed
                    codeSection.append("mov ah, 02h\n");
                    codeSection.append("int 21h\n");

                    // convert result to ASCII and print
                    codeSection.append("mov ax, " + result + "\n");
                    codeSection.append("add ax, 30h\n"); // convert to ASCII
                    codeSection.append("mov dl, al\n");
                    codeSection.append("mov ah, 02h\n");
                    codeSection.append("int 21h\n");
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