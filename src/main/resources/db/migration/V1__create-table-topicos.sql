CREATE TABLE topicos (
    id bigint not null auto_increment,
    titulo VARCHAR(255) NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion datetime not null,
    status VARCHAR(255) not null,
    usuario_id VARCHAR(255) not null,
    curso_id VARCHAR(255) not null,

    PRIMARY KEY (id)
);