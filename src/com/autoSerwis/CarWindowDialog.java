package com.autoSerwis;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/*
 * Program: Pomocnicze okno edycji obiektów klasy Car dla
 * aplikacji okienkowej.
 *
 * Plik: CarWindowDialog.java
 *
 * Autor: Elżbieta Czerniak
 * Data: październik 2018r.
 */

public class CarWindowDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private Car car;

    //------------- etykiety -----------------------------------
    JLabel manufacturerLabel = new JLabel("Manufacturer: ");
    JLabel modelLabel = new JLabel("Model: ");
    JLabel dateLabel = new JLabel("Reg. date: ");
    JLabel mileageLabel = new JLabel("Mileage: ");
    JLabel fuelLabel = new JLabel("Fuel: ");

    //---------------- pola tekstowe ----------------------------
    JTextField manufacturerText = new JTextField();
    JTextField modelText = new JTextField();
    JTextField dateText = new JTextField();
    JTextField mileageText = new JTextField();
    JComboBox fuelText = new JComboBox();
    String fuelName;
    //JTextField fuelText = new JTextField();

    //---------------- przyciski --------------------------------
    JButton OKButton = new JButton("OK");
    JButton CancelButton = new JButton("Cancel");

    private CarWindowDialog(Window parent, Car car)
    {
        super(parent, Dialog.ModalityType.DOCUMENT_MODAL);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 300);
        setLocationRelativeTo(parent);

        // ustawienie listy wyboru
        fuelText.addItem(Fuel.Pb.toString());
        fuelText.addItem(Fuel.ON.toString());
        fuelText.addItem(Fuel.LPG.toString());

        this.car = car;

        if(car == null)
        {
            setTitle("New car");
        }
        else
        {
            setTitle("Edit car");
            manufacturerText.setText(car.getManufacturer());
            modelText.setText(car.getModel());
            dateText.setText(car.getRegDate().toString());
            mileageText.setText(Integer.toString(car.getMileage()));
            //fuelText.setText(car.getFuel().toString());
        }

        //******* dodanie actionListenera przyciskom ***********
        OKButton.addActionListener(this);
        CancelButton.addActionListener(this);

        fuelText.addActionListener(this);

        //********** dodanie panelu ******************************
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0x393939));

        Color buttonColor = new Color(0x000000);
        Color textButtonColor = new Color(0xFF5A02);
        Color textLabelColor = new Color(0xFF5A02);
        Color backgroundLabelColor = new Color(0x2A2A2A);
        Color textFileColor = new Color(0xFDC64F);

        //********** wygląd etykiet ******************************
        int xLabel = 30;
        int yLabel = 30;
        int wLabel = 150;
        int hLabel = 20;
        int ySpaceLabel = 15;
        int xSpaceLabel = 30;

        manufacturerLabel.setBorder(new LineBorder(Color.BLACK));
        modelLabel.setBorder(new LineBorder(Color.BLACK));
        dateLabel.setBorder(new LineBorder(Color.BLACK));
        mileageLabel.setBorder(new LineBorder(Color.BLACK));
        fuelLabel.setBorder(new LineBorder(Color.BLACK));

        manufacturerLabel.setForeground(textLabelColor);
        modelLabel.setForeground(textLabelColor);
        dateLabel.setForeground(textLabelColor);
        mileageLabel.setForeground(textLabelColor);
        fuelLabel.setForeground(textLabelColor);

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

        manufacturerLabel.setBounds(xLabel, yLabel, wLabel, hLabel);
        modelLabel.setBounds(xLabel, manufacturerLabel.getY() + hLabel + ySpaceLabel, wLabel, hLabel);
        dateLabel.setBounds(xLabel, modelLabel.getY() + hLabel + ySpaceLabel, wLabel, hLabel);
        mileageLabel.setBounds(xLabel, dateLabel.getY() + hLabel + ySpaceLabel, wLabel, hLabel);
        fuelLabel.setBounds(xLabel, mileageLabel.getY() + hLabel + ySpaceLabel, wLabel, hLabel);


        //********** wygląd pól tekstowych ************************
        int hText = 20;
        int wText = 260;

        manufacturerText.setBorder(new LineBorder(Color.BLACK));
        modelText.setBorder(new LineBorder(Color.BLACK));
        dateText.setBorder(new LineBorder(Color.BLACK));
        mileageText.setBorder(new LineBorder(Color.BLACK));
        fuelText.setBorder(new LineBorder(Color.BLACK));

        manufacturerText.setBackground(textFileColor);
        modelText.setBackground(textFileColor);
        dateText.setBackground(textFileColor);
        mileageText.setBackground(textFileColor);
        fuelText.setBackground(textFileColor);

        int xTextFile = xLabel + wLabel + xSpaceLabel;
        manufacturerText.setBounds(xTextFile, manufacturerLabel.getY(), wText, hText);
        modelText.setBounds(xTextFile, modelLabel.getY(), wText, hText);
        dateText.setBounds(xTextFile, dateLabel.getY(), wText, hText);
        mileageText.setBounds(xTextFile, mileageLabel.getY(), wText, hText);
        fuelText.setBounds(xTextFile, fuelLabel.getY(), wText, hText);

        //************* wygląd przycisków ***************************
        OKButton.setBackground(buttonColor);
        CancelButton.setBackground(buttonColor);

        OKButton.setForeground(textButtonColor);
        CancelButton.setForeground(textButtonColor);

        int buttonW = 120;
        int buttonH = 30;
        int buttonX = 100;
        int buttonY = fuelLabel.getY() + hLabel + 20;

        OKButton.setBounds(buttonX, buttonY, buttonW, buttonH);
        CancelButton.setBounds(buttonX + buttonW + 60, buttonY, buttonW, buttonH);

        //************ dodawanie na panel ***************************
        panel.add(manufacturerLabel);
        panel.add(manufacturerText);

        panel.add(modelLabel);
        panel.add(modelText);

        panel.add(dateLabel);
        panel.add(dateText);

        panel.add(mileageLabel);
        panel.add(mileageText);

        panel.add(fuelLabel);
        panel.add(fuelText);

        panel.add(OKButton);
        panel.add(CancelButton);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object eventSource = e.getSource();

        if(eventSource == OKButton)
        {
            try
            {
                if(car == null)
                {
                    car = new Car();
                }
                car.setManufacturer(manufacturerText.getText());
                car.setModel(modelText.getText());
                car.setRegDate(LocalDate.parse(dateText.getText()));
                car.setMileage(Integer.parseInt(mileageText.getText()));
                if(fuelName != null)
                {
                    car.setFuel(Fuel.valueOf(fuelName));
                }

                dispose();

            }
            catch (CarException ex)
            {
               JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(eventSource == CancelButton)
        {
            dispose();
        }

        if(eventSource == fuelText)
        {
            fuelName = (String)(((JComboBox) e.getSource()).getSelectedItem());
        }
    }

    public static Car createNewCar(Window parent)
    {
        CarWindowDialog dialog = new CarWindowDialog(parent, null);
        return dialog.car;
    }

    public static void changeCarData(Window parent, Car car)
    {
        new CarWindowDialog(parent, car);
    }
}
