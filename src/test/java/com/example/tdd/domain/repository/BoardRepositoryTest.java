package com.example.tdd.domain.repository;

import com.example.tdd.domain.entity.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("게시글을 저장한다.")
    @Test
    void save() throws Exception {
        //given
        Board board = Board.create("제목", "내용", "이영섭", "123456!");

        //when
        Board savedBoard = boardRepository.save(board);

        // 영속성 컨텍스트를 초기화
        em.flush();
        em.clear();

        // then
        Board foundBoard = boardRepository.findById(savedBoard.getId())
                .orElseThrow();

        assertThat(foundBoard)
                .extracting("title", "content", "writer", "password")
                .containsExactly(
                        board.getTitle(),
                        board.getContent(),
                        board.getWriter(),
                        board.getPassword()
                );
    }
}