package baton.studentinformationinputsystem;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class NativePlaceService extends Service {
    public NativePlaceService() {

    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        String code = intent.getStringExtra("code");//获取六位编码
        String nativePlace = GetNativePlace(code);//获取籍贯
        BasicInfoActivity.setNative_place_edit(nativePlace);//更新UI
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     * 通过身份证号编码获得籍贯
     * @param number 籍贯六位编码
     * @return 籍贯的完整字符串文本
     */
    public String GetNativePlace(String number)
    {
        String content = readAssetsTxt(this,"native_place");
        int begin = content.indexOf(number)+7;
        if(begin!=6)
        {
            int end = content.indexOf(" ",begin);
            return content.substring(begin,end);
        }else
            return "null";
    }
    /**
     * 从文件读取籍贯编码与实际地址对应表
     * @param context 上下文
     * @param fileName 文件地址
     * @return 文件内容
     */
    public static String readAssetsTxt(Context context, String fileName){
        try {
            InputStream is = context.getAssets().open(fileName+".txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }
}
