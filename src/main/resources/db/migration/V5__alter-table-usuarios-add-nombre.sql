ALTER TABLE usuarios
ADD COLUMN nombre VARCHAR(255),
ADD COLUMN fecha_creacion datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD CONSTRAINT unique_login UNIQUE (login);