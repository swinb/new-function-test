package kr.codesquad.secondhand.global.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.PatternMatchUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import kr.codesquad.secondhand.api.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthFilter implements Filter {

	private final String[] whiteListUris = new String[] {};

	private final ObjectMapper objectMapper;

	private final JwtService jwtService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;

		if (whiteListCheck(httpServletRequest.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}
		if (!isContainToken(httpServletRequest)) {
			sendErrorApiResponse(httpServletResponse, new MalformedJwtException(""));
			return;
		}
		try {
			String token = getToken(httpServletRequest);
			Claims claims = jwtService.getClaims(token);
			request.setAttribute("userId", claims.get("userId"));
			chain.doFilter(request, response);
		} catch (RuntimeException e) {
			sendErrorApiResponse(httpServletResponse, e);
		}
	}
	private boolean whiteListCheck(String uri) {
		return PatternMatchUtils.simpleMatch(whiteListUris, uri);
	}

	private boolean isContainToken(HttpServletRequest httpServletRequest) {
		String authorization = httpServletRequest.getHeader("Authorization");
		return authorization != null && authorization.startsWith("Bearer ");
	}

	private void sendErrorApiResponse(HttpServletResponse response, RuntimeException e) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		response.getWriter().write(
			objectMapper.writeValueAsString(e));
	}

	private String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		return authorization.substring(7);
	}
}

