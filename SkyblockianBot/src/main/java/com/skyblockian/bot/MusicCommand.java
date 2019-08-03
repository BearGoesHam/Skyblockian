package com.skyblockian.bot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class MusicCommand
    extends ListenerAdapter
{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {
        String message = e.getMessage().getContentRaw();

        if (e.getMember().getUser().isBot()) return;

        if (!(message.startsWith("!play"))) return;

        Guild g = e.getGuild();

        VoiceChannel channel = g.getVoiceChannelsByName("music", true).get(0);
        AudioManager manager = g.getAudioManager();

        ma
    }
}
