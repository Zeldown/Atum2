package com.teammetallurgy.atum.entity.stone;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.ITexture;
import com.teammetallurgy.atum.init.AtumItems;
import com.teammetallurgy.atum.misc.StackHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class StoneguardEntity extends StoneBaseEntity implements ITexture {
    private static final ResourceLocation STONEGUARD_IRON_TEXTURE = new ResourceLocation(Atum.MOD_ID, "textures/entity/stoneguard_derp.png");
    private static final AttributeModifier SHIELD_ARMOR = new AttributeModifier(UUID.fromString("29c9fac8-7da1-43c0-95e7-4a3cae9bcbef"), "Stoneguard shield armor", 4, AttributeModifier.Operation.ADDITION);

    public StoneguardEntity(EntityType<? extends StoneguardEntity> entityType, World world) {
        super(entityType, world);
        this.experienceValue = 8;
        this.setCanPickUpLoot(true);
        new GroundPathNavigator(this, world).getNodeProcessor().setCanEnterDoors(true);
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return getBaseAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 40.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D).createMutableAttribute(Attributes.ARMOR, 10.0F);
    }

    @Override
    protected void setFriendlyAttributes() {
        super.setFriendlyAttributes();
        final AttributeModifier FRIENDLY_HEALTH = new AttributeModifier(UUID.fromString("41d44fff-f8a8-47c5-a753-d7eb9f715d40"), "Friendly Stoneguard health", 30.0D, AttributeModifier.Operation.ADDITION);
        this.getAttribute(Attributes.MAX_HEALTH).applyPersistentModifier(FRIENDLY_HEALTH);
        this.heal(30);
    }

    @Override
    protected void dropSpecialItems(@Nonnull DamageSource source, int lootingModifier, boolean wasRecentlyHit) {
        if (this.isPlayerCreated()) {
            for (EquipmentSlotType slot : EquipmentSlotType.values()) {
                ItemStack stack = this.getItemStackFromSlot(slot);
                if (!stack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(stack) && wasRecentlyHit) {
                    this.entityDropItem(stack, 0.0F);
                }
            }
        } else {
            super.dropSpecialItems(source, lootingModifier, wasRecentlyHit);
        }
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(@Nonnull DifficultyInstance difficulty) {
        int randomWeapon = MathHelper.nextInt(this.rand, 0, 3);
        this.setStoneguardEquipment(randomWeapon);
    }

    private void setStoneguardEquipment(int randomWeapon) {
        if (randomWeapon != 2) {
            this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(AtumItems.STONEGUARD_SHIELD));

            if (!this.world.isRemote) {
                ModifiableAttributeInstance attribute = this.getAttribute(Attributes.MOVEMENT_SPEED);

                if (attribute != null && !attribute.hasModifier(SHIELD_ARMOR)) {
                    this.getAttribute(Attributes.ARMOR).applyPersistentModifier(SHIELD_ARMOR);
                }
            }
        }

        switch (randomWeapon) {
            case 0:
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.STONEGUARD_SWORD));
                break;
            case 1:
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.STONEGUARD_CLUB));
                break;
            case 2:
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.STONEGUARD_GREATSWORD));
                break;
            case 3:
                this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(AtumItems.STONEGUARD_KHOPESH));
                break;
        }
    }

    @Override
    @Nullable
    public ILivingEntityData onInitialSpawn(@Nonnull IServerWorld world, @Nonnull DifficultyInstance difficulty, @Nonnull SpawnReason spawnReason, @Nullable ILivingEntityData livingdata, @Nullable CompoundNBT nbt) {
        livingdata = super.onInitialSpawn(world, difficulty, spawnReason, livingdata, nbt);

        if (!this.isPlayerCreated()) {
            this.setEquipmentBasedOnDifficulty(difficulty);
            final int variant = MathHelper.nextInt(rand, 0, 7);
            this.setVariant(variant);
        } else {
            this.setVariant(8);
        }
        return livingdata;
    }

    @Override
    public boolean func_230292_f_(@Nonnull PlayerEntity player) { //isPreventingPlayerRest
        return this.getVariant() != 8;
    }

    @Override
    @Nonnull
    protected ActionResultType func_230254_b_(PlayerEntity player, @Nonnull Hand hand) {
        if (this.isPlayerCreated() && player.isCrouching() && player.getHeldItem(hand).isEmpty()) {
            if (!world.isRemote) {
                for (ItemStack held : this.getHeldEquipment()) {
                    StackHelper.giveItem(player, hand, held);
                }
            }
            return ActionResultType.SUCCESS;
        } else {
            return super.func_230254_b_(player, hand);
        }
    }

    @Override
    public String getTexture() {
        if (this.hasCustomName() && this.getCustomName() != null) {
            String customName = this.getCustomName().getString();
            if (customName.equalsIgnoreCase("iron") || customName.equalsIgnoreCase("nutz") || customName.equalsIgnoreCase("vequinox")) {
                return STONEGUARD_IRON_TEXTURE.toString();
            }
        }
        return new ResourceLocation(Atum.MOD_ID, "textures/entity/stoneguard_" + this.getVariant() + ".png").toString();
    }
}