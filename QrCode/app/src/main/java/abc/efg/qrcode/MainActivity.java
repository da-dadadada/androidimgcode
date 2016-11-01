package abc.efg.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lht.qrcode.QRCodeFacade;
import com.lht.qrcode.decode.QRCodeDecoder;
import com.lht.qrcode.generate.QRCodeGenerator;
import com.lht.qrcode.scan.IScanResultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    private EditText editText;

    private Button btnGenerate;

    private ImageView imgShowQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.et);

        btnGenerate = (Button) findViewById(R.id.btn_generate);

        imgShowQrcode = (ImageView) findViewById(R.id.img_gen_qr);

        final QRCodeFacade qrCodeFacade = new QRCodeFacade();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                Bitmap qrCode = QRCodeGenerator.generate(str);
                imgShowQrcode.setImageBitmap(qrCode);
            }
        });

        findViewById(R.id.btn_decode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                File qr = getQrcode();
//                if (!qr.exists()) {
//                    Log.e("lmsg","not found:"+qr.getAbsolutePath());
//                    return;
//                }
//                try {
//                    InputStream input = new FileInputStream(qr);
//                    byte[] byt = new byte[input.available()];
//                    input.read(byt);
//
//                    String s = QRCodeDecoder.decode(convertBytes2Bimap(byt));
//                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, 1001);

            }
        });

        findViewById(R.id.btn_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrCodeFacade.scan(MainActivity.this, new ScanHandler());
            }
        });


    }

    private File getQrcode() {
        File root = Environment.getExternalStorageDirectory();
        File qr = new File(root.getAbsolutePath() + "/Qrcode/Qrcode.jpg");
        return qr;
    }

    private Bitmap convertBytes2Bimap(byte[] b) {
        if (b.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1001:
                if (resultCode == RESULT_OK) {
                    String path = FileUtils.queryImageByUri(data.getData(), getContentResolver());
                    if (!TextUtils.isEmpty(path)) {
                        try {
                            InputStream input = new FileInputStream(new File(path));
                            byte[] byt = new byte[input.available()];
                            input.read(byt);

                            String s = QRCodeDecoder.decode(convertBytes2Bimap(byt));
                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                } else {
                    //相册 未获取图片
                    Log.e("lmsg", "select: fail");
                }
                break;
            default:
                break;
        }
    }

    class ScanHandler implements IScanResultHandler {

        private static final String tag = "defaulthandler";

        @Override
        public void onSuccess(String result) {
            Log.d(tag, "scan result is:\r\n"+result);
            Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure() {
            Log.d(tag, "scan failure,maybe not support encoding type");
        }

        @Override
        public void onTimeout() {
            Log.d(tag, "scan timeout,default time is 30s,have you set a very short one?");
        }

        @Override
        public void onCancel() {
            Log.d(tag, "scan canceled by user");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
