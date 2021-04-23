package me.bon.badlionplus.module.Beta;

import me.bon.badlionplus.event.Events.PacketEvent;
import me.bon.badlionplus.module.Category;
import me.bon.badlionplus.module.Module;
import me.bon.badlionplus.setting.Setting;
import me.bon.badlionplus.util.EZUtil;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class AutoEZ extends Module {
	public AutoEZ() {
		super("Auto EZ (WIP)", Category.Beta);
	}
	int delay_count = 0;

	private static final Minecraft mc = Minecraft.getMinecraft();
	
    Setting custom;
    
    @Override
    public void setup() {
		rSetting(custom = new Setting("Custom", this, false, "custom"));
    }

    private static final ConcurrentHashMap targeted_players = new ConcurrentHashMap();

    @EventHandler
    private Listener<PacketEvent.Send> send_listener = new Listener<>(event -> {

        if (mc.player == null) return;

        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity cPacketUseEntity = (CPacketUseEntity) event.getPacket();
            if (cPacketUseEntity.getAction().equals(CPacketUseEntity.Action.ATTACK)) {
                Entity target_entity = cPacketUseEntity.getEntityFromWorld(mc.world);
                if (target_entity instanceof EntityPlayer) {
                    add_target(target_entity.getName());
                }
            }
        }

    });

    @EventHandler
    private Listener<LivingDeathEvent> living_death_listener = new Listener<>(event -> {

        if (mc.player == null) return;

        EntityLivingBase e = event.getEntityLiving();
        if (e == null) return;

        if (e instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e;

            if (player.getHealth() <= 0) {
                if (targeted_players.containsKey(player.getName())) {
                    announce(player.getName());
                }
            }
        }

    });

    @Override
    public void onUpdate() {

        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.getHealth() <= 0) {
                    if (targeted_players.containsKey(player.getName())) {
                        announce(player.getName());
                    }
                }
            }
        }

        targeted_players.forEach((name, timeout) -> {
            if ((int)timeout <= 0) {
                targeted_players.remove(name);
            } else {
                targeted_players.put(name, (int)timeout - 1);
            }

        });

        delay_count++;

    }

    public void announce(String name) {
        if (delay_count < 150) {
            return;
        }
        delay_count = 0;
        targeted_players.remove(name);
        String message = "";
        if (custom.getValBoolean()) {
            message += EZUtil.get_message().replace("[", "").replace("]", "");
        } else {
            message += "you just got nae nae'd by wurst+2";
        }
        mc.player.connection.sendPacket(new CPacketChatMessage(message));
    }

    public static void add_target(String name) {
        if (!Objects.equals(name, mc.player.getName())) {
            targeted_players.put(name, 20);
        }
    }

}
