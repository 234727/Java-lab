package com.autoSerwis;

/*
 *  Program: Operacje na obiektach klasy Car
 *     Plik: CarException.java
 *           definicja klasy CarException
 *
 *    Autor: Elżbieta Czerniak
 *     Data:  pazdziernik 2018 r.
 */

/*
 * Klasa CarException jest klasą wyjątków dedykowanych do
 * zgłaszania błędów przy operacjach na obiektach klasy Car.
 */

public class CarException extends Exception
{
    private static final long serialVersionUID = 1L;

    public CarException(String message)
    {
        super(message);
    }
}
