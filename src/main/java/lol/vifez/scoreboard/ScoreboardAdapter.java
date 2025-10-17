package lol.vifez.scoreboard;

import lol.vifez.util.CC;
import lol.vifez.util.PingUtil;
import lol.vifez.util.TPSUtil;
import lol.vifez.util.assemble.AssembleAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class ScoreboardAdapter implements AssembleAdapter {

    private final ScoreboardConfig scoreboardConfig;

    public ScoreboardAdapter() {
        this.scoreboardConfig = new ScoreboardConfig();
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate(scoreboardConfig.getString("scoreboard.title", "&b&lStress Test"));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        String footer = CC.translate(scoreboardConfig.getString("scoreboard.footer", "&7&oPurchase at vifez.lol"));

        for (String line : scoreboardConfig.getStringList("scoreboard.lines")) {
            lines.add(
                    CC.translate(line)
                            .replace("<server-online-time>", getServerUptime())
                            .replace("<date>", getDate())
                            .replace("<online>", String.valueOf(Bukkit.getOnlinePlayers().size()))
                            .replace("<spigot-name>", Bukkit.getServer().getName())
                            .replace("<tps>", String.format("%.2f", TPSUtil.getTPS()))
                            .replace("<ping>", String.valueOf(PingUtil.getPing(player)))
                            .replace("<footer>", footer)
            );
        }

        return lines;
    }

    private String getServerUptime() {
        long uptimeMillis = ManagementFactory.getRuntimeMXBean().getUptime();
        long totalSeconds = uptimeMillis / 1000;

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return String.format("%02dh %02dm %02ds", hours, minutes, seconds);
    }

    private String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}