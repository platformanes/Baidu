package us.sanguo.ane.baidu.funs;

import us.sanguo.ane.baidu.context.IABCont;
import us.sanguo.ane.baidu.utils.Constant;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;

public class Pause extends IABFunctionBase {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		Log.d(getTag(), "---------pause-------");
		try
		{
			Constant.mActivityAnalytics.onPause();
		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
    }

	@Override
	public String getTag() {
		return IABCont.FUNS.PAUSE.toString();
	}
}
