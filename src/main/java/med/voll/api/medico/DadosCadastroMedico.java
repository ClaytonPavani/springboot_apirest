package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
        // Vamos usar o BEAN VALIDATION para fazer a validação. Not Blank é parte do Jakarta.Validation
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String telefone,
        // OBS: Not Blank é apenas utilizado para campos STRINGS
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        // Como esse é um campo ENUM ele não será Not Blank e sim Not Null
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid
        DadosEndereco endereco) {

}
