package fuzs.effectinsights.common.client.handler;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.effectinsights.common.client.gui.component.EffectComponents;
import fuzs.effectinsights.common.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.common.config.ClientConfig;
import fuzs.tooltipinsights.common.api.v1.client.handler.TooltipDescriptionsHandler;
import fuzs.tooltipinsights.common.api.v1.config.StyledTooltipsConfig;
import fuzs.tooltipinsights.common.api.v1.config.TooltipComponentsConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class EffectItemTooltipHandler extends TooltipDescriptionsHandler<MobEffectInstance, TooltipComponentsConfig> {
    public static final TooltipDescriptionsHandler<MobEffectInstance, TooltipComponentsConfig> INSTANCE = new EffectItemTooltipHandler();

    private EffectItemTooltipHandler() {
        super(MobEffectTooltipLines.ITEM_SUPPLIERS);
    }

    @Override
    protected StyledTooltipsConfig<TooltipComponentsConfig> getStyleConfig() {
        return EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips;
    }

    @Override
    protected Map<String, MobEffectInstance> getByDescriptionId(ItemStack itemStack, HolderLookup.Provider registries) {
        // an item can contain the same effect multiple times, so make sure to include a merge function in our collect call
        return EffectComponents.getAllMobEffects(itemStack)
                .collect(Collectors.toMap(MobEffectInstance::getDescriptionId,
                        Function.identity(),
                        (MobEffectInstance o1, MobEffectInstance o2) -> o2));
    }
}
