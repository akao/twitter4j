package twitter4j;

public interface RateLimitStatusListener {

	public void rateLimitStatusUpdated(RateLimitStatus status);
	
}
