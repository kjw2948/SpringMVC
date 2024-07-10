package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;
    @Autowired private EntityManager em;
    @Test
    void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("김정원");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("중복");
        member2.setName("중복");

        memberService.join(member1);
        try {
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }

        fail("예외가 발생한다");
    }
}