package frankv.bettersafebed.mixins;

import java.util.List;

import net.minecraft.entity.monster.MonsterEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
	@ModifyVariable(method = "startSleepInBed", at = @At(value="INVOKE_ASSIGN", target="net/minecraft/world/World.getEntitiesOfClass (Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/List;"))
	private List additionalCheck(List<MonsterEntity> list) {
		list.removeIf(m -> !(m.getTarget() instanceof PlayerEntity)
				||  m.getTarget().getVehicle() != ((PlayerEntity)(Object)this).getVehicle());
		return list;
	}
}
