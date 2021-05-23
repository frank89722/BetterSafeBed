package frankv.bettersafebed.mixins;

import java.util.List;
import java.util.Optional;

import frankv.bettersafebed.MixinCallbacks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
	private ServerPlayerEntityMixin(World world, BlockPos pos, float f, GameProfile profile) {
		super(world, pos, f, profile);
	}

	@Inject(method="trySleep", locals=LocalCapture.CAPTURE_FAILEXCEPTION, at = @At(value="INVOKE_ASSIGN", target="net/minecraft/world/World.getEntitiesByClass (Ljava/lang/Class;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;)Ljava/util/List;", ordinal=0))
	public void onTrySleep(BlockPos at, CallbackInfoReturnable<Either> cir,  double d0, double d1, Vec3d vector3d, List list) {
		MixinCallbacks.onTrySleep(list, (PlayerEntity)this.getVehicle());
	}

	@ModifyVariable(method = "trySleep", at = @At("STORE"), ordinal = 0)
	private List<HostileEntity> injected(List<HostileEntity> list) {
		return MixinCallbacks.getList();
	}
}