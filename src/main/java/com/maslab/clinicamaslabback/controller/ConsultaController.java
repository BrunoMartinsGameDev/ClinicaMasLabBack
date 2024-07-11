package com.maslab.clinicamaslabback.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maslab.clinicamaslabback.model.Consulta;
import com.maslab.clinicamaslabback.model.Medico;
import com.maslab.clinicamaslabback.model.Paciente;
import com.maslab.clinicamaslabback.model.user.Usuario;
import com.maslab.clinicamaslabback.repository.ConsultaRepository;
import com.maslab.clinicamaslabback.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Consulta")
@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public List<Consulta> GetAllConsultas() {
        return consultaRepository.findAll();
    }

    @GetMapping("/findby-paciente-id")
    public Set<Consulta> getConsultasByPacienteID() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Obtém a autenticação do contexto de segurança

        Usuario usuario = (Usuario) auth.getPrincipal();
        // Obtém o usuário autenticado

        Optional<Paciente> optionalPaciente = usuarioRepository.findByIdAndIsMedicoFalse(usuario.getId());
        // Busca um paciente pelo ID do usuário autenticado e verifica se não é um médico

        if (optionalPaciente.isPresent()) {
            
            Paciente paciente = optionalPaciente.get();

            return paciente.getConsultas();
            // Retorna o conjunto de consultas do paciente encontrado

        } else {
            throw new IndexOutOfBoundsException("Paciente não cadastrado");
            // Lança uma exceção se o paciente não estiver cadastrado
        }
    }


    @PostMapping
    public Consulta CreateConsulta(@RequestBody Consulta consulta) {

        Optional<Paciente> optionalPaciente = usuarioRepository.findByIdAndIsMedicoFalse(consulta.getPaciente().getId());
        Optional<Medico> optionalMedico = usuarioRepository.findByIdAndIsMedicoTrue(consulta.getMedico().getId());
        // Busca o paciente e o médico pelos seus respectivos IDs e verifica seus papéis

        if (optionalPaciente.isPresent() && optionalMedico.isPresent()) {
            
            consulta.setPaciente(optionalPaciente.get());
            consulta.setMedico(optionalMedico.get());
            // Define o paciente e o médico da consulta se ambos estiverem cadastrado

        } else {
            throw new IndexOutOfBoundsException("Paciente ou médico não cadastrados");
        }

        return consultaRepository.save(consulta);
        // Salva a nova consulta no banco de dados e a retorna
    }

    @PutMapping("/{id}")
    public Consulta putConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
    // Recebe o ID da consulta e os novos dados da consulta no corpo da requisição

        Optional<Consulta> consultaData = consultaRepository.findById(id);
        // Busca a consulta existente pelo ID

        if (consultaData.isPresent()){
            Consulta _consulta = consultaData.get();
            BeanUtils.copyProperties(consulta, _consulta, "id");
            // Copia as propriedades da nova consulta para a consulta existente, exceto o ID

            return consultaRepository.save(_consulta);
            // Salva a consulta atualizada no banco de dados e a retorna

        } else {
            throw new IndexOutOfBoundsException("Consulta não cadastrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteConsulta(@PathVariable Long id) {
    // Recebe o ID da consulta a ser excluída

        Optional<Consulta> _consulta = consultaRepository.findById(id);
        // Busca a consulta existente pelo ID

        if (_consulta.isPresent()) {
            consultaRepository.deleteById(id);
            // Deleta a consulta do banco de dados
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // Retorna uma resposta de status 204 (No Content) indicando que a exclusão foi bem-sucedida

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // Retorna uma resposta de status 404 (Not Found) se a consulta não for encontrada
        }
    }
}
