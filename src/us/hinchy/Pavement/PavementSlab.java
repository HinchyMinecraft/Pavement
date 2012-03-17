package us.hinchy.Pavement;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.material.block.GenericCuboidCustomBlock;

public class PavementSlab extends GenericCuboidCustomBlock {
    public PavementSlab(Plugin plugin, Texture texture) {
        super(plugin, "Pavement Slab", 44, new GenericCuboidBlockDesign(plugin, texture, new int[]{0,1,1,1,1,0}, 0, 0, 0, 1, (float) 0.5, 1));
        this.setBlockDesign(this.getBlockDesign().setBoundingBox(0,0,0,1,(float)0.5,1));
    }
}