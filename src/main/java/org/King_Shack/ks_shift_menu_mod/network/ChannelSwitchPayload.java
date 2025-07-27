package org.King_Shack.ks_shift_menu_mod.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChannelSwitchPayload(String targetChannel) implements CustomPayload {

    public static final Id<ChannelSwitchPayload> ID =
            new Id<>(Identifier.of("ks_shift_menu_mod", "channel_switch"));

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

    // 반드시 호출되도록 보장: 서버 or 클라이언트 init에서 호출
    public static void registerCodec(PayloadTypeRegistry registry) {
        registry.register(ID, ChannelSwitchPayload::read);
    }
}
