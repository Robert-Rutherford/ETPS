
drop database etps_db;

use etps_db;

# submissions
INSERT INTO submissions (deadline, status) VALUES
('2020-01-01','approved'),
('2020-01-01','approved'),
('2020-01-01','approved');

# providers table (MUST BE GENERATED FIRST)
INSERT INTO providers ( prov_id, provider_name,description, submission_id) VALUES
(1, 'TWC', 'Admins of the TWC/ this column should never appear',null),
(802,'ACCD Northwest Vista Collage','an alamo collage',1),
(803,'ACCD Palo Alto Collage','an alamo collage',2),
(900,'Codeup','an alamo school',3);

# users table
INSERT INTO users (email, is_admin, password,  username, user_provider_id) VALUES
('testuser@email.com', false,'password1','testuser',802),
('testuser2@email.com', false,'password2','testuser2',803),
('testAdmin@email.com', true,'pass','testAdmin',1),
('testuser3@email.com', false,'pass','testuser3',900);

# campus table
INSERT INTO campuses (camp_id, name, provider_id) VALUES
(1952,'Main Campus',2),
(1496,'Main Campus',3);

# programs
INSERT INTO programs ( prog_id, name,etp_code_id, description, campus_id) VALUES
(8841,'Pharmacy technician','3164','Pharmacy Technician Fast Track Certificate',1),
(8842,'Computer systems network','3174','AAS Network Administrator',1),
(8843,'Network and Systems','3173','Advanced Cisco Networking Technologies Level 1 Certificate',1),
(8844,'Computer systems network','3172','Certificate Information Security Assurance',1),
(8851,'Computer and Information','Netw0300','Network Administrator AAS',2),
(8852,'Computer and Information','Comp0300','Computer Programing AAS',2);


