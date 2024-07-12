package net.misterj05.mixin;

import net.minecraft.entity.ai.goal.DiveJumpingGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.plaf.synth.SynthStyle;

@Mixin(FoxEntity.JumpChasingGoal.class)
public abstract class WhatTheFoxMixin {

	@Inject(at = @At("RETURN"), method = "shouldContinue", cancellable = true)
	private void shouldContinue(CallbackInfoReturnable<Boolean> cir) {
		// FoxEntity.JumpChasingGoal thisObject = (FoxEntity.JumpChasingGoal)(Object)this;
		System.out.println("Pre Inject: shouldContinue = " + cir.getReturnValue());
		cir.setReturnValue(false);
		System.out.println("Post Inject: shouldContinue = " + cir.getReturnValue());
	}

	@Inject(at = @At("HEAD"), method = "start")
	private void Start(CallbackInfo info) {
		System.out.println("Fox has started jump.");
	}

	@Inject(at = @At("HEAD"), method = "stop")
	private void Stop(CallbackInfo info) {
		System.out.println("Fox has stopped jump.");
	}
}