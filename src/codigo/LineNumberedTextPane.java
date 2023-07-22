/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;
import java.awt.Font;
import javax.swing.*;
import javax.swing.text.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

public class LineNumberedTextPane {
    private static final String EOL = "\n";
    private JTextPane textPane;

    public LineNumberedTextPane(JTextPane textPane) {
        this.textPane = textPane;
        this.textPane.setFont(new Font("monospaced", Font.PLAIN, 12));
        this.textPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                addLineNumbers();
            }
            public void removeUpdate(DocumentEvent e) {
                addLineNumbers();
            }
            public void changedUpdate(DocumentEvent e) {
                addLineNumbers();
            }
        });
    }

    public void addLineNumbers() {
        StyledDocument doc = this.textPane.getStyledDocument();
        Style style = this.textPane.addStyle("I'm a Style", null);
        String text = this.textPane.getText();
        int pos = 0;
        int lineNum = 1;
        while ((pos = text.indexOf(EOL, pos) + 1) != 0) {
            String lineNumStr = String.format("%3d: ", lineNum);
            try {
                doc.insertString(pos, lineNumStr, style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            lineNum++;
        }
    }
}

