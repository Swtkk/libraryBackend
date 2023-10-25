package library.demo.controller;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
