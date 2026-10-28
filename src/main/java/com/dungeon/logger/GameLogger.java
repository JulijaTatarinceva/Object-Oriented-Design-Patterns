package com.dungeon.logger;

public final class GameLogger {

    private static volatile GameLogger instance;

    private GameLogger() {
    }

    public static GameLogger getInstance() {
        if (instance == null) {
            synchronized (GameLogger.class) {
                if (instance == null) {
                    instance = new GameLogger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }

    public void blankLine() {
        System.out.println();
    }
}
