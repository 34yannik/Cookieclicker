package de.yannik.cookieclicker.listeners;

import de.yannik.cookieclicker.Cookieclicker;
import de.yannik.cookieclicker.enums.Items;
import de.yannik.cookieclicker.handler.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemUseListener implements Listener {

    private final Cookieclicker plugin;

    public ItemUseListener(Cookieclicker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        ItemStack item = event.getItem();

        if (item == null || !item.hasItemMeta()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "itemUUID"), PersistentDataType.STRING)) {
            // Die UUID und argsName aus den Metadaten holen
            String itemUUIDStr = meta.getPersistentDataContainer().get(new NamespacedKey(plugin, "itemUUID"), PersistentDataType.STRING);
            UUID itemUUID = UUID.fromString(itemUUIDStr);
            String keyName = meta.getPersistentDataContainer().get(new NamespacedKey(plugin, "keyName"), PersistentDataType.STRING);

            // Hier kannst du nach der UUID oder dem argsName entscheiden, was mit dem Item gemacht wird
            Player player = event.getPlayer();
            handleItemUse(player, Objects.requireNonNull(ItemHandler.getItemByKeyName(keyName)), item, event);
        }
    }

    /**
     * Behandelt die Aktion, wenn ein registriertes Item benutzt wird.
     */
    @Deprecated
    private void handleItemUse(Player player, Items item, ItemStack itemstack, PlayerInteractEvent event) {

        // Logik für jede Crate
        switch (item) {
            case MENU_ITEM:
                Inventory inventory = Bukkit.createInventory(null, 5*9, "§6Cookie-Fabrik");
                event.setCancelled(true);
                player.openInventory(inventory);

                for(int i = 0; i <= inventory.getSize(); i++){
                    ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta glassMeta = glass.getItemMeta();
                    glassMeta.setDisplayName("");
                    glass.setItemMeta(glassMeta);

                    inventory.setItem(i, glass);
                }



            default:
                event.setCancelled(true);
                break;
        }
    }

    /**
     * Entfernt das Item aus der Hand des Spielers nach Benutzung (optional).
     */
    private void removeItem(Player player, ItemStack item) {
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            player.getInventory().removeItem(item);
        }
    }
}
