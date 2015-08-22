package com.infinity.worldcontrol.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.infinity.worldcontrol.main.WC;

public class MainCmd implements Listener, CommandExecutor{
	
	String wcs = ChatColor.BLACK + "[" + ChatColor.GREEN + "WorldControl" + ChatColor.BLACK + "]" + ChatColor.RESET + " ";
	List<World> asol = new ArrayList<World>();
	List<World> pvpol = new ArrayList<World>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(wcs + ChatColor.RED + "You must be a player to use this command.");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("wc")){
			if(args.length == 0){
				p.sendMessage(wcs + ChatColor.RED + "Invalid usage. Use: \"/wc usage\" for usage.");
				return true;
			}
			if(args[0].equalsIgnoreCase("save")){
				if(p.hasPermission("wc.save")){
					World sworld = p.getWorld();
					sworld.save();
					p.sendMessage(wcs + ChatColor.GREEN + "The world has been saved to the world file!");
					return true;	
				}else if(p.hasPermission("wc.save")){
					noPermissionMessage(p);
					return true;
				}
			}else if(args[0].equalsIgnoreCase("auto-save")){
				if(p.hasPermission("wc.auto-save")){
					World asworld = p.getWorld();
					if(asol.contains(asworld)){
						asworld.setAutoSave(false);
						p.sendMessage(wcs + ChatColor.RED + "Auto-Save disabled!");
						asol.remove(asworld);
						return true;
					}else if(!(asol.contains(asworld))){
						asworld.setAutoSave(true);
						p.sendMessage(wcs + ChatColor.GREEN + "Auto-Save enabled!");
						asol.add(asworld);
						return true;
					}
				}else if(!p.hasPermission("wc.auto-save")){
					noPermissionMessage(p);
					return true;
				}
			}else if(args[0].equalsIgnoreCase("pvp")){
				if(p.hasPermission("wc.pvp")){
					World pvpworld = p.getWorld();
					if(pvpol.contains(pvpworld)){
						disablePvP(pvpworld, p);
						pvpol.remove(pvpworld);
						return true;
					}else if(!(pvpol.contains(pvpworld))){
						enablePvP(pvpworld, p);
						pvpol.add(pvpworld);
						return true;
					}
				}else if(!p.hasPermission("wc.pvp")){
					noPermissionMessage(p);
					return true;
				}
			}else if(args[0].equalsIgnoreCase("usage")){
				p.sendMessage
 (wcs + ChatColor.GREEN + "Usage: \"/wc save\n"
						+ "auto-save\n"
						+ "pvp\n"
						+ "creload\n"
						+ "difficulty(easy/medium/hard)\n"
						+ "get-max-height\n"
						+ "explode(<x> <y> <z> <power ex.4.0>/<power ex.4.0>)>\n"
						+ "");
				return true;
			}else if(args[0].equalsIgnoreCase("creload")){
				if(p.hasPermission("wc.configreload")){
					WC.instance.saveConfig();
					WC.instance.reloadConfig();
					p.sendMessage(wcs + ChatColor.GREEN + "Config reloaded!");
					return true;
				}else if(!p.hasPermission("wc.configreload")){
					noPermissionMessage(p);
					return true;
				}
			}else if(args[0].equalsIgnoreCase("difficulty")){
				if(p.hasPermission("wc.difficulty")){
					World dworld = p.getWorld();
					if(args.length == 0){
						p.sendMessage(wcs + ChatColor.RED + "You did not put in a difficulty!");
					}else if(args[1].equalsIgnoreCase("peaceful")){
						dworld.setDifficulty(Difficulty.PEACEFUL);
						p.sendMessage(wcs + ChatColor.GREEN + "The world difficulty has been set to " + ChatColor.GOLD + "Peaceful" + ChatColor.GREEN + "!");
						return true;
					}else if(args[1].equalsIgnoreCase("easy")){
						dworld.setDifficulty(Difficulty.EASY);
						p.sendMessage(wcs + ChatColor.GREEN + "The world difficulty has been set to " + ChatColor.GOLD + "Easy" + ChatColor.GREEN + "!");
						return true;
					}else if(args[1].equalsIgnoreCase("normal")){
						dworld.setDifficulty(Difficulty.NORMAL);
						p.sendMessage(wcs + ChatColor.GREEN + "The world difficulty has been set to " + ChatColor.GOLD + "Normal" + ChatColor.GREEN + "!");
						return true;
					}else if(args[1].equalsIgnoreCase("hard")){
						dworld.setDifficulty(Difficulty.PEACEFUL);
						p.sendMessage(wcs + ChatColor.GREEN + "The world difficulty has been set to " + ChatColor.GOLD + "Hard" + ChatColor.GREEN + "!");
						return true;
					}else{
						p.sendMessage(wcs + ChatColor.RED + "Invalid command usage. Please specify a difficulty.");
						return true;
					}
				}else if(!p.hasPermission("wc.difficulty")){
					noPermissionMessage(p);
					return true;
				}
			}else if(args[0].equalsIgnoreCase("get-max-height")){
				if(p.hasPermission("wc.gmh")){
					World gmhworld = p.getWorld();
					int mxht = gmhworld.getMaxHeight();
					p.sendMessage(wcs + ChatColor.GOLD + gmhworld.getName() + "'s" + ChatColor.GREEN + " max build height is " + ChatColor.GOLD + mxht + ChatColor.GREEN + "!");
					return true;
				}else{
					noPermissionMessage(p);
					return true;
				}
			}else if(args[0].equalsIgnoreCase("explode")){
				if(p.hasPermission("wc.explode")){
					World expworld = p.getWorld();
					if(args.length == 0){
						p.sendMessage(wcs + ChatColor.RED + "Missing paramaters. Usage: /wc explode <x> <y> <z> <power ex.4.0> or /wc explode <power ex.4.0>");
						return true;
					}else if(args.length == 1){
						Location ploc = p.getLocation();
						double x = ploc.getBlockX();
						double y = ploc.getBlockY();
						double z = ploc.getBlockZ();
						expworld.createExplosion(x, y, z, Float.parseFloat(args[1]));
						p.sendMessage("" + wcs + ChatColor.GREEN + "Explosion of " + ChatColor.GOLD + args[1] + ChatColor.GREEN + " power has been made at your location!");
						return true;
					}else if(args.length == 4){
						double x = Double.parseDouble(args[1]);
						double y = Double.parseDouble(args[2]);
						double z = Double.parseDouble(args[3]);
						float power = Float.parseFloat(args[4]);
						expworld.createExplosion(x, y, z, power);
						p.sendMessage("" + wcs + ChatColor.GREEN + "Explosion of " + ChatColor.GOLD + args[1] + ChatColor.GREEN + " power has been made at " + ChatColor.GOLD + x + " " + y + " " + z + ChatColor.GREEN + "!");
						return true;
					}else{
						p.sendMessage(wcs + ChatColor.RED + "Invalid Usage. Usage: /wc explode <x> <y> <z> <power ex.4.0> or /wc explode <power ex.4.0>");
						return true;
					}
				}
			}else if(!args[0].isEmpty()){
				p.sendMessage(wcs + ChatColor.RED + "Invalid usage. To see usage, use \"/wc usage\".");
				return true;
			}
		}
		return false;
	}
	
	public void enablePvP(World pvpworld, Player p){
		pvpworld.setPVP(true);
		p.sendMessage(wcs + ChatColor.GREEN + "PvP enabled!");
	}
	public void disablePvP(World pvpworld, Player p){
		pvpworld.setPVP(false);
		p.sendMessage(wcs + ChatColor.RED + "PvP disabled!");
	}
	
	public void noPermissionMessage(Player p){
		p.sendMessage(wcs + ChatColor.RED + "You do not have permission to use this command!");
	}

}
