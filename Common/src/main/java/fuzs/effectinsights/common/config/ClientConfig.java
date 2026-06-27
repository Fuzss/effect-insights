package fuzs.effectinsights.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;
import fuzs.tooltipinsights.common.api.v1.config.StyledTooltipsConfig;
import fuzs.tooltipinsights.common.api.v1.config.TooltipComponentsConfig;

public class ClientConfig implements ConfigCore {
    @Config(description = "Controls effect descriptions shown when hovering effect widgets in the player inventory screen.")
    public final StyledTooltipsConfig<EffectTooltipComponents> effectWidgetTooltips = new SingleEffectTooltips();
    @Config(description = "Controls effect descriptions shown on item tooltips.")
    public final EffectItemTooltips effectItemTooltips = new EffectItemTooltips();
    @Config(description = "Controls effect descriptions shown when hovering effect buttons in the beacon screen.")
    public final StyledTooltipsConfig<EffectTooltipComponents> effectBeaconTooltips = new SingleEffectTooltips();

    public static class SingleEffectTooltips extends StyledTooltipsConfig<EffectTooltipComponents> {
        @Config(description = "Controls text lines added to effect description tooltips.")
        public final EffectTooltipComponents widgetTooltipLines = new EffectTooltipComponents();

        @Override
        public EffectTooltipComponents tooltipLines() {
            return this.widgetTooltipLines;
        }
    }

    public static class EffectItemTooltips extends StyledTooltipsConfig<TooltipComponentsConfig> {
        @Config(description = "Add effect descriptions to certain item groups.")
        public final EffectDescriptionTargets itemDescriptionTargets = new EffectDescriptionTargets();
        @Config(description = "Controls text lines added to effect description tooltips.")
        public final TooltipComponentsConfig itemTooltipLines = new TooltipComponentsConfig();
        @Config(description = "Display potion effects for consumable item tooltips, such as food or the totem of undying.")
        public boolean consumablesEffectTooltips = true;

        @Override
        public TooltipComponentsConfig tooltipLines() {
            return this.itemTooltipLines;
        }
    }

    public static class EffectDescriptionTargets implements ConfigCore {
        @Config(description = "Add effect descriptions to potion items.")
        public boolean potion = true;
        @Config(description = "Add effect descriptions to consumable items (such as rotten flesh and raw chicken).")
        public boolean consumable = true;
        @Config(description = "Add effect descriptions to totem of undying items.")
        public boolean totemOfUndying = true;
        @Config(description = "Add effect descriptions to ominous bottle items.")
        public boolean ominousBottle = true;
        @Config(description = "Add effect descriptions to suspicious stew items.")
        public boolean suspiciousStew = true;
    }

    public static class EffectTooltipComponents extends TooltipComponentsConfig {
        @Config(description = "Add attributes granted by an effect to tooltips.")
        public boolean effectAttributes = true;
    }
}
