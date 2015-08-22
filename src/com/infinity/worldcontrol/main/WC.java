package com.infinity.worldcontrol.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.infinity.worldcontrol.commands.MainCmd;
import com.infinity.worldcontrol.config_values.ExplosionListener;
import com.infinity.worldcontrol.config_values.FireListener;
import com.infinity.worldcontrol.config_values.MobHealth;

public class WC extends JavaPlugin{
	
	public static WC instance;
	
	public static WC getInstance(){
		return instance;
	}
	
	public void registerCommands(){
		getCommand("wc").setExecutor(new MainCmd());
	}
	
	public void registerClasses(){
		PluginManager pm = Bukkit.getPluginManager();
		
		//Commands
		pm.registerEvents(new MainCmd(), this);
		
		//Config Classes
		pm.registerEvents(new ExplosionListener(), this);
		pm.registerEvents(new FireListener(), this);
		pm.registerEvents(new MobHealth(), this);
		
	}
	
	public void consoleBroadcast(String s){
		Bukkit.getServer().getLogger().info(s);
	}
	
	PluginDescriptionFile pdf = getDescription();
	
	@Override
	public void onEnable(){
		consoleBroadcast("[WorldControl] WorldControl v" + pdf.getVersion() + " has been enabled!");
		instance = this;
		registerClasses();
		registerCommands();
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable(){
		consoleBroadcast("[WorldControl] WorldControl v" + pdf.getVersion() + " has been disabled!");
		instance = null;
	}

}
