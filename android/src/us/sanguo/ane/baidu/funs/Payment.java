package us.sanguo.ane.baidu.funs;

import us.sanguo.ane.baidu.context.IABCont;
import us.sanguo.ane.baidu.utils.Constant;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.baidu.gamesdk.BDGameSDK;
import com.baidu.gamesdk.IResponse;
import com.baidu.gamesdk.ResultCode;
import com.baidu.platformsdk.PayOrderInfo;

public class Payment extends IABFunctionBase {

	@Override
	public FREObject call(FREContext $context, FREObject[] $args)
	{
		_context = $context;
		Log.d(getTag(), "---------payment-------");
		try
		{
			String amount = $args[0].getAsString(); 	//金额
			String strOrderId = $args[1].getAsString(); //订单号
			String strPayName = $args[2].getAsString(); //商品名称
			
			PayOrderInfo payOrderInfo = new PayOrderInfo();
			payOrderInfo.setCooperatorOrderSerial(strOrderId); //CP订单号 
			payOrderInfo.setProductName(strPayName); //商品名称 
			payOrderInfo.setTotalPriceCent(Long.parseLong(amount));//以分为单位，long类型 
			payOrderInfo.setRatio(Integer.parseInt(Constant.rate_DkDemo)); //兑换比例，此时不生效 
			payOrderInfo.setExtInfo(""); //该字段在支付通知中原样返回,不超过500个字符
			
			BDGameSDK.pay(payOrderInfo, null, 
				new IResponse<PayOrderInfo>(){
					@Override
					public void onResponse(int resultCode, String resultDesc,
							PayOrderInfo extraData) {
						String resultStr = "";
						switch(resultCode){
						case ResultCode.PAY_SUCCESS://支付成功
							resultStr = "支付成功:" + resultDesc;
							break;
						case ResultCode.PAY_CANCEL://订单支付取消
							resultStr = "取消支付";
							break;	
						case ResultCode.PAY_FAIL://订单支付失败
							resultStr = "支付失败：" + resultDesc;
							break;	
						case ResultCode.PAY_SUBMIT_ORDER://订单已经提交，支付结果未知（比如：已经请求了，但是查询超时）
							resultStr = "订单已经提交，支付结果未知";
							break;	
						}
						Log.d(getTag(), "---------"+ resultStr + "-------");
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
		return IABCont.FUNS.PAYMENT.toString();
	}

}