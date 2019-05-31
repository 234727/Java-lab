package com.autoSerwis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


/*
 *  Program: Operacje na obiektach klasy Person
 *     Plik: Person.java
 *           definicja typu wyliczeniowego Job
 *           definicja klasy PersonException
 *           definicja publicznej klasy Person
 *
 *    Autor: Pawel Rogalinski
 *     Data:  pazdziernik 2017 r.
 */


/*
 *  Typ wyliczeniowy PersonJob reprezentuje przyk?adowe stanowiska,
 *  kt?re mo?e zajmowa? osoba. Klasa zosta?a zaimplementowana
 *  tak, by mog?a by? rozszerzana o dodatkowe stanowiska.
 *  W tym celu wystarczy do zdefiniowanej listy doda? kolejne
 *  wywo?anie konstruktora.
 */





/*
 * Klasa PersonException jest klas? wyj?tk?w dedykowan? do zg?aszania b??d?w
 * wyst?puj?cych przy operacjach na obiektach klasy Person.
 */



/*
 * Klasa Person reprezentuje osoby, kt?re s? opisane za pomoc?
 * czterech atrybutow: imi?, nazwisko, rok urodzenia, stanowisko.
 * W klasie przyj?to ograniczenia:
 *   - pola firstName oraz lastName musz? zawiera? niepusty ci?g znak?w
 *   - pole birthYear musi zawiera? liczb? z przedzia?u [1900-2030]
 *     lub 0 (0 oznacza niezdefiniowany rok urodzenia.
 *   - pole job musi zawiera? wy??cznie jedn? z pozycji zdefiniowanych
 *     w typie wyliczeniowym enum PersonJob.
 *
 * Powy?sze ograniczenia s? kontrolowane i w przypadku pr?by nadania
 * niedozwolonej warto?ci, kt?remu? z atrybut?w jest zg?aszany wyj?tek
 * zawieraj?cy stosowny komunikat.
 */
public class Person {

    private String firstName;
    private String lastName;
    private int birthYear;
    private PersonJob job;


    public Person(String first_name, String last_name) throws PersonException {
        setFirstName(first_name);
        setLastName(last_name);
        job = PersonJob.UNKNOWN;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String first_name) throws PersonException {
        if ((first_name == null) || first_name.equals(""))
            throw new PersonException("Pole <Imi?> musi by? wype?nione.");
        this.firstName = first_name;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String last_name) throws PersonException {
        if ((last_name == null) || last_name.equals(""))
            throw new PersonException("Pole <Nazwisko> musi by? wype?nione.");
        this.lastName = last_name;
    }


    public int getBirthYear() {
        return birthYear;
    }


    public void setBirthYear(int birth_year) throws PersonException {
        if ((birth_year!=0) && (birth_year < 1900 || birth_year > 2030))
            throw new PersonException("Rok urodzenia musi by? w przedziale [1900 - 2030].");
        this.birthYear = birth_year;
    }


    public void setBirthYear(String birth_year) throws PersonException {
        if (birth_year == null || birth_year.equals("")){  // pusty ?a?cuch znak?w oznacza rok niezdefiniowany
            setBirthYear(0);
            return;
        }
        try {
            setBirthYear(Integer.parseInt(birth_year));
        } catch (NumberFormatException e) {
            throw new PersonException("Rok urodzenia musi by? liczb? ca?kowit?.");
        }
    }


    public PersonJob getJob() {
        return job;
    }


    public void setJob(PersonJob job){
        this.job = job;
    }


    public void setJob(String job_name) throws PersonException {
        if (job_name == null || job_name.equals("")) {  // pusty ?a?cuch znak?w oznacza stanowisko niezdefiniowane
            this.job = PersonJob.UNKNOWN;
            return;
        }
        for(PersonJob job : PersonJob.values()){
            if (job.jobName.equals(job_name)) {
                this.job = job;
                return;
            }
        }
        throw new PersonException("Nie ma takiego stanowiska.");
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


    public static void printToFile(PrintWriter writer, Person person){
        writer.println(person.firstName + "#" + person.lastName +
                "#" + person.birthYear + "#" + person.job);
    }


    public static void printToFile(String file_name, Person person) throws PersonException {
        try (PrintWriter writer = new PrintWriter(file_name)) {
            printToFile(writer, person);
        } catch (FileNotFoundException e){
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        }
    }


    public static Person readFromFile(BufferedReader reader) throws PersonException{
        try {
            String line = reader.readLine();
            String[] txt = line.split("#");
            Person person = new Person(txt[0], txt[1]);
            person.setBirthYear(txt[2]);
            person.setJob(txt[3]);
            return person;
        } catch(IOException e){
            throw new PersonException("Wyst?pi? b??d podczas odczytu danych z pliku.");
        }
    }


    public static Person readFromFile(String file_name) throws PersonException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
            return Person.readFromFile(reader);
        } catch (FileNotFoundException e){
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        } catch(IOException e){
            throw new PersonException("Wyst?pi? b??d podczas odczytu danych z pliku.");
        }
    }

}  // koniec klasy Person