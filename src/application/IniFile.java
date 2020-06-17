package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class IniFile {

	private static IniFile instance = new IniFile();

	private boolean isLoaded;
	private String ip = "192.168.3.32";
	private String port = "9999";

	public IniFile() {
		try {
			File file = new File("jelly.ini");
			if (!file.exists()) {
				if (file.createNewFile()) {
					Wini ini = new Wini(file);
					ini.put("CommsInfo", "Ip", ip);
					ini.put("CommsInfo", "Port", port);
					ini.store();
				} else {
					isLoaded = false;
				}
			}
			Wini ini = new Wini();
			ini.load(new FileReader(file));
			ip = ini.get("CommsInfo", "Ip");
			port = ini.get("CommsInfo", "Port");
			isLoaded = true;
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IniFile getInstance() {
		return instance;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

}
