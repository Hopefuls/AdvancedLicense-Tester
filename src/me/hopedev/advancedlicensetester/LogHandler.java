package me.hopedev.advancedlicensetester;

import java.awt.*;

public class LogHandler {

    private static StringBuilder sb = new StringBuilder();
    private static boolean run = false;

    public static void addLog(String x) {
        sb.append(x + "\n");
        GateKeeper.output.setText(sb.toString());

    }

    public static void setStatus(String x, Color col) {
        System.out.println("Set!");
        GateKeeper.status.setText(x);
        System.out.println(GateKeeper.status.getText());
        GateKeeper.status.setForeground(col);

    }
}
