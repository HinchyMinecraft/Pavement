package us.hinchy.Pavement;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class PavementBlock extends GenericCubeCustomBlock {
    public PavementBlock(Plugin plugin, Texture texture) {
        super(plugin, "Pavement", 1, new GenericCubeBlockDesign(plugin, texture, 0));
    }
}