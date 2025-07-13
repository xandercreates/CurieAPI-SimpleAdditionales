package net.timeworndevs.quantumadds.item.GeigerCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.timeworndevs.quantum.Quantum;
import net.timeworndevs.quantum.radiation.RadiationType;
import net.timeworndevs.quantum.util.QuantumConfig;
import net.timeworndevs.quantum.util.RadiationCalculator;

import static net.timeworndevs.quantum.radiation.RadiationType.RADIATION_TYPES;

public class GeigerCounter extends Item {

    public GeigerCounter(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        World world = ctx.getWorld();
        PlayerEntity player = ctx.getPlayer();
        Hand hand = ctx.getHand();
        if (player == null) { return ActionResult.PASS; }

        if ((hand.equals(Hand.MAIN_HAND) && !player.getOffHandStack().isEmpty()) || (hand.equals(Hand.OFF_HAND) && !player.getMainHandStack().isEmpty())) {
          int radiationFromItems = 0;
          ItemStack itemStack = hand.equals(Hand.MAIN_HAND) ? player.getOffHandStack() : player.getMainHandStack();
          if (!itemStack.isEmpty()) {
              Item item = itemStack.getItem();

              if (QuantumConfig.ITEM_RADIATION_VALUES.containsKey(item)) {

                  radiationFromItems = QuantumConfig.ITEM_RADIATION_VALUES
                          .get(item)
                          .values()
                          .stream()
                          .mapToInt(Integer::intValue)
                          .sum();
              }
          }


          switch (ctx.getHand()) {
              case OFF_HAND ->
                      player.sendMessage(Text.literal("Main Hand item radiation: " + radiationFromItems + "Ci"), true);
              case MAIN_HAND ->
                      player.sendMessage(Text.literal("Offhand item radiation: " + radiationFromItems + "Ci"), true);
          }
        } else {

            int radiationAround = 0;
            float biomeMultiplier = 0;
            if (!world.isClient) {
                for (RadiationType type : RADIATION_TYPES.values()) {

                    radiationAround += RadiationCalculator.calculateBlockRadiation((ServerPlayerEntity) player, type);
                    biomeMultiplier = RadiationCalculator.calculateBiomeRadiation((ServerWorld) world, (ServerPlayerEntity) player);
                    Quantum.LOGGER.info("Radiation Around: " + radiationAround);
                    Quantum.LOGGER.info("Biome Multiplier: " + biomeMultiplier);
                }
                ctx.getStack().getOrCreateNbt().putFloat("quantum:radiation_detected", radiationAround + biomeMultiplier);
                player.sendMessage(Text.literal("Radiation around: " + (radiationAround + biomeMultiplier) + "Ci"), true);
            }
        }
        ctx.getStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }




}
