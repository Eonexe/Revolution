package me.eonexe.chungus.hack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.eonexe.chungus.Chungus;
import me.eonexe.chungus.hack.command.WurstplusCommand;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;


public class WurstplusSettings extends WurstplusCommand {
	public WurstplusSettings() {
		super("settings", "To save/load settings.");
	}

	public boolean get_message(String[] message) {
		String msg = "null";

		if (message.length > 1) {
			msg = message[1];
		}

		if (msg.equals("null")) {
			WurstplusMessageUtil.send_client_error_message(current_prefix() + "settings <save/load>");

			return true;
		}

		ChatFormatting c = ChatFormatting.GRAY;

		if (msg.equalsIgnoreCase("save")) {
			Chungus.get_config_manager().save_settings();

			WurstplusMessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "saved!");
		} else if (msg.equalsIgnoreCase("load")) {
			Chungus.get_config_manager().load_settings();

			WurstplusMessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "loaded!");
		} else {
			WurstplusMessageUtil.send_client_error_message(current_prefix() + "settings <save/load>");

			return true;
		}

		return true;
	}
}