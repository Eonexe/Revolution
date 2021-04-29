package me.eonexe.chungus.hack.guiscreen.hud;


import me.eonexe.chungus.Chungus;
import me.eonexe.chungus.hack.guiscreen.render.pinnables.WurstplusPinnable;


public class WurstplusWatermark extends WurstplusPinnable {
	public WurstplusWatermark() {
		super("Watermark", "Watermark", 1, 0, 0);
	}

	@Override
	public void render() {
		int nl_r = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
		int nl_g = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
		int nl_b = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
		int nl_a = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);

		String line = Chungus.WURSTPLUS_NAME + Chungus.g + " v" + Chungus.get_version();

		create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);

		this.set_width(this.get(line, "width") + 2);
		this.set_height(this.get(line, "height") + 2);
	}
}