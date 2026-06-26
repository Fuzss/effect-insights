package fuzs.effectinsights.common.mixin.client;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.effectinsights.common.client.handler.BeaconTooltipHandler;
import fuzs.effectinsights.common.config.ClientConfig;
import fuzs.puzzleslib.common.api.client.gui.v2.tooltip.TooltipBuilder;
import fuzs.tooltipinsights.common.api.v1.config.TooltipDescriptionMode;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(targets = "net.minecraft.client.gui.screens.inventory.BeaconScreen$BeaconPowerButton")
abstract class BeaconScreen$BeaconPowerButtonMixin extends AbstractButton {
    @Shadow
    @Final
    private boolean isPrimary;
    @Shadow
    @Final
    protected int tier;
    @Shadow
    @Final
    BeaconScreen this$0;

    public BeaconScreen$BeaconPowerButtonMixin(int i, int j, int k, int l, Component component) {
        super(i, j, k, l, component);
    }

    @Inject(method = "setEffect", at = @At("TAIL"))
    protected void setEffect(Holder<MobEffect> effect, CallbackInfo callback) {
        if (EffectInsights.CONFIG.get(ClientConfig.class).effectBeaconTooltips.tooltipDescriptions
                == TooltipDescriptionMode.DISABLED) {
            return;
        }

        TooltipBuilder.create().setLines(() -> {
            List<Component> tooltipLines = new ArrayList<>(Arrays.asList(this.createEffectDescription(effect)));
            MobEffectInstance mobEffect = new MobEffectInstance(effect,
                    0,
                    this.effectinsights$getBeaconEffectAmplifier(effect));
            new BeaconTooltipHandler(mobEffect).onGatherTooltipComponents(this.this$0.minecraft, tooltipLines);
            return tooltipLines;
        }).build(this);
    }

    @Shadow
    protected abstract MutableComponent createEffectDescription(Holder<MobEffect> effect);

    @Unique
    private int effectinsights$getBeaconEffectAmplifier(Holder<MobEffect> holder) {
        // BeaconPowerButton::createEffectDescription does not provide something we can use, so try to mimic it.
        if (!this.isPrimary && this.tier < BeaconBlockEntity.BEACON_EFFECTS.size()) {
            if (!BeaconBlockEntity.BEACON_EFFECTS.get(this.tier).contains(holder)) {
                return 1;
            }
        }

        return 0;
    }
}
