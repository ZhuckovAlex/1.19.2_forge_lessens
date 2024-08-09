package net.sanberdir.lessen_192_forge.items;

import net.minecraftforge.registries.ObjectHolder;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.items.custom.CustomBoat;
import net.sanberdir.lessen_192_forge.items.custom.CustomChestBoat;


public class ModEntitiesItem {
    @ObjectHolder(registryName = "minecraft:item", value = Lessen192Forge.MODID + ":custom_boat")
    public static CustomBoat CUSTOM_BOAT;
    @ObjectHolder(registryName = "minecraft:item", value = Lessen192Forge.MODID + ":custom_chest_boat")
    public static CustomChestBoat CUSTOM_CHEST_BOAT;
}
