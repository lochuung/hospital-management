ALTER TABLE employee ADD UNIQUE KEY(username);
INSERT IGNORE INTO employee (full_name, dob, gender, address, phone, email, join_date, username,
password, type)
VALUES
    ('Doctor 1', '1990-01-01', 'MALE', 'Address 1', '123456789', 'doctor1@example.com',
'2023-01-01', 'doctor1', 'password', 'Doctor'),
    ('Doctor 2', '1985-05-15', 'FEMALE', 'Address 2', '987654321', 'doctor2@example.com',
'2023-02-01', 'doctor2', 'password', 'Doctor'),
('Accountant 1', '1988-08-20', 'FEMALE', 'Address 3', '111222333', 'accountant1@example.com',
'2023-01-01', 'accountant1', 'password', 'Accountant'),
    ('Accountant 2', '1995-12-10', 'MALE', 'Address 4', '444555666', 'accountant2@example.com',
'2023-02-01', 'accountant2', 'password', 'Accountant'),
('Pharmacist 1', '1992-04-12', 'FEMALE', 'Address 7', '654123789', 'pharmacist1@example.com',
'2023-01-01', 'pharmacist1', 'password', 'Pharmacist'),
    ('Pharmacist 2', '1998-09-30', 'MALE', 'Address 8', '987123654', 'pharmacist2@example.com',
'2023-02-01', 'pharmacist2', 'password', 'Pharmacist'),
('Receptionist 1', '1993-06-28', 'FEMALE', 'Address 9', '321654987', 'receptionist1@example.com',
'2023-01-01', 'receptionist1', 'password', 'Receptionist'),
    ('Receptionist 2', '1990-10-18', 'MALE', 'Address 10', '456987123', 'receptionist2@example.com',
'2023-02-01', 'receptionist2', 'password', 'Receptionist');

