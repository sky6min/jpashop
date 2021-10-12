package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional      // jpa의 모든 데이터 변경, 로직은 트랜젝션 안에서 실행되어야 함. lazy load같은거 할 수 있음.
//@AllArgsConstructor   // 생성자 자동 생성
@RequiredArgsConstructor // final만 이용해서 생성자 자동 생성.
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 목업이나 테스트 코드를 작성할 때, 직접 주입해 줄수도 있다.
    // 단점 : 실제 개발 도중에 누군가 변경할수도 있다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        //EXCEPTION
    }

    //회원 전체 조회
    @Transactional(readOnly = true)   // 조회 기능 최적화 읽기 전용 트랜젝션이니 리소스 많이 쓰지 말라고 함.  하지만, readonly하면 읽기만 처리함. 쓰기안됨.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
