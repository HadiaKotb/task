package com.sarmad.task.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	
	private final UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, 
			@NonNull HttpServletResponse response, 
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String requestTokenHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String loginId;
		
		if(requestTokenHeader==null||!requestTokenHeader.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken=requestTokenHeader.substring(7);
		loginId=jwtService.extractLoginIdFromToken(jwtToken);
		if(loginId!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails=this.userDetailsService.loadUserByUsername(loginId);
			
			if(jwtService.isTokenValid(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,null);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
			
		}
		filterChain.doFilter(request, response);
		
	}

}
