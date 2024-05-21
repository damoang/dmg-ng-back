package net.damoang.angs.application.api.v1.board;

import lombok.RequiredArgsConstructor;
import net.damoang.angs.application.api.v1.board.dto.BoardDTO;
import net.damoang.angs.application.api.v1.board.service.BoardService;
import net.damoang.angs.domain.model.post.Post;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/board"
        , consumes = MediaType.APPLICATION_JSON_VALUE
        , produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BoardController {

    BoardService boardService;
    /**
     * 게시판의 게시글 목록을 조회합니다
     * @return List<Post> 게시글 목록
     */
    @GetMapping("/post")
    public List<Post> findBoardPosts(@RequestBody BoardDTO boardDTO) {
        return boardService.findBoardPosts(boardDTO);
    }

    /**
     * 게시판의 게시글을 조회합니다
     * @return Post 게시글 상세
     * @see net.damoang.angs.domain.model.comment.Comment
     */
    @GetMapping("/post/{postId}")
    public Post findBoardPostById() {
        return null;
    }
}
