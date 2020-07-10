package me.hopedev.advancedlicensetester;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class AdvancedLicense {

	public static int responsecode;
	private String licenseKey;
	private String validationServer;
	private LogType logType = LogType.NORMAL;
	private String securityKey = "YecoF0I6M05thxLeokoHuW8iUhTdIUInjkfF";
	private boolean debug = false;

	public AdvancedLicense(String licenseKey, String validationServer) {
		this.licenseKey = licenseKey;
		this.validationServer = validationServer;
	}

	private static String xor(String s1, String s2) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
			result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
		return result.toString();
	}

	public AdvancedLicense setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
		return this;
	}

	public AdvancedLicense setConsoleLog(LogType logType) {
		this.logType = logType;
		return this;
	}

	public AdvancedLicense useDebug(boolean useDebug) {
		debug = useDebug;
		return this;
	}

	public boolean register() {
		log("[]==========[License-System]==========[]");
		log("Connecting to License-Server...");
		ValidationType vt = isValid();
		if (vt == ValidationType.VALID) {
			log("License valid!");
			log("[]==========[License-System]==========[]");

			return true;
		} else {
			log("License is NOT valid!");
			log("Failed as a result of " + vt.toString());
			log("Disabling plugin!");
			log("[]==========[License-System]==========[]");


			return false;
		}
	}

	public boolean isValidSimple() {
		return (isValid() == ValidationType.VALID);
	}

	private String requestServer(String v1, String v2) throws IOException {
		LogHandler.setStatus("Connecting to License-Server...", Color.BLUE);
		URL url = new URL(validationServer + "?v1=" + v1 + "&v2=" + v2);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		this.responsecode = responseCode;
		if (debug) {
			log("\nSending 'GET' request to URL : " + url);
			log("Response Code : " + responseCode);
		}
		if (responseCode == 200) {
			LogHandler.setStatus("Connected to License Server! [200]", Color.BLUE);

		}
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			return response.toString();
		}
	}

	//
	// Cryptographic
	//

	public ValidationType isValid() {
		String rand = toBinary(UUID.randomUUID().toString());
		String sKey = toBinary(securityKey);
		String key = toBinary(licenseKey);

		try {
			String response = requestServer(xor(rand, sKey), xor(rand, key));

			if (response.startsWith("<")) {
				log("The License-Server returned an invalid response!");
				log("In most cases this is caused by:");
				log("1) Your Web-Host injects JS into the page (often caused by free hosts)");
				log("2) Your ValidationServer-URL is wrong");
				log(
						"SERVER-RESPONSE: " + (response.length() < 150 || debug ? response : response.substring(0, 150) + "..."));

				return ValidationType.PAGE_ERROR;
			}

			try {


				return ValidationType.valueOf(response);

			} catch (IllegalArgumentException exc) {
				String respRand = xor(xor(response, key), sKey);
				if (rand.substring(0, respRand.length()).equals(respRand))
					return ValidationType.VALID;
				else
					return ValidationType.WRONG_RESPONSE;
			}
		} catch (IOException e) {
			if (debug)
				e.printStackTrace();
			return ValidationType.PAGE_ERROR;
		}
	}

	//
	// Enums
	//

	private String toBinary(String s) {
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}

	private void log(String message) {
		LogHandler.addLog(message);


	}

	//
	// Binary methods
	//

	public enum LogType {
		NORMAL, LOW, NONE;
	}

	//
	// Console-Log
	//

	public enum ValidationType {
		WRONG_RESPONSE, PAGE_ERROR, URL_ERROR, KEY_OUTDATED, KEY_NOT_FOUND, NOT_VALID_IP, INVALID_PLUGIN, VALID;
	}
}
