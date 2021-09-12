package frankv.bettersafebed.mixins;

import java.util.List;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerPlayer.class)
public class ServerPlayerEntityMixin {
	@ModifyVariable(method = "startSleepInBed", at = @At(value="INVOKE_ASSIGN", target="Lnet/minecraft/world/level/Level;getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;"))
	private List additionalCheck(List<Monster> list) {
		list.removeIf(m -> !(m.getTarget() instanceof Player)
				||  m.getTarget().getVehicle() != ((Player)(Object)this).getVehicle());
		return list;
	}
}
