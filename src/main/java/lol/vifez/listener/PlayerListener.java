package lol.vifez.listener;

import lol.vifez.SpigotTester;
import lol.vifez.kit.Kit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import lol.vifez.util.CC;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Kit activeKit = SpigotTester.getInstance().getKitManager().getActiveKit();
        ItemStack[] contents;

        if (activeKit == null) {
            contents = new ItemStack[36];

            if (event.getPlayer().hasPermission("test.kit")) {
                event.getPlayer().sendMessage(" ");
                event.getPlayer().sendMessage(CC.translate("&7[&cWARNING&7] &fThere is no &bactive kit &fset in &ekits.yml"));
                event.getPlayer().sendMessage(CC.translate("&7[&cWARNING&7] &fRun &b/kit setActive <kitName> &fto fix it"));
                event.getPlayer().sendMessage(" ");
            }
        } else {
            contents = activeKit.getContents();
        }

        event.getPlayer().getInventory().clear();
        event.getPlayer().getInventory().setContents(contents);
    }
}