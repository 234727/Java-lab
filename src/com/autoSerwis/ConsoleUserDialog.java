package com.autoSerwis;


/*
 * Program: Prosta biblioteka metod do realizacji dialogu z u�ytkownikiem
 *          w prostych aplikacjach bez graficznego interfejsu u�ytkownika.
 *    Plik: ConsoleUserDialog.java
 *
 *   Autor: Paweł Rogalinski
 *  Modyfikacja: Elżbieta Czerniak
 *    Data: pazdziernik 2017 r.
 *    Modyfikacja: październik 2018 r.
 *
 */

import java.util.Scanner;

public class ConsoleUserDialog
{
    private final String ERROR_MESSAGE = "Incorrect data. Try again.";

    private Scanner scanner = new Scanner(System.in);

    public void printMessage(String m)
    {
        System.out.println(m);
    }

    public void printInfoMessage(String m)
    {
        System.out.println(m);
        printCommend("Press ENTER");
    }

    public void printErrorMessage(String m)
    {
        System.err.println(m);
        System.err.println("Press ENTER");
        printCommend("");
    }

    public void clearConsole()
    {
        System.out.println("\n\n");
    }

    public String printCommend(String commend)
    {
        System.out.print(commend);
        return scanner.nextLine();
    }

    public String enterString(String s)
    {
        System.out.print(s);
        return scanner.nextLine();
    }

    public char enterChar(String c)
    {
        boolean isError;
        char cr = ' ';

        do {
            isError = false;
            try{
                cr = printCommend(c).charAt(0);
            } catch (IndexOutOfBoundsException e) {
                System.err.println(ERROR_MESSAGE);
                isError = true;
            }
        } while (isError);
        return cr;
    }

    public int enterInt(String c)
    {
        boolean isError;
        int i = 0;
        do {
            isError = false;

            try{
                i = Integer.parseInt(printCommend(c));
            } catch (NumberFormatException e) {
                System.err.println(ERROR_MESSAGE);
            }
        } while (isError);
        return i;
    }

    public float enterFloat (String c)
    {
        boolean isError;
        float d = 0;

        do {
            isError = false;

            try {
                d = Float.parseFloat(printCommend(c));
            } catch(NumberFormatException e){
                System.err.println(ERROR_MESSAGE);
                isError = true;
            }
        } while(isError);
        return d;
    }

    public double enterDouble(String c)
    {
        boolean isError;
        double d = 0;

        do{
            isError = false;

            try{
                d = Double.parseDouble(printCommend(c));
            } catch(NumberFormatException e){
                System.err.println(ERROR_MESSAGE);
                isError = true;
            }
        }while(isError);

        return d;
    }
}
