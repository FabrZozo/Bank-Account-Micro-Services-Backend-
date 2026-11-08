package fabrice.accountservice.model;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
