package me.eonexe.chungus.hack.event;

import me.eonexe.chungus.hack.manager.WurstplusCommandManager;
import me.eonexe.chungus.hack.manager.WurstplusEventManager;
import net.minecraftforge.common.MinecraftForge;


public class WurstplusEventRegister {
	public static void register_command_manager(WurstplusCommandManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}

	public static void register_module_manager(WurstplusEventManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}
}