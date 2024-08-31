package net.misterj05.mixin;

import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This Mixin is to prevent the Fox entity from getting stuck in a Jump/Pounce if not landing on the same y level as it's target.

// Set our scope to be the FoxPounceGoal Class which is inside the Fox Class.
// This class handles the Jump/Pounce that the Fox does when it's within range of it's target.
@Mixin(Fox.FoxPounceGoal.class)
public abstract class WhatTheFoxPounceMixin {
    // Grabbing the "this" instance of the parent Fox Class. this$0 is equivalent to "Fox.this".
    @Shadow @Final Fox this$0;

    // Inject into the BEGINNING of the "canContinueToUse" method inside the FoxPounceGoal Class.
    // canContinueToUse runs every tick of the Jump/Pounce. Not very intensive.
    @Inject(at = @At("HEAD"), method = "canContinueToUse", cancellable = true)
    private void canContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        // Check if the Fox is on the ground OR touching water, cancel out of the method if either of these are true.
        // No further code in "canContinueToUse" will be processed if one of these are true.
        if (this$0.onGround() || this$0.isInWater()) {
            cir.setReturnValue(false);
        }
    }
}