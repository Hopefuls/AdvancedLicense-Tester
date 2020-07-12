package me.hopedev.advancedlicensetester;

public class ConnectorThread implements Runnable {
    public static Thread currentThread;
    private static String License;
    private static String Validation;
    private static boolean Debug;

    public ConnectorThread(String License, String Validation, boolean Debug) {
        ConnectorThread.License = License;
        ConnectorThread.Validation = Validation;
        ConnectorThread.Debug = Debug;
    }

    @Override
    public void run() {
        new startVerification(License, Validation, Debug);
    }

    public void connect() {
        Runnable run = new ConnectorThread(License, Validation, Debug);
        Thread thread = new Thread(run);
        currentThread = thread;
        thread.start();

    }
}
