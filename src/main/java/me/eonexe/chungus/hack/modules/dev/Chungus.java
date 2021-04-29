package me.eonexe.chungus.hack.modules.dev;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.eonexe.chungus.hack.guiscreen.settings.WurstplusSetting;
import me.eonexe.chungus.hack.modules.WurstplusCategory;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import me.eonexe.chungus.hack.util.Wrapper;
import me.eonexe.chungus.hack.util.WurstplusBlockInteractionHelper;
import me.eonexe.chungus.hack.util.WurstplusEntityUtil;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Chungus extends WurstplusHack {
    WurstplusSetting ticks = this.create("Ticks", "Ticks", 0, 0, 60);

    public Chungus() {
        super(WurstplusCategory.WURSTPLUS_BETA);
        this.name = "Chungus";
        this.tag = "bruh";
        this.description = "glitch you in a block";
    }

    public void update() {
        WurstplusMessageUtil.send_client_message("" + ChatFormatting.GRAY + "> " + ChatFormatting.RESET + "attempting to burrow.");
        if (mc.player != null) {
            Vec3d vec3d = WurstplusEntityUtil.getInterpolatedPos(mc.player, (float)this.ticks.get_value(1));
            BlockPos blockPos = (new BlockPos(vec3d)).down();
            BlockPos belowBlockPos = blockPos.down();
            if (mc.player.onGround) {
                mc.player.jump();
            }

            if (Wrapper.getWorld().getBlockState(blockPos).getMaterial().isReplaceable()) {
                int newSlot = -1;

                int oldSlot;
                for(oldSlot = 0; oldSlot < 9; ++oldSlot) {
                    ItemStack stack = Wrapper.getPlayer().inventory.getStackInSlot(oldSlot);
                    if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                        Block block = ((ItemBlock)stack.getItem()).getBlock();
                        if (!WurstplusBlockInteractionHelper.blackList.contains(block) && !(block instanceof BlockContainer) && Block.getBlockFromItem(stack.getItem()).getDefaultState().isFullBlock() && (!(((ItemBlock)stack.getItem()).getBlock() instanceof BlockFalling) || !Wrapper.getWorld().getBlockState(belowBlockPos).getMaterial().isReplaceable())) {
                            newSlot = oldSlot;
                            break;
                        }
                    }
                }

                if (newSlot != -1) {
                    oldSlot = Wrapper.getPlayer().inventory.currentItem;
                    Wrapper.getPlayer().inventory.currentItem = newSlot;
                    if (WurstplusBlockInteractionHelper.checkForNeighbours(blockPos)) {
                        WurstplusBlockInteractionHelper.placeBlockScaffold(blockPos);
                        Wrapper.getPlayer().inventory.currentItem = oldSlot;
                        WurstplusMessageUtil.send_client_message("" + ChatFormatting.GRAY + "> " + ChatFormatting.GOLD + "burrow complete." + ChatFormatting.RESET);
                        this.set_disable();
                    }
                }
            }
        }
    }
}