package lol.vifez.kit.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lol.vifez.SpigotTester;
import lol.vifez.kit.Kit;
import lol.vifez.kit.KitManager;
import lol.vifez.util.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

@CommandAlias("kit")
public class KitCommand extends BaseCommand {

    private final KitManager kitManager = SpigotTester.getInstance().getKitManager();

    @Default
    @CommandPermission("test.kit")
    public void onDefault(Player player) {
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&b&lKit Commands"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&7* &f/kit create <kit>"));
        player.sendMessage(CC.translate("&7* &f/kit delete <kit>"));
        player.sendMessage(CC.translate("&7* &f/kit setActive <kit>"));
        player.sendMessage(CC.translate("&7* &f/kit setContents <kit>"));
        player.sendMessage(CC.translate("&7* &f/kit getContents <kit>"));
        player.sendMessage(CC.translate("&7* &f/kit setIcon <kit>"));
        player.sendMessage(CC.translate("&7* &f/kit list"));
        player.sendMessage(" ");
    }

    @Subcommand("create")
    @CommandPermission("test.kit")
    public void onCreate(Player player, String name) {
        if (kitManager.getKit(name) != null) {
            player.sendMessage(CC.translate("&cb" + name + " &calready exists!"));
            return;
        }
        kitManager.createKit(name);
        player.sendMessage(CC.translate("&b" + name + " &fhas been created!"));
    }

    @Subcommand("delete")
    @CommandPermission("test.kit")
    public void onDelete(Player player, String name) {
        if (kitManager.getKit(name) == null) {
            player.sendMessage(CC.translate("&b" + name + " &cdoes not exist!"));
            return;
        }
        kitManager.deleteKit(name);
        player.sendMessage(CC.translate("&b" + name + " &chas been deleted!"));
    }

    @Subcommand("setActive")
    @CommandPermission("test.kit")
    public void onSetActive(Player player, String name) {
        Kit kit = kitManager.getKit(name);
        if (kit == null) {
            player.sendMessage(CC.translate("&b" + name + " &cdoes not exist!"));
            return;
        }
        kitManager.setActiveKit(name);
        player.sendMessage(CC.translate("&b" + name + " &ais now active!"));
    }

    @Subcommand("setContents")
    @CommandPermission("test.kit")
    public void onSetContents(Player player, String name) {
        Kit kit = kitManager.getKit(name);
        if (kit == null) {
            player.sendMessage(CC.translate("&b" + name + " &ckit does not exist!"));
            return;
        }
        ItemStack[] contents = player.getInventory().getContents();
        kitManager.setKitContents(name, contents);
        player.sendMessage(CC.translate("&b" + name + " &fcontents have been saved!"));
    }

    @Subcommand("getContents")
    @CommandPermission("test.kit")
    public void onGetContents(Player player, String name) {
        Kit kit = kitManager.getKit(name);
        if (kit == null) {
            player.sendMessage(CC.translate("&cb" + name + " &cdoes not exist!"));
            return;
        }
        ItemStack[] contents = kit.getContents();
        if (contents == null) contents = new ItemStack[36];
        player.getInventory().clear();
        player.getInventory().setContents(contents);
        player.sendMessage(CC.translate("&aYou have received &b" + name + " kit"));
    }

    @Subcommand("setIcon")
    @CommandPermission("test.kit")
    public void onSetIcon(Player player, String name) {
        Kit kit = kitManager.getKit(name);
        if (kit == null) {
            player.sendMessage(CC.translate("&b" + name + " &cdoes not exist!"));
            return;
        }
        ItemStack item = player.getItemInHand();
        if (item == null || item.getType() == Material.AIR) {
            player.sendMessage(CC.translate("&cNothing in your hand"));
            return;
        }
        kitManager.setKitIcon(name, item);
        player.sendMessage(CC.translate("&fYou have set &b" + name + "'s &ficon"));
    }


    @Subcommand("list")
    @CommandPermission("test.kit")
    public void onList(Player player) {
        player.sendMessage(CC.translate("&bAvailable Kits:"));
        if (kitManager.getKits().isEmpty()) {
            player.sendMessage(CC.translate("&7* &cNo kits have been created yet!"));
            return;
        }
        for (Kit kit : kitManager.getKits()) {
            String active = kitManager.getActiveKit() == kit ? " &a(Active)" : "";
            player.sendMessage(CC.translate("&7* &f" + kit.getName() + active));
        }
    }
}