package com.schoolchat.school.chat.service.userService;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);

		// Extract user attributes
		Map<String, Object> attributes = oAuth2User.getAttributes();

		String email = (String) attributes.get("email");
		String name = (String) attributes.get("name");
		String login = (String) attributes.get("login");

		// You can save to DB or Session here
		// For autocomplete: Store attributes in session
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("oauthName", name);
		session.setAttribute("oauthEmail", email);
		session.setAttribute("oauthLoginGithub", login);
		session.setAttribute("oAuth2User", oAuth2User);

		System.out.println("------------------------------------------");
		System.out.println("OAuth2 User Email: " + email);
		System.out.println("------------------------------------------");
		return oAuth2User;
	}
}
