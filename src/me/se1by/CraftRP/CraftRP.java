package me.se1by.CraftRP;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftRP extends JavaPlugin
{
  private final pJoin pJoin = new pJoin(this);
  public String Plugin = ChatColor.DARK_AQUA + "[CraftRP] ";

public void onDisable()
  {
    System.out.println("[CraftRP] disabled");
  }

  public void onEnable()
  {
    System.out.println("[CraftRP] enabled");
    setupEconomy();
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvent(Event.Type.PLAYER_JOIN, this.pJoin, Event.Priority.Normal, this);
    setupFile("economy");
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("crp"))
    {
      if (!(sender instanceof Player))
      {
        sender.sendMessage("[CraftRP] This command isn't compatible with the console in this version.");
        return true;
      }
      Player player = (Player) sender;
      if(args.length == 0){
    	  showHelp(player);
      }
      else if (args.length == 1){
    	  if (args[0].equalsIgnoreCase("money")){
    		  String[] money = economy.loadMoney(player);
    		  String gold = money[0];
    		  String iron = money[1];
    		  String currency = money[2];
    		  
    		  player.sendMessage(Plugin + ChatColor.BLUE + "You got " + gold + "," + iron + " " + currency);
    		  return true;
    	  }
      }
      else if (args.length == 3){
      
    	  
    	if (args[0].equalsIgnoreCase("trade")){
    		String item = args[1];
    		int amount = Integer.parseInt(args[2]);
    		  
    		if (item.equalsIgnoreCase("gold")){
    			int ItemID = 266;
    			economy.trade(player, ItemID, amount);
    		}
    		else if (item.equalsIgnoreCase("iron")){
    			int ItemID = 265;
    			economy.trade(player, ItemID, amount);
    		}
    		else{
    			player.sendMessage(Plugin + ChatColor.BLUE + "You can't trade this!");
    		}
    	}
    		  
      }
    	  return true;
    }
	return false;
  }

  private void showHelp(Player player) {
	player.sendMessage(Plugin + ChatColor.BLUE + "Placeholder");
	
}

private void setupEconomy() {
    boolean succeed = economy.setupEconomy();
    if (succeed) {
      System.out.println("[CraftRP] Economy enabled!");
    }
    else
      System.out.println("[CraftRP] Economy couldn't be loaded!");
  }
private void setupFile(String FileName){
	boolean exists = new File("plugins/CraftRP/" + FileName + ".yml").exists();
    if (!exists) {
      new File("plugins/CraftRP/").mkdir();
      try {
        new File("plugins/CraftRP/" + FileName + ".yml").createNewFile();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
}
}