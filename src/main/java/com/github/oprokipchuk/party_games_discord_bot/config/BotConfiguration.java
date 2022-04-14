package com.github.oprokipchuk.party_games_discord_bot.config;

import com.github.oprokipchuk.party_games_discord_bot.event.discord.DiscordEventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BotConfiguration {

    @Value("${bot.config.token}")
    private String token;

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<DiscordEventListener<T>> discordEventListeners) {
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        for(DiscordEventListener<T> listener : discordEventListeners) {
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }

        //client.onDisconnect().doFirst(client::logout).block();
        return client;
    }
}
