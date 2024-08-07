package net.misterj05.mixin;

import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This Mixin is to prevent the FoxEntity from being able to Jump/Pounce while in water.

// Set our scope to be the MoveToHuntGoal Class which is inside the FoxEntity Class.
// This Class handles the run/speedup that the Fox does when first acquiring a target, when it gets in range of it's target it will stop and JumpChasingGoal will take control.
@Mixin(targets = "net.minecraft.entity.passive.FoxEntity$MoveToHuntGoal")
public abstract class WhatTheFoxMoveToHuntMixin {
    // Grabbing the "this" instance of the parent FoxEntity Class. field_17995 is equivalent to "FoxEntity.this".
    @Shadow @Final FoxEntity field_17995;

    // Inject into the BEGINNING of the "canStart" method inside the MoveToHuntGoal Class.
    // canStart runs every tick of the FoxEntity. Can be intensive so be careful.
    @Inject(at = @At("HEAD"), method = "canStart", cancellable = true)
    private void canStart(CallbackInfoReturnable<Boolean> cir) {
        // Check if the FoxEntity is touching water, cancel out of the method if true.
        // No further code in "canStart" will be processed if this is true.
        if (field_17995.isTouchingWater()) {
            cir.setReturnValue(false);
        }
    }
}
