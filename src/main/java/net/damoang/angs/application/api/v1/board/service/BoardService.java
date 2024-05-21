package net.damoang.angs.application.api.v1.board.service;

import lombok.RequiredArgsConstructor;
import net.damoang.angs.application.api.v1.board.dto.BoardDTO;
import net.damoang.angs.application.api.v1.board.dao.BoardMapper;
import net.damoang.angs.domain.model.post.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private BoardMapper boardMapper;

    public List<Post> findBoardPosts(BoardDTO boardDTO) {
//        List<Post> posts = postRepository.findPostAll(boardDTO);
        return null;
    }
}
