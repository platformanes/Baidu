package us.sanguo.ane.baidu.funs;

import us.sanguo.ane.baidu.context.IABCont;
import us.sanguo.ane.baidu.utils.Constant;

import android.app.Activity;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.baidu.gamesdk.ActivityAdPage;
import com.baidu.gamesdk.ActivityAnalytics;
import com.baidu.gamesdk.BDGameSDK;
import com.baidu.gamesdk.BDGameSDKSetting;
import com.baidu.gamesdk.IResponse;
import com.baidu.gamesdk.BDGameSDKSetting.Domain;
import com.baidu.gamesdk.BDGameSDKSetting.Orientation;
import com.baidu.gamesdk.ResultCode;

public class Init extends IABFunctionBase {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------init-------");
		try
		{
			Constant.appId_DkDemo = $args[0].getAsString();
			Constant.appKey_DkDemo = $args[1].getAsString();
			Constant.rate_DkDemo = $args[2].getAsString();
			Constant.gamebiName_DkDemo = $args[3].getAsString();

	        initApp(getActivity());
	        
	        Constant.mActivityAnalytics = new ActivityAnalytics(getActivity());
	        Constant.mActivityAdPage = new ActivityAdPage(getActivity(), null);
		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
	}
	
	private void initApp(Activity $context)
    {
		BDGameSDKSetting mBDGameSDKSetting = new BDGameSDKSetting();
		mBDGameSDKSetting.setAppID(Integer.parseInt(Constant.appId_DkDemo));//APPID设置
		mBDGameSDKSetting.setAppKey(Constant.appKey_DkDemo);//APPKEY设置
		mBDGameSDKSetting.setDomain(Domain.RELEASE);//设置为正式模式
		mBDGameSDKSetting.setOrientation(Orientation.PORTRAIT);//Utils.getOrientation($context)
		 
		BDGameSDK.init($context, mBDGameSDKSetting, new IResponse<Void>(){

			@Override
			public void onResponse(int resultCode, String resultDesc,
					Void extraData) {
				switch(resultCode){
				case ResultCode.INIT_SUCCESS:
					break;
				case ResultCode.INIT_FAIL:
				default:
					//初始化失败
				}
			}
		});
    }
	
	@Override
	public String getTag() {
		return IABCont.FUNS.INIT.toString();
	}
}
