package us.sanguo.ane.baidu.funs;

import org.json.JSONObject;

import us.sanguo.ane.baidu.context.IABCont;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.baidu.gamesdk.BDGameSDK;
import com.baidu.gamesdk.IResponse;
import com.baidu.gamesdk.ResultCode;

public class Login extends IABFunctionBase {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------login-------");
		try
		{
			BDGameSDK.login(new IResponse<Void>() {
				@Override
				public void onResponse(int resultCode, String resultDesc, Void extraData) {
					String hint = "";
					switch(resultCode){
					case ResultCode.LOGIN_SUCCESS:
						hint = "登录成功";
						JSONObject __json = new JSONObject();
						try {
							__json.put("state_code", 1021);
							__json.put("user_id", BDGameSDK.getLoginUid());
							__json.put("user_sessionid", BDGameSDK.getLoginAccessToken());
						} catch (Exception e) {
							e.printStackTrace();
						}
		    			dispatch(__json.toString(), getTag());
//	                    getActivity().finish();
						break; 
					case ResultCode.LOGIN_CANCEL:
						hint = "取消登录";
						break;
					case ResultCode.LOGIN_FAIL:
					default:	
						hint = "登录失败";	 
					}
					Log.d(getTag(), "---------"+ hint + "-------");
				}
			});
		}
		catch (Exception $e)
		{
			dispatch($e.getMessage());
		}
		return null;
	}
	
	@Override
	public String getTag() {
		return IABCont.FUNS.LOGIN.toString();
	}
}
