package net.sanberdir.lessen_192_forge.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.sanberdir.lessen_192_forge.ModWoodType;
import net.sanberdir.lessen_192_forge.entity.sign.ModEntitiesBlock;
import net.sanberdir.lessen_192_forge.entity.sign.ModSignBlockEntity;

public class CustomWallSign extends WallSignBlock {
    public CustomWallSign() {
        super(Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(() -> ModEntitiesBlock.CUSTOM_SIGN), ModWoodType.CUSTOM_WOOD);
    }

    /**
     * Gets the sign block entity for this sign
     *
     * @param position Position of the sign in the level
     * @param blockState State of the sign
     * @return The block entity for this sign
     */
    @Override
    public BlockEntity newBlockEntity(BlockPos position, BlockState blockState) {
        return new ModSignBlockEntity(position, blockState);
    }
}