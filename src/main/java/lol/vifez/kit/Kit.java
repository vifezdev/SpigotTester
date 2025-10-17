package lol.vifez.kit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class Kit {

    private final String name;
    private ItemStack[] contents = new ItemStack[36];
    private ItemStack icon;

    public Kit(String name) {
        this.name = name;
    }
}