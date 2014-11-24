package us.sanguo.ane.baidu.context;

import java.util.HashMap;
import java.util.Map;

import us.sanguo.ane.baidu.funs.Destroy;
import us.sanguo.ane.baidu.funs.Init;
import us.sanguo.ane.baidu.funs.Login;
import us.sanguo.ane.baidu.funs.Pause;
import us.sanguo.ane.baidu.funs.Payment;
import us.sanguo.ane.baidu.funs.Resume;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class IABCont extends FREContext{
	public static final String TAG = "us.sanguo.ane.baidu.context.IABCont";

	/**
	 * 记录所有支持的FREFunction
	 */
	public static enum FUNS {
		INIT, LOGIN, PAYMENT, DESTROY, PAUSE, RESUME
	};

	static private IABCont _instance;

	static public IABCont getInstance() {
		return _instance;
	}

	@Override
	public void dispose() {
		String __info = "dispose";
		Log.d(TAG, __info);
		dispatchStatusEventAsync(__info, getTag());
	}

	private void dispatch(String $code, String $level) {
		Log.d($code, $level);
		dispatchStatusEventAsync($code, $level);
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		_instance = this;
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		functions.put(FUNS.INIT.toString(), new Init());
		functions.put(FUNS.LOGIN.toString(), new Login());
		functions.put(FUNS.PAYMENT.toString(), new Payment());
		functions.put(FUNS.DESTROY.toString(), new Destroy());
		functions.put(FUNS.PAUSE.toString(), new Pause());
		functions.put(FUNS.RESUME.toString(), new Resume());
		return functions;
	}

	public void destroy() {
		dispatch(TAG, "Destroying helper.");
	}

	public String getTag() {
		return TAG;
	}
}
