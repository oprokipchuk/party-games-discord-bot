package com.github.oprokipchuk.party_games_discord_bot.command;

import org.springframework.stereotype.Component;

@Component
public class StartGameCommand implements DiscordCommand {

    private static final String COMMAND_NAME = "!startgame";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String processCommand() {
        return "NO";
    }
}
