package me.eonexe.chungus.hack.modules.dev;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.eonexe.chungus.hack.event.WurstplusEventBus;
import me.eonexe.chungus.hack.guiscreen.settings.WurstplusSetting;
import me.eonexe.chungus.hack.modules.WurstplusCategory;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import me.eonexe.chungus.hack.util.WurstplusFriendUtil;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;
import me.eonexe.turok.draw.RenderHelp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoTNTCart extends WurstplusHack {
	public AutoTNTCart() {
    	super(WurstplusCategory.WURSTPLUS_BETA);

		this.name        = "5bTNTCart";
		this.tag         = "AutoTNTCart";
		this.description = "I just like this";
    }
	
	private boolean firstSwap = true;
	private boolean secondSwap = true;
	private boolean beginPlacing = false;
	
	private int lighterSlot;
	
	private int tickDelay;
	
	private EntityPlayer closestTarget;
	private BlockPos targetPos;
	
	WurstplusSetting announceUsage = create("Announce Usage", "announceUsageTnt", true);
	WurstplusSetting debug = create("Debug Mode", "debugtnt", false);
	WurstplusSetting carts = create("Place Duration (ticks", "cartstoplace", 15, 1 , 100);
	
	@Override
	public void enable() {
		if(mc.player == null || mc.world == null) {
			this.toggle();
			return;
		}
		
		WurstplusEventBus.EVENT_BUS.subscribe(this);
		MinecraftForge.EVENT_BUS.register(this);
		
		this.tickDelay = 0;
		
		try {
			findClosestTarget();
		} catch(Exception gay) {}
		//not going to run this every tick, it should pick a target and commit to it
		if(closestTarget != null) {
			if(announceUsage.get_value(true)) {
				WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + "Attempting to TNT cart " + ChatFormatting.BLUE.toString() + closestTarget.getName() + ChatFormatting.WHITE.toString() + " ...");
			}
			targetPos = new BlockPos(closestTarget.getPositionVector());
		} else {
			if(announceUsage.get_value(true)) {
				WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + "No target within range to TNT Cart.");
			}
			this.toggle();
		}
	}
	
	@Override
	public void disable() {
		if(mc.player == null || mc.world == null) {
			return;
		}
		
		
		if (announceUsage.get_value(true)) {
			WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + TextFormatting.BLUE + "[" + TextFormatting.GOLD + "AutoTNTCart" + TextFormatting.BLUE + "]" + ChatFormatting.RED.toString() + " Disabled!");
        }
		
		firstSwap = true;
		secondSwap = true;
		beginPlacing = false;
		this.tickDelay = 0;
		
		WurstplusEventBus.EVENT_BUS.unsubscribe(this);
		MinecraftForge.EVENT_BUS.unregister(this);
		
		//these first and second swaps ensure that both a) only 1 rail gets placed and b) that both swaps have occurred without an issue
		
	}
	
	@Override
	public void update() {
		if(mc.player == null || mc.world == null) return;
			int tntSlot = findTNTCart();
			int railSlot = findRail();
			
			if(railSlot > -1 && firstSwap) {
				mc.player.inventory.currentItem = railSlot;
				firstSwap = false;
				placeBlock(targetPos, EnumFacing.DOWN);
				if(debug.get_value(true)) {
					WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + "place rail");
				}
			}
			
			if(tntSlot > -1 && secondSwap && !firstSwap) {
				mc.player.inventory.currentItem = tntSlot;
				secondSwap = false;
				beginPlacing = true;
				if(debug.get_value(true)) {
					WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + "swap tnt & place");
				}
			}
			
			if(!firstSwap && !secondSwap && beginPlacing) {
				if(this.tickDelay > 0) {
					mc.player.swingArm(EnumHand.MAIN_HAND);
					mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(targetPos, EnumFacing.DOWN, EnumHand.MAIN_HAND, 0, 0, 0));
				}
				if(this.tickDelay == carts.get_value(1)) {
					int pickSlot = findPick();
					if(pickSlot > -1) {
						mc.player.inventory.currentItem = pickSlot;
					}
					mc.player.swingArm(EnumHand.MAIN_HAND);
					mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, targetPos, EnumFacing.DOWN));
		        	mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, targetPos, EnumFacing.DOWN));
		        	if(debug.get_value(true)) {
		        		WurstplusMessageUtil.client_message_simple(WurstplusMessageUtil.opener + "break rail");
					}
				}
					if(this.tickDelay == carts.get_value(1) + 5) {
						int flint = findFlint();
			        	if(flint > -1) {
			        		mc.player.inventory.currentItem = flint;
			        		placeBlock(targetPos, EnumFacing.DOWN);
			        	} else {
			        		this.invGrabFlint();
			        		mc.player.inventory.currentItem = 0;
			        		placeBlock(targetPos, EnumFacing.DOWN);
			        	}
			        this.toggle();
				}

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
            if (WurstplusFriendUtil.isFriend(target.getName())) {
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
	
	private int findTNTCart() {
		int slot = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY || stack.getItem() instanceof ItemBlock) {
                continue;
            }
            Item item = ((Item) stack.getItem());
            if(item instanceof ItemMinecart) {
            	slot = i;
            	break;
            }
        }
        return slot;
	}
	private int findRail() {
		int slot = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock)) {
                continue;
            }
            Block block = ((ItemBlock) stack.getItem()).getBlock();
            if(block instanceof BlockRailBase) {
            	slot = i;
            	break;
            }
        }
        return slot;
	}
	private int findFlint() {
		int slot = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY || stack.getItem() instanceof ItemBlock) {
                continue;
            }
            Item item = ((Item) stack.getItem());
            if(item instanceof ItemFlintAndSteel) {
            	slot = i;
            	break;
            }
        }
        return slot;
	}
	private void invGrabFlint() {
    	if (mc.currentScreen == null || !(mc.currentScreen instanceof GuiContainer)) {
            if (mc.player.inventory.getStackInSlot(0).getItem() != Items.FLINT_AND_STEEL) {
               for(int i = 9; i < 36; ++i) {
                  if (mc.player.inventory.getStackInSlot(i).getItem() == Items.FLINT_AND_STEEL) {
                     mc.playerController.windowClick(mc.player.inventoryContainer.windowId, i, 0, ClickType.SWAP, mc.player);
                     lighterSlot = i;
                     break;
                  }

               }

            }
         }
    }
	private int findPick() {
	int pickSlot = -1;
    for (int i = 0; i < 9; i++) {
        ItemStack stack = mc.player.inventory.getStackInSlot(i);
        if (stack == ItemStack.EMPTY) {
            continue;
        }
        if ((stack.getItem() instanceof ItemPickaxe)) {
            pickSlot = i;
            break;
        }
    	}
    return pickSlot;
	}
	private void placeBlock(BlockPos pos, EnumFacing side) {
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        Vec3d hitVec = new Vec3d(neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        mc.playerController.processRightClickBlock(mc.player, mc.world, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
        mc.player.swingArm(EnumHand.MAIN_HAND);
    }
	
	@SubscribeEvent
    public void render(RenderWorldLastEvent event) {
		if(mc.world == null || targetPos == null) return;
		try {
		float posx = targetPos.getX();
		float posy = targetPos.getY();
		float posz = targetPos.getZ();
		RenderHelp.prepare("lines");
		RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(),
                targetPos.getX(), targetPos.getY(), targetPos.getZ(),
            1, .2f, 1,
            255, 20, 20, 180,
            "all"
            );
		}catch(Exception gay) {}
		RenderHelp.release();
	} 

}
