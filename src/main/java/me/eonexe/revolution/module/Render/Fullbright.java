package me.eonexe.revolution.module.Render;

import me.eonexe.revolution.module.Category;
import me.eonexe.revolution.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Objects;

public class Fullbright extends Module {
	private boolean hasEffect = false;
	private final PotionEffect effect = new PotionEffect(Objects.requireNonNull(Potion.getPotionById(16)));

	public Fullbright() {
		super("Fullbright", Category.Render);

		effect.setPotionDurationMax(true);
		this.tickDelay = 1000;
	}

	@Override
	public void onEnable() {
		if (mc.player == null) return;

		mc.player.addPotionEffect(effect);
		hasEffect = true;
	}

	@Override
	public void onUpdate() {
		if (mc.player == null) return;

		if (!hasEffect) {
			mc.player.addPotionEffect(effect);
			hasEffect = true;
		}
	}

	@Override
	public void onDisable() {
		mc.player.removeActivePotionEffect(effect.getPotion());
	}
}
