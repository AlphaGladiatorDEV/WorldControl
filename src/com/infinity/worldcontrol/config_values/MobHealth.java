package com.infinity.worldcontrol.config_values;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.infinity.worldcontrol.main.WC;

public class MobHealth implements Listener{
	
	WC wc = WC.instance;
	
	//Peaceful Mobs
	public int batHealth = wc.getConfig().getInt("MobHealth" + ".BAT");
	public int chickenHealth = wc.getConfig().getInt("MobHealth" + ".CHICKEN");
	public int cowHealth = wc.getConfig().getInt("MobHealth" + ".COW");
	public int pigHealth = wc.getConfig().getInt("MobHealth" + ".PIG");
	public int rabbitHealth = wc.getConfig().getInt("MobHealth" + ".RABBIT");
	public int sheepHealth = wc.getConfig().getInt("MobHealth" + ".SHEEP");
	public int squidHealth = wc.getConfig().getInt("MobHealth" + ".SQUID");
	public int villagerHealth = wc.getConfig().getInt("MobHealth" + ".VILLAGER");
	
	//Neutral Mobs
	public int spiderHealth = wc.getConfig().getInt("MobHealth" + ".SPIDER");
	public int endermanHealth = wc.getConfig().getInt("MobHealth" + ".ENDERMAN");
	public int zpmHealth = wc.getConfig().getInt("MobHealth" + ".ZOMBIEPIGMAN");
	
	//Hostile Mobs
	public int blazeHealth = wc.getConfig().getInt("MobHealth" + ".BLAZE");
	public int creeperHealth = wc.getConfig().getInt("MobHealth" + ".CREEPER");
	public int guardHealth = wc.getConfig().getInt("MobHealth" + ".GUARDIAN");
	public int emiteHealth = wc.getConfig().getInt("MobHealth" + ".ENDERMITE");
	public int ghastHealth = wc.getConfig().getInt("MobHealth" + ".GHAST");
	public int sfishHealth = wc.getConfig().getInt("MobHealth" + ".SILVERFISH");
	public int skellyHealth = wc.getConfig().getInt("MobHealth" + ".SKELETON");
	public int witchHealth = wc.getConfig().getInt("MobHealth" + ".WITCH");
	public int zombieHealth = wc.getConfig().getInt("MobHealth" + ".ZOMBIE");
	
	//Tamable Mobs
	public int ocelotHealth = wc.getConfig().getInt("MobHealth" + ".OCELOT");
	
	//Utility Mobs
	public int irongHealth = wc.getConfig().getInt("MobHealth" + ".IRONGOLEM");
	public int snowgHealth = wc.getConfig().getInt("MobHealth" + ".SNOWGOLEM");
	
	//Boss Mobs
	public int dragonHealth = wc.getConfig().getInt("MobHealth" + ".ENDERDRAGON");
	public int witherHealth = wc.getConfig().getInt("MobHealth" + ".WITHER");
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e){
		EntityType type = e.getEntityType();
		LivingEntity entity = e.getEntity();
		if(type == EntityType.BAT){
			healEntity(entity, batHealth);
		}else if(type == EntityType.CHICKEN){
			healEntity(entity, chickenHealth);
		}else if(type == EntityType.COW){
			healEntity(entity, cowHealth);
		}else if(type == EntityType.PIG){
			healEntity(entity, pigHealth);
		}else if(type == EntityType.RABBIT){
			healEntity(entity, rabbitHealth);
		}else if(type == EntityType.SHEEP){
			healEntity(entity, sheepHealth);
		}else if(type == EntityType.SQUID){
			healEntity(entity, squidHealth);
		}else if(type == EntityType.VILLAGER){
			healEntity(entity, villagerHealth);
		}else if(type == EntityType.SPIDER){
			healEntity(entity, spiderHealth);
		}else if(type == EntityType.ENDERMAN){
			healEntity(entity, endermanHealth);
		}else if(type == EntityType.PIG_ZOMBIE){
			healEntity(entity, zpmHealth);
		}else if(type == EntityType.OCELOT){
			healEntity(entity, ocelotHealth);
		}else if(type == EntityType.IRON_GOLEM){
			healEntity(entity, irongHealth);
		}else if(type == EntityType.SNOWMAN){
			healEntity(entity, snowgHealth);
		}else if(type == EntityType.ENDER_DRAGON){
			healEntity(entity, dragonHealth);
		}else if(type == EntityType.WITHER){
			healEntity(entity, witherHealth);
		}else if(type == EntityType.BLAZE){
			healEntity(entity, blazeHealth);
		}else if(type == EntityType.CREEPER){
			healEntity(entity, creeperHealth);
		}else if(type == EntityType.GUARDIAN){
			healEntity(entity, guardHealth);
		}else if(type == EntityType.ENDERMITE){
			healEntity(entity, emiteHealth);
		}else if(type == EntityType.GHAST){
			healEntity(entity, ghastHealth);
		}else if(type == EntityType.SILVERFISH){
			healEntity(entity, sfishHealth);
		}else if(type == EntityType.SKELETON){
			healEntity(entity, skellyHealth);
		}else if(type == EntityType.WITCH){
			healEntity(entity, witchHealth);
		}else if(type == EntityType.ZOMBIE){
			healEntity(entity, zombieHealth);
		}
	}
	
	public void healEntity(LivingEntity e, int d){
		e.setMaxHealth(d);
		e.setHealth(d);
	}

}
