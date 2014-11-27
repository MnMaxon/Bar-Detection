package me.MnMaxon.BarDetection;

import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	public static String dataFolder;
	public static Main plugin;

	@Override
	public void onEnable() {
		plugin = this;
		dataFolder = this.getDataFolder().getAbsolutePath();
		setupConfig();
		Config.Load(dataFolder + "/Data");
		getServer().getPluginManager().registerEvents(new MainListener(), this);
	}

	public static YamlConfiguration setupConfig() {
		cfgSetter("Max Detection Distance", 5.0);
		cfgSetter("Set Level", true);
		return Config.Load(dataFolder + "/Config.yml");
	}

	public static void cfgSetter(String path, Object value) {
		YamlConfiguration cfg = Config.Load(dataFolder + "/Config.yml");
		if (cfg.get(path) == null) {
			cfg.set(path, value);
			Config.Save(cfg, dataFolder + "/Config.yml");
		}
	}

	public static boolean enemyChecker(List<Entity> nearbyEntities) {
		for (Entity ent : nearbyEntities) {
			EntityType type = ent.getType();
			if (type.equals(EntityType.BLAZE) || type.equals(EntityType.CAVE_SPIDER) || type.equals(EntityType.CREEPER)
					|| type.equals(EntityType.ENDER_DRAGON) || type.equals(EntityType.ENDERMAN)
					|| type.equals(EntityType.GHAST) || type.equals(EntityType.GIANT)
					|| type.equals(EntityType.MAGMA_CUBE) || type.equals(EntityType.PIG_ZOMBIE)
					|| type.equals(EntityType.SILVERFISH) || type.equals(EntityType.SKELETON)
					|| type.equals(EntityType.SLIME) || type.equals(EntityType.SPIDER) || type.equals(EntityType.WITCH)
					|| type.equals(EntityType.WITHER) || type.equals(EntityType.WITHER_SKULL)
					|| type.equals(EntityType.ZOMBIE))
				return true;
			else if (ent instanceof Wolf && ((Wolf) ent).isAngry())
				return true;
		}
		return false;
	}

	public static int enemyAmount(List<Entity> nearbyEntities) {
		int x = 0;
		for (Entity ent : nearbyEntities) {
			EntityType type = ent.getType();
			if (type.equals(EntityType.BLAZE) || type.equals(EntityType.CAVE_SPIDER) || type.equals(EntityType.CREEPER)
					|| type.equals(EntityType.ENDER_DRAGON) || type.equals(EntityType.ENDERMAN)
					|| type.equals(EntityType.GHAST) || type.equals(EntityType.GIANT)
					|| type.equals(EntityType.MAGMA_CUBE) || type.equals(EntityType.PIG_ZOMBIE)
					|| type.equals(EntityType.SILVERFISH) || type.equals(EntityType.SKELETON)
					|| type.equals(EntityType.SLIME) || type.equals(EntityType.SPIDER) || type.equals(EntityType.WITCH)
					|| type.equals(EntityType.WITHER) || type.equals(EntityType.WITHER_SKULL)
					|| type.equals(EntityType.ZOMBIE))
				x++;
			else if (ent instanceof Wolf && ((Wolf) ent).isAngry())
				x++;
		}
		return x;
	}
}