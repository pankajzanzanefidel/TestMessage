package com.example.testmessage.testmessageapp.utils;


import java.util.Random;

public class RandomUtils {

    public static int getRandomInrange(int startRange,int endRange){

        int range = new Random().nextInt(endRange-startRange);

        return (range+startRange);
    }
}
