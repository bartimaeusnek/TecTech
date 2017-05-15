package com.github.technus.tectech.thing.metaTileEntity.multi;

import com.github.technus.tectech.CommonValues;
import com.github.technus.tectech.thing.block.QuantumGlassBlock;
import com.github.technus.tectech.thing.metaTileEntity.constructableTT;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.GT_RenderedTexture;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import static com.github.technus.tectech.Util.StructureBuilder;
import static com.github.technus.tectech.thing.casing.GT_Container_CasingsTT.sBlockCasingsTT;
import static gregtech.api.enums.GT_Values.E;

/**
 * Created by danie_000 on 17.12.2016.
 */
public class GT_MetaTileEntity_EM_wormhole extends GT_MetaTileEntity_MultiblockBase_EM implements constructableTT {//TODO MAKE COMPATIBLE WITH STARGATES XD
    private static Textures.BlockIcons.CustomIcon ScreenOFF;
    private static Textures.BlockIcons.CustomIcon ScreenON;

    //region structure
    private static final String[][] shape = new String[][]{
            {E,E,E,"C   ","C . ","C   ",E,E,E,},
            {E,E,"D0","C000","B00100","C000","D0",E,E,},
            {E,E,"D0","C2A2","B0C0","C2A2","D0",E,E,},
            {E,"D0","D0",E,"A00C00",E,"D0","D0",E,},
            {E,"D0",E,E,"A0E0",E,E,"D0",E,},
            {"D0","D0",E,E,"00E00",E,E,"D0","D0",},
            {"B00000","A0033300","003C300","03E30","03E30","03E30","003C300","A0033300","B00000",},
            {"B0!!!0","A 31113 ","031222130","!12C21!","!12C21!","!12C21!","031222130","A 31113 ","B0!!!0",},
            {"B0!!!0","A 31113 ","031444130","!14C41!","!14C41!","!14C41!","031444130","A 31113 ","B0!!!0",},
            {"B0!!!0","A 31113 ","031222130","!12C21!","!12C21!","!12C21!","031222130","A 31113 ","B0!!!0",},
            {"B00000","A0033300","003C300","03E30","03E30","03E30","003C300","A0033300","B00000",},
    };
    private static final Block[] blockType = new Block[]{sBlockCasingsTT, sBlockCasingsTT, QuantumGlassBlock.INSTANCE ,sBlockCasingsTT, sBlockCasingsTT};
    private static final byte[] blockMeta = new byte[]{12, 10, 0, 5, 11};
    private static final String[] addingMethods = new String[]{"addClassicToMachineList", "addElementalToMachineList"};
    private static final byte[] casingTextures = new byte[]{textureOffset, textureOffset + 4};
    private static final Block[] blockTypeFallback = new Block[]{sBlockCasingsTT, sBlockCasingsTT};
    private static final byte[] blockMetaFallback = new byte[]{0, 4};
    //endregion

    public GT_MetaTileEntity_EM_wormhole(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_EM_wormhole(String aName) {
        super(aName);
    }

    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_EM_wormhole(this.mName);
    }

    @Override
    public void registerIcons(IIconRegister aBlockIconRegister) {
        ScreenOFF = new Textures.BlockIcons.CustomIcon("iconsets/EM_WH");
        ScreenON = new Textures.BlockIcons.CustomIcon("iconsets/EM_WH_ACTIVE");
        super.registerIcons(aBlockIconRegister);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        if (aSide == aFacing) {
            return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[textureOffset + 4], new GT_RenderedTexture(aActive ? ScreenON : ScreenOFF)};
        }
        return new ITexture[]{Textures.BlockIcons.CASING_BLOCKS[textureOffset + 4]};
    }

    @Override
    public boolean EM_checkMachine(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
        return EM_StructureCheckAdvanced(shape, blockType, blockMeta, addingMethods, casingTextures, blockTypeFallback, blockMetaFallback, 4, 4, 0);
    }

    @Override
    public void construct(int qty) {
        StructureBuilder(shape, blockType, blockMeta,4, 4, 0, getBaseMetaTileEntity());
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                CommonValues.tecMark,
                "It is not full of worms.",
                EnumChatFormatting.AQUA.toString() + EnumChatFormatting.BOLD + "It is full of anti-worms!!!"
        };
    }
}
