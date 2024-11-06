package net.sanberdir.lessen_192_forge.entity.boat;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.entity.chest_boat.ModChestBoatEntity;




public class ModEntityData {
    @ObjectHolder(registryName = "minecraft:entity_type", value = Lessen192Forge.MODID + ":mod_boat_entity")
    public static EntityType<ModBoatEntity> MOD_BOAT_DATA;

    @ObjectHolder(registryName = "minecraft:entity_type", value = Lessen192Forge.MODID + ":mod_chest_boat_entity")
    public static EntityType<ModChestBoatEntity> MOD_CHEST_BOAT_DATA;
}
