package com.teammetallurgy.atum.entity.bandit;

import com.teammetallurgy.atum.init.AtumEffects;
import com.teammetallurgy.atum.init.AtumItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SergeantEntity extends BanditBaseEntity {

    public SergeantEntity(EntityType<? extends BanditBaseEntity> entityType, World world) {
        super(entityType, world);
        this.experienceValue = 16;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
    }

    @Override
    boolean hasSkinVariants() {
        return false;
    }

    @Override
    public boolean canBeLeader() {
        return true;
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return getBaseAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 36.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D).createMutableAttribute(Attributes.ARMOR, 8.0F);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(@Nonnull DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.SCIMITAR_IRON));
        this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.SHIELD));
    }

    @Override
    public void onDeath(@Nonnull DamageSource cause) {
        super.onDeath(cause);
        Entity killer = cause.getTrueSource();
        if (killer instanceof PlayerEntity) {
            double chance = this.rand.nextDouble();
            if (chance <= 0.1D) {
                ((PlayerEntity) killer).addPotionEffect(new EffectInstance(AtumEffects.MARKED_FOR_DEATH, 1020, 0, false, false, true));
            }
        }
    }
}