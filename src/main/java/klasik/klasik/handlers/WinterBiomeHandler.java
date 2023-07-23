package klasik.klasik.handlers;

import klasik.klasik.Klasik;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

import java.util.List;
import java.util.Random;

public class WinterBiomeHandler implements Listener {
    public WinterBiomeHandler(Klasik plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    boolean active = false;
    long spawn_cooldown;
    Location spawn_location;

    @EventHandler
    public void onWinterBiome(PlayerMoveEvent event){
        Location event_location = event.getPlayer().getLocation();
        Block block = event_location.getBlock();

        if(!active) {
            if (block.getBiome() == Biome.GROVE) {
                Random random = new Random();
                if (random.nextInt(100) == 0) {
                    active = true;
                    spawn_cooldown = System.currentTimeMillis();
                    event_location.getWorld().playSound(event_location, Sound.ITEM_GOAT_HORN_SOUND_1, SoundCategory.AMBIENT, 1, 1);
                    spawn_location = event_location;
                }
            }
        }else if(System.currentTimeMillis() - spawn_cooldown > 5000){
            spawnZombies(spawn_location);
            active = false;
        }
    }

    private void spawnZombies(Location location){
        Random random = new Random();
        for(int i = 0; i<random.nextInt(3)+1; i++){
            Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        }
    }
}
