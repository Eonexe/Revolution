package me.eonexe.chungus.hack.event.events;

import me.eonexe.chungus.hack.event.WurstplusEventCancellable;

public class WurstplusEventPlayerTravel extends WurstplusEventCancellable {
    
    public float Strafe;
    public float Vertical;
    public float Forward;

    public WurstplusEventPlayerTravel(float p_Strafe, float p_Vertical, float p_Forward) {
        Strafe = p_Strafe;
        Vertical = p_Vertical;
        Forward = p_Forward;
    }

}