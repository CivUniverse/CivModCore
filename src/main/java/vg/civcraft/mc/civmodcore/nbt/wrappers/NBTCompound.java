package vg.civcraft.mc.civmodcore.nbt.wrappers;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.experimental.ExtensionMethod;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import org.apache.commons.lang3.StringUtils;
import vg.civcraft.mc.civmodcore.nbt.NBTType;
import vg.civcraft.mc.civmodcore.nbt.extensions.NBTTagListExtensions;
import vg.civcraft.mc.civmodcore.utilities.JavaExtensions;
import vg.civcraft.mc.civmodcore.utilities.UuidUtils;

@ExtensionMethod({JavaExtensions.class, NBTTagListExtensions.class})
public class NBTCompound extends NBTTagCompound {

	public static final String NULL_STRING = "\u0000";
	private static final String UUID_MOST_SUFFIX = "Most";
	private static final String UUID_LEAST_SUFFIX = "Least";
	private static final String UUID_KEY = "uuid";

	/**
	 * Creates a new NBTCompound.
	 */
	public NBTCompound() {
		super();
	}

	/**
	 * Creates a new NBTCompound based on an existing inner-map.
	 */
	public NBTCompound(@Nonnull final Map<String, NBTBase> raw) {
		super(Objects.requireNonNull(raw));
	}

	/**
	 * Creates a new NBTCompound by extracting the inner-map of the given NBTTagCompound.
	 *
	 * @param tag The NBTTagCompound to extract from.
	 */
	public NBTCompound(@Nullable final NBTTagCompound tag) {
		this(tag.orElseGet(NBTTagCompound::new).x);
	}

	/**
	 * Returns the size of the tag compound.
	 *
	 * @return The size of the tag compound.
	 */
	public int size() {
		return this.x.size();
	}

	/**
	 * Checks if the tag compound is empty.
	 *
	 * @return Returns true if the tag compound is empty.
	 */
	public boolean isEmpty() {
		return this.x.isEmpty();
	}

	/**
	 * Checks if the tag compound contains a particular key.
	 *
	 * @param key The key to check.
	 * @return Returns true if the contains the given key.
	 */
	@Override
	public boolean hasKey(@Nullable final String key) {
		return this.x.containsKey(key);
	}

	/**
	 * Checks if the tag compound contains a particular key of a particular type.
	 *
	 * @param key The key to check.
	 * @param type The type to check for.
	 * @return Returns true if the contains the given key of the given type.
	 */
	@Override
	public boolean hasKeyOfType(@Nonnull final String key, final int type) {
		return super.hasKeyOfType(key, type);
	}

	/**
	 * Gets the keys within this compound.
	 *
	 * @return Returns the set of keys.
	 */
	@Nonnull
	@Override
	public Set<String> getKeys() {
		return this.x.keySet();
	}

	/**
	 * Moves a value from one key to another.
	 *
	 * @param fromKey The previous key.
	 * @param toKey The new key.
	 */
	public void switchKey(@Nonnull final String fromKey, @Nonnull final String toKey) {
		if (StringUtils.equals(fromKey, toKey)) {
			return;
		}
		this.x.computeIfPresent(fromKey, (_key, value) -> {
			this.x.put(toKey, value);
			return null;
		});
	}

	/**
	 * <p>Removes a key and its respective value from the tag compound, if it exists.</p>
	 *
	 * <p>Note: If you're removing a UUID, use {@link NBTCompound#removeUUID(String)} instead.</p>
	 *
	 * @param key The key to remove.
	 */
	@Override
	public void remove(@Nullable final String key) {
		this.x.remove(key);
	}

	/**
	 * Clears all values from the tag compound.
	 */
	public void clear() {
		this.x.clear();
	}

	/**
	 * Adopts a copy of the NBT data from another compound.
	 *
	 * @param nbt The NBT data to copy and adopt.
	 */
	public void adopt(@Nonnull final NBTCompound nbt) {
		Preconditions.checkNotNull(nbt);
		this.x.clear();
		this.x.putAll(nbt.x);
	}

	/**
	 * Gets a primitive boolean value from a key.
	 *
	 * @param key The key to get the boolean from.
	 * @return The value of the key, default: FALSE
	 */
	@Override
	public boolean getBoolean(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getBoolean(key);
	}

	/**
	 * Sets a primitive boolean value to a key.
	 *
	 * @param key The key to set to the boolean to.
	 * @param value The boolean to set to the key.
	 */
	@Override
	public void setBoolean(@Nonnull final String key, final boolean value) {
		Preconditions.checkNotNull(key);
		super.setBoolean(key, value);
	}

	/**
	 * Gets a primitive byte value from a key.
	 *
	 * @param key The key to get the byte from.
	 * @return The value of the key, default: 0
	 */
	@Override
	public byte getByte(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getByte(key);
	}

	/**
	 * Sets a primitive byte value to a key.
	 *
	 * @param key The key to set to the byte to.
	 * @param value The byte to set to the key.
	 */
	@Override
	public void setByte(@Nonnull final String key, final byte value) {
		Preconditions.checkNotNull(key);
		super.setByte(key, value);
	}

	/**
	 * Gets a primitive short value from a key.
	 *
	 * @param key The key to get the short from.
	 * @return The value of the key, default: 0
	 */
	@Override
	public short getShort(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getShort(key);
	}

	/**
	 * Sets a primitive short value to a key.
	 *
	 * @param key The key to set to the short to.
	 * @param value The short to set to the key.
	 */
	@Override
	public void setShort(@Nonnull final String key, final short value) {
		Preconditions.checkNotNull(key);
		super.setShort(key, value);
	}

	/**
	 * Gets a primitive integer value from a key.
	 *
	 * @param key The key to get the integer from.
	 * @return The value of the key, default: 0
	 */
	@Override
	public int getInt(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getInt(key);
	}

	/**
	 * Sets a primitive integer value to a key.
	 *
	 * @param key The key to set to the integer to.
	 * @param value The integer to set to the key.
	 */
	@Override
	public void setInt(@Nonnull final String key, final int value) {
		Preconditions.checkNotNull(key);
		super.setInt(key, value);
	}

	/**
	 * Gets a primitive long value from a key.
	 *
	 * @param key The key to get the long from.
	 * @return The value of the key, default: 0L
	 */
	@Override
	public long getLong(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getLong(key);
	}

	/**
	 * Sets a primitive long value to a key.
	 *
	 * @param key The key to set to the long to.
	 * @param value The long to set to the key.
	 */
	@Override
	public void setLong(@Nonnull final String key, final long value) {
		Preconditions.checkNotNull(key);
		super.setLong(key, value);
	}

	/**
	 * Gets a primitive float value from a key.
	 *
	 * @param key The key to get the float from.
	 * @return The value of the key, default: 0f
	 */
	@Override
	public float getFloat(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getFloat(key);
	}

	/**
	 * Sets a primitive float value to a key.
	 *
	 * @param key The key to set to the float to.
	 * @param value The float to set to the key.
	 */
	@Override
	public void setFloat(@Nonnull final String key, final float value) {
		Preconditions.checkNotNull(key);
		super.setFloat(key, value);
	}

	/**
	 * Gets a primitive double value from a key.
	 *
	 * @param key The key to get the double from.
	 * @return The value of the key, default: 0d
	 */
	@Override
	public double getDouble(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getDouble(key);
	}

	/**
	 * Sets a primitive double value to a key.
	 *
	 * @param key The key to set to the double to.
	 * @param value The double to set to the key.
	 */
	@Override
	public void setDouble(@Nonnull final String key, final double value) {
		Preconditions.checkNotNull(key);
		super.setDouble(key, value);
	}

	/**
	 * Checks whether a UUID exists at the given key.
	 *
	 * @param key The key of the UUID.
	 * @return Returns true if a UUID exists at the given key.
	 */
	public boolean hasUUID(@Nonnull final String key) {
		return super.b(key);
	}

	/**
	 * Gets a UUID value from a key.
	 *
	 * @param key The key to get the UUID from.
	 * @return The value of the key, default: 00000000-0000-0000-0000-000000000000
	 */
	public UUID getUUID(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return !super.b(key) ? UuidUtils.IDENTITY : super.a(key);
	}

	/**
	 * Gets a UUID value from a key.
	 *
	 * @param key The key to get the UUID from.
	 * @return The value of the key, default: NULL
	 */
	@Nullable
	public UUID getNullableUUID(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return !super.b(key) ? null : super.a(key);
	}

	/**
	 * Sets a UUID value to a key.
	 *
	 * @param key The key to set to the UUID to.
	 * @param value The UUID to set to the key.
	 */
	public void setUUID(@Nonnull final String key, @Nullable final UUID value) {
		setUUID(key, value, false);
	}

	/**
	 * Sets a UUID value to a key.
	 *
	 * @param key The key to set to the UUID to.
	 * @param value The UUID to set to the key.
	 * @param useMojangFormat Whether to save as Mojang's least+most, or the updated int array.
	 */
	public void setUUID(@Nonnull final String key, @Nullable final UUID value, final boolean useMojangFormat) {
		Preconditions.checkNotNull(key);
		if (value == null) {
			removeUUID(key);
		}
		else {
			if (useMojangFormat) {
				super.setLong(key + UUID_MOST_SUFFIX, value.getMostSignificantBits());
				super.setLong(key + UUID_LEAST_SUFFIX, value.getLeastSignificantBits());
			}
			else {
				super.a(key, value);
			}
		}
	}

	/**
	 * Removes a UUID value, which is necessary because Bukkit stores UUIDs by splitting up the two significant parts
	 * into their own values.
	 *
	 * @param key The key of the UUID to remove.
	 */
	public void removeUUID(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		remove(key);
		remove(key + UUID_MOST_SUFFIX);
		remove(key + UUID_LEAST_SUFFIX);
	}

	/**
	 * Gets a String value from a key.
	 *
	 * @param key The key to get the String from.
	 * @return The value of the key, default: ""
	 */
	@Nonnull
	@Override
	public String getString(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getString(key);
	}

	/**
	 * Gets a String value from a key.
	 *
	 * @param key The key to get the String from.
	 * @return The value of the key, default: NULL
	 */
	@Nullable
	public String getNullableString(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		if (!super.hasKeyOfType(key, 8)) {
			return null;
		}
		final String value = super.getString(key);
		if (NULL_STRING.equals(value)) {
			return null;
		}
		return value;
	}

	/**
	 * Sets a String value to a key.
	 *
	 * @param key The key to set to the String to.
	 * @param value The String to set to the key.
	 */
	@Override
	public void setString(@Nonnull final String key, @Nullable final String value) {
		Preconditions.checkNotNull(key);
		if (value == null) {
			remove(key);
		}
		else {
			super.setString(key, value);
		}
	}

	/**
	 * Gets an NBT compound from a key.
	 *
	 * @param key The key to get the NBT compound from.
	 * @return The value of the key, default: {}
	 */
	@Nonnull
	@Override
	public NBTCompound getCompound(@Nonnull final String key) {
		final var found = getNullableCompound(key);
		return found == null ? new NBTCompound() : found;
	}

	/**
	 * Gets an NBT compound from a key.
	 *
	 * @param key The key to get the NBT compound from.
	 * @return The value of the key, default: NULL
	 */
	@Nullable
	public NBTCompound getNullableCompound(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final var found = this.x.get(key);
		if (found instanceof NBTCompound compound) {
			return compound;
		}
		if (found instanceof NBTTagCompound compound) {
			return new NBTCompound(compound);
		}
		return null;
	}

	/**
	 * Sets an NBT compound to a key.
	 *
	 * @param key The key to set to the NBT compound to.
	 * @param value The NBT compound to set to the key.
	 */
	public void setCompound(@Nonnull final String key, @Nullable final NBTCompound value) {
		Preconditions.checkNotNull(key);
		if (value == null) {
			remove(key);
		}
		else {
			set(key, value);
		}
	}

	/**
	 * Gets a Component value from a key.
	 *
	 * @param key The key of the Component.
	 * @return Returns a Component, defaulted to {@link Component#empty()}
	 */
	public Component getComponent(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		if (hasKeyOfType(key, NBTType.STRING)) {
			return Component.empty();
		}
		else {
			return GsonComponentSerializer.gson().deserialize(getString(key));
		}
	}

	/**
	 * Sets a Component value to a key.
	 *
	 * @param key The key of the Component to set.
	 * @param value The Component value to set.
	 */
	public void setComponent(@Nonnull final String key, @Nonnull final Component value) {
		Preconditions.checkNotNull(key);
		Preconditions.checkNotNull(value);
		setString(key, GsonComponentSerializer.gson().serialize(value));
	}

	// ------------------------------------------------------------
	// Array Functions
	// ------------------------------------------------------------

	/**
	 * Gets an array of primitive booleans from a key.
	 *
	 * @param key The key to of the array.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public boolean[] getBooleanArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final byte[] cache = getByteArray(key);
		final boolean[] result = new boolean[cache.length];
		for (int i = 0; i < cache.length; i++) {
			result[i] = cache[i] != 0;
		}
		return result;
	}

	/**
	 * Sets an array of primitive booleans to a key.
	 *
	 * @param key The key to set to array to.
	 * @param booleans The booleans to set to the key.
	 */
	public void setBooleanArray(@Nonnull final String key, @Nullable final boolean[] booleans) {
		Preconditions.checkNotNull(key);
		if (booleans == null) {
			remove(key);
			return;
		}
		final byte[] converted = new byte[booleans.length];
		for (int i = 0; i < booleans.length; i++) {
			converted[i] = (byte) (booleans[i] ? 1 : 0);
		}
		setByteArray(key, converted);
	}

	/**
	 * Gets an array of primitive bytes from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	@Override
	public byte[] getByteArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getByteArray(key);
	}

	/**
	 * Sets an array of primitive bytes to a key.
	 *
	 * @param key The key to set to the bytes to.
	 * @param bytes The bytes to set to the key.
	 */
	@Override
	public void setByteArray(@Nonnull final String key, @Nullable final byte[] bytes) {
		Preconditions.checkNotNull(key);
		if (bytes == null) {
			remove(key);
			return;
		}
		super.setByteArray(key, bytes);
	}

	/**
	 * Gets an array of primitive shorts from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public short[] getShortArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final NBTTagList list = getList(key, NBTType.SHORT);
		final short[] result = new short[list.size()];
		for (int i = 0; i < result.length; i++) {
			if (list.get(i) instanceof NBTTagShort nbtShort) {
				result[i] = nbtShort.asShort();
			}
		}
		return result;
	}

	/**
	 * Sets an array of primitive bytes to a key.
	 *
	 * @param key The key to set to values to.
	 * @param shorts The shorts to set to the key.
	 */
	public void setShortArray(@Nonnull final String key, @Nullable final short[] shorts) {
		Preconditions.checkNotNull(key);
		if (shorts == null) {
			remove(key);
			return;
		}
		final NBTTagList list = new NBTTagList();
		for (final short value : shorts) {
			list.add(NBTTagShort.a(value));
		}
		set(key, list);
	}

	/**
	 * Gets an array of primitive integers from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	@Override
	public int[] getIntArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getIntArray(key);
	}

	/**
	 * Sets an array of primitive integers to a key.
	 *
	 * @param key The key to set to values to.
	 * @param ints The values to set to the key.
	 */
	@Override
	public void setIntArray(@Nonnull final String key, @Nullable final int[] ints) {
		Preconditions.checkNotNull(key);
		if (ints == null) {
			remove(key);
			return;
		}
		super.setIntArray(key, ints);
	}

	/**
	 * Gets an array of primitive longs from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	@Override
	public long[] getLongArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		return super.getLongArray(key);
	}

	/**
	 * Sets an array of primitive longs to a key.
	 *
	 * @param key The key to set to values to.
	 * @param longs The values to set to the key.
	 */
	public void setLongArray(@Nonnull final String key, @Nullable final long[] longs) {
		Preconditions.checkNotNull(key);
		if (longs == null) {
			remove(key);
			return;
		}
		super.a(key, longs);
	}

	/**
	 * Gets an array of primitive floats from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public float[] getFloatArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final NBTTagList list = getList(key, NBTType.FLOAT);
		final float[] result = new float[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.i(i);
		}
		return result;
	}

	/**
	 * Sets an array of primitive floats to a key.
	 *
	 * @param key The key to set to values to.
	 * @param floats The values to set to the key.
	 */
	public void setFloatArray(@Nonnull final String key, @Nullable final float[] floats) {
		Preconditions.checkNotNull(key);
		if (floats == null) {
			remove(key);
			return;
		}
		final NBTTagList list = new NBTTagList();
		for (final float value : floats) {
			list.add(NBTTagFloat.a(value));
		}
		set(key, list);
	}

	/**
	 * Gets an array of primitive doubles from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public double[] getDoubleArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final NBTTagList list = getList(key, NBTType.DOUBLE);
		final double[] result = new double[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.h(i);
		}
		return result;
	}

	/**
	 * Sets an array of primitive doubles to a key.
	 *
	 * @param key The key to set to values to.
	 * @param doubles The values to set to the key.
	 */
	public void setDoubleArray(@Nonnull final String key, @Nullable final double[] doubles) {
		Preconditions.checkNotNull(key);
		if (doubles == null) {
			remove(key);
			return;
		}
		final NBTTagList list = new NBTTagList();
		for (final double value : doubles) {
			list.add(NBTTagDouble.a(value));
		}
		set(key, list);
	}

	/**
	 * Gets an array of UUIDs from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public UUID[] getUUIDArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		if (get(key) instanceof NBTTagList list) {
			if (list.e() == NBTType.INT_ARRAY) {
				final UUID[] result = new UUID[list.size()];
				for (int i = 0, l = list.size(); i < l; i++) {
					result[i] = list.getUUID(i);
				}
				return result;
			}
			if (list.e() == NBTType.COMPOUND) {
				final UUID[] result = new UUID[list.size()];
				for (int i = 0, l = list.size(); i < l; i++) {
					result[i] = list.getCompound(i).a(UUID_KEY);
				}
				return result;
			}
		}
		return new UUID[0];
	}

	/**
	 * Sets an array of UUIDs to a key.
	 *
	 * @param key The key to set to values to.
	 * @param uuids The values to set to the key.
	 */
	public void setUUIDArray(@Nonnull final String key, @Nullable final UUID[] uuids) {
		Preconditions.checkNotNull(key);
		if (uuids == null) {
			remove(key);
			return;
		}
		final NBTTagList list = new NBTTagList();
		for (final UUID value : uuids) {
			list.addUUID(value);
		}
		set(key, list);
	}

	/**
	 * Gets an array of Strings from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public String[] getStringArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final NBTTagList list = getList(key, NBTType.STRING);
		final String[] result = new String[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i) instanceof NBTTagString nbtString ? nbtString.asString() : "";
		}
		return result;
	}

	/**
	 * Sets an array of Strings to a key.
	 *
	 * @param key The key to set to values to.
	 * @param strings The values to set to the key.
	 */
	public void setStringArray(@Nonnull final String key, @Nullable final String[] strings) {
		Preconditions.checkNotNull(key);
		if (strings == null) {
			remove(key);
			return;
		}
		final NBTTagList list = new NBTTagList();
		List.of(strings).forEach((string) -> list.add(NBTTagString.a(string)));
		set(key, list);
	}

	/**
	 * Gets an array of tag compounds from a key.
	 *
	 * @param key The key to get the values of.
	 * @return The values of the key, default: empty array
	 */
	@Nonnull
	public NBTCompound[] getCompoundArray(@Nonnull final String key) {
		Preconditions.checkNotNull(key);
		final NBTTagList list = getList(key, NBTType.COMPOUND);
		final NBTCompound[] result = new NBTCompound[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = new NBTCompound(list.getCompound(i));
		}
		return result;
	}

	/**
	 * Sets an array of tag compounds to a key.
	 *
	 * @param key The key to set to values to.
	 * @param compounds The values to set to the key.
	 */
	public void setCompoundArray(@Nonnull final String key, @Nullable final NBTCompound[] compounds) {
		Preconditions.checkNotNull(key);
		if (compounds == null) {
			remove(key);
			return;
		}
		final NBTTagList list = new NBTTagList();
		list.addAll(List.of(compounds));
		set(key, list);
	}

	// ------------------------------------------------------------
	// Object Overrides
	// ------------------------------------------------------------

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof NBTCompound other) {
			return Objects.equals(this.x, other.x);
		}
		return false;
	}

	@Nonnull
	@Override
	public String toString() {
		return "NBTCompound" + this.x.toString();
	}

}
