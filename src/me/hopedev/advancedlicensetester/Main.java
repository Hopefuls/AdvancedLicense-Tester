package me.hopedev.advancedlicensetester;


import me.hopedev.advancedlicensetester.RichPresence.ReadyEvent;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private static final ClassLoader classLoader = Main.class.getClassLoader();

    public static void main(String[] args) throws IOException {
        startup();
        initializeGUI();


    }

    private static void initializeGUI() throws IOException {
        System.out.println("Starting...");
        // Download Icon because im tired asf of the resources folder gonna kms
        URL url = new URL("https://raw.githubusercontent.com/Hopefuls/AdvancedLicense-Tester/master/resources/Icon.png");
        BufferedImage img = ImageIO.read(url);
        File file = new File("Icon");
        ImageIO.write(img, "png", file);
        System.out.println("Setting GUI..");
        // Start the GUI
        GUI dialog = new GUI();
        // pack it
        dialog.pack();
        // set title, icon and return some debugging
        dialog.setTitle("AdvancedLicense-Tester made by HopeDev");
        System.out.println("Reading Icon at " + file.getAbsolutePath());
        Image image = ImageIO.read(file);
        dialog.setIconImage(image);
        // finally, show it
        dialog.setResizable(false);
        dialog.setVisible(true);
    }


    public static void startup() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyEvent()).build();

        DiscordRPC.discordInitialize("735171066263109726", handlers, true);
        DiscordRichPresence rich = new DiscordRichPresence.Builder("Testing their AdvancedLicense System").setBigImage("icon", "AdvancedLicense-Tester by HopeDev").setSmallImage("advancedlicense", "AdvancedLicense by Leoko").setStartTimestamps(System.currentTimeMillis()).build();
        DiscordRPC.discordRunCallbacks();
        DiscordRPC.discordUpdatePresence(rich);
    }

}
