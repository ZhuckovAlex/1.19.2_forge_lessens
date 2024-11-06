package net.sanberdir.lessen_192_forge.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.blocks.custom.*;
import net.sanberdir.lessen_192_forge.items.InitItems;
import net.sanberdir.lessen_192_forge.tab.ModCreativeTab;
import net.sanberdir.lessen_192_forge.trees.CustomTree;

import java.util.function.Supplier;

public class InitBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Lessen192Forge.MODID);


    public static final RegistryObject<Block> WHEAT = registerBlockWithoutBlockItem("wheat",
            () -> new CustomWheat(BlockBehaviour.Properties.of(Material.PLANT).noCollission()
                    .randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> CUSTOM_PLANKS = registerBlock("custom_planks",
            () -> new FlameBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0F, 5.0F)
                    .sound(SoundType.STEM)), ModCreativeTab.SWEET_TAB);

    public static final RegistryObject<Block> CUSTOM_LEAVES = registerBlockWithoutBlockItem("custom_leaves",
            () -> new FlameLeaves(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.GRASS).strength(0.1F, 1.0F)
                    .sound(SoundType.AZALEA_LEAVES).noOcclusion()));

    public static final RegistryObject<Block> CUSTOM_SAPLING = registerBlockWithoutBlockItem("custom_sapling",
            () -> new SaplingBlock(new CustomTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission()
                    .randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> CUSTOM_STAIRS = registerBlock("custom_stairs",
            () -> new FlameStairsBlock(() -> InitBlocks.CUSTOM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD).strength(2).sound(SoundType.WOOD)),
            ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_SLAB = registerBlock("custom_slab",
            () -> new FlameSlabBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2).sound(SoundType.WOOD)), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_BUTTON = registerBlock("custom_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION)
                    .strength(1).sound(SoundType.WOOD)), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_PRESSURE_PLATE = registerBlock("custom_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(1).sound(SoundType.WOOD)), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_DOOR = registerBlock("custom_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2).sound(SoundType.WOOD).noOcclusion()), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_TRAPDOOR = registerBlock("custom_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2).sound(SoundType.WOOD).noOcclusion()), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_FENCE = registerBlockWithoutBlockItem("custom_fence",
            () -> new FlameFenceBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CUSTOM_FENCE_GATE = registerBlockWithoutBlockItem("custom_fence_gate",
            () -> new FlameGateFenceBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CUSTOM_WOOD = registerBlock("custom_wood",
            () -> new FlameBlockRotate(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0F, 5.0F)
                    .sound(SoundType.STEM)), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> CUSTOM_LOG = registerBlock("custom_log",
            () -> new FlameBlockRotate(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0F, 5.0F)
                    .sound(SoundType.STEM)), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> STRIPPED_CUSTOM_LOG = registerBlock("stripped_custom_log",
            () -> new FlameBlockRotate(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0F, 5.0F)
                    .sound(SoundType.STEM)), ModCreativeTab.SWEET_TAB);
    public static final RegistryObject<Block> STRIPPED_CUSTOM_WOOD = registerBlock("stripped_custom_wood",
            () -> new FlameBlockRotate(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0F, 5.0F)
                    .sound(SoundType.STEM)), ModCreativeTab.SWEET_TAB);
    
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return InitItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
