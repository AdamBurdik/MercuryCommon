package me.adamix.mercury.common.configuration.toml;

import me.adamix.mercury.api.configuration.MercuryArray;
import me.adamix.mercury.api.configuration.MercuryConfiguration;
import me.adamix.mercury.api.configuration.MercuryTable;
import me.adamix.mercury.api.exception.configuration.ConfigurationException;
import me.adamix.mercury.api.math.MercuryPosition;
import me.adamix.mercury.common.math.SimpleMercuryPosition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tomlj.Toml;
import org.tomlj.TomlArray;
import org.tomlj.TomlParseResult;
import org.tomlj.TomlTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public record TomlConfiguration(
		@NotNull TomlParseResult parseResult,
		@NotNull Path filePath
) implements MercuryConfiguration {

	@Override
	public @NotNull String name() {
		return filePath.getFileName().toString();
	}

	@Override
	public boolean contains(@NotNull String dottedKey) {
		return parseResult.contains(dottedKey);
	}

	@Override
	public @Nullable Object getObject(@NotNull String dottedKey) {
		return parseResult.get(dottedKey);
	}

	@Override
	public @Nullable String getString(@NotNull String dottedKey) {
		return parseResult.getString(dottedKey);
	}

	@Nullable
	@Override
	public Integer getInteger(@NotNull String dottedKey) {
		Long longValue = parseResult.getLong(dottedKey);
		if (longValue == null) {
			return null;
		}
		return longValue.intValue();
	}

	@Override
	public @Nullable Long getLong(@NotNull String dottedKey) {
		return parseResult.getLong(dottedKey);
	}

	@Override
	public @Nullable Float getFloat(@NotNull String dottedKey) {
		Double doubleValue = parseResult.getDouble(dottedKey);
		if (doubleValue == null) {
			return null;
		}
		return doubleValue.floatValue();
	}

	@Override
	public @Nullable Double getDouble(@NotNull String dottedKey) {
		return parseResult.getDouble(dottedKey);
	}

	@Override
	public @Nullable Boolean getBoolean(@NotNull String dottedKey) {
		return parseResult.getBoolean(dottedKey);
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
		Object object = parseResult.get(dottedKey);
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

	public static TomlConfiguration create(@NotNull Path path) {
		// ToDo Add check for file extension
		try {
			TomlParseResult parseResult = Toml.parse(path);
			if (parseResult.hasErrors()) {
				parseResult.errors().forEach(error -> {
					throw new ConfigurationException("Error while parsing " + path.getFileName() + "!\n " + error.toString());
				});
			}
			return new TomlConfiguration(parseResult, path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static TomlConfiguration create(@NotNull File file) {
		return create(file.toPath());
	}
}
