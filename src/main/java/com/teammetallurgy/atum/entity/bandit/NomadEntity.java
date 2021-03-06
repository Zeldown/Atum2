package com.teammetallurgy.atum.entity.bandit;

import com.teammetallurgy.atum.entity.ai.goal.CustomRangedBowAttackGoal;
import com.teammetallurgy.atum.init.AtumItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NomadEntity extends BanditBaseEntity implements IRangedAttackMob {
    private final CustomRangedBowAttackGoal<NomadEntity> aiArrowAttack = new CustomRangedBowAttackGoal<>(this, 0.75D, 35, 12.0F);
    private final MeleeAttackGoal aiAttackOnCollide = new MeleeAttackGoal(this, 1.0D, false) {
        @Override
        public void resetTask() {
            super.resetTask();
            NomadEntity.this.setAggroed(false);
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            NomadEntity.this.setAggroed(true);
        }
    };

    public NomadEntity(EntityType<? extends NomadEntity> entityType, World world) {
        super(entityType, world);
        this.experienceValue = 6;
        this.setCombatTask();
    }

    @Override
    protected int getVariantAmount() {
        return 6;
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return getBaseAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 13.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.20D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D).createMutableAttribute(Attributes.ARMOR, 2.0F);
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(@Nonnull DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.SHORT_BOW));
    }

    @Override
    @Nullable
    public ILivingEntityData onInitialSpawn(@Nonnull IServerWorld world, @Nonnull DifficultyInstance difficulty, @Nonnull SpawnReason spawnReason, @Nullable ILivingEntityData livingdata, @Nullable CompoundNBT nbt) {
        livingdata = super.onInitialSpawn(world, difficulty, spawnReason, livingdata, nbt);
        this.setCombatTask();

        return livingdata;
    }

    private void setCombatTask() {
        if (this.world != null && !this.world.isRemote) {
            this.goalSelector.removeGoal(this.aiAttackOnCollide);
            this.goalSelector.removeGoal(this.aiArrowAttack);
            ItemStack heldBow = this.getHeldItem(ProjectileHelper.getHandWith(this, AtumItems.SHORT_BOW));
            if (heldBow.getItem() instanceof BowItem) {
                int cooldown = 20;
                if (this.world.getDifficulty() != Difficulty.HARD) {
                    cooldown = 35;
                }
                this.aiArrowAttack.setAttackCooldown(cooldown);
                this.goalSelector.addGoal(4, this.aiArrowAttack);
            } else {
                this.goalSelector.addGoal(4, this.aiAttackOnCollide);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull LivingEntity target, float distanceFactor) {
        ItemStack ammo = this.findAmmo(this.getHeldItem(ProjectileHelper.getHandWith(this, AtumItems.SHORT_BOW)));
        AbstractArrowEntity arrow = ProjectileHelper.fireArrow(this, ammo, distanceFactor);
        if (this.getHeldItemMainhand().getItem() instanceof BowItem) {
            arrow = ((BowItem) this.getHeldItemMainhand().getItem()).customArrow(arrow);
        }
        double x = target.getPosX() - this.getPosX();
        double y = target.getPosYHeight(0.3333333333333333D) - arrow.getPosY();
        double z = target.getPosZ() - this.getPosZ();
        double height = MathHelper.sqrt(x * x + z * z);
        arrow.shoot(x, y + height * 0.2D, z, 1.6F, (float) (11 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(arrow);
    }

    @Override
    public void readAdditional(@Nonnull CompoundNBT compound) {
        super.readAdditional(compound);
        this.setCombatTask();
    }

    @Override
    public void setItemStackToSlot(@Nonnull EquipmentSlotType slot, @Nonnull ItemStack stack) {
        super.setItemStackToSlot(slot, stack);
        if (!this.world.isRemote) {
            this.setCombatTask();
        }
    }

    @Override
    public float getEyeHeight(@Nonnull Pose pose) {
        return 1.74F;
    }

    @Override
    public double getYOffset() {
        return -0.35D;
    }
}