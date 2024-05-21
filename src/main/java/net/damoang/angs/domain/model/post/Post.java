package net.damoang.angs.domain.model.post;

import lombok.RequiredArgsConstructor;
import net.damoang.angs.domain.model.comment.Comment;
import net.damoang.angs.domain.model.user.User;
import org.apache.commons.lang3.Validate;

@RequiredArgsConstructor
public class Post {
    private User user;
    private PostContent postContent;
    private Comment comment;

    /**
     * 차단한 사용자 정보를 조회합니다.
     * @param userId
     */
    public void blockedUser(final String userId) {
        Validate.notEmpty(userId, "아이디는 필수입니다.");
        this.user = user.assignBlockedUser(userId);
    }

}
