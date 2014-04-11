package axmlprinter;
/**
 * Touch screen enum.
 * <p/>
 * This is used in the manifest in the uses-configuration node and in the
 * resource folder names.
 */
public enum TouchScreen implements ResourceEnum {
	NOTOUCH("notouch", "No Touch", "No-touch screen"), //$NON-NLS-1$
	STYLUS("stylus", "Stylus", "Stylus-based touchscreen"), //$NON-NLS-1$
	FINGER("finger", "Finger", "Finger-based touchscreen"); //$NON-NLS-1$

	private final String mValue;
	private final String mShortDisplayValue;
	private final String mLongDisplayValue;

	private TouchScreen(String value, String displayValue, String longDisplayValue) {
		mValue = value;
		mShortDisplayValue = displayValue;
		mLongDisplayValue = longDisplayValue;
	}

	/**
	 * Returns the enum for matching the provided qualifier value.
	 * 
	 * @param value
	 *            The qualifier value.
	 * @return the enum for the qualifier value or null if no matching was
	 *         found.
	 */
	public static TouchScreen getEnum(String value) {
		for (TouchScreen orient : values()) {
			if (orient.mValue.equals(value)) {
				return orient;
			}
		}

		return null;
	}

	public String getResourceValue() {
		return mValue;
	}

	public String getShortDisplayValue() {
		return mShortDisplayValue;
	}

	public String getLongDisplayValue() {
		return mLongDisplayValue;
	}

	public static int getIndex(TouchScreen touch) {
		int i = 0;
		for (TouchScreen t : values()) {
			if (t == touch) {
				return i;
			}

			i++;
		}

		return -1;
	}

	public static TouchScreen getByIndex(int index) {
		int i = 0;
		for (TouchScreen value : values()) {
			if (i == index) {
				return value;
			}
			i++;
		}

		return null;
	}

	public boolean isFakeValue() {
		return false;
	}

	public boolean isValidValueForDevice() {
		return true;
	}

}
