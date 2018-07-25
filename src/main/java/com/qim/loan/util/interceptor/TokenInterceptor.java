package com.qim.loan.util.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qim.loan.util.author.OauthUtil;
import com.qim.loan.util.cache.classify.string.IStringCache;
import com.qim.loan.util.cache.string.StringCacheFactory;
import com.qim.loan.util.common.PrimaryKeyUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.setting.StatusSetting;

@Component
public class TokenInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
	
	@Autowired
	private StringCacheFactory stringCacheFactory;
		
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		IStringCache userCache=stringCacheFactory.getUser();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		BaseResponse baseResponse = null;
		String userToken=request.getParameter("userToken");
		String userId=request.getParameter("userKey");
    	if (StringUtil.isNull(userId) || StringUtil.isNull(userToken) || !userToken.contains("@")) {
			baseResponse = BaseResponse.setStatus(StatusSetting.getAuthorityFailure(), "你没传userKey或者userToken.");
			output(response, baseResponse);
			return false;
		}
		if(!userCache.exists(userId)){//不存在userId
			baseResponse =BaseResponse.setStatus(StatusSetting.getAuthorityFailure(),"userKey或者userToken过期.");
			output(response,baseResponse);
			return false;
		}else{
			String userTokenCache=userCache.get(userId);
			if(!StringUtil.equal(userToken, userTokenCache)){
				baseResponse =BaseResponse.setStatus(StatusSetting.getAuthorityFailure(),"鉴权失败.");
				output(response,baseResponse);
				return false;
			}
		}
		return true;
	}

	private void output(HttpServletResponse response,BaseResponse baseResponse){
		if(baseResponse==null)
			return;
		logger.error(baseResponse.toString());
		try {
			response.getWriter().write(baseResponse.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		IStringCache authorCache=stringCacheFactory.getAuthor();
		if(!authorCache.exists("author")) {
			String author=PrimaryKeyUtil.getGraphCode(8);
			authorCache.set("author", author);
			OauthUtil.setCryptKey(author);
		}
		
		System.out.println("POST");	
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//System.out.println("AFTER");	
	}

}
