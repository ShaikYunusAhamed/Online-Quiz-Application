CREATE DATABASE quizdb;

USE quizdb;

-- Table for questions
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255),
    option_a VARCHAR(100),
    option_b VARCHAR(100),
    option_c VARCHAR(100),
    option_d VARCHAR(100),
    correct_answer CHAR(1)
);

-- Insert sample questions
INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES
('What is the capital of India?', 'Delhi', 'Mumbai', 'Kolkata', 'Chennai', 'A'),
('Which language is platform-independent?', 'C', 'C++', 'Java', 'Python', 'C'),
('Who developed Java?', 'James Gosling', 'Dennis Ritchie', 'Guido van Rossum', 'Bjarne Stroustrup', 'A');

-- Table for leaderboard
CREATE TABLE leaderboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    score INT
);
