package org.King_Shack.ks_shift_menu_mod.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChannelSwitchPayload(String targetChannel) implements CustomPayload {
    public static final Id<ChannelSwitchPayload> ID = new Id<>(Identifier.of("ks_shift_menu_mod", "channel_switch"));
    public static final PacketCodec<PacketByteBuf, ChannelSwitchPayload> CODEC =
        PacketCodecs.STRING.xmap(ChannelSwitchPayload::new, ChannelSwitchPayload::targetChannel);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
