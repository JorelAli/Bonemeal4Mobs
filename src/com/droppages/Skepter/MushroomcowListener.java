package com.droppages.Skepter;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class MushroomcowListener
	implements Listener
{
		
    public MushroomcowListener(BoneMeal4Mobs plugin)
    {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }


@EventHandler
	public void PlayerInteract(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if(entity instanceof MushroomCow)
		{
			if(!(((MushroomCow) entity).isAdult()))
			{
				if(player.getItemInHand().getType() == Material.INK_SACK && player.getItemInHand().getDurability() == 15)
					if(player.hasPermission("BoneMeal4Mobs.mooshroom") || player.isOp()){
				{
					player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
					if(player.getItemInHand().getAmount() == 1)
					{
						player.setItemInHand(null);
					}
					Location entityloc = entity.getLocation();
					entity.getWorld().spawnEntity(entityloc, EntityType.MUSHROOM_COW);
					entity.remove();
					player.sendMessage(ChatColor.GOLD + "[BoneMeal4Mobs] " + ChatColor.AQUA + "You bonemealed yourself a Mooshroom!");
				}}
			}
		}
	}
}