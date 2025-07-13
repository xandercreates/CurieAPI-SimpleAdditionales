package net.timeworndevs.quantumadds.world.biome;

import net.timeworndevs.quantumadds.QuantumAdds;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(new Identifier(QuantumAdds.MOD_ID, "overworld"), 4));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, QuantumAdds.MOD_ID, ModMaterialRules.makeRules());
    }
}
