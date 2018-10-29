package com.lj.czwgl.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.czwgl.domain.Userb;
import com.lj.czwgl.service.ICzwglService;
import com.lj.czwgl.utils.MessageHandlerUtil;

@Controller
@ResponseBody
@RequestMapping(path = "/wx")
@SuppressWarnings("unchecked")
public class WxController {

	private final Userb curUser = new Userb("1", "admin", "管理员", "1");

	@Autowired
	private ICzwglService czwglService;
	/**
	 * Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
	 * 比如这里我将Token设置为gacl
	 */
	private final String TOKEN = "gzlj";

	@GetMapping(path = "")
	public String doGet(@RequestParam String signature,
			@RequestParam String timestamp, @RequestParam String nonce,
			@RequestParam String echostr) {
		// 排序
		String sortString = sort(TOKEN, timestamp, nonce);
		// 加密
		String mySignature = sha1(sortString);
		// 校验签名
		if (mySignature != null && mySignature != ""
				&& mySignature.equals(signature)) {
			System.out.println("签名校验通过。");
			// 如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
			// response.getWriter().println(echostr);
			return echostr;
		} else {
			System.out.println("签名校验失败.");
		}
		return null;
	}

	/**
	 * 处理微信服务器发来的消息
	 * 
	 * @throws IOException
	 */
	@PostMapping(path = "")
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO 接收、处理、响应由微信服务器转发的用户发送给公众帐号的消息
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("请求进入");
		String result = "";
		try {
			Map<String, String> map = MessageHandlerUtil.parseXml(request);
			System.out.println("开始构造消息");
			result = MessageHandlerUtil.buildXml(map);
			System.out.println(result);
			if (result.equals("")) {
				result = "未正确响应";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发生异常：" + e.getMessage());
		}
		return result;
	}

	/**
	 * 排序方法
	 *
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * 将字符串进行sha1加密
	 *
	 * @param str
	 *            需要加密的字符串
	 * @return 加密后的内容
	 */
	public String sha1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
