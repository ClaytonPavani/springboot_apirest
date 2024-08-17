package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    // Vamos fazer a injeção de dependência através do autowired
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    // Anotação Transactional é um método de insert no banco de dados, temos que ter transação no banco de dados;
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    // Parâmetro abaixo @PageableDefault - Definimos um padrão default para exibir - Com Parâmetros de size 10, Sort = Nome
    // <DadosListagemMedico> --> é o nosso DTO
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        // Vamos usar as STREAM para fazer um mapeamento
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    //Vamos criar o método PUT para atualizar nossas requisições
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        // vamos criar uma variável para chamar o banco de dados e sobrescrever esses dados atualizando:
        // Usando o método getReferenteById() buscamos a referência dessa entidade no repositório pelo ID
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    // Vamos criar um método para excluir, com a anotação do verbo HTTP de requisição para deletar
    // O parâmetro dinâmico está inserido entre {} chaves que inserimos no delete mapping {id}
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
