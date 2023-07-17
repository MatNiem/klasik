package klasik.klasik.handlers;

import klasik.klasik.Klasik;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
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

    @EventHandler
    public void onWinterBiome(PlayerMoveEvent event){
        Location event_location = event.getPlayer().getLocation();
        Block block = event_location.getBlock();
        Bukkit.getLogger().info("marchew");
        if(block.getBiome() == Biome.GROVE){
            Random random = new Random();
            Bukkit.getLogger().info("A torch was placed!");
            if(random.nextInt(1000) == 0){
                event_location.getWorld().playSound(event_location, Sound.ITEM_GOAT_HORN_SOUND_1, SoundCategory.AMBIENT, 1, 1);
                Bukkit.getLogger().info("Time!");
            }
        }
    }
}
