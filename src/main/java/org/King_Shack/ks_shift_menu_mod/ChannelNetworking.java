package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class ChannelNetworking {
    public static final Identifier CHANNEL_SWITCH = new Identifier("ks_shift_menu_mod", "channel_switch");

    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(CHANNEL_SWITCH, (server, player, handler, buf, responseSender) -> {
            // ✅ readString()으로 되돌림
            String target = buf.readString();

            // ✅ 서버 전용 실행 큐 사용
            server.execute(() -> {
                RegistryKey<World> worldKey;

                // ✅ Identifier.of() → new Identifier(...)로 되돌림
                switch (target) {
                    case "spawn" -> worldKey = World.OVERWORLD;
                    case "village" -> worldKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier("ks_shift_menu_mod", "village"));
                    case "wild" -> worldKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier("ks_shift_menu_mod", "wild"));
                    default -> {
                        System.out.println("[KS_Shift_Menu_MOD] Unknown channel: " + target);
                        return;
                    }
                }

                ServerWorld targetWorld = server.getWorld(worldKey);

                if (targetWorld != null) {
                    Vec3d spawn = Vec3d.ofBottomCenter(targetWorld.getSpawnPos());

                    // ✅ teleport() → requestTeleport() 또는 위치 직접 지정
                    player.teleport(targetWorld,
                            spawn.getX(), spawn.getY(), spawn.getZ(),
                            player.getYaw(), player.getPitch());
                } else {
                    System.out.println("[KS_Shift_Menu_MOD] World not found: " + worldKey.getValue());
                }
            });
        });
    }
}
