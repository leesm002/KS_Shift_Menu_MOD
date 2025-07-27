// 수정된 버전: Minecraft 1.21.8 + Fabric API 최신 구조 대응
// 기능: Shift+F 키를 눌렀을 때 GUI 메뉴 표시

package org.king_shack.ks_shift_menu_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.King_Shack.ks_shift_menu_mod.client.ChannelScreen;
import org.lwjgl.glfw.GLFW;

public class KS_Shift_Menu_MOD implements ModInitializer, ClientModInitializer {

    public static final Identifier CHANNEL_SWITCH_ID = Identifier.of("ks_shift_menu_mod:channel_switch");
    private static KeyBinding openGuiKeyBinding;

    @Override
    public void onInitialize() {
        // 서버 관련 초기화 필요시 추가
    }

    @Override
    public void onInitializeClient() {
        openGuiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ks_shift_menu_mod.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F,
                "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKeyBinding.wasPressed() && client.player != null && client.player.isSneaking()) {
                client.execute(() -> client.setScreen(new ChannelScreen()));
            }
        });
    }
}
