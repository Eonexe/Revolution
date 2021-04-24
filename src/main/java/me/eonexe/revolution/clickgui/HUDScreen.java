package me.eonexe.revolution.clickgui;

import java.io.IOException;

import me.eonexe.revolution.clickgui.hud.render.HUDElement;
import me.eonexe.revolution.util.GUIUtil;
import net.minecraft.client.gui.GuiScreen;

public class HUDScreen extends GuiScreen {

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		for (HUDFrame frame : HUDFrame.frames) {
			frame.mouseWheelListen();
			frame.renderHUDFrame();
		}

		for (HUDElement element : HUDElementManager.getComponents()) {
			element.renderElementOverlay();
		}

		GUIUtil.mouseListen(mouseX, mouseY);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (mouseButton == 0) {
			for (HUDFrame frame : HUDFrame.frames)
				frame.lclickListen();

			for (HUDElement element : HUDElementManager.getComponents())
				element.lclickListen(mouseButton);

			GUIUtil.lclickListen();
		}

		if (mouseButton == 1) {
			for (HUDFrame frame : HUDFrame.frames)
				frame.rclickListen();

			for (HUDElement element : HUDElementManager.getComponents())
				element.rclickListen();

			GUIUtil.rclickListen();
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		if (state == 0) {
			for (HUDFrame frame : HUDFrame.frames)
				frame.releaseListen();

			for (HUDElement element : HUDElementManager.getComponents())
				element.releaseListen();

			GUIUtil.releaseListen();
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		GUIUtil.keyListen(keyCode);
	}

	/*
	@Override
	public void onGuiClosed() {
		try {
			super.onGuiClosed();
			ModuleManager.getModuleByName("HUD").disable();

			mc.entityRenderer.getShaderGroup().deleteShaderGroup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	//@Override
	//public boolean doesGuiPauseGame() {
	//	return ClickGUI.pauseGame.getValue();
	//}
}