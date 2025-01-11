package com.example.tdd.domain.controller;

import com.example.tdd.domain.dto.BoardCreateRequest;
import com.example.tdd.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestBody BoardCreateRequest request) {
        Long boardId = boardService.createBoard(request);
        URI location = URI.create("/api/boards/" + boardId);
        return ResponseEntity.created(location).build();
    }
}
