package klasik.klasik.handlers;

import klasik.klasik.Klasik;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class ZombiePatrol implements Listener {
    public ZombiePatrol(Klasik plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onGrassBlockDestroy(BlockBreakEvent event){
        if(event.getBlock().getType() == Material.GRASS_BLOCK) {
            Random random = new Random();
            if (((random.nextInt(10) + 1) % 7) == 0) {
                Location event_location = event.getBlock().getLocation();
                event_location.getWorld().playSound(event_location, Sound.ITEM_GOAT_HORN_SOUND_1, SoundCategory.AMBIENT, 1, 1);
                spawnZombies(event_location);
            }
        }
    }

    private void spawnZombies(Location location){
        Random random = new Random();
        for(int i = 0; i<random.nextInt(3)+1; i++){
            Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        }
    }
}
