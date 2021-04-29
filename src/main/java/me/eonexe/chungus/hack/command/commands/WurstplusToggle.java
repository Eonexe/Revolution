package me.eonexe.chungus.hack.command.commands;

import me.eonexe.chungus.Chungus;
import me.eonexe.chungus.hack.command.WurstplusCommand;
import me.eonexe.chungus.hack.manager.WurstplusCommandManager;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;

public class WurstplusToggle extends WurstplusCommand {
	public WurstplusToggle() {
		super("t", "turn on and off stuffs");
	}

	public boolean get_message(String[] message) {
		String module = "null";

		if (message.length > 1) {
			module = message[1];
		}

		if (message.length > 2) {
			WurstplusMessageUtil.send_client_error_message(current_prefix() + "t <ModuleNameNoSpace>");

			return true;
		}

		if (module.equals("null")) {
			WurstplusMessageUtil.send_client_error_message(WurstplusCommandManager.get_prefix() + "t <ModuleNameNoSpace>");

			return true;
		}

		WurstplusHack module_requested = Chungus.get_module_manager().get_module_with_tag(module);

		if (module_requested != null) {
			module_requested.toggle();

			WurstplusMessageUtil.send_client_message("[" + module_requested.get_tag() + "] - Toggled to " + module_requested.is_active() + ".");
		} else {
			WurstplusMessageUtil.send_client_error_message("Module does not exist.");
		}

		return true;
	}
}