package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import lab6.from_lab2.Shape;
import lab6.from_lab2.FileWorker;
import lab6.Dialogs.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Shapes App");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        DefaultListModel<Shape> listModel = new DefaultListModel<>();
        FileWorker fileWorker = new FileWorker();
        JList<Shape> list = new JList<>(listModel);
        JButton moveDownBtn = new JButton("Move Down");
        JButton moveUpBtn = new JButton("Move Up");
        JButton removeBtn = new JButton("Remove");
        JButton createCircleBtn = new JButton("Create Circle");
        JButton createTriangleBtn = new JButton("Create Triangle");
        JButton createRectangleBtn = new JButton("Create Rectangle");
        JButton createSquareBtn = new JButton("Create Square");
        createSquareBtn.setPreferredSize(createRectangleBtn.getPreferredSize());
        moveDownBtn.setMaximumSize(createRectangleBtn.getMinimumSize());
        moveUpBtn.setMaximumSize(createRectangleBtn.getMinimumSize());
        removeBtn.setMaximumSize(createRectangleBtn.getMinimumSize());
        createCircleBtn.setMaximumSize(createRectangleBtn.getMinimumSize());
        createTriangleBtn.setMaximumSize(createRectangleBtn.getMinimumSize());
        createSquareBtn.setMaximumSize(createRectangleBtn.getMinimumSize());
        JLabel listTitle = new JLabel("Shapes");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.add(moveDownBtn);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(moveDownBtn);
        buttonsPanel.add(moveUpBtn);
        buttonsPanel.add(removeBtn);
        buttonsPanel.add(createCircleBtn);
        buttonsPanel.add(createTriangleBtn);
        buttonsPanel.add(createRectangleBtn);
        buttonsPanel.add(createSquareBtn);

        moveUpBtn.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            Shape token = listModel.get(selectedIndex);
            listModel.set(selectedIndex, listModel.get(selectedIndex-1));
            listModel.set(selectedIndex-1, token);
            list.setSelectedIndex(selectedIndex-1);
        });

        moveDownBtn.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            Shape token = listModel.get(selectedIndex);
            listModel.set(selectedIndex, listModel.get(selectedIndex+ 1));
            listModel.set(selectedIndex+1, token);
            list.setSelectedIndex(selectedIndex+1);
        });

        removeBtn.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            listModel.remove(selectedIndex);
        });

        createCircleBtn.addActionListener(e -> {
            JDialog addCircleDialog = new AddCircleDialog (newCircle -> listModel.addElement(newCircle));
            addCircleDialog.setModal(true);
            addCircleDialog.setLocationRelativeTo(this);
            addCircleDialog.setVisible(true);
        });

        createSquareBtn.addActionListener(e -> {
            JDialog addSquareDialog = new AddSquareDialog (newShape -> listModel.addElement(newShape));
            addSquareDialog.setModal(true);
            addSquareDialog.setLocationRelativeTo(this);
            addSquareDialog.setVisible(true);
        });

        createRectangleBtn.addActionListener(e -> {
            JDialog addRectangleDialog = new AddRectangleDialog (newShape -> listModel.addElement(newShape));
            addRectangleDialog.setModal(true);
            addRectangleDialog.setLocationRelativeTo(this);
            addRectangleDialog.setVisible(true);
        });

        createTriangleBtn.addActionListener(e -> {
            JDialog addTriangleDialog = new AddTriangleDialog (newShape -> listModel.addElement(newShape));
            addTriangleDialog.setModal(true);
            addTriangleDialog.setLocationRelativeTo(this);
            addTriangleDialog.setVisible(true);
        });

        list.addListSelectionListener(e -> {
            removeBtn.setEnabled(list.getSelectedIndex() != -1);
            moveDownBtn.setEnabled((list.getSelectedIndex() != -1) && (list.getSelectedIndex() != listModel.getSize()-1));
            moveUpBtn.setEnabled((list.getSelectedIndex() != -1) && (list.getSelectedIndex() != 0));
        });
        moveDownBtn.setEnabled(false);
        moveUpBtn.setEnabled(false);
        removeBtn.setEnabled(false);
        ArrayList<Shape> temp = new ArrayList<>();
        try {
            temp = fileWorker.readList("C:\\Users\\Ксения Лучкова\\IdeaProjects\\Graphic_interface\\data.dat");
        } catch (IOException e) {
            JDialog error = new JDialog();
            JOptionPane.showMessageDialog(error,
                    "Failed to recover the data", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            JDialog error = new JDialog();
            JOptionPane.showMessageDialog(error,
                    "Failed to recover the data", "Error", JOptionPane.WARNING_MESSAGE);
        }
        for (Shape current: temp) {
            listModel.addElement(current);
        }
        mainPanel.add (listTitle, BorderLayout.NORTH);
        mainPanel.add(list, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.EAST);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ArrayList<Shape> temp = new ArrayList<>();
                for (int i = 0; i < listModel.getSize(); i++) {
                    temp.add(listModel.getElementAt(i));
                }
                try {
                    fileWorker.writeList(temp,"C:\\Users\\Ксения Лучкова\\IdeaProjects\\Graphic_interface\\data.dat");
                } catch (IOException ioException) {
                    JDialog error = new JDialog();
                    JOptionPane.showMessageDialog(error,
                            "Failed to save the data", "Error", JOptionPane.WARNING_MESSAGE);
                }
                System.exit(0);
            }
        });
        setContentPane(mainPanel);
    }
}