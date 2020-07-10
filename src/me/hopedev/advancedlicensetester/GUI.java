package me.hopedev.advancedlicensetester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JDialog {
    public JTextPane OutputLog;
    public JTextField VerificationURL;
    public JTextField LicenseKey;
    public JLabel StatusRep;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox debugModeOutputCheckBox;
    private JPanel Verification;


    public GUI() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        GateKeeper.status = StatusRep;
        GateKeeper.output = OutputLog;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main() {
        GUI dialog = new GUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
        GateKeeper.status.setVisible(true);
        String License = LicenseKey.getText();
        String Validation = VerificationURL.getText();
        boolean Debug = debugModeOutputCheckBox.isSelected();
        if (License.isEmpty() || Validation.isEmpty()) {
            LogHandler.setStatus("Please fill all the required fields!", Color.red);

        } else {
            new startVerification(License, Validation, Debug);
        }
        //  dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
