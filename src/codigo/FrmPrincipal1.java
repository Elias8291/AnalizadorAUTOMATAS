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
import java.awt.image.RescaleOp;
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
public class FrmPrincipal1 extends javax.swing.JFrame {

    TablaSimbolos tabla_s = new TablaSimbolos();
    TablaIntermedio intermediateCode = new TablaIntermedio();
    JTable tabla_intermedios = new JTable();
    JTable tabla_simbolos = new JTable();

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal1() {
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
//                txtResultado.setText(resultado);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sintactico = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        Pane = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        limpiT = new javax.swing.JButton();
        analizarTodo = new javax.swing.JButton();
        analizarTodo1 = new javax.swing.JButton();
        cargar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 211, 211));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("COMPILADOR");
        jLabel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder()));

        sintactico.setEditable(false);
        sintactico.setBackground(new java.awt.Color(51, 51, 51));
        sintactico.setColumns(20);
        sintactico.setRows(5);
        sintactico.setAutoscrolls(false);
        jScrollPane2.setViewportView(sintactico);

        Pane.setEditable(false);
        Pane.setBackground(new java.awt.Color(255, 255, 255));
        Pane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        Pane.setForeground(new java.awt.Color(153, 0, 0));
        Pane.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        jScrollPane7.setViewportView(Pane);

        txtEntrada.setBackground(new java.awt.Color(51, 51, 51));
        txtEntrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));
        txtEntrada.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        txtEntrada.setCaretColor(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(txtEntrada);

        jPanel3.setBackground(new java.awt.Color(211, 211, 211));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        limpiT.setBackground(new java.awt.Color(0, 0, 0));
        limpiT.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        limpiT.setForeground(new java.awt.Color(255, 255, 255));
        limpiT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PSL.png"))); // NOI18N
        limpiT.setText("Limpiar");
        limpiT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiTActionPerformed(evt);
            }
        });

        analizarTodo.setBackground(new java.awt.Color(0, 0, 0));
        analizarTodo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        analizarTodo.setForeground(new java.awt.Color(255, 255, 255));
        analizarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ps.png"))); // NOI18N
        analizarTodo.setText("Analizar");
        analizarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarTodoActionPerformed(evt);
            }
        });

        analizarTodo1.setBackground(new java.awt.Color(0, 0, 0));
        analizarTodo1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        analizarTodo1.setForeground(new java.awt.Color(255, 255, 255));
        analizarTodo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tab.png"))); // NOI18N
        analizarTodo1.setText("Tabla Intermedio");
        analizarTodo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarTodo1ActionPerformed(evt);
            }
        });

        cargar.setBackground(new java.awt.Color(0, 0, 0));
        cargar.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        cargar.setForeground(new java.awt.Color(255, 255, 255));
        cargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MKL.png"))); // NOI18N
        cargar.setText("Cargar Archivo");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(limpiT)
                .addGap(32, 32, 32)
                .addComponent(analizarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(analizarTodo1)
                .addGap(18, 18, 18)
                .addComponent(cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(limpiT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(analizarTodo)
                                .addComponent(analizarTodo1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(cargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("INSTITUTO TECNOLOGICO NACIONAL DE MEXICO");

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("CAMPUS OAXACA");

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-cerrar-ventana-24.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-minimizar-24.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(84, 84, 84)))
                        .addGap(219, 219, 219))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(253, 253, 253))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void analizarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarTodoActionPerformed
        try {
            // TODO add your handling code here:
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
        }

        analizisSintatico();
        probando();

    }//GEN-LAST:event_analizarTodoActionPerformed

    private void limpiTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiTActionPerformed
        // TODO add your handling code here:
        tabla_s.limpiarTabla();
        intermediateCode.limpiarTabla();
        txtEntrada.setText(" ");
//        txtResultado.setText(" ");
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
            Logger.getLogger(FrmPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrmPrincipal1.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_analizarTodo1ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
         dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        
       setState(ICONIFIED);
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(FrmPrincipal1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal1().setVisible(true);

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton limpiT;
    private javax.swing.JTextArea sintactico;
    private javax.swing.JTextPane txtEntrada;
    // End of variables declaration//GEN-END:variables
}
