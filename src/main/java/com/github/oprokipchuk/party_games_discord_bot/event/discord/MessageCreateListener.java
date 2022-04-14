package com.github.oprokipchuk.party_games_discord_bot.event.discord;

import com.github.oprokipchuk.party_games_discord_bot.command.DiscordCommand;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class MessageCreateListener implements DiscordEventListener<MessageCreateEvent> {

    private final Map<String, DiscordCommand> commandsMap;

    public MessageCreateListener(@Qualifier("commandNameToCommandMap") Map<String, DiscordCommand> commandsMap) {
        this.commandsMap = commandsMap;
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return Mono.just(event.getMessage())
                //.filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .map(Message::getContent)
                .filter(content -> content.startsWith("!"))
                .map(content -> commandsMap.getOrDefault(
                        content.toLowerCase(),
                        DiscordCommand.NULL_COMMAND))
                .filter(command -> !DiscordCommand.NULL_COMMAND.equals(command))
                //.flatMap(command -> Mono.fromCallable(command::processCommand))
                .map(DiscordCommand::processCommand)
                .flatMap((response) -> event.getMessage().getChannel().flatMap(channel -> channel.createMessage(response)))
                .then();
    }
}
