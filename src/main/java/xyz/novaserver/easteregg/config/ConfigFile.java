package xyz.novaserver.easteregg.config;

import com.google.common.base.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ConfigFile {
    private final JavaPlugin plugin;
    private final String fileName;

    private final File file;
    private FileConfiguration config;

    public ConfigFile(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);

        reloadConfig();
        saveDefaultConfig();
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);

        final InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream == null) {
            return;
        }

        config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }

    public void saveConfig() {
        try {
            getConfig().save(file);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config to " + file, ex);
        }
    }

    public void saveDefaultConfig() {
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
    }

    public String getMessage(String path) {
        return replaceColors(config.getString(path));
    }

    public List<String> getMessageList(String path) {
        List<String> messages = new ArrayList<>();
        for (String message : config.getStringList(path)) {
            messages.add(replaceColors(message));
        }
        return messages;
    }

    private String replaceColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
