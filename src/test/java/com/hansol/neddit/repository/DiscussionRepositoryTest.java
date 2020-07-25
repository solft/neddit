package com.hansol.neddit.repository;

import com.hansol.neddit.entity.Discussion;
import com.hansol.neddit.entity.Member;
import com.hansol.neddit.entity.Submission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiscussionRepositoryTest {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void tableTest() {
        Member member = Member.builder()
                .name("two")
                .email("second@naver.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        Submission submission = Submission.builder()
                .title("second submission")
                .content("lorem ipsum")
                .member(member)
                .build();
        member.addSubmission(submission);

        submissionRepository.save(submission);

        Discussion discussion = Discussion.builder()
                .comment("hello world")
                .member(member)
                .targetSubmission(submission)
                .build();
        submission.addDiscussion(discussion);
        member.addDiscussions(discussion);

        discussionRepository.save(discussion);
    }

}