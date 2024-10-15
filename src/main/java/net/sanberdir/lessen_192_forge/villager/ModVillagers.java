package net.sanberdir.lessen_192_forge.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

import static net.sanberdir.lessen_192_forge.Lessen192Forge.MODID;


public class ModVillagers {
    // Регистрируем новые типы точек интереса (POI)
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);

    // Регистрируем новые профессии жителей
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MODID);

    // Регистрируем новый POI с использованием верстака
    public static final RegistryObject<PoiType> FABRICATOR_POI = POI_TYPES.register("fabricator_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.CRAFTING_TABLE.getStateDefinition().getPossibleStates()),
                    50, 1)); // Указываем возможные состояния верстака, радиус 50 и минимальное количество жителей 1

    // Регистрируем новую профессию жителя, связанную с новым POI
    public static final RegistryObject<VillagerProfession> FABRICATOR_MASTER = VILLAGER_PROFESSIONS.register("fabricator_master",
            () -> new VillagerProfession("fabricator_master",
                    x -> x.get() == FABRICATOR_POI.get(), // Условия, по которым житель выбирает точку интереса
                    x -> x.get() == FABRICATOR_POI.get(), // Условия для рабочего места
                    ImmutableSet.of(), // Пустой набор для других условий
                    ImmutableSet.of(), // Еще один пустой набор для биомов или других условий
                    SoundEvents.VILLAGER_WORK_TOOLSMITH)); // Звук работы, когда житель использует POI




    // Регистрируем новый POI с использованием верстака
    public static final RegistryObject<PoiType> SHALKER_POI = POI_TYPES.register("shalker_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.SHULKER_BOX.getStateDefinition().getPossibleStates()),
                    50, 1)); // Указываем возможные состояния верстака, радиус 50 и минимальное количество жителей 1

    // Регистрируем новую профессию жителя, связанную с новым POI
    public static final RegistryObject<VillagerProfession> SHALKER_MASTER = VILLAGER_PROFESSIONS.register("shalker_master",
            () -> new VillagerProfession("shalker_master",
                    x -> x.get() == SHALKER_POI.get(), // Условия, по которым житель выбирает точку интереса
                    x -> x.get() == SHALKER_POI.get(), // Условия для рабочего места
                    ImmutableSet.of(), // Пустой набор для других условий
                    ImmutableSet.of(), // Еще один пустой набор для биомов или других условий
                    SoundEvents.VILLAGER_WORK_BUTCHER)); // Звук работы, когда житель использует POI

    // Регистрируем состояния блока для нового POI вручную через рефлексию
    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, FABRICATOR_POI.get());
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, SHALKER_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace(); // Обрабатываем возможные исключения
        }
    }

    // Метод регистрации для добавления всех наших объектов в игру
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus); // Регистрируем POI
        VILLAGER_PROFESSIONS.register(eventBus); // Регистрируем профессии
    }
}