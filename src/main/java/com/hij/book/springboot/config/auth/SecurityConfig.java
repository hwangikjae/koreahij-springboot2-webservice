package com.hij.book.springboot.config.auth;

import com.hij.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity//spring security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 disabled 한다.
                .and()
                .authorizeRequests()//url별 권한 관리를 설정하는 옵션의 시작점.
                //authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.
                .antMatchers("/", "/css/**", "/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/posts").hasRole(Role.USER.name())    //
                //권한관리대상을 지정하는 옵션이다.
                //URL, HTTP 메소드별로 관리가 가능하다.
                //"/"등 지정된 url들은 permitall() 옵션을 통해 전체열람권한을 줬다.
                //"/api/v1/**"주소를 가진 api는 user구너한을 가진 사람만 가능하도록 했다.
                .anyRequest().authenticated()//설정된 값 이외 나머지 url을 나타낸다.
                //여기서는 authenticated()를 추가하여 나머지 url들은 모두 인증된 사용자(즉 로그인한 사용자)에게만 허용하게 했다.
                .and()
                .logout()
                .logoutSuccessUrl("/")  //로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공시 /주소로 이동한다.
                .and()
                .oauth2Login()//oauth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint()//outh2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                .userService(customOauth2UserService);//소셜 로그인 성공시 후속조치를 진행할 userservice 인터페이스의 구현체를 등록한다.
        //리소스서버 (즉 소셜서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수있다.
    }
}
