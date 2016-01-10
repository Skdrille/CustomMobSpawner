package fr.llexows.customMobSpawner.managers;

import fr.llexows.customMobSpawner.BuySign;
import fr.llexows.customMobSpawner.Core;
import fr.llexows.customMobSpawner.SpawnerType;
import fr.llexows.customMobSpawner.Utils;
import fr.llexows.customMobSpawner.exceptions.CustomSpawnerException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class SpawnerManager {

    private Inventory inv, inv2;
    private HashMap<CreatureSpawner, SpawnerType> spawners = new HashMap<>();
    private List<UUID> inBypass = new ArrayList<>();
    private HashMap<Player, CreatureSpawner> playerSpawner = new HashMap<>();
    private Material magic_pickaxe_material;
    private List<BuySign> buySigns = new ArrayList<>();

    public SpawnerManager(){
        this.inv = Bukkit.createInventory(null, 9*6, "§l» Select spawner type «");
        this.inv2 = Bukkit.createInventory(null, 9*6,"§l» Select spawner type «");
    }

    public Inventory getSpawnerTypeInventory(int i){
        if(i == 1){
            if(inv == null) {
                inv = Bukkit.createInventory(null, 9 * 6, "§l♦ Select spawner type ♦");
                init();
            }
            return inv;
        }else if(i == 2){
            if(inv2 == null){
                inv2 = Bukkit.createInventory(null, 9*6,"§l♦ Select spawner type ♦");
                init();
            }
            return inv2;
        }
        return null;
    }

    public void init(){
        /*
        Pages
         */
        ItemStack nextPage = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
        ItemMeta meta = nextPage.getItemMeta();
        meta.setDisplayName("§a§lNext Page ►");
        nextPage.setItemMeta(meta);

        ItemStack previousPage = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        ItemMeta previousMeta = previousPage.getItemMeta();
        previousMeta.setDisplayName("§c§l◄ Previous Page");
        previousPage.setItemMeta(previousMeta);

        getSpawnerTypeInventory(1).setItem(49, nextPage);
        getSpawnerTypeInventory(2).setItem(49, previousPage);

        /*
        Eggs
         */
        getSpawnerTypeInventory(1).setItem(10, SpawnerType.CREEPER.getEggIcon());
        getSpawnerTypeInventory(1).setItem(11, SpawnerType.SKELETON.getEggIcon());
        getSpawnerTypeInventory(1).setItem(12, SpawnerType.SPIDER.getEggIcon());
        getSpawnerTypeInventory(1).setItem(13, SpawnerType.ZOMBIE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(14, SpawnerType.SLIME.getEggIcon());
        getSpawnerTypeInventory(1).setItem(15, SpawnerType.GHAST.getEggIcon());
        getSpawnerTypeInventory(1).setItem(16, SpawnerType.PIGZOMBIE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(19, SpawnerType.CAVE_SPIDER.getEggIcon());
        getSpawnerTypeInventory(1).setItem(20, SpawnerType.SILVERFISH.getEggIcon());
        getSpawnerTypeInventory(1).setItem(21, SpawnerType.ENDERMAN.getEggIcon());
        getSpawnerTypeInventory(1).setItem(22, SpawnerType.BLAZE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(23, SpawnerType.MAGMA_CUBE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(24, SpawnerType.ENDER_DRAGON.getEggIcon());
        getSpawnerTypeInventory(1).setItem(25, SpawnerType.ENDERMITE.getEggIcon());
        getSpawnerTypeInventory(1).setItem(28, SpawnerType.GUARDIAN.getEggIcon());

        getSpawnerTypeInventory(2).setItem(10, SpawnerType.PIG.getEggIcon());
        getSpawnerTypeInventory(2).setItem(11, SpawnerType.SHEEP.getEggIcon());
        getSpawnerTypeInventory(2).setItem(12, SpawnerType.COW.getEggIcon());
        getSpawnerTypeInventory(2).setItem(13, SpawnerType.CHICKEN.getEggIcon());
        getSpawnerTypeInventory(2).setItem(14, SpawnerType.SQUID.getEggIcon());
        getSpawnerTypeInventory(2).setItem(15, SpawnerType.WOLF.getEggIcon());
        getSpawnerTypeInventory(2).setItem(16, SpawnerType.MUSHROOM_COW.getEggIcon());
        getSpawnerTypeInventory(2).setItem(19, SpawnerType.RABBIT.getEggIcon());
        getSpawnerTypeInventory(2).setItem(20, SpawnerType.VILLAGER.getEggIcon());
    }

    public ItemStack getMagicPickaxe() throws CustomSpawnerException {
        if(magic_pickaxe_material == null){
            if(Material.getMaterial(ConfigManager.getConfig().getString("Global.magic-pickaxe-material").toUpperCase()) == null){
                throw new CustomSpawnerException("Cannot found the material : " + ConfigManager.getConfig().getString("Global.magic-pickaxe-material").toUpperCase() + " for the magic pickaxe");
            }else{
                magic_pickaxe_material = Material.getMaterial(ConfigManager.getConfig().getString("Global.magic-pickaxe-material").toUpperCase());
            }
        }

        if(!magic_pickaxe_material.equals(Material.WOOD_PICKAXE) && !magic_pickaxe_material.equals(Material.STONE_PICKAXE)
                && !magic_pickaxe_material.equals(Material.IRON_PICKAXE) && !magic_pickaxe_material.equals(Material.GOLD_PICKAXE)
                && !magic_pickaxe_material.equals(Material.DIAMOND_PICKAXE)){
            throw new CustomSpawnerException(magic_pickaxe_material + " is not a valid material for the magic pickaxe !");
        }else{
            ItemStack pickaxe = new ItemStack(magic_pickaxe_material);
            ItemMeta meta = pickaxe.getItemMeta();
            pickaxe.setDurability((short) 10000);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.setDisplayName(Utils.format(ConfigManager.getConfig().getString("Global.magic-pickaxe-title")));
            List<String> lore = Arrays.asList(Utils.format(ConfigManager.getConfig().getString("Global.magic-pickaxe-desc")));
            meta.setLore(lore);
            pickaxe.setItemMeta(meta);
            return pickaxe;
        }
    }

    public void openSelectionInventory(Player player, int page){
        player.openInventory(getSpawnerTypeInventory(page));
    }

    public void setSpawnerType(CreatureSpawner theSpawner, SpawnerType spawnerType){
        theSpawner.setCreatureTypeByName(spawnerType.getTypeName());
        spawners.put(theSpawner, spawnerType);
    }

    /**
     * <b>Get the entity type of a mob spawner</b>
     * @param cs the mob spawner
     * @return the spawner type
     */
    public SpawnerType getSpawnerType(CreatureSpawner cs){
        if(!spawners.containsKey(cs)) return null;
        return spawners.get(cs);
    }

    /**
     * <b>Check if the target player is in bypass mode</b>
     * @param player player to check
     * @return true if bypass
     */
    public boolean isBypass(Player player){
        return inBypass.contains(player.getUniqueId());
    }

    /**
     * <b>Set the bypass mode to the target player</b>
     * @param player the player to set bypass mode
     * @param bol true for enable bypass, false for disable
     */
    public void setBypass(Player player, boolean bol){
        if(bol){
            inBypass.add(player.getUniqueId());
        }else{
            inBypass.remove(player.getUniqueId());
        }
    }

    public ItemStack createItemSpawnerWithSpecifiedType(SpawnerType spawnerType){
        ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta meta = spawner.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("Type : §6" + spawnerType.getTypeName().toUpperCase());
        meta.setLore(lore);
        spawner.setItemMeta(meta);
        return spawner;
    }

    public HashMap<Player, CreatureSpawner> getPlayerSpawner(){
        return playerSpawner;
    }

    public List<BuySign> getBuySignsList(){
        return buySigns;
    }

    public BuySign getBuySignFromLocation(Location location){
        for(BuySign buySign : getBuySignsList()){
            if(buySign.getLocation().equals(location)){
                return buySign;
            }
        }
        return null;
    }

}
