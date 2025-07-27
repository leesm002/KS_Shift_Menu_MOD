package org.King_Shack.ks_shift_menu_mod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Ks_shift_menu_modClient implements ClientModInitializer {
    private static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        openGuiKey = new KeyBinding("key.ks_shift_menu_mod.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F, "category.ks_shift_menu_mod");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.wasPressed() && InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                MinecraftClient.getInstance().setScreen(new ChannelScreen());
            }
        });
    }
}
