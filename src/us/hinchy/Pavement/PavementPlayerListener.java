package us.hinchy.Pavement;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PavementPlayerListener extends PlayerListener {
	
	Logger log = Logger.getLogger("Minecraft");
	public Pavement plugin;
	
    public PavementPlayerListener(Pavement instance) {
	    plugin = instance;
	}

	@Override public void onPlayerMove(PlayerMoveEvent event) {
		SpoutPlayer sp = (SpoutPlayer) event.getPlayer();
		
        Block below = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY() - 1, event.getPlayer().getLocation().getBlockZ());
        SpoutBlock sb = (SpoutBlock) below;
    	
    	if (sb.getCustomBlock() != null) {
    		if (sb.getCustomBlock() instanceof PavementBlock) {
    			sp.setAirSpeedMultiplier(2);
    			sp.setWalkingMultiplier(2);
    			sp.setJumpingMultiplier(1.25);
    		} else {
    			sp.setAirSpeedMultiplier(1);
    			sp.setWalkingMultiplier(1);
    			sp.setJumpingMultiplier(1);
    		}
    	} else {
    		if (below.getType() != Material.AIR) {
    			sp.setAirSpeedMultiplier(1);
    			sp.setWalkingMultiplier(1);
    			sp.setJumpingMultiplier(1);
    		}
    	}
    }
}