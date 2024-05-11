package org.kuroneko42.rtp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kuroneko42.rtp.command.TPCommand;

public final class RTP extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().warning("플러그인 활성화");

        Bukkit.getCommandMap().register("", new TPCommand());

    }

    @Override
    public void onDisable() {
    Bukkit.getLogger().warning("플러그인 비활성화");
        
    }
}
