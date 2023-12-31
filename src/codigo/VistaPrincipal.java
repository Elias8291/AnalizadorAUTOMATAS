/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.lang.Float;

import static codigo.Tokens.Cadena;
import static codigo.Tokens.Char;
import static codigo.Tokens.Clase;
import static codigo.Tokens.Comillas;
import static codigo.Tokens.Corchete_a;
import static codigo.Tokens.Corchete_c;
import static codigo.Tokens.Division;
import static codigo.Tokens.Do;
import static codigo.Tokens.ERROR;
import static codigo.Tokens.Else;
import static codigo.Tokens.Float;
import static codigo.Tokens.For;
import static codigo.Tokens.Identificador;
import static codigo.Tokens.If;
import static codigo.Tokens.Igual;
import static codigo.Tokens.Int;
import static codigo.Tokens.Linea;
import static codigo.Tokens.Llave_a;
import static codigo.Tokens.Llave_c;
import static codigo.Tokens.Main;
import static codigo.Tokens.Multiplicacion;
import static codigo.Tokens.Numero;
import static codigo.Tokens.Op_atribucion;
import static codigo.Tokens.Op_booleano;
import static codigo.Tokens.Op_incremento;
import static codigo.Tokens.Op_logico;
import static codigo.Tokens.Op_relacional;
import static codigo.Tokens.P_coma;
import static codigo.Tokens.Parentesis_a;
import static codigo.Tokens.Parentesis_c;
import static codigo.Tokens.Publico;
import static codigo.Tokens.Resta;
import static codigo.Tokens.Suma;
import static codigo.Tokens.While;
import java.awt.Color;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

/**
 *
 * @author Charly Ponce
 */
public class VistaPrincipal extends javax.swing.JFrame {

    TablaSimbolos tabla_s = new TablaSimbolos();
    TablaIntermedio intermediateCode = new TablaIntermedio();
 //JTable tabla_intermedios= new JTable();
 //  JTable tabla_simbolos=new JTable();

    /**
     * Creates new form FrmPrincipal
     */
    public VistaPrincipal() {
        setUndecorated(true);
        initComponents();
        probando();
        this.setLocationRelativeTo(null);
        probar();

    }

    public void probar() {
        Document doc = txtEntrada.getDocument();
        Pane.setEditable(false);
        txtEntrada.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            private void updateLineNumbers() {
                int lineCount = doc.getDefaultRootElement().getElementCount();
                StringBuilder lineNumbers = new StringBuilder();

                for (int i = 0; i < lineCount; i++) {
                    lineNumbers.append(i + 1).append("\n");
                }
                Pane.setText(lineNumbers.toString());
            }
        });

    }

    public void table() {
        tabla_simbolos.setBackground(new Color(0, 0, 0, 0));
        tabla_simbolos.getTableHeader().setOpaque(false);
        tabla_simbolos.getTableHeader().setBackground(Color.red);
    }

    private void analizarLexico() throws IOException {
        int cont = 1;

        String expr = (String) txtEntrada.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtResultado.setText(resultado);
                return;
            }
            switch (token) {
                case Linea:
                    cont++;
                    resultado += "LINEA " + cont + "\n";
                    break;
                case Comillas:
                    resultado += "  <Comillas>\t\t" + lexer.lexeme + "\n";
                    break;
                case Cadena:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    break;
                case Int:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    break;

                case Float:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    break;
                case Char:
                    resultado += "  <Tipo de dato>\t" + lexer.lexeme + "\n";
                    break;
                case If:
                    resultado += "  <Reservada if>\t" + lexer.lexeme + "\n";
                    break;
                case Else:
                    resultado += "  <Reservada else>\t" + lexer.lexeme + "\n";
                    break;
                case Do:
                    resultado += "  <Reservada do>\t" + lexer.lexeme + "\n";
                    break;
                case While:
                    resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                    break;
                case For:
                    resultado += "  <Reservada while>\t" + lexer.lexeme + "\n";
                    break;
                case Igual:
                    resultado += "  <Operador igual>\t" + lexer.lexeme + "\n";
                    break;
                case Suma:
                    resultado += "  <Operador suma>\t" + lexer.lexeme + "\n";
                    break;
                case Resta:
                    resultado += "  <Operador resta>\t" + lexer.lexeme + "\n";
                    break;
                case Multiplicacion:
                    resultado += "  <Operador multiplicacion>\t" + lexer.lexeme + "\n";
                    break;
                case Division:
                    resultado += "  <Operador division>\t" + lexer.lexeme + "\n";
                    break;
                case Op_logico:
                    resultado += "  <Operador logico>\t" + lexer.lexeme + "\n";
                    break;
                case Op_incremento:
                    resultado += "  <Operador incremento>\t" + lexer.lexeme + "\n";
                    break;
                case Op_relacional:
                    resultado += "  <Operador relacional>\t" + lexer.lexeme + "\n";
                    break;
                case Op_atribucion:
                    resultado += "  <Operador atribucion>\t" + lexer.lexeme + "\n";
                    break;
                case Op_booleano:
                    resultado += "  <Operador booleano>\t" + lexer.lexeme + "\n";
                    break;
                case Parentesis_a:
                    resultado += "  <Parentesis de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case Parentesis_c:
                    resultado += "  <Parentesis de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case Llave_a:
                    resultado += "  <Llave de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case Llave_c:
                    resultado += "  <Llave de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case Corchete_a:
                    resultado += "  <Corchete de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case Corchete_c:
                    resultado += "  <Corchete de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case Main:
                    resultado += "  <Reservada main>\t" + lexer.lexeme + "\n";
                    break;
                case Clase:
                    resultado += "  <Reservada     >\t" + lexer.lexeme + "\n";
                    break;
                case Publico:
                    resultado += "  <Reservada     >\t" + lexer.lexeme + "\n";
                    break;
                case P_coma:
                    resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                    break;
                case Identificador:
                    resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                    break;
                case SystemOutPrintln:
                    resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                    break;
                case Numero:
                    resultado += "  <Numero>\t\t" + lexer.lexeme + "\n";
                    break;
                case ERROR:
                    resultado += "  <Simbolo no definido>\n";
                    break;
                default:
                    resultado += "  < " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cargar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        sintactico = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_simbolos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
       
        analizarTodo = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_intermedios = new javax.swing.JTable();
        limpiT = new javax.swing.JButton();
        analizarTodo1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Pane = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ANALIZADOR ");

        cargar.setBackground(new java.awt.Color(51, 0, 51));
        cargar.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        cargar.setForeground(new java.awt.Color(255, 255, 255));
        cargar.setText("Cargar Archivo");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane3.setViewportView(txtResultado);

        sintactico.setColumns(20);
        sintactico.setRows(5);
        jScrollPane2.setViewportView(sintactico);

        tabla_simbolos.setBackground(new java.awt.Color(235, 214, 255));
        tabla_simbolos.setForeground(new java.awt.Color(0, 0, 0));
        tabla_simbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_simbolos.setGridColor(new java.awt.Color(239, 208, 254));
        tabla_simbolos.setSelectionBackground(new java.awt.Color(0, 51, 102));
        tabla_simbolos.setSelectionForeground(new java.awt.Color(204, 204, 255));
        tabla_simbolos.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tabla_simbolos);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Lexico");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Semantico");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Texto");

      
        analizarTodo.setBackground(new java.awt.Color(0, 0, 0));
        analizarTodo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        analizarTodo.setForeground(new java.awt.Color(255, 255, 255));
        analizarTodo.setText("Analizar");
        analizarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarTodoActionPerformed(evt);
            }
        });

        tabla_intermedios.setBackground(new java.awt.Color(235, 214, 255));
        tabla_intermedios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tabla_intermedios);

        limpiT.setBackground(new java.awt.Color(51, 0, 51));
        limpiT.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        limpiT.setForeground(new java.awt.Color(255, 255, 255));
        limpiT.setText("Limpiar");
        limpiT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiTActionPerformed(evt);
            }
        });

        analizarTodo1.setBackground(new java.awt.Color(0, 0, 0));
        analizarTodo1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        analizarTodo1.setForeground(new java.awt.Color(255, 255, 255));
        analizarTodo1.setText("Tabla Intermedio");
        analizarTodo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarTodo1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tabla Simbolos");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tabla intermedio");

        Pane.setEditable(false);
        Pane.setBackground(new java.awt.Color(255, 255, 255));
        Pane.setForeground(new java.awt.Color(102, 0, 0));
        Pane.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        jScrollPane7.setViewportView(Pane);

        jScrollPane5.setViewportView(txtEntrada);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(limpiT, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(analizarTodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(analizarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
         
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(318, 318, 318)
                .addComponent(jLabel7)
                .addGap(191, 191, 191))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(limpiT)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cargar)
                        .addGap(4, 4, 4))
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(12, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(analizarTodo)
                                .addGap(18, 18, 18)
                                .addComponent(analizarTodo1)
                                .addGap(59, 59, 59))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed

        JFileChooser cargar = new JFileChooser();
        int iResp;

        try {
            iResp = cargar.showOpenDialog(this);
            if (iResp == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = cargar.getSelectedFile();
                FileReader lector = new FileReader(archivoSeleccionado);
                BufferedReader bufferedReader = new BufferedReader(lector);
                String linea;
                StringBuilder contenido = new StringBuilder();

                while ((linea = bufferedReader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }

                txtEntrada.setText(contenido.toString());

                bufferedReader.close();
                lector.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_cargarActionPerformed

    public void analizisSintatico() {
        String ST = txtEntrada.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        try {
            tabla_s.limpiarTabla();
            intermediateCode.limpiarTabla();
            s.parse();
            sintactico.setText("Analisis realizado correctamente");
            sintactico.setForeground(new Color(25, 111, 61));

        } catch (Exception ex) {
            Symbol sym = s.getS();
            String ar = s.getError();
            System.out.println("ar" + ar);
            sintactico.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            sintactico.setForeground(Color.red);
        }

    }

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void analizarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarTodoActionPerformed
        try {
            // TODO add your handling code here:
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        analizisSintatico();
        probando();

    }//GEN-LAST:event_analizarTodoActionPerformed

    private void limpiTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiTActionPerformed
        // TODO add your handling code here:
        tabla_s.limpiarTabla();
        intermediateCode.limpiarTabla();
        txtEntrada.setText(" ");
        txtResultado.setText(" ");
        sintactico.setText("");
        DefaultTableModel model = (DefaultTableModel) tabla_simbolos.getModel();
        model.setRowCount(0);

        DefaultTableModel modeli = (DefaultTableModel) tabla_intermedios.getModel();
        modeli.setRowCount(0);
    }//GEN-LAST:event_limpiTActionPerformed

    private void analizarTodo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarTodo1ActionPerformed
        cargarTabla();
        AssemblerGenerator generator = new AssemblerGenerator();

        // Obtener la tabla de cuádruples
        Vector<Quadruple> quadruples = TablaIntermedio.getTabla();

        // Crear una instancia de TablaSimbolos
        TablaSimbolos tablaSimbolos = new TablaSimbolos();

        // Generar el código de ensamblador
        String assemblyCode = generator.generateAssemblyCode(quadruples, tablaSimbolos);
        generator.writeToFile(assemblyCode, "C:/dosbox/tasm/archivo.asm");

        Process compiladp;
        try {
            System.out.println("Pase");
            compiladp = Runtime.getRuntime().exec("C:/dosbox/dosbox.exe -conf c:/dosbox/auto.conf");
            compiladp.waitFor();
        } catch (IOException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_analizarTodo1ActionPerformed

    public void probando() {

        Vector vc = tabla_s.getTabla();
        Object elementos[][] = tabla_s.getElementos();
        String[] columna = new String[]{"Nombre", "valor", "Tipo"};
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(columna);
        tabla_simbolos.setModel(dtm);
        for (int a = 0; a < vc.size(); a++) {
            dtm.addRow(elementos[a]);
        }

    }

    public void cargarTabla() {
        Vector ft = intermediateCode.getTabla();
        for (Object str : ft) {
            System.out.println(str);
        }
        Object elemen[][] = intermediateCode.getElementos();
        String[] columnNames = {"Operador", "Resultado", "Operando 1", "Operando 2"};
        DefaultTableModel mo = new DefaultTableModel();
        mo.setColumnIdentifiers(columnNames);
        tabla_intermedios.setModel(mo);
        for (int a = 0; a < ft.size(); a++) {
            mo.addRow(elemen[a]);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Pane;
    private javax.swing.JButton analizarTodo;
    private javax.swing.JButton analizarTodo1;
    private javax.swing.JButton cargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton limpiT;
    private javax.swing.JTextArea sintactico;
    private javax.swing.JTable tabla_intermedios;
    private javax.swing.JTable tabla_simbolos;
    private javax.swing.JTextPane txtEntrada;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
