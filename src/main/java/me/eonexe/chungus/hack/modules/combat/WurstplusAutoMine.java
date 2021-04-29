package me.eonexe.chungus.hack.modules.combat;

import me.eonexe.chungus.hack.guiscreen.settings.WurstplusSetting;
import me.eonexe.chungus.hack.modules.WurstplusCategory;
import me.eonexe.chungus.hack.modules.WurstplusHack;
import me.eonexe.chungus.hack.util.WurstplusBreakUtil;
import me.eonexe.chungus.hack.util.WurstplusEntityUtil;
import me.eonexe.chungus.hack.util.WurstplusMessageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class WurstplusAutoMine extends WurstplusHack {

    public WurstplusAutoMine() {
        super(WurstplusCategory.WURSTPLUS_COMBAT);

        this.name        = "Auto Mine";
        this.tag         = "AutoMine";
        this.description = "jumpy is now never going to use the client again";
    }

    WurstplusSetting end_crystal = create("End Crystal", "MineEndCrystal", false);
    WurstplusSetting range = create("Range", "MineRange", 4, 0, 6);

    @Override
    protected void enable() {

        BlockPos target_block = null;

        for (EntityPlayer player : mc.world.playerEntities) {
            if (mc.player.getDistance(player) > range.get_value(1)) continue;

            BlockPos p = WurstplusEntityUtil.is_cityable(player, end_crystal.get_value(true));

            if (p != null) {
                target_block = p;
            }
        }

        if (target_block == null) {
            WurstplusMessageUtil.send_client_message("cannot find block");
            this.disable();
        }

        WurstplusBreakUtil.set_current_block(target_block);

    }

    @Override
    protected void disable() {
        WurstplusBreakUtil.set_current_block(null);
    }
}
