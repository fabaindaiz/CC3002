package controller.observer;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndGameObserverTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.getGameMap().printMap();
    }

    @Test
    void updateCaseSpecial() {
        controller.initEndlessGame();
        assertEquals(controller.getInitiatedGameStatus(), true);
        controller.removeTactician("Player 0");
        assertEquals(controller.getInitiatedGameStatus(), false);
    }

    @Test
    void updateCaseNormal() {

    }
}