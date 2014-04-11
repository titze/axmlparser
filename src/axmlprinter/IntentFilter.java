package axmlprinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class IntentFilter implements Serializable {
	private final List<String> mActions = new ArrayList<String>();
	private final List<String> mCategories = new ArrayList<String>();
	private final List<String> mDatas = new ArrayList<String>();
	private int priority = 0;

	public void addAction(String e) {
		mActions.add(e);
	}

	public void addCategory(String e) {
		mCategories.add(e);
	}

	public void addData(String e) {
		mDatas.add(e);
	}

	public List<String> getActions() {
		return mActions;
	}

	public List<String> getCategories() {
		return mCategories;
	}

	public List<String> getDatas() {
		return mDatas;
	}

	public void setPriority(int p) {
		this.priority = p;
	}

	public int getPriority() {
		return priority;
	}
}