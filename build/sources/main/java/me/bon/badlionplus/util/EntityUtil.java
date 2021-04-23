package me.bon.badlionplus.util;

import me.bon.badlionplus.friends.Friends;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

public class EntityUtil {
	public static final Minecraft mc = Minecraft.getMinecraft();
	public static void attackEntity(final Entity entity, final boolean packet) {
        if (packet) {
            mc.player.connection.sendPacket(new CPacketUseEntity(entity));
        }
        else {
            mc.playerController.attackEntity(mc.player, entity);
        }

    }
	public static boolean basicChecksEntity(Entity pl) {
        return pl.getName().equals(mc.player.getName()) || Friends.isFriend(pl.getName()) || pl.isDead;
    }
}