package com.example.tdd.domain.service;

import com.example.tdd.domain.dto.BoardCreateRequest;
import com.example.tdd.domain.entity.Board;
import com.example.tdd.domain.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Transactional
    @DisplayName("게시글을 등록한다.")
    @Test
    void createBoard() throws Exception {
        //given
        BoardCreateRequest request = new BoardCreateRequest(
                "제목", "내용", "이영섭", "123456!"
        );

        Board board = Board.create(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword()
        );

        given(boardRepository.save(any(Board.class)))
                .willReturn(board);

        // when
        Long boardId = boardService.createBoard(request);

        // then
        ArgumentCaptor<Board> boardCaptor = ArgumentCaptor.forClass(Board.class);
        verify(boardRepository, times(1)).save(boardCaptor.capture());
        Board savedBoard = boardCaptor.getValue();
        assertThat(savedBoard)
                .extracting("title", "content", "writer", "password")
                .containsExactly(
                        request.getTitle(),
                        request.getContent(),
                        request.getWriter(),
                        request.getPassword()
                );
    }
}