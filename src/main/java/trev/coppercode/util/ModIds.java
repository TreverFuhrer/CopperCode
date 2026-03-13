package trev.coppercode.util;

import net.minecraft.resources.Identifier;

/** Small helper for creating identifiers inside the Copper Code namespace. */
public final class ModIds {
	private ModIds() {
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, path);
	}
}
