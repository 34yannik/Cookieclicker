package de.yannik.cookieclicker;

import de.yannik.cookieclicker.handler.ItemHandler;
import de.yannik.cookieclicker.listeners.ItemUseListener;
import de.yannik.cookieclicker.listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public final class Cookieclicker extends JavaPlugin {

    public static Cookieclicker plugin;

    /*
        Public Variables
     */

    public static String prefix;
    public static String syntaxError;
    public static String noPermission;
    public static String noPlayerFound;
    public static String onlyPlayerCmd;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();

        registerConfigSettings();
        registerCommands();
        registerEvents();

        ItemHandler itemHandler = new ItemHandler(plugin);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Cookieclicker getPlugin() {
        return plugin;
    }

    public void registerConfigSettings(){
        prefix = plugin.getConfig().getString("pluginPrefix");
        syntaxError = plugin.getConfig().getString("syntaxErrorMsg");
        noPermission = plugin.getConfig().getString("noPermissionMsg");
        noPlayerFound = plugin.getConfig().getString("noPlayerFoundMsg");
        onlyPlayerCmd = plugin.getConfig().getString("onlyPlayerCmdMsg");
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemUseListener(plugin), this);
    }

    public void registerCommands(){

    }
}
