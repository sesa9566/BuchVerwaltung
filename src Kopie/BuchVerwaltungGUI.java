import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class BuchVerwaltungGUI {

    private JFrame frame;
    private JTextField textFieldTitel;
    private JTextField textFieldAutor;
    private JSpinner spinnerJahr;
    private JComboBox<String> cbGenre;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<Buch> buchListe = new ArrayList<Buch>();
    private JComboBox<String> cbFilterGenre;

    /**
     * Startet die Anwendung.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BuchVerwaltungGUI window = new BuchVerwaltungGUI();
                    window.frame.setVisible(true);
                    window.showGreeting();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Erstellt die Anwendung.
     */
    public BuchVerwaltungGUI() {
        initialize();
    }

    /**
     * Initialisiert den Inhalt des Fensters.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 255, 240));

        // Panel für das Formular
        JPanel panelForm = new JPanel();
        panelForm.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buchdaten eingeben", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelForm.setBounds(20, 60, 400, 220);
        frame.getContentPane().add(panelForm);
        panelForm.setLayout(null);
        panelForm.setBackground(new Color(255, 250, 240));

        JLabel lblTitel = new JLabel("Titel:");
        lblTitel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitel.setBounds(20, 20, 100, 16);
        panelForm.add(lblTitel);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAutor.setBounds(20, 52, 100, 16);
        panelForm.add(lblAutor);

        JLabel lblJahr = new JLabel("Jahr:");
        lblJahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblJahr.setBounds(20, 84, 100, 16);
        panelForm.add(lblJahr);

        JLabel lblGenre = new JLabel("Genre:");
        lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenre.setBounds(20, 116, 100, 16);
        panelForm.add(lblGenre);

        textFieldTitel = new JTextField();
        textFieldTitel.setBounds(130, 16, 250, 26);
        panelForm.add(textFieldTitel);
        textFieldTitel.setColumns(10);

        textFieldAutor = new JTextField();
        textFieldAutor.setBounds(130, 48, 250, 26);
        panelForm.add(textFieldAutor);
        textFieldAutor.setColumns(10);

        spinnerJahr = new JSpinner();
        spinnerJahr.setModel(new SpinnerNumberModel(2023, 1500, 2100, 1));
        spinnerJahr.setBounds(130, 80, 250, 26);
        panelForm.add(spinnerJahr);

        cbGenre = new JComboBox<>();
        cbGenre.setModel(new DefaultComboBoxModel<>(new String[]{"Fiktion", "Sachbuch", "Fantasy", "Science Fiction", "Krimi"}));
        cbGenre.setBounds(130, 112, 250, 27);
        panelForm.add(cbGenre);

        JButton btnSpeichern = new JButton("Speichern");
        btnSpeichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSpeichern.setBounds(130, 150, 250, 29);
        btnSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addData();
            }
        });
        panelForm.add(btnSpeichern);

        // Löschen-Button hinzufügen
        JButton btnLoeschen = new JButton("Löschen");
        btnLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLoeschen.setBounds(130, 190, 250, 29);
        btnLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        panelForm.add(btnLoeschen);

        // Panel für die Tabelle
        JPanel panelTable = new JPanel();
        panelTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buchliste", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelTable.setBounds(20, 300, 840, 250);
        frame.getContentPane().add(panelTable);
        panelTable.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 20, 820, 220);
        panelTable.add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"Titel", "Autor", "Jahr", "Genre"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        // Filter-Panel
        JPanel panelFilter = new JPanel();
        panelFilter.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelFilter.setBounds(450, 60, 400, 100);
        frame.getContentPane().add(panelFilter);
        panelFilter.setLayout(null);
        panelFilter.setBackground(new Color(255, 250, 240));

        JLabel lblFilterGenre = new JLabel("Genre filtern:");
        lblFilterGenre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFilterGenre.setBounds(20, 20, 100, 16);
        panelFilter.add(lblFilterGenre);

        cbFilterGenre = new JComboBox<>();
        cbFilterGenre.setModel(new DefaultComboBoxModel<>(new String[]{"Alle", "Fiktion", "Sachbuch", "Fantasy", "Science Fiction", "Krimi"}));
        cbFilterGenre.setBounds(130, 16, 250, 27);
        panelFilter.add(cbFilterGenre);

        JButton btnFilter = new JButton("Filter anzeigen");
        btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnFilter.setBounds(130, 50, 250, 29);
        btnFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterData();
            }
        });
        panelFilter.add(btnFilter);

        // Initialisierte Beispielobjekte
        initBuecher();
        updateTable();
    }

    /**
     * Zeigt ein Begrüßungs-Popup-Fenster an.
     */
    private void showGreeting() {
        JOptionPane.showMessageDialog(frame, "Willkommen zur Buchverwaltung!", "Begrüßung", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Fügt ein neues Buch zur Liste hinzu und aktualisiert die Tabelle.
     */
    public void addData() {
        try {
            String titel = textFieldTitel.getText();
            String autor = textFieldAutor.getText();
            int jahr = (int) spinnerJahr.getValue();
            String genre = (String) cbGenre.getSelectedItem();

            if (titel.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Bitte alle Felder ausfüllen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Buch buch = new Buch(titel, autor, jahr, genre);
            buchListe.add(buch);
            updateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Eingabe nicht korrekt. Bitte überprüfen Sie Ihre Eingaben.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Filtert die Bücher basierend auf dem ausgewählten Genre.
     */
    public void filterData() {
        String selectedGenre = (String) cbFilterGenre.getSelectedItem();
        ArrayList<Buch> gefilterteListe = new ArrayList<>();

        for (Buch buch : buchListe) {
            if ("Alle".equals(selectedGenre) || buch.getGenre().equals(selectedGenre)) {
                gefilterteListe.add(buch);
            }
        }

        updateTable(gefilterteListe);
    }

    /**
     * Initialisiert Beispielbücher zur Anzeige.
     */
    public void initBuecher() {
        buchListe.add(new Buch("Der Herr der Ringe", "J.R.R. Tolkien", 1954, "Fantasy"));
        buchListe.add(new Buch("1984", "George Orwell", 1949, "Science Fiction"));
        buchListe.add(new Buch("Die Verwandlung", "Franz Kafka", 1915, "Fiktion"));
        buchListe.add(new Buch("Sapiens", "Yuval Noah Harari", 2011, "Sachbuch"));
        buchListe.add(new Buch("Der Alchemist", "Paulo Coelho", 1988, "Fiktion"));
    }

    /**
     * Aktualisiert die Tabelle mit allen Büchern.
     */
    private void updateTable() {
        updateTable(buchListe);
    }

    /**
     * Aktualisiert die Tabelle mit einer spezifischen Liste von Büchern.
     */
    private void updateTable(ArrayList<Buch> liste) {
        tableModel.setRowCount(0);
        for (Buch buch : liste) {
            tableModel.addRow(new Object[]{buch.getTitel(), buch.getAutor(), buch.getJahr(), buch.getGenre()});
        }
    }

    /**
     * Löscht ein Buch aus der Liste und aktualisiert die Tabelle.
     */
    public void deleteData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            buchListe.remove(selectedRow);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Bitte wählen Sie ein Buch zum Löschen aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
}



