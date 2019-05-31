package com.autoSerwis;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * Program: Aplikacja okienkowa z GUI, kt�ra umo�liwia testowanie
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonWindowDialog.java
 *
 *   Autor: Pawe� Rogalinski
 *    Data: pazdziernik 2017 r.
 *
 *
 * Klasa PersonWindowDialog implementuje pomocnicze okna dialogowe
 * umo�liwiaj�ce utworzenie i wype�nienie danymi nowego obiektu klasy Person
 * oraz modyfikacj� danych dla istniej�cego obiektu klasy Person.
 */
public class PersonWindowDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;


    /*
     *  Referencja do obiektu, kt�ry zawiera dane osoby.
     */
    private Person person;


    // Utworzenie i inicjalizacja komponent�w do do budowy
    // okienkowego interfejsu u�ytkownika

    // Font dla etykiet o sta�ej szeroko�ci znak�w
    Font font = new Font("MonoSpaced", Font.BOLD, 12);

    // Etykiety wy�wietlane na panelu
    JLabel firstNameLabel = new JLabel("      Imi�: ");
    JLabel lastNameLabel  = new JLabel("  Nazwisko: ");
    JLabel yearLabel      = new JLabel("   Rok ur.: ");
    JLabel jobLabel       = new JLabel("Stanowisko: ");

    // Pola tekstowe wy�wietlane na panelu
    JTextField firstNameField = new JTextField(10);
    JTextField lastNameField = new JTextField(10);
    JTextField yearField = new JTextField(10);
    JComboBox<PersonJob> jobBox = new JComboBox<PersonJob>(PersonJob.values());

    // Przyciski wy�wietlane na panelu
    JButton OKButton = new JButton("  OK  ");
    JButton CancelButton = new JButton("Anuluj");


    /*
     * Konstruktor klasy PersonWindowDialog.
     *     parent - referencja do okna aplikacji, z kt�rego
     *              zosta�o wywo�ane to okno dialogowe.
     *     person - referencja do obiektu reprezentuj�cego osob�,
     *              kt�rej dane maj� by� modyfikowane.
     *              Je�li person jest r�wne null to zostanie utworzony
     *              nowy obiekt klasy Person
     */
    private PersonWindowDialog(Window parent, Person person) {
        // Wywo�anie konstruktora klasy bazowej JDialog.
        // Ta instrukcja pododuje ustawienie jako rodzica nowego okna dialogowego
        // referencji do tego okna, z kt�rego wywo�ano to okno dialogowe.
        // Drugi parametr powoduje ustawienie trybu modalno�ci nowego okna diakogowego
        //       - DOCUMENT_MODAL oznacza, �e okno rodzica b�dzie blokowane.
        super(parent, Dialog.ModalityType.DOCUMENT_MODAL);

        // Konfiguracja parametr�w tworzonego okna dialogowego
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(220, 200);
        setLocationRelativeTo(parent);

        // zapami�tanie referencji do osoby, kt�rej dane b�d� modyfikowane.
        this.person = person;

        // Ustawienie tytu�u okna oraz wype�nienie zawarto�ci p�l tekstowych
        if (person==null){
            setTitle("Nowa osoba");
        } else{
            setTitle(person.toString());
            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            yearField.setText(""+person.getBirthYear());
            jobBox.setSelectedItem(person.getJob());
        }

        // Dodanie s�uchaczy zdarze� do przycisk�w.
        // UWAGA: s�uchaczem zdarze� b�dzie metoda actionPerformed
        //        zaimplementowana w tej klasie i wywo�ana dla
        //        bie��cej instancji okna dialogowego - referencja this
        OKButton.addActionListener( this );
        CancelButton.addActionListener( this );

        // Utworzenie g��wnego panelu okna dialogowego.
        // Domy�lnym mened�erem rozd�adu dla panelu b�dzie
        // FlowLayout, kt�ry uk�ada wszystkie komponenty jeden za drugim.
        JPanel panel = new JPanel();

        // Zmiana koloru t�a g��wnego panelu okna dialogowego
        panel.setBackground(Color.green);

        // Dodanie i rozmieszczenie na panelu wszystkich komponent�w GUI.
        panel.add(firstNameLabel);
        panel.add(firstNameField);

        panel.add(lastNameLabel);
        panel.add(lastNameField);

        panel.add(yearLabel);
        panel.add(yearField);

        panel.add(jobLabel);
        panel.add(jobBox);

        panel.add(OKButton);
        panel.add(CancelButton);

        // Umieszczenie Panelu w oknie dialogowym.
        setContentPane(panel);


        // Pokazanie na ekranie okna dialogowego
        // UWAGA: T� instrukcj� nale�y wykona� jako ostatni�
        // po zainicjowaniu i rozmieszczeniu na panelu
        // wszystkich komponent�w GUI.
        // Od tego momentu aplikacja wy�wietla nowe okno dialogowe
        // i bokuje g��wne okno aplikacji, z kt�rego wywo�ano okno dialogowe
        setVisible(true);
    }


    /*
     * Implementacja interfejsu ActionListener.
     *
     * Metoda actionPerformrd bedzie automatycznie wywo�ywana
     * do obs�ugi wszystkich zdarze� od obiekt�w, kt�rym jako s�uchacza zdarze�
     * do��czono obiekt reprezentuj�cy bie��c� instancj� okna aplikacji (referencja this)
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Odczytanie referencji do obiektu, kt�ry wygenerowa� zdarzenie.
        Object source = event.getSource();

        if (source == OKButton) {
            try {
                if (person == null) { // Utworzenie nowej osoby
                    person = new Person(firstNameField.getText(), lastNameField.getText());
                } else { // Aktualizacji imienia i nazwiska istniej�cej osoby
                    person.setFirstName(firstNameField.getText());
                    person.setLastName(lastNameField.getText());
                }
                // Aktualizacja pozosta�ych danych osoby
                person.setBirthYear(yearField.getText());
                person.setJob((PersonJob) jobBox.getSelectedItem());

                // Zamkni�cie okna i zwolnienie wszystkich zasob�w.
                dispose();
            } catch (PersonException e) {
                // Tu s� wychwytywane wyj�tki zg�aszane przez metody klasy Person
                // gdy nie s� spe�nione ograniczenia na�o�one na dopuszczelne warto�ci
                // poszczeg�lnych atrybut�w.
                // Wy�wietlanie modalnego okna dialogowego
                // z komunikatem o b��dzie zg�oszonym za pomoc� wyj�tku PersonException.
                JOptionPane.showMessageDialog(this, e.getMessage(), "B��d", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (source == CancelButton) {
            // Zamkni�cie okna i zwolnienie wszystkich zasob�w.
            dispose();
        }
    }


    /*
     * Metoda tworzy pomocnicze okno dialogowe, kt�re tworzy
     * nowy obiekt klasy Person i umo�liwia wprowadzenie danych
     * dla nowo utworzonej osoby.
     * Jako pierwszy parametr nale�y przekaza� referencj� do g��wnego okna
     * aplikacji, z kt�rego ta metoda jest wywo�ywana.
     * G��wne okno aplikacji zostanie zablokowane do momentu zamkni�cia
     * okna dialogowego.
     * Po zatwierdzeniu zmian przyciskiem OK odbywa si�  walidacja poprawno�ci
     * danych w konstruktorze i setterach klasy Person.
     * Je�li zostan� wykryte niepoprawne dane to zostanie przechwycony wyj�tek
     * PersonException i zostanie wy�wietlony komunikat o b��dzie.
     *
     *  Po poprawnym wype�nieniu danych metoda zamyka okno dialogowe
     *  i zwraca referencj� do nowo utworzonego obiektu klasy Person.
     */
    public static Person createNewPerson(Window parent) {
        PersonWindowDialog dialog = new PersonWindowDialog(parent, null);
        return dialog.person;
    }


    /*
     * Metoda tworzy pomocnicze okno dialogowe, kt�re umo�liwia
     * modyfikacj� danych osoby reprezentowanej przez obiekt klasy Person,
     * kt�ry zosta� przekazany jako drugi parametr.
     * Jako pierwszy parametr nale�y przekaza� referencj� do g��wnego okna
     * aplikacji, z kt�rego ta metoda jest wywo�ywana.
     * G��wne okno aplikacji zostanie zablokowane do momentu zamkni�cia
     * okna dialogowego.
     * Po zatwierdzeniu zmian przyciskiem OK odbywa si�  walidacja poprawno�ci
     * danych w konstruktorze i setterach klasy Person.
     * Je�li zostan� wykryte niepoprawne dane to zostanie przechwycony wyj�tek
     * PersonException i zostanie wy�wietlony komunikat o b��dzie.
     *
     *  Po poprawnym wype�nieniu danych metoda aktualizuje dane w obiekcie person
     *  i zamyka okno dialogowe
     */
    public static void changePersonData(Window parent, Person person) {
        new PersonWindowDialog(parent, person);
    }

}
