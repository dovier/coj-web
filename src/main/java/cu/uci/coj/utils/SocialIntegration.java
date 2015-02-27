package cu.uci.coj.utils;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

@Component
public class SocialIntegration {
	//App twitter
	final String consumerKey = "V3xfbPZRwTFnwQ10q1ZQd8wOT";
	final String consumerSecret = "qsIecwNCQtfTVyphDJNn7BN3er8qFlo5bgd8tG01LdeA4oOHxx";
	
	//Coj Twitter account
	final String accessToken = "2890833509-HT2xbdjFtl4oO1ngoWYcglXf2DV39wOcQ6zSj2a";
	final String accessTokenSecret = "ja8mwMhPDpmPa7njDPe8aD8SCOrIJf9oyqQ3VZD7Y3ToM";
	
	Twitter twitterTemplate;
	
	SocialIntegration() {
		twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

	public Twitter getTwitterTemplate() {
		return twitterTemplate;
	}		
}
