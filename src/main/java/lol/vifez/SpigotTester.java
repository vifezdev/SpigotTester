package lol.vifez;

import lol.vifez.scoreboard.ScoreboardAdapter;
import lol.vifez.util.assemble.Assemble;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public final class SpigotTester extends JavaPlugin {

    private ScoreboardAdapter scoreboard;

    @Override
    public void onEnable() {
        scoreboard = new ScoreboardAdapter();

        new Assemble(this, scoreboard);
    }

    @Override
    public void onDisable() {
    }
}