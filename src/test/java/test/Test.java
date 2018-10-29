package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wx.DataObj;
import wx.TemplateObj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lj.czwgl.utils.AccessToken;
import com.lj.czwgl.utils.NetWorkHelper;

public class Test {

	public static void main(String[] args) throws Exception {
		// AccessToken token =
		// getAccessToken("wx5682b088ee6d92a4","3a5489c45254edc27f80f397a512d5ea");
		// AccessToken token =
		// getAccessToken("wxa49564198a94c518","48491262cd07c6b0c104c0b687250d6c");
		// System.out.println(token.getAccessToken());
		String strtoken = "15_NK87N2a_M7yBi0lUy2sPrH1IlN7zgXRVu_SNX1dc6gXkvEAZhOwnYIceCmXR9OjTuhB1AOghwEBjw_a-GjGNtZDgJc6pIUTNwCXDRt0oLyvbY8A5AdtpLn2I06A1wQ_V-DgeVlsSphJ3IZtTPBEcAIABNQ";
		NetWorkHelper netHelper = new NetWorkHelper();
		//模板消息测试
		//设置所属行业
//		String url = String
//				.format("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s",
//						strtoken);
//		String body = "{\"industry_id1\":\"1\",\"industry_id2\":\"4\"}";
//		String result = netHelper.postHttps(url, body);
//		System.out.println(result);
		
		//获取所属行业
//		String url = String
//				.format("https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s",
//						strtoken);
//		String result = netHelper.getHttpsResponse(url, "");
//		System.out.println(result);
		//获取模板列表
		String url = String
				.format("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s",
						strtoken);
		String result = netHelper.getHttpsResponse(url, "");
		System.out.println(result);

		//发送模板消息
		TemplateObj tmobj = new TemplateObj();
		 JSONObject jsonobj = new JSONObject();
//		tmobj.put("","oLXPC0uDnrwiQ-GcwnRcQh4jTyiM");
		tmobj.setTemplate_id("omtuiMD0-VxUMRkABVq6D-FQ8heRGbnlT7uw-HWMFX8");
		tmobj.setUrl("http://hczdnn.natappfree.cc");
		DataObj  dataObj = new DataObj("测试参数","#173177");
		List datalist = new ArrayList();
		datalist.add(dataObj);
		tmobj.setData(datalist);
		
//		tmobj.
		
		// String strtoken = token.getAccessToken();
		// JSONObject jsonObj = new JSONObject();
		// ArrayList list = new ArrayList();
		// list.add(new
		// ButtonObj("view","进入极简出租","CZWGLXT","http://myk8m6.natappfree.cc"));
		// jsonObj.put("button", list);
		// String menuStr = jsonObj.toJSONString();
		// System.out.println(menuStr);
		//
		// String Url =
		// String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",strtoken);
		// NetWorkHelper netHelper = new NetWorkHelper();
		// String result = netHelper.postHttps(Url, menuStr);
		// System.out.println(result);
	}

	/**
	 * 获取access_token
	 *
	 * @return AccessToken
	 */
	private static AccessToken getAccessToken(String appId, String appSecret) {
		NetWorkHelper netHelper = new NetWorkHelper();
		/**
		 * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=
		 * client_credential
		 * &appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
		 */
		String Url = String
				.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
						appId, appSecret);
		// 此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
		String result = netHelper.getHttpsResponse(Url, "");
		// JSONObject jsonObj = JSONObject.parseObject(result);
		// String token = (String)jsonObj.get("access_token");
		// System.out.println(token);
		// System.out.println("获取到的access_token="+result);
		// 使用FastJson将Json字符串解析成Json对象
		JSONObject json = JSON.parseObject(result);
		AccessToken token = new AccessToken();
		token.setAccessToken(json.getString("access_token"));
		token.setExpiresin(json.getInteger("expires_in"));
		return token;
	}

	private static String subMonth(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(date);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MONTH, 1);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

}
