package com.autoSerwis;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * Program: Aplikacja okienkowa z GUI, kt�ra umo�liwia testowanie
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonWindowApp.java
 *
 *   Autor: Pawe� Rogalinski
 *    Data: pazdziernik 2017 r.
 */
public class PersonWindowApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final String GREETING_MESSAGE =
            "Program Person - wersja okienkowa\n" +
                    "Autor: Pawe� Rogalinski\n" +
                    "Data:  pa�dziernik 2017 r.\n";


    public static void main(String[] args) {
        // Utworzenie obiektu reprezentuj�cego g��wne okno aplikacji.
        // Po utworzeniu obiektu na pulpicie zostanie wy�wietlone
        // g��wne okno aplikacji.
        new PersonWindowApp();

        // UWAGA: mo�na utworzy� kilka okien aplikacji.
        // Wsystkie okna b�d� wy�wietlone w tym samum miejscu na pulpicie.
        // Po uruchomieniu kilku okien nale�y je r�cznie porozsuwa�.
        // Ka�de okno dzia�a niezale�nie od pozosta�ych.
        // Aplikacja ko�czy swoje dzia�anie po zamkni�ciu wszystkich utworzonych okien
        // lub po naci�ni�ciu przycisku "Zako�cz aplikacj�",
        //
        // Je�eli chcesz wypr�bowa� dzia�anie kilku okien odkoment�j poni�sze linie
        // new PersonWindowApp();
        // new PersonWindowApp();
    }



    /*
     *  Referencja do obiektu, kt�ry zawiera dane aktualnej osoby.
     */
    private Person currentPerson;


    // Font dla etykiet o sta�ej szeroko�ci znak�w
    Font font = new Font("MonoSpaced", Font.BOLD, 12);

    // Etykiety wy�wietlane na panelu w g��wnym oknie aplikacji
    JLabel surnameLabel = new JLabel("  Nazwisko: ");
    JLabel nameLabel    = new JLabel("      Imi�: ");
    JLabel yearLabel    = new JLabel("   Rok ur.: ");
    JLabel jobLabel     = new JLabel("Stanowisko: ");

    // Pola tekstowe wy�wietlane na panelu w g��wnym oknie aplikacji
    JTextField firstNameField = new JTextField(10);
    JTextField lastNameField    = new JTextField(10);
    JTextField yearField    = new JTextField(10);
    JTextField jobField     = new JTextField(10);

    // Przyciski wy�wietlane na panelu w g��wnym oknie aplikacji
    JButton newButton    = new JButton("Nowa osoba");
    JButton editButton   = new JButton("Zmie� dane");
    JButton saveButton   = new JButton("Zapisz do pliku");
    JButton loadButton   = new JButton("Wczytaj z pliku");
    JButton deleteButton = new JButton("Usu� osob�");
    JButton infoButton   = new JButton("O programie");
    JButton exitButton   = new JButton("Zako�cz aplikacj�");


    /*
     * Utworzenie i konfiguracja g��wnego okna apkikacji
     */
    public PersonWindowApp(){
        // Konfiguracja parametr�w g��wnego okna aplikacji
        setTitle("PersonWindowApp");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(270, 270);
        setResizable(false);
        setLocationRelativeTo(null);

        // Zmiana domy�lnego fontu dla wszystkich etykiet
        // U�yto fontu o sta�ej szeroko�ci znak�w, by wyr�wna�
        // szeroko�� wszystkich etykiet.
        surnameLabel.setFont(font);
        nameLabel.setFont(font);
        yearLabel.setFont(font);
        jobLabel.setFont(font);

        // Zablokowanie mo�liwo�ci edycji tekst�w we wszystkich
        // polach tekstowych.  (pola nieedytowalne)
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        yearField.setEditable(false);
        jobField.setEditable(false);


        // Dodanie s�uchaczy zdarze� do wszystkich przycisk�w.
        // UWAGA: s�uchaczem zdarze� b�dzie metoda actionPerformed
        //        zaimplementowana w tej klasie i wywo�ana dla
        //        bie��cej instancji okna aplikacji - referencja this
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        deleteButton.addActionListener(this);
        infoButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Utworzenie g��wnego panelu okna aplikacji.
        // Domy�lnym mened�erem rozd�adu dla panelu b�dzie
        // FlowLayout, kt�ry uk�ada wszystkie komponenty jeden za drugim.
        JPanel panel = new JPanel();

        // Dodanie i rozmieszczenie na panelu wszystkich
        // komponent�w GUI.
        panel.add(surnameLabel);
        panel.add(firstNameField);

        panel.add(nameLabel);
        panel.add(lastNameField);

        panel.add(yearLabel);
        panel.add(yearField);

        panel.add(jobLabel);
        panel.add(jobField);

        panel.add(newButton);
        panel.add(deleteButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(editButton);
        panel.add(infoButton);
        panel.add(exitButton);

        // Umieszczenie Panelu w g��wnym oknie aplikacji.
        setContentPane(panel);

        // Wype�nienie p�l tekstowych danymi aktualnej osoby.
        showCurrentPerson();

        // Pokazanie na ekranie g��wnego okna aplikacji
        // UWAGA: T� instrukcj� nale�y wykona� jako ostatni�
        // po zainicjowaniu i rozmieszczeniu na panelu
        // wszystkich komponent�w GUI.
        // Od tego momentu aplikacja uruchamia g��wn� p�tl� zdarze�
        // kt�ra dzia�a w nowym w�tku niezale�nie od pozosta�ej cz�ci programu.
        setVisible(true);
    }


    /*
     * Metoda wype�nia wszystkie pola tekstowe danymi
     * aktualnej osoby.
     */
    void showCurrentPerson() {
        if (currentPerson == null) {
            firstNameField.setText("");
            lastNameField.setText("");
            yearField.setText("");
            jobField.setText("");
        } else {
            firstNameField.setText(currentPerson.getFirstName());
            lastNameField.setText(currentPerson.getLastName());
            yearField.setText("" + currentPerson.getBirthYear());
            jobField.setText("" + currentPerson.getJob());
        }
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
        Object eventSource = event.getSource();

        try {
            if (eventSource == newButton) {
                currentPerson = PersonWindowDialog.createNewPerson(this);
            }
            if (eventSource == deleteButton) {
                currentPerson = null;
            }
            if (eventSource == saveButton) {
                String fileName = JOptionPane.showInputDialog("Podaj nazw� pliku");
                if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku.
                Person.printToFile(fileName, currentPerson);
            }
            if (eventSource == loadButton) {
                String fileName = JOptionPane.showInputDialog("Podaj nazw� pliku");
                if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku.
                currentPerson = Person.readFromFile(fileName);
            }
            if (eventSource == editButton) {
                if (currentPerson == null) throw new PersonException("�adna osoba nie zosta�a utworzona.");
                PersonWindowDialog.changePersonData(this, currentPerson);
            }
            if (eventSource == infoButton) {
                JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
            }
            if (eventSource == exitButton) {
                System.exit(0);
            }
        } catch (PersonException e) {
            // Tu s� wychwytywane wyj�tki zg�aszane przez metody klasy Person
            // gdy nie s� spe�nione ograniczenia na�o�one na dopuszczelne warto�ci
            // poszczeg�lnych atrybut�w.
            // Wy�wietlanie modalnego okna dialogowego
            // z komunikatem o b��dzie zg�oszonym za pomoc� wyj�tku PersonException.
            JOptionPane.showMessageDialog(this, e.getMessage(), "B��d", JOptionPane.ERROR_MESSAGE);
        }

        // Aktualizacja zawarto�ci wszystkich p�l tekstowych.
        showCurrentPerson();
    }


} // koniec klasy PersonWindowApp