package dennis.testdemos.a02_permission.manager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import dennis.testdemos.R;
import dennis.testdemos.a02_permission.BaseManager;
import dennis.testdemos.a02_permission.PermissionDemoActivity;
import dennis.testdemos.utils.app.MyApp;

/**
 * 照相权限Demo
 * Created by Dennis Can on 2016/12/16.
 */
public class CameraManager extends BaseManager {
	private File cacheImageFile;

	public CameraManager(PermissionDemoActivity.ICommunicator communicator) { super(communicator); }

	@Override public void onButtonClick() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M // 6.0系统异步获取权限
		    && ContextCompat.checkSelfPermission(communicator.getActivity(), Manifest.permission.CAMERA) !=
		       PackageManager.PERMISSION_GRANTED)
			communicator.requestPermissions(new String[]{Manifest.permission.CAMERA},
					PermissionDemoActivity.PERMISSION_REQUEST_CODE_CAMERA);
		else
			startTakePhoto();
	}

	@Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
			@NonNull int[] grantResults) {
		if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
			startTakePhoto();
	}

	private void startTakePhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cacheImageFile = new File(MyApp.CACHE_IMAGE_PATH,
				"image_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + "_" +
				(System.currentTimeMillis() % 1000) + ".jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cacheImageFile));
		communicator.startActivityForResult(intent, PermissionDemoActivity.REQUEST_CODE_TAKE_PHOTO);
	}

	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		ImageView imageView = new ImageView(communicator.getActivity());
		imageView.setImageURI(Uri.fromFile(cacheImageFile));
		AlertDialog alertDialog =
				new AlertDialog.Builder(communicator.getActivity(), R.style.MyAlertDialog).setView(imageView).create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
		MyApp.showToast("照片已保存到：" + cacheImageFile.getAbsolutePath());
	}
}
