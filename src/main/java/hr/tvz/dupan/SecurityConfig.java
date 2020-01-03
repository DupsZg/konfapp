package hr.tvz.dupan;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from Korisnik where username=?")
		.authoritiesByUsernameQuery("select username, authority from KorisnikPrava where username=?")
		.passwordEncoder(new BCryptPasswordEncoder(4));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/predavanja/**")
		.hasRole("USER")
		.antMatchers("/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/predavanja/novo", true)
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.and()
		.rememberMe()
		.and()
			.csrf().ignoringAntMatchers("/h2-console/**")
		.and()
			.headers().frameOptions().sameOrigin()
		.and() 
			.csrf().ignoringAntMatchers("/api/**", "/login");
	}	
}
