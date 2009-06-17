package twitter4j;

import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.TestCase;

public class RateLimitStatusTest extends TestCase {
	protected Twitter twitterAPI1 = null;
	protected Twitter unauthenticated = null;
	protected Properties p = new Properties();
	
	protected String id1,pass1;
	
	protected void setUp() throws Exception{
        super.setUp();
        p.load(new FileInputStream("test.properties"));
        id1 = p.getProperty("id1");
        pass1 = p.getProperty("pass1");
        twitterAPI1 = new Twitter(id1, pass1);
        unauthenticated = new Twitter();
	}
	
	//need to think of a way to test this, perhaps mocking out Twitter is the way to go
	public void testRateLimitStatus() throws Exception{
		twitterAPI1.addAccountRateLimitStatusListener(new RateLimitStatusListener(){

			@Override
			public void rateLimitStatusUpdated(RateLimitStatus status) {
				System.out.println(status.getHourlyLimit());	
				System.out.println(status.getRemainingHits());
				System.out.println(status.getResetTimeInSeconds());
				System.out.println(status.getDateTime());
			}
			
		});
		
		unauthenticated.addIpRateLimitStatusListener(new RateLimitStatusListener(){

			@Override
			public void rateLimitStatusUpdated(RateLimitStatus status) {
				System.out.println(status.getHourlyLimit());	
				System.out.println(status.getRemainingHits());
				System.out.println(status.getResetTimeInSeconds());
				System.out.println(status.getDateTime());
			}
			
		});

		twitterAPI1.getMentions();
		unauthenticated.getPublicTimeline();

	}

}
