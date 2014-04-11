package axmlprinter;
import java.io.Serializable;

/**
 * Class representing the <code>uses-configuration</code> node in the manifest.
 */
public final class UsesConfiguration implements Serializable {
	Boolean mReqFiveWayNav;
	Boolean mReqHardKeyboard;
	Keyboard mReqKeyboardType;
	TouchScreen mReqTouchScreen;
	Navigation mReqNavigation;

	/**
	 * returns the value of the <code>reqFiveWayNav</code> attribute or null if
	 * not present.
	 */
	public Boolean getReqFiveWayNav() {
		return mReqFiveWayNav;
	}

	/**
	 * returns the value of the <code>reqNavigation</code> attribute or null if
	 * not present.
	 */
	public Navigation getReqNavigation() {
		return mReqNavigation;
	}

	/**
	 * returns the value of the <code>reqHardKeyboard</code> attribute or null
	 * if not present.
	 */
	public Boolean getReqHardKeyboard() {
		return mReqHardKeyboard;
	}

	/**
	 * returns the value of the <code>reqKeyboardType</code> attribute or null
	 * if not present.
	 */
	public Keyboard getReqKeyboardType() {
		return mReqKeyboardType;
	}

	/**
	 * returns the value of the <code>reqTouchScreen</code> attribute or null if
	 * not present.
	 */
	public TouchScreen getReqTouchScreen() {
		return mReqTouchScreen;
	}
}