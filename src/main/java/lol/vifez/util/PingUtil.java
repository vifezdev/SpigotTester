package lol.vifez.util;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class PingUtil {

    public static int getPing(Player player) {
        try {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            return craftPlayer.getHandle().ping;
        } catch (Exception e) {
            return 0;
        }
    }
}