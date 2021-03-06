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
		//测试公众号
//		 AccessToken token =
//		 getAccessToken("wx5682b088ee6d92a4","3a5489c45254edc27f80f397a512d5ea");
		 //服务号
		// AccessToken token =
		// getAccessToken("wxa49564198a94c518","48491262cd07c6b0c104c0b687250d6c");
		//小程序的tokey
//		 AccessToken token =
//		 getAccessToken("wx074eecae1210da4a","ad174bf3f0d99c9962e3329b475ef708");
//		 String strtoken = token.getAccessToken();
		String strtoken = "16_r1E4RD9uPqx_-i59e84tbAWvMGpT_VF5E5ExY2_n5BXUqGj-xNRxntFot0lDTTOLi8y9NwVYiiF3bpziOieJEhLHVemnbtPbrWnJ1SewLOwoNuSrKu1k_71okpe0-sSCLNN_EUJpxRVJXHCkCVBiAFANGM";
		 System.out.println(strtoken);
		NetWorkHelper netHelper = new NetWorkHelper();
//小程序liujun的openid: on_Li5Pa4d5XQklE_NCiI2IoPKsM
//		String url = String
//		.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",
//				strtoken);
// String body = "{\"touser\":\"on_Li5Pa4d5XQklE_NCiI2IoPKsM\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\"}}";
// 
//String result = netHelper.postHttps(url, body);
//System.out.println(result);
		
		// 模板消息测试
		// 设置所属行业
		// String url = String
		// .format("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s",
		// strtoken);
		// String body = "{\"industry_id1\":\"1\",\"industry_id2\":\"4\"}";
		// String result = netHelper.postHttps(url, body);
		// System.out.println(result);

		// 获取所属行业
		// String url = String
		// .format("https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s",
		// strtoken);
		// String result = netHelper.getHttpsResponse(url, "");
		// System.out.println(result);
		// 获取模板列表
		// String url = String
		// .format("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s",
		// strtoken);
		// String result = netHelper.getHttpsResponse(url, "");
		// System.out.println(result);
		//
		// //发送模板消息
		TemplateObj tmobj = new TemplateObj();
		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("template_id",
//				"omtuiMD0-VxUMRkABVq6D-FQ8heRGbnlT7uw-HWMFX8");
		jsonObj.put("template_id",
				"50_-U2e4vq8STuhhDTqEWVywu1RQYFSzujZv_NG2h6k");
		
//		jsonObj.put("touser", "on_Li5Pa4d5XQklE_NCiI2IoPKsM");  //liujun 小程序
		jsonObj.put("touser", "oLXPC0uDnrwiQ-GcwnRcQh4jTyiM");  //测试号
		
		jsonObj.put("form_id", "1542156183323");
		
//		jsonObj.put("url", "http://c6paje.natappfree.cc");
		JSONObject dataObjs = new JSONObject();
		DataObj dataObj = new DataObj("中华人民共和国", "red");
		dataObjs.put("para1", dataObj);
		jsonObj.put("data", dataObjs);
		System.out.println(jsonObj.toJSONString());
		String url = String
				.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",
						strtoken);
		String body = jsonObj.toJSONString();
		String result = netHelper.postHttps(url, body);
		System.out.println(result);

		// 获取用户列表
//		 String url = String
//		 .format("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s",strtoken);
//		 String result = netHelper.getHttpsResponse(url, "");
//		 System.out.println(result);
			// 获取用户基本信息
//		 String url = String
//		 .format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN",strtoken,"oLXPC0uDnrwiQ-GcwnRcQh4jTyiM");
//		 String result = netHelper.getHttpsResponse(url, "");
//		 System.out.println(result);
		//生成二维码
//		 String url = String
//		 .format("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s",strtoken);
//		 String body = "{\"expire_seconds\":604800,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":123}}}";
//		String result = netHelper.postHttps(url, body);
//		 System.out.println(result);
//		 {"ticket":"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEQ8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycDlaN2d4SlBmMmkxUW8zdzFyMWEAAgSYCNdbAwSAOgkA","expire_seconds":604800,"url":"http:\/\/weixin.qq.com\/q\/02p9Z7gxJPf2i1Qo3w1r1a"}

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
