package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;
import web.service.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private UserService us;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        if (us.listUsers().isEmpty()) {
            //Если база пользователей пуста - используем по умолчанию: [ADMIN:123]
            auth.inMemoryAuthentication().withUser("ADMIN").password("$2y$10$Oedz6pMQNyI9iLjaPn15E.SHIIplJNxNZ7PMcsspjUf5xqv2Jes7O").authorities("ROLE_ADMIN", "ROLE_USER");
        } else {
            //При наличии в базе пользователей - аутентификация по содержимому БД
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("ADMIN").password("$2y$10$Oedz6pMQNyI9iLjaPn15E.SHIIplJNxNZ7PMcsspjUf5xqv2Jes7O").authorities("ROLE_ADMIN", "ROLE_USER").build());
        //more as above
        return inMemoryUserDetailsManager;
    }
   */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()

                .loginPage("/login")   // указываем страницу с формой логина
                .successHandler(loginSuccessHandler)//указываем логику обработки при логине//new LoginSuccessHandler()
                .loginProcessingUrl("/login")     // указываем action с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password") // Указываем параметры логина и пароля с формы логина

                .permitAll();// даем доступ к форме логина всем

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                //для Юзера
                .antMatchers("/api/user").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                // защищенные Админа
                .antMatchers("/api/users/**").access("hasAnyRole('ROLE_ADMIN')").anyRequest().authenticated()
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


