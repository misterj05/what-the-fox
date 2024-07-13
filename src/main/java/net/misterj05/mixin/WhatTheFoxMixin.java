package net.misterj05.mixin;

import net.minecraft.entity.passive.FoxEntity;
import java.util.Timer;
import java.util.TimerTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoxEntity.JumpChasingGoal.class)
public abstract class WhatTheFoxMixin {
	@Unique
	private Timer timer = new Timer();
	@Unique
	private boolean taskScheduled = false;
	@Unique
	private boolean stopJump = false;

	@Inject(at = @At("RETURN"), method = "shouldContinue", cancellable = true)
	private void canStop(CallbackInfoReturnable<Boolean> cir) {
		if (stopJump && !taskScheduled) {
			stopJump = false;
			cir.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "start")
	private void start(CallbackInfo info) {
		if (!taskScheduled) {
			taskScheduled = true;
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					taskScheduled = false;
					stopJump = true;
				}
			};
			timer.schedule(task, 1200);
		}
	}
}