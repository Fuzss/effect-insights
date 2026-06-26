package fuzs.effectinsights.common.client;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.effectinsights.common.client.handler.EffectItemTooltipHandler;
import fuzs.effectinsights.common.client.handler.FoodItemTooltipHandler;
import fuzs.effectinsights.common.client.handler.SingleEffectTooltipHandler;
import fuzs.effectinsights.common.config.ClientConfig;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.event.v1.gui.GatherEffectScreenTooltipCallback;
import fuzs.puzzleslib.common.api.client.event.v1.gui.ItemTooltipCallback;
import fuzs.puzzleslib.common.api.event.v1.core.EventPhase;
import fuzs.tooltipinsights.common.api.v1.client.handler.TooltipDescriptionsHandler;
import fuzs.tooltipinsights.common.api.v1.config.StyledTooltipsConfig;
import fuzs.tooltipinsights.common.api.v1.config.TooltipDescriptionMode;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public class EffectInsightsClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
        TooltipDescriptionsHandler.printMissingDescriptionWarnings(Registries.MOB_EFFECT,
                (Holder.Reference<MobEffect> holder) -> holder.value().getDescriptionId());
    }

    private static void registerEventHandlers() {
        ItemTooltipCallback.EVENT.register(EventPhase.LAST, EffectItemTooltipHandler.INSTANCE::onItemTooltip);
        ItemTooltipCallback.EVENT.register(EventPhase.AFTER, FoodItemTooltipHandler::onItemTooltip);
        GatherEffectScreenTooltipCallback.EVENT.register(EffectInsightsClient::onGatherEffectScreenTooltip);
    }

    private static void onGatherEffectScreenTooltip(AbstractContainerScreen<?> screen, MobEffectInstance mobEffect, List<Component> tooltipLines) {
        if (EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.tooltipDescriptions
                == TooltipDescriptionMode.DISABLED) {
            return;
        }

        tooltipLines.clear();
        tooltipLines.add(mobEffect.getEffect().value().getDisplayName());
        new SingleEffectTooltipHandler(mobEffect) {
            @Override
            protected StyledTooltipsConfig<ClientConfig.EffectTooltipComponents> getStyleConfig() {
                return EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips;
            }
        }.onGatherTooltipComponents(screen.minecraft, tooltipLines);
    }
}
