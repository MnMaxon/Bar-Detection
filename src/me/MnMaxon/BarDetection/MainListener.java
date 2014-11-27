package me.MnMaxon.BarDetection;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MainListener implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		YamlConfiguration cfg = Main.setupConfig();
		double tenth = cfg.getDouble("Max Detection Distance") / 10;
		double x = 10;
		while (x > 0) {
			if (!e.getPlayer().getNearbyEntities(tenth * x, 3, tenth * x).isEmpty()
					&& Main.enemyChecker(e.getPlayer().getNearbyEntities(tenth * x, 3, tenth * x))) {
				x--;
			} else {
				if (cfg.getBoolean("Set Level"))
					e.getPlayer()
							.setLevel(Main.enemyAmount(e.getPlayer().getNearbyEntities(tenth * 10, 3, tenth * 10)));
				e.getPlayer().setExp((float) ((double) ((10.0 - x) / 10.0)));
				return;
			}
		}
		if (cfg.getBoolean("Set Level"))
			e.getPlayer().setLevel(Main.enemyAmount(e.getPlayer().getNearbyEntities(tenth * 10, 3, tenth * 10)));
		e.getPlayer().setExp((float) ((double) ((10 - x) / 10)));
		return;
	}
}
