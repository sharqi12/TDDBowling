package com.company;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    public void testWorstPlayer(){

        Game game = new Game();
        for(int i=0; i<20; i++)
            game.roll(0);

        Assert.assertEquals(0 ,game.finalScore );
    }

    @Test
    public void testMostConsistentPlayer(){
        Game game = new Game();
        for(int i=0; i<20; i++)
            game.roll(1);

        Assert.assertEquals(20 , game.finalScore);
    }

    @Test
    public void firstRoundStrike(){
        Game game = new Game();
        game.roll(10);
        for(int i=0; i<18; i++)
            game.roll(1);

        Assert.assertEquals(30 , game.finalScore);
    }

    @Test
    public void allRoundsSpare(){
        Game game = new Game();
        for(int i=0; i<10; i++) {
            game.roll(1);
            game.roll(9);
        }
        game.roll(1);
        Assert.assertEquals(110 , game.finalScore);
    }

    @Test
    public void bestGameEver(){
        Game game = new Game();
        for(int i=0; i<12; i++)
            game.roll(10);

        Assert.assertEquals(300 , game.finalScore);
    }

    @Test
    public void strikeCorrectly(){
        Game game = new Game();
        game.roll(10);
        game.roll(3);
        game.roll(3);

        Assert.assertEquals(22 , game.finalScore);
    }

    @Test
    public void spareCorrectly(){
        Game game = new Game();
        game.roll(2);
        game.roll(8);
        game.roll(3);

        Assert.assertEquals(16 , game.finalScore);
    }

}