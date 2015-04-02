package cu.uci.coj.utils.corrections;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordGen {

	public static void main(String[] args) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		System.out.println(md5.encodePassword("password",null));

	}

}
