package com.kinpetstore.restbackend.domain.request;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String givenName;
    private String nickname;
    private String name;
    private String picture;
}