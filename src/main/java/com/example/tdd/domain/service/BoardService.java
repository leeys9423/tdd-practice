package com.example.tdd.domain.service;

import com.example.tdd.domain.dto.BoardCreateRequest;
import com.example.tdd.domain.entity.Board;
import com.example.tdd.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(BoardCreateRequest request) {
        return boardRepository.save(Board.create(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword()
        )).getId();
    }
}
