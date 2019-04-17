package com.bmeproject.game.bmeProject.util;

import com.badlogic.gdx.Gdx;
import com.bmeproject.game.BMEProject;
import com.bmeproject.game.bmeProject.gameObjects.Deck;
import com.bmeproject.game.bmeProject.userData.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class SaveGameHandler {

    BMEProject bmeProject;
    String filepath = "savegame.txt";
    private static final String TAG = SaveGameHandler.class.getName();

    public SaveGameHandler(BMEProject bmeProject){
        this.bmeProject = bmeProject;
    }
    public void saveGame(User user){
        File file = new File(filepath);
        String cardIds = getCardIds(user);

        try{
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(user.getName() + ";");
            writer.write(cardIds);

            writer.flush();
            writer.close();
        } catch (Exception e){
            Gdx.app.debug(TAG, e.getMessage());
        }
    }

    private String getCardIds(User user){
        HashMap<Integer, Deck> decksToSave = user.getDecks();
        Deck deck;
        String ids = "";
        for (int i : decksToSave.keySet()) {
            deck = decksToSave.get(i);
            for(int j = 0; j <= deck.getAllCardsFromDeck().size()-1; j++){
                ids += String.valueOf(deck.getCardIdFromDeck(j)) + ",";
            }
            ids += ";";
        }
        return ids;
    }

    public User loadGame(User user){
        String[] saveGameFromFile;
        //if file exists
        File tmp = new File(filepath);
        if(tmp.exists()) {
            try {
                FileReader fileReader = new FileReader(filepath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while (true) {
                    String textFromFile = bufferedReader.readLine();
                    if (textFromFile == null) {
                        break;
                    }
                    saveGameFromFile = textFromFile.split(";");
                    Deck deck = new Deck();
                    String[] ids;
                    for (int i = 1; i <= saveGameFromFile.length - 1; i++) {
                        ids = saveGameFromFile[i].split(",");

                        for (int j = 0; j <= ids.length - 1; j++) {
                            deck.addCardToDeck(Integer.parseInt(ids[j]));
                        }
                    }
                }
            } catch (Exception e) {
                Gdx.app.debug(TAG, e.getMessage());
            }
        } else {
            Gdx.app.debug(TAG, "File not found!");
        }
        return user;
    }

}
