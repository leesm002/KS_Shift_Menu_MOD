package org.King_Shack.ks_shift_menu_mod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.King_Shack.ks_shift_menu_mod.network.ChannelSwitchPayload;

public class ChannelNetworkingClient {
    public static void send(String channelName) {
        ClientPlayNetworking.send(new ChannelSwitchPayload(channelName));
    }

    public static void sendChannelSwitch(String targetChannel) {
        ClientPlayNetworking.send(new ChannelSwitchPayload(targetChannel));
    }
}
