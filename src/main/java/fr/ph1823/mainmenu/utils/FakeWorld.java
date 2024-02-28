package fr.ph1823.mainmenu.utils;

import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldInfo;

public class FakeWorld extends World {
    private ChunkProviderClient clientChunkProvider;
    public FakeWorld() {

        super(new SaveHandlerMP(), new WorldInfo(new WorldSettings(48948484464L, GameType.SURVIVAL, false,false, WorldType.FLAT), "lol"),net.minecraftforge.common.DimensionManager.createProviderFor(0) ,new Profiler(), true);
        this.getWorldInfo().setDifficulty(EnumDifficulty.EASY);
        this.provider.setWorld(this);
        this.setSpawnPoint(new BlockPos(8, 64, 8)); //Forge: Moved below registerWorld to prevent NPE in our redirect.
    }

    @Override
    protected IChunkProvider createChunkProvider()
    {
        this.clientChunkProvider = new ChunkProviderClient(this);
        return this.clientChunkProvider;
    }

    @Override
    protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
        return false;
    }
}
