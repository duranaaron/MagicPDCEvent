package xyz.kaasplakje.magic.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MagicCMD implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        ItemStack potion = new ItemStack(Material.POTION); //Creates the stack
        ItemMeta meta = potion.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Magico Potion"); //Add the lore
        meta.setLore(lore); //Set the lore to the item
        meta.setDisplayName(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "MAGIC POTION" + ChatColor.DARK_GRAY + "]"); //Set the display name (aka the name of the item)
        potion.setItemMeta(meta); //Set it to the item

        if (sender != null) {
                player.sendMessage(ChatColor.GREEN + "I just gave you a magic fucking potion, do what you want with it but don't throw it away.");
                player.getInventory().setItem(0, potion);
        } else {
            assert sender != null;
            sender.sendMessage("Only for players asshat.");
        }
        return false;
    }
}
