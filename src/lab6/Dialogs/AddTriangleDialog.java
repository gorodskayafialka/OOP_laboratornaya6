package lab6.Dialogs;

import javax.swing.*;
import java.awt.event.*;
import lab6.from_lab2.*;

public class AddTriangleDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField AInput;
    private JTextField BInput;
    private JTextField CInput;
    private ShapeConsumer shapeConsumer;

    public AddTriangleDialog(ShapeConsumer shapeConsumer) {
        this.shapeConsumer = shapeConsumer;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        pack();
    }

    private void onOK() {
        String CheckA = AInput.getText();
        String CheckB = BInput.getText();
        String CheckC = CInput.getText();
        double A;
        double B;
        double C;
        if ( CheckA.isEmpty() || CheckB.isEmpty()|| CheckC.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all the text fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            A = Double.parseDouble(CheckA);
            B = Double.parseDouble(CheckB);
            C = Double.parseDouble(CheckC);
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,
                    "Incorrect number format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ((A <= 0)||(B <= 0)||(C <= 0)) {
            JOptionPane.showMessageDialog(this,
                    "All sides should be positive", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Triangle triangle = new Triangle(A, B, C);
        shapeConsumer.addShape(triangle);
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}