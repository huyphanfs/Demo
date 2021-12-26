-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 26, 2021 lúc 06:39 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `book`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `author` varchar(100) NOT NULL,
  `genre` varchar(60) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`id`, `title`, `author`, `genre`, `year`) VALUES
(1, 'The Haunting of Hill House', 'Shirley Jackson', 'Horror', 2006),
(2, 'It Ends with Us', 'Colleen Hoover', 'Romance', 2016),
(3, 'The Wish ', 'Nicholas Sparks ', 'Romance', 2021),
(4, 'Obsessed', 'Ivy Smoak ', 'Romance', 2021),
(5, 'Riccardino', 'Andrea Camilleri', 'Thriller', 2021),
(6, 'Red Wolves', 'Adam Hamdy', 'Thriller', 2021),
(7, 'The Taste of Ginger', 'Mansi Shah ', 'Romance', 2022),
(8, 'The Fireman', 'Joe Hill', 'Horror', 2016),
(9, 'The Ballad of Black Tom', 'Victor LaValle', 'Horror', 2016),
(10, 'The Last Days of Jack Sparks', 'Jason Arnopp', 'Horror', 2016),
(14, 'The Nesting Dolls', 'Alina Adams', 'Family', 2020),
(15, 'Sold on a Monday', 'Kristina McMorris', 'Fiction', 2018),
(16, 'Into Thin Air', 'Jon Krakauer', 'Adventure', 1999),
(17, 'The Last Thing He Told Me', 'Laura Dave', 'Thriller', 2021),
(18, 'The Casanova', 'T L Swan', 'Romance', 2021),
(19, 'The Sweetest Oblivion', 'Danielle Lori', 'Romance', 2018),
(20, 'Eleanor & Park', 'Rainbow Rowell', 'Romance', 2013),
(21, 'The Fault in Our Stars', 'John Green', 'Romance', 2012),
(22, 'Leviathan Falls', 'James S.A. Corey', 'Science Fiction', 2021),
(23, 'The Last Shadow', 'Orson Scott Card', 'Science Fiction', 2021);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
