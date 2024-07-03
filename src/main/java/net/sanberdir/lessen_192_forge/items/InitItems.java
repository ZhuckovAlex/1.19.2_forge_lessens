package net.sanberdir.lessen_192_forge.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.blocks.InitBlocks;
import net.sanberdir.lessen_192_forge.items.custom.BurnFences;
import net.sanberdir.lessen_192_forge.items.custom.MiniCoal;
import net.sanberdir.lessen_192_forge.tab.ModCreativeTab;

public class InitItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Lessen192Forge.MODID);
    public static final RegistryObject<Item> CUSTOM_LEAVES = ITEMS.register("custom_leaves",
            () -> new ItemNameBlockItem(InitBlocks.CUSTOM_LEAVES.get(),(new Item.Properties().tab(ModCreativeTab.SWEET_TAB))));
    public static final RegistryObject<Item> CUSTOM_SAPLING = ITEMS.register("custom_sapling",
            () -> new ItemNameBlockItem(InitBlocks.CUSTOM_SAPLING.get(),(new Item.Properties().tab(ModCreativeTab.SWEET_TAB))));
    public static final RegistryObject<Item> CUSTOM_FENCE = ITEMS.register("custom_fence",
            () -> new BurnFences(InitBlocks.CUSTOM_FENCE.get(),(new Item.Properties().tab(ModCreativeTab.SWEET_TAB))));
    public static final RegistryObject<Item> CUSTOM_FENCE_GATE = ITEMS.register("custom_fence_gate",
            () -> new BurnFences(InitBlocks.CUSTOM_FENCE_GATE.get(),(new Item.Properties().tab(ModCreativeTab.SWEET_TAB))));
    public static final RegistryObject<Item> SWEET_CAKE = ITEMS.register("sweet_cake",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.SWEET_TAB).food(new FoodProperties.Builder()
                    .nutrition(8).saturationMod(0.5F)
                    .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 4), 1)
                    .effect(new MobEffectInstance(MobEffects.JUMP, 3600, 3), 0.5F)
                    .build())));
    public static final RegistryObject<Item> MINI_COAL = ITEMS.register("mini_coal",
            () -> new MiniCoal(new Item.Properties().tab(ModCreativeTab.SWEET_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
