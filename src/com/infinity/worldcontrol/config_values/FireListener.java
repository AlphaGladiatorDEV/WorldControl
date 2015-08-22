package com.infinity.worldcontrol.config_values;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

import com.infinity.worldcontrol.main.WC;

public class FireListener implements Listener{
	
	boolean cfb = WC.instance.getConfig().getBoolean("Fire");

	@EventHandler
	public void igniteEvent(BlockIgniteEvent e){
		if(cfb == true){
			e.setCancelled(false);
		}else if(cfb == false){
			e.setCancelled(true);
		}
	}
}
