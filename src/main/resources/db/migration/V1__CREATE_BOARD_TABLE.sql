CREATE TABLE TB_BOARD (
    ID BIGSERIAL PRIMARY KEY,
    POST_TITLE TEXT NOT NULL,
    DEL_YN VARCHAR(1) NOT NULL DEFAULT 'N',
    CRT_DATE DATE NOT NULL,
    UPD_DATE DATE NULL,
    unique (POST_TITLE)
);