package dennis.testdemos.a02_permission.manager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import dennis.testdemos.a02_permission.BaseManager;
import dennis.testdemos.a02_permission.PermissionDemoActivity;
import dennis.testdemos.utils.app.MyApp;
import dennis.utils.A;
/**
 * IMEI权限Demo
 * Created by Dennis Can on 2016/12/16.
 */
public class ImeiManager extends BaseManager {
	public ImeiManager(PermissionDemoActivity.ICommunicator communicator) { super(communicator); }

	@Override public void onButtonClick() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
		    ContextCompat.checkSelfPermission(communicator.getActivity(), Manifest.permission.READ_PHONE_STATE) !=
		    PackageManager.PERMISSION_GRANTED)
			communicator.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
					PermissionDemoActivity.PERMISSION_REQUEST_CODE_PHONE_STATE);
		else
			showImeiInfo();
	}

	@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
			@NonNull int[] grantResults) {
		if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
			showImeiInfo();
	}

	private void showImeiInfo() {
		TelephonyManager tm = (TelephonyManager) communicator.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		String info = "DeviceId: " + deviceId;
		A.log(info);
		MyApp.showToast(info);
	}
}
