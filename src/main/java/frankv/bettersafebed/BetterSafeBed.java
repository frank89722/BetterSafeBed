package frankv.bettersafebed;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fmllegacy.network.FMLNetworkConstants;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(BetterSafeBed.MODID)
public class BetterSafeBed {
	public static final String MODID = "bettersafebed";

	public BetterSafeBed() {
		ModLoadingContext.get()
				.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
	}
}