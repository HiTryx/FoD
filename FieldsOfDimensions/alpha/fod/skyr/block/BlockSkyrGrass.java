package alpha.fod.skyr.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import alpha.fod.skyr.Skyr;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSkyrGrass extends Block
{
    public BlockSkyrGrass(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
    }
	
    @SideOnly(Side.CLIENT)
	int top = RenderingRegistry.addTextureOverride("/terrain.png", "/FoD/Skyr/Blocks/grass_top.png");
    @SideOnly(Side.CLIENT)
	int side = RenderingRegistry.addTextureOverride("/terrain.png", "/FoD/Skyr/Blocks/grass_side.png");
	
    @SideOnly(Side.CLIENT)
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? top : (par1 == 0 ? 2 : side);
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
            {
                par1World.setBlockWithNotify(par2, par3, par4, Block.dirt.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = par2 + par5Random.nextInt(3) - 1;
                    int var8 = par3 + par5Random.nextInt(5) - 3;
                    int var9 = par4 + par5Random.nextInt(3) - 1;
                    int var10 = par1World.getBlockId(var7, var8 + 1, var9);

                    if (par1World.getBlockId(var7, var8, var9) == Block.dirt.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && par1World.getBlockLightOpacity(var7, var8 + 1, var9) <= 2)
                    {
                        par1World.setBlockWithNotify(var7, var8, var9, Skyr.SkyrGrass.blockID);
                    }
                }
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }
}
