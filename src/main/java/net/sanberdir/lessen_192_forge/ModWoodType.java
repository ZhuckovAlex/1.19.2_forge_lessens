package net.sanberdir.lessen_192_forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;


public class ModWoodType {
    public static WoodType CUSTOM_WOOD = WoodType.create(new ResourceLocation(Lessen192Forge.MODID, "custom_wood").toString());
}

