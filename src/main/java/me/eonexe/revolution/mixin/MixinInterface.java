package me.eonexe.revolution.mixin;

import net.minecraft.client.Minecraft;

public interface MixinInterface {
	
	Minecraft mc = Minecraft.getMinecraft();
	boolean nullCheck = (mc.player == null || mc.world == null);
}
