SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `eagle_trace`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `provider` int(10) NOT NULL COMMENT '地图服务商',
  `server_id` bigint(20) NULL DEFAULT NULL COMMENT '所属的服务id',
  `terminal_id` bigint(20) NULL DEFAULT NULL COMMENT '所属的终端id',
  `trace_id` bigint(20) NULL DEFAULT NULL COMMENT '地图服务商的轨迹id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轨迹名称',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态，0-运动中，1-已结束',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `size` int(11) NULL DEFAULT NULL COMMENT '轨迹点数量',
  `distance` double NULL DEFAULT NULL COMMENT '此段轨迹的里程数，单位：米',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '轨迹持续时间，单位：毫秒',
  `start_point` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '起点坐标，经纬度，逗号分隔',
  `end_point` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终点坐标，经纬度，逗号分隔',
  `point_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '轨迹点数据，json格式',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `provider`(`provider`) USING BTREE,
  INDEX `sid`(`server_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轨迹表' ROW_FORMAT = Dynamic;

CREATE TABLE `eagle_trace_fence`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `provider` int(2) NOT NULL COMMENT '地图服务商',
  `server_id` bigint(20) NOT NULL COMMENT '地图服务商中的服务id',
  `fence_id` bigint(20) NOT NULL COMMENT '地图服务商的围栏id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务描述',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '电子围栏类型，1-圆形，2-多边形，3-线性，4-行政区',
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '创建围栏的参数',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `provider`(`provider`) USING BTREE,
  INDEX `server_id`(`server_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轨迹电子围栏表' ROW_FORMAT = Dynamic;

CREATE TABLE `eagle_trace_server`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `provider` int(2) NOT NULL COMMENT '地图服务商',
  `server_id` bigint(20) NOT NULL COMMENT '地图服务商中的服务id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务描述',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '服务状态，1-可用，0-不可用',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `provider`(`provider`) USING BTREE,
  INDEX `server_id`(`server_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轨迹所属服务表' ROW_FORMAT = Dynamic;

CREATE TABLE `eagle_trace_terminal`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `provider` int(2) NOT NULL COMMENT '地图服务商',
  `server_id` bigint(20) NOT NULL COMMENT '地图服务商中的服务id',
  `terminal_id` bigint(20) NULL DEFAULT NULL COMMENT '终端id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终端描述',
  `props` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户自定义字段,json格式',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户当前位置：经度,纬度',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `provider`(`provider`) USING BTREE,
  INDEX `sid`(`server_id`) USING BTREE,
  INDEX `terminal_id`(`terminal_id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轨迹终端表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
