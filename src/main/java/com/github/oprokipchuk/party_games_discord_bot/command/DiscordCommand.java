package com.github.oprokipchuk.party_games_discord_bot.command;

import org.springframework.stereotype.Component;

@Component
public interface DiscordCommand {

    DiscordCommand NULL_COMMAND = new NullDiscordCommand();

    String getCommandName();
    String processCommand();
}
