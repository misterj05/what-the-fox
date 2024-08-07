package net.misterj05.mixin;

import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This Mixin is to prevent the FoxEntity from getting stuck in a Jump/Pounce if not landing on the same y level as it's target.

// Set our scope to be the JumpChasingGoal Class which is inside the FoxEntity Class.
// This class handles the Jump/Pounce that the Fox does when it's within range of it's target.
@Mixin(FoxEntity.JumpChasingGoal.class)
public abstract class WhatTheFoxJumpChasingMixin {
	// Grabbing the "this" instance of the parent FoxEntity Class. field_17984 is equivalent to "FoxEntity.this".
	@Shadow @Final FoxEntity field_17984;

	// Inject into the BEGINNING of the "shouldContinue" method inside the JumpChasingGoal Class.
	// shouldContinue runs every tick of the Jump/Pounce. Not very intensive.
	@Inject(at = @At("HEAD"), method = "shouldContinue", cancellable = true)
	private void shouldContinue(CallbackInfoReturnable<Boolean> cir) {
		// Check if the FoxEntity is on the ground OR touching water, cancel out of the method if either of these are true.
		// No further code in "shouldContinue" will be processed if one of these are true.
		if (field_17984.isOnGround() || field_17984.isTouchingWater()) {
			cir.setReturnValue(false);
		}
	}
}