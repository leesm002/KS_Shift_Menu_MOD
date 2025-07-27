package org.King_Shack.ks_shift_menu_mod.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChannelSwitchPayload(String targetChannel) implements CustomPayload {

    public static final CustomPayload.Id<ChannelSwitchPayload> ID =
            new CustomPayload.Id<>(Identifier.of("ks_shift_menu_mod", "channel_switch"));

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

    public static void registerCodec() {
        PayloadTypeRegistry.play().register(ID, ChannelSwitchPayload::read);
    }
}
