package com.example.flashcard;
import java.util.ArrayList;

public class ArrayHelper {
    public static ArrayList<String> getStringArrayList(String... s) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }

        return list;
    }
}