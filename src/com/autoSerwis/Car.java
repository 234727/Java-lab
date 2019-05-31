package com.autoSerwis;

import java.io.*;
import java.time.Month;
import java.time.LocalDate;
import java.util.Objects;

/*
 *  Program: Operacje na obiektach klasy Car
 *     Plik: Car.java
 *           definicja publicznej klasy Car
 *
 *    Autor: Elżbieta Czerniak
 *     Data:  pazdziernik 2018 r.
 */

/*
 * Klasa Car reprezentuje samochody, które są opisywane
 * za pomocą 5 atrybutów: mileage, manufacturer, model, regDate, fuel
 * W klasie przyjęto ograniczenia:
 *  - pola manufacturer oraz model muszą zawierać ciąg znaków
 *  - pole mileage nie może być ujemne
 *  - pole regDate nie może zawierać daty wcześniejszej niż data
 *  wynalezienia pierwszego samochodu oraz późniejszej niż obecna
 */

public class Car implements Serializable , Comparable<Car>
{
    private static final long serialVersionUID = 1L;

    private int mileage;         //przebieg
    private String manufacturer; // marka
    private String model;        // model
    private LocalDate  regDate;  // data produkcji
    private Fuel fuel;           // typ paliwa
    //-------------- constructors --------------
    public Car(int aMileage, String aManufacturer, String aModel, LocalDate rDate, Fuel aFuel) throws CarException
    {
        this.mileage = aMileage;
        this.manufacturer = aManufacturer;
        this.model = aModel;
        this.regDate = rDate;
        this.fuel = aFuel;
    }

    public Car()
    {
        mileage = 0;
        manufacturer = "";
        model = "";
        regDate = LocalDate.now();
    }
    //-----------------------------------------
    //-------------- getters -------------------
    public int getMileage()
    {
        return mileage;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getModel()
    {
        return model;
    }

    public LocalDate getRegDate()
    {
        return regDate;
    }

    public Fuel getFuel()
    {
        return fuel;
    }
    //-----------------------------------------------
    //-------------- setters ------------------------
    public void setMileage(int aMileage) throws CarException
    {
        if(aMileage < 0)
        {
            throw new CarException("Car's mileage can not be less than 0!");
        }
        this.mileage = aMileage;
    }

    public void setModel(String aModel) throws CarException
    {
        if( aModel.equals("") || aModel == null )
        {
            throw new CarException("Car's model have to be defined!");
        }
        this.model = aModel;
    }

    public void setManufacturer(String aManufacturer) throws CarException
    {
        if(aManufacturer.equals("") || aManufacturer == null)
        {
            throw new CarException("Car's manufacturer have to be defined!");
        }
        this.manufacturer = aManufacturer;
    }

    public void setRegDate(LocalDate rDate) throws CarException
    {
        LocalDate firstCar = LocalDate.of(1886, Month.JANUARY, 29);
        // compareTo zwróci wartość ujemną, jeśli punkt w czasie był przed datą z którą porównujemy
        if( (rDate.compareTo(firstCar) < 0) || (rDate.compareTo(LocalDate.now()) > 0) )
        {
            throw new CarException("Car's reg. date can not be before the first car was invented" +
                    " and can not be after today!");
        }
        this.regDate = rDate;
    }

    public void setFuel(Fuel aFuel)
    {
        this.fuel = aFuel;
    }
    //-----------------------------------------------
    //----------- methods ---------------------------
    @Override
    public String toString()
    {
        return getClass().getName() + ": " + manufacturer + " " + model
                + " " + regDate.toString() + " " + Integer.toString(mileage)
                + " " + fuel.toString();
    }
    public static void writeToFile(BufferedWriter writer, Car car) throws IOException
    {
            writer.write(car.manufacturer);
            writer.newLine();
            writer.write(car.model);
            writer.newLine();
            writer.write(car.regDate.toString());
            writer.newLine();
            writer.write(Integer.toString(car.mileage));
            writer.newLine();
            writer.write(car.fuel.toString());
            writer.newLine();
    }

    public  static void writeToFile(String fileName, Car car) throws CarException
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            writeToFile(writer, car);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static Car readFromFile(BufferedReader reader) throws  IOException, CarException
    {
        Car car = new Car();
        //System.out.println(reader.readLine());
        car.setManufacturer(reader.readLine());
        car.setModel(reader.readLine());
        car.setRegDate(LocalDate.parse(reader.readLine()));
        car.setMileage(Integer.parseInt(reader.readLine()));
        car.setFuel(Fuel.valueOf(reader.readLine()));

        return car;
    }

    public static Car readFromFile(String fileName) throws CarException
    {
        Car car = new Car();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            return readFromFile(reader);
        }
        catch (IOException | CarException e)
        {
            e.printStackTrace();
        }
        return car;
    }

    public static void writeToBinaryFile(String fileName, Car car) throws CarException
    {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            outputStream.writeObject(car);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Car readFromBinaryFile(String fileName) throws CarException
    {
        Car car = new Car();

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            car = (Car) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return  car;
    }
    //************* MAIN ****************************
    // DO TESTÓW
    public static void main(String[] args) {

        /*Car myCar = null;
        String fileName = "car.txt";

        try {
            myCar = new Car(160000, "Ford", "Fusion",
                    LocalDate.of(2005, Month.DECEMBER, 1), Fuel.ON);

        } catch (CarException e) {
            e.printStackTrace();
        }

        try{
            Car.writeToFile(fileName, myCar);
        } catch (CarException e) {
            e.printStackTrace();
        }

        Car newCar = null;
        try{
            newCar = Car.readFromFile(fileName);
            System.out.println(newCar);
        } catch (CarException e) {
            e.printStackTrace();
        }*/
    }

    // ------------------ TYMCZASOWO NIEPOTRZEBNE --------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return mileage == car.mileage &&
                Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(model, car.model) &&
                Objects.equals(regDate, car.regDate) &&
                fuel == car.fuel;
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(mileage, manufacturer, model, regDate, fuel);
    }
    //***********************************************
    @Override
    public int compareTo(Car another)
    {
        return manufacturer.compareTo(another.manufacturer);
    }
}