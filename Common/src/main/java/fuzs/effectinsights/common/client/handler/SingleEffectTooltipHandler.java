package fuzs.effectinsights.common.client.handler;

import fuzs.effectinsights.common.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.common.config.ClientConfig;
import fuzs.tooltipinsights.common.api.v1.client.handler.TooltipDescriptionsHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;

public abstract class SingleEffectTooltipHandler extends TooltipDescriptionsHandler<MobEffectInstance, ClientConfig.EffectTooltipComponents> {
    private final MobEffectInstance mobEffect;

    public SingleEffectTooltipHandler(MobEffectInstance mobEffect) {
        super(MobEffectTooltipLines.WIDGET_SUPPLIERS);
        this.mobEffect = mobEffect;
    }

    @Override
    protected Map<String, MobEffectInstance> getByDescriptionId(ItemStack itemStack, HolderLookup.Provider registries) {
        return Collections.singletonMap(this.mobEffect.getDescriptionId(), this.mobEffect);
    }

    @Override
    protected @Nullable Component getValueComponent(MobEffectInstance mobEffect) {
        int maxWidth = this.getStyleConfig().tooltipLines().maximumWidth;
        return MobEffectTooltipLines.DISPLAY_NAME.getTooltipLines(mobEffect, maxWidth).findFirst().orElse(null);
    }
}
