package com.hansol.neddit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "discussion")
public class Discussion {

    @Id @GeneratedValue
    @Column(name = "discussion_id")
    private Long discussionId;

    @Column(name = "comment")
    private String comment;

    @Builder.Default
    @Column(name = "up_vote")
    private Long upVote = 0L;

    @Builder.Default
    @Column(name = "down_vote")
    private Long downVote = 0L;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission targetSubmission;

    @ManyToOne
    @JoinColumn(name = "target_discussion_id")
    private Discussion targetDiscussion;

    @Builder.Default
    @OneToMany(mappedBy = "targetDiscussion")
    private List<Discussion> replies = new ArrayList<>();

    public void addReply(Discussion discussion) {
        discussion.setTargetDiscussion(this);
        this.replies.add(discussion);
    }
}
