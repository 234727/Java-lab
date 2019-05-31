package com.autoSerwis;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * Program: Aplikacja okienkowa z GUI umożliwiająca działania na grupach obiektów klasy Car
 *
 * Plik: GroupOfCars.java
 *
 * Autor: Elżbieta Czerniak
 * Data: listopad 2018r.
 */

public class GroupOfCarsApp  extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static final String GREETING_MESSAGE = "Program Group Of Cars - wersja okienkowa\n" +
            "Autor: Elżbieta Czerniak\n" + "Data: listopad 2018r.\n";

    private GroupOfCars groupOfCars;
    private List<GroupOfCars> groupList = new ArrayList<>();
    //elementy okna
    //-------------------- panels -----------------------------------------
    JPanel containerPanel;
    JScrollPane scPane;
    JPanel bottomPanlel;
    //------------------- tabela ---------------------------------
    JTable dataTable;
    DefaultTableModel tableModel;
    //------------------ przyciski -------------------------------
    JButton createButton = new JButton("Create");
    JButton editButton = new JButton("Edit group");
    JButton saveButton = new JButton("Save to file");
    JButton loadButton = new JButton("Load from file");
    JButton deleteButton = new JButton("Delete group");

    JButton sumButton = new JButton("Union");
    JButton intersectionButton = new JButton("Intersection ");
    JButton differenceButton = new JButton("Difference ");
    JButton symmetricDifferenceButton = new JButton("Symmetric difference ");
    //------------- pasek menu ------------------------------------
    //---------------------- menu ---------------------------------------------
    JMenuBar menuBar = new JMenuBar();
    JMenu menu1 = new JMenu("Groups");
    JMenu menu2 = new JMenu("Special Groups");
    JMenuItem menu3 = new JMenuItem("About program");

    JMenuItem createItem = new JMenuItem("Create group");
    JMenuItem editItem = new JMenuItem("Edit group");
    JMenuItem deleteItem = new JMenuItem("Delete group");
    JMenuItem loadItem = new JMenuItem("Load from file");
    JMenuItem saveItem = new JMenuItem("Save to file");

    JMenuItem sumItem = new JMenuItem("Union");
    JMenuItem intersectionItem= new JMenuItem("Intersection");
    JMenuItem differenceItem = new JMenuItem("Difference");
    JMenuItem symmetricDifferenceItem = new JMenuItem("Symmetric Difference");


    public GroupOfCarsApp()
    {
        //--------------- Window's settings -------------------------
        int width = 540;
        int height = 510;
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Groups Of Cars");

        //--------- PANELS -----------------------------------------
        containerPanel = new JPanel();
        containerPanel.setLayout(null);//(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(new Color(0x303030));

       //************* scPane + tablea***************************************

        String[] colsNames =  new String[]{ "Group name", "Group type", "Number of car"};
        int rowCount = 0;
        tableModel = new DefaultTableModel(colsNames, rowCount);
        dataTable = new JTable(tableModel){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            };
        };

        JScrollPane scPane = new JScrollPane(dataTable);
        dataTable.setFillsViewportHeight(true);

        scPane.setBounds(0, 15, width, 300);

        //************ panel dolny ****************************************
        bottomPanlel = new JPanel();
        bottomPanlel.setLayout(null);
        bottomPanlel.setBounds(0, scPane.getY()+scPane.getHeight(), width, scPane.getHeight());
        bottomPanlel.setBackground(new Color(0x303030));

        // -------------- formatowanie --------------------------------
        //----------------- KOLOR -------------------------------------
        Color buttonBackgroundColor = new Color(0x000000);
        Color buttonLineColor = new Color(0xFFFFFF);
        Color textFileLineColor = new Color(0x000000);
        Color buttonTextColor = new Color(0xFF5A02);

        //************* wypełnienie ***********************************
        createButton.setBackground(buttonBackgroundColor);
        editButton.setBackground(buttonBackgroundColor);
        deleteButton.setBackground(buttonBackgroundColor);
        saveButton.setBackground(buttonBackgroundColor);
        loadButton.setBackground(buttonBackgroundColor);
        sumButton.setBackground(buttonBackgroundColor);
        intersectionButton.setBackground(buttonBackgroundColor);
        differenceButton.setBackground(buttonBackgroundColor);
        symmetricDifferenceButton.setBackground(buttonBackgroundColor);

        //***************** obramowanie *********************************
        createButton.setBorder(new LineBorder(buttonLineColor));
        editButton.setBorder(new LineBorder(buttonLineColor));
        deleteButton.setBorder(new LineBorder(buttonLineColor));
        saveButton.setBorder(new LineBorder(buttonLineColor));
        loadButton.setBorder(new LineBorder(buttonLineColor));
        sumButton.setBorder(new LineBorder(buttonLineColor));
        intersectionButton.setBorder(new LineBorder(buttonLineColor));
        differenceButton.setBorder(new LineBorder(buttonLineColor));
        symmetricDifferenceButton.setBorder(new LineBorder(buttonLineColor));

        //*************** kolor czcionki ********************************
        createButton.setForeground(buttonTextColor);
        editButton.setForeground(buttonTextColor);
        saveButton.setForeground(buttonTextColor);
        loadButton.setForeground(buttonTextColor);
        deleteButton.setForeground(buttonTextColor);
        sumButton.setForeground(buttonTextColor);
        intersectionButton.setForeground(buttonTextColor);
        differenceButton.setForeground(buttonTextColor);
        symmetricDifferenceButton.setForeground(buttonTextColor);

        //----------------- ROZMIESZCZENIE ----------------------------
        int buttonW = 120;
        int buttonH = 30;

        createButton.setBounds(width/3 - buttonW - 10, 10, buttonW, buttonH);
        editButton.setBounds(createButton.getX() + buttonW + 20, createButton.getY(), buttonW, buttonH);
        deleteButton.setBounds(editButton.getX() + buttonW + 20, editButton.getY(), buttonW, buttonH);
        loadButton.setBounds(width/2 - buttonW - 10, createButton.getY() + buttonH + 10, buttonW, buttonH);
        saveButton.setBounds(loadButton.getX() + buttonW + 20, loadButton.getY(), buttonW, buttonH);
        sumButton.setBounds(width/4 - buttonW - 10, loadButton.getY() + buttonH + 10, buttonW, buttonH);
        intersectionButton.setBounds(sumButton.getX() + buttonW + 10, sumButton.getY(), buttonW, buttonH);
        differenceButton.setBounds(intersectionButton.getX()+ buttonW + 10, sumButton.getY(), buttonW, buttonH);
        symmetricDifferenceButton.setBounds(differenceButton.getX() + buttonW + 10, differenceButton.getY(), buttonW + 10, buttonH);

        //----------------- TABELA -------------------------------------
        dataTable.setBackground(Color.black);//new Color(0x434343));
        dataTable.setBorder(new LineBorder(textFileLineColor));
        dataTable.setForeground(Color.white);

        //------------------- MENU ------------------------------------
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        menu1.add(createItem);
        menu1.add(editItem);
        menu1.add(deleteItem);
        menu1.add(loadItem);
        menu1.add(saveItem);

        menu2.add(sumItem);
        menu2.add(intersectionItem);
        menu2.add(differenceItem);
        menu2.add(symmetricDifferenceItem);

        // --------------------  wygląd menu ---------------------------
        Color backgroundMenuColor = new Color(0x2A2A2A);
        Color itemTextColor = new Color(0xFDC64F);
        menuBar.setBackground(new Color(0x000000));
        menu1.setForeground(buttonTextColor);
        menu2.setForeground(buttonTextColor);
        menu3.setForeground(buttonTextColor);
        menu3.setBackground(Color.black);
        menuBar.setBorder(new LineBorder(Color.black));

        //****************** kolor tła dla itemów w menu *******************
        createItem.setBackground(backgroundMenuColor);
        editItem.setBackground(backgroundMenuColor);
        deleteItem.setBackground(backgroundMenuColor);
        loadItem.setBackground(backgroundMenuColor);
        saveItem.setBackground(backgroundMenuColor);
        sumItem.setBackground(backgroundMenuColor);
        intersectionItem.setBackground(backgroundMenuColor);
        differenceItem.setBackground(backgroundMenuColor);
        symmetricDifferenceItem.setBackground(backgroundMenuColor);

        //****************** kolor czconki dla itemów z menu ***************
        createItem.setForeground(itemTextColor);
        editItem.setForeground(itemTextColor);
        deleteItem.setForeground(itemTextColor);
        saveItem.setForeground(itemTextColor);
        loadItem.setForeground(itemTextColor);
        sumItem.setForeground(itemTextColor);
        intersectionItem.setForeground(itemTextColor);
        differenceItem.setForeground(itemTextColor);
        symmetricDifferenceItem.setForeground(itemTextColor);

        //------------- obramowanie itemów - zamiast separatora ------
        createItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        editItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        deleteItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        saveItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        loadItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        sumItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        intersectionItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        differenceItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        symmetricDifferenceItem.setBorder(new WindowsBorders.DashedBorder(Color.black));

        //--------------- dodawanie actionListenera -------------------
        //*************** przyciski ***********************************
        editButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        createButton.addActionListener(this);
        deleteButton.addActionListener(this);
        sumButton.addActionListener(this);
        intersectionButton.addActionListener(this);
        differenceButton.addActionListener(this);
        symmetricDifferenceButton.addActionListener(this);

        //**************** pasek menu *********************************
        createItem.addActionListener(this);
        editItem.addActionListener(this);
        deleteItem.addActionListener(this);
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        sumItem.addActionListener(this);
        intersectionItem.addActionListener(this);
        differenceItem.addActionListener(this);
        symmetricDifferenceItem.addActionListener(this);

        menu3.addActionListener(this);

        //---------------- dodawanie do panelu ----------------------
        containerPanel.add(scPane);
        containerPanel.add(bottomPanlel);
        bottomPanlel.add(createButton);
        bottomPanlel.add(editButton);
        bottomPanlel.add(saveButton);
        bottomPanlel.add(loadButton);
        bottomPanlel.add(deleteButton);
        bottomPanlel.add(sumButton);
        bottomPanlel.add(intersectionButton);
        bottomPanlel.add(differenceButton);
        bottomPanlel.add(symmetricDifferenceButton);

        setContentPane(containerPanel);
        setJMenuBar(menuBar);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object eventSource = e.getSource();
        try
        {
            if(eventSource == createButton || eventSource == createItem)
            {
                groupOfCars = GroupOfCarWindowDialog.createNewGroup(this);
                groupList.add(groupOfCars);
                fillTable();
            }
            if(eventSource == editButton || eventSource == editItem)
            {
                int selectedRow = dataTable.getSelectedRow();

                if(selectedRow >= 0)
                {
                    GroupOfCarWindowDialog.changeGroupData(this, groupList.get(selectedRow));
                    fillTable();
                }
                else
                {
                    throw new CarException("Any car was not selected");
                }
            }
            if(eventSource == deleteButton || eventSource == deleteItem)
            {
                int selectedRow = dataTable.getSelectedRow();

                if(selectedRow >= 0)
                {
                    groupList.remove(selectedRow);
                    fillTable();
                }
                else
                {
                    throw new CarException("Any car was not selected");
                }
            }
            if(eventSource == loadButton || eventSource == loadItem)
            {
                String fileName = "";
                JFileChooser chooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "TXT", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(this);

                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getName();
                }
                if(fileName == null || fileName.equals("")) return;

                groupOfCars = GroupOfCars.readFromFile(fileName);
                groupList.add(groupOfCars);
                editTable(groupOfCars, tableModel.getRowCount());
            }
            if(eventSource == saveButton || eventSource == saveItem)
            {
                int selectedRow = dataTable.getSelectedRow();
                if(selectedRow >= 0)
                {
                    String fileName = JOptionPane.showInputDialog("Enter a file name");
                    if(fileName == null || fileName.equals("")) return;

                    GroupOfCars.writeToFile(fileName, groupList.get(selectedRow));
                }
                else
                {
                    throw new CarException("Any car was not selected");
                }
            }
            if(eventSource == menu3)
            {
                JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
            }
            if(eventSource == sumButton || eventSource == sumItem)
            {
                String message1 = "Create groups union\n\n" + "Choice first group\n";
                String message2 = "Create groups union\n\n" + "Choice second group\n";

                GroupOfCars group1 = chooseGroup(this, message1);
                if(group1 == null) { return; }

                GroupOfCars group2 = chooseGroup(this, message2);
                if(group2 == null) { return; }

                GroupOfCars newGroup = createUnion(group1, group2);
                groupList.add(newGroup);
                fillTable();
            }

            if(eventSource == intersectionButton || eventSource == intersectionItem)
            {
                String message1 = "Create groups intersection\n\n" + "Choice first group\n";
                String message2 = "Create groups intersection\n\n" + "Choice second group\n";

                GroupOfCars group1 = chooseGroup(this, message1);
                if(group1 == null) { return; }

                GroupOfCars group2 = chooseGroup(this, message2);
                if(group2 == null) { return; }

                GroupOfCars newGroup = createIntersection(group1, group2);
                groupList.add(newGroup);
                fillTable();
            }

            if(eventSource == differenceButton || eventSource == differenceItem)
            {
                String message1 = "Create groups difference\n\n" + "Choice first group\n";
                String message2 = "Create groups difference\n\n" + "Choice second group\n";

                GroupOfCars group1 = chooseGroup(this, message1);
                if(group1 == null) { return; }

                GroupOfCars group2 = chooseGroup(this, message2);
                if(group2 == null) { return; }

                GroupOfCars newGroup = createDifference(group1, group2);
                groupList.add(newGroup);
                fillTable();
            }

            if(eventSource == symmetricDifferenceButton || eventSource == symmetricDifferenceItem)
            {
                String message1 = "Create groups symmetric difference\n\n" + "Choice first group\n";
                String message2 = "Create groups symmetric difference\n\n" + "Choice second group\n";

                GroupOfCars group1 = chooseGroup(this, message1);
                if(group1 == null) { return; }

                GroupOfCars group2 = chooseGroup(this, message2);
                if(group2 == null) { return; }

                GroupOfCars newGroup = createSymmetricDifference(group1, group2);
                groupList.add(newGroup);
                fillTable();
            }

        } catch (CarException carExc)
        {
            JOptionPane.showMessageDialog(this, carExc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillTable()
    {
        for(int i = 0; i < tableModel.getRowCount(); ++i)
        {
            tableModel.removeRow(i);
        }

        int counter = 0;
        for(GroupOfCars group : groupList)
        {
            editTable(group, counter);
            ++counter;
        }
    }
    private void editTable( GroupOfCars group, int row)
    {
        if(group != null)
        {
            if(row >= tableModel.getRowCount())
            {
                tableModel.addRow(new Object[] {group.getGroupName(), group.getType(), group.size()});
            }
            else
            {
                int i = 0;
                tableModel.setValueAt(group.getGroupName(), row, i);
                tableModel.setValueAt(group.getType(), row, ++i);
                tableModel.setValueAt(group.size(), row, ++i);
            }
        }
    }
    public static void main(String []args)
    {
        GroupOfCarsApp window = new GroupOfCarsApp();
    }

    //************** pomocnicze metody prywatne ***********************************
    private GroupOfCars chooseGroup(Window parent, String message){
        Object [] groups = groupList.toArray();
        GroupOfCars group = (GroupOfCars)JOptionPane.showInputDialog(parent, message, "Choice a group", JOptionPane.QUESTION_MESSAGE,
                null, groups, null);
        return group;
    }
    //************************************************************************
    private GroupOfCars createUnion(GroupOfCars group1, GroupOfCars group2)
    {
        GroupOfCars newGroup = new GroupOfCars();
        try
        {
            String name = (group1.getGroupName() + " OR " + group2.getGroupName());
            String type1 = group1.getType().toString();
            String type2 = group2.getType().toString();

            if((type1.startsWith("List") && type2.startsWith("List"))
                    || (type1.startsWith("Set") && type2.startsWith("Set")))
            {
                newGroup = new GroupOfCars(name, group1.getType());
            }
            else
            {
                if(type1.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group1.getType());
                }
                else if(type2.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group2.getType());
                }
            }

            for(Car car: group1.getCollection())
            {
                newGroup.add(car);
            }
            for(Car car: group2.getCollection())
            {
                newGroup.add(car);
            }
            return newGroup;

        }  catch (CarException e) {
            e.printStackTrace();
        }
        // ?
        return null;
    }

    private GroupOfCars createIntersection(GroupOfCars group1, GroupOfCars group2)
    {
        GroupOfCars newGroup = new GroupOfCars();
        try
        {
            String name = (group1.getGroupName() + " AND " + group2.getGroupName());
            String type1 = group1.getType().toString();
            String type2 = group2.getType().toString();

            if((type1.startsWith("List") && type2.startsWith("List"))
                    || (type1.startsWith("Set") && type2.startsWith("Set")))
            {
                newGroup = new GroupOfCars(name, group1.getType());
            }
            else
            {
                if(type1.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group1.getType());
                }
                else if(type2.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group2.getType());
                }
            }

            for(Car car1: group1.getCollection())
            {
                for(Car car2: group2.getCollection())
                {
                    if(car1.equals(car2))
                    {
                        newGroup.add(car1);
                    }
                }
            }

            return newGroup;

        }  catch (CarException e) {
            e.printStackTrace();
        }
        // ?
        return null;
    }

    private GroupOfCars createDifference(GroupOfCars group1, GroupOfCars group2)
    {
        GroupOfCars newGroup = new GroupOfCars();
        try
        {
            String name = (group1.getGroupName() + " SUB " + group2.getGroupName());
            String type1 = group1.getType().toString();
            String type2 = group2.getType().toString();

            if((type1.startsWith("List") && type2.startsWith("List"))
                    || (type1.startsWith("Set") && type2.startsWith("Set")))
            {
                newGroup = new GroupOfCars(name, group1.getType());
            }
            else
            {
                if(type1.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group1.getType());
                }
                else if(type2.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group2.getType());
                }
            }

            for(Car car1: group1.getCollection())
            {
                boolean condition1 = false;
                for(Car car2: group2.getCollection())
                {
                    if(car1.equals(car2))
                    {
                        condition1 = true;
                        break;
                    }
                }
                if(condition1 == false)
                {
                    newGroup.add(car1);
                }
            }

            return newGroup;

        }  catch (CarException e) {
            e.printStackTrace();
        }
        // ?
        return null;
    }

    private GroupOfCars createSymmetricDifference(GroupOfCars group1, GroupOfCars group2)
    {
        GroupOfCars newGroup = new GroupOfCars();
        String name = (group1.getGroupName() + " XOR " + group2.getGroupName());
        try
        {
            String type1 = group1.getType().toString();
            String type2 = group2.getType().toString();

            if((type1.startsWith("List") && type2.startsWith("List"))
                    || (type1.startsWith("Set") && type2.startsWith("Set")))
            {
                newGroup = new GroupOfCars(name, group1.getType());
            }
            else
            {
                if(type1.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group1.getType());
                }
                else if(type2.startsWith("Set"))
                {
                    newGroup = new GroupOfCars(name, group2.getType());
                }
            }

            newGroup = createUnion(group1, group2);
            GroupOfCars helper = createIntersection(group1, group2);

            for(Car car1: newGroup.getCollection())
            {
                for(Car car2: helper.getCollection())
                {
                    if(car1.equals(car2))
                    {
                        newGroup.getCollection().remove(car1);
                        helper.getCollection().remove(car2);
                        break;
                    }
                }
            }

            newGroup.setGroupName(name);

            return newGroup;

        }  catch (CarException e) {
            e.printStackTrace();
        }
        // ?
        return null;
    }
}

