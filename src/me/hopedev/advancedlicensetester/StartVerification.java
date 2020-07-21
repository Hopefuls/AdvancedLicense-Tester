package me.hopedev.advancedlicensetester;


import java.awt.*;

public class StartVerification {
    private static final LogHandler Logger = new LogHandler();

    public StartVerification(String LicenseKey, String validationServer, boolean debug) {
        AdvancedLicense.ValidationType vt = new AdvancedLicense(LicenseKey, validationServer).useDebug(debug).isValid();
        Color dark = new Color(0x0E4802);

        switch (vt) {
            case VALID:
                LogHandler.setStatus("Key is VALID! [200]", dark);
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
                LogHandler.setStatus("KEY NOT FOUND, but Server VALID! [200]", dark);


                break;
            default:
                LogHandler.setStatus(vt.toString(), Color.blue);
                break;
        }


    }
}
