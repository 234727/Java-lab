package com.autoSerwis;

/*
 *  Program: Aplikacja konsolowa do testowania operacji na obiektach
 *          klasy Car
 *     Plik: CarConsoleApp.java
 *
 *    Autor: Elżbieta Czerniak
 *     Data:  pazdziernik 2018 r.
 */

import java.time.LocalDate;

public class CarConsoleApp
{
    private static final String GREETING =
            "Program Car - wersja konsolowa\n" +
                    "Autor: Elżbieta Czerniak\n" +
                    "Data: październik 2018 r.\n";

    private static final String MENU =
            "||---------------- MENU ------------------------||\n" +
                    "||oooooooooooooooooooooooooooooooooooooooooooooo||\n" +
                    "|| 1 - Enter a new car's data                   ||\n" +
                    "|| 2 - Delete car's data                        ||\n" +
                    "|| 3 - Edit car's data                          ||\n" +
                    "|| 4 - Load data from file                      ||\n" +
                    "|| 5 - Export data to file                      ||\n" +
                    "|| 6 - Exit                                     ||\n" +
                    "||oooooooooooooooooooooooooooooooooooooooooooooo|| \n";

    private static final String CHANGE_MENU =
            "||------- WHAT DO YOU WANT TO CHANGE? ----------||\n" +
                    "||oooooooooooooooooooooooooooooooooooooooooooooo||\n" +
                    "|| 1 - Manufacturer                             ||\n" +
                    "|| 2 - Model                                    ||\n" +
                    "|| 3 - Mileage                                  ||\n" +
                    "|| 4 - RegDate                                  ||\n" +
                    "|| 5 - Fuel Type                                ||\n" +
                    "|| 6 - Return                                   ||\n" +
                    "||oooooooooooooooooooooooooooooooooooooooooooooo|| \n";

    private static ConsoleUserDialog UI = new ConsoleUserDialog();
    private Car newCar = null;

    public void manager()
    {
        UI.printMessage(GREETING);

        while(true)
        {
            UI.clearConsole();
            showCurrentCar();

            try
            {
                switch (UI.enterInt(MENU + "=>"))
                {
                    case 1:
                        newCar = createCar();
                        break;
                    case 2:
                        newCar = null;
                        UI.printInfoMessage("Data of this car was deleted.");
                        break;
                    case 3:
                        if(newCar == null)
                        {
                            throw  new CarException("This car wasn't created.");
                        }
                        changeCarData(newCar);
                        break;
                    case 4:
                        String fileName = UI.enterString("Enter a file name: ");
                        newCar = Car.readFromFile(fileName);
                        UI.printInfoMessage("Data of this car was loaded from file: " + fileName);
                        break;
                    case 5:
                        String fileName2 = UI.enterString("Enter a file name: ");
                        Car.writeToFile(fileName2, newCar);
                        UI.printInfoMessage("Data of this car was loaded to file: " + fileName2);
                        break;
                    case 6:
                        UI.printInfoMessage("The program has ended.");
                        System.exit(0);

                }
            } catch (CarException c) {
                UI.printErrorMessage(c.getMessage());
            }
        }
    }

    public void showCurrentCar()
    {
        showCar(newCar);
    }

    public void showCar(Car car)
    {
        StringBuilder data = new StringBuilder();

        if(car != null)
        {
            data.append("Current car: \n");
            data.append("Manufacturer: " + car.getManufacturer() + "\n");
            data.append("Model: " + car.getModel() + " \n");
            data.append("Mileage: " + Integer.toString(car.getMileage()) + " \n");
            data.append("RegDate: " + car.getRegDate() + " \n");
            data.append("Fuel: " + car.getFuel() + " \n");
        }
        else
        {
            data.append("This car has not any data");
        }
        UI.printMessage(data.toString());
    }

    public Car createCar()
    {
        String manufacturer = UI.enterString("Enter a car's manufacturer: ");
        String model = UI.enterString("Enter a car's model: ");
        int mileage = UI.enterInt("Enter a car's mileage: ");
        LocalDate regDate = LocalDate.of(UI.enterInt("Enter a car's regDate: \nYear : "), UI.enterInt("Month: "), UI.enterInt("Day: "));
        Fuel fuel = Fuel.valueOf(UI.enterString("Enter a type of fuel (ON, DIESEL, LPG): "));

        Car car;

        try
        {
            car = new Car();
            car.setManufacturer(manufacturer);
            car.setModel(model);
            car.setMileage(mileage);
            car.setRegDate(regDate);
            car.setFuel(fuel);
        } catch (CarException c)
        {
            UI.printErrorMessage(c.getMessage());
            return null;
        }
        return car;
    }

    public void changeCarData(Car car)
    {
        UI.clearConsole();
        showCurrentCar();

        try
        {
            switch (UI.enterInt(CHANGE_MENU + "=>"))
            {
                case 1:
                    car.setManufacturer(UI.enterString("Enter a car's manufacturer: "));
                    break;
                case 2:
                    car.setModel(UI.enterString("Enter a car's model: "));
                    break;
                case 3:
                    car.setMileage(UI.enterInt("Enter a car's mileage: "));
                    break;
                case 4:
                    car.setRegDate(LocalDate.of(UI.enterInt("Enter a car's regDate: \nYear : "), UI.enterInt("Month: "), UI.enterInt("Day: ")));
                    break;
                case 5:
                    car.setFuel(Fuel.valueOf(UI.enterString("Enter a car's type of fuel (ON, DIESEL, LPG): ")));
                    break;
                case 6:
                    return;
            }
        } catch (CarException c) {
            UI.printErrorMessage(c.getMessage());
        }
    }

    public static void main(String[] args)
    {
        CarConsoleApp app = new CarConsoleApp();
        app.manager();
    }
}


