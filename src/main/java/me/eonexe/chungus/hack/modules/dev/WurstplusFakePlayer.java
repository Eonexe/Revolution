package me.eonexe.chungus.hack.modules.dev;

import com.mojang.authlib.GameProfile;

import me.eonexe.chungus.hack.modules.WurstplusCategory;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

public class WurstplusFakePlayer extends WurstplusHack {
    
    public WurstplusFakePlayer() {
        super(WurstplusCategory.WURSTPLUS_BETA);

		this.name        = "Fake Player";
		this.tag         = "FakePlayer";
		this.description = "hahahaaha what a noob its in beta ahahahahaha";
    }

    private EntityOtherPlayerMP fake_player;

    @Override
    protected void enable() {
        
        fake_player = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("a07208c2-01e5-4eac-a3cf-a5f5ef2a4700"), "travis"));
        fake_player.copyLocationAndAnglesFrom(mc.player);
        fake_player.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(-100, fake_player);

    }

    @Override
    protected void disable() {
        try {
            mc.world.removeEntity(fake_player);
        } catch (Exception ignored) {}
    }

}