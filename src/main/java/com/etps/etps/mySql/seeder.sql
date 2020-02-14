
drop database etps_db;

use etps_db;

# providers table (MUST BE GENERATED FIRST)
INSERT INTO providers (id, provider_name, description) VALUES
(1, 'TWC', 'Admins of the TWC/ this column should never appear'),
(802,'ACCD Northwest Vista Collage','an alamo collage'),
(803,'ACCD Palo Alto Collage','an alamo collage'),
(900,'Codeup','an alamo school');

# users table
INSERT INTO users (email, is_admin, password, username, provider_id) VALUES
('testuser@email.com', false,'password1','testuser',802),
('testuser2@email.com', false,'password2','testuser2',803),
('testAdmin@email.com', true,'pass','testAdmin',1),
('testuser3@email.com', false,'pass','testuser3',900);

# campus table
INSERT INTO campuses (id, name, provider_id) VALUES
(1952,'Main Campus',802),
(1496,'Main Campus',803);

# programs
INSERT INTO programs (id, description, etp_code_id, name, campus_id) VALUES
(8841,'Pharmacy technician','3164','Pharmacy Technician Fast Track Certificate',1952),
(8842,'Computer systems network','3174','AAS Network Administrator',1952),
(8843,'Network and Systems','3173','Advanced Cisco Networking Technologies Level 1 Certificate',1952),
(8844,'Computer systems network','3172','Certificate Information Security Assurance',1952),
(8851,'Computer and Information','Netw0300','Network Administrator AAS',1496),
(8852,'Computer and Information','Comp0300','Computer Programing AAS',1496);

# submissions
INSERT INTO submissions (deadline, status, campus_id, program_id, provider_id) VALUES
('2020-01-01','approved',1952,8841,802),
('2020-01-01','approved',1952,8842,802),
('2020-01-01','approved',1952,8843,802),
('2020-01-01','approved',1952,8844,802),
('2020-01-01','approved',1952,8851,803),
('2020-01-01','approved',1952,8852,803);
