package me.choi.inflearnthejavatest.member;

import me.choi.inflearnthejavatest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
