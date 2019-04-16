package com.bmeproject.game.bmeProject.util;

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

    public SaveGameHandler(BMEProject bmeProject){
        this.bmeProject = bmeProject;
    }
    public void saveGame(User user){
        File file = new File("SaveGames/savegame.txt");
        String cardIds = getCardIds(user);

        try{
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(user.getName() + ";");
            writer.write(cardIds);

            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
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
        try{
            FileReader fileReader = new FileReader("SaveGames/savegame.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            while(true){
                String textFromFile = bufferedReader.readLine();
                System.out.println(textFromFile);
                if(textFromFile == null){
                    System.out.println("izz emptyyys");
                    break;
                }
                saveGameFromFile = textFromFile.split(";");
                Deck deck = new Deck();
                for (int i = 1; i<= saveGameFromFile.length; i++){
                    deck.addCardToDeck(saveGameFromFile[i]);
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
