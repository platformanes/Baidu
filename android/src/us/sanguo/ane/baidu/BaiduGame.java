package us.sanguo.ane.baidu;

import us.sanguo.ane.baidu.context.IABCont;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class BaiduGame implements FREExtension{
public static final String TAG = "us.sanguo.ane.baidu.BaiduGame";

	@Override
	public FREContext createContext(String $type)
	{
		//if(ANEContext.IAB.toString().equals($type)) return new IABCont();
		return new IABCont();
	}

	@Override
	public void initialize()
	{
		Log.i(TAG, "BaiduGame initialize");
	}

	@Override
	public void dispose()
	{
		Log.i(TAG, "BaiduGame dispose");
	}
}
