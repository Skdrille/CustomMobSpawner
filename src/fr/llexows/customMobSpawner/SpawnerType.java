package fr.llexows.customMobSpawner;

import fr.llexows.customMobSpawner.managers.ConfigManager;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public enum SpawnerType {
    CREEPER("Creeper", 50),
    SKELETON("Skeleton",  51),
    SPIDER("Spider", 52),
    ZOMBIE("Zombie", 54),
    SLIME("Slime", 55),
    GHAST("Ghast", 56),
    PIGZOMBIE("PigZombie", 57),
    ENDERMAN("Enderman", 58),
    CAVE_SPIDER("CaveSpider", 59),
    SILVERFISH("Silverfish", 60),
    BLAZE("Blaze", 61),
    MAGMA_CUBE("LavaSlime", 62),
    ENDER_DRAGON("EnderDragon", 63),
    ENDERMITE("Endermite", 67),
    GUARDIAN("Guardian", 68),
    PIG("Pig", 90),
    SHEEP("Sheep", 91),
    COW("Cow", 92),
    CHICKEN("Chicken", 93),
    SQUID("Squid", 94),
    WOLF("Wolf", 95),
    MUSHROOM_COW("MushroomCow", 96),
    RABBIT("Rabbit", 101),
    VILLAGER("Villager", 120);

    private final String type_name;
    private String displayName;
    private ItemStack eggIcon;
    private double price;
    private boolean available;

    SpawnerType(String type_name, int type_id){
        this.type_name = type_name;
        this.displayName = ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig().getString("Spawner-type." + type_name.toUpperCase() + ".egg-title"));
        this.price = ConfigManager.getConfig().getDouble("Spawner-type." + type_name.toUpperCase() + ".price");
        this.available = ConfigManager.getConfig().getBoolean("Spawner-type."+type_name.toUpperCase() + ".available");

        this.eggIcon = new ItemStack(Material.MONSTER_EGG, 1, (byte) type_id);
        updatePriceAndAvailableState();
    }

    public String getTypeName(){
        return type_name;
    }

    public String getDisplayName(){
        return displayName;
    }

    public ItemStack getEggIcon(){
        return eggIcon;
    }

    public boolean isAvailable(){
        return available;
    }

    public double getPrice(){
        return price;
    }

    private void updatePriceAndAvailableState(){
        price = ConfigManager.getConfig().getDouble("Spawner-type." + type_name.toUpperCase() + ".price");
        available = ConfigManager.getConfig().getBoolean("Spawner-type."+type_name.toUpperCase() + ".available");

        ItemMeta meta = eggIcon.getItemMeta();
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        lore.add("Price : " + (price == 0 ? Utils.format(ConfigManager.getConfig().getString("Global.price-free")) :  (Utils.format(ConfigManager.getConfig().getString("Global.price-color"))) + price));
        lore.add("Status : " + (isAvailable() ?  Utils.format(ConfigManager.getConfig().getString("Global.available-status")): Utils.format(ConfigManager.getConfig().getString("Global.not-available-status"))));
        meta.setLore(lore);
        eggIcon.setItemMeta(meta);
    }

}
