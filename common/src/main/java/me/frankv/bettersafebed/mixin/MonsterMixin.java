package me.frankv.bettersafebed.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Monster.class)
public class MonsterMixin {

    @Inject(method = "isPreventingPlayerRest", at = @At("HEAD"), cancellable = true)
    private void injectIsPreventingPlayerRest(ServerLevel level, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (((Monster) (Object) this).getTarget() instanceof Player target) {
            if (target.equals(player)) {
                cir.setReturnValue(true);
                return;
            }
        }
        cir.setReturnValue(false);
    }

}
