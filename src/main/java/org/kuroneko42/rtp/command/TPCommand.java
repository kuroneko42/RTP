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

            // -60부터 200까지 증가
            for (int i = -60; i < 200; i++) {

                // safeY함수에 랜덤한 X, Z 좌표와 -60 ~ 200 까지 좌표를 저장
                Location safeY = new Location(world, x + 0.5, i, z + 0.5);

                // 만약 safeY가 안전한 좌표라면 safeList에 safeY좌표를 추가하라
                if (safeLoc(safeY)) {
                    safeList.add(safeY);
                }

            }

            // 플레이어가 safeList안 모든 좌표중 랜덤으로 하나의 좌표로 이동시켜라
            player.teleport(safeList.get(random.nextInt(safeList.size())));
            playerLoc(player);
        }
        return false;
    }

    public void playerLoc(Player player) {
        Location playerLoc = player.getLocation();
        player.sendMessage("티피완료 -> " + "X: " + playerLoc.getX() + " " + "Y: " + playerLoc.getY() + " " + "Z: " + playerLoc.getZ());
    }

    public boolean safeLoc(Location location) {
        // 만약 이동한 좌표의 밑이 공기가 아니라면 그리고 위에 두칸이 공기라면 반환
        if (!location.getBlock().getRelative(0,-1,0).getType().isAir()
            && location.getBlock().getRelative(0, 0, 0).getType().isAir()
            && location.getBlock().getRelative(0, 1, 0).getType().isAir()) {
            return true;
        }
        return false;
    }
}
