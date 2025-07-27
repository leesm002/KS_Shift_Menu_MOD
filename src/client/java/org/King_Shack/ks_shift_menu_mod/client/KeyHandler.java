package org.King_Shack.ks_shift_menu_mod.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.lwjgl.glfw.GLFW;

public class KeyHandler {
    public static KeyBinding openGuiKey;

    public static void register() {
        openGuiKey = new KeyBinding(
                "key.ks_shift_menu_mod.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F,
                "category.ks_shift_menu_mod"
        );
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.wasPressed() && InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                MinecraftClient.getInstance().setScreen(new ChannelScreen());
            }
        });
    }
}
