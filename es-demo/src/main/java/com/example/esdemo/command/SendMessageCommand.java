package com.example.esdemo.command;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class SendMessageCommand implements Serializable {
    private static final long serialVersionUID = -5644799954031156649L;

    private String messageId;

    private Map<Long, String> proSource;
}
