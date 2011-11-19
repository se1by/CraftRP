package me.se1by.CraftRP;

import java.io.File;
import java.io.IOException;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class pJoin extends PlayerListener{
	
	public CraftRP plugin;
	public YamlConfiguration economy = YamlConfiguration.loadConfiguration(new File("plugins/CraftRP/economy.yml"));
	
	public pJoin(CraftRP CraftRP){
		plugin = CraftRP;
	}

	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if (economy.getString(player.getName() + ".Gold") == null){
			economy.set(player.getName() + ".Gold", "0");
			economy.set(player.getName() + ".Iron", "0");
			save("economy", player);
		}
		if (economy.getString("Currency") == null){
			economy.set("Currency", "MineCoins");
			save("economy", player);
		}
	}
	
	public void save(String data, Player player){
		try {
			economy.save("plugins/CraftRP/" + data + ".yml");
		} catch (IOException e) {
			System.out.println("[CraftRP] Unable to save file " + data +".yml");
			System.out.println("[CraftRP] Caused by player: " + player.getName());
			e.printStackTrace();
		}
	}
}
