package com.computerdude.revenger.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.computerdude.revenger.Main;
import com.computerdude.revenger.TopRevengerHandler;
import com.computerdude.revenger.utils.Messager;

public class RevengerCommand implements CommandExecutor {

	/**
	 * @author Justin Brubaker
	 */
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if (command.getName().equalsIgnoreCase("revenger")) {
			if (sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if (player.hasPermission("revenger.revenger")) {
					
					if (args.length == 0) { // No args
						Messager.msgPlayer("&4&lRevenger v1.0 by " + Main.AUTHORS, player);
						Messager.msgPlayer("&e/revenger info - Shows information about this plugin", player);
						Messager.msgPlayer("&e/revenger revenge - Shows the player that Owes you", player);
						Messager.msgPlayer("&e/revenger top - Lists top Revengeful players", player);
						Messager.msgPlayer("&e/revenger reload - Reloads plugin", player);
						return true;
					}
					
					if (args.length == 1) {
						
						if (args[0].equalsIgnoreCase("revenge")) { // /revenger revenge
							
							if (Main.hashmap.containsKey(player)) {
								Messager.msgPlayer("&e&l" + Main.hashmap.get(player).getName() + " &4&l Owes you!", player);
								return true;
							} else {
								Messager.msgPlayer("&cNo one has killed you recently.", player);
								return true;
							}
							
						} else if (args[0].equalsIgnoreCase("top")) { // /revenger top
							
							if (player.hasPermission("revenger.top")) {
								Messager.msgPlayer("&e" + TopRevengerHandler.calculateTopRevenger(), player);
							return true;
							} else {
								Messager.msgPlayer("&cNo permission.", player);
								return true;
							}
							
						} else if (args[0].equalsIgnoreCase("info")) { // /revenger info
							Messager.msgPlayer("&4&lRevenger Info", player);
							Messager.msgPlayer("&eWhen you kill a player, that player has a temporarry bounty on you.", player);
							Messager.msgPlayer("&eIf they kill you before dying again, they steal &4&l " + Main.getInstance().getConfig().getString("revenge-worth") + " &e from you!", player);
							Messager.msgPlayer("&eThe top revenger is in /revenger top", player);
							return true;
						} else if (args[0].equalsIgnoreCase("reload")) {// /revenger reload
							if (player.hasPermission("revenger.reload")) {
								Main.getInstance().reloadConfig();
								Messager.msgPlayer("&aThe config has been reloaded.", player);			
								return true;
							} else {
								Messager.msgPlayer("&cSorry you don't have permission.", player);
								return true;
							}
							
						}
						
					} else {
						Messager.msgPlayer("&cSorry too many args. Do /revenger to see vaild args.", player);
						return true;
					}					
				} else {
					Messager.msgPlayer("&cSorry you don't have permission.", player);
					return true;
				}
			} else {
				Messager.msgSender("&cSorry only players can use that command. #BlameComputerDude", sender);
				return true;
			}
		}
		
		return false;
	}

}
