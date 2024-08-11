package com.sparta.msa_exam.auth;

import com.sparta.msa_exam.auth.core.User;
import com.sparta.msa_exam.auth.dto.SignInRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
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
	@PostMapping("/signIn")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequest signInRequest) {
		final String userId = signInRequest.getUserId();

		if (userId == null || userId.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("User ID must not be null or empty");
		}
		String token = authService.signIn(signInRequest.getUserId(), signInRequest.getPassword());
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody User user) {
		User createdUser = authService.signUp(user);
		return ResponseEntity.ok(createdUser);
	}


}