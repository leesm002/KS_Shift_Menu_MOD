package org.King_Shack.ks_shift_menu_mod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.PacketByteBufs;
import org.King_Shack.ks_shift_menu_mod.network.ChannelNetworking;

public class ChannelNetworkingClient {
    public static void sendChannelSwitch(String targetChannel) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(targetChannel);
        ClientPlayNetworking.send(ChannelNetworking.CHANNEL_SWITCH, buf);
    }
}