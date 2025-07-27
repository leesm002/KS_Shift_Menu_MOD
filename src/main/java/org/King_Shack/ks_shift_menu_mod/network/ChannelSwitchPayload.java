package org.King_Shack.ks_shift_menu_mod.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChannelSwitchPayload(String targetChannel) implements CustomPayload {

    public static final Id<ChannelSwitchPayload> ID =
            new Id<>(new Identifier("ks_shift_menu_mod", "channel_switch"));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static ChannelSwitchPayload read(PacketByteBuf buf) {
        return new ChannelSwitchPayload(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        buf.writeString(targetChannel);
    }

}
