package axmlprinter;
import java.io.Serializable;


/**
 * Class representing a <code>uses-library</code> node in the manifest.
 */
public final class UsesLibrary implements Serializable {
	String mName;
	Boolean mRequired = Boolean.TRUE; // default is true even if missing

	public String getName() {
		return mName;
	}

	public Boolean getRequired() {
		return mRequired;
	}
}