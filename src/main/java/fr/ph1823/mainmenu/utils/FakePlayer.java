package fr.ph1823.mainmenu.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;

public class FakePlayer extends AbstractClientPlayer {
    public FakePlayer(GameProfile profile, World world) {
        super(world, profile);
    }

    @Nullable
    protected NetworkPlayerInfo getPlayerInfo()
    {

        return new NetworkPlayerInfo(this.getGameProfile());
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public double getDistanceSq(Entity p_70068_1_) {
        return 1.0D;
    }

    @Override
    public double getDistanceSq(BlockPos p_174818_1_) {
        return 1.0D;
    }

    @Override
    public double getDistance(double p_70011_1_, double p_70011_3_, double p_70011_5_) {
        return 1.0D;
    }

    @Override
    public double getDistanceSq(double p_70092_1_, double p_70092_3_, double p_70092_5_) {
        return 1.0D;
    }

    @Override
    public float getDistance(Entity p_70032_1_) {
        return 1.0f;
    }

}
