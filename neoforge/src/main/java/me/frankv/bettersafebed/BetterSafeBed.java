package me.frankv.bettersafebed;


import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

@Mod(BetterSafeBed.MODID)
public class BetterSafeBed {
    public static final String MODID = "bettersafebed";

    public BetterSafeBed() {
        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(
                        () -> IExtensionPoint.DisplayTest.IGNORESERVERONLY,
                        (remote, isServer) -> true
                )
        );
    }
}
