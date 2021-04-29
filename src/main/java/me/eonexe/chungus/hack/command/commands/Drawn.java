package me.eonexe.chungus.hack.command.commands;

import me.eonexe.chungus.Chungus;
import me.eonexe.chungus.hack.command.WurstplusCommand;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import me.eonexe.chungus.hack.util.WurstplusDrawnUtil;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;

import java.util.List;

public class Drawn extends WurstplusCommand {
    
    public Drawn() {
        super("drawn", "Hide elements of the array list");
    }

    public boolean get_message(String[] message) {

        if (message.length == 1) {
            WurstplusMessageUtil.send_client_error_message("module name needed");

            return true;
        }

        if (message.length == 2) {

            if (is_module(message[1])) {
                WurstplusDrawnUtil.add_remove_item(message[1]);
                Chungus.get_config_manager().save_settings();
            } else {
                WurstplusMessageUtil.send_client_error_message("cannot find module by name: " + message[1]);
            }
            return true;

        }

        return false;
    
    }

    public boolean is_module(String s) {

        List<WurstplusHack> modules = Chungus.get_hack_manager().get_array_hacks();

        for (WurstplusHack module : modules) {
            if (module.get_tag().equalsIgnoreCase(s)) {
                return true;
            }
        }

        return false;

    }

}