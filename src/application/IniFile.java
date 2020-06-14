package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class IniFile {

	private boolean isLoaded;
	private String ip;
	private String port;

	public IniFile() {
		try {
			File file = new File("jelly.ini");
			if (!file.exists()) {
				isLoaded = false;
				return;
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
