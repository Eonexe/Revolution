package me.eonexe.revolution.module.Beta;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.eonexe.revolution.friends.Friends;
import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.setting.Setting;
import me.eonexe.revolution.util.Messages;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPiston;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
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
			/*
			if(pistonSlot > -1 && firstSwap) {
				mc.player.inventory.currentItem = pistonSlot;
				firstSwap = false;
				placeBlock(targetPos, EnumFacing.EAST);
			}
			*/
			
		}
	}
	// le me pasting this exact same method into every mf module
		private void findClosestTarget() {
	        List<EntityPlayer> playerList = mc.world.playerEntities;
	        closestTarget = null;
	        for (EntityPlayer target : playerList) {
	            if (target == mc.player) {
	                continue;
	            }
	            if (Friends.isFriend(target.getName())) {
	                continue;
	            }
	            if (!isLiving(target)) {
	                continue;
	            }
	            if ((target).getHealth() <= 0) {
	                continue;
	            }
	            if(mc.player.getDistance(target) > 6) {
	            	//im not gonna bother making a range option
	            	//6 seems reasonable enough
	            	continue;
	            }
	            if (closestTarget == null) {
	                closestTarget = target;
	                continue;
	            }
	        }
	    }
		public static boolean isLiving(Entity e) {
	        return e instanceof EntityLivingBase;
	    }
}