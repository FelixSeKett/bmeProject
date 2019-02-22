package com.bmeproject.game.bmeProject.dataAccess;

import com.bmeproject.game.bmeProject.archive.BattleCard;
import com.bmeproject.game.bmeProject.archive.Background;
import com.bmeproject.game.bmeProject.gameObjects.Type;
import com.bmeproject.game.bmeProject.gameObjects.Card;

import java.util.ArrayList;



/**
 * Daten-Container für Karteninhalte. Enthält alle Attribute einer Karte in Form primitiver Datentypen, die nichts
 * mit deren formeller Darstellung (Grafik, Akustik) zu tun haben. Wird an
 * {@link Card} und ihre Subklassen bei deren Instanziierung übergeben.
 */
public class CardContainer{
    // ===================================
    // ATTRIBUTES
    // ===================================

    //public final BMEProject BME_PROJECT;
    private String name;
    private int strength;
    private String illustrator;
    private String illustrationFilePath;
    private Type type;
    private Background background;

    private ArrayList<CardContainer> Cards;

    // ===================================
    // CONTRUCTORS
    // ===================================

    public CardContainer(String cardName, int cardStrengh, String cardIllustrator, String cardIlluFilePath, Type cType, Background cBackground) {
        name = cardName;
        strength = cardStrengh;
        illustrator = cardIllustrator;
        illustrationFilePath = cardIlluFilePath;
        type = cType;
        background = cBackground;
    }

    // ===================================
    // PROCEDURES
    // ===================================

    /*
TODO
Im Zuge dieser Methode soll ein XML-File ausgelesen und die Feldvariablen name, strength, illustrator,
illustrationFilePath, type und background initialisiert werden. type und background sind dabei in den
entsprechenden Enum-Eintrag zu parsen. Die ID der Entität muss nicht hier gespeichert werden, sondern sollte
als Key in der HashMap entities der Klasse BMEProject hinterlegt sein - das ist Performance-technisch besser
(siehe 2. Semester Lano)
 */
    public void initialize(int ID)
    {

    }

    // ===================================
    // FUNCTIONS
    // ===================================

    public String getCardName() { return name; }

    public int getStrength() {
        return strength;
    }

    public String getIllustrationFilePath() { return illustrationFilePath; }

    public BattleCard createBattleCardOfYourself() {
        return null; // type.createBattleCard(this);
    }
}
