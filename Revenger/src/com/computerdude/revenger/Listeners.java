package com.computerdude.revenger;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.milkbowl.vault.economy.EconomyResponse;

public class Listeners implements Listener{
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		if (player.getKiller() == null) {
			return;
		}
		
		
		
		Main.hashmap.put(player, player.getKiller());
		if (Main.hashmap.containsValue(player)) {
			
			@SuppressWarnings("deprecation")
			EconomyResponse r = Main.econ.depositPlayer(player.getName(), Main.getInstance().getConfig().getInt("revenge-worth"));
			EconomyResponse rr = Main.econ.withdrawPlayer(player.getKiller(), Main.getInstance().getConfig().getInt("revenge-worth"));
			
						
			if (!Main.topRevengers.containsKey(player.getKiller().getUniqueId().toString()))  { // Check if the data exists in the config.
				Main.topRevengers.put(player.getKiller().getUniqueId().toString(), 1);
				Main.getInstance().getConfig().createSection("revengers-data", Main.topRevengers);
				Main.getInstance().saveConfig();
				Main.topRevengers = (HashMap<String, Integer>) Main.getInstance().getConfig().getConfigurationSection("revengers-data");
			} else {
				Main.topRevengers.put(player.getKiller().getUniqueId().toString(), Main.topRevengers.get(player.getKiller().getUniqueId().toString()) + 1);
				Main.getInstance().getConfig().createSection("revengers-data", Main.topRevengers);
				Main.getInstance().saveConfig();
				Main.topRevengers = (HashMap<String, Integer>) Main.getInstance().getConfig().getConfigurationSection("revengers-data");
			}
			
			
			if(r.transactionSuccess()) {				
				player.getKiller().sendMessage(Main.color("&aYou just got " + Main.getInstance().getConfig().getInt("revenge-worth")));
			}
			else {
				player.getKiller().sendMessage(Main.color("&cAn Error Occored when giving revenge profit. " + r.errorMessage));
			}

			
			
			if(rr.transactionSuccess()) {
				player.sendMessage(Main.color("&4You just lost " + Main.getInstance().getConfig().getInt("revenge-worth")));
			}
			else {
				player.getKiller().sendMessage(Main.color("&cAn Error Occored when loosing revenge profit. " + rr.errorMessage));
			}
			return;
		}
	}
	
}
