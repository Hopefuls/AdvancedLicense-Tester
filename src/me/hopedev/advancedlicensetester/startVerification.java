package me.hopedev.advancedlicensetester;


import java.awt.*;

public class startVerification {
    private static LogHandler Logger = new LogHandler();

    public startVerification(String LicenseKey, String validationServer, boolean debug) {
        AdvancedLicense.ValidationType vt = new AdvancedLicense(LicenseKey, validationServer).useDebug(debug).isValid();

        switch (vt) {
            case VALID:
                LogHandler.setStatus("Key is VALID! [200]", Color.green);
                break;
            case PAGE_ERROR:
                if (AdvancedLicense.responsecode == 404)
                    LogHandler.setStatus("LicenseSystem could not be reached or doesn't exist, see log above.", Color.red);
                else
                    LogHandler.addLog(AdvancedLicense.TransferException.getLocalizedMessage());
                LogHandler.addLog(AdvancedLicense.TransferException.getMessage());
                LogHandler.setStatus("PAGE_ERROR! See Log above", Color.red);


                break;
            case WRONG_RESPONSE:
                LogHandler.setStatus("WRONG_RESPONSE! See Log above", Color.red);
                break;
            case KEY_NOT_FOUND:
                LogHandler.setStatus("KEY NOT FOUND, but Server VALID! [200]", Color.green);
                break;

        }


    }
}
