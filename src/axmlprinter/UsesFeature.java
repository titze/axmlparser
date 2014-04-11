package axmlprinter;
import java.io.Serializable;

/**
 * Class representing a <code>uses-feature</code> node in the manifest.
 */
public final class UsesFeature implements Serializable {
	String mName;
	int mGlEsVersion = 0;
	Boolean mRequired = Boolean.TRUE; // default is true even if missing

	public String getName() {
		return mName;
	}

	/**
	 * Returns the value of the glEsVersion attribute, or 0 if the attribute was
	 * not present.
	 */
	public int getGlEsVersion() {
		return mGlEsVersion;
	}

	public Boolean getRequired() {
		return mRequired;
	}
}