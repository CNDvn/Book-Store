USE [BookStore]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[userId] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[name] [nvarchar](50) NULL,
	[roleId] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[bookId] [varchar](5) NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[image] [varchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[price] [float] NOT NULL,
	[author] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[date] [date] NOT NULL,
	[statusId] [int] NOT NULL,
	[categoryId] [int] NOT NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[bookId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[cartId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](50) NOT NULL,
	[discountId] [varchar](10) NULL,
	[createDate] [date] NOT NULL,
 CONSTRAINT [PK_Cart] PRIMARY KEY CLUSTERED 
(
	[cartId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CartDetail]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartDetail](
	[cartDetailId] [int] IDENTITY(1,1) NOT NULL,
	[cartId] [int] NOT NULL,
	[bookId] [varchar](5) NOT NULL,
	[amount] [int] NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[image] [varchar](50) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[price] [float] NOT NULL,
	[author] [nvarchar](50) NOT NULL,
	[dateCreateBook] [date] NOT NULL,
	[categoryId] [int] NOT NULL,
 CONSTRAINT [PK_CartDetail] PRIMARY KEY CLUSTERED 
(
	[cartDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[discountId] [varchar](10) NOT NULL,
	[value] [int] NOT NULL,
	[description] [nvarchar](max) NULL,
 CONSTRAINT [PK_Discount_1] PRIMARY KEY CLUSTERED 
(
	[discountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount_Account]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount_Account](
	[discountId] [varchar](10) NOT NULL,
	[userId] [varchar](50) NOT NULL,
	[statusId] [int] NOT NULL,
 CONSTRAINT [PK_Discount_Account] PRIMARY KEY CLUSTERED 
(
	[discountId] ASC,
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleId] [int] NOT NULL,
	[name] [varchar](10) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 6/29/2021 10:36:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([userId], [password], [name], [roleId]) VALUES (N'duchieu', N'123456', N'Duc Hieu', 1)
INSERT [dbo].[Account] ([userId], [password], [name], [roleId]) VALUES (N'hieuduc', N'123456', N'Hello Customer', 2)
GO
INSERT [dbo].[Book] ([bookId], [title], [image], [description], [price], [author], [quantity], [date], [statusId], [categoryId]) VALUES (N'BS001', N'Greenlights', N'BS001.jpg', N'hello 
baba
aaa', 11, N'Matthew McConaughey', 2, CAST(N'2021-05-12' AS Date), 1, 1)
INSERT [dbo].[Book] ([bookId], [title], [image], [description], [price], [author], [quantity], [date], [statusId], [categoryId]) VALUES (N'BS002', N'Final Fantasy XIV: Heavensward -- The Art of Ishga', N'BS002.jpg', N'The second volume of a two-volume set of the official art books for Final Fantasy XIV: Heavensward. This beautiful new edition features hundreds of pages of full-color art and an exclusive bonus item code! (NOTE: E-book edition does not include bonus item code.)', 100.97000122070313, N'aaa', 5, CAST(N'2021-06-01' AS Date), 1, 1)
INSERT [dbo].[Book] ([bookId], [title], [image], [description], [price], [author], [quantity], [date], [statusId], [categoryId]) VALUES (N'BS003', N'Hieu ne', N'default.jpg', N'123', 11, N'Duc Hieu', 1, CAST(N'2021-06-17' AS Date), 1, 2)
INSERT [dbo].[Book] ([bookId], [title], [image], [description], [price], [author], [quantity], [date], [statusId], [categoryId]) VALUES (N'BS006', N'sadadsasdsa', N'default.jpg', N'12312', 12, N'asdasdsa', 3, CAST(N'2021-06-17' AS Date), 1, 2)
INSERT [dbo].[Book] ([bookId], [title], [image], [description], [price], [author], [quantity], [date], [statusId], [categoryId]) VALUES (N'BS009', N'Duc hieu', N'BS009.jpg', N'', 12, N'asdsadas', 12, CAST(N'2021-06-18' AS Date), 2, 1)
INSERT [dbo].[Book] ([bookId], [title], [image], [description], [price], [author], [quantity], [date], [statusId], [categoryId]) VALUES (N'BS123', N'Good Morning Bresfast', N'BS123.jpg', N'', 12, N'Hello', 2, CAST(N'2021-06-28' AS Date), 2, 1)
GO
SET IDENTITY_INSERT [dbo].[Cart] ON 

INSERT [dbo].[Cart] ([cartId], [userId], [discountId], [createDate]) VALUES (42, N'hieuduc', N'KM80', CAST(N'2021-06-24' AS Date))
INSERT [dbo].[Cart] ([cartId], [userId], [discountId], [createDate]) VALUES (43, N'hieuduc', N'KM30', CAST(N'2021-06-24' AS Date))
INSERT [dbo].[Cart] ([cartId], [userId], [discountId], [createDate]) VALUES (44, N'hieuduc', NULL, CAST(N'2021-06-24' AS Date))
INSERT [dbo].[Cart] ([cartId], [userId], [discountId], [createDate]) VALUES (45, N'hieuduc', NULL, CAST(N'2021-06-24' AS Date))
SET IDENTITY_INSERT [dbo].[Cart] OFF
GO
SET IDENTITY_INSERT [dbo].[CartDetail] ON 

INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (39, 42, N'BS002', 2, N'Final Fantasy XIV: Heavensward -- The Art of Ishga', N'BS002.jpg', N'The second volume of a two-volume set of the official art books for Final Fantasy XIV: Heavensward. This beautiful new edition features hundreds of pages of full-color art and an exclusive bonus item code! (NOTE: E-book edition does not include bonus item code.)', 100.97000122070313, N'aaa', CAST(N'2021-06-01' AS Date), 1)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (40, 42, N'BS003', 1, N'Hieu ne', N'default.jpg', N'123', 11, N'Duc Hieu', CAST(N'2021-06-17' AS Date), 2)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (41, 42, N'BS001', 1, N'Greenlights', N'BS001.jpg', N'hello 
baba
aaa', 11, N'Matthew McConaughey', CAST(N'2021-05-12' AS Date), 1)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (42, 43, N'BS002', 3, N'Final Fantasy XIV: Heavensward -- The Art of Ishga', N'BS002.jpg', N'The second volume of a two-volume set of the official art books for Final Fantasy XIV: Heavensward. This beautiful new edition features hundreds of pages of full-color art and an exclusive bonus item code! (NOTE: E-book edition does not include bonus item code.)', 100.97000122070313, N'aaa', CAST(N'2021-06-01' AS Date), 1)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (43, 44, N'BS001', 1, N'Greenlights', N'BS001.jpg', N'hello 
baba
aaa', 11, N'Matthew McConaughey', CAST(N'2021-05-12' AS Date), 1)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (44, 44, N'BS002', 1, N'Final Fantasy XIV: Heavensward -- The Art of Ishga', N'BS002.jpg', N'The second volume of a two-volume set of the official art books for Final Fantasy XIV: Heavensward. This beautiful new edition features hundreds of pages of full-color art and an exclusive bonus item code! (NOTE: E-book edition does not include bonus item code.)', 100.97000122070313, N'aaa', CAST(N'2021-06-01' AS Date), 1)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (45, 45, N'BS002', 1, N'Final Fantasy XIV: Heavensward -- The Art of Ishga', N'BS002.jpg', N'The second volume of a two-volume set of the official art books for Final Fantasy XIV: Heavensward. This beautiful new edition features hundreds of pages of full-color art and an exclusive bonus item code! (NOTE: E-book edition does not include bonus item code.)', 100.97000122070313, N'aaa', CAST(N'2021-06-01' AS Date), 1)
INSERT [dbo].[CartDetail] ([cartDetailId], [cartId], [bookId], [amount], [title], [image], [description], [price], [author], [dateCreateBook], [categoryId]) VALUES (46, 45, N'BS001', 1, N'Greenlights', N'BS001.jpg', N'hello 
baba
aaa', 11, N'Matthew McConaughey', CAST(N'2021-05-12' AS Date), 1)
SET IDENTITY_INSERT [dbo].[CartDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([categoryId], [name]) VALUES (1, N'Arts & Photography Books')
INSERT [dbo].[Category] ([categoryId], [name]) VALUES (2, N'Biographies & Memoirs')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
INSERT [dbo].[Discount] ([discountId], [value], [description]) VALUES (N'KM10', 10, N'Khuyen mai 10%')
INSERT [dbo].[Discount] ([discountId], [value], [description]) VALUES (N'KM12', 12, N'')
INSERT [dbo].[Discount] ([discountId], [value], [description]) VALUES (N'KM20', 20, N'')
INSERT [dbo].[Discount] ([discountId], [value], [description]) VALUES (N'KM30', 30, N'')
INSERT [dbo].[Discount] ([discountId], [value], [description]) VALUES (N'KM70', 70, N'khuyen mai 70%')
INSERT [dbo].[Discount] ([discountId], [value], [description]) VALUES (N'KM80', 80, N'khuyen mai 80%')
GO
INSERT [dbo].[Discount_Account] ([discountId], [userId], [statusId]) VALUES (N'KM10', N'hieuduc', 1)
INSERT [dbo].[Discount_Account] ([discountId], [userId], [statusId]) VALUES (N'KM30', N'hieuduc', 2)
INSERT [dbo].[Discount_Account] ([discountId], [userId], [statusId]) VALUES (N'KM70', N'hieuduc', 1)
INSERT [dbo].[Discount_Account] ([discountId], [userId], [statusId]) VALUES (N'KM80', N'hieuduc', 2)
GO
INSERT [dbo].[Role] ([roleId], [name]) VALUES (1, N'Admin')
INSERT [dbo].[Role] ([roleId], [name]) VALUES (2, N'Customer')
GO
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([statusId], [name]) VALUES (1, N'Active')
INSERT [dbo].[Status] ([statusId], [name]) VALUES (2, N'Inactive')
SET IDENTITY_INSERT [dbo].[Status] OFF
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([roleId])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([categoryId])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_Category]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Status] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([statusId])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_Status]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Account] FOREIGN KEY([userId])
REFERENCES [dbo].[Account] ([userId])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Account]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Discount] FOREIGN KEY([discountId])
REFERENCES [dbo].[Discount] ([discountId])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Discount]
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_CartDetail_Cart] FOREIGN KEY([cartId])
REFERENCES [dbo].[Cart] ([cartId])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_CartDetail_Cart]
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_CartDetail_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([categoryId])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_CartDetail_Category]
GO
ALTER TABLE [dbo].[Discount_Account]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Account_Account] FOREIGN KEY([userId])
REFERENCES [dbo].[Account] ([userId])
GO
ALTER TABLE [dbo].[Discount_Account] CHECK CONSTRAINT [FK_Discount_Account_Account]
GO
ALTER TABLE [dbo].[Discount_Account]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Account_Discount] FOREIGN KEY([discountId])
REFERENCES [dbo].[Discount] ([discountId])
GO
ALTER TABLE [dbo].[Discount_Account] CHECK CONSTRAINT [FK_Discount_Account_Discount]
GO
ALTER TABLE [dbo].[Discount_Account]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Account_Status] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([statusId])
GO
ALTER TABLE [dbo].[Discount_Account] CHECK CONSTRAINT [FK_Discount_Account_Status]
GO
