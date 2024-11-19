-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Nov 19, 2024 at 12:46 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `ID` int(11) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`ID`, `Brand`, `Status`) VALUES
(1, 'Samsung', 'Active'),
(2, 'Panasonic', 'Active'),
(3, 'Dell', 'Active'),
(4, 'Acer', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `ID` int(11) NOT NULL,
  `Category` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID`, `Category`, `Status`) VALUES
(1, 'Mobile Phone', 'Active'),
(2, 'Specker', 'Active'),
(3, 'Tab', 'Deactive'),
(4, 'Laptop', 'Active'),
(5, 'Smart Watch', 'Deactive'),
(6, 'Headset', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ID` int(128) NOT NULL,
  `Product_Name` varchar(255) NOT NULL,
  `Description` text NOT NULL,
  `Category_ID` int(128) NOT NULL,
  `Brand_ID` int(128) NOT NULL,
  `Cost_price` int(128) NOT NULL,
  `Retail_price` int(128) NOT NULL,
  `Quantity` int(128) NOT NULL,
  `Barcode` int(128) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `Product_Name`, `Description`, `Category_ID`, `Brand_ID`, `Cost_price`, `Retail_price`, `Quantity`, `Barcode`, `Status`) VALUES
(1, 'Panasonic M3 Pro', 'Mini Specker\nBlootooth Speacker', 2, 2, 8000, 10000, 47, 1001, 'Active'),
(2, 'Samsung Galaxy M03', 'Ram 3GB\nRom 64GB', 1, 1, 26000, 30000, 49, 1002, 'Active'),
(3, 'Blootooth Headset', 'White Colour\nBlootooth', 6, 2, 18000, 20000, 50, 1003, 'Active'),
(4, 'Dell Laptop', '4GB Ram\n1TB Rom', 4, 3, 135000, 151000, 50, 1004, 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `ID` int(11) NOT NULL,
  `Date` varchar(255) NOT NULL,
  `Subtotal` int(11) NOT NULL,
  `Pay` int(11) NOT NULL,
  `Balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`ID`, `Date`, `Subtotal`, `Pay`, `Balance`) VALUES
(1, '2024/11/01', 50000, 60000, 10000),
(2, '2024/11/01', 10000, 10000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `sales_product`
--

CREATE TABLE `sales_product` (
  `ID` int(11) NOT NULL,
  `Sales_ID` int(11) NOT NULL,
  `Product_ID` int(11) NOT NULL,
  `Sell_Price` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales_product`
--

INSERT INTO `sales_product` (`ID`, `Sales_ID`, `Product_ID`, `Sell_Price`, `Quantity`, `Total`) VALUES
(1, 1, 1001, 10000, 2, 20000),
(2, 1, 1002, 30000, 1, 30000),
(3, 2, 1001, 10000, 1, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `sign_up`
--

CREATE TABLE `sign_up` (
  `ID` int(11) NOT NULL,
  `Email` varchar(127) NOT NULL,
  `User_Name` varchar(127) NOT NULL,
  `Password` varchar(127) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sign_up`
--

INSERT INTO `sign_up` (`ID`, `Email`, `User_Name`, `Password`) VALUES
(1, 'user@gmail.com', 'User', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sales_product`
--
ALTER TABLE `sales_product`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sign_up`
--
ALTER TABLE `sign_up`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ID` int(128) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sales_product`
--
ALTER TABLE `sales_product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sign_up`
--
ALTER TABLE `sign_up`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
