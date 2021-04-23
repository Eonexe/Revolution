package me.eonexe.revolution.event.Events;

import me.eonexe.revolution.event.BadlionEvent;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EventPlayerClickBlock extends BadlionEvent {

	public BlockPos Location;
    public EnumFacing Facing;

    public EventPlayerClickBlock(BlockPos loc, EnumFacing face)
    {
        Location = loc;
        Facing = face;
    }
	
}
