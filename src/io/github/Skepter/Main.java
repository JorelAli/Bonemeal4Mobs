package io.github.Skepter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	private static JavaPlugin instance;

	@Override
	public void onEnable() {
		instance = this;
		
		ItemStack is = new ItemStack(Material.INK_SACK, 1);
		is.setDurability((short) 15);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Anti-Bonemeal");
		is.setItemMeta(im);
		ShapedRecipe recipe = new ShapedRecipe(is);
		recipe.shape("A", "B");
		recipe.setIngredient('A', is.getData());
		recipe.setIngredient('B', Material.COAL);
		getServer().addRecipe(recipe);
		
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
	}

	public static JavaPlugin instance() {
		return instance;
	}
	
	private boolean hp(Player player, String perm) {
		if (player.isOp()) {
			return true;
		}
		if (player.hasPermission("BoneMeal4Mobs." + perm)) {
			return true;
		} else {
			return false;
		}
	}

	private void ti(Player player) {
		player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
		if (player.getItemInHand().getAmount() == 1) {
			player.setItemInHand(null);
		}
	}

	private boolean holdingABonemeal(Player player) {
		if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().getDisplayName() == "Anti-Bonemeal") {
			return true;
		} else {
			return false;
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		Location l = entity.getLocation();
		World w = entity.getWorld();
		if (player.getItemInHand().getType() == Material.INK_SACK && player.getItemInHand().getDurability() == 15) {
			switch (entity.getType()) {
			case CAVE_SPIDER:
				if (hp(player, "spider") && !holdingABonemeal(player)) {
					ti(player);
					entity.remove();
					w.spawnEntity(l, EntityType.SPIDER);
				}
				break;
			case CHICKEN:
				if (((Chicken) entity).isAdult() && hp(player, "antichicken") && holdingABonemeal(player)) {
					((Chicken) entity).setBaby();
					ti(player);
				} else if (!((Chicken) entity).isAdult() && hp(player, "chicken") && !holdingABonemeal(player)) {
					((Chicken) entity).setAdult();
					ti(player);
				}
				break;
			case COW:
				if (((Cow) entity).isAdult() && hp(player, "anticow") && holdingABonemeal(player)) {
					((Cow) entity).setBaby();
					ti(player);
				} else if (!((Cow) entity).isAdult() && hp(player, "cow") && !holdingABonemeal(player)) {
					((Cow) entity).setAdult();
					ti(player);
				}
				break;
			case ENDERMAN:
				// for Endermite!
				break;
			case GIANT:
				if (hp(player, "antizombie") && holdingABonemeal(player)) {
					entity.remove();
					w.spawnEntity(l, EntityType.ZOMBIE);
					ti(player);
				}
				break;
			case HORSE:
				if (((Horse) entity).isAdult() && hp(player, "antihorse") && holdingABonemeal(player)) {
					((Horse) entity).setBaby();
					ti(player);
				} else if (!((Horse) entity).isAdult() && hp(player, "horse") && !holdingABonemeal(player)) {
					((Horse) entity).setAdult();
					ti(player);
				}
				break;
			case MAGMA_CUBE:
				if (hp(player, "magmacube") && !holdingABonemeal(player)) {
					((MagmaCube) entity).setSize(((MagmaCube) entity).getSize() * 2);
					ti(player);
				} else if (hp(player, "antimagmacube") && holdingABonemeal(player)) {
					((MagmaCube) entity).setSize(((MagmaCube) entity).getSize() / 2);
					ti(player);
				}
				break;
			case MUSHROOM_COW:
				if (((MushroomCow) entity).isAdult() && hp(player, "antimooshroom") && holdingABonemeal(player)) {
					((MushroomCow) entity).setBaby();
					ti(player);
				} else if (!((MushroomCow) entity).isAdult() && hp(player, "mooshroom") && !holdingABonemeal(player)) {
					((MushroomCow) entity).setAdult();
					ti(player);
				}
				break;
			case OCELOT:
				if (((Ocelot) entity).isAdult() && hp(player, "antiocelot") && holdingABonemeal(player)) {
					((Ocelot) entity).setBaby();
					ti(player);
				} else if (!((Ocelot) entity).isAdult() && hp(player, "ocelot") && !holdingABonemeal(player)) {
					((Ocelot) entity).setAdult();
					ti(player);
				}
				break;
			case PIG:
				if (((Pig) entity).isAdult() && hp(player, "antipig") && holdingABonemeal(player)) {
					((Pig) entity).setBaby();
					ti(player);
				} else if (!((Pig) entity).isAdult() && hp(player, "pig") && !holdingABonemeal(player)) {
					((Pig) entity).setAdult();
					ti(player);
				}
				break;
			case PIG_ZOMBIE:
				if (((PigZombie) entity).isBaby() && hp(player, "zombiepigman") && !holdingABonemeal(player)) {
					((PigZombie) entity).setBaby(false);
					ti(player);
				} else if (!((PigZombie) entity).isBaby() && hp(player, "antizombiepigman") && holdingABonemeal(player)) {
					((PigZombie) entity).setBaby(true);
					ti(player);
				}
				break;
			case SHEEP:
				if (((Sheep) entity).isAdult() && hp(player, "antisheep") && holdingABonemeal(player)) {
					((Sheep) entity).setBaby();
					ti(player);
				} else if (!((Sheep) entity).isAdult() && hp(player, "sheep") && !holdingABonemeal(player)) {
					((Sheep) entity).setAdult();
					ti(player);
				}
				break;
			case SKELETON:
				if (((Skeleton) entity).getSkeletonType().equals(SkeletonType.WITHER) && hp(player, "antiskeleton") && holdingABonemeal(player)) {
					Skeleton skeleton = (Skeleton) w.spawnEntity(l, EntityType.SKELETON);
					EntityEquipment skeletonInventory = skeleton.getEquipment();
					ItemStack bow = new ItemStack(Material.BOW);
					skeletonInventory.setItemInHand(bow);
					ti(player);
				} else if (((Skeleton) entity).getSkeletonType().equals(SkeletonType.NORMAL) && hp(player, "skeleton") && !holdingABonemeal(player)) {
					Skeleton skeleton = (Skeleton) w.spawnEntity(l, EntityType.SKELETON);
					skeleton.setSkeletonType(Skeleton.SkeletonType.WITHER);
					EntityEquipment skeletonInventory = skeleton.getEquipment();
					ItemStack sword = new ItemStack(Material.STONE_SWORD);
					skeletonInventory.setItemInHand(sword);
					ti(player);
				}
				break;
			case SLIME:
				if (hp(player, "slime") && !holdingABonemeal(player)) {
					((Slime) entity).setSize(((Slime) entity).getSize() * 2);
					ti(player);
				} else if (hp(player, "antislime") && holdingABonemeal(player)) {
					((Slime) entity).setSize(((Slime) entity).getSize() / 2);
					ti(player);
				}
				break;
			case SPIDER:
				if (hp(player, "antispider") && holdingABonemeal(player)) {
					ti(player);
					entity.remove();
					w.spawnEntity(l, EntityType.CAVE_SPIDER);
				}
				break;
			case VILLAGER:
				if (((Villager) entity).isAdult() && hp(player, "antivillager") && holdingABonemeal(player)) {
					((Villager) entity).setBaby();
					ti(player);
				} else if (!((Villager) entity).isAdult() && hp(player, "villager") && !holdingABonemeal(player)) {
					((Villager) entity).setAdult();
					ti(player);
				}
				break;
			case WOLF:
				if (((Wolf) entity).isAdult() && hp(player, "antiwolf") && holdingABonemeal(player)) {
					((Wolf) entity).setBaby();
					ti(player);
				} else if (!((Wolf) entity).isAdult() && hp(player, "wolf") && !holdingABonemeal(player)) {
					((Wolf) entity).setAdult();
					ti(player);
				}
				break;
			case ZOMBIE:
				if (((Zombie) entity).isBaby() && hp(player, "zombie") && !holdingABonemeal(player)) {
					((Zombie) entity).setBaby(false);
					ti(player);
				} else if (!((Zombie) entity).isBaby() && hp(player, "zombie") && !holdingABonemeal(player)) {
					entity.remove();
					w.spawnEntity(l, EntityType.GIANT);
					ti(player);
				} else if (!((Zombie) entity).isBaby() && hp(player, "antizombie") && holdingABonemeal(player)) {
					((Zombie) entity).setBaby(true);
					ti(player);
				}
				break;
			default:
				break;
			}
		}
	}
}