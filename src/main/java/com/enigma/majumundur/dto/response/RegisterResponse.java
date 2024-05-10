package com.enigma.majumundur.dto.response;

import java.util.List;

public record RegisterResponse(String username, List<String> roles) {

}
