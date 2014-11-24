package us.sanguo.ane.baidu.funs
{
import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;

import us.sanguo.ane.baidu.enum.BaiduFunction;

/**
 * 提供Android中Baidu的所有功能
 * @author zm
 * 创建日期：2014-10-30
 */
public class BaiduCont extends EventDispatcher
{
	protected var _extension:ExtensionContext;
	
	public function BaiduCont($context:ExtensionContext)
	{
		_extension = $context;
		if(!_extension) throw new TypeError('必须提供ExtensionContext实例！');
		_extension.addEventListener(StatusEvent.STATUS, handler_status);
	}
	
	protected function handler_status($evt:StatusEvent):void
	{
		this.dispatchEvent($evt);
	}
	
	public function init(
		appId:String,
		appKey:String,
		rate:String,
		gamebiName:String):String{
		
		return _extension.call(BaiduFunction.INIT,
			appId,
			appKey,
			rate,
			gamebiName) as String;
	} 
	
	public function login():String{
		return _extension.call(BaiduFunction.LOGIN) as String;
	}
	
	/**
	 * 显示购买
	 * @param amount 产品价格
	 * @param orderID 订单号
	 * @param productName 产品名
	 */	
	public function payment(amount:String, orderID:String, productName:String):String{
		return _extension.call(BaiduFunction.PAYMENT,
			amount,
			orderID,
			productName
		)as String;
	}
	
	/**
	 *退出SDK时候调用   这个函数只在退出游戏的时候调用  
	 */
	public function destroy():String{
		return _extension.call(BaiduFunction.DESTROY) as String;
	}
	
	public function pause():String{
		return _extension.call(BaiduFunction.PAUSE) as String;
	}
	
	public function resume():String{
		return _extension.call(BaiduFunction.RESUME) as String;
	}
	
	public function dispose():void
	{
		if(_extension)
		{
			_extension.removeEventListener(StatusEvent.STATUS, handler_status);
			_extension.dispose();
			_extension = null;
		}
	}
}
}