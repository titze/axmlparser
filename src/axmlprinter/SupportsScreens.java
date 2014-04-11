package axmlprinter;
import java.io.Serializable;

/**
 * Class representing the <code>supports-screens</code> node in the manifest. By
 * default, all the getters will return null if there was no value defined in
 * the manifest.
 * 
 * To get an instance with all the actual values, use
 * {@link #resolveSupportsScreensValues(int)}
 */
public final class SupportsScreens implements Serializable {
	private Boolean mResizeable;
	private Boolean mAnyDensity;
	private Boolean mSmallScreens;
	private Boolean mNormalScreens;
	private Boolean mLargeScreens;

	public SupportsScreens() {
	}

	/**
	 * Instantiate an instance from a string. The string must have been created
	 * with {@link #getEncodedValues()}.
	 * 
	 * @param value
	 *            the string.
	 */
	public SupportsScreens(String value) {
		String[] values = value.split("\\|");

		mAnyDensity = Boolean.valueOf(values[0]);
		mResizeable = Boolean.valueOf(values[1]);
		mSmallScreens = Boolean.valueOf(values[2]);
		mNormalScreens = Boolean.valueOf(values[3]);
		mLargeScreens = Boolean.valueOf(values[4]);
	}

	/**
	 * Returns an instance of {@link SupportsScreens} initialized with the
	 * default values based on the given targetSdkVersion.
	 * 
	 * @param targetSdkVersion
	 */
	public static SupportsScreens getDefaultValues(int targetSdkVersion) {
		SupportsScreens result = new SupportsScreens();

		result.mNormalScreens = Boolean.TRUE;
		// Screen size and density became available in Android 1.5/API3, so
		// before that
		// non normal screens were not supported by default. After they are
		// considered
		// supported.
		result.mResizeable = result.mAnyDensity = result.mSmallScreens = result.mLargeScreens = targetSdkVersion <= 3 ? Boolean.FALSE : Boolean.TRUE;

		return result;
	}

	/**
	 * Returns a version of the receiver for which all values have been set,
	 * even if they were not present in the manifest.
	 * 
	 * @param targetSdkVersion
	 *            the target api level of the app, since this has an effect on
	 *            default values.
	 */
	public SupportsScreens resolveSupportsScreensValues(int targetSdkVersion) {
		SupportsScreens result = getDefaultValues(targetSdkVersion);

		// Override the default with the existing values:
		if (mResizeable != null)
			result.mResizeable = mResizeable;
		if (mAnyDensity != null)
			result.mAnyDensity = mAnyDensity;
		if (mSmallScreens != null)
			result.mSmallScreens = mSmallScreens;
		if (mNormalScreens != null)
			result.mNormalScreens = mNormalScreens;
		if (mLargeScreens != null)
			result.mLargeScreens = mLargeScreens;

		return result;
	}

	/**
	 * returns the value of the <code>resizeable</code> attribute or null if not
	 * present.
	 */
	public Boolean getResizeable() {
		return mResizeable;
	}

	void setResizeable(Boolean resizeable) {
		mResizeable = getConstantBoolean(resizeable);
	}

	/**
	 * returns the value of the <code>anyDensity</code> attribute or null if not
	 * present.
	 */
	public Boolean getAnyDensity() {
		return mAnyDensity;
	}

	void setAnyDensity(Boolean anyDensity) {
		mAnyDensity = getConstantBoolean(anyDensity);
	}

	/**
	 * returns the value of the <code>smallScreens</code> attribute or null if
	 * not present.
	 */
	public Boolean getSmallScreens() {
		return mSmallScreens;
	}

	void setSmallScreens(Boolean smallScreens) {
		mSmallScreens = getConstantBoolean(smallScreens);
	}

	/**
	 * returns the value of the <code>normalScreens</code> attribute or null if
	 * not present.
	 */
	public Boolean getNormalScreens() {
		return mNormalScreens;
	}

	void setNormalScreens(Boolean normalScreens) {
		mNormalScreens = getConstantBoolean(normalScreens);
	}

	/**
	 * returns the value of the <code>largeScreens</code> attribute or null if
	 * not present.
	 */
	public Boolean getLargeScreens() {
		return mLargeScreens;
	}

	void setLargeScreens(Boolean largeScreens) {
		mLargeScreens = getConstantBoolean(largeScreens);
	}

	/**
	 * Returns either {@link Boolean#TRUE} or {@link Boolean#FALSE} based on the
	 * value of the given Boolean object.
	 */
	private Boolean getConstantBoolean(Boolean v) {
		if (v != null) {
			if (v.equals(Boolean.TRUE)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}

		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SupportsScreens) {
			SupportsScreens support = (SupportsScreens) obj;
			// since all the fields are guaranteed to be either Boolean.TRUE or
			// Boolean.FALSE
			// (or null), we can simply check they are identical and not bother
			// with
			// calling equals (which would require to check != null.
			// see #getConstanntBoolean(Boolean)
			return mResizeable == support.mResizeable && mAnyDensity == support.mAnyDensity && mSmallScreens == support.mSmallScreens
					&& mNormalScreens == support.mNormalScreens && mLargeScreens == support.mLargeScreens;
		}

		return false;
	}

	/**
	 * Returns true if the two instances support the same screen sizes. This is
	 * similar to {@link #equals(Object)} except that it ignores the values of
	 * {@link #getAnyDensity()} and {@link #getResizeable()}.
	 * 
	 * @param support
	 *            the other instance to compare to.
	 * @return true if the two instances support the same screen sizes.
	 */
	public boolean hasSameScreenSupportAs(SupportsScreens support) {
		// since all the fields are guaranteed to be either Boolean.TRUE or
		// Boolean.FALSE
		// (or null), we can simply check they are identical and not bother with
		// calling equals (which would require to check != null.
		// see #getConstanntBoolean(Boolean)

		// This only checks that matter here are the screen sizes. resizeable
		// and anyDensity
		// are not checked.
		return mSmallScreens == support.mSmallScreens && mNormalScreens == support.mNormalScreens && mLargeScreens == support.mLargeScreens;
	}

	/**
	 * Returns true if the two instances have strictly different screen size
	 * support. This means that there is no screen size that they both support.
	 * 
	 * @param support
	 *            the other instance to compare to.
	 * @return true if they are stricly different.
	 */
	public boolean hasStrictlyDifferentScreenSupportAs(SupportsScreens support) {
		// since all the fields are guaranteed to be either Boolean.TRUE or
		// Boolean.FALSE
		// (or null), we can simply check they are identical and not bother with
		// calling equals (which would require to check != null.
		// see #getConstanntBoolean(Boolean)

		// This only checks that matter here are the screen sizes. resizeable
		// and anyDensity
		// are not checked.
		return (mSmallScreens != Boolean.TRUE || support.mSmallScreens != Boolean.TRUE)
				&& (mNormalScreens != Boolean.TRUE || support.mNormalScreens != Boolean.TRUE)
				&& (mLargeScreens != Boolean.TRUE || support.mLargeScreens != Boolean.TRUE);
	}

	/**
	 * Comparison of 2 Supports-screens. This only uses screen sizes (ignores
	 * resizeable and anyDensity), and considers that
	 * {@link #hasStrictlyDifferentScreenSupportAs(SupportsScreens)} returns
	 * true and {@link #overlapWith(SupportsScreens)} returns false.
	 * 
	 * @throws IllegalArgumentException
	 *             if the two instanced are not strictly different or overlap
	 *             each other
	 * @see #hasStrictlyDifferentScreenSupportAs(SupportsScreens)
	 * @see #overlapWith(SupportsScreens)
	 */
	public int compareScreenSizesWith(SupportsScreens o) {
		if (hasStrictlyDifferentScreenSupportAs(o) == false) {
			throw new IllegalArgumentException("The two instances are not strictly different.");
		}
		if (overlapWith(o)) {
			throw new IllegalArgumentException("The two instances overlap each other.");
		}

		int comp = mLargeScreens.compareTo(o.mLargeScreens);
		if (comp != 0)
			return comp;

		comp = mNormalScreens.compareTo(o.mNormalScreens);
		if (comp != 0)
			return comp;

		comp = mSmallScreens.compareTo(o.mSmallScreens);
		if (comp != 0)
			return comp;

		return 0;
	}

	/**
	 * Returns a string encoding of the content of the instance. This string can
	 * be used to instantiate a {@link SupportsScreens} object through
	 * {@link #SupportsScreens(String)}.
	 */
	public String getEncodedValues() {
		return String.format("%1$s|%2$s|%3$s|%4$s|%5$s", mAnyDensity, mResizeable, mSmallScreens, mNormalScreens, mLargeScreens);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		boolean alreadyOutputSomething = false;

		if (Boolean.TRUE.equals(mSmallScreens)) {
			alreadyOutputSomething = true;
			sb.append("small");
		}

		if (Boolean.TRUE.equals(mNormalScreens)) {
			if (alreadyOutputSomething) {
				sb.append(", ");
			}
			alreadyOutputSomething = true;
			sb.append("normal");
		}

		if (Boolean.TRUE.equals(mLargeScreens)) {
			if (alreadyOutputSomething) {
				sb.append(", ");
			}
			alreadyOutputSomething = true;
			sb.append("large");
		}

		if (alreadyOutputSomething == false) {
			sb.append("<none>");
		}

		return sb.toString();
	}

	/**
	 * Returns true if the two instance overlap with each other. This can happen
	 * if one instances supports a size, when the other instance doesn't while
	 * supporting a size above and a size below.
	 * 
	 * @param otherSS
	 *            the other supports-screens to compare to.
	 */
	public boolean overlapWith(SupportsScreens otherSS) {
		if (mSmallScreens == null || mNormalScreens == null || mLargeScreens == null || otherSS.mSmallScreens == null || otherSS.mNormalScreens == null
				|| otherSS.mLargeScreens == null) {
			throw new IllegalArgumentException("Some screen sizes Boolean are not initialized");
		}

		if (mSmallScreens == Boolean.TRUE && mNormalScreens == Boolean.FALSE && mLargeScreens == Boolean.TRUE) {
			return otherSS.mNormalScreens == Boolean.TRUE;
		}

		if (otherSS.mSmallScreens == Boolean.TRUE && otherSS.mNormalScreens == Boolean.FALSE && otherSS.mLargeScreens == Boolean.TRUE) {
			return mNormalScreens == Boolean.TRUE;
		}

		return false;
	}
}