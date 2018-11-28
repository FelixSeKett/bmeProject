import com.bmeproject.game.bmeProject.Entity;
import com.bmeproject.game.bmeProject.entity.Background;
import com.bmeproject.game.bmeProject.entity.Type;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests sind mit JUnit 4 umgesetzt. Sollte es nicht möglich sein, neue Tests zu schreiben. Importiert die JUnit Library mit
 * der Anleitung unter: https://stackoverflow.com/questions/19330832/setting-up-junit-with-intellij-idea
 * (natürlich ohne den Part, den Test-Ordner und Klasse zu erstellen, oft muss der Ordner auch neu als Test-Root definiert werden)
 * Naming: class_method_erwartesErgebnis + Kommentar
 */

public class junit_test_desktop {

    //Test 000: einfacher Basis-Test
    @Test
    public void baseTest() {
        Assert.assertTrue(true);
    }

    //Test 001: Class entity / Methode getStrengh /
    @Test
    public void entity_getStrengh_int() {
        // Setup
        int testStrengh = 10;
        Entity e = new Entity("TEST", testStrengh, "TESTIllu", "TestilluPath", Type.BASE, Background.GROUND);

        // Test
        Assert.assertEquals(testStrengh, e.getStrength());
    }

}
