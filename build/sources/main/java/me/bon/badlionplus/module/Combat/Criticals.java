package me.eonexe.revolution.module.Combat;

import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.event.Events.PacketEvent;
import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;

public class Criticals extends Module {
	public Criticals() {
		super("Criticals", Category.Combat);
	}

	public void onEnable(){
        BadlionMod.EVENT_BUS.subscribe(this);
    }

    public void onDisable(){
        BadlionMod.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private Listener<PacketEvent.Send> sendListener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketUseEntity) {
            if(((CPacketUseEntity) event.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && mc.player.onGround) {
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1f, mc.player.posZ, false));
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
            }
        }
    });
	
}
