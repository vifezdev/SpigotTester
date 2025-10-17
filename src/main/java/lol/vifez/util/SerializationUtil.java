package lol.vifez.util;

import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * @author vifez
 * @project SpigotTester
 * @website https://vifez.lol
 */

public class SerializationUtil {

    public static Map<String, Object> itemToMap(ItemStack item) {
        if (item == null || item.getType() == null) item = new ItemStack(org.bukkit.Material.AIR);
        return item.serialize();
    }

    public static ItemStack mapToItem(Map<String, Object> map) {
        if (map == null || map.isEmpty()) return new ItemStack(org.bukkit.Material.AIR);
        try {
            return ItemStack.deserialize(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ItemStack(org.bukkit.Material.AIR);
        }
    }

    public static List<Map<String, Object>> itemArrayToMaps(ItemStack[] items) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (items != null) {
            for (ItemStack item : items) list.add(itemToMap(item));
        }
        return list;
    }

    public static ItemStack[] mapsToItemArray(List<Map<String, Object>> list) {
        if (list == null) return new ItemStack[36];
        ItemStack[] items = new ItemStack[list.size()];
        for (int i = 0; i < list.size(); i++) items[i] = mapToItem(list.get(i));
        return items;
    }

    public static List<Map<String, Object>> mapToMaps(Map<String, ItemStack[]> map) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, ItemStack[]> entry : map.entrySet()) {
                Map<String, Object> m = new HashMap<>();
                m.put("kit", entry.getKey());
                m.put("items", itemArrayToMaps(entry.getValue()));
                list.add(m);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, ItemStack[]> mapsToMap(List<Map<String, Object>> list) {
        Map<String, ItemStack[]> map = new HashMap<>();
        if (list != null) {
            for (Map<String, Object> m : list) {
                String kitName = (String) m.get("kit");
                List<Map<String, Object>> itemsList = (List<Map<String, Object>>) m.get("items");
                map.put(kitName, mapsToItemArray(itemsList));
            }
        }
        return map;
    }
}