package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
// JPA는 트랜잭션 하에서 값 수정을 해야함! --> @Transactional
public class MemberService {


    private final MemberRepository memberRepository;

    /*
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     @RequiredArgsConstructor 가 이 생성자 주입을 시켜줌
     */

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
       List<Member> findMembers = memberRepository.findByName(member.getUsername());
       // 만약 여러 사용자가 동시에 같은 이름으로 등록할 수 있는데 이럴때 문제가 될 수 있다. 그래서 이름은 유니크 제약조건으로 잡아주면 좋다
        //--> 유니크 제약조건 찾아보기
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    @Transactional(readOnly = true) // 조회는 읽기전용 모드로 설정하면 성능에 도움이 됨
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.find(memberId);
    }

}
