package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.api.ModInitializer;

public class Ks_shift_menu_mod implements ModInitializer {
    @Override
    public void onInitialize() {
        ChannelNetworking.register();
    }
}
