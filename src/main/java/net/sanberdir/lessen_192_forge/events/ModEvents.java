package net.sanberdir.lessen_192_forge.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sanberdir.lessen_192_forge.Lessen192Forge;
import net.sanberdir.lessen_192_forge.items.InitItems;
import net.sanberdir.lessen_192_forge.villager.ModVillagers;

import java.util.List;

@Mod.EventBusSubscriber(modid = Lessen192Forge.MODID) // Класс будет слушать события от EventBus для мода с указанным MODID
public class ModEvents {

    // Подписываемся на событие добавления новых торговых предложений жителям
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        // Если тип жителя - фермер
        if(event.getType() == VillagerProfession.FARMER) {
            // Получаем список торговых предложений для фермера
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // Создаем предмет для торговли - обычные листья (1 штука)
            int villagerLevel = 1; // Устанавливаем уровень жителя, при котором доступно предложение

            // Добавляем новое торговое предложение для фермера на уровне 1
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND, 2), // Что покупаем
                    new ItemStack(InitItems.CUSTOM_LEAVES.get(), 3), // Что продаём
                    10, // Максимальное количество сделок
                    8, // Максимальный опыт, который получит житель за сделку
                    0.02F)); // Шанс повышения цены
        }

        // Если тип жителя - наш кастомный "Fabricator Master"
        if (event.getType() == ModVillagers.FABRICATOR_MASTER.get()) {
            // Получаем список торговых предложений для "Fabricator Master"
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Уровень 1: Продаёт настраиваемые листья
            int villagerLevel = 1;
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5), // Цена: 5 изумрудов
                    new ItemStack(InitItems.CUSTOM_LEAVES.get(), 15), // Что житель даёт: 15 настраиваемых листьев
                    10, // Максимальное количество сделок
                    8, // Максимальный опыт
                    0.02F)); // Шанс повышения цены

            // Уровень 2: Торги книгами с чаром "Починка" (Mending)
            villagerLevel = 2;

            // 1. Fabricator Master продаёт книгу с чаром "Починка" (Mending)
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20), // Цена: 20 изумрудов
                    EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.MENDING, 1)), // Продает книгу с чаром "Починка"
                    5, 10, 0.05F));

            // 2. Fabricator Master покупает книгу с чаром "Удача"
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.BLOCK_FORTUNE, 3)), // Покупает книгу с чаром "Починка"
                    new ItemStack(Items.EMERALD, 10), // Платит 10 изумрудов
                    10, 10, 0.02F));

            // Уровень 3: Торги зельями
            villagerLevel = 3;

            // 3. Fabricator Master продает зелье силы
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7), // Цена: 7 изумрудов
                    PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH), // Продает зелье силы
                    6, 10, 0.05F));

            // 4. Fabricator Master покупает зелье исцеления
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING), // Покупает зелье исцеления
                    new ItemStack(Items.BOOK, 4), // Платит 4 книги
                    8, 10, 0));

            // 3. Fabricator Master продает зелье силы
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7), // Цена: 7 изумрудов
                    PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.STRENGTH), // Продает зелье силы
                    6, 10, 0.05F));

            // 4. Fabricator Master покупает зелье исцеления
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_HEALING), // Покупает зелье исцеления
                    new ItemStack(Items.BOOK, 4), // Платит 4 книги
                    8, 10, 0));

            // 3. Fabricator Master продает зелье силы
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7), // Цена: 7 изумрудов
                    PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), Potions.STRENGTH), // Продает зелье силы
                    6, 10, 0.05F));

            // 4. Fabricator Master покупает зелье исцеления
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.STRONG_HEALING), // Покупает зелье исцеления
                    new ItemStack(Items.BOOK, 4), // Платит 4 книги
                    8, 10, 0));
        }

        if (event.getType() == ModVillagers.SHALKER_MASTER.get()) {
            // Получаем список торговых предложений для "Fabricator Master"
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Уровень 1: Продаёт настраиваемые листья
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.ACACIA_LEAVES, 15), // Что житель даёт: 15 настраиваемых листьев
                    new ItemStack(Items.EMERALD, 5), // Цена: 5 изумрудов
                    10, // Максимальное количество сделок
                    8, // Максимальный опыт
                    0.02F)); // Шанс повышения цены

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5), // Цена: 5 изумрудов
                    new ItemStack(Items.BLUE_SHULKER_BOX, 2), // Что житель даёт: 15 настраиваемых листьев
                    10, // Максимальное количество сделок
                    8, // Максимальный опыт
                    0.02F)); // Шанс повышения цены
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5), // Цена: 5 изумрудов
                    new ItemStack(Items.RED_SHULKER_BOX, 7), // Что житель даёт: 15 настраиваемых листьев
                    10, // Максимальное количество сделок
                    8, // Максимальный опыт
                    0.02F)); // Шанс повышения цены

            villagerLevel = 2;
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5), // Цена: 5 изумрудов
                    new ItemStack(Items.RED_SHULKER_BOX, 2), // Что житель даёт: 15 настраиваемых листьев
                    10, // Максимальное количество сделок
                    8, // Максимальный опыт
                    0.02F)); // Шанс повышения цены
        }
    }
}