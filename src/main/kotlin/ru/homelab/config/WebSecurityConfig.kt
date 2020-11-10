package ru.homelab.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import ru.homelab.server.UserService

@Configuration
@EnableWebSecurity
open class WebSecurityConfig(
    private val userService: UserService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/", "/registration")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }
}