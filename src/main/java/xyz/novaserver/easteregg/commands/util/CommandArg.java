package xyz.novaserver.easteregg.commands.util;

import org.bukkit.command.CommandSender;
import xyz.novaserver.easteregg.Plugin;

import java.util.List;

public abstract class CommandArg {
  protected final Plugin plugin;


  public CommandArg(Plugin plugin) {
    this.plugin = plugin;
  }

  public abstract void execute(CommandSender sender, String[] args);

  public abstract List<String> tabComplete(String[] args);

  public abstract String getPermission();
}