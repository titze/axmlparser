package axmlprinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * service/receiver/provider nodes.
 */
public final class SRPNode implements Serializable {
	public final String mName;
	final boolean mIsExported;
	final boolean mIsEnabled;
	List<String> mActions = new ArrayList<String>();

	/**
	 * Not to be made final. For some reason,
	 * {@link AndroidManifestParser.ManifestHandler#startElement(String, String, String, Attributes)
	 * AndroidManifestParser.ManifestHandler.startElement(...)} calls
	 * {@link SRPNode#setCategory(String) setCategory()}. TODO: Why is that call
	 * made?
	 */
	private String mCategory = null;
	public final String mPermission;
	private IntentFilter lastIntentFilter;
	private List<IntentFilter> mIntentFilter = new ArrayList<IntentFilter>();

	/**
	 * 
	 * @param mName
	 *            Name of the Service/BroadcastReceiver/Provider
	 * @param mIsExported
	 * @param mIsEnabled
	 * @param mPermission
	 *            Permission the S/R/P is protected with, {@link null} if no
	 *            permission is set
	 * @param mCategory
	 *            One of {@link SdkConstants#CLASS_SERVICE
	 *            SdkConstants.CLASS_SERVICE},
	 *            {@link SdkConstants#CLASS_BROADCASTRECEIVER
	 *            SdkConstants.CLASS_BROADCASTRECEIVER}, or
	 *            {@link SdkConstants#CLASS_PROVIDER
	 *            SdkConstants.CLASS_PROVIDER}
	 */
	public SRPNode(String mName, boolean mIsExported, boolean mIsEnabled, String mPermission, String mCategory) {
		this.mName = mName;
		this.mIsExported = mIsExported;
		this.mIsEnabled = mIsEnabled;
		this.mPermission = mPermission;
		this.mCategory = mCategory;
	}

	/**
	 * Returns the permission the SRPNode is protected with.
	 * 
	 * @return The permission, if set, or null if none is set.
	 */
	public String getPermission() {
		return mPermission;
	}

	public List<String> getActions() {
		return mActions;
	}

	public void addAction(String action) {
		this.mActions.add(action);
	}

	public void setCategory(String mCategory) {
		this.mCategory = mCategory;
	}

	public String getCategory() {
		return mCategory;
	}

	public String getName() {
		return mName;
	}

	public boolean isExported() {
		return mIsExported;
	}

	public boolean isEnabled() {
		return mIsEnabled;
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