package net.damoang.angs.application.api.v1.board.dao;

import net.damoang.angs.application.api.v1.board.dto.BoardDTO;
import net.damoang.angs.domain.model.post.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Post> findPostAll(@Param("search") BoardDTO boardDTO);
}
