package com.computerdude.revenger;

import java.util.Map.Entry;

import org.bukkit.Bukkit;

public class TopRevengerHandler {

	/**
	 * @author Justin Brubaker
	 */
	
	public static String calculateTopRevenger() {
		
		int highestScore = -1;
		String highestPlayerName = "";
		
		for(Entry<String, Integer> item : Main.topRevengers.entrySet()) {
			if (item.getValue() > highestScore) {
				highestScore = item.getValue();
				highestPlayerName = Bukkit.getPlayer(item.getKey()).getName();
			}
		}	
		
		
		return "The player with the most revenge kills is: \n" + highestPlayerName + ": " + highestScore;
	}
	
}
