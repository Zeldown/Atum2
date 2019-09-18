package com.teammetallurgy.atum.items.artifacts.geb;

import com.teammetallurgy.atum.entity.projectile.arrow.EntityArrowSlowness;
import com.teammetallurgy.atum.items.tools.BaseBowItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class GebsGroundingItem extends BaseBowItem {

    public GebsGroundingItem() {
        super(new Item.Properties().maxDamage(650).rarity(Rarity.RARE));
        this.setRepairItem(Items.DIAMOND);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(@Nonnull ItemStack stack) {
        return true;
    }

    @Override
    protected ArrowEntity setArrow(@Nonnull ItemStack stack, World world, PlayerEntity player, float velocity) {
        return new EntityArrowSlowness(world, player, velocity);
    }
}