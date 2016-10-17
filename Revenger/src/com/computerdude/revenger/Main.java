package com.computerdude.revenger;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin{
	
	//PLAYER WHO DIED, PLAYER WHO KILLED
	public static HashMap<Player, Player> hashmap = new HashMap<Player, Player>();
	private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
	public static Main plugin;
    public static Chat chat = null;
   // public HashMap<UUID, Integer> TopList = new HashMap<String, Integer>();
	
	@Override
	public void onEnable(){
		PluginManager pl = Bukkit.getServer().getPluginManager();
		pl.registerEvents(new Listeners(), this);
		
		 plugin = this;
		
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            saveDefaultConfig();
            return;
           
        }
        setupPermissions();
        setupChat();
	}
	

	@Override
	public void onDisable(){
	 plugin = null;
	}
	
	/** 
	  * @param uncoloredtext | Text to Color
	  * @return | Returns Text Colored
	  */
	 public static String color(String uncoloredtext) {
	  return ChatColor.translateAlternateColorCodes('&', uncoloredtext);
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
	 
	    private boolean setupChat() {
	        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
	        chat = rsp.getProvider();
	        return chat != null;
	    }

	    private boolean setupPermissions() {
	        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
	        perms = rsp.getProvider();
	        return perms != null;
	    }
	    
		@Override
		public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {	
			
			Player player = (Player) sender;
			
			if (command.getName().equalsIgnoreCase("revenger")) {	
				if (sender.hasPermission("revenger.revenger")){
				if (args.length == 0) {
					player.sendMessage(color("&4&lRevenger v1.0 by ComputerDude"));
					player.sendMessage(color("&e/revenger info - Shows information about this plugin"));
					player.sendMessage(color("&e/revenger revenge - Shows the player that Owes you"));
					player.sendMessage(color("&e/revenger top - Lists top Revengeful players"));
					player.sendMessage(color("&e/revenger reload - Reloads plugin"));

					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("revenge")) {
						if (hashmap.containsKey(player)){
							player.sendMessage(color("&e&l" + hashmap.get(player).getName() + "&4&lOwes You!"));
						return true;
						}
						else {
							player.sendMessage(color("&cNo one has killed you recently."));
							return true;
						}
					} else if (args[0].equalsIgnoreCase("top")) { 
						if (sender.hasPermission("revenger.top")) {
						player.sendMessage(color("some txt here"));	}		
						else { 
						player.sendMessage(color("&4Nope!"));
						return true; }
					} else if (args[0].equalsIgnoreCase("info")) { 
						player.sendMessage(color("&4&lRevenger Info"));
						player.sendMessage(color("&eWhen you kill a player, that player has a temporarry bounty on you."));
						player.sendMessage(color("&eIf they kill you before dying again, they steal &4&l" + getConfig().getString("revenge-worth") + "&e from you!"));
						player.sendMessage(color("&eTop revengers show up on /revenger top"));
						return true;
					} else if (args[0].equalsIgnoreCase("reload")) {
						if (sender.hasPermission("revenger.reload")) {
							player.sendMessage(color("&ePlugin Reloaded."));
			                reloadConfig();
			                return true;
						} else {
							player.sendMessage(("&4Nope!"));
							return true;
						}
						
				} else {
						player.sendMessage(color("&4Error - /revenger (args)"));
					    return true;
					}				
				}
				}
			}
			return false;
		}
		
	 //   public boolean onTopListCommand(CommandSender sender, Command command, String label, String[] args) {
	        
	   //     TopList.put("uuidhere1", 36);
	    //    TopList.put("uuidhere2", 36);
	   //     TopList.put("uuidhere3", 2);
	   //     TopList.put("uuidhere4", 36);
	   //     TopList.put("uuidhere5", 3);
	   //     TopList.put("uuidhere6", 36);
	  //      TopList.put("uuidhere7", 5);
	  //      TopList.put("uuidhere8", 36);
	        
	    //    getConfig().set("revenger-data", TopList);
	  //      saveConfig();
	        
	   //     sender.sendMessage("Saved map to config.");
	        
	      //  @SuppressWarnings("unchecked")
	     //   HashMap<String, Integer> getStuff = (HashMap<String, Integer>) getConfig().get("revenger-data");
	        
	     //   if (getStuff == null) {
	    //        sender.sendMessage("NULL GETSTUFF");
	   //         return true;
	     //   }
	        
	     //   for(Entry<UUID, Integer> entry : getStuff.entrySet()) {
	      //      String key = entry.getKey();
	      //      int value = entry.getValue();
	        
	       //     sender.sendMessage("Key: " + key + " Data: " + value);
	      //  }        
	        
	    
	        
	        //getStuff.forEach((k, v) -> sender.sendMessage("key: " + k + " Value: " + v)); // JAVA 8
	 
//}
}
