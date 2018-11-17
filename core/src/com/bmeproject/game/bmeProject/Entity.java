package com.bmeproject.game.bmeProject;

import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.battleScreen.player.BattleCard;
import com.bmeproject.game.bmeProject.entity.Background;
import com.bmeproject.game.bmeProject.entity.Type;

/**
 * Daten-Container für Karteninhalte. Enthält alle Attribute einer Karte in Form primitiver Datentypen, die nichts
 * mit deren formeller Darstellung (Grafik, Akustik) zu tun haben. Wird an
 * {@link com.bmeproject.game.bmeProject.theatricalScreen.Card} und ihre Subklassen bei deren Instanziierung übergeben.
 */
public class Entity
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public final BMEProject BME_PROJECT;
	private      String     name;
	private      int        strength;
	private      String     illustrator;
	private      String     illustrationFilePath;
	private      Type       type;
	private      Background background;

	// ===================================
	// CONTRUCTORS
	// ===================================

	public Entity(BMEProject bmeProject)
	{
		BME_PROJECT = bmeProject;
	}

	// ===================================
	// PROCEDURES
	// ===================================

	public void initialize()
	{
		/*
		TODO
		Im Zuge dieser Methode soll ein XML-File ausgelesen und die Feldvariablen name, strength, illustrator,
		illustrationFilePath, type und background initialisiert werden. type und background sind dabei in den
		entsprechenden Enum-Eintrag zu parsen. Die ID der Entität muss nicht hier gespeichert werden, sondern sollte
		als Key in der HashMap entities der Klasse BMEProject hinterlegt sein - das ist Performance-technisch besser
		(siehe 2. Semester Lano)
		 */

	}

	// ===================================
	// FUNCTIONS
	// ===================================

	public int getStrength()
	{
		return strength;
	}

	public BattleCard createBattleCardOfYourself()
	{
		return type.createBattleCard(this);
	}
}
