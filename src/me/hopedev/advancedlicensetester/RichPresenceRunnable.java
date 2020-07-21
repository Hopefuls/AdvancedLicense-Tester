package me.hopedev.advancedlicensetester;

import net.arikia.dev.drpc.DiscordEventHandlers;

public class RichPresenceRunnable implements Runnable {
    @Override
    public void run() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + "!");
        }).build();
    }
}
