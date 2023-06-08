package me.frankv.bettersafebed;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

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
