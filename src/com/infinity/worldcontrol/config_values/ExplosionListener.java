package com.infinity.worldcontrol.config_values;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.infinity.worldcontrol.main.WC;

public class ExplosionListener implements Listener{
	
	public boolean es = WC.instance.getConfig().getBoolean("Explosions");
	
	@EventHandler
	public void boomBoomEvent(EntityExplodeEvent e){
		if(es == true){
			e.setCancelled(false);
		}else if(es == false){
			e.setCancelled(true);
		}
	}

}
