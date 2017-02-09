package dennis.testdemos.a02_permission;

import android.content.Intent;
public abstract class BaseManager implements PermissionDemoActivity.IManager {
	protected final PermissionDemoActivity.ICommunicator communicator;

	protected BaseManager(PermissionDemoActivity.ICommunicator communicator) {this.communicator = communicator;}

	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {}
}