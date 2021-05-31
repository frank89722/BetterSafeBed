package frankv.bettersafebed.mixins;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

	@ModifyVariable(method = "trySleep", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getEntitiesByClass(Ljava/lang/Class;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;)Ljava/util/List;"))
	private List<HostileEntity> additionalCheck(List<HostileEntity> list) {
		list.removeIf(m -> !(m.getTarget() instanceof PlayerEntity)
						 ||  m.getTarget().getVehicle() != ((PlayerEntity)(Object)this).getVehicle());
		return list;
	}

}