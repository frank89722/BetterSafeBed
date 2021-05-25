package frankv.bettersafebed.mixins;

import java.util.List;
import java.util.Optional;

import frankv.bettersafebed.MixinCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
	private ServerPlayerEntityMixin(World world, BlockPos pos, float f, GameProfile profile) {
		super(world, pos, f, profile);
	}

	@Inject(method="startSleepInBed", locals=LocalCapture.CAPTURE_FAILEXCEPTION, at = @At(value="INVOKE_ASSIGN", target="net/minecraft/world/World.getEntitiesOfClass (Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/List;", ordinal=0))
	private void onTrySleep(BlockPos at, CallbackInfoReturnable<Either> cir, Optional optAt, SleepResult ret, Direction direction, double d0, double d1, Vector3d vector3d, List list) {
		MixinCallbacks.onTrySleep(list, (PlayerEntity) this.getEntity());
	}

	@ModifyVariable(method = "startSleepInBed", at = @At(value="INVOKE_ASSIGN", target="net/minecraft/world/World.getEntitiesOfClass (Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/List;", ordinal = 0))
	private List injected(List list) {
		return MixinCallbacks.getList();
	}
}
