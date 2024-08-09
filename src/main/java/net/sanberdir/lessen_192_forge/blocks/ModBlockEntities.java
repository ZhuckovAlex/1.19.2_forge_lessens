package net.sanberdir.lessen_192_forge.blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.sanberdir.lessen_192_forge.entity.sign.ModEntitiesBlock;
import net.sanberdir.lessen_192_forge.entity.sign.ModSignBlockEntity;

public class ModBlockEntities {
    public static BlockEntityType<ModSignBlockEntity> SIGN_ENTITY_TYPE = BlockEntityType.Builder.of(ModSignBlockEntity::new, ModEntitiesBlock.CUSTOM_SIGN, ModEntitiesBlock.CUSTOM_WALL_SIGN).build(null);
}
