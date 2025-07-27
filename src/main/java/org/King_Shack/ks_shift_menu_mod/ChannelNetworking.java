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

public class ChannelNetworking {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ChannelSwitchPayload.ID, (server, player, handler, buf, responseSender) -> {
            ChannelSwitchPayload payload = ChannelSwitchPayload.read(buf);
            String target = payload.targetChannel();

            server.execute(() -> {
                RegistryKey<World> worldKey = switch (target) {
                    case "spawn" -> World.OVERWORLD;
                    case "village" -> RegistryKey.of(RegistryKeys.WORLD, new Identifier("ks_shift_menu_mod", "village"));
                    case "wild" -> RegistryKey.of(RegistryKeys.WORLD, new Identifier("ks_shift_menu_mod", "wild"));
                    default -> null;
                };

                if (worldKey == null) return;
                ServerWorld targetWorld = server.getWorld(worldKey);
                if (targetWorld != null) {
                    Vec3d pos = Vec3d.ofBottomCenter(targetWorld.getSpawnPos());
                    player.teleport(targetWorld, pos.getX(), pos.getY(), pos.getZ(), player.getYaw(), player.getPitch());
                }
            });
        });
    }
}