package br.com.onaya.schedule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String address;
    @NonNull
    private String email;
    @NonNull
    private String cell;
    @NonNull
    private String telephone;
}
