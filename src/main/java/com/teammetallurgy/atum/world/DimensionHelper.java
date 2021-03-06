package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.SandLayersBlock;
import com.teammetallurgy.atum.init.AtumBiomes;
import com.teammetallurgy.atum.init.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atum.MOD_ID)
public class DimensionHelper {

    @SubscribeEvent
    public static void onSleepFinished(SleepFinishedTimeEvent event) {
        IWorld world = event.getWorld();
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (serverWorld.getDimensionKey() == Atum.ATUM) {
                if (world.getWorldInfo() instanceof DerivedWorldInfo) {
                    ((DerivedWorldInfo) world.getWorldInfo()).delegate.setDayTime(event.getNewTime()); //Workaround for making sleeping work in Atum
                }
            }
        }
    }

    public static int getSkyColorWithTemperatureModifier(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static boolean canPlaceSandLayer(RegistryKey<Biome> biome, IWorldReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        BlockState stateDown = world.getBlockState(pos.down());
        return biome != AtumBiomes.OASIS
                && state.getMaterial().isReplaceable()
                && stateDown.getBlock() != AtumBlocks.LIMESTONE_CRACKED
                && Block.hasSolidSideOnTop(world, pos.down())
                && !(stateDown.getBlock() instanceof SandLayersBlock)
                && !(state.getBlock() instanceof SandLayersBlock);
    }

    public static Biome getBiome(RegistryKey<Biome> key) {
        return WorldGenRegistries.BIOME.getValueForKey(key);
    }

    public static int getBiomeID(RegistryKey<Biome> key) {
        return WorldGenRegistries.BIOME.getId(getBiome(key));
    }
}