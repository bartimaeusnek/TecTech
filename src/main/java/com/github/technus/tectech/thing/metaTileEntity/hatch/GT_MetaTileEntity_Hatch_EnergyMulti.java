package com.github.technus.tectech.thing.metaTileEntity.hatch;

import com.github.technus.tectech.CommonValues;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import static com.github.technus.tectech.Util.V;

/**
 * Created by danie_000 on 16.12.2016.
 */
public class GT_MetaTileEntity_Hatch_EnergyMulti extends GT_MetaTileEntity_Hatch {
    public final int Amperes;
    public final int eTier;
    public static ITexture[] overlay;
    static{
        try {
            overlay=(ITexture[]) GT_Utility.getField(Textures.BlockIcons.class,"OVERLAYS_ENERGY_IN_POWER").get(null);
        }catch (IllegalAccessException | NullPointerException e){
            overlay = Textures.BlockIcons.OVERLAYS_ENERGY_IN_MULTI;
        }
    }

    public GT_MetaTileEntity_Hatch_EnergyMulti(int aID, String aName, String aNameRegional, int aTier, int aAmp) {
        super(aID, aName, aNameRegional, aTier, 0, "Multiple Ampere Energy Injector for Multiblocks");
        Amperes = aAmp;
        eTier=aTier;
    }

    public GT_MetaTileEntity_Hatch_EnergyMulti(String aName, int aTier, int aAmp, String aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, 0, aDescription, aTextures);
        Amperes = aAmp;
        eTier=aTier;
    }

    @Override
    public ITexture[] getTexturesActive(ITexture aBaseTexture) {
        return new ITexture[]{aBaseTexture, overlay[mTier]};
    }

    @Override
    public ITexture[] getTexturesInactive(ITexture aBaseTexture) {
        return new ITexture[]{aBaseTexture, overlay[mTier]};
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return true;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isInputFacing(byte aSide) {
        return aSide == getBaseMetaTileEntity().getFrontFacing();
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return false;
    }

    @Override
    public long getMinimumStoredEU() {
        return 128L * Amperes;
    }

    @Override
    public long maxEUInput() {
        return V[eTier];
    }

    @Override
    public long maxEUStore() {
        return 512L + V[eTier] * 4L * Amperes;
    }

    @Override
    public long maxAmperesIn() {
        return Amperes + (Amperes >> 2);
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Hatch_EnergyMulti(mName, eTier, Amperes, mDescription, mTextures);
    }

    @Override
    public boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return false;
    }

    @Override
    public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return false;
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                CommonValues.tecMark,
                mDescription,
                "Amperes In: " + EnumChatFormatting.AQUA + maxAmperesIn() + " A"
        };
    }
}
