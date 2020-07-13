package me.hopedev.advancedlicensetester;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private static ClassLoader classLoader = Main.class.getClassLoader();

    public static void main(String[] args) throws IOException {
        initializeGUI();


    }

    private static void initializeGUI() throws IOException {
        System.out.println("Starting...");
        // Download Icon because im tired asf of the resources folder gonna kms
        URL url = new URL("https://raw.githubusercontent.com/Hopefuls/AdvancedLicense-Tester/master/resources/Icon.png");
        BufferedImage img = ImageIO.read(url);
        File file = new File("Icon");
        ImageIO.write(img, "png", file);

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
        dialog.setVisible(true);
        System.out.println("Successfully started! yes no does it work?");
    }
}
