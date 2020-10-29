package xyz.novaserver.easteregg.commands.egg;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import xyz.novaserver.easteregg.Plugin;
import xyz.novaserver.easteregg.commands.util.CommandArg;
import xyz.novaserver.easteregg.config.Config;

import java.util.List;

public class Reload extends CommandArg {
    public Reload(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatColor.RED + "You do not have access to this command!");
            return;
        }
        //Reloads the config files
        sender.sendMessage(ChatColor.YELLOW + "The configuration file is reloading...");
        plugin.loadConfig();
        sender.sendMessage(ChatColor.GREEN + "The configuration file is done reloading.");
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return null;
    }

    @Override
    public String getPermission() {
        return Config.permAdmin;
    }
}
