package lol.vifez.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class CC {

    public static String translate(String message) {
        if (message == null) return "";
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String[] translate(String... messages) {
        String[] result = new String[messages.length];
        for (int i = 0; i < messages.length; i++) {
            result[i] = translate(messages[i]);
        }
        return result;
    }

    public static void console(String message) {
        if (message == null) return;
        Bukkit.getConsoleSender().sendMessage(translate(message));
    }
}

