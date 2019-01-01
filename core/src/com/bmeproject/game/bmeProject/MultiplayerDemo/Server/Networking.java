package com.bmeproject.game.bmeProject.MultiplayerDemo.Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bmeproject.game.Sprites.MovingCard;
import com.bmeproject.game.bmeProject.Entity;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Networking {

    //Server Variablen
    private HashMap<String, MovingCard> friendlyPlayers;
    private Socket socket;
    String id;
    private final float UPDATE_TIME = 1/60f;
    float timer;
    MovingCard player;
    Texture playerCard;
    Texture friendlyCard;


    //Key Inputs und dazugehörige Aktionen werden definiert
    public void handleInput(float dt) {
        if(player != null){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.setPosition(player.getX() + (-200 * dt), player.getY());
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player.setPosition(player.getX() + (+200 * dt), player.getY());
            }
        }
    }

    //Server wird bei jedem Aufruf geupdatet
    public void updateServer(float dt) {
        timer += dt;
        if (timer >= UPDATE_TIME && player != null && player.hasMoved()) {
            JSONObject data = new JSONObject();
            try {
                //liest Spieler-Koordinaten ein
                data.put("x", player.getX());
                data.put("y", player.getY());
                socket.emit("playerMoved", data);
            } catch (JSONException e) {
                Gdx.app.log("SOCKET.IO", "Error sending update data");
            }
        }
    }

    //Fügt Spieler dem Spielfeld hinzu und speicher ihn in eine HashMap
    public void addPlayer(SpriteBatch batch) {
        if(player != null){
            //Zeichnet Spieler
            player.draw(batch);
        }
        for(HashMap.Entry<String, MovingCard> entry : friendlyPlayers.entrySet()) {
            entry.getValue().draw(batch);
        }
    }

    public void playerTextures() {
        // Gibt Spielern Texturen
        playerCard = new Texture("core/assets/visuals/card.png");
        friendlyCard = new Texture("core/assets/visuals/card2.jpg");
        friendlyPlayers = new HashMap<String, MovingCard>();
    }

    //Verbindet die lokale Socket mit dem Server, hier noch lokaler Server
    public void connectSocket(){
        try {
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    // SocketEvents sind die Server-Events, die ausgelöst werden, wenn der Spieler eine Aktion macht
    public void configSocketEvents () {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                player = new MovingCard(playerCard);
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
                    String playerId = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connected: " + id);
                    friendlyPlayers.put(playerId, new MovingCard((friendlyCard)));
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New Player ID");
                }
            }



        }).on("playerDisconnected", new Emitter.Listener() {
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    friendlyPlayers.remove(id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New Player ID");
                }
            }


        }).on("getPlayers", new Emitter.Listener() {
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];
                try {
                    for(int i =0; i < objects.length(); i++) {
                        MovingCard coopPlayer = new MovingCard(friendlyCard);
                        Vector2 position = new Vector2();
                        position.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        position.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        coopPlayer.setPosition(position.x, position.y);

                        friendlyPlayers.put(objects.getJSONObject(i).getString("id"), coopPlayer);
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New Player ID");
                }
            }



        }).on("playerMoved", new Emitter.Listener() {
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    if (friendlyPlayers.get(playerId) != null) {
                        friendlyPlayers.get(playerId).setPosition(x.floatValue(), y.floatValue());
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New Player ID");
                }
            }
        });
    }


    public void dispose() {
        playerCard.dispose();
        friendlyCard.dispose();
    }
}

