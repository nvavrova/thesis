package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Nik on 29-11-2015
 */
public class Settings {
	private static Settings instance;

	private final Properties config;
	private final Properties mapsConfig;
	private final Properties metricsConfig;
	private final Properties suspectConfig;

	private static Settings getInstance() throws IOException {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	private Settings() throws IOException {
		this.config = this.load("main\\src\\config.properties");
		this.mapsConfig = this.loadOptional("main\\src\\maps.properties");
		this.metricsConfig = this.loadOptional("main\\src\\metrics.properties");
		this.suspectConfig = this.loadOptional("main\\src\\suspects.properties");
	}

	public static Properties getConfig() throws IOException {
		Settings settings = Settings.getInstance();
		return settings.config;
	}

	public static Properties getMapsConfig() throws IOException {
		Settings settings = Settings.getInstance();
		return settings.mapsConfig;
	}

	public static Properties getMetricsConfig() throws IOException {
		Settings settings = Settings.getInstance();
		return settings.metricsConfig;
	}

	public static Properties getSuspectConfig() throws IOException {
		Settings settings = Settings.getInstance();
		return settings.suspectConfig;
	}

	private Properties loadOptional(String fileName) {
		try {
			return this.load(fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return new Properties();
	}

	private Properties load(String fileName) throws IOException {
		Properties props = new Properties();
		FileReader metricsReader = new FileReader(new File(fileName));
		props.load(metricsReader);
		return props;
	}
}
