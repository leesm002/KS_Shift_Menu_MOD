package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.api.ModInitializer;

public class Ks_shift_menu_mod implements ModInitializer {
    @Override
    public void onInitialize() {
        // 네트워크 등록 (타입 등록 → 수신기 등록)
        ChannelNetworking.register();
    }
}
