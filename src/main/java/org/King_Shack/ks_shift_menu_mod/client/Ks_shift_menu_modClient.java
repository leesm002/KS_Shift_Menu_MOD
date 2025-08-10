package org.King_Shack.ks_shift_menu_mod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.King_Shack.ks_shift_menu_mod.network.ChannelSwitchC2SPayload;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Ks_shift_menu_modClient implements ClientModInitializer {
    private static KeyBinding openMenu;

    @Override
    public void onInitializeClient() {
        // 클라에서도 타입 등록(권장)
        ChannelSwitchC2SPayload.registerType();

        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ks_shift_menu_mod.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F,
                "key.categories.ks_shift_menu_mod"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null || client.getWindow() == null) return;

            long handle = client.getWindow().getHandle();
            boolean shift = InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_SHIFT)
                    || InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_RIGHT_SHIFT);

            while (openMenu.wasPressed()) {
                if (shift) {
                    ClientPlayNetworking.send(new ChannelSwitchC2SPayload("spawn"));
                }
            }
        });
    }
}
