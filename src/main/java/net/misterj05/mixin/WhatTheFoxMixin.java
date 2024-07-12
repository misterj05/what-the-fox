package net.misterj05.mixin;

import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoxEntity.JumpChasingGoal.class)
public abstract class WhatTheFoxMixin {

	@Inject(at = @At("HEAD"), method = "shouldContinue", cancellable = true)
	private void shouldContinue(CallbackInfoReturnable<Boolean> cir) {

	}
}