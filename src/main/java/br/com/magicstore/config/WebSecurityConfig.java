package br.com.magicstore.config;
//package br.com.margicstore.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////	@Qualifier("userDetailsServiceImpl")
////	@Autowired
////	private UserDetailsService userDetailsService;
//	
//	private static final String[] AUTH_WHITELIST = {
//            // -- swagger ui
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/swagger-ui/**",
//            "/webjars/**",
//            "/actuator/**",
//            "/cielo-redirect/**",
//            "/resources/**",
//            "/properties/**",
//            "/login",
//            "/js/**",
//            "/css/**",
//            "/iconfonts/**",
//            "/images/**",
//            "/dist/**",
//            "/controllers/**",
//            "/webjars/**",
//            "/fonts/**"
//    };
//
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/registration")
//				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATIONAL')").antMatchers("/tiquei/**")
//				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATIONAL') or hasRole('ROLE_TIQUEI')")
//				.antMatchers(AUTH_WHITELIST).permitAll()
//				.anyRequest().authenticated().and().formLogin()
//				.loginPage("/login").defaultSuccessUrl("/index", true).permitAll();
////				.successHandler(new AuthenticationSucessHandler()).and().logout().permitAll();
//		http.sessionManagement().maximumSessions(10).expiredUrl("/login?expired=true");
//		http.csrf().disable();
//		
//	}
//
////	@Bean
////	public AuthenticationManager customAuthenticationManager() throws Exception {
////		return authenticationManager();
////	}
////
////	@Autowired
////	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
////	}
//}