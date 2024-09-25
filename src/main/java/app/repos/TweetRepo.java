package app.repos;

import app.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepo extends JpaRepository<Tweet, Long> {
}