package xyz.novaserver.easteregg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import xyz.novaserver.easteregg.Plugin;
import xyz.novaserver.easteregg.commands.egg.Give;
import xyz.novaserver.easteregg.commands.egg.Reload;
import xyz.novaserver.easteregg.commands.util.CommandArg;

import java.util.*;

public class CommandEgg implements TabExecutor {
    private final HashMap<String, CommandArg> commands = new HashMap<>();

    public CommandEgg(Plugin plugin) {
        commands.put("reload", new Reload(plugin));
        commands.put("give", new Give(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String mainArg = args[0];
            if (commands.containsKey(mainArg)) {
                commands.get(mainArg).execute(sender, args);
            }
            else {
                //Print help menu
                commands.get("help").execute(sender, args);
            }
        }
        else {
            //Print help menu
            commands.get("help").execute(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length > 0) {
            //List of main args
            if (args.length == 1 && !commands.containsKey(args[0])) {
                HashMap<String, CommandArg> cmds = new HashMap<>(commands);
                //Remove options player doesn't have perms for
                for (Map.Entry<String, CommandArg> cmd : commands.entrySet()) {
                    if (cmd.getValue().getPermission() != null && !sender.hasPermission(cmd.getValue().getPermission())) {
                        cmds.remove(cmd.getKey(), cmd.getValue());
                    }
                }
                StringUtil.copyPartialMatches(args[0], cmds.keySet(), completions);
            }
            else if (args.length != 1 && commands.containsKey(args[0])) {
                if (commands.get(args[0]).tabComplete(args) != null) {
                    completions.addAll(commands.get(args[0]).tabComplete(args));
                }
            }
        }

        Collections.sort(completions);
        return completions;
    }
}
