package com.example.demo.config;

/**
 * Spring Security 的配置类
 * @author libingbin2015@aliyun.com
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = false)
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        // 所有的请求，都需要经过HTTP basic认证
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 明文编码器。这是一个不做任何操作的密码编码器，是Spring提供给我们做明文测试的。A password encoder that does nothing. Useful for testing where working with plain text
        return NoOpPasswordEncoder.getInstance();
    }

   // @Autowired
   // private ConsumerService consumerService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(this.consumerService).passwordEncoder(this.passwordEncoder());
    }*/



}
