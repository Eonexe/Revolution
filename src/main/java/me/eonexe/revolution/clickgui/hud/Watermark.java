package me.eonexe.revolution.clickgui.hud;

import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.clickgui.hud.render.AnchorPoint;
import me.eonexe.revolution.clickgui.hud.render.HUDElement;
import me.eonexe.revolution.module.Beta.HUD;
import me.eonexe.revolution.util.Font.FontUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;

public class Watermark extends HUDElement {
	public Watermark() {
		super("WaterMark", 2, 2, Category.MISC, AnchorPoint.TopRight);
	}


	@Override
	public void renderElement() {
		GlStateManager.pushMatrix();
		FontUtils.drawString(BadlionMod.NAME + TextFormatting.WHITE + BadlionMod.VERSION, this.x, this.y, HUD.colorSync.getValue() ? ThemeColor.BRIGHT : -1);
		GlStateManager.popMatrix();
	}
}