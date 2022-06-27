package com.tecapro.demobinhan.configuration.security;

import com.tecapro.demobinhan.configuration.custom.CustomAccessDeniedHandler;
import com.tecapro.demobinhan.configuration.custom.RestAuthenticationEntryPoint;
import com.tecapro.demobinhan.configuration.filter.JwtAuthenticationFilter;
import com.tecapro.demobinhan.service.role.RoleService;
import com.tecapro.demobinhan.service.shoppingCart.IShoppingCartService;
import com.tecapro.demobinhan.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @PostConstruct
//    public void init() {
//        List<User> users = (List<User>) userService.findAll();
//        List<Role> roleList = (List<Role>) roleService.findAll();
//        if (roleList.isEmpty()) {
//            Role roleAdmin = new Role();
//            roleAdmin.setId(1L);
//            roleAdmin.setName(RoleName.ROLE_ADMIN.toString());
//            roleService.save(roleAdmin);
//            Role roleCoach = new Role();
//            roleCoach.setId(2L);
//            roleCoach.setName(RoleName.ROLE_USER.toString());
//            roleService.save(roleCoach);
//        }
//        if (users.isEmpty()) {
//            User admin = new User();
//            Set<Role> roles = new HashSet<>();
//            roles.add(new Role(1L, RoleName.ROLE_ADMIN.toString()));
//            admin.setEmail("admin");
//            admin.setFullName("admin");
//            admin.setPassword("123456");
//            admin.setRoles(roles);
//            userService.save(admin);
//            ShoppingCart shoppingCart = new ShoppingCart();
//            shoppingCart.setUser(admin);
//            shoppingCartService.save(shoppingCart);
//        }
//    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());
        http.authorizeRequests()
                .antMatchers("/", "/login", "/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
