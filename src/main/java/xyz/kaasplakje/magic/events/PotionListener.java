package xyz.kaasplakje.magic.events;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.kaasplakje.magic.utils.XPotion;

public class PotionListener implements Listener {

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {

        Player player = event.getPlayer();
        Location spin = player.getLocation();
        Location previous = player.getLocation();
        Object desired = previous.add(0.0D, 200.0D, 0.0D);
        
        if (event.getItem() != null) {
            if (event.getItem().getLore().contains(ChatColor.GRAY + "Magico Potion")) {
                player.sendMessage(ChatColor.GREEN + "Magico");
                player.setGameMode(GameMode.SURVIVAL);
                for(int i = 0; i < 50000; ++i) {
                    spin.setYaw(spin.getYaw() + 1.0F);
                    player.teleport(spin);
                    System.out.println("Spin");
                }
                for(int i = 0; i < 50000; ++i) {
                    player.addPotionEffect(new PotionEffect(XPotion.LEVITATION.parsePotionEffectType(), 2147483647, 1));
                    System.out.println("Fly");
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        setSkin(event.getPlayer());
    }

    private void setSkin(Player player) {
        EntityPlayer ep = ((CraftPlayer) player).getHandle();
        GameProfile gp = ep.getProfile();
        PropertyMap pm = gp.getProperties();
        Property property = pm.get("textures").iterator().next();

        pm.remove("textures", property);
        pm.put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NzczMDc2NDExMTYsInByb2ZpbGVJZCI6ImVkNTNkZDgxNGY5ZDRhM2NiNGViNjUxZGNiYTc3ZTY2IiwicHJvZmlsZU5hbWUiOiJGb3J5eExPTCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZkMzcxY2E0MGI3ODBlNzQxY2ZhMDk5NjM0NjZhNzY3NDJmZWM0YmFhOWQzYWRhMmM0YmY5NTRmY2JlNGNlMiJ9fX0=", "f2zyEoxoXHXo3DEEnxjew2OaCA3cs/xWW35cTaHM4R9r/uN14pyJmDOiY2J/uSwPWzER1sL58zQcI0LT0P05McU8p/UE5bR9m9JIjCPGoWrWojvPTCzl2oEO08Pd83zhZsn1DlvHEKCjfmr6tb33B6Z81uqpyp26r3lVzfPMECsRE4Idt3bmzPEVXJUzkBlkSf6oCwcBgriPoboklf1gIEhgX1rlCidrOqYpmdFb7mGC9lREh0nUUrgbUrL1nI5Pof5I/LMTB5Xz/WCLaqlxian80bmfUwMfyqrFdOAeMnR1kb6uMQhY2DMCm/Es0qLw1KT21vb1HEpe9HxEniInSr5ZDYnpI2w2/yfsCNj+g661EcDDH2yMqukkqeEtXs3jIsgCjPLmb5m1zhfwMSaYCjSWPKuAKcyBNuVlBLPqdwDkBp/c4wZU5bm+ZdaQl+efEo89cmrDbcSSzFXynHSYl1x+3Ycc7fULMoRyrJhOrM0UzhFFpuv0OyYp0CxkmMlCdTkER0iBRYSDt3tKaZvmwPz/OQC5K/5itDOxNkd1/kdFqrn8nwXcmo4MppvBkhBxqwLp4poWYgAjLLlQIMflLVSI+65EztUc7B8SupZ8ZpMxX5sjiAoCybKaP4jDOfAG+WfLo0UBfLIGH5i4l6IKNzt6wFNtsQ2+Xf5mLrNEPq8="));

    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (event.getItemDrop().getItemStack().hasItemMeta()) {
            if (event.getItemDrop().getItemStack().getLore().contains(ChatColor.GRAY + "Magico Potion")) {
                player.sendMessage(ChatColor.GREEN + "You little fuckhole, I told you not to drop, you fucking piece of shit.");
                player.setHealth(0.0D);
                event.setCancelled(true);
            }
        }
    }
}
