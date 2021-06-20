用户表

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL COMMENT '用户id',
  `name` varchar(255) NOT NULL COMMENT '用户姓名',
  `tel` varchar(255) NOT NULL COMMENT '电话',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `电话` (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



地址表

CREATE TABLE `address` (
  `id` varchar(255) NOT NULL COMMENT '地址id',
  `user_id` varchar(255) NOT NULL COMMENT '所属用户id',
  `receiving_name` varchar(255) NOT NULL COMMENT '收货人姓名',
  `receiving_tel` varchar(255) NOT NULL COMMENT '收货人电话',
  `address` varchar(255) NOT NULL COMMENT '收货人地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `所属用户id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



类别表

CREATE TABLE `catalog` (
  `id` varchar(255) NOT NULL COMMENT '类别编号',
  `name` varchar(255) NOT NULL COMMENT '类名称',
  `parent_id` varchar(255) NOT NULL COMMENT '父id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



商品表

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL COMMENT '商品id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `catalog_id` varchar(255) NOT NULL COMMENT '类别id',
  `sell_user_id` varchar(255) NOT NULL COMMENT '卖家id',
  `sell_num` int NOT NULL COMMENT '销量',
  `main_image` varchar(255) DEFAULT NULL COMMENT '主页图片',
  `sub_image` varchar(255) DEFAULT NULL COMMENT '图列',
  `detail` varchar(255) DEFAULT NULL COMMENT '详情',
  `status` varchar(255) NOT NULL COMMENT '订单状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `商品名称` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



商品sku表

CREATE TABLE `product_sku` (
  `id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `attribute` varchar(1024) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` varchar(255) NOT NULL DEFAULT '0',
  `sell_num` int DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `商品id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



商品属性key表

CREATE TABLE `attribute_key` (
  `id` varchar(255) NOT NULL COMMENT '属性key编号',
  `name` varchar(255) DEFAULT NULL COMMENT '属性key名称',
  `catalog_id` varchar(255) DEFAULT NULL COMMENT '类别编号',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `类别id` (`catalog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



商品属性value表

CREATE TABLE `attribute_value` (
  `id` varchar(255)  NOT NULL COMMENT '属性value编号',
  `value` varchar(255)  NOT NULL COMMENT '属性value',
  `attribute_key_id` varchar(255)  NOT NULL COMMENT '商品属性key编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `属性key编号` (`attribute_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



商品属性关系表

CREATE TABLE `product_attribute` (
  `id` varchar(255) NOT NULL COMMENT '关系id',
  `product_id` varchar(255) NOT NULL COMMENT '商品id',
  `attribute_key_id` varchar(255) NOT NULL COMMENT '属性key编号',
  `attribute_value_id` varchar(255) NOT NULL COMMENT '属性value编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `商品编号` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



订单表

CREATE TABLE `order` (
  `id` varchar(255) NOT NULL COMMENT '订单id',
  `order_number` varchar(255) NOT NULL COMMENT '订单编号',
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `address` varchar(1024) NOT NULL COMMENT '收货人信息json',
  `product` varchar(1024) NOT NULL COMMENT '商品json',
  `product_sku` varchar(1024) NOT NULL COMMENT '商品sku-json',
  `nums` int NOT NULL COMMENT '商品数量',
  `total_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总价',
  `status` varchar(255) NOT NULL COMMENT '订单状态',
  `logistics_number` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `订单号` (`order_number`),
  KEY `用户id` (`user_id`),
  KEY `订单状态` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;