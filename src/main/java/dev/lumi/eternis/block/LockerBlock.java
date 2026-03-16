package dev.lumi.eternis.block;

import dev.lumi.eternis.cca.PlayerSavedInventoryComponent;
import dev.lumi.eternis.init.EternisComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class LockerBlock extends Block {
    public LockerType lockerType;

    public LockerBlock(Settings settings, LockerType lockerType) {
        super(settings);
        this.lockerType = lockerType;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        PlayerSavedInventoryComponent playerSavedInventoryComponent = (PlayerSavedInventoryComponent) EternisComponents.SAVED_INVENTORY.get(player);

        if (Objects.equals(lockerType, LockerType.SAVE)) {
            if (!playerSavedInventoryComponent.hasSavedInventory()) {
                for (int i = 0; i < player.getInventory().size(); ++i) {
                    playerSavedInventoryComponent.saveStack(player.getInventory().getStack(i));
                }

            /*TrinketsApi.getTrinketComponent(player).ifPresent((trinkets) -> {
                for(class_3545<SlotReference, class_1799> slotReferenceItemStackPair : trinkets.getAllEquipped()) {
                    playerSavedInventoryComponent.saveStack((class_1799)slotReferenceItemStackPair.method_15441());
                }

            });*/
                player.getInventory().clear();
            } else {
                player.sendMessage(Text.translatable("block.eternis.locker_save.full").formatted(Formatting.DARK_RED), true);
            }
        }
        if (Objects.equals(lockerType, LockerType.RETRIEVE)) {
            if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                if (playerSavedInventoryComponent.hasSavedInventory()) {
                    SimpleInventory savedInventory = playerSavedInventoryComponent.getSavedInventory();

                    for (ItemStack stack : savedInventory.heldStacks) {
                        serverPlayerEntity.dropStack(stack.copy());
                    }

                    playerSavedInventoryComponent.resetSavedInventory();
                } else {
                    player.sendMessage(Text.translatable("block.eternis.locker_retrieve.empty").formatted(Formatting.DARK_RED), true);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    public enum LockerType implements StringIdentifiable {
        SAVE("save"),
        RETRIEVE("retrieve");

        private final String name;

        LockerType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
