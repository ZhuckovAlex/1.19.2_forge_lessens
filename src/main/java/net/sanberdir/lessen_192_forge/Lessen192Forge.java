package net.sanberdir.lessen_192_forge;

import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.sanberdir.lessen_192_forge.blocks.InitBlocks;
import net.sanberdir.lessen_192_forge.blocks.ModBlockEntities;
import net.sanberdir.lessen_192_forge.blocks.custom.CustomSign;
import net.sanberdir.lessen_192_forge.blocks.custom.CustomWallSign;
import net.sanberdir.lessen_192_forge.entity.ClientOnlyRegistrar;
import net.sanberdir.lessen_192_forge.entity.EntityTypeInitializer;
import net.sanberdir.lessen_192_forge.entity.boat.ModBoatRenderer;
import net.sanberdir.lessen_192_forge.entity.boat.ModEntityData;
import net.sanberdir.lessen_192_forge.entity.chest_boat.ModChestBoatRenderer;
import net.sanberdir.lessen_192_forge.entity.sign.ModEntitiesBlock;
import net.sanberdir.lessen_192_forge.items.InitItems;
import net.sanberdir.lessen_192_forge.items.custom.CustomBoat;
import net.sanberdir.lessen_192_forge.items.custom.CustomChestBoat;
import net.sanberdir.lessen_192_forge.tab.ModCreativeTab;
import net.sanberdir.lessen_192_forge.world.feature.ModConfiguredFeatures;
import net.sanberdir.lessen_192_forge.world.feature.ModPlacedFeatures;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Lessen192Forge.MODID)
@Mod.EventBusSubscriber(modid = Lessen192Forge.MODID)
public class Lessen192Forge
{

    // Define mod id in a common place for everything to reference
    public static final String MODID = "lessen_192_forge";
    // Directly reference a slf4j logger

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public Lessen192Forge()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        
        ITEMS.register(modEventBus);
        InitItems.register(modEventBus);
        InitBlocks.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);

        ModPlacedFeatures.register(modEventBus);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ClientOnlyRegistrar clientOnlyRegistrar = new ClientOnlyRegistrar(modEventBus);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> clientOnlyRegistrar::registerClientOnlyEvents);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
               WoodType.register(ModWoodType.CUSTOM_WOOD);

        });
    }




    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegisterEvent registerEvent) {
            registerEvent.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, registrar -> {


                // All sign block entities
                registrar.register(new ResourceLocation(MODID, "mod_sign_entity"), ModBlockEntities.SIGN_ENTITY_TYPE);

            });
        }
        @SubscribeEvent
        public static void onBlocksRegistry(final RegisterEvent registryEvent) {
            registryEvent.register(ForgeRegistries.Keys.BLOCKS, registrar -> {
                registrar.register(new ResourceLocation(MODID, "custom_sign"), new CustomSign());
                registrar.register(new ResourceLocation(MODID, "custom_wall_sign"), new CustomWallSign());
            });
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegisterEvent registerEvent) {
            registerEvent.register(ForgeRegistries.Keys.ITEMS, registrar -> {
                registrar.register(new ResourceLocation(Lessen192Forge.MODID, "custom_boat"), new CustomBoat());
                registrar.register(new ResourceLocation(Lessen192Forge.MODID, "custom_chest_boat"), new CustomChestBoat());
                registrar.register(new ResourceLocation(Lessen192Forge.MODID, "custom_sign"), new SignItem(new Item.Properties().tab(ModCreativeTab.SWEET_TAB), ModEntitiesBlock.CUSTOM_SIGN, ModEntitiesBlock.CUSTOM_WALL_SIGN));
            });
        }
        @SubscribeEvent
        public static void onEntityRegistry(final RegisterEvent registerEvent) {
            registerEvent.register(ForgeRegistries.Keys.ENTITY_TYPES, registrar -> {
                // All vehicle entities

                registrar.register(new ResourceLocation(Lessen192Forge.MODID, "mod_boat_entity"), EntityTypeInitializer.BOAT_ENTITY_TYPE);
                registrar.register(new ResourceLocation(Lessen192Forge.MODID, "mod_chest_boat_entity"), EntityTypeInitializer.CHEST_BOAT_ENTITY_TYPE);
            });
        }


    }






    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onEntityRendererRegistry(final EntityRenderersEvent.RegisterRenderers registerEntityEvent) {
            registerEntityEvent.registerEntityRenderer(ModEntityData.MOD_BOAT_DATA, ModBoatRenderer::new);
            registerEntityEvent.registerBlockEntityRenderer(ModBlockEntities.SIGN_ENTITY_TYPE, SignRenderer::new);

            registerEntityEvent.registerEntityRenderer(ModEntityData.MOD_CHEST_BOAT_DATA, ModChestBoatRenderer::new);

        }
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                ComposterBlock.COMPOSTABLES.put(InitItems.CUSTOM_LEAVES.get(), 0.2f);
                ComposterBlock.COMPOSTABLES.put(InitItems.CUSTOM_SAPLING.get(), 0.2f);
                ComposterBlock.COMPOSTABLES.put(InitItems.WHEAT.get(), 0.4f);
                ComposterBlock.COMPOSTABLES.put(InitItems.WHEAT_SEEDS.get(), 0.2f);
            });
        }
    }
}
