package com.droppages.Skepter;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

// Referenced classes of package com.me.Skepter:
//            BowListener

public class BoneMeal4Mobs extends JavaPlugin
{

    Logger log;

    public BoneMeal4Mobs()
    {
    }

    public void onEnable()
    {
    	PluginDescriptionFile description = this.getDescription();
        log = getLogger();
        new GiantListener(this);
        new CowListener(this);
        new SheepListener(this);
        new ChickenListener(this);
        new PigListener(this);
        new VillagerListener(this);
        new WolfListener(this);
        new OcelotListener(this);
        new MushroomcowListener(this);
        new BabyZombieListener(this);
        new BabyPigzombieListener(this);
        new SlimeListener(this);
        new MagmaCubeListener(this);
        new AntiGiantListener(this);
        new AntiCowListener(this);
        new AntiSheepListener(this);
        new AntiChickenListener(this);
        new AntiPigListener(this);
        new AntiVillagerListener(this);
        new AntiWolfListener(this);
        new AntiOcelotListener(this);
        new AntiMushroomcowListener(this);
        new AntiBabyZombieListener(this);
        new AntiBabyPigzombieListener(this);
        new AntiSlimeListener(this);
        new AntiMagmaCubeListener(this);
        recipe();
        log.info("Bonemeal4Mobs " + description.getVersion() + " activated!");
    }

    public void onDisable()
    {
    	PluginDescriptionFile description = this.getDescription();
        log.info("Bonemeal4Mobs " + description.getVersion() + " de-activated!");
    }
    
    public void recipe()
    {	
    	ItemStack antibonemeal = new ItemStack(Material.COAL, 1);
        antibonemeal.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        ItemMeta im = antibonemeal.getItemMeta();
		im.setDisplayName("Anti-Bonemeal");
		antibonemeal.setItemMeta(im);
        ShapedRecipe recipe = new ShapedRecipe(antibonemeal);
        recipe.shape("A", "B");
    	recipe.setIngredient('A', Material.INK_SACK.getNewData((byte) 15));
    	recipe.setIngredient('B', Material.COAL);
    	getServer().addRecipe(recipe);
    }
}
    



  
