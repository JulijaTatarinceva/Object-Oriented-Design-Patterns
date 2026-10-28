package com.dungeon;

import com.dungeon.logger.GameLogger;
import com.dungeon.run.DungeonRun;
import com.dungeon.run.NormalDungeonRun;
import com.dungeon.run.SurvivalDungeonRun;

import java.util.Random;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        long seed = Math.abs(System.nanoTime() % 100_000L);
        GameLogger logger = GameLogger.getInstance();

        DungeonRun normalRun = new NormalDungeonRun(new Random(seed), seed);
        normalRun.execute();

        logger.blankLine();
        logger.log("========================================");
        logger.log("Same seed, different mode and iterator");
        logger.log("========================================");
        logger.blankLine();

        DungeonRun survivalRun = new SurvivalDungeonRun(new Random(seed), seed);
        survivalRun.execute();
    }
}
