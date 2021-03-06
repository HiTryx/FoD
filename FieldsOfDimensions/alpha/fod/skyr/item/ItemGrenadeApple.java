
package alpha.fod.skyr.item;

import alpha.fod.skyr.SkyrRef;
import alpha.fod.skyr.entity.EntityGrenadeApple;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGrenadeApple extends Item {
	
	public ItemGrenadeApple(int par1) {
		super(par1);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}
		
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!par2World.isRemote) {
			par2World.spawnEntityInWorld(new EntityGrenadeApple(par2World));
		}
		
		return par1ItemStack;
	}
	
	public String getTextureFile(){
		return SkyrRef.item;
	}
	
}
