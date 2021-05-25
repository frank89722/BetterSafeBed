package frankv.bettersafebed;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;


public class MixinCallbacks {
	private static List list;
	private static final List empty = Collections.emptyList();

	public static void onTrySleep(List list, PlayerEntity player) {
		if (list != null) {
			MixinCallbacks.list = list;
			checkList(player);
		}
	}

	private static void checkList(PlayerEntity player) {
		if (!list.isEmpty()) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (!(o instanceof HostileEntity)) {
					it.remove();
					continue;
				}
				HostileEntity m = (HostileEntity) o;
				if (!(m.getTarget() instanceof PlayerEntity)) {
					it.remove();
					continue;
				}
				if(m.getTarget().getVehicle() != player) it.remove();
			}
		}
	}

	public static List getList() {
		if (list == null) return empty;
		return list;
	}
}
