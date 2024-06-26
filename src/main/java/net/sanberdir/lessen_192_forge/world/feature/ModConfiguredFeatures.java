package net.sanberdir.lessen_192_forge.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.blocks.InitBlocks;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Lessen192Forge.MODID);


    private static TreeConfiguration.TreeConfigurationBuilder createFancyOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(InitBlocks.CUSTOM_LOG.get()), // Провайдер состояния блока для брёвен
                new FancyTrunkPlacer(7, 15, 0), // Высота от 7 до 15 блоков
                BlockStateProvider.simple(InitBlocks.CUSTOM_LEAVES.get()), // Провайдер состояния блока для листвы
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(5), 7), // Листва с радиусом 3, высотой 5, ширина кроны до 7 блоков
                new TwoLayersFeatureSize(2, 0, 2, OptionalInt.of(4)))) // Размер двухслойной структуры, чтобы дерево было конусообразным
                .ignoreVines(); // Игнорировать лозы
    }

    public static final RegistryObject<ConfiguredFeature<?, ?>> CUSTOM_TREE =
            CONFIGURED_FEATURES.register("custom_tree", () ->
                    new ConfiguredFeature<>(Feature.TREE, createFancyOak().build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CUSTOM_SPAWN =
            CONFIGURED_FEATURES.register("custom_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.CUSTOM_CHECKED.getHolder().get(),
                            0.8F)), ModPlacedFeatures.CUSTOM_CHECKED.getHolder().get())));



    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
