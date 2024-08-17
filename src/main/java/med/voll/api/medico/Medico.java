package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

/*
@Getter: Esta anotação é do Lombok e gera automaticamente os métodos getter para todos os campos da classe.
Isso elimina a necessidade de escrever manualmente esses métodos.

@NoArgsConstructor: Outra anotação do Lombok que gera um construtor sem argumentos para a classe.
Isso é útil, especialmente quando você precisa de um construtor padrão para frameworks de persistência, como JPA.

@AllArgsConstructor: Também do Lombok, gera um construtor que aceita um argumento para cada campo na classe.
Isso facilita a criação de instâncias da classe com todos os seus campos inicializados.

@EqualsAndHashCode(of = "id"): Essa anotação gera os métodos equals e hashCode baseados no campo id da classe.
Isso é importante para garantir a correta comparação e uso em coleções (como HashSet, HashMap) com base no id.


 */

// vamos mapear nossa entidade com anotações
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    // Abaixo temos o método construtor
    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;

    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        // Vamos pegar o DTO que está chegando como parâmetro e usar os campos para atualizar o OBJETO
        // Temos que criar as condições para que o nome sendo diferente de null ele será atualizado
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            // Vamos criar um método na classe endereço para atualizar os parâmetros do DTO de endereço
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
