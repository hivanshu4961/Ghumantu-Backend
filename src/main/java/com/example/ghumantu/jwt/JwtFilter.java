package com.example.ghumantu.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ghumantu.Service.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtProvider;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		if(authorization != null && authorization.startsWith("Bearer ") ) {
			jwtToken = authorization.substring(7);
			try {
				username = this.jwtProvider.getUsernameFromToken(jwtToken); 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
			
			if(StringUtils.hasText(jwtToken) && jwtProvider.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
	                    null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}
	
}
