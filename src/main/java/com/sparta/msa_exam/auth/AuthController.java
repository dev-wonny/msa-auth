package com.sparta.msa_exam.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * 사용자 ID를 받아 JWT 액세스 토큰을 생성하여 응답합니다.
	 *
	 * @param userId 사용자 ID
	 * @return JWT 액세스 토큰을 포함한 AuthResponse 객체를 반환합니다.
	 */
	@GetMapping("/auth/signIn")
	public ResponseEntity<?> createAuthenticationToken(@RequestParam String userId) {
		if (userId == null || userId.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("User ID must not be null or empty");
		}
		return ResponseEntity.ok(new AuthResponse(authService.createAccessToken(userId)));
	}


}