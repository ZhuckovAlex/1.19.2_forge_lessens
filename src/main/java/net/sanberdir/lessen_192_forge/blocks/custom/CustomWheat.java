package net.sanberdir.lessen_192_forge.blocks.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.sanberdir.lessen_192_forge.items.InitItems;

public class CustomWheat extends CropBlock {
    public CustomWheat(Properties properties) {
        super(properties);
    }

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);

    @Override
    protected ItemLike getBaseSeedId() {
        return InitItems.WHEAT_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
