package me.se1by.CraftRP;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class economy
{
  public static YamlConfiguration economy;

  public static boolean setupEconomy()
  {
    boolean succeed;
    if (economy != null) {
      succeed = true;
    }
    else {
      succeed = false;
    }
    return succeed;
  }
  public static String[] loadMoney(Player player) {
	  new YamlConfiguration();
	economy = YamlConfiguration.loadConfiguration(new File("plugins/CraftRP/economy.yml"));
    String gold = economy.getString(player.getName() + ".Gold");
    System.out.println(gold);
    String iron = economy.getString(player.getName() + ".Iron");
    System.out.println(iron);
    String currency = economy.getString("Currency");
    if (Integer.parseInt(iron) >= 100){
    	while (Integer.parseInt(iron) >= 100){
    		System.out.println("in while");
    		gold = String.valueOf(Integer.parseInt(gold) + 1);
    		iron = String.valueOf(Integer.parseInt(iron) - 100);
    	}
    }
    if(iron == "0"){
		  iron = "00";
	  }
    String[] money = new String[3];
    money[0] = gold;
    money[1] = iron;
    money[2] = currency;
    return money;
  }
  public static void trade(Player player, int item, int amount) {
	String ItemName;
	if (item == 266){
		ItemName = "Gold";
	}
	else{
		ItemName = "Iron";
	}
	if (player.getInventory().contains(item, amount)){
		ItemStack ItemStack = new ItemStack(item, amount);
		player.getInventory().remove(ItemStack);
		economy = YamlConfiguration.loadConfiguration(new File("plugins/CraftRP/economy.yml"));
		String money = economy.getString(player.getName() + "." + ItemName);
		String newMoney = String.valueOf(Integer.parseInt(money) + amount);
		economy.set(player.getName() + "." + ItemName, newMoney);
		player.sendMessage(ChatColor.DARK_AQUA + "[CraftRP] " + ChatColor.BLUE + ItemName + " exchanged!");
	}
	else{
		player.sendMessage(ChatColor.DARK_AQUA + "[CraftRP] " + ChatColor.BLUE + "You don't have enough " + ItemName + "!");
	}
  }
}