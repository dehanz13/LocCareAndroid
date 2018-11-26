package com.example.cse3311.loccareandroid;

import java.util.Random;

public class RoomGenerator {
    public static String generate() {
        Random generator = new Random();
        int i, rnd;
        String codeStr = "";
        i = 0;
        while (i < 6) {
            rnd = 65 + generator.nextInt(26);
            codeStr = new StringBuilder().append(codeStr).append((char) rnd).toString();
            i += 1;
        }
        return codeStr;
    }
}
