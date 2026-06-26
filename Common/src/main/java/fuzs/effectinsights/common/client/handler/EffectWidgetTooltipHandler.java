package fuzs.effectinsights.common.client.handler;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.effectinsights.common.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.common.config.ClientConfig;
import fuzs.tooltipinsights.common.api.v1.client.gui.tooltip.TooltipLinesExtractor;
import fuzs.tooltipinsights.common.api.v1.client.handler.TooltipDescriptionsHandler;
import fuzs.tooltipinsights.common.api.v1.config.TooltipDescriptionMode;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class EffectWidgetTooltipHandler extends TooltipDescriptionsHandler<MobEffectInstance> {
    private final MobEffectInstance mobEffect;

    public EffectWidgetTooltipHandler(MobEffectInstance mobEffect) {
        this.mobEffect = mobEffect;
    }

    @Override
    protected TooltipDescriptionMode getTooltipDescriptionMode() {
        return EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.tooltipDescriptions;
    }

    @Override
    protected Map<String, MobEffectInstance> getByDescriptionId(ItemStack itemStack, HolderLookup.Provider registries) {
        return Collections.singletonMap(this.mobEffect.getDescriptionId(), this.mobEffect);
    }

    @Override
    protected @Nullable Component getValueComponent(MobEffectInstance mobEffect) {
        int maxWidth = EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.tooltipLines.maximumWidth;
        return MobEffectTooltipLines.DISPLAY_NAME.getTooltipLines(mobEffect, maxWidth).findFirst().orElse(null);
    }

    @Override
    protected List<Component> getItemTooltipLines(MobEffectInstance mobEffectInstance) {
        return TooltipLinesExtractor.getTooltipLines(MobEffectTooltipLines.WIDGET_SUPPLIERS,
                mobEffectInstance,
                EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips);
    }
}
