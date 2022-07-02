package net.fabricmc.example.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.Mouse.class)
public abstract class ExampleMixin {
	@Shadow @Final private MinecraftClient client;

	@Inject(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;setKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;Z)V"), allow = 1)
	private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (button == 1/*right click*/ && action == 1 && client.player != null && client.player.isSpectator())
            client.player.sendCommand("trigger t");
    }
}
