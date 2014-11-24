package us.sanguo.ane.baidu
{

import flash.external.ExtensionContext;
import flash.system.Capabilities;

import us.sanguo.ane.baidu.funs.BaiduCont;

/**
 * 
 * @author zm
 * 创建日期：2014-10-30
 */
public class BaiduExt
{
	/**
	 * 定义本地插件的ID
	 */	
	public static const EXT_ID:String = 'us.sanguo.ane.baidu';
	
	protected static var _cont:BaiduCont= null;
	
	/**
	 * 获取当前插件
	 */
	public static function get iab():BaiduCont
	{
		if(!_cont)
		{
			checkSuppored();
			_cont = new BaiduCont(ExtensionContext.createExtensionContext(EXT_ID, ""));
		}
		return _cont;
	}
	
	protected static function get isSupported() : Boolean
	{
		return (Capabilities.os.indexOf("Linux") >= 0);
	}
	
	private static function checkSuppored():void
	{
		if(!isSupported) throw new TypeError('The native extension is not supported on this device!');
	}
}
}