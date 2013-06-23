package com.droppages.Skepter;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class AntiSlimeListener
	implements Listener
{
		
    public AntiSlimeListener(BoneMeal4Mobs plugin)
    {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }


@EventHandler
	public void PlayerInteract(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if(entity instanceof Slime)
		{
			if(!(entity instanceof MagmaCube))
			{
			if(player.getItemInHand().getType() == Material.COAL && player.getItemInHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 1)
			{
				if(player.hasPermission("BoneMeal4Mobs.antislime") || player.isOp())
					{
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						if(player.getItemInHand().getAmount() == 1)
						{
							player.setItemInHand(null);
						}
						Location entityloc = entity.getLocation();
						Entity e = entity.getWorld().spawnEntity(entityloc, EntityType.SLIME);
						Slime s = (Slime) e;
						s.setSize(((Slime) entity).getSize() / 2);
						entity.remove();
						player.sendMessage(ChatColor.GOLD + "[BoneMeal4Mobs] " + ChatColor.AQUA + "You antibonemealed yourself a Slime!");
					}			
			}
		}
	}
}}