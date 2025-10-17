package lol.vifez.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class TPSUtil {

    private static long lastTime = System.nanoTime();
    private static long tickCount = 0;
    private static double tps = 20.0;

    public static void tick() {
        tickCount++;
        long currentTime = System.nanoTime();

        if (currentTime - lastTime >= 1_000_000_000L) {
            tps = tickCount / ((currentTime - lastTime) / 1_000_000_000.0);
            tps = Math.min(tps, 20.0);
            tickCount = 0;
            lastTime = currentTime;
        }
    }

    public static double getTPS() {
        return tps;
    }

    public static void start(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, TPSUtil::tick, 0L, 1L);
    }
}