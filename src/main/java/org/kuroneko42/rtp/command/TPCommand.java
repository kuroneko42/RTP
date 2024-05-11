package org.kuroneko42.rtp.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class TPCommand extends BukkitCommand {

    public TPCommand() {
        super("rtp");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player){
            Random random = new Random();
            World world = player.getWorld();
            Location randomlocation;

            int x = random.nextInt(20000) - 10000;
            int y = random.nextInt(264) - 60;
            int z = random.nextInt(20000) - 10000;
            randomlocation = new Location(world, x + 0.5, y, z + 0.5);

            while (randomlocation.getBlock().getType() != Material.AIR
                    || randomlocation.clone().add(0, 1, 0).getBlock().getType() != Material.AIR
                    || randomlocation.clone().add(0, -1, 0).getBlock().getType() == Material.AIR){
                randomlocation.subtract(0, 1, 0);
            }

            player.teleport(randomlocation);
            playerLoc(player);
        }
        return false;
    }

    public void playerLoc(Player player) {
        Location playerLoc = player.getLocation();
        player.sendMessage("티피완료 -> " + "X: " + playerLoc.getX() + "Y: " + playerLoc.getY() + "Z: " + playerLoc.getZ());
    }
}
