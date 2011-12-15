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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;

public class Pavement extends JavaPlugin {

	public static CustomBlock pavementBlock;
	
	Logger log = Logger.getLogger("Minecraft");

	private final PavementPlayerListener pPlayerListener = new PavementPlayerListener(this);
	
	public Texture pavementTexture = new Texture(this, "http://hinchy.us/pavement.png", 64, 16, 16);
	
	public void onEnable() { 
		pavementBlock = new PavementBlock(this);

        Bukkit.getServer().addRecipe(new FurnaceRecipe(new SpoutItemStack(pavementBlock, 1), Material.GRAVEL));
		
		log.info("Pavement alpha 1 by Zach Hinchy (http://hinchy.us/) enabled.");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, pPlayerListener, Event.Priority.Normal, this);
	}
	 
	public void onDisable() { 
		log.info("Pavement has been disabled.");
	}
 
    @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName().toLowerCase(); // Only Players
 
        if (commandName.equals("/ps")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("/" + commandName + " can only be run from in game.");
                return true;
            } else {
	            Player player = (Player) sender;
	            player.getInventory().addItem(new SpoutItemStack(pavementBlock, 64));
	            return true;
            }
        }
        return false;
    }
	
}
