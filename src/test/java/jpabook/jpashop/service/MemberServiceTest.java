package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;
    @Test
    void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("김정원");
        memberService.join(member);
        memberRepository.findByName(member.getName());
    }

    @Test
    void 중복_회원_예외() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("중복");
        member2.setName("중복");

        System.out.println("member1 저장 시작");
        memberService.join(member1);
        System.out.println("member1 저장 완료");

        System.out.println("member2 저장 시작");
        memberService.join(member2);
        System.out.println("member2 저장 완료");
    }
}