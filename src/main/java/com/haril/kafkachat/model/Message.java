package com.haril.kafkachat.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    private String author;
    private String content;
    private String timestamp;

}
