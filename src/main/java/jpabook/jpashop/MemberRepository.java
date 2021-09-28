package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    // 엔티티 매니저를 자동 주입.
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();   // member를 반환하지 않는 이유 : command query 분리 원칙, 사이드 이펙트를 일으키는 커맨드 이기때문에 ID정도만 리턴
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
