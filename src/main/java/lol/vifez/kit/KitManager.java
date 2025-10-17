package lol.vifez.kit;

import lol.vifez.SpigotTester;
import lol.vifez.util.ConfigFile;
import lol.vifez.util.SerializationUtil;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class KitManager {

    private final ConfigFile config;
    private final Map<String, Kit> kits = new HashMap<>();
    @Getter
    private Kit activeKit;

    public KitManager() {
        this.config = new ConfigFile(SpigotTester.getInstance(), "kits.yml");
        loadKits();
    }

    public void createKit(String name) {
        if (!kits.containsKey(name)) {
            Kit kit = new Kit(name);
            kit.setIcon(new ItemStack(Material.BOOK));
            kits.put(name, kit);
            saveKits();
        }
    }


    public void deleteKit(String name) {
        if (kits.containsKey(name)) {
            kits.remove(name);
            if (activeKit != null && activeKit.getName().equalsIgnoreCase(name)) {
                activeKit = null;
            }
            saveKits();
        }
    }

    public void setActiveKit(String name) {
        Kit kit = kits.get(name);
        if (kit != null) {
            activeKit = kit;
            saveKits();
        }
    }

    public void setKitContents(String name, ItemStack[] contents) {
        Kit kit = kits.get(name);
        if (kit != null) {
            kit.setContents(contents);
            saveKits();
        }
    }

    public ItemStack[] getKitContents(String name) {
        Kit kit = kits.get(name);
        if (kit != null) return kit.getContents();
        return new ItemStack[36];
    }

    public void setKitIcon(String name, ItemStack icon) {
        Kit kit = kits.get(name);
        if (kit != null) {
            kit.setIcon(icon);
            saveKits();
        }
    }

    public Kit getKit(String name) {
        return kits.get(name);
    }

    public Collection<Kit> getKits() {
        return kits.values();
    }

    @SuppressWarnings("unchecked")
    private void loadKits() {
        List<Map<?, ?>> rawList = config.getConfiguration().getMapList("kits");
        List<Map<String, Object>> list = new ArrayList<>();

        for (Map<?, ?> rawMap : rawList) {
            list.add((Map<String, Object>) rawMap);
        }

        Map<String, ItemStack[]> map = SerializationUtil.mapsToMap(list);
        for (Map.Entry<String, ItemStack[]> entry : map.entrySet()) {
            Kit kit = new Kit(entry.getKey());
            kit.setContents(entry.getValue());
            kits.put(entry.getKey(), kit);
        }

        String active = config.getString("activeKit");
        if (active != null && kits.containsKey(active)) {
            activeKit = kits.get(active);
        }
    }

    public void saveKits() {
        Map<String, ItemStack[]> map = new HashMap<>();
        for (Kit kit : kits.values()) {
            map.put(kit.getName(), kit.getContents());
        }
        List<Map<String, Object>> list = SerializationUtil.mapToMaps(map);
        config.set("kits", list);
        config.set("activeKit", activeKit != null ? activeKit.getName() : null);
        config.save();
    }
}