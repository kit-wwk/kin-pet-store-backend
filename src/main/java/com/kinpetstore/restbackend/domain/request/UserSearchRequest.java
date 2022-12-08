package com.kinpetstore.restbackend.domain.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchRequest {
    private String sub;
    private String givenName;
    private String nickname;
    private String name;
    private String picture;
    private String email;
}
