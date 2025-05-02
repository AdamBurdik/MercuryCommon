package me.adamix.mercury.common.configuration.toml;

import me.adamix.mercury.api.configuration.MercuryArray;
import me.adamix.mercury.api.configuration.MercuryTable;
import me.adamix.mercury.api.math.MercuryPosition;
import me.adamix.mercury.common.math.SimpleMercuryPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tomlj.TomlArray;
import org.tomlj.TomlTable;

import java.nio.file.Path;


public record MercuryTomlTable(
		TomlTable tomlTable,
		Path filePath,
		String dottedKeyPath
) implements MercuryTable {
	@Override
	public @NotNull String name() {
		return filePath + " -> " + dottedKeyPath;
	}

	@Override
	public boolean contains(@NotNull String dottedKey) {
		return tomlTable.contains(dottedKey);
	}

	@Override
	public @Nullable Object getObject(@NotNull String dottedKey) {
		return tomlTable.get(dottedKey);
	}

	@Override
	public @Nullable String getString(@NotNull String dottedKey) {
		return tomlTable.getString(dottedKey);
	}

	@Nullable
	@Override
	public Integer getInteger(@NotNull String dottedKey) {
		Long longValue = tomlTable.getLong(dottedKey);
		if (longValue == null) {
			return null;
		}
		return longValue.intValue();
	}

	@Override
	public @Nullable Long getLong(@NotNull String dottedKey) {
		return tomlTable.getLong(dottedKey);
	}

	@Override
	public @Nullable Float getFloat(@NotNull String dottedKey) {
		Double doubleValue = tomlTable.getDouble(dottedKey);
		if (doubleValue == null) {
			return null;
		}
		return doubleValue.floatValue();
	}

	@Override
	public @Nullable Double getDouble(@NotNull String dottedKey) {
		return tomlTable.getDouble(dottedKey);
	}

	@Override
	public @Nullable Boolean getBoolean(@NotNull String dottedKey) {
		return tomlTable.getBoolean(dottedKey);
	}

	@Override
	public @Nullable MercuryTable getTable(@NotNull String dottedKey) {
		// ToDO Implement
		return null;
	}

	@Override
	public @Nullable MercuryArray getArray(@NotNull String dottedKey) {
		return null;
	}

	@Override
	public @Nullable MercuryPosition getPosition(@NotNull String dottedKey) {
		Object object = tomlTable.get(dottedKey);
		if (object instanceof TomlArray tomlArray) {
			return extractPosition(tomlArray);
		} else if (object instanceof TomlTable tomlTable) {
			// ToDo Implement parsing position from table
			return null;
		}
		return null;
	}

	private @Nullable MercuryPosition extractPosition(@NotNull TomlArray tomlArray) {
		if (tomlArray.size() != 3 && tomlArray.size() != 5) {
			return null;
		}
		double x = tomlArray.getDouble(0);
		double y = tomlArray.getDouble(1);
		double z = tomlArray.getDouble(2);
		float yaw = 0f;
		float pitch = 0f;
		if (tomlArray.size() == 5) {
			yaw = (float) tomlArray.getDouble(3);
			pitch = (float) tomlArray.getDouble(4);
		}
		return new SimpleMercuryPosition(x, y, z, yaw, pitch);
	}
}
