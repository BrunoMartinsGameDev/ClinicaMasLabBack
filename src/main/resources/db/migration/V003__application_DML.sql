-- Inserindo médicos
INSERT INTO usuario (login, senha, role, nome, email, telefone, crm, especialidade, prontuario_id, isMedico, isAtivo)
VALUES
('medico1', 'senha123', 'MEDICO', 'Dr. João', 'joao@hospital.com', '123456789', 'CRM12345', 'Cardiologia', NULL, TRUE, TRUE),
('medico2', 'senha123', 'MEDICO', 'Dra. Maria', 'maria@hospital.com', '987654321', 'CRM67890', 'Pediatria', NULL, TRUE, TRUE);

-- Inserindo pacientes
INSERT INTO usuario (login, senha, role, nome, email, telefone, cpf, prontuario_id, isMedico, isAtivo)
VALUES
('paciente1', 'senha123', 'PACIENTE', 'José da Silva', 'jose@paciente.com', '111222333', 'CPF123456789', 1, FALSE, TRUE),
('paciente2', 'senha123', 'PACIENTE', 'Ana de Souza', 'ana@paciente.com', '444555666', 'CPF987654321', 2, FALSE, TRUE);


-- Inserindo consultas
INSERT INTO consulta (paciente_id, medico_id)
VALUES 
(1, 1),  -- José da Silva com Dr. João
(2, 2);  -- Ana de Souza com Dra. Maria

-- Inserindo feedbacks
INSERT INTO feedback (descricao, medico_id)
VALUES 
('Muito satisfeito com o atendimento.', 1),
('Ótimo atendimento, recomendo.', 2);

-- Inserindo prescrições
INSERT INTO prescricao (descricao, medico_id, paciente_id)
VALUES 
('Tomar 1 comprimido de Paracetamol a cada 6 horas.', 1, 1),  -- Dr. João prescrevendo para José da Silva
('Usar pomada 2 vezes ao dia.', 2, 2);  -- Dra. Maria prescrevendo para Ana de Souza

-- Inserindo receitas
INSERT INTO receita (descricao, medico_id, paciente_id)
VALUES 
('Receita de analgésicos.', 1, 1),  -- Dr. João prescrevendo para José da Silva
('Receita de antibióticos.', 2, 2);  -- Dra. Maria prescrevendo para Ana de Souza
