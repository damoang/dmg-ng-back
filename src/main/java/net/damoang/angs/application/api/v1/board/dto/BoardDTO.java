package net.damoang.angs.application.api.v1.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BoardDTO implements Serializable {

    private String totalCount;
    private String rno;
    private String id;
    private String postTitle;
    private Date creDate;
    private Date updDate;
}
