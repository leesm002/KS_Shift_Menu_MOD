package org.King_Shack.ks_shift_menu_mod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.King_Shack.ks_shift_menu_mod.network.ChannelSwitchPayload;

public class ChannelNetworkingClient {
    public static void send(String channelName) {
        ClientPlayNetworking.send(new ChannelSwitchPayload(channelName));
    }
}
