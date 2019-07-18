import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.dataAccess.CardGenerator;
import com.bmeproject.game.bmeProject.gameObjects.Card;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class junit_test_desktopTest {

    @Test
    public void baseTest(){
        assertTrue(true);
    }

    //DataAccess-Tests
    @Test
    public void generateAllCardsTest(){
        CardGenerator cardgen = new CardGenerator("testCard.xml");
        HashMap<Integer, Card> cardList = cardgen.createAllCards();
        Card cardTest = cardList.get(1);
        assertTrue(cardList != null);
        assertEquals(cardTest.NAME, "TEST");
        assertEquals(cardTest.TYPE, Type.CREATURE);
        assertEquals(cardTest.ID, 1);
    }

}