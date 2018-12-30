package com.bmeproject.game.bmeProject.MultiplayerDemo.Server;

import com.badlogic.gdx.graphics.Texture;
import com.bmeproject.game.Sprites.MovingCard;
import io.socket.client.Socket;

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

}

