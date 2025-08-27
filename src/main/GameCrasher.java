package main;

import javax.swing.*;

public class GameCrasher {

    public static void crashIndexOutOfBounds() {
        int[] smallArray = new int[1];
        smallArray[1] = 5; //Causes indexOutOfBounds
    }

    public static void crashThrowError() {
        throw new RuntimeException();
    }

    public static void anotherGame(){
        Main marin = new Main();
        marin.main(new String[1]);
    }

    public static void crashPC() {
        while(1 > 0) {
            Main marin = new Main();
            marin.main(new String[1]);
        }
    }

    public static void crashWhileLoop() { //the only fatal one so far
        while(true) {
            Object o = new Object();
        }
    }

    public static void crashRecursive() {
        crashRecursive();
    }
}
