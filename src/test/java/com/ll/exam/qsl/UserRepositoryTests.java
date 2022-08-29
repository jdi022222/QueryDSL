package com.ll.exam.qsl;


import com.ll.exam.qsl.user.entity.SiteUser;
import com.ll.exam.qsl.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
// 클래스에 @Tran을 붙이면 클래스의 각 테스트 케이스에 전부 @Tran이 붙은 것과 동일
// @Test + @Trsn 조합은 롤백 모드 활성화화@ActiveProfiles("test") // 테스트 모드 활성화
@ActiveProfiles("test") // 테스트 모드 활성화
class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

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
    @DisplayName("1번 회원을 Qsl로 가져오기")
    void t2() {
        SiteUser u1 = userRepository.getQslUser(1L);
        assertThat(u1.getId()).isEqualTo(1L);
        assertThat(u1.getUsername()).isEqualTo("user1");
        assertThat(u1.getEmail()).isEqualTo("user1@test.com");
        assertThat(u1.getPassword()).isEqualTo("{noop}1234");
    }
//    @Test
//    @DisplayName("2번 회원을 Qsl로 가져오기")
//    void t3() {
//        SiteUser u2 = userRepository.getQslUser(2L);
//        assertThat(u2.getId()).isEqualTo(2L);
//        assertThat(u2.getUsername()).isEqualTo("user2");
//        assertThat(u2.getEmail()).isEqualTo("user2@test.com");
//        assertThat(u2.getPassword()).isEqualTo("{noop}1234");
//    }

    @Test
    @DisplayName("모든 회원의 수")
    void t4() {
        int count = userRepository.getQslCount();

        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("가장 오래된 회원 1명")
    void t5() {
        SiteUser u1 = userRepository.getQslUserOrderByIdAscOne();

        assertThat(u1.getId()).isEqualTo(1L);
        assertThat(u1.getUsername()).isEqualTo("user1");
        assertThat(u1.getEmail()).isEqualTo("user1@test.com");
        assertThat(u1.getPassword()).isEqualTo("{noop}1234");
    }
}