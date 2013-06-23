package com.droppages.Skepter;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class MagmaCubeListener
	implements Listener
{
		
    public MagmaCubeListener(BoneMeal4Mobs plugin)
    {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }


@EventHandler
	public void PlayerInteract(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if(entity instanceof MagmaCube)
		{
			if(player.getItemInHand().getType() == Material.INK_SACK && player.getItemInHand().getDurability() == 15)
				if(player.hasPermission("BoneMeal4Mobs.magmacube") || player.isOp()){
			{
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
				if(player.getItemInHand().getAmount() == 1)
				{
					player.setItemInHand(null);
				}
				Location entityloc = entity.getLocation();
				Entity e = entity.getWorld().spawnEntity(entityloc, EntityType.MAGMA_CUBE);
				MagmaCube m = (MagmaCube) e;
				m.setSize(((MagmaCube) entity).getSize() * 2);
				entity.remove();
				player.sendMessage(ChatColor.GOLD + "[BoneMeal4Mobs] " + ChatColor.AQUA + "You bonemealed yourself a Massive Magma Cube!");
			}}
		}
	}
}