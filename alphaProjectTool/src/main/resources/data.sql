INSERT INTO project (name, customer, start_date, end_date, status) VALUES
                                                                       ('Alpha Website', 'Alpha Solutions', '2025-11-01', '2025-12-20', 'IN_PROGRESS'),
                                                                       ('Beta App', 'Beta Corp', '2025-10-10', NULL, 'ON_HOLD'),
                                                                       ('Internal Tool', 'Alpha Solutions', '2025-09-01', '2025-11-30', 'COMPLETED');

INSERT INTO task (name, description, status, project_id) VALUES
                                                             ('Database setup', 'Create schema and test data', 'COMPLETED', 1),
                                                             ('Project CRUD', 'Create and edit projects', 'IN_PROGRESS', 1),
                                                             ('Reporting', 'Generate project reports', 'NOT_STARTED', 1),

                                                             ('Login system', 'Spring Security login', 'IN_PROGRESS', 2),
                                                             ('Time registration', 'Register hours on tasks', 'NOT_STARTED', 2),

                                                             ('Cleanup', 'Refactor and polish code', 'COMPLETED', 3);

INSERT INTO time_entry (date, hours, description, task_id) VALUES
                                                               ('2025-11-02', 2.5, 'Initial database design', 1),
                                                               ('2025-11-03', 1.5, 'Constraints and relations', 1),
                                                               ('2025-11-10', 3.0, 'Project controller', 2),
                                                               ('2025-11-11', 2.0, 'Thymeleaf forms', 2);

INSERT INTO roles (name) VALUES
                             ('ADMIN'),
                             ('USER');
