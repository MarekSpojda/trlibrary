package pl.marekspojda.TrLibrary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.marekspojda.TrLibrary.converter.StringToDateConverter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackages = { "pl.marekspojda.TrLibrary.repository" })
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder getPasswordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(getStringToDateConverter());
	}

	@Bean
	public StringToDateConverter getStringToDateConverter() {
		return new StringToDateConverter();
	}

	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().and().formLogin().successHandler(successHandler).loginPage("/login")
				.permitAll();
	}
}
