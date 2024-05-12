package org.kuroneko42.rtp.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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
            List<Location> safeList = new ArrayList<>();

            int x = random.nextInt(20000) - 10000;
            int y = random.nextInt(260) - 60;
            int z = random.nextInt(20000) - 10000;

            for (int i = -60; i < 200; i++) {
                Location safeY = new Location(world, x + 0.5, i, z + 0.5);

                if (safeLoc(safeY)) {
                    safeList.add(safeY);
                }

            }

            player.teleport(safeList.get(random.nextInt(safeList.size())));
            playerLoc(player);
        }
        return false;
    }

    public void playerLoc(Player player) {
        Location playerLoc = player.getLocation();
        player.sendMessage("티피완료 -> " + "X: " + playerLoc.getX() + "Y: " + playerLoc.getY() + "Z: " + playerLoc.getZ());
    }

    public boolean safeLoc(Location location) {
        if (!location.getBlock().getRelative(0,-1,0).getType().isAir()
            && location.getBlock().getRelative(0, 0, 0).getType().isAir()
            && location.getBlock().getRelative(0, 1, 0).getType().isAir()) {
            return true;
        }
        return false;
    }
}
