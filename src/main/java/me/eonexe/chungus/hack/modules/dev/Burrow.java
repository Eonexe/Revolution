package me.eonexe.chungus.hack.modules.dev;

import me.eonexe.chungus.hack.guiscreen.settings.WurstplusSetting;
import me.eonexe.chungus.hack.modules.WurstplusCategory;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import me.eonexe.chungus.hack.util.BurrowUtil;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

/**
 * @author Ciruu
 */

public class Burrow extends WurstplusHack {
	
	 WurstplusSetting rotate = create("Rotate", "Rotate", false);
	 WurstplusSetting offset = create("Offset", "Offset", 7.0F, -20.0F, 20.0F);

    private BlockPos originalPos;
    private int oldSlot = -1;

    public Burrow() {
    	super(WurstplusCategory.WURSTPLUS_BETA);

		this.name        = "Burrow";
		this.tag         = "no";
		this.description = "brue";
    }

    @Override
    public void enable() {
        super.enable();

        // Save our original pos
        originalPos = new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ);

        // If we can't place in our actual post then toggle and return
        if (mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ)).getBlock().equals(Blocks.OBSIDIAN) ||
                intersectsWithEntity(this.originalPos)) {
            toggle();
            return;
        }

        // Save our item slot
        oldSlot = mc.player.inventory.currentItem;
    }

    @EventHandler
    private final Listener<ClientTickEvent> onTick = new Listener<>(event -> {
        // If we don't have obsidian in hotbar toggle and return
        if (BurrowUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + "Can't find obsidian in hotbar!");
            toggle();
            return;
        }

        // Change to obsidian slot
        BurrowUtil.switchToSlot(BurrowUtil.findHotbarBlock(BlockObsidian.class), false);

        // Fake jump
        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.41999998688698D, mc.player.posZ, true));
        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.7531999805211997D, mc.player.posZ, true));
        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.00133597911214D, mc.player.posZ, true));
        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 1.16610926093821D, mc.player.posZ, true));

        // Place block
        BurrowUtil.placeBlock(originalPos, EnumHand.MAIN_HAND, rotate.getValue(), true, false);

        // Rubberband
        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + offset.getValue1(), mc.player.posZ, false));

        // SwitchBack
        BurrowUtil.switchToSlot(oldSlot, false);

        // AutoDisable
        toggle();
    });

    private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : mc.world.loadedEntityList) {
            if (entity.equals(mc.player)) continue;
            if (entity instanceof EntityItem) continue;
            if (new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) return true;
        }
        return false;
    }
}