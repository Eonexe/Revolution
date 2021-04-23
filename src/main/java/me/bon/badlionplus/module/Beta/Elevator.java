package me.bon.badlionplus.module.Beta;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.bon.badlionplus.module.Category;
import me.bon.badlionplus.module.Module;
import me.bon.badlionplus.setting.Setting;
import me.bon.badlionplus.util.Messages;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPiston;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;

public class Elevator extends Module {
	public Elevator() {
		super("Elevator (WIP)", Category.Beta);
	}
	
	private boolean firstSwap = true;
	private boolean secondSwap = true;
	private boolean beginPlacing = false;
	
	private int lighterSlot;
	
	private EntityPlayer closestTarget;
	private BlockPos targetPos;
	
	Setting range;
	Setting announceUsage;
	
	@Override
	public void setup() {
		rSetting(range = new Setting("Range", this, 7, 0, 9, true, "range"));
		rSetting(announceUsage = new Setting("Announce Usage", this, true, "announceUsageElevate"));
	}
	
	@Override
	public void onEnable() {
		if(mc.player == null) {
			this.toggle();
			return;
		}
		MinecraftForge.EVENT_BUS.register(this);
		try { 
			//findClosestTarget();
		} catch(Exception gay) {}
		
		if(closestTarget != null) {
			if(announceUsage.getValBoolean()) {
				Messages.sendMessagePrefix("Elevating " + ChatFormatting.BLUE.toString() + closestTarget.getName());
			}
			targetPos = new BlockPos(closestTarget.getPositionVector());
		} else {
			if(announceUsage.getValBoolean()) {
				Messages.sendMessagePrefix("No target found!");
			}
			this.toggle();
		}
	}
	
	@Override
	public void onDisable() {
		if(mc.player == null) {
			return;
		}
		
		if (announceUsage.getValBoolean()) {
			Messages.sendMessagePrefix(TextFormatting.BLUE + "[" + TextFormatting.GOLD + "Elevator" + TextFormatting.BLUE + "]" + ChatFormatting.RED.toString() + " Disabled!");
		}
		
		firstSwap = true;
		secondSwap = true;
		beginPlacing = false;
		
		MinecraftForge.EVENT_BUS.unregister(this);
	}
	
	@Override
	public void onUpdate() {
		if(mc.player == null) {
			//int redstoneSlot = findRedstoneBlock();
			//int pistonSlot = findPiston();
		}
	}
}