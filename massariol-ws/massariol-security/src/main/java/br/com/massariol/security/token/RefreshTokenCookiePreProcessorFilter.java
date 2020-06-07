package br.com.massariol.security.token;


import br.com.massariol.security.config.property.MassariolSecutiryProperty;
import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class  RefreshTokenCookiePreProcessorFilter extends OncePerRequestFilter {
	private final MassariolSecutiryProperty massariolSecutiryProperty;

	public RefreshTokenCookiePreProcessorFilter(MassariolSecutiryProperty massariolSecutiryProperty) {
		this.massariolSecutiryProperty = massariolSecutiryProperty;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String url=request.getContextPath()+ massariolSecutiryProperty.getSecurity().getOauthPath();// TODO: Mover para application.properties
		if (url.equalsIgnoreCase(request.getRequestURI())
				&& "refresh_token".equals(request.getParameter("grant_type"))
				&& request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("refreshToken")) {
					String refreshToken = cookie.getValue();
					request = new MyServletRequestWrapper(request, refreshToken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	static class MyServletRequestWrapper extends HttpServletRequestWrapper {

		private String refreshToken;
		
		private MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[]{refreshToken});
			map.setLocked(true);
			return map;
		}
	}

}