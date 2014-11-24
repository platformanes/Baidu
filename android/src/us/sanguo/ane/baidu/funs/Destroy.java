package us.sanguo.ane.baidu.funs;

import us.sanguo.ane.baidu.context.IABCont;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.baidu.gamesdk.BDGameSDK;

public class Destroy extends IABFunctionBase {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------destroy-------");
		try
		{
			BDGameSDK.destroy();
		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
	}

	@Override
	public String getTag() {
		return IABCont.FUNS.DESTROY.toString();
	}
}
