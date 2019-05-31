package com.autoSerwis;

import com.sun.java.swing.plaf.windows.WindowsBorders;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Program: Aplikacja okienkowa z GUI, umożliwiająca testowanie
 * operacji wykonywanych na obiektach klasy Car.
 *
 * Plik: CarWindowsApp.java
 *
 * Autor: Elżbieta Czerniak
 * Data: październik 2018r.
 */

public class CarWindowsApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final String GREETING_MESSAGE = "Program Car - wersja okienkowa\n" +
            "Autor: Elżbieta Czerniak\n" + "Data: październik 2018r.\n";

    private Car sampleCar;

    //elementy okna
    //-------------------- panels -----------------------------------------
    JPanel containerPanel;
    JPanel backgroundPanel;
    JPanel backgroundButtonPanel;
    JPanel backgroundTextPanel;

    //--------------------- buttons ---------------------------------------
    JButton newButton = new JButton("New car");
    JButton editButton = new JButton("Edit car");
    JButton saveButton = new JButton("Save to file");
    JButton loadButton = new JButton("Load from file");
    JButton saveBinaryButton = new JButton("Save to binary file");
    JButton loadBinaryButton = new JButton("Load from binary file");
    JButton deleteButton = new JButton("Delete car");
    JButton infoButton = new JButton("About");
    JButton exitButton = new JButton("Exit");

    //---------------------- labels ------------------------------------------
    JLabel background;

    JLabel manufacturerLabel = new JLabel("Manufacturer: ");
    JLabel modelLabel = new JLabel("Model: ");
    JLabel dateLabel = new JLabel("Reg. date: ");
    JLabel mileageLabel = new JLabel("Mileage: ");
    JLabel fuelLabel = new JLabel("Fuel: ");

    //-------------------- text files -----------------------------------------
    JTextField manufacturerText = new JTextField();
    JTextField modelText = new JTextField();
    JTextField dateText = new JTextField();
    JTextField mileageText = new JTextField();
    JTextField fuelText = new JTextField();

    //---------------------- menu ---------------------------------------------
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Options");

    JMenuItem newItem = new JMenuItem("New");
    JMenuItem editItem = new JMenuItem("Edit");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenuItem loadItem = new JMenuItem("Load");
    JMenuItem saveBinaryItem = new JMenuItem("Save binary");
    JMenuItem loadBinaryItem = new JMenuItem("Load binary");
    JMenuItem deleteItem = new JMenuItem("Delete");
    JMenuItem aboutItem = new JMenuItem("About");
    JMenuItem exitItem = new JMenuItem("Exit");

    //********************** konstruktor ****************************
    public CarWindowsApp()
    {
        //--------------- Window's settings -------------------------
        setSize(800, 470);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Car Service");

        //--------- PANELS -----------------------------------------
        containerPanel = new JPanel();
        containerPanel.setLayout(null);//(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 800, 200);

        backgroundButtonPanel = new JPanel();
        backgroundButtonPanel.setLayout(null);
        backgroundButtonPanel.setBackground(new Color(0x2A2A2A));
        backgroundButtonPanel.setBounds(0, 200, 800, 90);

        backgroundTextPanel = new JPanel();
        backgroundTextPanel.setLayout(null);
        backgroundTextPanel.setBackground(new Color(0x393939));
        backgroundTextPanel.setBounds(0, 280, 800, 140);

        //----------- BUTTONS --------------------------------------
        int buttonW = 120;
        int buttonH = 30;
        int buttonX = 40;
        int buttonY = 10;
        int buttonXSpace = 30;
        int buttonYSpace = 10;

        Color buttonColor = new Color(0x000000);
        Color textButtonColor = new Color(0xFF5A02);
        Color textLabelColor = new Color(0xFF5A02);
        Color textFileColor = new Color(0xFDC64F);
        Color backgroundLabelColor = new Color(0x2A2A2A);
        Color backgroundMenuColor = new Color(0x2A2A2A);

        //********* set buttons's color *****************************
        newButton.setBackground(buttonColor);
        editButton.setBackground(buttonColor);
        saveButton.setBackground(buttonColor);
        loadButton.setBackground(buttonColor);
        saveBinaryButton.setBackground(buttonColor);
        loadBinaryButton.setBackground(buttonColor);
        deleteButton.setBackground(buttonColor);
        infoButton.setBackground(buttonColor);
        exitButton.setBackground(buttonColor);

        //********* set buttons's text color **********************
        newButton.setForeground(textButtonColor);
        editButton.setForeground(textButtonColor);
        saveButton.setForeground(textButtonColor);
        loadButton.setForeground(textButtonColor);
        saveBinaryButton.setForeground(textButtonColor);
        loadBinaryButton.setForeground(textButtonColor);
        deleteButton.setForeground(textButtonColor);
        infoButton.setForeground(textButtonColor);
        exitButton.setForeground(textButtonColor);

        //************ set buttons's positions and sizes ************
        newButton.setBounds(buttonX, buttonY, buttonW, buttonH);
        editButton.setBounds(newButton.getX() + buttonW + buttonXSpace, buttonY, buttonW, buttonH);
        saveButton.setBounds(editButton.getX() + buttonW + buttonXSpace, buttonY, buttonW, buttonH);
        loadButton.setBounds(saveButton.getX() + buttonW + buttonXSpace, buttonY, buttonW, buttonH);
        saveBinaryButton.setBounds(loadButton.getX() + buttonW + buttonXSpace, buttonY, buttonW, buttonH);
        loadBinaryButton.setBounds(buttonX + 70, buttonY + buttonH + buttonYSpace, buttonW, buttonH);
        deleteButton.setBounds(loadBinaryButton.getX() + buttonW + buttonXSpace, buttonY + buttonH + buttonYSpace, buttonW, buttonH);
        infoButton.setBounds(deleteButton.getX() + buttonW + buttonXSpace, buttonY + buttonH + buttonYSpace, buttonW, buttonH);
        exitButton.setBounds(infoButton.getX() + buttonW + buttonXSpace, buttonY + buttonH + buttonYSpace, buttonW, buttonH);

        //----- adding actionListener ---------------------------
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        saveBinaryButton.addActionListener(this);
        loadBinaryButton.addActionListener(this);
        deleteButton.addActionListener(this);
        infoButton.addActionListener(this);
        exitButton.addActionListener(this);

        //----------- LABELS ---------------------------------------
        background = new JLabel(new ImageIcon("picture\\background.png"));
        background.setBounds(0, 0, 800, 200);

        int xLabel = 150;
        int yLabel = 25;
        int wLabel = 150;
        int hLabel = 15;
        int labelYSpace = 5;

        // ******************* size and location ****************
        manufacturerLabel.setBounds(xLabel, yLabel, wLabel, hLabel);
        modelLabel.setBounds(xLabel, manufacturerLabel.getY() + hLabel + labelYSpace, wLabel, hLabel);
        dateLabel.setBounds(xLabel, modelLabel.getY() + hLabel + labelYSpace, wLabel, hLabel);
        mileageLabel.setBounds(xLabel, dateLabel.getY() + hLabel + labelYSpace, wLabel, hLabel);
        fuelLabel.setBounds(xLabel, mileageLabel.getY() + hLabel + labelYSpace, wLabel, hLabel);

        //**************** text color ***************************
        manufacturerLabel.setForeground(textLabelColor);
        modelLabel.setForeground(textLabelColor);
        dateLabel.setForeground(textLabelColor);
        mileageLabel.setForeground(textLabelColor);
        fuelLabel.setForeground(textLabelColor);

        //********** border **********************************
        manufacturerLabel.setBorder(new LineBorder(Color.BLACK));
        modelLabel.setBorder(new LineBorder(Color.BLACK));
        dateLabel.setBorder(new LineBorder(Color.BLACK));
        mileageLabel.setBorder(new LineBorder(Color.BLACK));
        fuelLabel.setBorder(new LineBorder(Color.BLACK));

        //************** labels' background  ******************************
        manufacturerLabel.setOpaque(true);
        modelLabel.setOpaque(true);
        dateLabel.setOpaque(true);
        mileageLabel.setOpaque(true);
        fuelLabel.setOpaque(true);

        manufacturerLabel.setBackground(backgroundLabelColor);
        modelLabel.setBackground(backgroundLabelColor);
        dateLabel.setBackground(backgroundLabelColor);
        mileageLabel.setBackground(backgroundLabelColor);
        fuelLabel.setBackground(backgroundLabelColor);

        //---------------- text files --------------------------
        //***************** colors *********************************
        manufacturerText.setBackground(textFileColor);
        modelText.setBackground(textFileColor);
        dateText.setBackground(textFileColor);
        mileageText.setBackground(textFileColor);
        fuelText.setBackground(textFileColor);

        //***************** zablokokwanie pól *********************
        manufacturerText.setEditable(false);
        modelText.setEditable(false);
        dateText.setEditable(false);
        mileageText.setEditable(false);
        fuelText.setEditable(false);

        //****************** location ****************************
        int hText = 15;
        int wText = 340;
        int xTextSpace = 20;

        manufacturerText.setBounds(xLabel + wLabel + xTextSpace, yLabel, wText, hText);
        modelText.setBounds(xLabel + wLabel + xTextSpace, modelLabel.getY(), wText, hText);
        dateText.setBounds(xLabel + wLabel + xTextSpace, dateLabel.getY(), wText, hText);
        mileageText.setBounds(xLabel + wLabel + xTextSpace, mileageLabel.getY(), wText, hText);
        fuelText.setBounds(xLabel + wLabel + xTextSpace, fuelLabel.getY(), wText, hText);

        //******************* border **************************
        manufacturerText.setBorder(new LineBorder(Color.BLACK));
        modelText.setBorder(new LineBorder(Color.BLACK));
        dateText.setBorder(new LineBorder(Color.BLACK));
        mileageText.setBorder(new LineBorder(Color.BLACK));
        fuelText.setBorder(new LineBorder(Color.BLACK));

        //****************** MENU ***********************************
        menuBar.add(menu);
        menu.add(newItem);
        menu.add(editItem);
        menu.add(saveItem);
        menu.add(loadItem);
        menu.add(saveBinaryItem);
        menu.add(loadBinaryItem);
        menu.add(deleteItem);
        menu.add(aboutItem);
        menu.add(exitItem);

        menuBar.setBackground(new Color(0x000000));
        menu.setForeground(textButtonColor);
        menuBar.setBorder(new LineBorder(Color.black));

        //------------- kolor tła dla itemów w menu --------------
        newItem.setBackground(backgroundMenuColor);
        editItem.setBackground(backgroundMenuColor);
        saveItem.setBackground(backgroundMenuColor);
        loadItem.setBackground(backgroundMenuColor);
        saveBinaryItem.setBackground(backgroundMenuColor);
        loadBinaryItem.setBackground(backgroundMenuColor);
        deleteItem.setBackground(backgroundMenuColor);
        aboutItem.setBackground(backgroundMenuColor);
        exitItem.setBackground(backgroundMenuColor);

        //-------------- kolor czconki dla itemów z menu -----------
        newItem.setForeground(textFileColor);
        editItem.setForeground(textFileColor);
        saveItem.setForeground(textFileColor);
        loadItem.setForeground(textFileColor);
        saveBinaryItem.setForeground(textFileColor);
        loadBinaryItem.setForeground(textFileColor);
        deleteItem.setForeground(textFileColor);
        aboutItem.setForeground(textFileColor);
        exitItem.setForeground(textFileColor);

        //------------- obramowanie itemów - zamiast separatora ------
        newItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        editItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        saveItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        loadItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        saveBinaryItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        loadBinaryItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        deleteItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        aboutItem.setBorder(new WindowsBorders.DashedBorder(Color.black));
        exitItem.setBorder(new WindowsBorders.DashedBorder(Color.black));

        //---------------- dodanie ActionListenera ----------------------
        newItem.addActionListener(this);
        editItem.addActionListener(this);
        saveItem.addActionListener(this);
        loadItem.addActionListener(this);
        saveBinaryItem.addActionListener(this);
        loadBinaryItem.addActionListener(this);
        deleteItem.addActionListener(this);
        aboutItem.addActionListener(this);
        exitItem.addActionListener(this);

        //******** dodawanie do panelu ****************************
        containerPanel.add(backgroundPanel);
        containerPanel.add(backgroundButtonPanel);
        containerPanel.add(backgroundTextPanel);

        backgroundPanel.add(background);
        backgroundButtonPanel.add(newButton);
        backgroundButtonPanel.add(editButton);
        backgroundButtonPanel.add(saveButton);
        backgroundButtonPanel.add(loadButton);
        backgroundButtonPanel.add(saveBinaryButton);
        backgroundButtonPanel.add(loadBinaryButton);
        backgroundButtonPanel.add(deleteButton);
        backgroundButtonPanel.add(infoButton);
        backgroundButtonPanel.add(exitButton);

        backgroundTextPanel.add(manufacturerLabel);
        backgroundTextPanel.add(modelLabel);
        backgroundTextPanel.add(dateLabel);
        backgroundTextPanel.add(mileageLabel);
        backgroundTextPanel.add(fuelLabel);

        backgroundTextPanel.add(manufacturerText);
        backgroundTextPanel.add(modelText);
        backgroundTextPanel.add(dateText);
        backgroundTextPanel.add(mileageText);
        backgroundTextPanel.add(fuelText);

        setContentPane(containerPanel);
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public void showCar()
    {
        if(sampleCar == null)
        {
            manufacturerText.setText("");
            modelText.setText("");
            dateText.setText("");
            mileageText.setText("");
            fuelText.setText("");
        }
        else
        {
            manufacturerText.setText(sampleCar.getManufacturer());
            modelText.setText(sampleCar.getModel());
            dateText.setText(sampleCar.getRegDate().toString());
            mileageText.setText(Integer.toString(sampleCar.getMileage()));
            fuelText.setText(sampleCar.getFuel().toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object eventSource = e.getSource();

        try
        {
            if(eventSource == newButton || eventSource == newItem)
            {
                sampleCar = CarWindowDialog.createNewCar(this);
            }
            if(eventSource == editButton || eventSource == editItem)
            {
                if(sampleCar == null)
                {
                    throw new CarException("Car was not created");
                }
                CarWindowDialog.changeCarData(this, sampleCar);
            }
            if(eventSource == saveButton || eventSource == saveItem)
            {
                String fileName = JOptionPane.showInputDialog("Enter a file name");
                if(fileName == null || fileName.equals("")) return;

                Car.writeToFile(fileName, sampleCar);
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
                //String fileName = JOptionPane.showInputDialog("Enter a file name");
                if(fileName == null || fileName.equals("")) return;

                sampleCar = Car.readFromFile(fileName);
            }
            if(eventSource == saveBinaryButton || eventSource == saveBinaryItem)
            {
                String fileName = JOptionPane.showInputDialog("Enter a file name");
                if(fileName == null || fileName.equals("")) return;

                Car.writeToBinaryFile(fileName, sampleCar);
            }
            if(eventSource == loadBinaryButton || eventSource == loadBinaryItem)
            {
                String fileName = "";
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "BIN", "bin");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(this);

                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    fileName = chooser.getSelectedFile().getName();
                }
                //String fileName = JOptionPane.showInputDialog("Enter a file name");
                if(fileName == null || fileName.equals("")) return;

                sampleCar = Car.readFromBinaryFile(fileName);
            }

            if(eventSource == deleteButton || eventSource == deleteItem)
            {
                if(sampleCar != null)
                {
                    sampleCar = null;
                }
            }

            if(eventSource == infoButton || eventSource == aboutItem)
            {
                JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
            }

            if(eventSource == exitButton || eventSource == exitItem)
            {
                System.exit(0);
            }

        }
        catch(CarException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        showCar();
    }

    public static void main(String[] args){
        CarWindowsApp app = new CarWindowsApp();
    }
}
