/**
The MIT License (MIT)

Copyright (c) 2016 Justin "JustBru00" Brubaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
**/ 
package com.computerdude.revenger.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.computerdude.revenger.Main;


public class Messager {

	/**
	 * 
	 * @param uncolored The text you want colored using the '&' color code.
	 * @return The colored text as a {@link String}
	 */
	public static String color(String uncolored){
		return ChatColor.translateAlternateColorCodes('&', uncolored);		
	}
	
	/**
	 * 
	 * @param msg The message you want to send to the console.
	 */
	public static void msgConsole(String msg) {			
		if (Main.console != null) {
		Main.console.sendMessage(Main.PREFIX + Messager.color(msg));		
		} else {
			Main.log.info(ChatColor.stripColor(Messager.color(msg)));
		}
	}
	
	
	/** 
	 * @param player The player to send the message to.
	 * @param msg The uncolored message you want sent.
	 */
	public static void msgPlayer(String msg, Player player) {	
		player.sendMessage(Main.PREFIX + Messager.color(msg));
	}	
	
	/**
	 * @deprecated Use Messager.msgPlayer(msg, player) instead;
	 * @param player The player to send the message to.
	 * @param msg The uncolored message you want sent.
	 */
	public static void msgPlayer(Player player, String msg) {	
		player.sendMessage(Main.PREFIX + Messager.color(msg));
	}	
	
	/**
	 * 
	 * @param msg The message you want to send.
	 * @param sender the {@link CommandSender} you want to send the message to.
	 */
	public static void msgSender(String msg, CommandSender sender) {	
		sender.sendMessage(Main.PREFIX + Messager.color(msg));
	}	
}
