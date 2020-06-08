# drop database etps_db;
#
use etps_db;

# submissions
INSERT INTO submissions (deadline, status)
VALUES ('2020-01-01', 'approved'),
       ('2020-01-01', 'approved'),
       ('2020-01-01', 'approved');

# providers table (MUST BE GENERATED FIRST)
# need to add: provider url, address 1, addres 2, institution type, WDA
INSERT INTO providers (prov_id, provider_name, description, submission_id)
VALUES (1, 'TWC', 'Admins of the TWC/ this column should never appear', null),
       (802, 'ACCD Northwest Vista Collage', 'An Alamo College', 1),
       (803, 'ACCD Palo Alto Collage', 'An Alamo College', 2),
       (900, 'Codeup', 'An Alamo Cchool', 3);

# campus table
INSERT INTO campuses (camp_id, name, provider_id)
VALUES (1952, 'Main Campus', 2),
       (1496, 'Main Campus', 3);

# programs
# need to move: etp_code_id to table 11
INSERT INTO programs (prog_id, name, etp_code_id, description, campus_id)
VALUES (8841, 'Pharmacy technician', '3164', 'Pharmacy Technician Fast Track Certificate', 1),
       (8842, 'Computer systems network', '3174', 'AAS Network Administrator', 1),
       (8843, 'Network and Systems', '3173', 'Advanced Cisco Networking Technologies Level 1 Certificate', 1),
       (8844, 'Computer systems network', '3172', 'Certificate Information Security Assurance', 1),
       (8851, 'Computer and Information', 'Netw0300', 'Network Administrator AAS', 2),
       (8852, 'Computer and Information', 'Comp0300', 'Computer Programing AAS', 2);



# new Tables
# table 1: TWIST information
# values: TWIST provider ID, TWIST Program ID, Action, program id (key)

# table 2: provider address info
# values: address 1, address 2, AddressCity, AddressState, AddressZipCode, County, provider ID (key)

# table 3: admin contact info (provider)
# values: institution type, Admin Contact Person, Admin Job Title,  Admin Phone, Admin Phone Extension,
#           provider id (key)

# table 4: financial contact info (provider)
# values: Financial Aid Name, Financial aid phone, Financial Aid Email, provider id (key)

# table 5: additional contact info (provider)
# values: additonal contact person, additional contact job title, additional contact phone,
#           additional contact phone extension, additional contact email, provider id (key)

# table 6: ids and acks
# values: THECBPrgmID, TEAPrgmID, otherPrgmID, FERPAAck, WIOAAck, OtherAck1, OtherAck2, OtherAck3, OtherAck4,
#           provider id (key)

# table 7: campus address info
# values: address 1, address 2, campus city, campus state, campus zip code, campus county, campus id (key)

# table 8: campus contact info
# values: campus contact Name, campus contact job title, campus phone, campus phone extension, campus email,
#           campus id (key)

# table 9: campus additional contact info
# values: additional contact name, additional contact jonb title, additional contact phone, phone extension, email,
#           campus id (key)

# table 10: campus additional info
# values: info public transit, on sit child care, flexible hours,

# table 11: program additional info
# values: CSCPrgmID, ETPCodeID, CIPCode, PellEligble, PreRequisites, programURL, program outcome, Associate Credit Name,
#           length contact hours, length weeks, program format, ONET1, ONET2, ONET3, student data,
#           number of apprentices, program id (key)

# table 12: Tuition and costs
# values: cost required Tuition fee description, costReq booksSupplies, book supplies description, CostOptOther,
#           costOptOtherDescription, ETP Report type, Out of District Cost Req tuition fee,
#           Out of District Cost Req tuition fee description, program id (key)


# users table
# INSERT INTO users (email, is_admin, password,  username, user_provider_id) VALUES
# ('testuser@email.com', false,'password1','testuser',802),
# ('testuser2@email.com', false,'password2','testuser2',803),
# ('testAdmin@email.com', true,'pass','testAdmin',1),
# ('testuser3@email.com', false,'pass','testuser3',900);