package app.entities.dto;

public record FeedItemDto(
        Long tweetId,
        String content,
        String username
) {
}
