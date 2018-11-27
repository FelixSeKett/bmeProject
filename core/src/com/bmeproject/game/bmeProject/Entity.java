package com.bmeproject.game.bmeProject;

import com.bmeproject.game.bmeProject.battleScreen.Player;
import com.bmeproject.game.bmeProject.battleScreen.player.BattleCard;
import com.bmeproject.game.bmeProject.entity.Background;
import com.bmeproject.game.bmeProject.entity.Type;

/**
 * Daten-Container für Karteninhalte. Enthält alle Attribute einer Karte in Form primitiver Datentypen, die nichts
 * mit deren formeller Darstellung (Grafik, Akustik) zu tun haben. Wird an
 * {@link com.bmeproject.game.bmeProject.theatricalScreen.Card} und ihre Subklassen bei deren Instanziierung übergeben.
 */
public class Entity {
    // ===================================
    // ATTRIBUTES
    // ===================================

    private String name;
    private int strength;
    private String illustrator;
    private String illustrationFilePath;
    private Type type;
    private Background background;

    public Entity(String n, int s, String illu, String illuPath, Type cType, Background bGround) {
		name = n;
		strength = s;
		illustrator = illu;
		illustrationFilePath = illuPath;
		type = cType;
		background = bGround;
    }

    // ===================================
    // METHODS
    // ===================================

    public void initialize() {
		/*
		TODO
		Im Zuge dieser Methode soll ein XML-File ausgelesen und die Feldvariablen name, strength, illustrator,
		illustrationFilePath, type und background initialisiert werden. type und background sind dabei in den
		entsprechenden Enum-Eintrag zu parsen. Die ID der Entität muss nicht hier gespeichert werden, sondern sollte
		als Key in der HashMap entities der Klasse BMEProject hinterlegt sein - das ist Performance-technisch besser
		(siehe 2. Semester Lano)
		 */

    }

    public int getStrength() {
        return strength;
    }

    public boolean entityExists(Entity e){
        if (e != null){
            return true;
        } else return false;
    }

    public BattleCard createBattleCardOfYourself(Player player) {
        return type.createBattleCard(this, player);
    }
}
