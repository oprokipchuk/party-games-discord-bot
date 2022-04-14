package com.github.oprokipchuk.party_games_discord_bot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartGameCommandTest {

    private StartGameCommand startGameCommand;

    @BeforeEach
    void setUp() {
        startGameCommand = new StartGameCommand();
    }

    @Test
    void processCommandTest() {
        // When
        String commandResult = startGameCommand.processCommand();

        // Then
        assertEquals("NO", commandResult);
    }
}