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
        try {
            bot.setClickSpeed(Integer.parseInt(properties.getProperty("click_speed")));
        } catch (NumberFormatException exception) {
            printFieldFormatError("click_speed");
            System.exit(1);
        }
        try {
            bot.setxScreenOffset(Integer.parseInt(properties.getProperty("x_screen_offset")));
        } catch (NumberFormatException exception) {
            printFieldFormatError("x_screen_offset");
            System.exit(1);
        }

        return bot;
    }

    private static void printFieldFormatError(String field) {
        System.out.println("ERROR: Wrong format on config field " + field + ".");
        System.out.println("To restore the default config file delete the current one and rerun the program.");
    }

    private static Properties loadConfigProperties() {
        Properties properties = new Properties();
        try {
            String configFilePath = getProgramDirectory() + "/" + configFileName;
            FileInputStream fileInputStream = new FileInputStream(configFilePath);
            properties.load(fileInputStream);
        } catch (IOException exception) {
            System.out.println("WARNING: No config file found.");
            System.out.println("Creating a config file...");
            try {
                createDefaultConfigFile();
            } catch (IOException ioException) {
                System.out.println("ERROR: Could not create config file.");
                System.exit(1);
            }
        }
        return properties;
    }

    private static void createDefaultConfigFile() throws IOException {
        InputStream fileInputStream =
                BotConfigManager.class.getClassLoader().getResourceAsStream(defaultConfigFileName);

        String programDirectory;
        try {
            programDirectory = getProgramDirectory();
        } catch (IOException exception) {
            System.out.println("ERROR: Could not fetch the program directory.");
            exception.printStackTrace();
            throw new IOException();
        }


        if(fileInputStream != null) {
            Files.copy(fileInputStream, Path.of(programDirectory + "/" + configFileName));
        } else {
            throw new IOException();
        }
    }

    private static String getProgramDirectory() throws IOException {
        String programPath;
        try {
            programPath = BotConfigManager.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
            throw new IOException();
        }

        return programPath.substring(0, programPath.lastIndexOf("/") + 1);
    }
}
