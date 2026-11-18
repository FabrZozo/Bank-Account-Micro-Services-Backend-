package fabrice.customerservice2.dtos;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String firstname;
    private String lastname;
    private String email;

}
