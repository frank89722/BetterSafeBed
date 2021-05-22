package frankv.bettersafebed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;

public class MixinCallbacks {
	/**
	 * Called from ServerPlayerEntityMixin after nearby monsters are queried in ServerPlayerEntity::trySleep
	 * @param list The nearby monsters that would prevent the player from sleeping, if any
	 */

	private static List<MonsterEntity> list;
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
