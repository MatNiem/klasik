package klasik.klasik.handlers;

import klasik.klasik.Klasik;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EatingHandler implements Listener {
    public EatingHandler(Klasik plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEating(PlayerItemConsumeEvent event){
        if(event.getItem().getType() == Material.BREAD){

            Player player = event.getPlayer();
            ItemStack item = event.getItem();

            event.setCancelled(true);
            player.sendMessage(String.valueOf(player.getHealthScale()));
            item.setAmount(event.getItem().getAmount() - 1);
            player.getInventory().setItemInMainHand(item);

            if(player.getHealth() > player.getHealthScale()- 6){
                player.setHealth(player.getHealthScale());
            } else {
                player.setHealth(player.getHealth() + 6);
            }
        }
    }
}
