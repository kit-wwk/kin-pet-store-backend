package com.kinpetstore.restbackend.entity;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message extends BaseEntity {
    @Column
    private Locale locale;

    @Column(name = "code", length = 1000)
    private String code;

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;
}