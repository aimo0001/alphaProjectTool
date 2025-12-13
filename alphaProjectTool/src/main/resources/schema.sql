DROP TABLE IF EXISTS time_entry;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS roles;

CREATE TABLE project (
                         project_id BIGINT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         customer VARCHAR(255) NOT NULL,
                         start_date DATE,
                         end_date DATE,
                         status VARCHAR(50) NOT NULL,
                         PRIMARY KEY (project_id)
) ENGINE=InnoDB;

CREATE TABLE task (
                      task_id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      status VARCHAR(50) NOT NULL,
                      project_id BIGINT NOT NULL,
                      PRIMARY KEY (task_id),
                      CONSTRAINT fk_task_project
                          FOREIGN KEY (project_id)
                              REFERENCES project(project_id)
                              ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE time_entry (
                            time_entry_id BIGINT NOT NULL AUTO_INCREMENT,
                            date DATE NOT NULL,
                            hours DOUBLE NOT NULL,
                            description VARCHAR(255),
                            task_id BIGINT NOT NULL,
                            PRIMARY KEY (time_entry_id),
                            CONSTRAINT fk_time_entry_task
                                FOREIGN KEY (task_id)
                                    REFERENCES task(task_id)
                                    ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE roles (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY uk_role_name (name)
) ENGINE=InnoDB;
