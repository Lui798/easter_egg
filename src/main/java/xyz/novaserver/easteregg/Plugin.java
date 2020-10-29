package xyz.novaserver.easteregg;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.novaserver.easteregg.commands.CommandEgg;
import xyz.novaserver.easteregg.config.Config;

public class Plugin extends JavaPlugin
{
    public static Plugin instance;
    public Config config;
    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        this.getCommand("egg").setExecutor(new CommandEgg(this));
    }

    public static Plugin getPlugin() {
        return instance;
    }

    public void loadConfig() {
        config = new Config(this);
    }

    public Config getConf() {
        return config;
    }
}
