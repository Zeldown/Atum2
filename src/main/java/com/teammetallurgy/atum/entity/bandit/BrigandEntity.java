package com.teammetallurgy.atum.entity.bandit;

import com.teammetallurgy.atum.init.AtumItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BrigandEntity extends BanditBaseEntity {

    public BrigandEntity(EntityType<? extends BrigandEntity> entityType, World world) {
        super(entityType, world);
        this.experienceValue = 8;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return getBaseAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 18.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.ARMOR, 4.0F);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(@Nonnull DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.SCIMITAR_IRON));
        this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(AtumItems.BRIGAND_SHIELD));
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity) {
        if (!super.attackEntityAsMob(entity)) {
            return false;
        } else {
            if (this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == AtumItems.GREATSWORD_IRON) {
                float attackDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                float knockback = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);

                if (entity instanceof LivingEntity) {
                    attackDamage += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((LivingEntity) entity).getCreatureAttribute());
                    knockback += EnchantmentHelper.getKnockbackModifier(this);
                }

                int fireAspect = EnchantmentHelper.getFireAspectModifier(this);
                if (fireAspect > 0) {
                    entity.setFire(fireAspect * 4);
                }

                boolean attackEntity = entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);
                if (attackEntity) {
                    if (knockback > 0.0F && entity instanceof LivingEntity) {
                        ((LivingEntity) entity).applyKnockback(knockback * 0.5F, MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)), -MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)));
                        entity.addVelocity(-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
                        this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
                    }

                    int j = EnchantmentHelper.getFireAspectModifier(this);
                    if (j > 0) {
                        entity.setFire(j * 4);
                    }
                    this.applyEnchantments(this, entity);
                }
                return attackEntity;
            }
            return true;
        }
    }
}