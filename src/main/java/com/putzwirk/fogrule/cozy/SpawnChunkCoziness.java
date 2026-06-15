package com.putzwirk.fogrule.cozy;



import com.putzwirk.fogrule.FogRule;
import com.putzwirk.fogrule.FogRuleConfig;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber(modid = FogRule.MODID)
public class SpawnChunkCoziness {

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        if (event.getChunk() instanceof LevelChunk levelChunk && !levelChunk.getLevel().isClientSide()) {
            ChunkPos chunkPos = levelChunk.getPos();

            if (chunkPos.x == 0 && chunkPos.z == 0) {
                ChunkCozinessData data = levelChunk.getData(ChunkCozinessData.CHUNK_DATA);
                float spawnCoziness = FogRuleConfig.SPAWN_COZINESS.get().floatValue();
                if (data.getCoziness() != spawnCoziness) {
                    data.setCoziness(spawnCoziness);
                    data.setLastVisitedTime(levelChunk.getLevel().getGameTime());
                    levelChunk.setUnsaved(true);
                }
            }
        }
    }
}
