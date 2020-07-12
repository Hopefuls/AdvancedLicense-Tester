package me.hopedev.advancedlicensetester;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {
    private static ClassLoader classLoader = Main.class.getClassLoader();

    public static void main(String[] args) throws IOException {
        initializeGUI();


    }

    private static void initializeGUI() throws IOException {
        GUI dialog = new GUI();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setTitle("AdvancedLicense-Tester made by HopeDev");
        Image image = ImageIO.read(Objects.requireNonNull(classLoader.getResource("resources/icon.png")));
        dialog.setIconImage(image);
    }
}
