package jpabook.jpashop;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;


@Repository // 컴포넌트 대상이 되는 어노테이션 중 하나
public class MemberRepository {
    // 엔티티 매니저가 필요함
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
