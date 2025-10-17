package lol.vifez.util;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class ActionBar {

    public static String format(String message, Player player) {
        int online = Bukkit.getOnlinePlayers().size();
        int max = Bukkit.getMaxPlayers();

        double tps = TPSUtil.getTPS();
        int ping = player instanceof CraftPlayer
                ? ((CraftPlayer) player).getHandle().ping
                : 0;

        int chunksLoaded = 0;
        int entities = 0;
        for (World world : Bukkit.getWorlds()) {
            chunksLoaded += world.getLoadedChunks().length;
            entities += world.getEntities().size();
        }

        return message
                .replace("<online>", String.valueOf(online))
                .replace("<max>", String.valueOf(max))
                .replace("<TPS>", String.format("%.2f", tps))
                .replace("<ping>", String.valueOf(ping))
                .replace("<chunks-loaded>", String.valueOf(chunksLoaded))
                .replace("<entities-amount>", String.valueOf(entities));
    }

    public static void send(Player player, String message) {
        if (player == null || !player.isOnline()) return;
        ChatComponentText chat = new ChatComponentText(message);
        PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}