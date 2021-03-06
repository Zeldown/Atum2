package com.teammetallurgy.atum.items.tools;

import com.teammetallurgy.atum.Atum;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Atum.MOD_ID)
public class HammerItem extends SwordItem {
    private static final AttributeModifier STUN = new AttributeModifier(UUID.fromString("b4ebf092-fe62-4250-b945-7dc45b2f1036"), "Hammer stun", -1000.0D, AttributeModifier.Operation.ADDITION);
    private static final Object2FloatMap<PlayerEntity> cooldown = new Object2FloatOpenHashMap<>();
    protected static final Object2IntMap<LivingEntity> stun = new Object2IntOpenHashMap<>();

    protected HammerItem(IItemTier tier, Item.Properties properties) {
        super(tier, 17, -3.55F, properties.group(Atum.GROUP));
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        Entity trueSource = event.getSource().getTrueSource();
        if (trueSource instanceof PlayerEntity && cooldown.containsKey(trueSource)) {
            if (cooldown.getFloat(trueSource) == 1.0F) {
                Item heldItem = ((PlayerEntity)trueSource).getHeldItemMainhand().getItem();
                if (heldItem instanceof HammerItem) {
                    HammerItem hammerItem = (HammerItem) heldItem;
                    LivingEntity target = event.getEntityLiving();
                    ModifiableAttributeInstance attribute = target.getAttribute(Attributes.MOVEMENT_SPEED);
                    if (attribute != null && !attribute.hasModifier(STUN)) {
                        attribute.applyNonPersistentModifier(STUN);
                        hammerItem.onStun(target);
                    }
                }
            }
            cooldown.removeFloat(trueSource);
        }
    }

    protected void onStun(LivingEntity target) {
        stun.put(target, 40);
    }

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (stun.isEmpty()) return;
        ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attribute != null && attribute.hasModifier(STUN)) {
            int stunTime = stun.getInt(entity);
            if (stunTime <= 1) {
                attribute.removeModifier(STUN);
                stun.remove(entity, stunTime);
            } else {
                stun.replace(entity, stunTime - 1);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAttack(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player.world.isRemote) return;
        if (event.getTarget() instanceof LivingEntity && player.getHeldItemMainhand().getItem() instanceof HammerItem) {
            cooldown.put(player, player.getCooledAttackStrength(0.5F));
        }
    }
}