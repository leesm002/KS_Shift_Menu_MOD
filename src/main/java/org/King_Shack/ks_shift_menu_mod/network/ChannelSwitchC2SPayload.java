package org.King_Shack.ks_shift_menu_mod.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChannelSwitchC2SPayload(String target) implements CustomPayload {
    public static final Id<ChannelSwitchC2SPayload> ID =
            new Id<>(Identifier.of("ks_shift_menu_mod", "channel_switch"));

    public static final PacketCodec<RegistryByteBuf, ChannelSwitchC2SPayload> CODEC =
            PacketCodec.tuple(PacketCodecs.STRING, ChannelSwitchC2SPayload::target, ChannelSwitchC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    /** 수신기 등록 전에 반드시 호출해서 타입/코덱을 등록한다. (서버/클라 양쪽에서 호출 권장) */
    public static void registerType() {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
    }
}
