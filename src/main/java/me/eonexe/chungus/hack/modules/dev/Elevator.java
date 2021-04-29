package me.eonexe.chungus.hack.modules.dev;

import me.eonexe.chungus.hack.modules.WurstplusCategory;
import me.eonexe.chungus.hack.modules.WurstplusHack;

public class Elevator extends WurstplusHack {
	public Elevator() {
    	super(WurstplusCategory.WURSTPLUS_BETA);

		this.name        = "Elevator";
		this.tag         = "Elevator";
		this.description = "no";
    }
	
	
	
	@Override
	public void enable() {
	
	EntityPlayer closest_target = find_closest_target();
	
	}

}
