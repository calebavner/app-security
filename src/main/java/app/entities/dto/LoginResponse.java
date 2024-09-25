package app.entities.dto;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
