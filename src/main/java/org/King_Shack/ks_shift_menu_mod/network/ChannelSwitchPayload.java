package org.King_Shack.ks_shift_menu_mod.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChannelSwitchPayload(String targetChannel) {
    // ChannelSwitchPayload.java 안에서
    public static final CustomPayload.Id<ChannelSwitchPayload> ID =
            new CustomPayload.Id<>(new Identifier("ks_shift_menu_mod", "channel_switch"));



    public static ChannelSwitchPayload read(PacketByteBuf buf) {
        return new ChannelSwitchPayload(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        buf.writeString(targetChannel);
    }
}