package fuzs.effectinsights.common.client.gui.tooltip;

import com.google.common.collect.ImmutableList;
import fuzs.effectinsights.common.config.ClientConfig;
import fuzs.tooltipinsights.common.api.v1.client.gui.tooltip.DescriptionLines;
import fuzs.tooltipinsights.common.api.v1.client.gui.tooltip.InternalNameLines;
import fuzs.tooltipinsights.common.api.v1.client.gui.tooltip.ModNameLines;
import fuzs.tooltipinsights.common.api.v1.client.gui.tooltip.TooltipLinesExtractor;
import fuzs.tooltipinsights.common.api.v1.config.TooltipComponentsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.stream.Stream;

public final class MobEffectTooltipLines {
    public static final PotionContentsLines DISPLAY_NAME = new PotionContentsLines() {
        @Override
        protected boolean isEnabled(ClientConfig.EffectTooltipComponents tooltipComponents) {
            return true;
        }

        @Override
        protected Stream<Component> modifyTooltipLines(List<Component> tooltipLines, int separatorIndex) {
            if (separatorIndex != -1) {
                return tooltipLines.subList(0, separatorIndex).stream();
            } else {
                return tooltipLines.stream();
            }
        }
    };
    public static final TooltipLinesExtractor<MobEffectInstance, TooltipComponentsConfig> DESCRIPTION = new DescriptionLines<>() {
        @Override
        protected String getDescriptionId(MobEffectInstance mobEffect) {
            return mobEffect.getDescriptionId();
        }
    };
    public static final TooltipLinesExtractor<MobEffectInstance, TooltipComponentsConfig> MOD_NAME = new ModNameLines<>() {
        @Override
        protected ResourceKey<?> getResourceKey(MobEffectInstance mobEffect) {
            return mobEffect.getEffect().unwrapKey().orElseThrow();
        }
    };
    public static final TooltipLinesExtractor<MobEffectInstance, TooltipComponentsConfig> INTERNAL_NAME = new InternalNameLines<>() {
        @Override
        protected ResourceKey<?> getResourceKey(MobEffectInstance mobEffect) {
            return mobEffect.getEffect().unwrapKey().orElseThrow();
        }
    };
    public static final TooltipLinesExtractor<MobEffectInstance, ClientConfig.EffectTooltipComponents> ATTRIBUTES = new PotionContentsLines() {
        @Override
        protected boolean isEnabled(ClientConfig.EffectTooltipComponents tooltipComponents) {
            return tooltipComponents.effectAttributes;
        }

        @Override
        protected Stream<Component> modifyTooltipLines(List<Component> tooltipLines, int separatorIndex) {
            if (separatorIndex != -1) {
                return tooltipLines.subList(separatorIndex, tooltipLines.size()).stream();
            } else {
                return Stream.empty();
            }
        }
    };
    public static final List<TooltipLinesExtractor<MobEffectInstance, TooltipComponentsConfig>> ITEM_SUPPLIERS = ImmutableList.of(
            DESCRIPTION,
            MOD_NAME,
            INTERNAL_NAME);
    public static final List<TooltipLinesExtractor<MobEffectInstance, ClientConfig.EffectTooltipComponents>> WIDGET_SUPPLIERS = ImmutableList.of(
            DESCRIPTION.cast(),
            MOD_NAME.cast(),
            INTERNAL_NAME.cast(),
            ATTRIBUTES);

    private MobEffectTooltipLines() {
        // NO-OP
    }
}
