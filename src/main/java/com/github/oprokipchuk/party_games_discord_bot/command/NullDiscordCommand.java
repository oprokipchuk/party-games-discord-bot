package com.github.oprokipchuk.party_games_discord_bot.command;

public class NullDiscordCommand implements DiscordCommand {

    @Override
    public String getCommandName() {
        return "non-existing-command";
    }

    @Override
    public String processCommand() {
        return "non-existing-command";
    }
}
