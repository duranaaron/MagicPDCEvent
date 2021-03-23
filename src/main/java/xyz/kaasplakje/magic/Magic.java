package xyz.kaasplakje.magic;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kaasplakje.magic.commands.MagicCMD;
import xyz.kaasplakje.magic.events.PotionListener;

public final class Magic extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("MAGICO MAGICO");
        Bukkit.getPluginManager().registerEvents(new PotionListener(), this);
        getCommand("magic").setExecutor(new MagicCMD());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
