package me.hopedev.advancedlicensetester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
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



    private void onOK() {
        try {
            if (ConnectorThread.currentThread.isAlive() || ConnectorThread.currentThread == null) {
                LogHandler.setStatus("Request still running, press CANCEL to abort", Color.blue);
                return;
            }
        } catch (NullPointerException e) {
            //fuck you because java
        }

        try {

            GateKeeper.status.setVisible(true);
            String License = LicenseKey.getText();
            String Validation = VerificationURL.getText();
            boolean Debug = debugModeOutputCheckBox.isSelected();
            if (License.isEmpty() || Validation.isEmpty()) {
                LogHandler.setStatus("Please fill all the required fields!", Color.red);

            } else if (!Validation.toLowerCase().startsWith("http://") && !Validation.toLowerCase().startsWith("https://")) {
                LogHandler.setStatus("Missing Protocol (https:// or http://)", Color.red);
            } else {
                new ConnectorThread(License, Validation, Debug).connect();
            }
            //  dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void onCancel() {
        if (ConnectorThread.currentThread.isAlive()) {
            ConnectorThread.currentThread.interrupt();
            LogHandler.setStatus("Request aborted!", Color.red);
        } else {
            dispose();
        }
    }
}
