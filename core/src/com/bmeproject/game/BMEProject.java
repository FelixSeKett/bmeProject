package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.Sprites.MovingCard;
import com.bmeproject.game.bmeProject.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.bmeproject.game.bmeProject.MultiplayerDemo.Server.Networking;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Die Wurzel des Code-Stamms und Erweiterung von {@link Game}. Wird das Spiel gestartet, erstellt die jeweilige
 * Launcher-Klasse (Desktop-Launcher, IOS-Launcher, etc.) eine Instanz dieser Klasse per Default-Constructor und ruft
 * anschließend {@link #create} auf.
 */
public class BMEProject extends Game
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	public static final boolean DEBUG = true;

	SpriteBatch batch;

	private TitleScreen             titleScreen;
	private BattleScreen            battleScreen;
	private DeckScreen              deckScreen;
	private TestScreen              testScreen;
	private Profile                 profile;
    private HashMap<String, Entity> entities;

	//Server Variablen
    private Networking network;

	// ===================================
	// PROCEDURES
	// ===================================





	@Override
		public void render() {
			//Farbe vom Hintergrund
			Gdx.gl.glClearColor(0,0,0,1);

			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			network.handleInput(Gdx.graphics.getDeltaTime());
			network.updateServer(Gdx.graphics.getDeltaTime());
			batch.begin();
			network.addPlayer(batch);
			//if(player != null){
			//	player.draw(batch);
			//}
			//for(HashMap.Entry<String, MovingCard> entry : friendlyPlayers.entrySet()) {
			//	entry.getValue().draw(batch);
			//}
			batch.end();
		}

	/**
	 * Verrichtet alle Arbeiten, die einmalig beim Erzeugen der Instanz dieser Klasse - sprich: bei Spielstart -
	 * anfallen. Soll alle komplexe Datentypen initialisieren und einen Screen aufrufen.
	 */
	@Override public void create()
	{
		batch = new SpriteBatch();
		titleScreen = new TitleScreen(this);
		battleScreen = new BattleScreen(this);
		deckScreen = new DeckScreen(this);
		testScreen = new TestScreen(this);
		profile = new Profile(this);
        entities = new HashMap();

		//Server Methoden
		network = new Networking();
		network.playerTextures();
		network.connectSocket();
		network.configSocketEvents();

		setScreen(titleScreen);
	}

			public void activateTitleScreen() {
				setScreen(titleScreen);
			}

			public void activateBattleScreen() {
				setScreen(battleScreen);
			}

			public void activateDeckScreen() {
				setScreen(deckScreen);
			}

			public void activateTestScreen() {
				setScreen(testScreen);
			}

			// ===================================
			// FUNCTIONS
			// ===================================

			public Profile getProfile() {
				return profile;
			}

	@Override
	public void dispose() {
		super.dispose();;
	}



}

