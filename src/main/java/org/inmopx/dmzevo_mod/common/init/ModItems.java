package org.inmopx.dmzevo_mod.common.init;

import com.dragonminez.common.init.armor.DbzArmorItem;
import com.dragonminez.common.init.armor.ModArmorMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.inmopx.dmzevo_mod.DMZEvoMod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DMZEvoMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DMZEvoMod.MOD_ID);

    public static final RegistryObject<Item> TAB_ICON = ITEMS.register("tab_icon",
            () -> new Item(new Item.Properties()));

    public static final Map<ArmorItem.Type, RegistryObject<Item>> DAISHIN_ARMOR =
            registerArmorSet("daishin_armor", "daishin");
    public static final Map<ArmorItem.Type, RegistryObject<Item>> BILLS_ARMOR =
            registerArmorSet("bills_armor", "bills");
    public static final Map<ArmorItem.Type, RegistryObject<Item>> ZENO_ARMOR =
            registerArmorSet("zeno_armor", "zeno");

    public static final RegistryObject<CreativeModeTab> DMZ_EVO_TAB = TABS.register("tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.dmzevo_mod.tab"))
                    .icon(() -> new ItemStack(TAB_ICON.get()))
                    .displayItems((params, output) -> {
                        output.accept(DAISHIN_ARMOR.get(ArmorItem.Type.CHESTPLATE).get());
                        output.accept(DAISHIN_ARMOR.get(ArmorItem.Type.LEGGINGS).get());
                        output.accept(DAISHIN_ARMOR.get(ArmorItem.Type.BOOTS).get());
                        output.accept(BILLS_ARMOR.get(ArmorItem.Type.CHESTPLATE).get());
                        output.accept(BILLS_ARMOR.get(ArmorItem.Type.LEGGINGS).get());
                        output.accept(BILLS_ARMOR.get(ArmorItem.Type.BOOTS).get());
                        output.accept(ZENO_ARMOR.get(ArmorItem.Type.CHESTPLATE).get());
                        output.accept(ZENO_ARMOR.get(ArmorItem.Type.LEGGINGS).get());
                        output.accept(ZENO_ARMOR.get(ArmorItem.Type.BOOTS).get());
                    })
                    .build()
    );

    private static Map<ArmorItem.Type, RegistryObject<Item>> registerArmorSet(
            String itemPrefix, String textureBase) {
        Map<ArmorItem.Type, RegistryObject<Item>> pieces = new HashMap<>();
        pieces.put(ArmorItem.Type.CHESTPLATE, ITEMS.register(itemPrefix + "_chestplate",
                () -> new EvoDbzArmorItem(ArmorItem.Type.CHESTPLATE, textureBase)));
        pieces.put(ArmorItem.Type.LEGGINGS, ITEMS.register(itemPrefix + "_leggings",
                () -> new EvoDbzArmorItem(ArmorItem.Type.LEGGINGS, textureBase)));
        pieces.put(ArmorItem.Type.BOOTS, ITEMS.register(itemPrefix + "_boots",
                () -> new EvoDbzArmorItem(ArmorItem.Type.BOOTS, textureBase)));
        return pieces;
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        TABS.register(bus);
    }

    private static class EvoDbzArmorItem extends DbzArmorItem {

        public EvoDbzArmorItem(Type type, String textureBase) {
            super(ModArmorMaterials.KIKONO, type,
                    new Item.Properties().fireResistant().stacksTo(1),
                    textureBase, false);
        }

        @Override
        public @Nullable String getArmorTexture(ItemStack stack, Entity entity,
                                                EquipmentSlot slot, String type) {
            if ("inner_armor".equals(type) || "outer_armor".equals(type)) {
                return "dragonminez:textures/armor/blank.png";
            }
            String base = DMZEvoMod.MOD_ID + ":textures/armor/" + getItemId();
            return slot == EquipmentSlot.LEGS
                    ? base + "_layer2.png"
                    : base + "_layer1.png";
        }

    }
}