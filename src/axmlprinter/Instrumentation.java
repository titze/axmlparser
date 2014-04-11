package axmlprinter;
import java.io.Serializable;

/**
 * Instrumentation info obtained from manifest
 */
public class Instrumentation implements Serializable {
	private final String mName;
	private final String mTargetPackage;

	Instrumentation(String name, String targetPackage) {
		mName = name;
		mTargetPackage = targetPackage;
	}

	/**
	 * Returns the fully qualified instrumentation class name
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Returns the Android app package that is the target of this
	 * instrumentation
	 */
	public String getTargetPackage() {
		return mTargetPackage;
	}
}