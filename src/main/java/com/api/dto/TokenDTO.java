package com.api.dto;

import lombok.Data;

@Data
public class TokenDTO {
	private String token;
	private String refreshToken;
}
