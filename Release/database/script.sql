USE [master]
GO
/****** Object:  Database [tunga]    Script Date: 4/5/2016 6:43:20 PM ******/
CREATE DATABASE [tunga]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'tunga', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\tunga.mdf' , SIZE = 3136KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'tunga_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\tunga_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [tunga] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [tunga].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [tunga] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [tunga] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [tunga] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [tunga] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [tunga] SET ARITHABORT OFF 
GO
ALTER DATABASE [tunga] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [tunga] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [tunga] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [tunga] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [tunga] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [tunga] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [tunga] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [tunga] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [tunga] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [tunga] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [tunga] SET  DISABLE_BROKER 
GO
ALTER DATABASE [tunga] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [tunga] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [tunga] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [tunga] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [tunga] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [tunga] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [tunga] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [tunga] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [tunga] SET  MULTI_USER 
GO
ALTER DATABASE [tunga] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [tunga] SET DB_CHAINING OFF 
GO
ALTER DATABASE [tunga] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [tunga] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [tunga]
GO
/****** Object:  Table [dbo].[food]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[food](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[menuId] [int] NULL,
	[name] [varchar](255) NULL,
	[price] [float] NULL,
	[image] [varchar](255) NULL,
	[description] [text] NULL,
 CONSTRAINT [PK_food] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[invoice]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[invoice](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullName] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[address] [varchar](255) NULL,
	[phone] [varchar](15) NULL,
	[total] [float] NULL,
	[token] [varchar](255) NULL,
	[status] [tinyint] NULL,
	[createdAt] [datetime] NULL,
	[notify] [int] NULL,
 CONSTRAINT [PK_customer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[invoiceFood]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[invoiceFood](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[invoiceId] [int] NULL,
	[foodId] [int] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_order_item] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[invoiceTable]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[invoiceTable](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[invoiceId] [int] NULL,
	[tableId] [int] NULL,
	[price] [float] NULL,
	[fromTime] [datetime] NULL,
	[toTime] [datetime] NULL,
 CONSTRAINT [PK_reservation] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[menu]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[menu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[order] [int] NULL,
 CONSTRAINT [PK_menu] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[room]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[room](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[type] [tinyint] NULL,
 CONSTRAINT [PK_room] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[table]    Script Date: 4/5/2016 6:43:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[table](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[roomId] [int] NULL,
	[name] [varchar](255) NULL,
	[type] [int] NULL,
	[price] [float] NULL,
	[description] [text] NULL,
	[image] [varchar](255) NULL,
 CONSTRAINT [PK_table] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[food] ON 

INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (1, 1, N'Coffee', 10, N'coffee.jpg', N'<p>Microroasters of organic <em>coffee</em>. Company history, descriptions of their blends, news, and retail or online sales</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (2, 1, N'Hamburger', 17, N'hamburger.jpg', N'<p>A <em>hamburger</em> is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun. <em>Hamburgers</em> may be<wbr />&nbsp;...</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (3, 1, N'Milk', 5, N'milk.jpg', N'<p><em>Milk</em> is a pale liquid produced by the mammary glands of mammals. It is the primary source of nutrition for infant mammals before they are able to digest other<wbr />&nbsp;</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (4, 2, N'Pizza', 19, N'pizza.jpg', N'<p>Each Papa John''s <em>pizza</em> is carefully crafted with flavorful, superior-quality ingredients and toppings. Order <em>pizza</em> online for delivery or carryout.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (5, 2, N'Chicken', 25, N'chicken.jpg', N'<p>Breast is a really versatile cut of <em>chicken</em>. You can bake it whole, breadcrumb it, cut it into goujons, chunks or mini fillets, create kebab skewers or use it in any</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (6, 2, N'Spaghetti', 21, N'spaghetti.jpg', N'<p><em>Spaghetti</em> is a long, thin, cylindrical, solid pasta. It is a staple food of traditional Italian cuisine. Like other pasta, <em>spaghetti</em> is made of milled wheat and water.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (7, 1, N'Tea', 10, N'tea.jpg', N'<p><em>Tea</em> is an aromatic beverage commonly prepared by pouring hot or boiling water over cured leaves of the Camellia sinensis, an evergreen shrub native to Asia.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (8, 2, N'Large Fries', 6, N'largefries.jpg', N'<p>Curious about how many calories are in <em>Large</em> French <em>Fries</em>? Get nutrition information and sign up for a free online diet program at CalorieCount.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (9, 3, N'Susi', 13, N'susi.JPG', N'<p>About the differnt types of <em>sushi</em> dishes commonly found in Japan. ...<em>Sushi</em> Vocabulary. <em>Sushi</em> terminology by the Tokyo <em>Food</em> Page. Share this</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (10, 3, N'Tacos', 22, N'tacos.jpg', N'<p>We use only the freshest ingredients in our Mexican <em>tacos</em>. Try the Funk Meister or The Overachiever, and don''t forget the free chips &amp; salsa!</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (11, 3, N'Risotto', 32, N'risotto.jpg', N'<p><em>Risotto</em> is a dish that''s become associated with fancy high end restaurants, but really, it''s the epitome of Italian home cooking and comfort food.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (12, 3, N'Teriyaki', 34, N'teriyaki.jpg', N'<p>Make your own <em>Teriyaki</em> Sauce recipe for your favorite Asian-style dishes, using Food Network''s blend of soy sauce, rice wine, brown sugar, garlic and ginger.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (13, 2, N'Bibimbap', 35, N'bibimbap.jpg', N'<p>restaurant in London''s Soho specialising in Korean <em>bibimbap</em> at an affordable price. Come and see our full menu here! Great casual dining restaurant.</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (14, 2, N'Ceviche', 17, N'cebiche.jpg', N'<p><em>Ceviche</em> is a seafood dish popular in the coastal regions of Latin America. The dish is typically made from fresh raw fish cured in citrus juices, such as lemon or</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (15, 3, N'Shakshouka', 38, N'shakshouka.JPG', N'<p><em>Shakshouka</em> or shakshuka is a dish of eggs poached in a sauce of tomatoes, chili peppers, and onions, often spiced with cumin. It is believed to have a Tunisian</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (16, 3, N'Beefsteak', 37, N'beefsteak.jpg', N'<p>A <em>beefsteak</em> is a type of banquet in which sliced beef tenderloin is served to diners as all-you-can-eat finger food</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (17, 1, N'BreadPudding', 13, N'breadpudding.jpg', N'<p>This recipe is proof-positive that leftover bread can easily be converted to dessert without much work There''s room for customization here: consider adding fresh&nbsp;</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (18, 1, N'Panini', 29, N'panini.jpg', N'<p><em>Panini</em> &amp; Sandwiches. The best sandwiches start with great bread, freshly baked each day in our bakery-cafes. Crafted with our fresh, high-quality meats</p>')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (19, 4, N'Local cooked ham sandwich', 30, N'ham sandwhich.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (20, 4, N'Local smoked ham sandwich', 45, N'ham sandwhich.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (21, 4, N'Serrano ham sandwich', 50, N'ham sandwhich.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (22, 4, N'Tuna Sandwich', 30, N'ham sandwhich.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (23, 4, N'Grilled Chicken Sandwich', 30, N'ham sandwhich.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (24, 4, N'Beef Steak Sandwich', 40, N'ham sandwhich.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (25, 5, N'Soft Drink', 10, N'coca.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (26, 5, N'Fresh Fruit Juice', 20, N'Fresh Fruit Juice.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (27, 5, N'beer', 31, N'beer.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (28, 5, N'tea', 15, N'tea.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (29, 5, N'Coffee', 15, N'Coffee.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (30, 6, N'1 Grilled Pork Tenderloin With Shallots And Asparagus', 110, N'1 Grilled Pork Tenderloin With Shallots And Asparagus.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (31, 6, N'2 Blue-Cheese Topped Pork Steak', 115, N'1 Grilled Pork Tenderloin With Shallots And Asparagus.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (32, 6, N'3 Grilled Pork Tenderloin Wrap Ham & Cheese', 120, N'1 Grilled Pork Tenderloin With Shallots And Asparagus.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (33, 6, N'4 Pork Chops With Choice Of Sauce', 120, N'7 Pork Chops With Choice Of Sauce.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (34, 6, N'5 Pork Chops With Blue Cheese Sauce', 123, N'7 Pork Chops With Choice Of Sauce.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (35, 7, N'Australian Beef Steak', 230, N'Australian Beef Steak.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (36, 7, N' Australian T-Bone Steak', 230, N'Australian T-Bone Steak.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (37, 7, N'Grilled Beef Wrap Cheese', 125, N'Grilled Beef Wrap Cheese.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (38, 7, N'Grilled Lamb Chops', 225, N'Grilled Lamb Chops.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (39, 7, N'Local Beef Steak', 100, N'Local Beef Steak.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (40, 8, N'French Onion Soup', 30, N'French Onion Soup.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (41, 8, N'Pumpkin Soup', 30, N'Pumpkin Soup.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (42, 8, N'Creamy corn soup', 30, N'Creamy corn soup.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (43, 8, N'Apple Tart With Warm Chocolate', 25, N'Apple Tart With Warm Chocolate.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (44, 8, N'Mixed Fruits Plate', 30, N'Mixed Fruits Plate.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (45, 8, N'Banana With Chocolate', 20, N'Banana With Chocolate.png', N'')
INSERT [dbo].[food] ([id], [menuId], [name], [price], [image], [description]) VALUES (46, 8, N'Caramel Custard', 15, N'Caramel Custard.png', N'')
SET IDENTITY_INSERT [dbo].[food] OFF
SET IDENTITY_INSERT [dbo].[invoice] ON 

INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (1, N'hoangha', N'hoangha@fpt.com', N'cau dien', N'0974617297', 10, N'hgjghjgh', 1, CAST(0x0000A5D0010F4BCA AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (2, N'Hong Phuong Le', N'phuong17889@gmail.com', N'Trung Kinh, Cau Giay', N'0949968789', 42, N'68467314', 1, CAST(0x0000A5D10134BA12 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (3, N'thailh', N'thai@gmail.com', N'ha noi', N'0912404281', 175, N'98796248', 1, CAST(0x0000A5D30134F985 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (4, N'hapt', N'ha@gmail.com', N'TP.HCM', N'0988889988', 138, N'51970357', 1, CAST(0x0000A5D3013598E1 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (5, N'Ba Tung', N'batung@batung.com', N'TP.HCM', N'0989999999', 74, N'08386300', 1, CAST(0x0000A5D5013640C9 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (6, N'Le Roi', N'leroi@leroi.com', N'hai duong', N'0977777777', 37, N'43267269', 1, CAST(0x0000A5D70136A770 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (7, N'IS', N'is@is.com', N'is', N'0000000000', 80, N'12320427', 1, CAST(0x0000A5D7013725C4 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (8, N'hoang ha', N'hoangha@gmail.com', N'19 XÃ£ Ä?Ã n, HÃ  Ná»?i', N'09899898998', 74, N'56930270', 1, CAST(0x0000A5D801379960 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (9, N'tung', N'tung@gmail.com', N'hai phong', N'098765543', 32, N'49641507', 1, CAST(0x0000A5D801381200 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (10, N'Them An', N'theman@gmail.com', N'so 8 Ton That Thuyet', N'3242343242', 85, N'54468950', 1, CAST(0x0000A5D80138853E AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (11, N'avc', N'thai@gmail.com', N'sg', N'098765465', 46, N'48877857', 1, CAST(0x0000A5D8014804C5 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (12, N'quang mot', N'quangmot@gmail.com', N'ha noi', N'0938839393', 47, N'57400736', 1, CAST(0x0000A5D801484268 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (13, N'mai ta', N'maita@gmail.com', N'nha trang', N'0932332323', 69, N'84584958', 1, CAST(0x0000A5D801489A40 AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (14, N'ha', N'ha@gmail.com', N'kjijkja', N'0978654', 54, N'17071010', 1, CAST(0x0000A5D801491AAC AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (15, N'hytu', N'huy@gmail.com', N'hung yen', N'890765', 17, N'82416680', 1, CAST(0x0000A5D8014958CD AS DateTime), 2)
INSERT [dbo].[invoice] ([id], [fullName], [email], [address], [phone], [total], [token], [status], [createdAt], [notify]) VALUES (16, N'Hong Phuong Le', N'phuong17889@gmail.com', N'Trung Kinh, Cau Giay', N'0949968789', 384, N'05113777', 1, CAST(0x0000A5DD00FA5671 AS DateTime), 2)
SET IDENTITY_INSERT [dbo].[invoice] OFF
SET IDENTITY_INSERT [dbo].[invoiceFood] ON 

INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (1, 2, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (2, 2, 5, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (3, 3, 1, 2)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (4, 3, 2, 3)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (5, 3, 18, 2)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (6, 3, 3, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (7, 3, 6, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (8, 3, 7, 2)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (9, 4, 17, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (10, 4, 6, 2)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (11, 4, 9, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (12, 4, 11, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (13, 4, 15, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (14, 5, 17, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (15, 5, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (16, 5, 18, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (17, 5, 3, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (18, 5, 7, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (19, 6, 1, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (20, 6, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (21, 6, 7, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (22, 7, 1, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (23, 7, 5, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (24, 7, 7, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (25, 7, 13, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (26, 8, 1, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (27, 8, 17, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (28, 8, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (29, 8, 18, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (30, 8, 3, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (31, 9, 1, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (32, 9, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (33, 9, 3, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (34, 10, 1, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (35, 10, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (36, 10, 18, 2)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (37, 11, 4, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (38, 11, 6, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (39, 11, 8, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (40, 12, 9, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (41, 12, 12, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (42, 13, 16, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (43, 13, 11, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (44, 14, 10, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (45, 14, 11, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (46, 15, 2, 1)
INSERT [dbo].[invoiceFood] ([id], [invoiceId], [foodId], [quantity]) VALUES (47, 16, 2, 2)
SET IDENTITY_INSERT [dbo].[invoiceFood] OFF
SET IDENTITY_INSERT [dbo].[invoiceTable] ON 

INSERT [dbo].[invoiceTable] ([id], [invoiceId], [tableId], [price], [fromTime], [toTime]) VALUES (1, 16, 4, 350, CAST(0x0000A5DD00000000 AS DateTime), CAST(0x0000A5DD0062E080 AS DateTime))
SET IDENTITY_INSERT [dbo].[invoiceTable] OFF
SET IDENTITY_INSERT [dbo].[menu] ON 

INSERT [dbo].[menu] ([id], [name], [order]) VALUES (1, N'Breakfast', NULL)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (2, N'Lunch', NULL)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (3, N'Dinner', NULL)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (4, N'OREGANO SANDWICH', 0)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (5, N'DRINK', 1)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (6, N'PORK', 1)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (7, N'BEEF & LAMB', 1)
INSERT [dbo].[menu] ([id], [name], [order]) VALUES (8, N'SIDE MENU', 1)
SET IDENTITY_INSERT [dbo].[menu] OFF
SET IDENTITY_INSERT [dbo].[room] ON 

INSERT [dbo].[room] ([id], [name], [type]) VALUES (1, N'VIP 01', 1)
INSERT [dbo].[room] ([id], [name], [type]) VALUES (2, N'VIP 02', 1)
INSERT [dbo].[room] ([id], [name], [type]) VALUES (3, N'VIP 03', 1)
INSERT [dbo].[room] ([id], [name], [type]) VALUES (4, N'Normal 01', 0)
INSERT [dbo].[room] ([id], [name], [type]) VALUES (5, N'Normal 02', 0)
INSERT [dbo].[room] ([id], [name], [type]) VALUES (6, N'Normal 03', 0)
INSERT [dbo].[room] ([id], [name], [type]) VALUES (7, N'Normal 04', 0)
SET IDENTITY_INSERT [dbo].[room] OFF
SET IDENTITY_INSERT [dbo].[table] ON 

INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (1, 1, N'Table 1', 8, 400, N'i love going out to eat chinese food. everyone sits at a round table and we order a large variety of dishes. food starts coming out to the table as each ...', N'1.jpg')
INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (2, 2, N'Table 2', 2, 150, N'An eat-in kitchen doesn’t always mean tiny. In this modern kitchen a long harvest table with mid century chairs seat 10.', N'2.jpg')
INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (3, 4, N'Table 3', 2, 100, N'We were in a bit of a hurry as we had to make it to Flybarre in less than an hour, so we rushed to order. I ordered the Warm Japanese Teriyaki', N'3.jpg')
INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (4, 3, N'Table 4', 5, 350, N'Recipes full of fall''s freshest produce are the way to go here. Visit your local farmers'' market for inspiration. Don''t limit your purchases to vegetables; local, pasture-raised meats, baked goods, jams and honey can also make seasonally- and geographically-appropriate additions to your meal.

We asked The Swag''s chef, Ronnie Potter-Bowers, to create an ideal fall menu at the resort, including a soup, salad, entree and dessert. Get his exclusive recipes right here.', N'4.jpg')
INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (5, 5, N'Table 5', 10, 700, N'When searching for spirits, think local, local, local. Is there a winery near your event space that can help you pair wines with local produce? For beer lovers, a sampling from regional breweries might be more appropriate. Utilize local distilleries to give that extra-special touch to before or after-dinner drinks. Then sit back, enjoy the view and raise a glass to a', N'5.jpg')
INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (6, 6, N'Table6', 9, 650, N'The food and the collective nature of how the restaurant is run are meant to evoke comfort, to promote consciousness and community.', N'6.jpg')
INSERT [dbo].[table] ([id], [roomId], [name], [type], [price], [description], [image]) VALUES (7, 7, N'Table 7', 3, 150, N'EAT: We started with the chilled Gazpacho Soup, a unique interpretation of the traditional Spanish soup. The color of the soup was more pink than red and', N'7.jpg')
SET IDENTITY_INSERT [dbo].[table] OFF
ALTER TABLE [dbo].[food] ADD  CONSTRAINT [DF_food_price]  DEFAULT ((0)) FOR [price]
GO
ALTER TABLE [dbo].[invoice] ADD  CONSTRAINT [DF_invoice_has_paid]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[invoice] ADD  CONSTRAINT [DF_orders_created_at]  DEFAULT (getdate()) FOR [createdAt]
GO
ALTER TABLE [dbo].[invoice] ADD  CONSTRAINT [DF_invoice_notify]  DEFAULT ((0)) FOR [notify]
GO
ALTER TABLE [dbo].[invoiceFood] ADD  CONSTRAINT [DF_order_item_quantity]  DEFAULT ((1)) FOR [quantity]
GO
ALTER TABLE [dbo].[food]  WITH CHECK ADD  CONSTRAINT [FK_food_menu] FOREIGN KEY([menuId])
REFERENCES [dbo].[menu] ([id])
GO
ALTER TABLE [dbo].[food] CHECK CONSTRAINT [FK_food_menu]
GO
ALTER TABLE [dbo].[invoiceFood]  WITH CHECK ADD  CONSTRAINT [FK_invoice_food_invoice] FOREIGN KEY([invoiceId])
REFERENCES [dbo].[invoice] ([id])
GO
ALTER TABLE [dbo].[invoiceFood] CHECK CONSTRAINT [FK_invoice_food_invoice]
GO
ALTER TABLE [dbo].[invoiceTable]  WITH CHECK ADD  CONSTRAINT [FK_invoice_table_invoice] FOREIGN KEY([invoiceId])
REFERENCES [dbo].[invoice] ([id])
GO
ALTER TABLE [dbo].[invoiceTable] CHECK CONSTRAINT [FK_invoice_table_invoice]
GO
ALTER TABLE [dbo].[invoiceTable]  WITH CHECK ADD  CONSTRAINT [FK_invoice_table_table] FOREIGN KEY([tableId])
REFERENCES [dbo].[table] ([id])
GO
ALTER TABLE [dbo].[invoiceTable] CHECK CONSTRAINT [FK_invoice_table_table]
GO
ALTER TABLE [dbo].[table]  WITH CHECK ADD  CONSTRAINT [FK_table_room] FOREIGN KEY([roomId])
REFERENCES [dbo].[room] ([id])
GO
ALTER TABLE [dbo].[table] CHECK CONSTRAINT [FK_table_room]
GO
USE [master]
GO
ALTER DATABASE [tunga] SET  READ_WRITE 
GO
