package ru.darkcov.remstick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;




public class main extends JavaPlugin implements Listener, CommandExecutor {
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "╔======================================╗");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "║ RemStick by darkcov has been enabled ║");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "║              Version " + getDescription().getVersion() + "             ║");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "╚======================================╝");
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("getremstick").setExecutor(this);
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "╔=======================================╗");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "║ RemStick by darkcov has been disabled ║");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "║              Version " + getDescription().getVersion() + "              ║");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "╚=======================================╝");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (cmd.getName().equalsIgnoreCase("getremstick") && (sender.isOp() || sender.hasPermission("remstick.use")) && sender instanceof Player) {
			Player p = (Player)sender;
			ItemStack stack = new ItemStack(Material.STICK, 1);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "REMOVE STICK");
			stack.setItemMeta(meta);
			p.getInventory().addItem(new ItemStack[] { stack });
			p.sendMessage(ChatColor.AQUA + "RemStick > "+ ChatColor.GREEN + "You take it!");
		} 


		return true;
	}
	@EventHandler
	public void entityinter(PlayerInteractAtEntityEvent event) {
		if ((event.getPlayer().isOp() || event.getPlayer().hasPermission("remstick.use")) &&
				event.getPlayer().getItemInHand() != null && 
				event.getPlayer().getItemInHand().getAmount() != 0 && 
				event.getPlayer().getItemInHand().getType().equals(Material.STICK) && 
				event.getPlayer().getItemInHand().hasItemMeta() && 
				ChatColor.stripColor(event.getPlayer().getItemInHand().getItemMeta().getDisplayName()).equalsIgnoreCase("REMOVE STICK"))
			event.getRightClicked().remove(); 
	}
}
