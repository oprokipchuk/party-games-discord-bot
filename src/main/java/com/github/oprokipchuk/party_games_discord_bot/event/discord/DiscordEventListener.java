package com.github.oprokipchuk.party_games_discord_bot.event.discord;

import discord4j.core.event.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface DiscordEventListener<T extends Event> {

    final Logger LOG = LoggerFactory.getLogger(DiscordEventListener.class);

    Class<T> getEventType();
    Mono<Void> execute(T event);

    default Mono<Void> handleError(Throwable error) {
        LOG.error("Unable to process " + getEventType().getSimpleName(), error);
        return Mono.empty();
    }
}

