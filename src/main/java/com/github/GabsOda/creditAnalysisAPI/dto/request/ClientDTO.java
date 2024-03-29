package com.github.GabsOda.creditAnalysisAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    @NotEmpty
    @Size(min = 4, max = 200)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    @Size(min = 12)
    private String rg;

    @NotEmpty
    private String address;

    @Valid
    @NotNull
    private Double income;

    @NotEmpty
    private String password;

}
