package frankv.bettersafebed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;

public class MixinCallbacks {
	private static List list;
	private static final List<MonsterEntity> empty = Collections.emptyList();

	public static void onTrySleep(List list, PlayerEntity player) {
		if (list != null) {
			MixinCallbacks.list = list;
			if(shouldSleep(player)) list.clear();
		}
	}

	private static boolean shouldSleep(PlayerEntity player) {
		if (!list.isEmpty()) {
			for (Object object : list) {
				if (object instanceof MonsterEntity) {
					if ((((MonsterEntity) object).getTarget() instanceof PlayerEntity)) {
						if(((MonsterEntity) object).getTarget().getEntity() == player) return false;
					}
				}
			}
		}
		return true;
	}

	public static List<MonsterEntity> getList() {
		if (list == null) return empty;
		return list;
	}
}
