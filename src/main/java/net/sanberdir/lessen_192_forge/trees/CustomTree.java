package net.sanberdir.lessen_192_forge.trees;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.sanberdir.lessen_192_forge.world.feature.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class CustomTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean p_222911_) {
        return  ModConfiguredFeatures.CUSTOM_TREE.getHolder().get();
    }
}
