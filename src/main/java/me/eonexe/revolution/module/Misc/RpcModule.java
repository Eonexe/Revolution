package me.eonexe.revolution.module.Misc;

import me.eonexe.revolution.BadlionRPC;
import me.eonexe.revolution.command.Command;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.module.Category;

public class RpcModule extends Module {
    public RpcModule() {
        super("DiscordRPC", Category.Misc);
    }

    public void onEnable(){
        BadlionRPC.init();
    }

    public void onDisable(){
        BadlionRPC.init();
    }
}
