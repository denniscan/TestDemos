package dennis.testdemos.a02_permission;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dennis.testdemos.R;
import dennis.testdemos.a02_permission.manager.CameraManager;
import dennis.testdemos.a02_permission.manager.ContactsManager;
import dennis.testdemos.a02_permission.manager.ImeiManager;
import dennis.testdemos.utils.constant.Constant;
import dennis.utils.base.ui.activity.BaseActivity;
/**
 * 权限Demo
 * Created by Dennis Can on 2017/2/9.
 */
public class PermissionDemoActivity extends BaseActivity {
	public static final int REQUEST_CODE_TAKE_PHOTO = 1;
	public static final int PERMISSION_REQUEST_CODE_CAMERA = 101;
	public static final int PERMISSION_REQUEST_CODE_PHONE_STATE = 102;
	public static final int PERMISSION_REQUEST_CODE_READ_CONTACTS = 103;

	private TextView sysVersionCodeTV, sysVersionNameTV;
	private Button cameraBtn, imeiBtn, contactsBtn;

	private IManager cameraManager, imeiManager, contactsManager;

	@Override protected int getLayoutId() {return R.layout.a02_activity_main;}

	@Override protected void assignViews() {
		sysVersionCodeTV = findView(R.id.sysVersionCodeTV);
		sysVersionNameTV = findView(R.id.sysVersionNameTV);
		cameraBtn = findView(R.id.cameraBtn);
		imeiBtn = findView(R.id.imeiBtn);
		contactsBtn = findView(R.id.contactsBtn);
	}

	@Override protected void onViewsReady() {
		int sdkInt = Build.VERSION.SDK_INT;
		SparseArray<String> infoArray = Constant.getAipInfoArray();
		sysVersionCodeTV.setText("API " + sdkInt);
		sysVersionNameTV.setText(infoArray.get(sdkInt));

		cameraManager = new CameraManager(communicator);
		cameraBtn.setOnClickListener(this);

		imeiManager = new ImeiManager(communicator);
		imeiBtn.setOnClickListener(this);

		contactsManager = new ContactsManager(communicator);
		contactsBtn.setOnClickListener(this);
	}

	@Override protected void loadRawData() {}

	@Override public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.cameraBtn:
			cameraManager.onButtonClick();
			break;
		case R.id.imeiBtn:
			imeiManager.onButtonClick();
			break;
		case R.id.contactsBtn:
			contactsManager.onButtonClick();
			break;
		}
	}

	@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
			@NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		switch (requestCode) {
		case PERMISSION_REQUEST_CODE_CAMERA:
			cameraManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
			break;
		case PERMISSION_REQUEST_CODE_PHONE_STATE:
			imeiManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
			break;
		case PERMISSION_REQUEST_CODE_READ_CONTACTS:
			contactsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
			break;
		}
	}

	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_CODE_TAKE_PHOTO:
			cameraManager.onActivityResult(requestCode, resultCode, data);
			break;
		}
	}

	// <-------- Manager -------->
	public interface IManager {
		void onButtonClick();

		void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

		void onActivityResult(int requestCode, int resultCode, Intent data);
	}

	private ICommunicator communicator = new ICommunicator() {
		@Override public Activity getActivity() { return activity; }

		@RequiresApi(api = Build.VERSION_CODES.M) @Override public void requestPermissions(
				@NonNull String[] permissions, int requestCode) {
			activity.requestPermissions(permissions, requestCode);
		}

		@Override public void startActivityForResult(Intent intent, int requestCode) {
			activity.startActivityForResult(intent, requestCode);
		}

		@Override public ContentResolver getContentResolver() { return activity.getContentResolver(); }
	};

	public interface ICommunicator {
		Activity getActivity();

		@RequiresApi(api = Build.VERSION_CODES.M) void requestPermissions(@NonNull String[] permissions,
				int requestCode);

		void startActivityForResult(Intent intent, int requestCode);

		ContentResolver getContentResolver();
	}
}
