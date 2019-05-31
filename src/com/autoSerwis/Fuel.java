package com.autoSerwis;

/*
 *  Program: Operacje na obiektach klasy Car
 *     Plik: Fuel.java
 *           definicja typu wyliczeniowego Fuel
 *
 *    Autor: Elżbieta Czerniak
 *     Data:  pazdziernik 2018 r.
 */

/*
 * Typ wyliczeniowy Fuel reprezentuje rodzaj poaliwa, na jakie auto jeździ
 * Typ LPG jest wymieniony jako odrębny typ, jednak jest logiczne,
 * że w tym przypadku auto może jeździć również na Pb.
 */

public enum Fuel
{
    UNKNOWN("----"),
    Pb("Pb"),
    ON("ON"),
    LPG("LPG");

    String fuelType;

    private Fuel(String fuelType)
    {
        this.fuelType = fuelType;
    }

    @Override
    public String toString()
    {
        return fuelType;
    }
}