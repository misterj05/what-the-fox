package net.misterj05.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoxEntity.class)
public abstract class WhatTheFoxFoxEntityMixin {
    @Unique
    FoxEntity thisFox = (FoxEntity) (Object)this;

    @Inject(at = @At("TAIL"), method = "setTarget")
    private void setTarget(LivingEntity target, CallbackInfo ci) {
        thisFox.setCanPickUpLoot(thisFox.getTarget() == null);
    }
}