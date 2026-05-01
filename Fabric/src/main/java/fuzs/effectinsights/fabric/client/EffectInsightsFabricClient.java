package fuzs.effectinsights.fabric.client;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.effectinsights.common.client.EffectInsightsClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class EffectInsightsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(EffectInsights.MOD_ID, EffectInsightsClient::new);
    }
}
