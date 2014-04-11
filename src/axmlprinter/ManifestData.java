package axmlprinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.Attributes;

/**
 * Class containing the manifest info obtained during the parsing.
 */
public final class ManifestData implements Serializable {

	/**
	 * Value returned by {@link #getMinSdkVersion()} when the value of the
	 * minSdkVersion attribute in the manifest is a codename and not an integer
	 * value.
	 */
	public final static int MIN_SDK_CODENAME = 0;

	/**
	 * Value returned by {@link #getGlEsVersion()} when there are no
	 * <uses-feature> node with the attribute glEsVersion set.
	 */
	public final static int GL_ES_VERSION_NOT_SET = -1;

	/** Application package */
	String mPackage;
	/** Application version Code, null if the attribute is not present. */
	Integer mVersionCode = null;
	/** List of all activities */
	final ArrayList<Activity> mActivities = new ArrayList<Activity>();
	/** Launcher activity */
	Activity mLauncherActivity = null;
	/** list of process names declared by the manifest */
	Set<String> mProcesses = null;
	/** debuggable attribute value. If null, the attribute is not present. */
	Boolean mDebuggable = null;
	/** API level requirement. if null the attribute was not present. */
	private String mMinSdkVersionString = null;
	/**
	 * API level requirement. Default is 1 even if missing. If value is a
	 * codename, then it'll be 0 instead.
	 */
	private int mMinSdkVersion = 1;
	private int mTargetSdkVersion = 0;
	/** List of all instrumentations declared by the manifest */
	final ArrayList<Instrumentation> mInstrumentations = new ArrayList<Instrumentation>();
	/** List of all libraries in use declared by the manifest */
	final ArrayList<UsesLibrary> mLibraries = new ArrayList<UsesLibrary>();
	/** List of all feature in use declared by the manifest */
	final ArrayList<UsesFeature> mFeatures = new ArrayList<UsesFeature>();

	/** hash of all Services providers receivers to SPRNODE **/
	final HashMap<String, List<SRPNode>> mSRPToNode = new HashMap<String, List<SRPNode>>();

	SupportsScreens mSupportsScreensFromManifest;
	SupportsScreens mSupportsScreensValues;
	UsesConfiguration mUsesConfiguration;

	private Set<String> mUsesPermissions = new HashSet<String>();

	private Set<String> mPermissions = new HashSet<String>();

	/**
	 * Returns the package defined in the manifest, if found.
	 * 
	 * @return The package name or null if not found.
	 */
	public String getPackage() {
		return mPackage;
	}

	/**
	 * Returns the versionCode value defined in the manifest, if found, null
	 * otherwise.
	 * 
	 * @return the versionCode or null if not found.
	 */
	public Integer getVersionCode() {
		return mVersionCode;
	}

	/**
	 * Returns the list of activities found in the manifest.
	 * 
	 * @return An array of fully qualified class names, or empty if no activity
	 *         were found.
	 */
	public Activity[] getActivities() {
		// System.out.println("I was asked for activities");
		mActivities.toArray(new Activity[mActivities.size()]);
		return mActivities.toArray(new Activity[mActivities.size()]);
	}

	/**
	 * Returns the list of activities found in the manifest containing an
	 * IntentFilter
	 * 
	 * @return An array of fully qualified class names, or empty if no activity
	 *         were found.
	 */
	public Activity[] getActivitiesWithIntentFilters() {
		List<Activity> ret = new ArrayList<Activity>();
		for (Activity act : mActivities) {
			List<IntentFilter> intentFilter = act.getIntentFilters();
			if (intentFilter.size() > 0) {
				ret.add(act);
			}
		}
		return ret.toArray(new Activity[ret.size()]);
	}

	/**
	 * Returns the name of one activity found in the manifest, that is
	 * configured to show up in the HOME screen.
	 * 
	 * @return the fully qualified name of a HOME activity or null if none were
	 *         found.
	 */
	public Activity getLauncherActivity() {
		return mLauncherActivity;
	}

	/**
	 * Returns the list of process names declared by the manifest.
	 */
	public String[] getProcesses() {
		if (mProcesses != null) {
			return mProcesses.toArray(new String[mProcesses.size()]);
		}

		return new String[0];
	}

	/**
	 * Returns the <code>debuggable</code> attribute value or null if it is not
	 * set.
	 */
	public Boolean getDebuggable() {
		return mDebuggable;
	}

	/**
	 * Returns the <code>minSdkVersion</code> attribute, or null if it's not
	 * set.
	 */
	public String getMinSdkVersionString() {
		return mMinSdkVersionString;
	}

	/**
	 * Sets the value of the <code>minSdkVersion</code> attribute.
	 * 
	 * @param minSdkVersion
	 *            the string value of the attribute in the manifest.
	 */
	public void setMinSdkVersionString(String minSdkVersion) {
		mMinSdkVersionString = minSdkVersion;
		if (mMinSdkVersionString != null) {
			try {
				mMinSdkVersion = Integer.parseInt(mMinSdkVersionString);
			} catch (NumberFormatException e) {
				mMinSdkVersion = MIN_SDK_CODENAME;
			}
		}
	}

	/**
	 * Returns the <code>minSdkVersion</code> attribute, or 0 if it's not set or
	 * is a codename.
	 * 
	 * @see #getMinSdkVersionString()
	 */
	public int getMinSdkVersion() {
		return mMinSdkVersion;
	}

	/**
	 * Sets the value of the <code>minSdkVersion</code> attribute.
	 * 
	 * @param targetSdkVersion
	 *            the string value of the attribute in the manifest.
	 */
	public void setTargetSdkVersionString(String targetSdkVersion) {
		if (targetSdkVersion != null) {
			try {
				mTargetSdkVersion = Integer.parseInt(targetSdkVersion);
			} catch (NumberFormatException e) {
				// keep the value at 0.
			}
		}
	}

	/**
	 * Returns the <code>targetSdkVersion</code> attribute, or the same value as
	 * {@link #getMinSdkVersion()} if it was not set in the manifest.
	 */
	public int getTargetSdkVersion() {
		if (mTargetSdkVersion == 0) {
			return getMinSdkVersion();
		}

		return mTargetSdkVersion;
	}

	/**
	 * Returns the list of instrumentations found in the manifest.
	 * 
	 * @return An array of {@link Instrumentation}, or empty if no
	 *         instrumentations were found.
	 */
	public Instrumentation[] getInstrumentations() {
		return mInstrumentations.toArray(new Instrumentation[mInstrumentations.size()]);
	}

	/**
	 * Returns the list of libraries in use found in the manifest.
	 * 
	 * @return An array of {@link UsesLibrary} objects, or empty if no libraries
	 *         were found.
	 */
	public UsesLibrary[] getUsesLibraries() {
		return mLibraries.toArray(new UsesLibrary[mLibraries.size()]);
	}

	/**
	 * Returns the list of features in use found in the manifest.
	 * 
	 * @return An array of {@link UsesFeature} objects, or empty if no libraries
	 *         were found.
	 */
	public UsesFeature[] getUsesFeatures() {
		return mFeatures.toArray(new UsesFeature[mFeatures.size()]);
	}

	/**
	 * Returns the glEsVersion from a <uses-feature> or
	 * {@link #GL_ES_VERSION_NOT_SET} if not set.
	 */
	public int getGlEsVersion() {
		for (UsesFeature feature : mFeatures) {
			if (feature.mGlEsVersion > 0) {
				return feature.mGlEsVersion;
			}
		}
		return GL_ES_VERSION_NOT_SET;
	}

	/**
	 * Returns the {@link SupportsScreens} object representing the
	 * <code>supports-screens</code> node, or null if the node doesn't exist at
	 * all. Some values in the {@link SupportsScreens} instance maybe null,
	 * indicating that they were not present in the manifest. To get an instance
	 * that contains the values, as seen by the Android platform when the app is
	 * running, use {@link #getSupportsScreensValues()}.
	 */
	public SupportsScreens getSupportsScreensFromManifest() {
		return mSupportsScreensFromManifest;
	}

	/**
	 * Returns an always non-null instance of {@link SupportsScreens} that's
	 * been initialized with the default values, and the values from the
	 * manifest. The default values depends on the manifest values for
	 * minSdkVersion and targetSdkVersion.
	 */
	public synchronized SupportsScreens getSupportsScreensValues() {
		if (mSupportsScreensValues == null) {
			if (mSupportsScreensFromManifest == null) {
				mSupportsScreensValues = SupportsScreens.getDefaultValues(getTargetSdkVersion());
			} else {
				// get a SupportsScreen that replace the missing values with
				// default values.
				mSupportsScreensValues = mSupportsScreensFromManifest.resolveSupportsScreensValues(getTargetSdkVersion());
			}
		}

		return mSupportsScreensValues;
	}

	/**
	 * Returns the {@link UsesConfiguration} object representing the
	 * <code>uses-configuration</code> node, or null if the node doesn't exist
	 * at all.
	 */
	public UsesConfiguration getUsesConfiguration() {
		return mUsesConfiguration;
	}

	void addProcessName(String processName) {
		if (mProcesses == null) {
			mProcesses = new TreeSet<String>();
		}

		if (processName.startsWith(":")) {
			mProcesses.add(mPackage + processName);
		} else {
			mProcesses.add(processName);
		}
	}

	/**
	 * Add a uses-permission attribute
	 * 
	 * @param attributeValue
	 */
	public void addUsesPermission(String attributeValue) {
		mUsesPermissions.add(attributeValue);

	}

	/**
	 * Returns a list of all permissions required by the app via
	 * uses-permission.
	 */
	public Set<String> getUsesPermissions() {
		return mUsesPermissions;

	}

	/**
	 * Returns a list of the permissions which are listed in the manifest
	 * 
	 * @return The set of permissions in the the manifest as a set of strings
	 */
	public Set<String> getPermissions() {
		return mPermissions;
	}

	/**
	 * Adds a new permission to the set of permissions of this app
	 * 
	 * @param mPermissions
	 *            The permission to be added.
	 */
	public void addPermissions(String mPermissions) {
		this.mPermissions.add(mPermissions);
	}

	public HashMap<String, List<SRPNode>> getSRPToNodeHash() {
		return mSRPToNode;
	}

	public List<SRPNode> getServices() {
		List<SRPNode> srvs = mSRPToNode.get(SdkConstants.CLASS_SERVICE);
		return srvs != null ? srvs : new ArrayList<SRPNode>();
	}

	public List<SRPNode> getReceivers() {
		List<SRPNode> rcvrs = mSRPToNode.get(SdkConstants.CLASS_BROADCASTRECEIVER);
		return rcvrs != null ? rcvrs : new ArrayList<SRPNode>();
	}

	public List<SRPNode> getProviders() {
		List<SRPNode> prvdrs = mSRPToNode.get(SdkConstants.CLASS_CONTENTPROVIDER);
		return prvdrs != null ? prvdrs : new ArrayList<SRPNode>();
	}

}
