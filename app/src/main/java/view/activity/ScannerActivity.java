package view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bawei.wangwenjie.subjectdemo.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
/**
*
* 类描述： 扫描类
* 创建人：wangwenjie
* 创建时间：2017/7/20/0020
* @version
*
*/
public class ScannerActivity extends AppCompatActivity implements QRCodeView.Delegate{

    private static final int REQUEST_CODE_CAMERA = 999;
    private static final String TAG = ScannerActivity.class.getSimpleName();

    private QRCodeView mQRCodeView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);

        Log.e(TAG, "扫码:01");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.startSpot();

        Log.e(TAG, "扫码:02");
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();

        Log.e(TAG, "扫码:03");
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
        Log.e(TAG, "扫码:04");
    }

    //震动器
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Log.e(TAG, "扫码:05");
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();//震动
        mQRCodeView.stopSpot();

        if (!TextUtils.isEmpty(result)) {
            mQRCodeView.stopCamera();
            mQRCodeView.onDestroy();
            Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
            intent.putExtra("url", result);
            //intent.setData(Uri.parse(result));
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "链接无效,请重新扫描", Toast.LENGTH_SHORT).show();
            mQRCodeView.startSpot();
        }
        Log.e(TAG, "扫码:06");
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "无相机权限,打开相机出错");

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
        Log.e(TAG, "扫码:07");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_spot:
                mQRCodeView.startSpot();
                break;
            case R.id.stop_spot:
                mQRCodeView.stopSpot();
                break;
            case R.id.open_flashlight:
                mQRCodeView.openFlashlight();
                break;
            case R.id.close_flashlight:
                mQRCodeView.closeFlashlight();
                break;
            case R.id.rl_back:
                //onDestroy();
                mQRCodeView.stopCamera();
                mQRCodeView.onDestroy();
                finish();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            mQRCodeView.startCamera();
            mQRCodeView.startSpot();
        }
    }
}
