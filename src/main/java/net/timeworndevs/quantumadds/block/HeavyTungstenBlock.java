package net.timeworndevs.quantumadds.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class HeavyTungstenBlock extends FallingBlock {
    public HeavyTungstenBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        /*if (world.getBlockState(pos.offset(Direction.Axis.Y, -1)).getBlock().equals(ModBlocks.PLUTONIUM_CORE)) {
            ((PlutoniumBlock) world.getBlockState(pos.offset(Direction.Axis.Y, -1)).getBlock()).goSupercritical(world, pos.offset(Direction.Axis.Y, -1), 40);
        }*/
    }
}
/*
for (int i=0; i<100; i++) {
        ParticleUtil.spawnParticle(world, pos, Random.create(), new ParticleEffect() {
    @Override
    public ParticleType<?> getType() {
        return ParticleTypes.SOUL_FIRE_FLAME;
    }

    @Override
    public void write(PacketByteBuf buf) {

    }

    @Override
    public String asString() {
        return null;
    }
});
        }*/