CREATE TABLE prontuario(
    id INT NOT NULL AUTO_INCREMENT primary key,
    descricao VARCHAR(255) NOT NULL
);
CREATE TABLE usuario(
    id INT NOT NULL AUTO_INCREMENT primary key,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    nome VARCHAR(255) ,
    email VARCHAR(255) ,
    telefone VARCHAR(255) ,
    crm VARCHAR(255),
    cpf VARCHAR(255),
    especialidade VARCHAR(255),
    user_type VARCHAR(255),
    prontuario_id INT,
    is_medico BOOLEAN,
    is_ativo BOOLEAN,
    CONSTRAINT FOREIGN KEY (prontuario_id) REFERENCES prontuario(id)
);
CREATE TABLE consulta(
    id INT NOT NULL AUTO_INCREMENT primary key,
    paciente_id INT,
    medico_id INT,
    CONSTRAINT FOREIGN KEY (paciente_id) REFERENCES usuario(id),
    CONSTRAINT FOREIGN KEY (medico_id) REFERENCES usuario(id)
);
CREATE TABLE feedback(
    id INT NOT NULL AUTO_INCREMENT primary key,
    descricao VARCHAR(255) NOT NULL,
    medico_id INT,
    CONSTRAINT FOREIGN KEY (medico_id) REFERENCES feedback(id)    
);
CREATE TABLE prescricao(
    id INT NOT NULL AUTO_INCREMENT primary key,
    descricao VARCHAR(255) NOT NULL,
    medico_id INT,
    paciente_id INT,
    CONSTRAINT FOREIGN KEY (medico_id) REFERENCES usuario(id),
    CONSTRAINT FOREIGN KEY (paciente_id) REFERENCES usuario(id)
);
CREATE TABLE receita(
    id INT NOT NULL AUTO_INCREMENT primary key,
    descricao VARCHAR(255) NOT NULL,
    medico_id INT,
    paciente_id INT,
    CONSTRAINT FOREIGN KEY (medico_id) REFERENCES usuario(id),
    CONSTRAINT FOREIGN KEY (paciente_id) REFERENCES usuario(id)
);