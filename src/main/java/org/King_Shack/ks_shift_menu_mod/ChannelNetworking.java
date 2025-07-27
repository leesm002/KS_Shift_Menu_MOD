package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.King_Shack.ks_shift_menu_mod.network.ChannelSwitchPayload;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import java.util.Set;
import java.util.EnumSet;

public class ChannelNetworking {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ChannelSwitchPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            MinecraftServer server = player.getServer();
            String target = payload.targetChannel();

            server.execute(() -> {
                RegistryKey<World> worldKey = switch (target) {
                    case "spawn" -> World.OVERWORLD;
                    case "village" -> RegistryKey.of(RegistryKeys.WORLD, Identifier.of("ks_shift_menu_mod", "village"));
                    case "wild" -> RegistryKey.of(RegistryKeys.WORLD, Identifier.of("ks_shift_menu_mod", "wild"));
                    default -> null;
                };

                if (worldKey == null) return;

                ServerWorld targetWorld = server.getWorld(worldKey);
                if (targetWorld != null) {
                    Vec3d pos = Vec3d.ofBottomCenter(targetWorld.getSpawnPos());
                    player.teleport(
                            targetWorld,
                            pos.x, pos.y, pos.z,
                            EnumSet.of(PositionFlag.X, PositionFlag.Y, PositionFlag.Z),
                            player.getYaw(),
                            player.getPitch(),
                            false
                    );
                }
            });
        });

    }

}