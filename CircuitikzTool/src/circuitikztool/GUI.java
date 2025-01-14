/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitikztool;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author James
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jMenu3 = new javax.swing.JMenu();
        schematicWindow = new CircuitMaker();
        jLabel1 = new javax.swing.JLabel();
        componentString = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        componentList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputField = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        componentLabel = new javax.swing.JTextField();
        toolSelector = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 32767));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        saveOption = new javax.swing.JMenuItem();
        openOption = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        preferences = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jTextField1.setText("jTextField1");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        schematicWindow.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout schematicWindowLayout = new javax.swing.GroupLayout(schematicWindow);
        schematicWindow.setLayout(schematicWindowLayout);
        schematicWindowLayout.setHorizontalGroup(
            schematicWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 896, Short.MAX_VALUE)
        );
        schematicWindowLayout.setVerticalGroup(
            schematicWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("Component String");

        componentString.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                componentStringCaretUpdate(evt);
            }
        });
        componentString.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                componentStringFocusGained(evt);
            }
        });
        componentString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentStringActionPerformed(evt);
            }
        });

        componentList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        componentList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                componentListFocusGained(evt);
            }
        });
        componentList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                componentListKeyPressed(evt);
            }
        });
        componentList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                componentListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(componentList);

        jLabel2.setText("Components");

        jLabel3.setText("LaTex String");

        outputField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outputFieldFocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(outputField);

        jLabel4.setText("Component Label");

        componentLabel.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                componentLabelCaretUpdate(evt);
            }
        });
        componentLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                componentLabelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                componentLabelFocusLost(evt);
            }
        });
        componentLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentLabelActionPerformed(evt);
            }
        });

        toolSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Path", "Resistor", "Capacitor", "Inductor", "Diode", "Voltage Source", "Current Source", "Block Component", "GND Node", "VCC Node", "VSS Node", "NPN Transistor", "PNP Transistor", "N-Mos", "P-Mos", "N-IGBT", "P-IGBT", "Opamp 3 Terminal", "Opamp 5 Terminal", "Basic Transformer", "Transformer With Core" }));
        toolSelector.setToolTipText("");
        toolSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                toolSelectorItemStateChanged(evt);
            }
        });

        jLabel5.setText("Tool");

        jMenu1.setText("File");

        saveOption.setText("Save");
        saveOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOptionActionPerformed(evt);
            }
        });
        jMenu1.add(saveOption);

        openOption.setText("Open");
        openOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openOptionActionPerformed(evt);
            }
        });
        jMenu1.add(openOption);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Edit");

        preferences.setText("Preferences");
        preferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesActionPerformed(evt);
            }
        });
        jMenu4.add(preferences);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Help");

        jMenuItem3.setText("About");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schematicWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(componentString, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(componentLabel)
                            .addComponent(jLabel5)
                            .addComponent(toolSelector, 0, 171, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toolSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(componentString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(componentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addComponent(schematicWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOptionActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setApproveButtonText("Save");
        fc.setCurrentDirectory(new File("."));

        int returnVal = fc.showOpenDialog(GUI.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file;
            if ((fc.getSelectedFile().getAbsolutePath() + "").contains(".ikz")) {
                file = new File(fc.getSelectedFile().getAbsoluteFile() + "");
            } else {
                file = new File(fc.getSelectedFile().getAbsoluteFile() + ".ikz");
            }
            try {
                //This is where a real application would open the file.
//            log.append("Opening: " + file.getName() + "." + newline);
                BufferedWriter w = new BufferedWriter(new FileWriter(file));
                w.write(schematicWindow.getCircuitXML());
                w.close();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
//            log.append("Open command cancelled by user." + newline);
        }

    }//GEN-LAST:event_saveOptionActionPerformed

    private void openOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openOptionActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        int returnVal = fc.showOpenDialog(GUI.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try {
                String xml = new String(Files.readAllBytes(file.toPath()));
                schematicWindow.loadCircuitFromXML(xml);
                updateComponentList();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
//            log.append("Open command cancelled by user." + newline);
        }
    }//GEN-LAST:event_openOptionActionPerformed

    private void componentListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_componentListValueChanged
        //stuff that happens whenever we change the selected component in the selection list
        schematicWindow.setSelectedComponentIndex(componentList.getSelectedIndex());
        componentString.setText(schematicWindow.getSelectedComponentLatexString());
        componentLabel.setText(schematicWindow.getSelectedComponentLabel());
    }//GEN-LAST:event_componentListValueChanged

    private void componentListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_componentListFocusGained

    }//GEN-LAST:event_componentListFocusGained

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

    }//GEN-LAST:event_formKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        keyHandler(evt);
    }//GEN-LAST:event_formKeyPressed

    private void componentStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_componentStringActionPerformed


    }//GEN-LAST:event_componentStringActionPerformed

    private void componentStringCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_componentStringCaretUpdate
        //stuff that happens whenever component string is updated, mostly just need to pass the current string down the line so that the component's field can be updated
        schematicWindow.setSelectedComponentLatexString(componentString.getText());
        updateLatexString(); //since we made a change that affects the latex output we need to update the output window
    }//GEN-LAST:event_componentStringCaretUpdate

    private void componentStringFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_componentStringFocusGained

    }//GEN-LAST:event_componentStringFocusGained

    private void componentListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_componentListKeyPressed
        keyHandler(evt);
        updateComponentList();
    }//GEN-LAST:event_componentListKeyPressed

    private void componentLabelCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_componentLabelCaretUpdate
        schematicWindow.setSelectedComponentLabel(componentLabel.getText());
        updateLatexString(); //since we made a change that affects the latex output we need to update the output window
    }//GEN-LAST:event_componentLabelCaretUpdate

    private void componentLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_componentLabelFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_componentLabelFocusGained

    private void componentLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_componentLabelActionPerformed

    }//GEN-LAST:event_componentLabelActionPerformed

    private void componentLabelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_componentLabelFocusLost
        updateComponentList(); //since what we're changing here affects how the list displays this is important
    }//GEN-LAST:event_componentLabelFocusLost

    private void toolSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_toolSelectorItemStateChanged
        CircuitMaker.currentTool = toolSelector.getSelectedIndex();
    }//GEN-LAST:event_toolSelectorItemStateChanged

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //just the "about" window, just want there to be some information inside the program itself somewhere.
        JOptionPane.showMessageDialog(this, "Circuitikz Tool by Matthew James Bellafaire \nProject Github Repo https://github.com/Bellafaire/CircuiTikZ-Tool", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void outputFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_outputFieldFocusGained
        //last check, when the user clicks into this window we need to make sure the output is up to date
        updateLatexString();
    }//GEN-LAST:event_outputFieldFocusGained

    private void preferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesActionPerformed
//           LatexStringBuilder w = new LatexStringBuilder(CircuitikzTool.ui, true, components.get(componentIndex));
//        w.setLocationRelativeTo(null);
//        w.setVisible(true);
        PreferencesConfig w = new PreferencesConfig(this, true);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }//GEN-LAST:event_preferencesActionPerformed

    /**
     * updates the UI Component list with a current list of all the components
     * in the schematic window. Should be called as often as possible if any
     * change has occurred to any of the components in the schematic window
     */
    public void updateComponentList() {
        String[] listItems = schematicWindow.getComponentList();
        componentList.setListData(listItems);
        componentList.setSelectedIndex(schematicWindow.getSelectedComponentIndex());
    }

    /**
     *
     */
    public void updateTheme() {

        componentLabel.setBackground(Preferences.themeAccent);
        componentList.setBackground(Preferences.themeAccent);
        componentString.setBackground(Preferences.themeAccent);

        jLabel1.setBackground(Preferences.themeBackgroundColor);
        jLabel2.setBackground(Preferences.themeBackgroundColor);
        jLabel3.setBackground(Preferences.themeBackgroundColor);
        jLabel4.setBackground(Preferences.themeBackgroundColor);
        jLabel5.setBackground(Preferences.themeBackgroundColor);

        toolSelector.setBackground(Preferences.themeAccent);
        jMenu1.setBackground(Preferences.themeAccent);
        jMenu2.setBackground(Preferences.themeAccent);
        jMenu3.setBackground(Preferences.themeAccent);
        jMenu4.setBackground(Preferences.themeAccent);
        jMenuBar1.setBackground(Preferences.themeAccent);
        jMenuItem3.setBackground(Preferences.themeAccent);
        jTextField1.setBackground(Preferences.themeAccent);
        openOption.setBackground(Preferences.themeAccent);
        outputField.setBackground(Preferences.themeAccent);
        preferences.setBackground(Preferences.themeAccent);
        saveOption.setBackground(Preferences.themeAccent);

        componentLabel.setForeground(Preferences.themeText);
        componentList.setForeground(Preferences.themeText);
        componentString.setForeground(Preferences.themeText);

        jLabel1.setForeground(Preferences.themeText);
        jLabel2.setForeground(Preferences.themeText);
        jLabel3.setForeground(Preferences.themeText);
        jLabel4.setForeground(Preferences.themeText);
        jLabel5.setForeground(Preferences.themeText);

        toolSelector.setForeground(Preferences.themeText);
        jMenu1.setForeground(Preferences.themeText);
        jMenu2.setForeground(Preferences.themeText);
        jMenu3.setForeground(Preferences.themeText);
        jMenu4.setForeground(Preferences.themeText);
        jMenuBar1.setForeground(Preferences.themeText);
        jMenuItem3.setForeground(Preferences.themeText);
        jTextField1.setForeground(Preferences.themeText);
        openOption.setForeground(Preferences.themeText);
        outputField.setForeground(Preferences.themeText);
        preferences.setForeground(Preferences.themeText);
        saveOption.setForeground(Preferences.themeText);

    }

    /**
     * updates the component label field of the UI, used by the schematicWindow
     * to change the UI Fields when a component is selected
     *
     * @param text value for the UI "Component Label" field to be set to
     */
    public void updateComponentLabel(String text) {
        componentLabel.setText(text);
    }

    /**
     * updates the component string field of the UI, used by the schematicWindow
     * to change the UI Fields when a component is selected
     *
     * @param text value for the UI "Component String" field to be set to
     */
    public void updateComponentString(String text) {
        componentString.setText(text);
    }

    private void keyHandler(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DELETE || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            //TODO: removing items in this manner causes a bug where the list doesn't update properly since it's controlled in circuitmaker.java
            schematicWindow.deleteSelectedComponent();
        }
    }

    /**Redraws the main circuit window on the main GUI
     *
     */
    public void repaintCircuitMaker() {
        schematicWindow.repaint();
    }

    /**
     * Updates the output latex string by calling the generateLatexString()
     * function of the schematicWindow class CircuitMaker
     *
     */
    public void updateLatexString() {
        //update output with the current LaTex string of the circuit
        outputField.setText(schematicWindow.generateLatexString());
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
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JTextField componentLabel;
    public javax.swing.JList<String> componentList;
    private javax.swing.JTextField componentString;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem openOption;
    public javax.swing.JTextPane outputField;
    private javax.swing.JMenuItem preferences;
    private javax.swing.JMenuItem saveOption;
    /*
    private javax.swing.JPanel schematicWindow;
    */
    private CircuitMaker schematicWindow;
    private javax.swing.JComboBox<String> toolSelector;
    // End of variables declaration//GEN-END:variables
}
