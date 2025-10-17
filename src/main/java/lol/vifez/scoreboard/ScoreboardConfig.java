package lol.vifez.scoreboard;

import lol.vifez.SpigotTester;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class ScoreboardConfig {

    private final File configFile;
    private final FileConfiguration config;

    public ScoreboardConfig() {
        SpigotTester plugin = SpigotTester.getPlugin(SpigotTester.class);
        configFile = new File(plugin.getDataFolder(), "scoreboard.yml");
        if (!configFile.exists()) {
            plugin.saveResource("scoreboard.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public String getString(String path, String def) {
        return config.getString(path, def);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}