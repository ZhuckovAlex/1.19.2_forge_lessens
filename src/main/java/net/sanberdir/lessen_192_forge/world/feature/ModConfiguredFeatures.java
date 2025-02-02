package net.sanberdir.lessen_192_forge.world.feature;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
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
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.blocks.InitBlocks;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

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



    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, InitBlocks.ZIRCON_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, InitBlocks.DEEPSLATE_ZIRCON_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), InitBlocks.ENDSTONE_ZIRCON_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, InitBlocks.NETHERRACK_ZIRCON_ORE.get().defaultBlockState())));


    public static final RegistryObject<ConfiguredFeature<?, ?>> ZIRCON_ORE = CONFIGURED_FEATURES.register("zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ZIRCON_ORES.get(),7)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> END_ZIRCON_ORE = CONFIGURED_FEATURES.register("end_zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_ZIRCON_ORES.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_ZIRCON_ORE = CONFIGURED_FEATURES.register("nether_zircon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_ZIRCON_ORES.get(), 9)));


    public static final RegistryObject<ConfiguredFeature<?, ?>> ZIRCON_GEODE = CONFIGURED_FEATURES.register("zircon_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, // Определяем, что это геодовая структура
                    new GeodeConfiguration(
                            new GeodeBlockSettings(
                                    BlockStateProvider.simple(Blocks.AIR),                  // Внутреннее пространство геоды (пустота)
                                    BlockStateProvider.simple(Blocks.DEEPSLATE),            // Внешняя оболочка геоды (например, из глубинного сланца)
                                    BlockStateProvider.simple(InitBlocks.ZIRCON_ORE.get()), // Средний слой геоды (цирконовая руда)
                                    BlockStateProvider.simple(Blocks.DIRT),                 // Дополнительный слой (например, грязь)
                                    BlockStateProvider.simple(Blocks.EMERALD_BLOCK),        // Внутренний кристаллический слой (изумрудные блоки)
                                    List.of(InitBlocks.CUSTOM_LOG.get().defaultBlockState()), // Особые блоки внутри геоды (например, кастомное бревно)
                                    BlockTags.FEATURES_CANNOT_REPLACE, // Блоки, которые не могут быть заменены геодой
                                    BlockTags.GEODE_INVALID_BLOCKS     // Блоки, внутри которых геода не может появиться
                            ),
                            new GeodeLayerSettings(1.7D, 1.2D, 2.5D, 3.5D), // Настройки толщины слоев геоды
                            new GeodeCrackSettings(0.25D, 1.5D, 1),  // Настройки трещин в геоде
                            0.5D,  // Вероятность появления дополнительного заполнения внутри геоды
                            0.1D,  // Шанс генерации геоды
                            true,  // Может ли геода появляться на поверхности
                            UniformInt.of(3, 8), // Количество рудных блоков внутри геоды
                            UniformInt.of(2, 6), // Размер среднего слоя
                            UniformInt.of(1, 2), // Размер внешнего слоя
                            -32, 20, // Диапазон генерации геоды по высоте (от -18 до 18)
                            0.075D, // Шанс появления геоды в чанке
                            1       // Количество геод на чанк
                    )));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
