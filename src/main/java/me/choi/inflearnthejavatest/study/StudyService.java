package me.choi.inflearnthejavatest.study;

import com.sun.istack.NotNull;
import me.choi.inflearnthejavatest.domain.Member;
import me.choi.inflearnthejavatest.domain.Study;
import me.choi.inflearnthejavatest.member.MemberRepository;

import java.util.Optional;

public class StudyService {

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    public StudyService(@NotNull MemberRepository memberRepository,@NotNull StudyRepository studyRepository) {
        this.memberRepository = memberRepository;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study){
        Optional<Member> member = memberRepository.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id : '" + memberId + "'")));
        return studyRepository.save(study);
    }

}
