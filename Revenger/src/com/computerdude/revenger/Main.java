package com.computerdude.revenger;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.computerdude.revenger.commands.RevengerCommand;
import com.computerdude.revenger.utils.Messager;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {
	
	//PLAYER WHO DIED, PLAYER WHO KILLED
	public static HashMap<Player, Player> hashmap = new HashMap<Player, Player>(); // For revenger revenger
	public static HashMap<String, Integer> topRevengers = new HashMap<String, Integer>(); // For top revengers command.
    public static Economy econ = null; // For Economy
    public static Permission perms = null; // For permissions additions
	public static Main plugin; // For #getInstance()
    public static Chat chat = null; // For Chat Stuff
    public static String AUTHORS; // The authors set in #onEnable()
    public static final String PREFIX = Messager.color("&8[&4Revenger&8] &6"); // For all messages
    public static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender(); // For console messages
    public static Logger log = Bukkit.getLogger(); // For a weird glitch on some servers.
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable(){
		plugin = this; // Changed location
		
		// Set authors to the String var
		for (String item : this.getDescription().getAuthors()) {
			AUTHORS = AUTHORS + item + ", ";
		}
		
		PluginManager pl = Bukkit.getServer().getPluginManager();
		pl.registerEvents(new Listeners(), this);
		
		if (getConfig().getConfigurationSection("revengers-data") == null) {
			this.getConfig().createSection("revengers-data", topRevengers);
			saveConfig();
			topRevengers = (HashMap<String, Integer>) getConfig().getConfigurationSection("revengers-data");
		} else {
			topRevengers = (HashMap<String, Integer>) getConfig().getConfigurationSection("revengers-data");
		}
		 
		
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            saveDefaultConfig();
            return;
           
        }     
        
        // Set command executors
        getCommand("revenger").setExecutor(new RevengerCommand());
        
	}
	

	@Override
	public void onDisable(){
	 plugin = null;
	}
	
	/** 
	 * @deprecated Use Messager.color() instead.
	  * @param uncoloredtext | Text to Color
	  * @return | Returns Text Colored
	  */
	 public static String color(String uncoloredtext) {
	  return Messager.color(uncoloredtext);
	 }
	 

	
	    private boolean setupEconomy() {
	        if (getServer().getPluginManager().getPlugin("Vault") == null) {
	            return false;
	        }
	        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	        if (rsp == null) {
	            return false;
	        }
	        econ = rsp.getProvider();
	        return econ != null;
	    }
	    
		public static Main getInstance(){
			return plugin;
		}
}
