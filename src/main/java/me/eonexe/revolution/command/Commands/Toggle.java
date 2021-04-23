package me.eonexe.revolution.command.Commands;

import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.command.Command;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.util.Messages;
import net.minecraft.util.text.TextFormatting;

public class Toggle extends Command {
    public Toggle() {
        super("Toggle", new String[]{"t", "toggle"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length > 1) {
            try {
            for (Module m: BadlionMod.moduleManager.getModules()) {
                if (m.getName().equalsIgnoreCase(args[1])) {
                    m.toggle();
                    if (m.isToggled()) {
                        Messages.sendMessagePrefix(TextFormatting.WHITE + m.getName() + TextFormatting.GREEN + " ENABLED");
                    } else {
                        Messages.sendMessagePrefix(TextFormatting.WHITE + m.getName() + TextFormatting.RED + " DISABLED");
                    }
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}