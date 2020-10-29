package xyz.novaserver.easteregg.item;

import org.bukkit.Material;
import xyz.novaserver.easteregg.Plugin;
import xyz.novaserver.easteregg.config.Config;

public class AmbiguousKey extends CustomItem
{
    private static final String key = "AMBIGUOUS_KEY";
    private static final Material material = Material.TRIPWIRE_HOOK;
    public AmbiguousKey() {
        super(material, key);
        setName(Plugin.instance.getConf().getMessage("key.name"));
        setLore(Plugin.instance.getConf().getMessageList("key.lore"));
    }
}
