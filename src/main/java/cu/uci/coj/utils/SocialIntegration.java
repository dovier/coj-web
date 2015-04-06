package cu.uci.coj.utils;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import cu.uci.coj.config.Config;

@Component
public class SocialIntegration {
	//Twitter
	//App twitter
	final String consumerKeyTwitter = Config.getProperty("twitter.consumer.key");
	final String consumerSecretTwitter = Config.getProperty("twitter.consumer.secret");
	
	//Coj Twitter account
	final String accessTokenTwitter = Config.getProperty("twitter.accesstoken");
	final String accessTokenSecretTwitter = Config.getProperty("twitter.accesstoken.secret");
	
	Twitter twitterTemplate;
	
	
	
	//Facebook
	//User token: only valid for 60days, till June 2th
	final String accessTokenFacebook = Config.getProperty("facebook.accesstoken");
	
	Facebook facebookTemplate;
	
	
	SocialIntegration() {
		twitterTemplate = new TwitterTemplate(consumerKeyTwitter, consumerSecretTwitter, accessTokenTwitter, accessTokenSecretTwitter);
		facebookTemplate = new FacebookTemplate(accessTokenFacebook);
	}

	public Twitter getTwitterTemplate() {
		return twitterTemplate;
	}		
	
	public Facebook getFacebookTemplate() {
		return facebookTemplate;
	}
		
}
