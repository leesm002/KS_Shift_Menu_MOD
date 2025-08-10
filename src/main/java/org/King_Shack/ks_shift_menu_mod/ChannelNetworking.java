package org.King_Shack.ks_shift_menu_mod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import org.King_Shack.ks_shift_menu_mod.network.ChannelSwitchC2SPayload;

public class ChannelNetworking {

    /** 네트워크 초기화: 반드시 onInitialize에서 호출 */
    public static void register() {
        // 1) 페이로드 타입/코덱을 먼저 등록
        ChannelSwitchC2SPayload.registerType();

        // 2) 서버 글로벌 수신기 등록 (클라 → 서버)
        ServerPlayNetworking.registerGlobalReceiver(
                ChannelSwitchC2SPayload.ID,
                (payload, context) -> {
                    // 서버 스레드에서 안전하게 처리
                    context.server().execute(() -> {
                        ServerPlayerEntity player = context.player();
                        String target = payload.target();

                        // TODO: 여기에 "서버 이동 메뉴" 로직 구현
                        //  - 예시: 플레이어에게 피드백 메세지
                        player.sendMessage(() -> net.minecraft.text.Text.literal(
                                "[KS Shift Menu] 요청된 타겟: " + target
                        ), false);

                        //  - 예시: 나중에 채널/서버 스위칭 로직, GUI 오픈용 패킷 전송 등 연결
                    });
                }
        );
    }
}
