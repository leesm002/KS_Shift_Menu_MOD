package org.King_Shack.ks_shift_menu_mod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.King_Shack.ks_shift_menu_mod.network.ChannelNetworkingClient;
// This class represents a screen that allows players to select a channel to switch to.

public class ChannelScreen extends Screen {
    public ChannelScreen() {
        super(Text.literal("채널 선택"));
    }

    @Override
    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 75, this.height / 2 - 30, 150, 20, Text.literal("스폰 서버"), b -> {
            ChannelNetworkingClient.sendChannelSwitch("spawn");
            this.close();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 75, this.height / 2, 150, 20, Text.literal("마을 서버"), b -> {
            ChannelNetworkingClient.sendChannelSwitch("village");
            this.close();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 75, this.height / 2 + 30, 150, 20, Text.literal("야생 서버"), b -> {
            ChannelNetworkingClient.sendChannelSwitch("wild");
            this.close();
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
