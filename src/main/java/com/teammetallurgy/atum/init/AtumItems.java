package com.teammetallurgy.atum.init;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.api.AtumMats;
import com.teammetallurgy.atum.items.*;
import com.teammetallurgy.atum.items.artifacts.anput.AnputsHungerItem;
import com.teammetallurgy.atum.items.artifacts.anubis.AnubisMercyItem;
import com.teammetallurgy.atum.items.artifacts.anubis.AnubisWrathItem;
import com.teammetallurgy.atum.items.artifacts.atum.*;
import com.teammetallurgy.atum.items.artifacts.geb.GebsGroundingItem;
import com.teammetallurgy.atum.items.artifacts.geb.GebsMightItem;
import com.teammetallurgy.atum.items.artifacts.geb.GebsToilItem;
import com.teammetallurgy.atum.items.artifacts.horus.HorusAscensionItem;
import com.teammetallurgy.atum.items.artifacts.horus.HorusSoaringItem;
import com.teammetallurgy.atum.items.artifacts.isis.IsisHealingItem;
import com.teammetallurgy.atum.items.artifacts.montu.MontusBlastItem;
import com.teammetallurgy.atum.items.artifacts.montu.MontusStrikeItem;
import com.teammetallurgy.atum.items.artifacts.nuit.NuitsDualityItem;
import com.teammetallurgy.atum.items.artifacts.nuit.NuitsIreItem;
import com.teammetallurgy.atum.items.artifacts.nuit.NuitsQuarterItem;
import com.teammetallurgy.atum.items.artifacts.nuit.NuitsVanishingItem;
import com.teammetallurgy.atum.items.artifacts.ptah.PtahsDecadenceItem;
import com.teammetallurgy.atum.items.artifacts.ptah.PtahsUndoingItem;
import com.teammetallurgy.atum.items.artifacts.ra.*;
import com.teammetallurgy.atum.items.artifacts.seth.SethsStingItem;
import com.teammetallurgy.atum.items.artifacts.seth.SethsVenomItem;
import com.teammetallurgy.atum.items.artifacts.shu.ShusBreathItem;
import com.teammetallurgy.atum.items.artifacts.shu.ShusExileItem;
import com.teammetallurgy.atum.items.artifacts.shu.ShusSwiftnessItem;
import com.teammetallurgy.atum.items.artifacts.tefnut.TefnutsBlessingItem;
import com.teammetallurgy.atum.items.artifacts.tefnut.TefnutsCallItem;
import com.teammetallurgy.atum.items.artifacts.tefnut.TefnutsRainItem;
import com.teammetallurgy.atum.items.food.CrunchyScarabItem;
import com.teammetallurgy.atum.items.food.EmmerFlourItem;
import com.teammetallurgy.atum.items.food.FishItem;
import com.teammetallurgy.atum.items.tools.*;
import com.teammetallurgy.atum.utils.Constants;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ObjectHolder;

import static com.teammetallurgy.atum.utils.AtumRegistry.registerItem;

@ObjectHolder(value = Constants.MOD_ID)
public class AtumItems {
    public static final Item DUST_BONE_STICK = registerItem(new SimpleItem(), "dusty_bone_stick");
    public static final Item KHNUMITE = registerItem(new SimpleItem(), "khnumite");
    public static final Item DIRTY_COIN = registerItem(new CoinItem(), "coin_dirty");
    public static final Item GOLD_COIN = registerItem(new CoinItem(), "coin_gold");
    public static final Item EFREET_HEART = registerItem(new SimpleItem(), "efreet_heart");
    public static final Item SCARAB = registerItem(new ScarabItem(), "scarab");
    public static final Item IDOL_OF_LABOR = registerItem(new IdolOfLaborItem(), "idol_of_labor");
    public static final Item SHORT_BOW = registerItem(new BaseBowItem(new Item.Properties().maxDamage(384)), "short_bow");
    public static final Item LIMESTONE_SHOVEL = registerItem(new ShovelItem(AtumMats.LIMESTONE, 1.2F, -3.0F, new Item.Properties().group(Atum.GROUP)), "limestone_shovel");
    public static final Item LIMESTONE_PICKAXE = registerItem(new PickaxeItem(AtumMats.LIMESTONE, 1, -2.8F, new Item.Properties().group(Atum.GROUP)), "limestone_pickaxe");
    public static final Item LIMESTONE_AXE = registerItem(new AxeItem(AtumMats.LIMESTONE, 7.0F, -3.2F, new Item.Properties().group(Atum.GROUP)), "limestone_axe");
    public static final Item LIMESTONE_SWORD = registerItem(new SwordItem(AtumMats.LIMESTONE, 3, -2.4F, new Item.Properties().group(Atum.GROUP)), "limestone_sword");
    public static final Item LIMESTONE_HOE = registerItem(new HoeItem(AtumMats.LIMESTONE, -1.8F, new Item.Properties().group(Atum.GROUP)), "limestone_hoe");
    public static final Item DAGGER_IRON = registerItem(new DaggerItem(ItemTier.IRON), "iron_dagger");
    public static final Item POISON_DAGGER = registerItem(new PoisonDaggerItem(), "dagger_poison");
    public static final Item SCIMITAR_IRON = registerItem(new SwordItem(ItemTier.IRON, 3, -2.4F, new Item.Properties().group(Atum.GROUP)), "iron_scimitar");
    public static final Item GREATSWORD_IRON = registerItem(new GreatswordItem(ItemTier.IRON), "iron_greatsword");
    public static final Item CLUB_IRON = registerItem(new ClubItem(ItemTier.IRON), "iron_club");
    public static final Item KHOPESH_IRON = registerItem(new KhopeshItem(ItemTier.IRON), "iron_khopesh");
    public static final Item STONEGUARD_SWORD = registerItem(new SwordItem(AtumMats.KHNUMITE, 3, -2.4F, new Item.Properties().group(Atum.GROUP)), "stoneguard_sword");
    public static final Item STONEGUARD_GREATSWORD = registerItem(new GreatswordItem(AtumMats.KHNUMITE), "stoneguard_greatsword");
    public static final Item STONEGUARD_CLUB = registerItem(new ClubItem(AtumMats.KHNUMITE), "stoneguard_club");
    public static final Item STONEGUARD_KHOPESH = registerItem(new KhopeshItem(AtumMats.KHNUMITE), "stoneguard_khopesh");
    public static final Item STONEGUARD_SHIELD = registerItem(new AtumShieldItem(90).setRepairItem(KHNUMITE), "stoneguard_shield");
    public static final Item BRIGAND_SHIELD = registerItem(new AtumShieldItem(150), "brigand_shield");
    public static final Item EYES_OF_ATUM = registerItem(new EyesOfAtumItem(), "eyes_of_atum");
    public static final Item BODY_OF_ATUM = registerItem(new BodyOfAtumItem(), "body_of_atum");
    public static final Item LEGS_OF_ATUM = registerItem(new LegsOfAtumItem(), "legs_of_atum");
    public static final Item FEET_OF_ATUM = registerItem(new FeetOfAtumItem(), "feet_of_atum");
    public static final Item ATUMS_WILL = registerItem(new AtumsWillItem(), "atums_will");
    public static final Item ATUMS_PROTECTION = registerItem(new AtumsProtectionItem(), "atums_protection");
    public static final Item ATUMS_BOUNTY = registerItem(new AtumsBountyItem(), "atums_bounty");
    public static final Item ATUMS_HOMECOMING = registerItem(new AtumsHomecomingItem(), "atums_homecoming");
    public static final Item HALO_OF_RA = registerItem(new HaloOfRaItem(), "halo_of_ra");
    public static final Item BODY_OF_RA = registerItem(new BodyOfRaItem(), "body_of_ra");
    public static final Item LEGS_OF_RA = registerItem(new LegsOfRaItem(), "legs_of_ra");
    public static final Item FEET_OF_RA = registerItem(new FeetOfRaItem(), "feet_of_ra");
    public static final Item RAS_FURY = registerItem(new RasFuryItem(), "ras_fury");
    public static final Item PTAHS_DECADENCE = registerItem(new PtahsDecadenceItem(), "ptahs_decadence");
    public static final Item PTAHS_UNDOING = registerItem(new PtahsUndoingItem(), "ptahs_undoing");
    public static final Item GEBS_TOIL = registerItem(new GebsToilItem(), "gebs_toil");
    public static final Item GEBS_GROUNDING = registerItem(new GebsGroundingItem(), "gebs_grounding");
    public static final Item GEBS_MIGHT = registerItem(new GebsMightItem(), "gebs_might");
    public static final Item TEFNUTS_RAIN = registerItem(new TefnutsRainItem(), "tefnuts_rain");
    public static final Item TEFNUTS_CALL = registerItem(new TefnutsCallItem(), "tefnuts_call");
    public static final Item TEFNUTS_BLESSING = registerItem(new TefnutsBlessingItem(), "tefnuts_blessing");
    public static final Item SHUS_BREATH = registerItem(new ShusBreathItem(), "shus_breath");
    public static final Item SHUS_EXILE = registerItem(new ShusExileItem(), "shus_exile");
    public static final Item SHUS_SWIFTNESS = registerItem(new ShusSwiftnessItem(), "shus_swiftness");
    public static final Item HORUSS_SOARING = registerItem(new HorusSoaringItem(), "horuss_soaring");
    public static final Item HORUSS_ASCENSION = registerItem(new HorusAscensionItem(), "horuss_ascension");
    public static final Item SETHS_STING = registerItem(new SethsStingItem(), "seths_sting");
    public static final Item SETHS_VENOM = registerItem(new SethsVenomItem(), "seths_venom");
    public static final Item ISIS_HEALING = registerItem(new IsisHealingItem(), "isis_healing");
    public static final Item MONTUS_BLAST = registerItem(new MontusBlastItem(), "montus_blast");
    public static final Item MONTUS_STRIKE = registerItem(new MontusStrikeItem(), "montus_strike");
    //public static final ItemThothsBearings THOTHS_BEARINGS = (ItemThothsBearings) registerItem(new ItemThothsBearings(), "thoths_bearings");
    //public static final Item THOTHS_DIRECTION = registerItem(new ItemThothsDirection(), "thoths_direction");
    public static final Item ANUBIS_MERCY = registerItem(new AnubisMercyItem(), "anubiss_mercy");
    public static final Item ANUBIS_WRATH = registerItem(new AnubisWrathItem(), "anubiss_wrath");
    public static final Item NUITS_VANISHING = registerItem(new NuitsVanishingItem(), "nuits_vanishing");
    public static final Item NUITS_DUALITY = registerItem(new NuitsDualityItem(), "nuits_duality");
    public static final Item NUITS_IRE = registerItem(new NuitsIreItem(), "nuits_ire");
    public static final Item NUITS_QUARTER = registerItem(new NuitsQuarterItem(), "nuits_quarter");
    public static final Item ANPUTS_HUNGER = registerItem(new AnputsHungerItem(), "anputs_hunger");
    public static final Item MUMMY_HELMET = registerItem(new TexturedArmorItem(AtumMats.MUMMY_ARMOR, "mummy_armor_1", EquipmentSlotType.HEAD), "mummy_helmet");
    public static final Item MUMMY_CHEST = registerItem(new TexturedArmorItem(AtumMats.MUMMY_ARMOR, "mummy_armor_1", EquipmentSlotType.CHEST), "mummy_chest");
    public static final Item MUMMY_LEGS = registerItem(new TexturedArmorItem(AtumMats.MUMMY_ARMOR, "mummy_armor_2", EquipmentSlotType.LEGS), "mummy_legs");
    public static final Item MUMMY_BOOTS = registerItem(new TexturedArmorItem(AtumMats.MUMMY_ARMOR, "mummy_armor_1", EquipmentSlotType.FEET), "mummy_boots");
    public static final Item WANDERER_HELMET = registerItem(new TexturedArmorItem(AtumMats.WANDERER_ARMOR, "wanderer_armor_1", EquipmentSlotType.HEAD).setDyeable(), "wanderer_helmet");
    public static final Item WANDERER_CHEST = registerItem(new TexturedArmorItem(AtumMats.WANDERER_ARMOR, "wanderer_armor_1", EquipmentSlotType.CHEST).setDyeable(), "wanderer_chest");
    public static final Item WANDERER_LEGS = registerItem(new TexturedArmorItem(AtumMats.WANDERER_ARMOR, "wanderer_armor_2", EquipmentSlotType.LEGS).setDyeable(), "wanderer_legs");
    public static final Item WANDERER_BOOTS = registerItem(new TexturedArmorItem(AtumMats.WANDERER_ARMOR, "wanderer_armor_1", EquipmentSlotType.FEET).setDyeable(), "wanderer_boots");
    public static final Item DESERT_HELMET_IRON = registerItem(new TexturedArmorItem(ArmorMaterial.IRON, "desert_iron_armor_1", EquipmentSlotType.HEAD).setDamageModifier(10).setDyeable(), "desert_helmet_iron");
    public static final Item DESERT_CHEST_IRON = registerItem(new TexturedArmorItem(ArmorMaterial.IRON, "desert_iron_armor_1", EquipmentSlotType.CHEST).setDamageModifier(10).setDyeable(), "desert_chest_iron");
    public static final Item DESERT_LEGS_IRON = registerItem(new TexturedArmorItem(ArmorMaterial.IRON, "desert_iron_armor_2", EquipmentSlotType.LEGS).setDamageModifier(10).setDyeable(), "desert_legs_iron");
    public static final Item DESERT_BOOTS_IRON = registerItem(new TexturedArmorItem(ArmorMaterial.IRON, "desert_iron_armor_1", EquipmentSlotType.FEET).setDamageModifier(10).setDyeable(), "desert_boots_iron");
    public static final Item DESERT_HELMET_GOLD = registerItem(new TexturedArmorItem(ArmorMaterial.GOLD, "desert_gold_armor_1", EquipmentSlotType.HEAD).setDamageModifier(20).setDyeable(), "desert_helmet_gold");
    public static final Item DESERT_CHEST_GOLD = registerItem(new TexturedArmorItem(ArmorMaterial.GOLD, "desert_gold_armor_1", EquipmentSlotType.CHEST).setDamageModifier(20).setDyeable(), "desert_chest_gold");
    public static final Item DESERT_LEGS_GOLD = registerItem(new TexturedArmorItem(ArmorMaterial.GOLD, "desert_gold_armor_2", EquipmentSlotType.LEGS).setDamageModifier(20).setDyeable(), "desert_legs_gold");
    public static final Item DESERT_BOOTS_GOLD = registerItem(new TexturedArmorItem(ArmorMaterial.GOLD, "desert_gold_armor_1", EquipmentSlotType.FEET).setDamageModifier(20).setDyeable(), "desert_boots_gold");
    public static final Item DESERT_HELMET_DIAMOND = registerItem(new TexturedArmorItem(ArmorMaterial.DIAMOND, "desert_diamond_armor_1", EquipmentSlotType.HEAD).setDamageModifier(15).setDyeable(), "desert_helmet_diamond");
    public static final Item DESERT_CHEST_DIAMOND = registerItem(new TexturedArmorItem(ArmorMaterial.DIAMOND, "desert_diamond_armor_1", EquipmentSlotType.CHEST).setDamageModifier(15).setDyeable(), "desert_chest_diamond");
    public static final Item DESERT_LEGS_DIAMOND = registerItem(new TexturedArmorItem(ArmorMaterial.DIAMOND, "desert_diamond_armor_2", EquipmentSlotType.LEGS).setDamageModifier(15).setDyeable(), "desert_legs_diamond");
    public static final Item DESERT_BOOTS_DIAMOND = registerItem(new TexturedArmorItem(ArmorMaterial.DIAMOND, "desert_diamond_armor_1", EquipmentSlotType.FEET).setDamageModifier(15).setDyeable(), "desert_boots_diamond");
    public static final Item DESERT_WOLF_IRON_ARMOR = registerItem(new NonStackableItem(), "desert_wolf_iron_armor");
    public static final Item DESERT_WOLF_GOLD_ARMOR = registerItem(new NonStackableItem(), "desert_wolf_gold_armor");
    public static final Item DESERT_WOLF_DIAMOND_ARMOR = registerItem(new NonStackableItem(), "desert_wolf_diamond_armor");
    public static final Item CAMEL_IRON_ARMOR = registerItem(new NonStackableItem(), "camel_iron_armor");
    public static final Item CAMEL_GOLD_ARMOR = registerItem(new NonStackableItem(), "camel_gold_armor");
    public static final Item CAMEL_DIAMOND_ARMOR = registerItem(new NonStackableItem(), "camel_diamond_armor");
    public static final Item GRAVEROBBERS_MAP = registerItem(new GraverobbersMapItem(), "graverobbers_map");
    public static final Item DISENCHANTING_SCROLL = registerItem(new SimpleItem(), "disenchanting_scroll");
    public static final Item SCROLL = registerItem(new SimpleItem(), "scroll");
    public static final Item SCRAP = registerItem(new SimpleItem(), "cloth_scrap");
    public static final Item LINEN_BANDAGE = registerItem(new LinenBandageItem(), "linen_bandage");
    public static final Item LINEN_THREAD = registerItem(new SimpleItem(), "linen_thread");
    public static final Item LINEN_CLOTH = registerItem(new SimpleItem(), "linen_cloth");
    public static final Item PAPYRUS_PLANT = registerItem(new BlockItem(AtumBlocks.PAPYRUS, new Item.Properties().group(Atum.GROUP)), "papyrus_plant"); //TODO Check if stuff works properly
    public static final Item FLAX_SEEDS = registerItem(new BlockNamedItem(AtumBlocks.FLAX, new Item.Properties().group(Atum.GROUP)), "flax_seeds");
    public static final Item FLAX = registerItem(new SimpleItem(), "flax");
    public static final Item OPHIDIAN_TONGUE_FLOWER = registerItem(new SimpleItem(), "ophidian_tongue_flower");
    public static final Item ANPUTS_FINGERS_SPORES = registerItem(new BlockNamedItem(AtumBlocks.ANPUTS_FINGERS, new Item.Properties().group(Atum.GROUP)), "anputs_fingers_spores");
    public static final Item EMMER_SEEDS = registerItem(new BlockNamedItem(AtumBlocks.EMMER_WHEAT, new Item.Properties().group(Atum.GROUP)), "emmer_seeds");
    public static final Item EMMER = registerItem(new SimpleItem(), "emmer");
    public static final Item EMMER_FLOUR = registerItem(new EmmerFlourItem(), "emmer_flour");
    public static final Item EMMER_DOUGH = registerItem(new SimpleItem(), "emmer_dough");
    public static final Item EMMER_BREAD = registerItem(new Item(new Item.Properties().food(AtumFoods.EMMER_BREAD).group(Atum.GROUP)), "emmer_bread");
    public static final Item CAMEL_RAW = registerItem(new Item(new Item.Properties().food(AtumFoods.CAMEL_RAW).group(Atum.GROUP)), "camel_raw");
    public static final Item CAMEL_COOKED = registerItem(new Item(new Item.Properties().food(AtumFoods.CAMEL_COOKED).group(Atum.GROUP)), "camel_cooked");
    public static final Item DATE = registerItem(new Item(new Item.Properties().food(Foods.APPLE).group(Atum.GROUP)), "date");
    public static final Item GLISTERING_DATE = registerItem(new SimpleItem(), "glistering_date");
    public static final Item GOLDEN_DATE = registerItem(new Item(new Item.Properties().food(AtumFoods.GOLDEN_DATE).group(Atum.GROUP)), "golden_date");
    public static final Item ENCHANTED_GOLDEN_DATE = registerItem(new Item(new Item.Properties().food(AtumFoods.ENCHANTED_GOLDEN_DATE).group(Atum.GROUP)), "golden_date_enchanted");
    public static final Item ECTOPLASM = registerItem(new SimpleItem(), "ectoplasm");
    public static final Item MANDIBLES = registerItem(new SimpleItem(), "mandibles");
    public static final Item DUSTY_BONE = registerItem(new SimpleItem(), "dusty_bone");
    public static final Item WOLF_PELT = registerItem(new SimpleItem(), "wolf_pelt");
    public static final Item FERTILE_SOIL_PILE = registerItem(new SimpleItem(), "fertile_soil_pile");
    public static final Item FORSAKEN_FISH = registerItem(new FishItem(FishItem.FishType.FORSAKEN), "fish_forsaken");
    public static final Item MUMMIFIED_FISH = registerItem(new FishItem(FishItem.FishType.MUMMIFIED), "fish_mummified");
    public static final Item JEWELED_FISH = registerItem(new FishItem(FishItem.FishType.JEWELED), "fish_jeweled");
    public static final Item SKELETAL_FISH = registerItem(new FishItem(FishItem.FishType.SKELETAL), "fish_skeletal");
    public static final Item CRUNCHY_SCARAB = registerItem(new CrunchyScarabItem(new Item.Properties().food(AtumFoods.CRUNCHY_SCARAB)), "crunchy_scarab");
    public static final Item CRUNCHY_GOLD_SCARAB = registerItem(new CrunchyScarabItem(new Item.Properties().food(AtumFoods.CRUNCHY_GOLD_SCARAB)), "crunchy_golden_scarab");

    /*public static void registerAdditionalItems() { //TODO
        BlockAtumPlank.registerSticks()
        ItemLoot.createLootItems()
        registerBlock(AtumBlocks.HEART_OF_RA, new Item.Properties().group(Atum.GROUP), "heart_of_ra")
        ItemScepter.registerScepters()
    }*/

    public static void setItemInfo() {
        //OreDictHelper.add(SCROLL, "paper"); //TODO
        //OreDictHelper.add(EMMER_DOUGH, "foodDough"); //TODO
        //OreDictHelper.add(new ItemStack(EMMER_BREAD), "foodBread", "bread"); //TODO

        MinecraftForge.EVENT_BUS.register(GEBS_MIGHT);
    }

    public static class AtumFoods {
        public static final Food EMMER_BREAD = new Food.Builder().hunger(4).saturation(0.9F).build();
        public static final Food CAMEL_RAW = new Food.Builder().hunger(3).saturation(0.2F).meat().build();
        public static final Food CAMEL_COOKED = new Food.Builder().hunger(9).saturation(0.7F).meat().build();
        public static final Food GOLDEN_DATE = new Food.Builder().hunger(5).saturation(1.5F).effect(new EffectInstance(Effects.REGENERATION, 100, 1), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 0), 1.0F).setAlwaysEdible().build();
        public static final Food ENCHANTED_GOLDEN_DATE = new Food.Builder().hunger(5).saturation(1.5F).effect(new EffectInstance(Effects.REGENERATION, 400, 1), 1.0F).effect(new EffectInstance(Effects.RESISTANCE, 6000, 0), 1.0F).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 3), 1.0F).setAlwaysEdible().build();
        public static final Food CRUNCHY_SCARAB = new Food.Builder().hunger(3).saturation(0.1F).meat().build();
        public static final Food CRUNCHY_GOLD_SCARAB = new Food.Builder().hunger(6).saturation(1.3F).meat().build();
    }
}