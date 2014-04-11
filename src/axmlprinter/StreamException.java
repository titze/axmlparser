package axmlprinter;
/**
 * Exception thrown when {@link IAbstractFile#getContents()} fails.
 */
public class StreamException extends Exception {
	private static final long serialVersionUID = 1L;

	public StreamException(Exception e) {
		super(e);
	}
}