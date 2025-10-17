package lol.vifez;

import co.aikar.commands.BukkitCommandManager;
import lol.vifez.kit.KitManager;
import lol.vifez.kit.command.KitCommand;
import lol.vifez.listener.PlayerListener;
import lol.vifez.scoreboard.ScoreboardAdapter;
import lol.vifez.util.assemble.Assemble;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public final class SpigotTester extends JavaPlugin {

    @Getter
    private static SpigotTester instance;

    private ScoreboardAdapter scoreboard;
    @Getter
    private KitManager kitManager;

    @Override
    public void onEnable() {
        instance = this;

        kitManager = new KitManager();

        scoreboard = new ScoreboardAdapter();
        new Assemble(this, scoreboard);

        registerCommands();
        registerListeners();
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public void registerCommands() {
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.registerCommand(new KitCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}