package com.example.tdd.domain.entity;

import com.example.tdd.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "boards")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String password;

    private Board(String title, String content, String writer, String password) {
        validateTitle(title);
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    public static Board create(String title, String content, String writer, String password) {
        return new Board(title, content, writer, password);
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }
    }
}
