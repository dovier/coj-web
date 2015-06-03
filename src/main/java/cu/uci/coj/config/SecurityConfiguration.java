package cu.uci.coj.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.security.COJAuthenticationFailureHandler;
import cu.uci.coj.security.COJAuthenticationProcessingFilter;
import cu.uci.coj.security.COJAuthenticationSuccessHandler;
import cu.uci.coj.security.COJSessionRegistryImpl;

@Configuration
public class SecurityConfiguration {

    @Resource
    private UserDAO userDAO;
    
    @Resource
    private COJSessionRegistryImpl cojSessionRegistryImpl;

    @Resource
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    @Resource
    private MessageSource messageSource;
    
    @Resource
    private COJAuthenticationFailureHandler cojAuthenticationFailureHandler;
            
    @Resource
    private COJAuthenticationSuccessHandler cojAuthenticationSuccessHandler;
    
    private AuthenticationManager authenticationManager;

    private TokenBasedRememberMeServices rememberMeServices;

    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Value("/index.xhtml")
    private String logoutUrl;
    
    @Value("/j_spring_logout")
    private String logoutFilterProcessesUrl;
    
    private JdbcDaoImpl jdbcDaoImpl;
    
    
    public TokenBasedRememberMeServices rememberMeServices(){
        if (rememberMeServices == null){
            rememberMeServices = new TokenBasedRememberMeServices("changeThis",jdbcDaoImpl());
        }
        return rememberMeServices;
    }
    
    private JdbcDaoImpl jdbcDaoImpl(){
        if (jdbcDaoImpl == null){
            jdbcDaoImpl = new JdbcDaoImpl();
            jdbcDaoImpl.setJdbcTemplate(jdbcTemplate);
        }
        return jdbcDaoImpl;
    }
    
    @Bean
    public DefaultWebSecurityExpressionHandler webexpressionHandler(){
    	return new DefaultWebSecurityExpressionHandler(); 
    }

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public AuthenticationManager authenticationManager(){
        
        if (authenticationManager == null){
		List providers = new ArrayList();
        providers.add(daoAuthenticationProvider());
        providers.add(new AnonymousAuthenticationProvider("changeThis"));
        providers.add(new RememberMeAuthenticationProvider("changeThis"));
        
        ProviderManager bean = new ProviderManager(providers);
        authenticationManager = bean;
        }
        return authenticationManager;
    }
    
    private DaoAuthenticationProvider daoAuthenticationProvider(){
       DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
       bean.setUserDetailsService(jdbcDaoImpl());
       bean.setPasswordEncoder(new Md5PasswordEncoder());
       SystemWideSaltSource saltSource = new SystemWideSaltSource();
       saltSource.setSystemWideSalt("ABC123XYZ789");
       bean.setSaltSource(saltSource);
       return bean;
    }

    
    @Bean
    public LogoutFilter logoutFilter(){
        LogoutFilter bean = new LogoutFilter(logoutUrl,rememberMeServices(),new SecurityContextLogoutHandler());
        bean.setFilterProcessesUrl(logoutFilterProcessesUrl);        
        return bean;
    }
                
    @Bean
    public COJAuthenticationProcessingFilter cojAuthenticationProcessingFilter(){
        COJAuthenticationProcessingFilter bean = new COJAuthenticationProcessingFilter();
        bean.setAuthenticationManager(authenticationManager());
        bean.setFilterProcessesUrl("/j_spring_security_check");
        bean.setAuthenticationSuccessHandler(cojAuthenticationSuccessHandler);
        bean.setAuthenticationFailureHandler(cojAuthenticationFailureHandler);
        bean.setRememberMeServices(rememberMeServices());
   //     bean.setSessionAuthenticationStrategy(concurrentSessionControlStrategy());
        bean.setUserDAO(userDAO);
        return bean;
    }
    
    
    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter(){
        return new RememberMeAuthenticationFilter(authenticationManager(),rememberMeServices());
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter(){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        AnonymousAuthenticationFilter bean = new AnonymousAuthenticationFilter("changeThis","anonymousUser",authorities);
        return bean;
    }
    
    @Bean
    public ExceptionTranslationFilter exceptionTranslationFilter(){
        LoginUrlAuthenticationEntryPoint entryPoint = new LoginUrlAuthenticationEntryPoint("/index.xhtml");
        entryPoint.setForceHttps(false);
        AccessDeniedHandlerImpl handler = new AccessDeniedHandlerImpl();
        handler.setErrorPage("/index.xhtml");
        ExceptionTranslationFilter bean = new ExceptionTranslationFilter(entryPoint);
        bean.setAccessDeniedHandler(handler);
        return bean;
    }
            
    
    @Bean
    public FilterSecurityInterceptor filterInvocationInterceptor(){
        List<AccessDecisionVoter<?>> vote = new ArrayList<AccessDecisionVoter<?>>(Arrays.asList(new WebExpressionVoter()));
        AffirmativeBased voters = new AffirmativeBased(vote);
        voters.setAllowIfAllAbstainDecisions(false);
        FilterSecurityInterceptor bean = new FilterSecurityInterceptor();
        bean.setAuthenticationManager(authenticationManager());
        bean.setAccessDecisionManager(voters);
        bean.setSecurityMetadataSource(securityMetadataSource);
        bean.setMessageSource(messageSource);
        return bean;
    }

    
    /*@Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlStrategy(){
    	ConcurrentSessionControlAuthenticationStrategy bean = new ConcurrentSessionControlAuthenticationStrategy(cojSessionRegistryImpl);
        bean.setMaximumSessions(3);
        bean.setExceptionIfMaximumExceeded(true);
        return bean;
    }    */

    private List<SecurityFilterChain> filterChain() {
	List<SecurityFilterChain> filters = new ArrayList<SecurityFilterChain>(12);
        RequestMatcher req = new AntPathRequestMatcher("/**");
        
        filters.add(new DefaultSecurityFilterChain(req, new SecurityContextPersistenceFilter(), 
                logoutFilter(),cojAuthenticationProcessingFilter(),new SecurityContextHolderAwareRequestFilter(),
                rememberMeAuthenticationFilter(),anonymousAuthenticationFilter(),exceptionTranslationFilter(),filterInvocationInterceptor()
                ));
	return filters;
    }
    
    @Bean
    public FilterChainProxy springSecurityFilterChain() {
	FilterChainProxy bean = new FilterChainProxy(filterChain());
	return bean;
    }
    
}
