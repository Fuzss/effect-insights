package fuzs.effectinsights.neoforge.client;

import fuzs.effectinsights.common.EffectInsights;
import fuzs.effectinsights.common.client.EffectInsightsClient;
import fuzs.effectinsights.common.data.client.ModLanguageProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = EffectInsights.MOD_ID, dist = Dist.CLIENT)
public class EffectInsightsNeoForgeClient {

    public EffectInsightsNeoForgeClient() {
        ClientModConstructor.construct(EffectInsights.MOD_ID, EffectInsightsClient::new);
        DataProviderHelper.registerDataProviders(EffectInsights.MOD_ID, ModLanguageProvider::new);
    }
}
