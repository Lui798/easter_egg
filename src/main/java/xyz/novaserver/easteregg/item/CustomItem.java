package xyz.novaserver.easteregg.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.novaserver.easteregg.Plugin;

import java.util.List;

public class CustomItem extends ItemStack {
    private final NamespacedKey key;

    public CustomItem(Material material, String key) {
        this(material, 1, key);
    }

    public CustomItem(Material material, int amount, String key) {
        super(material, amount);
        this.key = new NamespacedKey(Plugin.getPlugin(), key);
        ItemMeta itemMeta = getItemMeta();
        itemMeta.getPersistentDataContainer().set(this.key, PersistentDataType.STRING, key);
        setItemMeta(itemMeta);
    }

    public NamespacedKey getKey() {
        return key;
    }

    public void setName(String name) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.setDisplayName(name);
        setItemMeta(itemMeta);
    }

    public void setLore(List<String> lore) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

    public boolean equalsItem(ItemStack item) {
        if (item != null && item.getType() == this.getType()) {
            if (!item.getItemMeta().getPersistentDataContainer().isEmpty()) {

                PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();
                PersistentDataContainer thisContainer = this.getItemMeta().getPersistentDataContainer();

                if (dataContainer.has(key, PersistentDataType.STRING)) {
                    if (dataContainer.get(key, PersistentDataType.STRING).equals(thisContainer.get(key, PersistentDataType.STRING))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
