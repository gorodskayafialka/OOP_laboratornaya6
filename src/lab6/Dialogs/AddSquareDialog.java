package lab6.Dialogs;


import javax.swing.*;
import java.awt.event.*;

import lab6.Dialogs.ShapeConsumer;
import lab6.from_lab2.*;

public class AddSquareDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField AInput;
    private ShapeConsumer shapeConsumer;

    public AddSquareDialog(ShapeConsumer shapeConsumer) {
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
        String Check = AInput.getText();
        double  A = Double.parseDouble(Check);
        if ( Check.isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the side's length", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            A = Double.parseDouble(Check);
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,
                    "Incorrect number format", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (A <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Side should be positive", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Square square = new Square(A);
        shapeConsumer.addShape(square);
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}