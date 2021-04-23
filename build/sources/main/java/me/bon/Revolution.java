package me.eonexe.revolution;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import me.eonexe.revolution.command.CommandManager;
import me.eonexe.revolution.friends.Friends;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.module.ModuleManager;
import me.eonexe.revolution.module.Combat.Criticals;
import me.eonexe.revolution.setting.SettingsManager;
import me.eonexe.revolution.util.ConfigManager;
import me.eonexe.revolution.util.Messages;
import me.eonexe.revolution.util.Font.CFontRenderer;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

//most of the credits to this client can be found in mcmod.info
//thanks to everyone i copped code from

//might do a more original rewrite if i can be asked

@Mod(modid = revolution.MODID, name = Revolution.NAME, version = revolution.VERSION)
public class BadlionMod
{
    public static final String MODID = "revolution";
    public static final String NAME = "Revolution";
    public static final String VERSION = "1.0.0";
    
    public static CFontRenderer fontRenderer;
    
    public static SettingsManager settingsManager;
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public Friends friends;
    public static final EventBus EVENT_BUS = new EventManager();
    
    public static String prefix = ",";
    
    @Mod.Instance
    private static revolution INSTANCE;
    
    public revolution() {
    	INSTANCE = this;
    }
    
    public static revolution getInstance() {
    	return INSTANCE;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	Display.setTitle(NAME + " Utility Mod " + VERSION );
    	friends = new Friends();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	settingsManager = new SettingsManager();
    	moduleManager = new ModuleManager();
    	configManager = new ConfigManager();
    	fontRenderer = new CFontRenderer(new Font("Comfortaa", Font.PLAIN, 20), true, false);
    	CommandManager.init();
    	MinecraftForge.EVENT_BUS.register(new CommandManager());
    	MinecraftForge.EVENT_BUS.register(new Criticals());
    	MinecraftForge.EVENT_BUS.register(this);
    }
    


    
}
