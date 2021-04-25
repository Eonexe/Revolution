package me.eonexe.revolution.command.Commands;

import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.command.Command;
import me.eonexe.revolution.util.Messages;
import net.minecraft.util.text.TextFormatting;

public class Help extends Command {
	public Help() {
		super("Help", new String[] {"Help"});
	}
	
	@Override
    public void onCommand(String[] args) {
		
		Messages.sendMessagePrefix(TextFormatting.WHITE + "Revolution Help");
		Messages.sendMessagePrefix(TextFormatting.WHITE + "Type " + BadlionMod.prefix + "'bind [key] clickgui' to bind the ClickGUI");
		Messages.sendMessagePrefix(TextFormatting.WHITE + "The current prefix is " + BadlionMod.prefix);
		Messages.sendMessagePrefix(TextFormatting.WHITE + "Type " + BadlionMod.prefix + "'toggle [module]' to toggle a module");
		Messages.sendMessagePrefix(TextFormatting.WHITE + "Type " + BadlionMod.prefix + "'bind [module] [key]' to keybind a module");
		Messages.sendMessagePrefix(TextFormatting.WHITE + "Type " + BadlionMod.prefix + "'bind [module] none' to unbind a module");
		Messages.sendMessagePrefix(TextFormatting.WHITE + "Type " + BadlionMod.prefix + "'friend [add/del] [username]' to add or remove a friend");
		
		
	}
}
