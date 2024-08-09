package net.sanberdir.lessen_192_forge.entity.sign;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sanberdir.lessen_192_forge.blocks.ModBlockEntities;

public class ModSignBlockEntity extends SignBlockEntity {

    public ModSignBlockEntity(BlockPos position, BlockState blockState) {
        super(position, blockState);
    }

    /**
     * Gets the type for the sign block
     *
     * @return Sign block type
     */
    @Override
    public BlockEntityType<ModSignBlockEntity> getType() {
        return ModBlockEntities.SIGN_ENTITY_TYPE;
    }
}