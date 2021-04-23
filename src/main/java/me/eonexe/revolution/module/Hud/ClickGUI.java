package me.eonexe.revolution.module.Hud;

import org.lwjgl.input.Keyboard;

import me.eonexe.revolution.clickgui.ClickGui;
import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;

public class ClickGUI extends Module {
	public ClickGUI() {
		super("ClickGUI", Category.Render);
		setKey(Keyboard.KEY_P);
	}

	
	@Override
    public void onEnable() {
        if (mc.player != null && mc.world != null) {
            mc.displayGuiScreen(new ClickGui());
            toggle();
        }
    }
	

	
}
