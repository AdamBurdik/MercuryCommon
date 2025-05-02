package me.adamix.mercury.common.configuration.toml;

import me.adamix.mercury.api.configuration.MercuryArray;
import me.adamix.mercury.api.configuration.MercuryTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tomlj.TomlArray;
import org.tomlj.TomlTable;

import java.nio.file.Path;

public record MercuryTomlArray(
		TomlArray tomlArray,
		Path filePath,
		String dottedKeyPath
) implements MercuryArray {

	@Override
	public @NotNull String name() {
		return filePath + " -> " + dottedKeyPath;
	}

	@Override
	public int size() {
		return tomlArray.size();
	}

	@Override
	public @Nullable Object getObject(int index) {
		return tomlArray.get(index);
	}

	@Override
	public @NotNull Object getObjectSafe(int index) {
		return tomlArray.get(index);
	}

	@Override
	public @Nullable String getString(int index) {
		return tomlArray.getString(index);
	}

	@Override
	public @NotNull String getStringSafe(int index) {
		return tomlArray.getString(index);
	}

	@Override
	public @Nullable Integer getInteger(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof Long longValue) {
			return longValue.intValue();
		} else if (object instanceof Integer intValue) {
			return intValue;
		}
		return null;
	}

	@Override
	public int getIntegerSafe(int index) {
		return (int) tomlArray.getLong(index);
	}

	@Override
	public @Nullable Long getLong(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof Long longValue) {
			return longValue;
		}
		return null;
	}

	@Override
	public long getLongSafe(int index) {
		return tomlArray.getLong(index);
	}

	@Override
	public @Nullable Float getFloat(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof Float floatValue) {
			return floatValue;
		} else if (object instanceof Double doubleValue) {
			return doubleValue.floatValue();
		}
		return null;
	}

	@Override
	public float getFloatSafe(int index) {
		return (float) tomlArray.getDouble(index);
	}

	@Override
	public @Nullable Double getDouble(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof Double doubleValue) {
			return doubleValue;
		}
		return null;
	}

	@Override
	public double getDoubleSafe(int index) {
		return tomlArray.getDouble(index);
	}

	@Override
	public @Nullable Boolean getBoolean(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof Boolean booleanValue) {
			return booleanValue;
		}
		return null;
	}

	@Override
	public boolean getBooleanSafe(int index) {
		return tomlArray.getBoolean(index);
	}

	@Override
	public @Nullable MercuryArray getArray(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof TomlArray array) {
			return new MercuryTomlArray(array, this.filePath, this.dottedKeyPath + "[" + index + "]");
		}
		return null;
	}

	@Override
	public @NotNull MercuryArray getArraySafe(int index) {
		TomlArray array = tomlArray.getArray(index);
		return new MercuryTomlArray(array, this.filePath, this.dottedKeyPath + "[" + index + "]");
	}

	@Override
	public @Nullable MercuryTable getTable(int index) {
		Object object = tomlArray.get(index);
		if (object instanceof TomlTable table) {
			return new MercuryTomlTable(table, this.filePath, this.dottedKeyPath + "[" + index + "]");
		}
		return null;
	}

	@Override
	public @NotNull MercuryTable getTableSafe(int index) {
		TomlTable table = tomlArray.getTable(index);
		return new MercuryTomlTable(table, filePath, this.dottedKeyPath + "[" + index + "]");
	}
}
