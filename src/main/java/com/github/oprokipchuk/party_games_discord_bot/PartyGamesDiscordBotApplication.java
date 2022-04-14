package com.github.oprokipchuk.party_games_discord_bot;

import discord4j.core.GatewayDiscordClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PartyGamesDiscordBotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PartyGamesDiscordBotApplication.class, args);
        GatewayDiscordClient discordClient = context.getBean(GatewayDiscordClient.class);
        discordClient.onDisconnect().doFirst(discordClient::logout).block();
    }

}
