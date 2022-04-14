package com.github.oprokipchuk.party_games_discord_bot.config;

import com.github.oprokipchuk.party_games_discord_bot.command.DiscordCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BotInteractionConfiguration {

    @Bean
    public Map<String, DiscordCommand> commandNameToCommandMap(List<DiscordCommand> commandList) {
        return commandList.stream()
                .collect(Collectors.toMap(
                        command -> command.getCommandName().toLowerCase(),
                        Function.identity()));
    }
}
