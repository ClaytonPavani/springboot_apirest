package med.voll.api.medico;

// Aqui no nosso DTO adicionamos os parâmetros que queremos
public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    // Vamos declarar um construtor do DadosListagemMedicos, que irá receber como parâmetro objeto do tipo médico
    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
