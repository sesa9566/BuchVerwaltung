import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für die Buch-Klasse.
 */
public class BuchTest {

    private Buch buch;

    // Diese Methode wird vor jedem Test ausgeführt, um das Testobjekt vorzubereiten
    @BeforeEach
    public void setUp() {
        // Initialisieren des Buch-Objekts mit den Testdaten
        buch = new Buch("1984", "George Orwell", 1949, "Science Fiction");
    }

    // Test für die getTitel() Methode
    @Test
    public void testGetTitel() {
        // Überprüfen, ob der Titel korrekt zurückgegeben wird
        assertEquals("1984", buch.getTitel(), "Der Titel sollte '1984' sein.");
    }

    // Test für die getAutor() Methode
    @Test
    public void testGetAutor() {
        // Überprüfen, ob der Autor korrekt zurückgegeben wird
        assertEquals("George Orwell", buch.getAutor(), "Der Autor sollte 'George Orwell' sein.");
    }

    // Test für die getJahr() Methode
    @Test
    public void testGetJahr() {
        // Überprüfen, ob das Jahr korrekt zurückgegeben wird
        assertEquals(1949, buch.getJahr(), "Das Jahr sollte '1949' sein.");
    }

    // Test für die getGenre() Methode
    @Test
    public void testGetGenre() {
        // Überprüfen, ob das Genre korrekt zurückgegeben wird
        assertEquals("Science Fiction", buch.getGenre(), "Das Genre sollte 'Science Fiction' sein.");
    }
}
