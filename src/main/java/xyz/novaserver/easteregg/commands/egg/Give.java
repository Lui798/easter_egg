package xyz.novaserver.easteregg.commands.egg;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import xyz.novaserver.easteregg.Plugin;
import xyz.novaserver.easteregg.commands.util.CommandArg;
import xyz.novaserver.easteregg.config.Config;
import xyz.novaserver.easteregg.item.AmbiguousKey;

import java.util.ArrayList;
import java.util.List;

public class Give extends CommandArg {
    public Give(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player;
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatColor.RED + "You do not have access to this command!");
            return;
        }
        if(args.length == 1){
            player = plugin.getServer().getPlayer(sender.getName());
        }
        else if(args.length == 2){
            if(!plugin.getServer().getOnlinePlayers().contains(args[1])){
                return;
            }
            player = plugin.getServer().getPlayer(args[1]);
        }
        else{
            return;
        }
        player.getInventory().addItem(new AmbiguousKey());
    }

    @Override
    public List<String> tabComplete(String[] args) {
        final List<String> completions = new ArrayList<>();
        final List<String> options = new ArrayList<>(onlinePlayerNames());

        if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], options, completions);
        }

        return completions;
    }

    @Override
    public String getPermission() {
        return Config.permAdmin;
    }

    private List<String> onlinePlayerNames(){
        List<String> names = new ArrayList<>();
        for(Player player : plugin.getServer().getOnlinePlayers()){
            names.add(player.getName());
        }
        return names;
    }
}
