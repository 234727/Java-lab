package com.autoSerwis;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Vector;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;

/*
 *  Program: Operacje na obiektach klasy GroupOfCar
 *     Plik: GroupType.java
 *           definicja typu wyliczeniowego GroupType
 *
 *    Autor: Elżbieta Czerniak
 *     Data:  listopad 2018 r.
 */

/*
 * Typ wyliczeniowy GroupType reprezentuje rodzaj kolekcji, w jakiej mogą być przechowywane obiekty typu Car w klasie
 * GroupOfCar
 */

public enum GroupType
{

    VECTOR("List (class Vector)"),
    ARRAY_LIST("List (class arrayList)"),
    LINKED_LIST("List (class linkedList)"),
    TREE_SET("Set (class treeSet)"),
    HASH_SET("Set (hashSet)");

    String groupType;

    private GroupType(String type)
    {
        this.groupType = type;
    }

    @Override
    public String toString() {
        return groupType;
    }

    public static GroupType find(String type) {
        for (GroupType t : values())
        {
            if(t.groupType.equals(type))
            {
                return t;
            }
        }
        return null;
    }
    public Collection<Car> createColection() throws CarException
    {
        switch (this)
        {
            case VECTOR:        return new Vector<Car>();
            case ARRAY_LIST:    return new ArrayList<Car>();
            case LINKED_LIST:   return new LinkedList<Car>();
            case HASH_SET:      return new HashSet<Car>();
            case TREE_SET:      return new TreeSet<Car>();
            default:            throw new CarException("This type isn't implemented");
        }
    }

}
