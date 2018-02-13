package com.example.android.javalibjoke;

import java.util.ArrayList;
import java.util.Random;

public class ShowJoke {

    private ArrayList<String> jokes;

    public ShowJoke(){
        jokes = new ArrayList<String>();
        jokes.add("Do que diabo morreu? De diabetes.");
        jokes.add("O garoto apanhou da vizinha, e a mãe furiosa foi tomar satisfação: Por que a senhora bateu no meu filho? Ele foi mal-educado, e me chamou de gorda. E a senhora acha que vai emagrecer batendo nele?");
    }

    public String getJoke(){
        Random random = new Random();
        int position = random.nextInt(jokes.size());
        return jokes.get(position);
    }
}
