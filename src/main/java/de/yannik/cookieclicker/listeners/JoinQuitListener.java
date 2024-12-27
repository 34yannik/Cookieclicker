package de.yannik.cookieclicker.listeners;

import de.yannik.cookieclicker.Cookieclicker;
import de.yannik.cookieclicker.enums.Items;
import de.yannik.cookieclicker.handler.ItemHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class JoinQuitListener implements Listener {

    @Deprecated
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        FileConfiguration config = Cookieclicker.getPlugin().getConfig();
        Player player = event.getPlayer();

        if(config.getBoolean("togglePlayerJoinMsg")){
            event.setJoinMessage(config.getString("playerJoinMsg").replace("%player%", player.getName()));
        } else {
            event.setJoinMessage(null);
        }

        if(player.getInventory().getItem(8) == null || !Objects.requireNonNull(player.getInventory().getItem(8)).getItemMeta().getDisplayName().equals(Items.MENU_ITEM.getName()))
            event.getPlayer().getInventory().setItem(8, ItemHandler.createItem(Items.MENU_ITEM));

    }

    @Deprecated
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        FileConfiguration config = Cookieclicker.getPlugin().getConfig();
        Player player = event.getPlayer();

        if(config.getBoolean("togglePlayerQuitMsg")){
            event.setQuitMessage(config.getString("playerQuitMsg").replace("%player%", player.getName()));
        } else {
            event.setQuitMessage(null);
        }
    }
}
