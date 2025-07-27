package org.King_Shack.ks_shift_menu_mod.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ChannelNetworking {
    public static final Identifier CHANNEL_SWITCH = new Identifier("ks_shift_menu_mod", "channel_switch");

    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(CHANNEL_SWITCH, (server, player, handler, buf, responseSender) -> {
            String target = buf.readString();

            server.execute(() -> {
                RegistryKey<World> worldKey;
                switch (target) {
                    case "spawn" -> worldKey = World.OVERWORLD;
                    case "village" -> worldKey = RegistryKey.of(Registry.WORLD_KEY, new Identifier("ks_shift_menu_mod", "village"));
                    case "wild" -> worldKey = RegistryKey.of(Registry.WORLD_KEY, new Identifier("ks_shift_menu_mod", "wild"));
                    default -> {
                        System.out.println("[KS_Shift_Menu_MOD] Unknown channel: " + target);
                        return;
                    }
                }

                ServerWorld targetWorld = server.getWorld(worldKey);
                if (targetWorld != null) {
                    player.teleport(targetWorld,
                            targetWorld.getSpawnPos().getX() + 0.5,
                            targetWorld.getSpawnPos().getY(),
                            targetWorld.getSpawnPos().getZ() + 0.5,
                            player.getYaw(),
                            player.getPitch());
                } else {
                    System.out.println("[KS_Shift_Menu_MOD] Target world not found: " + worldKey.getValue());
                }
            });
        });
    }
}