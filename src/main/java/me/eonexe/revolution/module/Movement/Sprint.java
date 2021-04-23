package me.eonexe.revolution.module.Movement;

import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.setting.Setting;

public class Sprint extends Module {
	public Sprint() {
		super("Sprint", Category.Movement);
	}

	@Override
	public void onUpdate() {
		try {
			if(mc.gameSettings.keyBindForward.isKeyDown() && !(mc.player.collidedHorizontally)) {
				if(!mc.player.isSprinting()) {
					mc.player.setSprinting(true);
				}
			}
		} catch (Exception e) {
			//do nothing
		}
	}
	
}
