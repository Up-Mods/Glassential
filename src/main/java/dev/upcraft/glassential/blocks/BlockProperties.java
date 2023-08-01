package dev.upcraft.glassential.blocks;

import dev.upcraft.glassential.Glassential;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.apache.commons.lang3.ArrayUtils;

public enum BlockProperties {
    TINTED("tinted", Formatting.DARK_GRAY),
    ETHEREAL("ethereal", Formatting.AQUA),
    GHOSTLY("ghostly", Formatting.GRAY),
    LUMINOUS("luminous", Formatting.GOLD),
    REDSTONE("redstone", Formatting.DARK_RED),
    REVERSE_ETHEREAL("reverse_ethereal", Formatting.AQUA);

    private final String translationKey;
    private final Formatting[] formatting;

    BlockProperties(String translationKey, Formatting... formatting) {
        this.translationKey = Util.createTranslationKey("tooltip", new Identifier(Glassential.MODID, "property_" + translationKey));
        this.formatting = ArrayUtils.insert(0, formatting, Formatting.ITALIC);
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public Formatting[] getFormatting() {
        return this.formatting;
    }
}
