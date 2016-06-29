package com.lee.interceptor;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 系统日志，每一步的操作记录
 */
public class SystemLogInterceptor implements HandlerInterceptor {
    private final Logger log = Logger.getLogger(SystemLogInterceptor.class);

//	@Autowired
//	private SystemLogService systemLogService1;

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        this.log.debug("==============system log================");

        String context = request.getContextPath().toString();
        String uri = request.getRequestURI().toString();
        String path = uri.substring(context.length());

        if (((path.indexOf(".jsp") > 0) || (path.indexOf(".htm") > 0)) && (path.indexOf("login.jsp") == -1)
                && (path.indexOf("login.htm") == -1) && (path.indexOf("logout.jsp") == -1)
                && (path.indexOf("logout.htm") == -1)) {
            String Agent = request.getHeader("User-Agent");
            String browser = null;
            StringTokenizer st = new StringTokenizer(Agent, ";");
            st.nextToken();
            try {
                if (st.hasMoreTokens())
                    browser = st.nextToken();
                else
                    browser = Agent;
            } catch (Exception e) {
                e.printStackTrace();
            }
            String ipAdd = request.getHeader("x-forwarded-for");
            if ((ipAdd == null) || (ipAdd.length() == 0) || ("unknown".equalsIgnoreCase(ipAdd))) {
                ipAdd = request.getHeader("Proxy-Client-IP");
            }
            if ((ipAdd == null) || (ipAdd.length() == 0) || ("unknown".equalsIgnoreCase(ipAdd))) {
                ipAdd = request.getHeader("WL-Proxy-Client-IP");
            }
            if ((ipAdd == null) || (ipAdd.length() == 0) || ("unknown".equalsIgnoreCase(ipAdd))) {
                ipAdd = request.getRemoteAddr();
            }
            if (("127.0.0.1".equals(ipAdd)) && (request.getHeader("X-Real-IP") != null)) {
                ipAdd = request.getHeader("X-Real-IP");
            }

            Map hashmap = request.getParameterMap();
            JSONObject obj = JSONObject.fromObject(hashmap);
            String value = obj.toString();
            if (value.length() > 4000) {
                value = value.substring(0, 4000);
            }

//			User user = (User) request.getSession().getAttribute(GeneralConstants.CS_USER_INFOR);
//			if(user == null ){
//				user = (User) request.getSession().getAttribute(GeneralConstants.USER_INFOR);
//			}
//
//			SystemLog systemLog = new SystemLog();
//			if (user != null)
//				systemLog.setUserId(user.getId().toString());
//			else {
//				systemLog.setUserId("no_userid");
//			}
//
//			systemLog.setPath(path);
//			systemLog.setIpAdd(ipAdd);
//			systemLog.setBrowser(browser);
//			systemLog.setValue(value);
//			this.systemLogService1.insert(systemLog);
        }

        return true;
    }
}