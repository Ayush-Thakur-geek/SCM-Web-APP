package com.contact_manager.scm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String content;
    @Builder.Default
    private String type = MessageType.INFO.toString();
    @Builder.Default
    private String color = MessageColor.blue.toString();
}
