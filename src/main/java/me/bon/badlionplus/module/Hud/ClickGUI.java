package me.bon.badlionplus.module.Hud;

import org.lwjgl.input.Keyboard;

import me.bon.badlionplus.clickgui.ClickGui;
import me.bon.badlionplus.module.Category;
import me.bon.badlionplus.module.Module;

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
