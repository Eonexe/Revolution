package me.eonexe.chungus.hack.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import me.eonexe.chungus.hack.modules.WurstplusClickGUI;

public class Wrapper {
    private static FontRenderer fontRenderer;

    public static void init() {
        fontRenderer = WurstplusClickGUI.mc.fontRenderer;
    }

    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }

    public static World getWorld() {
        return getMinecraft().world;
    }

    public static int getKey(String keyname) {
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }

    public static FontRenderer getFontRenderer() {
        return fontRenderer;
    }
}