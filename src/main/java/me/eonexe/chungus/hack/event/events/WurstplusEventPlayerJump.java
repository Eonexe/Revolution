package me.eonexe.chungus.hack.event.events;

import me.eonexe.chungus.hack.event.WurstplusEventCancellable;

public class WurstplusEventPlayerJump extends WurstplusEventCancellable {
    
    public double motion_x;
    public double motion_y;

    public WurstplusEventPlayerJump(double motion_x, double motion_y) {
        super();

        this.motion_x = motion_x;
        this.motion_y = motion_y;
    }

}