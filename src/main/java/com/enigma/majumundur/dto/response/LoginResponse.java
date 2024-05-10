package com.enigma.majumundur.dto.response;

import java.util.List;

public record LoginResponse(String username, String token, List<String> roles) {

}
