drop database etps_db;
#
use etps_db;

# submissions
INSERT INTO submissions (deadline, status)
VALUES ('2020-01-01', 'approved'),
       ('2020-01-01', 'approved'),
       ('2020-01-01', 'approved');

# providers table (MUST BE GENERATED FIRST)
# need to add: provider url, institution type, WDA
INSERT INTO providers (prov_id, provider_name, description, submission_id, providerurl, institution_type, wda)
VALUES (1, 'TWC', 'Admins of the TWC/ this column should never appear', null, 'whwhwhwwhwhwhwh','shshshshshs', 'shshshshshshhs'),
       (802, 'ACCD Northwest Vista Collage', 'An Alamo College', 1, 'whwhwhwwhwhwhwh', 'shshshshshs', 'shshshshshshhs'),
       (803, 'ACCD Palo Alto Collage', 'An Alamo College', 2, 'whwhwhwwhwhwhwh', 'shshshshshs', 'shshshshshshhs'),
       (900, 'Codeup', 'An Alamo Cchool', 3, 'whwhwhwwhwhwhwh', 'whwhwhwhwhhww', 'shshshshshshhs');

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
INSERT INTO twist (action_taken, twist_programid, twist_providerid, provider_id)
VALUES ('y',18452, 10001,0),('y',18452, 10001,1),('y',18452, 10001,2),('y',18452, 10001,3)

# table 2: provider address info
# values: address 1, address 2, AddressCity, AddressState, AddressZipCode, County, provider ID (key)
INSERT INTO provider_address (address1, address2, address_city, address_state, address_zip_code, county, provider_id)
VALUES ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 0),
       ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 1),
       ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 2),
       ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 3)

# table 3: admin contact info (provider)
# values: institution type, Admin Contact Person, Admin Job Title,  Admin Phone, Admin Phone Extension,
#           provider id (key)
INSERT INTO provider_admin_contact (admin_email, admin_job_title, admin_name, admin_phone, admin_phone_extension, provider_id)
VALUES ('admin@email.com', 'boss', 'bob grills', '999-999-9999', '3845', 0),
       ('admin@email.com', 'boss', 'bob grills', '999-999-9999', '3845', 1),
       ('admin@email.com', 'boss', 'bob grills', '999-999-9999', '3845', 2),
       ('admin@email.com', 'boss', 'bob grills', '999-999-9999', '3845', 3)

# table 4: financial contact info (provider)
# values: Financial Aid Name, Financial aid phone, Financial Aid Email, provider id (key)
INSERT INTO provider_financial_contact (fin_aid_email, fin_aid_job_title, fin_aid_name, fin_aid_phone, fin_aid_phone_extension, provider_id)
VALUES ('admin@email.com', 'boss', 'bob grills II', '999-999-9999', '3845', 0),
       ('admin@email.com', 'boss', 'bob grills II', '999-999-9999', '3845', 1),
       ('admin@email.com', 'boss', 'bob grills II', '999-999-9999', '3845', 2),
       ('admin@email.com', 'boss', 'bob grills II', '999-999-9999', '3845', 3)

# table 5: additional contact info (provider)
# values: additonal contact person, additional contact job title, additional contact phone,
#           additional contact phone extension, additional contact email, provider id (key)
INSERT INTO provider_additional_contact (add_contact_email, add_contact_job_title, add_contact_name, add_contact_phone, add_contact_phone_extension, provider_id)
VALUES ('admin@email.com', 'boss', 'bob grills III', '999-999-9999', '3845', 0),
       ('admin@email.com', 'boss', 'bob grills III', '999-999-9999', '3845', 1),
       ('admin@email.com', 'boss', 'bob grills III', '999-999-9999', '3845', 2),
       ('admin@email.com', 'boss', 'bob grills III', '999-999-9999', '3845', 3)

# table 6: ids and acks
# values: THECBPrgmID, TEAPrgmID, otherPrgmID, FERPAAck, WIOAAck, OtherAck1, OtherAck2, OtherAck3, OtherAck4,
#           provider id (key)
INSERT INTO  additionalids_and_acks (teaprgmid, thecbprgmid, ferpaack, other_ack1, other_ack2, other_ack3, other_ack4, other_prgmid, wioaack, provider_id)
VALUES (10111,20202,'y','n','n','n','n','n','y',0),
       (10111,20202,'y','n','n','n','n','n','y',1),
       (10111,20202,'y','n','n','n','n','n','y',2),
       (10111,20202,'y','n','n','n','n','n','y',3)

# table 7: campus address info
# values: address 1, address 2, campus city, campus state, campus zip code, campus county, campus id (key)
INSERT INTO campus_address (address1, address2, address_city, address_state, address_zip_code, county, campus_id)
VALUES ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 0),
       ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 1),
       ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 2),
       ('whwhwhwhwhwhhw','adadadadadad','San Antonio', 'Texas', 78235, 'bexar', 3)

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