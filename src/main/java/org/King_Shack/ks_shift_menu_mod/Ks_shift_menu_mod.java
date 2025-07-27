package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.api.ModInitializer;
import org.King_Shack.ks_shift_menu_mod.network.ChannelNetworking;

public class Ks_shift_menu_mod implements ModInitializer {

    @Override
    public void onInitialize() {
        ChannelNetworking.registerServer(); // 네트워크 수신 등록
    }

}
