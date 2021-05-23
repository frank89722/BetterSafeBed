package frankv.bettersafebed;

import java.util.Collections;
import java.util.List;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;


public class MixinCallbacks {
	private static List list;
	private static final List<HostileEntity> empty = Collections.emptyList();

	public static void onTrySleep(List list, PlayerEntity player) {
		if (list != null) {
			MixinCallbacks.list = list;
			if(shouldSleep(player)) list.clear();
		}
	}

	private static boolean shouldSleep(PlayerEntity player) {
		if (!list.isEmpty()) {
			for (Object object : list) {
				if (object instanceof HostileEntity) {
					if ((((HostileEntity) object).getTarget() instanceof PlayerEntity)) {
						if(((HostileEntity) object).getTarget().getVehicle() == player) return false;
					}
				}
			}
		}
		return true;
	}

	public static List<HostileEntity> getList() {
		if (list == null) return empty;
		return list;
	}
}
