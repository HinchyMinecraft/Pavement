/*
 *  Copyright 2011 Zach Hinchy.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package us.hinchy.Pavement;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe; 
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Pavement extends JavaPlugin implements Listener {

	public static CustomBlock pavementBlock;
	public static CustomBlock pavementSlab;
	
	Logger log = Logger.getLogger("Minecraft");
	
	public static Texture pavementTexture;
	
	public void onEnable() { 
		this.getConfig();
		
		if (this.getConfig().isSet("pavement") == false) {
			this.saveDefaultConfig();
			log.info("[Pavement] Config did not exist or was invalid, default config saved.");
		}
		
		pavementTexture = new Texture(this, getConfig().getString("pavement.texture"), getConfig().getInt("pavement.size")*2, getConfig().getInt("pavement.size"), getConfig().getInt("pavement.size"));
		
		pavementBlock = new PavementBlock(this,pavementTexture);
		pavementSlab = new PavementSlab(this,pavementTexture);
		
        Bukkit.getServer().addRecipe(new FurnaceRecipe(new SpoutItemStack(pavementBlock, 1), Material.GRAVEL));
        
        SpoutShapedRecipe recipe = new SpoutShapedRecipe(new SpoutItemStack(pavementSlab, 6));
        recipe.shape("PPP");
        recipe.setIngredient('P', pavementBlock);
        SpoutManager.getMaterialManager().registerSpoutRecipe(recipe);
        
	    /*if (getServer().getPluginManager().isPluginEnabled("CustomSlabs")) {
	        SpoutFurnaceRecipes.registerSpoutRecipe(
	        	new SpoutFurnaceRecipe(new SpoutItemStack(CustomSlabs.GravelSlab, 1), new SpoutItemStack(pavementSlab, 1))
	        );
        }*/
        
        getServer().getPluginManager().registerEvents(this, this);
		
		log.info("[Pavement] Version 1.0.0 by Zach Hinchy (http://hinchy.us/) enabled.");
	}
	 
	public void onDisable() { 
		log.info("[Pavement] Pavement has been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("pavereload")) {
			this.reloadConfig();
			sender.sendMessage("Pavement configuration reloaded.");
			return true;
		}
		return false; 
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {		
		SpoutPlayer sp = (SpoutPlayer) event.getPlayer();
        
        boolean doMultiply = false;
		
        Block below = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY() - 1, event.getPlayer().getLocation().getBlockZ());
        SpoutBlock sb = (SpoutBlock) below;
    	
    	if (sb.getCustomBlock() != null) {
    		if ((sb.getCustomBlock() instanceof PavementBlock) || (sb.getCustomBlock() instanceof PavementSlab)) {
    			doMultiply = true;
    		}
    	} 
    	
    	if (!doMultiply) {
            Block at = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY(), event.getPlayer().getLocation().getBlockZ());
            SpoutBlock sb2 = (SpoutBlock) at;
            
    		if (sb2.getCustomBlock() != null) {
        		if ((sb2.getCustomBlock() instanceof PavementBlock) || (sb2.getCustomBlock() instanceof PavementSlab)) {
        			doMultiply = true;
        		}
        	}
    	}
    	
    	if (doMultiply) {
			sp.setAirSpeedMultiplier(2);
			sp.setWalkingMultiplier(2);
			sp.setJumpingMultiplier(1.25);
		} else {
			sp.setAirSpeedMultiplier(1);
			sp.setWalkingMultiplier(1);
			sp.setJumpingMultiplier(1);
		}
    }
	
}
