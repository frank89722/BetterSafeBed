package me.frankv.bettersafebed;


import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.NetworkConstants;

@Mod(BetterSafeBed.MODID)
public class BetterSafeBed {
    public static final String MODID = "bettersafebed";

    public BetterSafeBed() {
        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(
                        () -> NetworkConstants.IGNORESERVERONLY,
                        (remote, isServer) -> true
                )
        );
    }
}
