package com.bmeproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bmeproject.game.bmeProject.*;

import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
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

	private TitleScreen             titleScreen;
	private BattleScreen            battleScreen;
	private DeckScreen              deckScreen;
	private TestScreen              testScreen;
	private Profile                 profile;
	private HashMap<String, Entity> entities;
	private Socket socket;
	// ===================================
	// PROCEDURES
	// ===================================

	/**
	 * Verrichtet alle Arbeiten, die einmalig beim Erzeugen der Instanz dieser Klasse - sprich: bei Spielstart -
	 * anfallen. Soll alle komplexe Datentypen initialisieren und einen Screen aufrufen.
	 */
	@Override public void create()
	{
		titleScreen = new TitleScreen(this);
		battleScreen = new BattleScreen(this);
		deckScreen = new DeckScreen(this);
		testScreen = new TestScreen(this);
		profile = new Profile(this);
		entities = new HashMap();
		connectSocket();
		configSocketEvents();

		setScreen(titleScreen);
	}

	public void connectSocket(){
		try {
			socket = IO.socket("http://localhost:8080");
			socket.connect();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void configSocketEvents () {
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Gdx.app.log("SocketIO", "Connected");
			}
		}).on("socketID", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					String id = data.getString("id");
					Gdx.app.log("SocketIO", "My ID: " + id);

				} catch (JSONException e) {
					Gdx.app.log("SocketIO", "Error getting ID");
				}
			}
		}).on("newPlayer", new Emitter.Listener() {
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					String id = data.getString("id");
					Gdx.app.log("SocketIO", "New Player Connected: " + id);
				} catch (JSONException e) {
					Gdx.app.log("SocketIO", "Error getting New Player ID");
				}
			}
		});
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
}

