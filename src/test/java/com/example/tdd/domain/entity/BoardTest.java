package com.example.tdd.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @DisplayName("게시판 글을 생성합니다.")
    @Test
    void createBoard() throws Exception {
        //given
        String title = "게시판 제목";
        String content = "게시판 내용";
        String writer = "글쓴이";
        String password = "123456!";

        //when
        Board board = Board.create(title, content, writer, password);

        //then
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getWriter()).isEqualTo(writer);
        assertThat(board.getPassword()).isEqualTo(password);
    }

    @DisplayName("게시판 글의 제목이 null이면 예외가 발생한다.")
    @Test
    void validateTitleNull() throws Exception {
        //given
        String title = null;
        String content = "게시판 내용";
        String writer = "글쓴이";
        String password = "123456!";

        //when & then
        assertThatThrownBy(() -> Board.create(title, content, writer, password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목은 필수입니다.");
    }

    @DisplayName("게시판 글의 제목이 빈 값이면 예외가 발생한다.")
    @Test
    void validateTitleBlank() throws Exception {
        //given
        String title = "";
        String content = "게시판 내용";
        String writer = "글쓴이";
        String password = "123456!";

        //when & then
        assertThatThrownBy(() -> Board.create(title, content, writer, password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목은 필수입니다.");
    }
}