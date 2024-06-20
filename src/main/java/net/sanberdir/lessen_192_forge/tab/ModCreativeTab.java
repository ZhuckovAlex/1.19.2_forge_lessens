package net.sanberdir.lessen_192_forge.tab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.sanberdir.lessen_192_forge.items.InitItems;

public class ModCreativeTab {

    public static final CreativeModeTab SWEET_TAB = new CreativeModeTab("sweet_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(InitItems.SWEET_CAKE.get());
        }
    };

}
