package com.autoSerwis;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/*
 * Program: okno edycji obiektów klasy GroupOfCar
 *
 * Plik: GroupOfCarWindowDialog.java
 *
 * Autor: Elżbieta Czerniak
 * Data: listopad 2018r.
 */

public class GroupOfCarWindowDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static final String GREETING_MESSAGE = "Program Group Of Cars - wersja okienkowa\n" +
            "Autor: Elżbieta Czerniak\n" + "Data: listopad 2018r.\n";
    private GroupOfCars groupOfCars;
   // private Car car;

    //elementy okna
    //-------------------- panels -----------------------------------------
    JPanel containerPanel;
    JPanel topPanel;
    JScrollPane scPane;
    JPanel bottomPanlel;
    //------------- etykiety -----------------------------------
    JLabel groupNameLabel = new JLabel("Group name: ");
    JLabel groupTypeLabel = new JLabel("Group type: ");
    //---------------- pola tekstowe ----------------------------
    JTextField groupNameText = new JTextField();
    JTextField groupTypeText = new JTextField();
    //------------------- tabela ---------------------------------
    JTable dataTable;
    DefaultTableModel tableModel;
    //------------------ przyciski -------------------------------
    JButton addButton = new JButton("Add new car");
    JButton editButton = new JButton("Edit car");
    JButton saveButton = new JButton("Save to file");
    JButton loadButton = new JButton("Load from file");
    JButton deleteButton = new JButton("Delete car");
    //------------- pasek menu ------------------------------------
    //---------------------- menu ---------------------------------------------
    JMenuBar menuBar = new JMenuBar();
    JMenu menu1 = new JMenu("List of cars");
    JMenu menu2 = new JMenu("Sort");
    JMenu menu3 = new JMenu("Group attributes");
    JMenuItem menu4 = new JMenuItem("About program");

    JMenuItem addCarItem = new JMenuItem("Add new car");
    JMenuItem editCarItem = new JMenuItem("Edit car");
    JMenuItem deleteCarItem = new JMenuItem("Delete car");
    JMenuItem loadCarItem = new JMenuItem("Load from file");
    JMenuItem saveCarItem = new JMenuItem("Save to file");

    JMenuItem sortByManufacturerItem = new JMenuItem("Sort by manufacturer");
    JMenuItem sortByModelItem = new JMenuItem("Sort by model");
    JMenuItem sortByRegDateItem = new JMenuItem("Sort by reg. date");
    JMenuItem sortByMileageItem = new JMenuItem("Sort by mileage");
    JMenuItem sortByFuelItem = new JMenuItem("Sort by fuel type");

    JMenuItem editGroupNameItem = new JMenuItem("Edit group name");
    JMenuItem editGroupTypeItem = new JMenuItem("Edit group type");
    //********************* constructor *****************************
    public GroupOfCarWindowDialog(Window parent, GroupOfCars group)
    {
        super(parent, Dialog.ModalityType.DOCUMENT_MODAL);
           this.groupOfCars = group;
           // car = new Car();
        //--------------- Window's settings -------------------------
        int width = 500;
        int height = 510;
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Group Of Cars Details");

        //--------- PANELS -----------------------------------------
        containerPanel = new JPanel();
        containerPanel.setLayout(null);
        containerPanel.setBackground(new Color(0x303030));
        //************* panel górny *********************************
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, width, 60);
        topPanel.setBackground(new Color(0x303030));
        //************* scPane + tablea***************************************
        String[] colsNames =  new String[]{ "Manufacturer", "Model", "Reg. date", "Mileage", "Fuel type"};
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
        scPane.setBounds(0, topPanel.getY()+topPanel.getHeight(), width, 300);
        //************ panel dolny ****************************************
        bottomPanlel = new JPanel();
        bottomPanlel.setLayout(null);
        bottomPanlel.setBounds(0, scPane.getY()+scPane.getHeight(), width, height - topPanel.getHeight() + scPane.getHeight());
        bottomPanlel.setBackground(new Color(0x303030));

        // -------------- formatowanie --------------------------------
        //----------------- KOLOR -------------------------------------
        Color buttonBackgroundColor = new Color(0x000000);
        Color labelBackgroundColor = new Color(0x101010);
        Color textFileBackgroundColor = new Color(0xFDC64F);

        Color buttonLineColor = new Color(0xFFFFFF);
        Color labelLineColor = new Color(0x000000);
        Color textFileLineColor = new Color(0x000000);

        Color buttonTextColor = new Color(0xFF5A02);
        Color labelTextColor = new Color(0xFF5A02);
        Color textFileTextColor = new Color(0x000000);

        //************* wypełnienie ***********************************
        addButton.setBackground(buttonBackgroundColor);
        editButton.setBackground(buttonBackgroundColor);
        deleteButton.setBackground(buttonBackgroundColor);
        saveButton.setBackground(buttonBackgroundColor);
        loadButton.setBackground(buttonBackgroundColor);

        groupNameLabel.setBackground(labelBackgroundColor);
        groupTypeLabel.setBackground(labelBackgroundColor);

        groupNameText.setBackground(textFileBackgroundColor);
        groupTypeText.setBackground(textFileBackgroundColor);
        //***************** obramowanie *********************************
        addButton.setBorder(new LineBorder(buttonLineColor));
        editButton.setBorder(new LineBorder(buttonLineColor));
        deleteButton.setBorder(new LineBorder(buttonLineColor));
        saveButton.setBorder(new LineBorder(buttonLineColor));
        loadButton.setBorder(new LineBorder(buttonLineColor));

        groupTypeLabel.setBorder(new LineBorder(labelLineColor));
        groupNameLabel.setBorder(new LineBorder(labelLineColor));

        groupTypeText.setBorder(new LineBorder(textFileLineColor));
        groupNameText.setBorder(new LineBorder(textFileLineColor));

        //*************** kolor czcionki ********************************
        addButton.setForeground(buttonTextColor);
        editButton.setForeground(buttonTextColor);
        saveButton.setForeground(buttonTextColor);
        loadButton.setForeground(buttonTextColor);
        deleteButton.setForeground(buttonTextColor);

        groupNameLabel.setForeground(labelTextColor);
        groupTypeLabel.setForeground(labelTextColor);

        groupNameText.setForeground(textFileTextColor);
        groupTypeText.setForeground(textFileTextColor);
        //----------------- ROZMIESZCZENIE ----------------------------

        int labelW = 150;
        int labelH = 15;
        int labelX = (width/2) - labelW - 15;
        int labelY = 10;
        int buttonW = 120;
        int buttonH = 30;

        addButton.setBounds(width/3 - buttonW - 10, 10, buttonW, buttonH);
        editButton.setBounds(addButton.getX() + buttonW + 20, addButton.getY(), buttonW, buttonH);
        deleteButton.setBounds(editButton.getX() + buttonW + 20, editButton.getY(), buttonW, buttonH);
        loadButton.setBounds(width/2 - buttonW - 10, addButton.getY() + buttonH + 10, buttonW, buttonH);
        saveButton.setBounds(loadButton.getX() + buttonW + 20, loadButton.getY(), buttonW, buttonH);

        groupNameLabel.setBounds(labelX, labelY, labelW, labelH);
        groupTypeLabel.setBounds(labelX, labelY + labelH + 10, labelW, labelH);

        groupNameText.setBounds(labelX + labelW + 20, labelY, labelW, labelH);
        groupTypeText.setBounds(labelX + labelW + 20, labelY + labelH + 10, labelW, labelH);

        //----------------- TABELA -------------------------------------
        dataTable.setBackground(Color.black);
        dataTable.setBorder(new LineBorder(textFileLineColor));
        dataTable.setForeground(Color.white);
        //------------------- MENU ------------------------------------
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

        menu1.add(addCarItem);
        menu1.add(editCarItem);
        menu1.add(deleteCarItem);
        menu1.add(loadCarItem);
        menu1.add(saveCarItem);

        menu2.add(sortByManufacturerItem);
        menu2.add(sortByModelItem);
        menu2.add(sortByRegDateItem);
        menu2.add(sortByMileageItem);
        menu2.add(sortByFuelItem);

        menu3.add(editGroupNameItem);
        menu3.add(editGroupTypeItem);
        // --------------------  wyglą menu ---------------------------
        Color backgroundMenuColor = new Color(0x2A2A2A);
        Color itemTextColor = new Color(0xFDC64F);
        menuBar.setBackground(new Color(0x000000));
        menu1.setForeground(buttonTextColor);
        menu2.setForeground(buttonTextColor);
        menu3.setForeground(buttonTextColor);
        menu4.setForeground(buttonTextColor);
        menu4.setBackground(Color.black);
        menuBar.setBorder(new LineBorder(Color.black));
        //****************** kolor tła dla itemów w menu *******************
        addCarItem.setBackground(backgroundMenuColor);
        editCarItem.setBackground(backgroundMenuColor);
        deleteCarItem.setBackground(backgroundMenuColor);
        loadCarItem.setBackground(backgroundMenuColor);
        saveCarItem.setBackground(backgroundMenuColor);

        sortByManufacturerItem.setBackground(backgroundMenuColor);
        sortByModelItem.setBackground(backgroundMenuColor);
        sortByRegDateItem.setBackground(backgroundMenuColor);
        sortByMileageItem.setBackground(backgroundMenuColor);
        sortByFuelItem.setBackground(backgroundMenuColor);

        editGroupTypeItem.setBackground(backgroundMenuColor);
        editGroupNameItem.setBackground(backgroundMenuColor);
        //****************** kolor czconki dla itemów z menu ***************
        addCarItem.setForeground(itemTextColor);
        editCarItem.setForeground(itemTextColor);
        deleteCarItem.setForeground(itemTextColor);
        saveCarItem.setForeground(itemTextColor);
        loadCarItem.setForeground(itemTextColor);

        sortByManufacturerItem.setForeground(itemTextColor);
        sortByModelItem.setForeground(itemTextColor);
        sortByMileageItem.setForeground(itemTextColor);
        sortByRegDateItem.setForeground(itemTextColor);
        sortByFuelItem.setForeground(itemTextColor);

        editGroupNameItem.setForeground(itemTextColor);
        editGroupTypeItem.setForeground(itemTextColor);
        //------------- obramowanie itemów - zamiast separatora ------
        addCarItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        editCarItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        deleteCarItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        saveCarItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        loadCarItem.setBorder(new WindowsBorders.DashedBorder(Color.black));

        sortByManufacturerItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        sortByModelItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        sortByMileageItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        sortByRegDateItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        sortByFuelItem.setBorder(new WindowsBorders.DashedBorder(Color.black));

        editGroupTypeItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        editGroupNameItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        // ------------------ inne ------------------------------------
        groupNameText.setEditable(false);
        groupTypeText.setEditable(false);

        groupTypeLabel.setOpaque(true);
        groupNameLabel.setOpaque(true);
        //------------------ dodawanie danych -------------------------
        if(groupOfCars != null)
        {
            if(groupOfCars.getGroupName() != null)
                groupNameText.setText(groupOfCars.getGroupName());
            if(groupOfCars.getType() != null)
                groupTypeText.setText(groupOfCars.getType().toString());

            fillTable();
        }
        //--------------- dodawanie actionListenera -------------------
        //*************** przyciski ***********************************
        editButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        //**************** pasek menu *********************************
        addCarItem.addActionListener(this);
        editCarItem.addActionListener(this);
        deleteCarItem.addActionListener(this);
        loadCarItem.addActionListener(this);
        saveCarItem.addActionListener(this);

        sortByManufacturerItem.addActionListener(this);
        sortByModelItem.addActionListener(this);
        sortByRegDateItem.addActionListener(this);
        sortByMileageItem.addActionListener(this);
        sortByFuelItem.addActionListener(this);

        editGroupNameItem.addActionListener(this);
        editGroupTypeItem.addActionListener(this);

        menu4.addActionListener(this);
        //---------------- dodawanie do panelu ----------------------
        containerPanel.add(topPanel);
        containerPanel.add(scPane);
        containerPanel.add(bottomPanlel);

        topPanel.add(groupNameLabel);
        topPanel.add(groupNameText);
        topPanel.add(groupTypeLabel);
        topPanel.add(groupTypeText);

        bottomPanlel.add(addButton);
        bottomPanlel.add(editButton);
        bottomPanlel.add(saveButton);
        bottomPanlel.add(loadButton);
        bottomPanlel.add(deleteButton);

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
            if(eventSource == addButton || eventSource == addCarItem)
            {
                Car newCar = CarWindowDialog.createNewCar(this);
                if(newCar != null)
                {
                    groupOfCars.add(newCar);
                    editTable(newCar, tableModel.getRowCount());
                }
            }

            if(eventSource == editButton || eventSource == editCarItem)
            {
                int selectedRow = dataTable.getSelectedRow();

                if(selectedRow >= 0)
                {
                    Car newCar = getCarFromTable(selectedRow);

                    for(Car c: groupOfCars.getCollection())
                    {
                        if(newCar.equals(c))
                        {
                            CarWindowDialog.changeCarData(this, c);
                            editTable(c, selectedRow);
                            break;
                        }
                    }
                }
                else
                {
                        throw new CarException("Any car was not selected");
                }
            }
            if(eventSource == deleteButton || eventSource == deleteCarItem)
            {
                int selectedRow = dataTable.getSelectedRow();
                if(selectedRow >= 0)
                {
                    Car newCar = getCarFromTable(selectedRow);
                    groupOfCars.getCollection().remove(newCar);
                    tableModel.removeRow(selectedRow);
                }
                else
                {
                    throw new CarException("Any car was not selected");
                }
            }
            if(eventSource == loadButton || eventSource == loadCarItem)
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
               groupNameText.setText(groupOfCars.getGroupName());
               groupTypeText.setText(groupOfCars.getType().toString());
               fillTable();
            }
            if(eventSource == saveButton || eventSource == saveCarItem)
            {
                String fileName = JOptionPane.showInputDialog("Enter a file name");
                if(fileName == null || fileName.equals("")) return;

                GroupOfCars.writeToFile(fileName, groupOfCars);
            }
            if(eventSource == sortByManufacturerItem)
            {
                groupOfCars.sortManufacturer();
                fillTable();
            }
            if(eventSource == sortByModelItem)
            {
                groupOfCars.sortModel();
                fillTable();
            }
            if(eventSource == sortByRegDateItem)
            {
                groupOfCars.sortRegDate();
                fillTable();
            }
            if(eventSource == sortByMileageItem)
            {
                groupOfCars.sortMileage();
                fillTable();
            }
            if(eventSource == sortByFuelItem)
            {
                groupOfCars.sortFuelType();
                fillTable();
            }
            if(eventSource == editGroupNameItem)
            {
                String name = JOptionPane.showInputDialog("Enter a group name");
                if(name == null || name.equals("")) return;

                groupOfCars.setGroupName(name);
                groupNameText.setText(name);
            }
            if(eventSource == editGroupTypeItem)
            {
                    String type = (String) JOptionPane.showInputDialog(null, "Choice a type of group",
                        "Group type", JOptionPane.QUESTION_MESSAGE, null, new Object[] {
                        GroupType.ARRAY_LIST.toString(), GroupType.LINKED_LIST.toString(), GroupType.HASH_SET.toString(),
                        GroupType.TREE_SET.toString(), GroupType.VECTOR.toString()
                        }, groupTypeText.getText());

                if(type == null || type.equals("")) return;

                groupOfCars.setGroupType(type);
                groupTypeText.setText(type);

                fillTable();
            }
            if(eventSource == menu4)
            {
                JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
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
        for(Car car : groupOfCars.getCollection())
        {
            editTable(car, counter);
            ++counter;
        }
    }
    private void editTable( Car car, int row)
    {
        if(car != null && groupOfCars != null)
        {
            if(row >= tableModel.getRowCount())
            {
                tableModel.addRow(new Object[] {car.getManufacturer(), car.getModel(), car.getRegDate(), car.getMileage(), car.getFuel()});
            }
            else
            {
                int i = 0;
                tableModel.setValueAt(car.getManufacturer(), row, i);
                tableModel.setValueAt(car.getModel(), row, ++i);
                tableModel.setValueAt(car.getRegDate(), row, ++i);
                tableModel.setValueAt(car.getMileage(), row, ++i);
                tableModel.setValueAt(car.getFuel(), row, ++i);
            }
        }
    }
    private Car getCarFromTable( int row) throws CarException
    {
        Car newCar = new Car();
        int i = 0;
        newCar.setManufacturer((String)tableModel.getValueAt(row, i));
        newCar.setModel((String)tableModel.getValueAt(row, ++i));
        newCar.setRegDate((LocalDate)tableModel.getValueAt(row, ++i));
        newCar.setMileage((Integer)tableModel.getValueAt(row, ++i));
        newCar.setFuel((Fuel)tableModel.getValueAt(row, ++i));

        return newCar;
    }

    public static GroupOfCars createNewGroup(Window parent)
    {
        GroupOfCarWindowDialog dialog = new GroupOfCarWindowDialog(parent, null);
        return dialog.groupOfCars;
    }

    public static void changeGroupData(Window parent, GroupOfCars group)
    {
        GroupOfCarWindowDialog  groupOfCarWindowDialog = new GroupOfCarWindowDialog(parent, group);
    }
    public static void main(String[] args)
    {
        //GroupOfCarWindowDialog window = new GroupOfCarWindowDialog();
    }
}
