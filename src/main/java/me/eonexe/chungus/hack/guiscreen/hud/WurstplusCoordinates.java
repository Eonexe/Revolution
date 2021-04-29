package me.eonexe.chungus.hack.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.eonexe.chungus.Chungus;
import me.eonexe.chungus.hack.guiscreen.render.pinnables.WurstplusPinnable;


public class WurstplusCoordinates extends WurstplusPinnable {
	ChatFormatting dg = ChatFormatting.DARK_GRAY;
	ChatFormatting db = ChatFormatting.DARK_BLUE;
	ChatFormatting dr = ChatFormatting.DARK_RED;

	public WurstplusCoordinates() {
		super("Coordinates", "Coordinates", 1, 0, 0);
	}

	@Override
	public void render() {
		int nl_r = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
		int nl_g = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
		int nl_b = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
		int nl_a = Chungus.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);

		String x = Chungus.g + "[" + Chungus.r + Integer.toString((int) (mc.player.posX)) + Chungus.g + "]" + Chungus.r;
		String y = Chungus.g + "[" + Chungus.r + Integer.toString((int) (mc.player.posY)) + Chungus.g + "]" + Chungus.r;
		String z = Chungus.g + "[" + Chungus.r + Integer.toString((int) (mc.player.posZ)) + Chungus.g + "]" + Chungus.r;

		String x_nether = Chungus.g + "[" + Chungus.r + Long.toString(Math.round(mc.player.dimension != -1 ? (mc.player.posX / 8) : (mc.player.posX * 8))) + Chungus.g + "]" + Chungus.r;
		String z_nether = Chungus.g + "[" + Chungus.r + Long.toString(Math.round(mc.player.dimension != -1 ? (mc.player.posZ / 8) : (mc.player.posZ * 8))) + Chungus.g + "]" + Chungus.r;

		String line = "XYZ " + x + y + z + " XZ " + x_nether + z_nether;

		create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);

		this.set_width(this.get(line, "width"));
		this.set_height(this.get(line, "height") + 2);
	}
}