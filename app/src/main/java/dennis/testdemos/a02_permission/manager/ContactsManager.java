package dennis.testdemos.a02_permission.manager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import dennis.testdemos.a02_permission.BaseManager;
import dennis.testdemos.a02_permission.PermissionDemoActivity;
import dennis.utils.A;
/**
 * 联系人权限Demo
 * Created by Dennis Can on 2017/1/13.
 */
public class ContactsManager extends BaseManager {
	public ContactsManager(PermissionDemoActivity.ICommunicator communicator) { super(communicator); }

	@Override public void onButtonClick() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
		    ContextCompat.checkSelfPermission(communicator.getActivity(), Manifest.permission.READ_CONTACTS) !=
		    PackageManager.PERMISSION_GRANTED)
			communicator.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
					PermissionDemoActivity.PERMISSION_REQUEST_CODE_READ_CONTACTS);
		else
			loadContracts();
	}

	@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
			@NonNull int[] grantResults) {
		if (requestCode == PermissionDemoActivity.PERMISSION_REQUEST_CODE_READ_CONTACTS &&
		    grantResults[0] == PackageManager.PERMISSION_GRANTED)
			loadContracts();
	}

	private void loadContracts() {
		ContentResolver contentResolver = communicator.getContentResolver();
		Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
					String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

					if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
						Cursor pCur = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
								ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
						if (pCur != null) {
							if (pCur.moveToFirst()) {
								do {
									String phone = pCur.getString(
											pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
									A.log("name: " + name + ", phone: " + phone);
								} while (pCur.moveToNext());
							}
							pCur.close();
						}
					}
				} while (cursor.moveToNext());
				cursor.close();
			}
		}
	}
}
