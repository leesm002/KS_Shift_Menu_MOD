package org.King_Shack.ks_shift_menu_mod.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.text.Text;
import org.King_Shack.ks_shift_menu_mod.ChannelNetworking;
import org.King_Shack.ks_shift_menu_mod.client.ChannelNetworkingClient;

public class ChannelScreen extends Screen {
    public ChannelScreen() {
        super(Text.literal("채널 선택"));
    }

    @Override
    protected void init() {
        this.addDrawableChild(
                new ButtonWidget.Builder(Text.literal("스폰 서버"), button -> {
                    ChannelNetworkingClient.sendChannelSwitch("spawn");
                }).position(this.width / 2 - 75, this.height / 2 - 30).size(150, 20).build()
        );

        this.addDrawableChild(
                new ButtonWidget.Builder(Text.literal("마을 서버"), button -> {
                    ChannelNetworkingClient.sendChannelSwitch("village");
                }).position(this.width / 2 - 75, this.height / 2).size(150, 20).build()
        );

        this.addDrawableChild(
                new ButtonWidget.Builder(Text.literal("야생 서버"), button -> {
                    ChannelNetworkingClient.sendChannelSwitch("wild");
                }).position(this.width / 2 - 75, this.height / 2 + 30).size(150, 20).build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }


    @Override
    public boolean shouldPause() {
        return false;
    }
}
