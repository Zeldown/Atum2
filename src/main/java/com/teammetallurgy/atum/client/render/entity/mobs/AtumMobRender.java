package com.teammetallurgy.atum.client.render.entity.mobs;

import com.google.common.collect.Maps;
import com.teammetallurgy.atum.entity.ITexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class AtumMobRender<T extends MobEntity & ITexture, M extends EntityModel<T>> extends MobRenderer<T, M> {
    private static final Map<String, ResourceLocation> CACHE = Maps.newHashMap();

    public AtumMobRender(EntityRendererManager renderManager, M model) {
        super(renderManager, model, 0.5F);
    }

    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(T entity) {
        String texture = entity.getTexture();
        ResourceLocation location = CACHE.get(texture);

        if (location == null) {
            location = new ResourceLocation(texture);
            CACHE.put(texture, location);
        }
        return location;
    }
}