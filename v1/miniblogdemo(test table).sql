-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2015 at 09:45 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `miniblogdemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
`id` int(20) unsigned NOT NULL,
  `comment` varchar(254) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_id` int(20) unsigned NOT NULL,
  `user_id` int(16) unsigned NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `comment`, `create_at`, `modified_at`, `post_id`, `user_id`) VALUES
(1, 'comment own test', '2015-02-09 08:24:39', '0000-00-00 00:00:00', 24, 47);

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
`id` int(20) unsigned NOT NULL,
  `content` varchar(2048) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL,
  `title` varchar(100) NOT NULL,
  `user_id` int(16) unsigned NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `content`, `create_at`, `modified_at`, `status`, `title`, `user_id`) VALUES
(3, 'hello, this is unittest post check own content', '2015-02-09 04:53:18', '2015-02-09 04:53:18', 1, 'check own test', 10),
(24, 'asiofhilcfunhiofgasdbyfgavucfgyaubfgysdyugvuasgfusybdytfyvdf', '2015-02-09 07:59:47', '2015-02-09 07:59:47', 1, 'hello this is my first post', 9);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
`id` int(16) unsigned NOT NULL,
  `access_token` varchar(64) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expired` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(16) unsigned DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=169 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(16) unsigned NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(254) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(64) NOT NULL,
  `username` varchar(32) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `create_at`, `email`, `firstname`, `lastname`, `modified_at`, `password`, `username`) VALUES
(8, '2015-01-29 03:55:05', 'asd@asdaf.com', 'jackybumbum', 'marki', '2015-01-29 03:55:05', '42fe44ec7a8a15e1964ca5edabab5847636e94202f5e7d9f5412a54eb7486eb1', 'hello1590'),
(9, '2015-01-29 06:37:45', 'dang.huy@mulodo.com', 'sd', 'huy', '2015-02-09 04:17:37', '3e1aa305b7682bdf90dcb662abe47ca4d5d8fbfa5384ece916e5469b8fc72731', 'ledanghuy'),
(10, '2015-01-29 06:53:41', 'dang.huy@mulodo.com2', 'Le', 'Huy', '2015-01-29 06:53:41', 'c575be595260a4e4768b6d62bf32c1c4c2bc192e9c46c69e5024145012afc3d4', 'ledanghuy2'),
(11, '2015-02-03 06:26:02', 'dang.huyd@mulodo.com', 'Le', 'Huy', '2015-02-03 06:26:02', '3e1aa305b7682bdf90dcb662abe47ca4d5d8fbfa5384ece916e5469b8fc72731', ''),
(47, '2015-02-09 04:17:37', 'dang.huy.test@mulodo.com', 'le', 'huy', '2015-02-09 04:17:37', 'bb4eb4f1c068b8c68199a22e8415dd8e14fd94fbec6246209c3a6c40804312c3', 'ledanghuytest');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_comments_users` (`user_id`), ADD KEY `fk_comments_posts` (`post_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_posts_users` (`user_id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `access_token` (`access_token`), ADD KEY `fk_token_users` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`), ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
MODIFY `id` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
MODIFY `id` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
MODIFY `id` int(16) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=169;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(16) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=48;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
ADD CONSTRAINT `fk_comments_posts` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
ADD CONSTRAINT `fk_comments_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
ADD CONSTRAINT `fk_posts_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
ADD CONSTRAINT `fk_token_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
