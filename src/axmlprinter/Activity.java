package axmlprinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity info obtained from the manifest.
 */
public final class Activity implements Serializable {
	private final String mName;
	private int mIsExported = -1; // -1 unknown yet, 0 false, 1 true
	private boolean mHasAction = false;
	private boolean mHasMainAction = false;
	private boolean mHasLauncherCategory = false;
	private List<IntentFilter> mIntentFilter = new ArrayList<IntentFilter>();
	private IntentFilter lastIntentFilter;

	public Activity(String name) {
		mName = name;
	}

	public Activity(String name, boolean exported) {
		mName = name;
		if (exported)
			mIsExported = 1;
		else
			mIsExported = 0;
	}

	public String getName() {
		return mName;
	}

	public void setExported(boolean exp) {
		if (exp)
			mIsExported = 1;
		else
			mIsExported = 0;
	}

	public boolean isExported() {
		return (mIsExported == 1);
	}

	public boolean isNotExported() {
		return (mIsExported == 0);
	}

	public boolean hasAction() {
		return mHasAction;
	}

	public boolean isHomeActivity() {
		return mHasMainAction && mHasLauncherCategory;
	}

	void setHasAction(boolean hasAction) {
		mHasAction = hasAction;
	}

	/**
	 * If the activity doesn't yet have a filter set for the launcher, this
	 * resets both flags. This is to handle multiple intent-filters where one
	 * could have the valid action, and another one of the valid category.
	 */
	void resetIntentFilter() {
		if (isHomeActivity() == false) {
			mHasMainAction = mHasLauncherCategory = false;
		}
	}

	void setHasMainAction(boolean hasMainAction) {
		mHasMainAction = hasMainAction;
	}

	void setHasLauncherCategory(boolean hasLauncherCategory) {
		mHasLauncherCategory = hasLauncherCategory;
	}

	public void addIntentFilter() {
		lastIntentFilter = new IntentFilter();
		mIntentFilter.add(lastIntentFilter);
	}

	public IntentFilter getLastIntentFilter() {
		return lastIntentFilter;
	}

	public List<IntentFilter> getIntentFilters() {
		return mIntentFilter;
	}
}