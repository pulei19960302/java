package com.qim.loan.util.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.qim.loan.util.author.OauthUtil;
import com.qim.loan.util.cache.classify.string.IStringCache;
import com.qim.loan.util.cache.string.StringCacheFactory;
import com.qim.loan.util.common.ClientUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.setting.StatusSetting;

/**
 * 
 * 类名:AccessFilter 描述: 创建者:冯子文 创建时间: 2016年5月17日 上午11:48:10 更新者:冯子文 更新时间:
 * 2016年5月17日 上午11:48:10
 */
@Order(2)
// 重点
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
@Component
public class AccessFilter extends OncePerRequestFilter {
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Autowired
	private StringCacheFactory stringCacheFactory;
	
	private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

	/**
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		handlerAuthor();		
		String contentType = request.getContentType();
		BaseResponse baseResponse = null;
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		if (contentType == null || !contentType.startsWith("multipart/form-data")) {
		} else {
			multipartResolver.setDefaultEncoding("utf-8");
			request = multipartResolver.resolveMultipart(request);
		}
		Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
		Enumeration<?> enumHeader = request.getHeaderNames();
	    while (enumHeader.hasMoreElements()) {
	    	String key = (String) enumHeader.nextElement();
	    	if(StringUtil.equal(key, "referer") || StringUtil.equal(key, "service"))
	    		m.put(key, new String[] {request.getHeader(key)});
	    }
		// ipaddress
		String ipAddress = ClientUtil.getIpAddress(request);
		m.put("ipAddress", new String[] { ipAddress });
		// os
		String osVersion = ClientUtil.getOs(request);
		m.put("osVersion", new String[] { osVersion });
		// browser
		String browserVersion = ClientUtil.getBrowser(request);
		m.put("browseVersion", new String[] { browserVersion });
		if (m.containsKey("userKey") && m.containsKey("userToken")) {
			String userId = String.valueOf(String.join(",", m.remove("userKey")));
			String userToken = String.valueOf(String.join(",", m.remove("userToken")));
			if (StringUtil.isNull(userId) || StringUtil.isNull(userToken)) {
				baseResponse = BaseResponse.setStatus(StatusSetting.getAuthorityFailure(), "你没传userKey或者userToken.");
				output(response, baseResponse);
				return;
			}
			if (userToken.indexOf("@") < 1) {
				baseResponse = BaseResponse.setStatus(StatusSetting.getAuthorityFailure(), "非法userKey或者userToken.");
				output(response, baseResponse);
				return;
			}
			String[] userTokenArray = userToken.split("@");
			String client = userTokenArray[1].replace("\"", "");
			try {
				String tmpUserId = userId.replace("\"", "");
				String tmpUserToken = userTokenArray[0].replace("\"", "");
				userId = OauthUtil.decrypt(tmpUserId);
				userToken ="";
				userToken = OauthUtil.decrypt(tmpUserToken);
			} catch (Exception e) {
				baseResponse = BaseResponse.setStatus(StatusSetting.getAuthorityFailure(), "非法userKey或者userToken.");
				output(response, baseResponse);
				return;
			}
			switch (client.toLowerCase()) {
			case "c"://渠道后台
				m.put("userKey", new String[] { userId });
				m.put("channelUserId", new String[] { userId });
				m.put("userToken", new String[] { userToken + "@c" });
				m.put("resourceAuthor", new String[] { "c"});
				break;
			case "ep"://员工
				m.put("userKey", new String[] { userId });
				m.put("employeeUserId", new String[] { userId });
				m.put("userToken", new String[] { userToken + "@ep" });
				m.put("resourceAuthor", new String[] { "ep"});
				break;
			case "em":
				m.put("userKey", new String[] { userId });
				m.put("employeeUserId", new String[] { userId });
				m.put("userToken", new String[] { userToken + "@em" });
				m.put("resourceAuthor", new String[] { "em"});
				break;
			case "m":
				m.put("userKey", new String[] { userId });
				m.put("consoleUserId", new String[] { userId });
				m.put("userToken", new String[] { userToken + "@m" });
				m.put("resourceAuthor", new String[] { "m"});
				break;
			default:
				baseResponse = BaseResponse.setStatus(StatusSetting.getAuthorityFailure(), "非法userKey或者userToken.");
				output(response, baseResponse);
				return;
			}
			request = new RequestWrapper((HttpServletRequest) request, m);
			filterChain.doFilter(request, response);
		} else {
			request = new RequestWrapper((HttpServletRequest) request, m);
			filterChain.doFilter(request, response);
		}
	}

	private void output(HttpServletResponse response, BaseResponse baseResponse) {
		if (baseResponse == null)
			return;
		logger.error(baseResponse.toString());
		try {
			response.getWriter().write(baseResponse.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	     * 方法名:handlerAuthor
	     * 功能描述:处理动态秘钥	 
	     * 创建者:冯子文
	     * 创建时间: 2018年5月31日 上午11:19:08 
		 * 更新者:冯子文  
		 * 更新时间: 2018年5月31日 上午11:19:08
	 */
	private void handlerAuthor() {
		IStringCache authorCache = stringCacheFactory.getAuthor();
		IStringCache userCache=stringCacheFactory.getUser();
		if (!authorCache.exists("author")) {
			String author = PrimaryKeyUtil.getGraphCode(8);
			authorCache.set("author", author);
			userCache.removeAll();
		}
		OauthUtil.setCryptKey(authorCache.get("author"));	
	}
	
	
}
