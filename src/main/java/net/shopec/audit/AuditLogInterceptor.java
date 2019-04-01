package net.shopec.audit;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shopec.entity.AuditLog;
import net.shopec.service.AuditLogService;
import net.shopec.service.UserService;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baomidou.mybatisplus.toolkit.IdWorker;

/**
 * Audit - 审计日志拦截器
 * 
 */
public class AuditLogInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private AuditLogService auditLogService;
	@Inject
	private UserService userService;

	/**
	 * 请求前处理
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param handler
	 *            处理器
	 * @return 是否继续执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Audit audit = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Audit.class);
			if (audit != null) {
				AuditLog auditLog = new AuditLog();
				auditLog.setAction(audit.action());
				auditLog.setIp(request.getRemoteAddr());
				auditLog.setRequestUrl(request.getRequestURL().toString());
				auditLog.setParameters(new HashMap<>(request.getParameterMap()));
				auditLog.setUser(userService.getCurrent());
				request.setAttribute(AuditLog.AUDIT_LOG_ATTRIBUTE_NAME, auditLog);
			}
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 请求后处理
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param handler
	 *            处理器
	 * @param modelAndView
	 *            数据视图
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Audit audit = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Audit.class);
			if (audit != null) {
				AuditLog auditLog = (AuditLog) request.getAttribute(AuditLog.AUDIT_LOG_ATTRIBUTE_NAME);
				if (auditLog != null && auditLog.isNew()) {
					auditLog.setId(IdWorker.getId());
					auditLogService.create(auditLog);
				}
			}
		}
	}

}