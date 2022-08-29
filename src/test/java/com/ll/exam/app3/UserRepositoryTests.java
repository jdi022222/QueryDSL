package com.ll.exam.app3;


import com.ll.exam.app3.user.entity.SiteUser;
import com.ll.exam.app3.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // 테스트 모드 활성화
class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

//    @BeforeEach
//    void beforeEach(){
//        userRepository.truncate();
//        SiteUser u1 = SiteUser.builder()
//                .username("user1")
//                .password("{noop}1234")
//                .email("user1@test.com")
//                .build();
//
//        SiteUser u2 = SiteUser.builder()
//                .username("user2")
//                .password("{noop}1234")
//                .email("user2@test.com")
//                .build();
//        // SiteUser u2 = new SiteUser(null, "user2", "{noop}1234", "user2@test.com");
//
//        userRepository.saveAll(Arrays.asList(u1, u2));
//    }

    @Test
    @DisplayName("회원 생성")
    void t1() {
        SiteUser u3 = SiteUser.builder()
                .username("user3")
                .password("{noop}1234")
                .email("user3@test.com")
                .build();

        SiteUser u4 = SiteUser.builder()
                .username("user4")
                .password("{noop}1234")
                .email("user4@test.com")
                .build();

        userRepository.saveAll(Arrays.asList(u3, u4));
    }

    @Test
    @DisplayName("1번 회원 찾기")
    void t2() {
        SiteUser u1 = userRepository.getQslUser(1L);

        assertThat(u1.getId()).isEqualTo(1L);
        assertThat(u1.getUsername()).isEqualTo("user1");
        assertThat(u1.getPassword()).isEqualTo("{noop}1234");
        assertThat(u1.getEmail()).isEqualTo("user1@test.com");
    }
}