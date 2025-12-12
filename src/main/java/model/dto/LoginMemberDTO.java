package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginMemberDTO {
    private String name;

    private String password;

    private String position;
}
