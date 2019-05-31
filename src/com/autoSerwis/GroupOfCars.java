package com.autoSerwis;

import java.io.*;
import java.util.*;

/*
 *  Program: Operacje na obiektach klasy GroupOfCar
 *     Plik: GroupOfCar.java
 *           definicja publicznej klasy GroupOfCar
 *
 *    Autor: Elżbieta Czerniak
 *     Data:  listopad 2018 r.
 */

/*
 * Klasa GroupOfCar reprezentuje grupy samochody, które mogą być opisane
 * za pomocą 5 typów kolekcji: arraryList, LinkedList, TreeSet, HashSet, Vector
 */

public class GroupOfCars implements Iterable<Car>, Serializable
{
    private static final long serialVersionUID = 1L;

    private String groupName;
    private GroupType type;
    private Collection<Car> collection;

    //********************* constructors ***********************************
    public GroupOfCars()
    {
        groupName = null;
        type = null;
    }
    public GroupOfCars(String groupName, GroupType type) throws CarException {

        setGroupName(groupName);
        if (type == null) {
            throw new CarException("Incorrect collection's type");
        }
        this.type = type;
        collection = this.type.createColection();
    }

    public GroupOfCars (String groupName, String groupType) throws CarException
    {
        setGroupName(groupName);

        GroupType t = GroupType.find(groupType);
        if(t == null)
        {
            throw new CarException("Incorrect collection's type");
        }
        this.type = t;
        collection = this.type.createColection();
    }

    //***************** getters ********************************
   public String getGroupName()
    {
        return groupName;
    }
    Collection<Car> getCollection()
    {
        return collection;
    }

    public GroupType getType()
    {
        return type;
    }

    //****************** setters ***********************************
    public void setGroupName(String name) throws CarException
    {
        if(name.equals("") || name == null)
        {
            throw new CarException("Group's name can't be empty.");
        }
        this.groupName = name;
    }

    public void setGroupType(GroupType type) throws CarException
    {
        if(type == null)
        {
            throw new CarException("Collection's type have to be specified");
        }

        if(this.type == type)
        {
            return;
        }

        Collection<Car> oldCollection = collection;
        collection = type.createColection();
        this.type = type;
        for(Car c : oldCollection)
        {
            collection.add(c);
        }
    }

    public void setGroupType(String type) throws CarException
    {
        for(GroupType t : GroupType.values())
        {
            if(t.toString().equals(type))
            {
                setGroupType(t);
                return;
            }
        }

        throw new CarException("This collection's type wasn't found");
    }

    //****************************************************************
    public boolean add(Car newCar)
    {
        return collection.add(newCar);
    }

    public Iterator<Car> iterator()
    {
        return collection.iterator();
    }

    public int size()
    {
        return collection.size();
    }

    //sortowanie
    public void sortManufacturer() throws CarException
    {
        if(type == GroupType.HASH_SET || type == GroupType.TREE_SET)
        {
            throw new CarException("Collections SET type can't be sort.");
        }

        Collections.sort((List<Car>)collection);
    }

    public void sortModel() throws CarException
    {
        if(type == GroupType.HASH_SET || type == GroupType.TREE_SET)
        {
            throw new CarException("Collections SET type can't be sort.");
        }

        Collections.sort((List<Car>) collection, new Comparator<Car>() {

            @Override
            public int compare(Car c1, Car c2)
            {
                return c1.getModel().compareTo(c2.getModel());
            }
        });
    }

    public void sortRegDate() throws CarException
    {
        if(type == GroupType.HASH_SET || type == GroupType.TREE_SET)
        {
            throw new CarException("Collections SET type can't be sort.");
        }

        Collections.sort((List<Car>)collection, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return c1.getRegDate().compareTo(c2.getRegDate());
            }
        });
    }

    public void sortMileage() throws CarException
    {
        if(type == GroupType.HASH_SET || type == GroupType.TREE_SET)
        {
            throw new CarException("Collections SET type can't be sort.");
        }

        Collections.sort((List<Car>) collection, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getMileage() - o2.getMileage();
            }
        });
    }

    public void sortFuelType() throws CarException
    {
        if(type == GroupType.HASH_SET || type == GroupType.TREE_SET)
        {
            throw new CarException("Collections SET type can't be sort.");
        }

        Collections.sort((List<Car>) collection, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getFuel().compareTo(o2.getFuel());
            }
        });
    }

    @Override
    public String toString()
    {
        return groupName + " [" + type + "]";
    }

    // metoda zapisuje do pliku txt dane grupy: nazwę, typ kolekcji, ilość elementów, poszczególne elementy
    public static void writeToFile(BufferedWriter writer, GroupOfCars carsGroup) throws IOException, CarException
    {
        writer.write(carsGroup.getGroupName());
        writer.newLine();
        writer.write(carsGroup.getType().toString());
        writer.newLine();
        writer.write(Integer.toString(carsGroup.getCollection().size()));
        writer.newLine();
        for(Car car : carsGroup.collection)
        {
            Car.writeToFile(writer, car);
        }
    }

    public static void writeToFile(String fileName, GroupOfCars group)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            writeToFile(writer, group);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CarException e) {
            e.printStackTrace();
        }
    }
    // metoda wczytuje kolekcję: jej nazwę, typ i elementy
    public static GroupOfCars readFromFile(BufferedReader reader) throws CarException
    {
        try
        {
            String gName = reader.readLine();
            String tName = reader.readLine();
            String size = reader.readLine();
            GroupOfCars newGroup = new GroupOfCars(gName, tName);

            Car car;
            Integer i = Integer.parseInt(size);
            while(i != 0 )
            {
                newGroup.add(Car.readFromFile(reader));
                --i;
            }
            return newGroup;
        } catch (IOException | CarException e)
        {
            e.printStackTrace();
        }
        // albo zwraca newGroup z try, albo przerywa dzialanie z catch
        // ale kompilator nażeka, że brakuje mu return
        return null;
    }

    public static GroupOfCars readFromFile(String fileName) throws CarException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            return GroupOfCars.readFromFile(reader);
        }
        catch (IOException | CarException e)
        {
            e.printStackTrace();
        }
        // albo zwraca newGroup z try, albo przerywa dzialanie z catch
        // ale kompilator nażeka, że brakuje mu return
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupOfCars)) return false;
        GroupOfCars that = (GroupOfCars) o;
        return Objects.equals(groupName, that.groupName) &&
                type == that.type &&
                Objects.equals(collection, that.collection);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupName, type, collection);
    }
}
