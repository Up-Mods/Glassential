package dev.upcraft.glassential.blocks;

import dev.upcraft.glassential.Glassential;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

public enum BlockProperties {
    TINTED("tinted", ChatFormatting.DARK_GRAY),
    ETHEREAL("ethereal", ChatFormatting.AQUA),
    GHOSTLY("ghostly", ChatFormatting.GRAY),
    LUMINOUS("luminous", ChatFormatting.GOLD),
    REDSTONE("redstone", ChatFormatting.DARK_RED),
    REVERSE_ETHEREAL("reverse_ethereal", ChatFormatting.AQUA);

    private final String translationKey;
    private final ChatFormatting[] formatting;

    BlockProperties(String translationKey, ChatFormatting... formatting) {
        this.translationKey = Util.makeDescriptionId("tooltip", new ResourceLocation(Glassential.MODID, "property_" + translationKey));
        this.formatting = ArrayUtils.insert(0, formatting, ChatFormatting.ITALIC);
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public ChatFormatting[] getFormatting() {
        return this.formatting;
    }
}
