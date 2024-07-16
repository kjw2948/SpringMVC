package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jpabook.jpashop.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { // 전부 조회해야 할 때는 쿼리 생성 필요함 -> JPQL 쿼리
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // jpql => 엔티티객체를 대상으로 쿼리를 날림
        // sql ==> 테이블을 대상으로 쿼리를 날림
        // from에 대상이 엔티티 객체냐 테이블이냐 차이
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // Query에서 말하는 name이 어떤 name인지 setting 해주는 것
                .getResultList();
    }
}
