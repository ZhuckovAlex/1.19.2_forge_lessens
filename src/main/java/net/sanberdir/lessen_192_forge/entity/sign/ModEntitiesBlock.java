package net.sanberdir.lessen_192_forge.entity.sign;

import net.minecraftforge.registries.ObjectHolder;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.blocks.custom.CustomSign;
import net.sanberdir.lessen_192_forge.blocks.custom.CustomWallSign;

public class ModEntitiesBlock {
    @ObjectHolder(registryName = "minecraft:block", value = Lessen192Forge.MODID + ":custom_sign")
    public static CustomSign CUSTOM_SIGN;

    @ObjectHolder(registryName = "minecraft:block", value = Lessen192Forge.MODID + ":custom_wall_sign")
    public static CustomWallSign CUSTOM_WALL_SIGN;

}
