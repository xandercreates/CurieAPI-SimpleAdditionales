package net.timeworndevs.quantumadds.item.GeigerCounter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.timeworndevs.quantumadds.Quantum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class GeigerCounter extends Item {

    public GeigerCounter(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        if ((ctx.getHand().equals(Hand.MAIN_HAND) && !ctx.getPlayer().getOffHandStack().isEmpty()) || (ctx.getHand().equals(Hand.OFF_HAND) && !ctx.getPlayer().getMainHandStack().isEmpty())) {
          int radiationFromItems = 0;
          if (Quantum.radiation_data!=null) {
              for (String key : Quantum.radiation_data.keySet()) {
                  JsonObject curr = Quantum.radiation_data.get(key);
                  if (curr.has("items")) {
                      for (JsonElement element : curr.get("items").getAsJsonArray()) {
                          if (!Objects.equals(Registries.ITEM.get(new Identifier(element.getAsJsonObject().get("object").getAsString())).toString(), "minecraft:air")) {
                              Item item;
                              if (ctx.getHand().equals(Hand.MAIN_HAND)) {
                                  item = ctx.getPlayer().getInventory().offHand.get(0).getItem();
                              } else {
                                  item = ctx.getPlayer().getInventory().getMainHandStack().getItem();
                              }
                              if (Registries.ITEM.get(new Identifier(element.getAsJsonObject().get("object").getAsString())) == item) {
                                  for (String kind : Quantum.new_radiation_types.keySet()) {
                                      if (element.getAsJsonObject().has(kind)) {
                                          radiationFromItems += element.getAsJsonObject().get(kind).getAsInt() * item.getDefaultStack().getCount();
                                      }
                                  }
                              }
                          }
                      }
                  }
              }
          }
          switch (ctx.getHand()) {
              case OFF_HAND ->
                      ctx.getPlayer().sendMessage(Text.literal("Main Hand item radiation: " + radiationFromItems + "Ci"), true);
              case MAIN_HAND ->
                      ctx.getPlayer().sendMessage(Text.literal("Offhand item radiation: " + radiationFromItems + "Ci"), true);
          }
        } else {

            int radiationAround = 0;
            float biomeMultiplier = 0;
            String biome = ctx.getWorld().getBiome(ctx.getPlayer().getBlockPos()).getKey().toString().replace("Optional[ResourceKey[minecraft:worldgen/biome / ", "").replace("]]", ""); // I'm the worst dev hello there for doing that
            if (Quantum.radiation_data != null) {
                for (String key : Quantum.radiation_data.keySet()) {
                    JsonObject curr = Quantum.radiation_data.get(key);
                    if (curr.has("biomes")) {
                        for (JsonElement element : curr.get("biomes").getAsJsonArray()) {
                            if (Objects.equals(biome, element.getAsJsonObject().get("object").getAsString())) {
                                for (String kind : Quantum.new_radiation_types.keySet()) {
                                    if (element.getAsJsonObject().has(kind)) {
                                        biomeMultiplier += element.getAsJsonObject().get(kind).getAsInt();
                                    }
                                }
                            }
                            //loop trough jsons and check block, correct radiation level and radiation type... instead of blindly hard coding that

                        }
                    }
                    biomeMultiplier = biomeMultiplier * (ctx.getWorld().getLightLevel(LightType.SKY, ctx.getPlayer().getBlockPos()) / (float) 15);

                    for (String kind : Quantum.new_radiation_types.keySet()) {
                        radiationAround += calculateBlockRadiation(ctx.getPlayer(), kind);
                    }
                }
            }
            ctx.getPlayer().sendMessage(Text.literal("Radiation around: " + (radiationAround + biomeMultiplier) + "Ci"), true);
        }
        ctx.getStack().damage(1, ctx.getPlayer(), playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }










    private static BlockHitResult raycastInsulator(RaycastContext context, Predicate<BlockState> statePredicate, BlockPos ignored, PlayerEntity player) {
        return BlockView.raycast(context.getStart(), context.getEnd(), context, (innerContext, pos) -> {
            if (pos.equals(ignored)) {
                return null;
            }
            Vec3d vec3d = innerContext.getStart();
            Vec3d vec3d2 = innerContext.getEnd();
            BlockState blockState = player.getWorld().getBlockState((BlockPos)pos);
            if (! statePredicate.test(blockState)) {
                return null;
            }
            VoxelShape voxelShape = innerContext.getBlockShape(blockState, player.getWorld(), (BlockPos)pos);
            return player.getWorld().raycastBlock(vec3d, vec3d2, (BlockPos)pos, voxelShape, blockState);

        }, innerContext -> {
            Vec3d vec3d = innerContext.getStart().subtract(innerContext.getEnd());
            return BlockHitResult.createMissed(innerContext.getEnd(), Direction.getFacing(vec3d.x, vec3d.y, vec3d.z), BlockPos.ofFloored(innerContext.getEnd()));
        });
    }

    private static int calculateInsulators(PlayerEntity player, String kind, BlockPos blockPos) {
        int total_insulation = 0;
        Vec3d start, end;

        start = player.getPos().add(0.0, 0.1, 0.0);
        end = (blockPos).toCenterPos();

        //Find list of insulators from configs and put into hashmap for easier use
        HashMap<String, Integer> insulators = new HashMap<String, Integer>();
        if (Quantum.radiation_data!=null) {
            for (String key: Quantum.radiation_data.keySet()) {
                JsonObject curr = Quantum.radiation_data.get(key);
                if (curr.has("insulators")) {
                    for (JsonElement element : curr.get("insulators").getAsJsonArray()) {
                        if (element.getAsJsonObject().has(kind)) {
                            insulators.put(element.getAsJsonObject().get("object").getAsString(), element.getAsJsonObject().get(kind).getAsInt());
                        }
                    }
                }
            }
        }


        boolean reachedEnd = false;
        BlockPos lastBlockPos = null;
        while (!reachedEnd) {
            BlockHitResult result = raycastInsulator(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player), (blockState) -> {
                Integer insulatorValue = insulators.get(Registries.BLOCK.getId(blockState.getBlock()).toString());
                return insulatorValue != null;
            }, lastBlockPos, player);

            lastBlockPos = result.getBlockPos();
            if (lastBlockPos.equals(blockPos)) {
                reachedEnd = true;
            } else {
                Integer insulatorValue = insulators.get(Registries.BLOCK.getId(player.getWorld().getBlockState(lastBlockPos).getBlock()).toString());
                total_insulation += insulatorValue != null ? insulatorValue : 0;
                start = result.getPos();
            }
        }

        return total_insulation;
    }

    public static int calculateBlockRadiation(PlayerEntity player, String kind) {
        int radiation = 0;
        if (Quantum.radiation_data!=null) {
            for (String key : Quantum.radiation_data.keySet()) {
                JsonObject curr = Quantum.radiation_data.get(key);

                if (curr.has("blocks")) {
                    for (JsonElement element : curr.get("blocks").getAsJsonArray()) {
                        if (!Objects.equals(Registries.BLOCK.get(new Identifier ( element.getAsJsonObject().get("object").getAsString())).toString(), "minecraft:air")) {
                            if (element.getAsJsonObject().has(kind)) {
                                Stream<BlockPos> blockPosStream = BlockPos.stream(player.getBoundingBox().expand(5))
                                        .filter(blockPos -> player.getWorld().getBlockState(blockPos).isOf(Registries.BLOCK.get(new Identifier(element.getAsJsonObject().get("object").getAsString()))));
                                radiation += blockPosStream.mapToInt((blockPos) -> {
                                            int insulatorValue = calculateInsulators(player, kind, blockPos);
                                            return Math.max(0, element.getAsJsonObject().get(kind).getAsInt()-insulatorValue);
                                        })
                                        .reduce(0, Integer::sum);
                            }
                        }
                    }
                }
            }
        }
        return radiation;
    }
}
