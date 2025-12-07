package com.lycaniteadditions.objects.blocks;

import com.lycaniteadditions.commands.util.CustomTeleporter;
import com.lycanitesmobs.core.block.BlockBase;
import com.lycanitesmobs.core.info.ModInfo;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class BlockTeleporter extends BlockBase
{
    public BlockTeleporter(Material material, ModInfo group, String name) {
        super(material, group, name);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            CustomTeleporter Teleport = null;
            CustomTeleporter.teleportToDimension(playerIn, 2, playerIn.getPosition().getX(), playerIn.getPosition().getY() + 5, playerIn.getPosition().getZ());
            return true;
        }
        return false;
    }
}