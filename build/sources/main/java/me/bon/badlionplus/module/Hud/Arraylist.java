package me.eonexe.revolution.module.Hud;

import me.eonexe.revolution.BadlionMod;
import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import me.eonexe.revolution.setting.Setting;
import me.eonexe.revolution.util.ColorUtils;
import me.eonexe.revolution.util.Font.FontUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Arraylist extends Module {
	public Arraylist() {
		super("Arraylist", Category.Render);
	}
	
	Setting rainbow;
	Setting r;
	Setting g;
	Setting b;
	Setting a;
	
	
	@Override
	public void setup() {
		rSetting(rainbow = new Setting("Rainbow", this, true, "rainbow"));
		rSetting(r = new Setting("Red", this, 255, 0, 255, true, "r"));
		rSetting(g = new Setting("Green", this, 255, 0, 255, true, "g"));
		rSetting(b = new Setting("Blue", this, 255, 0, 255, true, "b"));
		rSetting(a = new Setting("Alpha", this, 255, 0, 255, true, "a"));
	}
	

	
	
	@Override
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);

	}
	
	@Override
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
	
	@SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {

        if (mc.player != null && mc.world != null) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            	int nonrainbow = ColorUtils.toRGBA(r.getValInt(), g.getValInt(), b.getValInt(), a.getValInt());
            	int currY = mc.fontRenderer.FONT_HEIGHT + 2;
            	if(rainbow.getValBoolean()) {
            	BadlionMod.fontRenderer.drawStringWithShadow(BadlionMod.NAME + " v" + BadlionMod.VERSION, 2, 190, ColorUtils.GenRainbow());
            	BadlionMod.fontRenderer.drawStringWithShadow("Welcome, " + TextFormatting.WHITE + mc.player.getName(), 2, 200, ColorUtils.GenRainbow());
            	} else {
            		FontUtils.drawStringWithShadow(true, "Revolution" + " v" + BadlionMod.VERSION, 2, 190, nonrainbow);
                	FontUtils.drawStringWithShadow(true, "Welcome, " + TextFormatting.WHITE + mc.player.getName(), 2, 200, nonrainbow);
            	}
                for (Module m : BadlionMod.moduleManager.getModules()) {
                    if (m.isToggled()) {
                    	if(rainbow.getValBoolean()) {
                        BadlionMod.fontRenderer.drawStringWithShadow(m.getName(), 2, currY + 210, ColorUtils.GenRainbow());
                        
                        currY += mc.fontRenderer.FONT_HEIGHT;
                    	} else {
                    		BadlionMod.fontRenderer.drawStringWithShadow(m.getName(), 2, currY + 210, nonrainbow);
                    		currY += mc.fontRenderer.FONT_HEIGHT;
                    	}
                    }
                }
            }
        }
    }
	
}
