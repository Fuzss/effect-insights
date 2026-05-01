package fuzs.effectinsights.fabric;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class EffectInsightsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(EffectInsights.MOD_ID, EffectInsights::new);
    }
}
