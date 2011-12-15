package us.hinchy.Pavement;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class PavementBlock extends GenericCubeCustomBlock {
    public PavementBlock(Plugin plugin) {
        super(plugin, "Pavement", new GenericCubeBlockDesign(plugin, "http://hinchy.us/pavement.png", 16));
    }
}