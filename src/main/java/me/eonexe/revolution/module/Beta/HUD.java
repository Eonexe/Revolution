package me.eonexe.revolution.module.Beta;

import org.lwjgl.input.Keyboard;

import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUD extends Module {
	public HUD() {
		super("HUD (WIP)", Category.Beta);
	}

	HUDScreen hudEditor = new HUDScreen();

	@Override
	public void onEnable() {
		if (nullCheck())
			return;

		super.onEnable();
		ScreenManager.setScreen(hudEditor);
	}
	
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Text event) {
		// boost = 0;
	}
}
	

