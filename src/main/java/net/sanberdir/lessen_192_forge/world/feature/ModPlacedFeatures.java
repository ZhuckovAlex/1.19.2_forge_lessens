package net.sanberdir.lessen_192_forge.world.feature;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.blocks.InitBlocks;

import java.util.List;
public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Lessen192Forge.MODID);

    // Кусты


    public static final RegistryObject<PlacedFeature> CUSTOM_CHECKED = PLACED_FEATURES.register("custom_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.CUSTOM_TREE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(InitBlocks.CUSTOM_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> CUSTOM_PLACED = PLACED_FEATURES.register("custom_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.CUSTOM_SPAWN.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(1),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
