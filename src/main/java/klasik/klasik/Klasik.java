package klasik.klasik;

import klasik.klasik.commands.Fly;
import klasik.klasik.handlers.TorchHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Klasik extends JavaPlugin {
    HashMap<UUID, Location> deathLocations;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World! Por");

        deathLocations = new HashMap<>();
        getCommand("fly").setExecutor(new Fly());

        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        new TorchHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down");
    }

    private class DeathListener implements Listener {
        @EventHandler
        public void onPlayerDeath(PlayerDeathEvent event) {
            Player player = event.getEntity();
            Location deathLocation = player.getLocation();
            deathLocations.put(player.getUniqueId(), deathLocation);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerId = player.getUniqueId();

        if (command.getName().equalsIgnoreCase("back")) {
            if (deathLocations.containsKey(playerId)) {
                Location deathLocation = deathLocations.get(playerId);
                player.teleport(deathLocation);
                player.sendMessage("Teleported to your recent death location.");
                deathLocations.remove(playerId);
            } else {
                player.sendMessage("You don't have a recent death location to teleport to.");
            }
            return true;
        }
        return false;
    }
}
