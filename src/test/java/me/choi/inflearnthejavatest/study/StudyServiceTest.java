package me.choi.inflearnthejavatest.study;

import me.choi.inflearnthejavatest.domain.Member;
import me.choi.inflearnthejavatest.domain.Study;
import me.choi.inflearnthejavatest.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Test
    public void createTest(@Mock MemberRepository memberRepository, @Mock StudyRepository studyRepository){
        StudyService studyService = new StudyService(memberRepository, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setName("Test");

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        assertEquals("Test", memberRepository.findById(1L).get().getName());

        doThrow(new IllegalArgumentException()).when(memberRepository).findAll();
        assertThrows(IllegalArgumentException.class, () -> {
            List<Member> all = memberRepository.findAll();
        });

        Study study = new Study();
        study.setId(1L);
        study.setName("Test");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        assertAll(
                () -> assertEquals(member, memberRepository.findById(1L).get()),
                () -> assertEquals(study, studyRepository.save(study))
        );

    }

}