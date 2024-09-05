package net.misterj05.mixin;

import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This Mixin is to prevent the Fox entity from being able to Jump/Pounce while in water.

// Set our scope to be the StalkPreyGoal Class which is inside the Fox Class.
// This Class handles the run/speedup that the Fox does when first acquiring a target, when it gets in range of it's target it will stop and FoxPounceGoal will take control.
@Mixin(targets = "net.minecraft.world.entity.animal.Fox$StalkPreyGoal")
public abstract class WhatTheFoxStalkPreyMixin {
    // Grabbing the "this" instance of the parent Fox Class. this$0 is equivalent to "Fox.this".
    @Shadow @Final Fox this$0;

    // Inject into the BEGINNING of the "canUse" method inside the StalkPreyGoal Class.
    // canUse runs every tick of the Fox. Can be intensive so be careful.
    @Inject(at = @At("HEAD"), method = "canUse", cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {
        // Check if the Fox is touching water, cancel out of the method if true.
        // No further code in "canUse" will be processed if this is true.
        if (this$0.isInWater()) {
            cir.setReturnValue(false);
        }
    }
}