/**
 * The Data class provides static methods for generating random words and handling special characters.
 * @Author: Robert Tronhage
 */
package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.io.ColorHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Data {
    public static final String[] swedishWords = {
            "Konstitution", "Förbiseende", "Beslutande", "Kommunikation", "Administration",
            "Utbildning", "Identifiering", "Konsolidering", "Förutsägbarhet", "Implementering",
            "Konkurrenskraftig", "Regeringsförhandlingar", "Konstruktiv", "Förstärkning", "Ansvarsfull",
            "Förhållningssätt", "Utveckling", "Framtidsutsikt", "Samhälle", "Kompetensutveckling"
    };

    public static final String[] englishWords = {
            "Constitutional", "Oversight", "Decision", "Communication", "Management",
            "Education", "Identification", "Consolidation", "Predictability", "Implementation",
            "Competitiveness", "Negotiations", "Constructive", "Reinforcement", "Accountable",
            "Approach", "Development", "Perspective", "Community", "Enhancement"
    };

    public static final String[] specialCharacters = {
            "!#&)", "%/", "=?½", "&%#", ")&/" , "%" , ")(" , "§" , "]" , "'"
    };

    public static List<String> getRandomWordsForGame(String[] words) {
        List<String> randomWords = new ArrayList<>();
        Random random = new Random();
        List<String> tempWords = new ArrayList<>(List.of(words));
        Collections.shuffle(tempWords);

        for (int i = 0; i < 10; i++) {
            String word = tempWords.get(i);
            randomWords.add(word);
        }
        return randomWords;
    }

    public static String generateHighlightedWords(String randomWords){
        String[] words = randomWords.split(" ");
        StringBuilder highlightedWords = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i % 2 == 0) {
                highlightedWords.append(ColorHandler.blue(words[i]));
            } else {
                highlightedWords.append(words[i]);
            }

            if (i < words.length - 1) {
                highlightedWords.append(" ");
            }
        }

        return highlightedWords.toString();
    }


}
