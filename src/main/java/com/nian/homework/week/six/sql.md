用户表

CREATE TABLE `commodity` (
  `id` varchar(255) NOT NULL COMMENT '商品id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '价钱',
  `stock` int NOT NULL COMMENT '库存',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



商品表

CREATE TABLE `commodity` (
  `id` varchar(255) NOT NULL COMMENT '商品id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '价钱',
  `stock` int NOT NULL COMMENT '库存',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



订单表

CREATE TABLE `order` (
  `id` varchar(255) NOT NULL COMMENT '订单id',
  `u_id` varchar(255) NOT NULL COMMENT '用户id',
  `c_id` varchar(255) NOT NULL COMMENT '商品id',
  `nums` int unsigned NOT NULL COMMENT '数量',
  `total_price` decimal(10,2) unsigned NOT NULL COMMENT '总价',
  `status` varchar(255) NOT NULL COMMENT '订单状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;