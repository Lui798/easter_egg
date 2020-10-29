package xyz.novaserver.easteregg.config;

import org.bukkit.plugin.java.JavaPlugin;

public class Config extends ConfigFile {
    private static final String fileName = "config.yml";

    public static final String permAdmin = "easteregg.admin";

    public Config(JavaPlugin plugin) {
        super(plugin, fileName);

    }
}
