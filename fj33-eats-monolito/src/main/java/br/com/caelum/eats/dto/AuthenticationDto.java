package br.com.caelum.eats.dto;

import java.util.List;

import br.com.caelum.eats.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {

	private String username;
	private List<String> roles;
	private String token;

	public AuthenticationDto(User user, String jwtToken) {
		this(user.getName(), user.getRoles(), jwtToken);
	}

}
