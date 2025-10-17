package lol.vifez.listener;

import lol.vifez.SpigotTester;
import lol.vifez.kit.Kit;
import lol.vifez.util.ActionBar;
import lol.vifez.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Kit activeKit = SpigotTester.getInstance().getKitManager().getActiveKit();
        ItemStack[] contents;

        if (activeKit == null) {
            contents = new ItemStack[36];

            if (player.hasPermission("test.kit")) {
                player.sendMessage(" ");
                player.sendMessage(CC.translate("&7[&cWARNING&7] &fThere is no &bactive kit &fset in &ekits.yml"));
                player.sendMessage(CC.translate("&7[&cWARNING&7] &fRun &b/kit setActive <kitName> &fto fix it"));
                player.sendMessage(" ");
            }
        } else {
            contents = activeKit.getContents();
        }

        player.getInventory().clear();
        player.getInventory().setContents(contents);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                String actionBarMessage = "&fOnline: &a<online>&f/&emax &b▎ &fTPS: &a<TPS> &b▎ &fPing: &a<ping> &b▎ &fChunks: &f<chunks-loaded> &b▎ &fEntities: &f<entities-amount>";
                ActionBar.send(player, CC.translate(ActionBar.format(actionBarMessage, player)));
            }
        }.runTaskTimer(SpigotTester.getInstance(), 0L, 20L);
    }
}