import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class BotConfigManager {
    private static final String configFileName = "bot.config";
    private static final String defaultConfigFileName = "default_bot.config";

    public static Bot generateBotFromConfig() {
        Bot bot = new Bot();

        Properties properties = loadConfigProperties();

        int clickSpeed = 0;
        try {
            clickSpeed = Integer.parseInt(properties.getProperty("click_speed"));
        } catch (NumberFormatException exception) {
            printFieldFormatError("click_speed");
            System.exit(1);
        }
        if(clickSpeed < 0 || clickSpeed > 60000) {
            System.out.println("WARNING: Invalid click speed, defaulting to 0.");
            clickSpeed = 0;
        }
        bot.setClickSpeed(clickSpeed);

        int xScreenOffset = 0;
        try {
            xScreenOffset = Integer.parseInt(properties.getProperty("x_screen_offset"));
        } catch (NumberFormatException exception) {
            printFieldFormatError("x_screen_offset");
            System.exit(1);
        }
        bot.setxScreenOffset(xScreenOffset);

        return bot;
    }

    private static void printFieldFormatError(String field) {
        System.out.println("ERROR: Wrong format on config field " + field + ".");
        System.out.println("To restore the default config file delete the current one and rerun the program.");
    }

    private static Properties loadConfigProperties() {
        Properties properties = new Properties();
        try {
            properties = attemptLoadConfigProperties();
        } catch (IOException exception) {
            System.out.println("WARNING: No config file found.");
            System.out.println("Creating a config file...");
            try {
                createDefaultConfigFile();
                properties = attemptLoadConfigProperties();
            } catch (IOException ioException) {
                System.out.println("ERROR: Could not create config file.");
                System.exit(1);
            }
        }
        return properties;
    }

    private static Properties attemptLoadConfigProperties() throws IOException{
        String configFilePath = "./" + configFileName;
        FileInputStream fileInputStream = new FileInputStream(configFilePath);
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties;
    }

    private static void createDefaultConfigFile() throws IOException {
        InputStream fileInputStream =
                BotConfigManager.class.getClassLoader().getResourceAsStream(defaultConfigFileName);

        if(fileInputStream != null) {
            Files.copy(fileInputStream, Path.of("./" + configFileName));
        } else {
            throw new IOException();
        }
    }
}
