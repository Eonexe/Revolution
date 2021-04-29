package me.eonexe.chungus.hack.command;


import me.eonexe.chungus.hack.manager.WurstplusCommandManager;


public class WurstplusCommand {
	String name;
	String description;

	public WurstplusCommand(String name, String description) {
		this.name        = name;
		this.description = description;
	}

	public boolean get_message(String[] message) {
		return false;
	}

	public String get_name() {
		return this.name;
	}

	public String get_description() {
		return this.description;
	}

	public String current_prefix() {
		return WurstplusCommandManager.get_prefix();
	}
}