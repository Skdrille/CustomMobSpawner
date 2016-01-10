package fr.llexows.customMobSpawner;

import org.bukkit.Location;

/**
 * Created by Llexows.
 *
 * @version 0.1
 */
public class BuySign {

    private final Location location;
    private final SpawnerType spawnerType;
    private final double price;

    public BuySign(Location loc, SpawnerType spawnerType, double price){
        this.location = loc;
        this.spawnerType = spawnerType;
        this.price = price;
    }

    public Location getLocation(){
        return location;
    }

    public SpawnerType getSpawnerType(){
        return spawnerType;
    }

    public double getPrice(){
        return price;
    }

}
