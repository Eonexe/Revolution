package me.eonexe.revolution.module.Movement;

import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.event.Events.PacketEvent;
import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.util.Messages;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;


public class Velocity extends Module {
	public Velocity() {
		super("Velocity", Category.Movement);
	}
	
	@Override
	public void onEnable() {
		BadlionMod.EVENT_BUS.subscribe(this);
	}
	
	@Override
	public void onDisable() {
		BadlionMod.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
    private Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if(event.getPacket() instanceof SPacketEntityVelocity){
            if(((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId())
                event.cancel();
        }
        if(event.getPacket() instanceof SPacketExplosion)
            event.cancel();
    });
}
