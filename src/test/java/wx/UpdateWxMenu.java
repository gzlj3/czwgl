package wx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lj.czwgl.utils.AccessToken;
import com.lj.czwgl.utils.NetWorkHelper;
import com.lj.czwgl.utils.Utils;

public class UpdateWxMenu {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		String proxyurl = getProxyUrl();
		// String proxyurl = "http://7m948z.natappfree.cc";
		AccessToken token = getAccessToken("wx5682b088ee6d92a4",
				"3a5489c45254edc27f80f397a512d5ea");
		// // AccessToken token =
		// //
		// getAccessToken("wxa49564198a94c518","48491262cd07c6b0c104c0b687250d6c");
		// String strtoken =
		// "15_Y9u81jvn1ciS0-8-uAnoPRslUHkIOfgngpveDs-6rCSv8Rdbmb_RwvDBIfrkyEzqhhSq1IDe6P8AAq7-TPNDXp3aAZhew1Av1BaTXMfbFOYLPNElN9TVTRo63uYIQVdAEAUCH";
		String strtoken = token.getAccessToken();
		if (Utils.empty(strtoken)) {
			System.out.println(String.format(
					"未生成accessToken,Errorcode:%s,Errmsg:%s",
					token.getErrcode(), token.getErrmsg()));
			System.exit(-1);
		}
		System.out.println("accessToken:" + strtoken);

		JSONObject jsonObj = new JSONObject();
		ArrayList list = new ArrayList();
		list.add(new ButtonObj("view", "进入极简出租", "CZWGLXT", proxyurl));
		jsonObj.put("button", list);
		String menuStr = jsonObj.toJSONString();
		System.out.println("向微信提交菜单串：" + menuStr);
		String Url = String
				.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",
						strtoken);
		NetWorkHelper netHelper = new NetWorkHelper();
		String result = netHelper.postHttps(Url, menuStr);

		AccessToken retuStat = JSON.parseObject(result, AccessToken.class);
		if (!"0".equals(retuStat.getErrcode())) {
			System.out.println("生成微信菜单失败！" + retuStat.getErrmsg());
			System.exit(-1);
		}
		System.out.println("生成微信菜单成功！");
	}

	private static String getProxyUrl() throws IOException,
			InterruptedException {
		String logFn = "logs.txt";
		File logFile = new File(logFn);
		// 先删除logs.txt文件
		if (logFile.exists()) {
			if (!logFile.delete()) {
				System.out.println("文件：" + logFn + "删除失败！");
				System.exit(-1);
			}
		}
		// 运行natapp.exe
		Runtime.getRuntime().exec("cmd /k start natapp.exe");
		// 读取生成的日志文件
		FileReader fr = null;
		while (true) {
			try {
				fr = new FileReader(logFile);
				break;
			} catch (FileNotFoundException ex) {
				Thread.sleep(1000);
			}
		}
		BufferedReader reader = new BufferedReader(fr);
		String s = null;
		String url = null;
		int line = 1;
		while (line < 100) {
			s = reader.readLine();
			if (s != null) {
				int pos = s.indexOf("http://");
				if (pos >= 0) {
					url = s.substring(pos);
					break;
				}
				line++;
			} else {
				Thread.sleep(1000);
			}
		}
		reader.close();
		if (url == null) {
			System.out.println("未读取到代理的URL！");
			System.exit(-1);
		}
		System.out.println("natapp代理的URL：" + url);
		return url;
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
		if (Utils.empty(result))
			return null;
		AccessToken token = JSON.parseObject(result, AccessToken.class);
		// AccessToken token = new AccessToken();
		// token.setAccessToken(json.getString("access_token"));
		// token.setExpiresin(json.getInteger("expires_in"));
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
