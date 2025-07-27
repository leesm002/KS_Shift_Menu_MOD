package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.api.ModInitializer;
import org.King_Shack.ks_shift_menu_mod.network.ChannelSwitchPayload;

public class Ks_shift_menu_mod implements ModInitializer {
    @Override
    public void onInitialize() {
        ChannelNetworking.register();
        ChannelSwitchPayload.registerCodec();
    }

}
