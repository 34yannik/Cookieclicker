package de.yannik.cookieclicker.handler;

import de.yannik.cookieclicker.Cookieclicker;
import de.yannik.cookieclicker.enums.Items;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class ItemHandler {

    public static Cookieclicker plugin;

    public ItemHandler(Cookieclicker plugin) {
        ItemHandler.plugin = plugin;
    }

    /**
     * Erstellt ein ItemStack basierend auf einem Items-Enum und fügt UUID und keyName als Metadata hinzu. (Dupe-Schutz)
     *
     * @param item Der Items-Enum.
     * @return Der passende ItemStack zum Item.
     */
    public static ItemStack createItem(Items item) {
        ItemStack stack = new ItemStack(item.getMaterial());
        ItemMeta meta = stack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(item.getName());

            UUID itemUUID = UUID.randomUUID();

            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(new NamespacedKey(plugin, "itemUUID"), PersistentDataType.STRING, itemUUID.toString());
            container.set(new NamespacedKey(plugin, "keyName"), PersistentDataType.STRING, item.getKeyName());

            meta.setLore(Arrays.asList(
                    " ",
                    item.getDescription()
            ));

            stack.setItemMeta(meta);
        }

        return stack;
    }

    /**
     * Gibt einem Spieler ein Item basierend auf einem Items-Enum.
     */
    public static void giveItemToPlayer(Player player, Items item, int amount) {
        ItemStack stack = createItem(item);
        stack.setAmount(amount);

        Map<Integer, ItemStack> leftovers = player.getInventory().addItem(stack);

        if (!leftovers.isEmpty()) {
            player.sendMessage("§cDein Inventar ist voll! Das Item konnte nicht hinzugefügt werden.");

            for (ItemStack leftover : leftovers.values()) {
                player.getWorld().dropItem(player.getLocation(), leftover);
            }
        }
    }


    /**
     * Gibt das Items-Enum basierend auf dem keyName zurück.
     *
     * @param keyName Der Key-Name des Items.
     * @return Das passende Items-Enum oder null, wenn kein Item mit diesem keyName existiert.
     */
    public static Items getItemByKeyName(String keyName) {
        // Iteriere über alle Items im Enum
        for (Items item : Items.values()) {
            // Vergleiche den argsName des Items mit dem gesuchten argsName
            if (item.getKeyName().equals(keyName)) {
                return item;
            }
        }
        return null;  // Kein Item mit diesem argsName gefunden
    }

}
