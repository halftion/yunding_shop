/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云Mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 39.106.14.147:3306
 Source Schema         : yunding_shop

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 31/08/2018 13:11:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auditing
-- ----------------------------
DROP TABLE IF EXISTS `t_auditing`;
CREATE TABLE `t_auditing`  (
  `auditing_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `full_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请说明',
  `id_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `type` int(255) NOT NULL COMMENT '申请类型 0-店铺申请',
  `state` int(255) NOT NULL DEFAULT 0 COMMENT '申请状态 0-待审核(默认)',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`auditing_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `goods_id` int(10) UNSIGNED NOT NULL COMMENT '商品id',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户id',
  `nick_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `state` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论状态：-1-删除，0-正常（默认)',
  `created_at` datetime(0) NOT NULL COMMENT '评论创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '评论更新时间',
  INDEX `商品id外键2`(`goods_id`) USING BTREE,
  INDEX `订单id外键2`(`order_id`) USING BTREE,
  CONSTRAINT `商品id外键2` FOREIGN KEY (`goods_id`) REFERENCES `t_order_goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `订单id外键2` FOREIGN KEY (`order_id`) REFERENCES `t_order_goods` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (21, 'ef605dc481', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 'haohaohao', 0, '2018-08-24 10:57:33', '2018-08-24 10:57:33');
INSERT INTO `t_comment` VALUES (2, 'ef605dc481', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 'haohaohaohaohao', 0, '2018-08-24 10:57:45', '2018-08-24 10:57:45');
INSERT INTO `t_comment` VALUES (103, 'f7f638e3aa', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻', 0, '2018-08-24 10:58:38', '2018-08-24 10:58:38');
INSERT INTO `t_comment` VALUES (123, '20180824075220254', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻嘻', 0, '2018-08-24 19:55:58', '2018-08-24 19:55:58');
INSERT INTO `t_comment` VALUES (121, '20180824075205210', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻嘻', 0, '2018-08-24 20:09:58', '2018-08-24 20:09:58');
INSERT INTO `t_comment` VALUES (120, '20180824075205210', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻嘻', 0, '2018-08-24 20:10:43', '2018-08-24 20:10:43');
INSERT INTO `t_comment` VALUES (98, '16a2dc4b22', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻嘻', 0, '2018-08-24 20:11:58', '2018-08-24 20:11:58');
INSERT INTO `t_comment` VALUES (91, '16a2dc4b22', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻嘻', 0, '2018-08-24 20:12:02', '2018-08-24 20:12:02');
INSERT INTO `t_comment` VALUES (94, '16a2dc4b22', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '易烊千玺嘻嘻嘻嘻', 0, '2018-08-24 20:12:17', '2018-08-24 20:12:17');
INSERT INTO `t_comment` VALUES (104, 'f7f638e3aa', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '哈UI的哈文档化核对', 0, '2018-08-24 21:46:10', '2018-08-24 21:46:10');
INSERT INTO `t_comment` VALUES (105, 'f7f638e3aa', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '哈哈哈哈', 0, '2018-08-24 21:46:33', '2018-08-24 21:46:33');
INSERT INTO `t_comment` VALUES (112, '12de15c3b9', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '哈哈哈哈哈', 0, '2018-08-24 21:50:01', '2018-08-24 21:50:01');
INSERT INTO `t_comment` VALUES (118, '20180824075145285', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '哈哈哈哈哈', 0, '2018-08-24 21:51:07', '2018-08-24 21:51:07');
INSERT INTO `t_comment` VALUES (100, '89ed22dc29', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '是大家has就肯定会', 0, '2018-08-24 21:52:10', '2018-08-24 21:52:10');
INSERT INTO `t_comment` VALUES (119, '20180824075145285', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 'shuishuisasdasdasdawda', 0, '2018-08-26 15:06:03', '2018-08-26 15:06:03');
INSERT INTO `t_comment` VALUES (95, '16a2dc4b22', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '垃圾易烊千玺', 0, '2018-08-26 15:45:06', '2018-08-26 15:45:06');
INSERT INTO `t_comment` VALUES (107, '2018082709243721', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '这个手机真好用！', 0, '2018-08-28 16:28:17', '2018-08-28 16:28:17');
INSERT INTO `t_comment` VALUES (123, '2018082709243721', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '这个打印机真好用！', 0, '2018-08-28 16:28:53', '2018-08-28 16:28:53');
INSERT INTO `t_comment` VALUES (118, '12de15c3b9', 2, '测试买家号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', '啦啦啦', 0, '2018-08-29 06:59:21', '2018-08-29 06:59:21');
INSERT INTO `t_comment` VALUES (134, '20180827103145226', 2, '测试买家号', 'https://i.loli.net/2018/08/29/5b869f85ea905.png', '123456789', 0, '2018-08-30 08:36:23', '2018-08-30 08:36:23');

-- ----------------------------
-- Table structure for t_content
-- ----------------------------
DROP TABLE IF EXISTS `t_content`;
CREATE TABLE `t_content`  (
  `content_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章 id',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章 商品Id',
  `title1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章 标题1',
  `title2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章 标题2',
  `title3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章 标题3',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章 图片地址',
  `type` int(4) NOT NULL COMMENT '文章 类型：0-轮播图，1-热卖商品，2-新品首发，3-精选好物小图 ,4精选好物大图，5精选好物长图',
  `state` int(4) NOT NULL DEFAULT 0 COMMENT '文章 状态：0-正常，-1-删除',
  PRIMARY KEY (`content_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_content
-- ----------------------------
INSERT INTO `t_content` VALUES (4, '/goodsdetail/123', '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机（粉色）便捷操作，轻松打印 ', '8,900人付款', '￥999.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 1, 0);
INSERT INTO `t_content` VALUES (5, '/goodsdetail/125', '咪曦 茶具茶壶茶杯茶具套装哥窑功夫茶具开片陶瓷套装商务中秋礼品可定制logo 7头哥窑绿圆壶', '400人付款', '￥49.90', 'https://img14.360buyimg.com/n1/s546x546_jfs/t23311/74/1121293374/194834/23760e99/5b51516dNf9c640c0.jpg', 1, 0);
INSERT INTO `t_content` VALUES (6, '/goodsdetail/126', '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用黄色经典版 10片/盒\",', '32,000人付款', '￥129.00', 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 1, 0);
INSERT INTO `t_content` VALUES (7, '/goodsdetail/127', 'Avalon 阿瓦隆 薰衣草滋润柔顺洗发水 325ml', '1700人付款', '￥9.90', 'https://img12.360buyimg.com/n5/s450x450_jfs/t17035/342/2627121013/297674/952d2fa7/5afe7044Na75c969b.jpg', 1, 0);
INSERT INTO `t_content` VALUES (9, '/goodsdetail/72', '', NULL, NULL, 'https://i.loli.net/2018/08/14/5b72ccb54f924.png', 0, 0);
INSERT INTO `t_content` VALUES (10, '/goodsdetail/78', NULL, NULL, NULL, 'https://i.loli.net/2018/08/14/5b72ccc974ad4.png', 0, 0);
INSERT INTO `t_content` VALUES (11, '/goodsdetail/144', NULL, NULL, '', 'https://i.loli.net/2018/08/14/5b72ccb54f924.png', 0, -1);
INSERT INTO `t_content` VALUES (12, '/goodsdetail/143', NULL, NULL, NULL, 'https://i.loli.net/2018/08/14/5b72ccf244a99.png', 0, 0);
INSERT INTO `t_content` VALUES (13, '/goodsdetail/107', NULL, NULL, NULL, 'https://i.loli.net/2018/08/19/5b78cb5474b8b.jpg', 0, 0);
INSERT INTO `t_content` VALUES (14, '/goodsdetail/124', 'Apple iPhone 8 (A1863) 64GB 金色 移动联通电信4G手机 ', '91,000人付款', '￥4399.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t9085/22/907696059/71305/93f88c62/59b85847N20776d8e.jpg', 1, 0);
INSERT INTO `t_content` VALUES (15, '/goodsdetail/2', '凌美LAMY钢笔签字笔水笔Safari狩猎者男女商务办公礼品笔', '2,001人付款', '￥209.00', 'https://img13.360buyimg.com/n5/s450x450_jfs/t5137/19/1668282377/157656/53fe3116/5912f208N8fa82c1b.jpg', 1, 0);
INSERT INTO `t_content` VALUES (16, '/goodsdetail/117', '卡西欧 LINE FRIENDS | CASIO 联名系列女表运动防水手表 GMA-S120MF-7A2PRL-BROWN款 ', '200人付款', '￥1090.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t22390/180/2567352096/504791/ab981e60/5b5eb38aN5b637c5a.jpg', 2, 0);
INSERT INTO `t_content` VALUES (17, '/goodsdetail/114', '【德国品牌 保税仓发货】Max.Mece 夏季睡衣女短袖两件套冰丝绸缎睡衣休闲韩版家居服套装配发带 紫色', '400人付款', '￥199.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t21010/85/1127055981/135892/ba9e6a70/5b1ff8b2N7ec26b0a.jpg', 2, 0);
INSERT INTO `t_content` VALUES (19, '/goodsdetail/116', 'CHOW TAI FOOK x WANGKAI【预订】定制款爱之星辰18K金钻石项链FU129510 9月6日前发货 40cm 5800元（黄K金链）', '2,001人付款', '￥5899.00', 'https://m.360buyimg.com/mobilecms/s750x750_jfs/t24457/221/2186534122/86286/664010f2/5b76e805N05f6ccc8.jpg!q80.dpg', 2, 0);
INSERT INTO `t_content` VALUES (20, '/goodsdetail/118', '新品上市 雀巢（Nestle）咖啡1+2原味速溶微研磨咖啡饮品100条1500g ', '342人付款', '￥112.00', 'https://img10.360buyimg.com/n1/jfs/t22405/41/2461238360/386634/e1760c1/5b580f43Nab2f0073.jpg', 2, 0);
INSERT INTO `t_content` VALUES (21, '/goodsdetail/119', '兰蔻（lancome） 【专柜直供】新款兰蔻菁纯柔润唇釉唇膏漆光唇釉王俊凯同款 134#樱桃红 500# ', '201人付款', '￥299.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t23413/146/1301003408/33221/8b0cb110/5b598c8eN3da82167.jpg', 2, 0);
INSERT INTO `t_content` VALUES (22, '/goodsdetail/120', '网易严选 喷塑便携运动水杯 落沙黄 ', '1,050人付款', '￥129.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t25720/171/6744192/154348/59ec0c84/5b62ac9dNdac8178a.png', 2, 0);
INSERT INTO `t_content` VALUES (23, '/goodsdetail/139', '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', '2,199人付款', '￥4599.00', 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 3, 0);
INSERT INTO `t_content` VALUES (24, '/goodsdetail/137', '富士（FUJIFILM）INSTAX 一次成像相机 MINI25相机 白色 ', '6,554人付款', '￥699.00', 'https://img13.360buyimg.com/n1/s450x450_jfs/t2611/251/2644635268/106180/420c1e96/576cf3c0N925362b0.jpg', 3, 0);
INSERT INTO `t_content` VALUES (25, '/search/The North Face/北面男卫衣外套连帽开衫纯色抓绒美国直邮tnf150', '大牌推荐 >', '品质男装', '奢侈好物', 'https://i.loli.net/2018/08/14/5b72cb73642b5.png', 4, 0);
INSERT INTO `t_content` VALUES (26, '/search/Jimmy Choo黑色羊皮经典百搭尖头女士高跟鞋', '我型我塑 >', '女神必备', '魅力无限', 'https://i.loli.net/2018/08/14/5b72cc684c34b.png', 4, 0);
INSERT INTO `t_content` VALUES (27, '/search/KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男', '爆款套装 >', '时尚有我', '品质衣橱', 'https://i.loli.net/2018/08/14/5b72cc7e0640f.png', 5, 0);
INSERT INTO `t_content` VALUES (28, '', 'nike秋季款Nike Air Max Air Force Sneakers', '500人付款', '￥1130.00', 'https://i.loli.net/2018/08/14/5b72cd5f79452.png', 5, -1);
INSERT INTO `t_content` VALUES (29, '/goodsdetail/138', '太阳芦荟社太阳社玻尿酸保湿原液 10ml/支 高效保湿锁水（cosme直供 大赏推荐 日本原装进口）', '610,554人付款', '￥45', 'https://img13.360buyimg.com/n5/s450x450_jfs/t19282/114/1124543108/137470/9c873272/5abc62caN2a3721e3.jpg', 3, 0);
INSERT INTO `t_content` VALUES (30, '/goodsdetail/133', '官方推荐', '火热促销', '￥199', 'https://i.loli.net/2018/08/17/5b76832412a49.png', 5, -1);
INSERT INTO `t_content` VALUES (31, '/goodsdetail/133', '官方推荐', '火热促销', '￥299', 'https://i.loli.net/2018/08/17/5b7685ad45279.png', 5, -1);
INSERT INTO `t_content` VALUES (32, '/search/CONVERSEHello Kitty', NULL, NULL, NULL, 'https://i.loli.net/2018/08/27/5b83bb6231a2f.jpg', 0, 0);
INSERT INTO `t_content` VALUES (33, '/goodsdetail/103', NULL, NULL, NULL, 'https://res2.vmallres.com/shopdc/pic/f814baed-8398-4617-840f-5292f41c7bb6.png', 0, 0);
INSERT INTO `t_content` VALUES (38, '/goodsdetail/171', '索尼（SONY）智能蓝牙音箱 LF-S80D（白色） ', '2,600人付款', '￥1899.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t10300/100/1315392557/167578/4eb216ca/59df2dd7N7c0882f4.jpg', 1, 0);
INSERT INTO `t_content` VALUES (39, '/goodsdetail/172', '索尼（SONY）ILCE-7M2K 全画幅微单相机标准套机（约2430万有效像素 a7M2K）', '12,099人付款', '￥8479.00', 'https://img14.360buyimg.com/n1/s546x546_jfs/t20149/237/18309204/153818/58a7d8de/5af566afN7e8bf13d.jpg', 1, 0);
INSERT INTO `t_content` VALUES (40, '', NULL, NULL, NULL, 'https://i.loli.net/2018/08/27/5b83a43d0d4ef.png', 6, 0);

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `goods_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品 id',
  `shop_id` int(10) UNSIGNED NOT NULL COMMENT '商品所属店铺id',
  `shop_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品所属店铺名称',
  `platform_goods_category_id` int(10) UNSIGNED NOT NULL COMMENT '商品 所属平台类别id',
  `shop_goods_category_id` int(10) UNSIGNED NOT NULL COMMENT '商品 所属店铺类别id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品 名称',
  `property` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品 属性/规格',
  `stock_num` int(10) NOT NULL COMMENT '商品 库存数量',
  `price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '商品 价格',
  `picture` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品 预览图片地址',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品 介绍图片HTML',
  `sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品 销量',
  `comment_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品 评论总数',
  `collection_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品 被收藏数量',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '商品 状态：0-正常(默认)，-1-下架',
  `created_at` datetime(0) NOT NULL COMMENT '商品 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '商品 更新时间',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `商铺id外键`(`shop_id`) USING BTREE,
  INDEX `商品所属商铺类别id外键`(`shop_goods_category_id`) USING BTREE,
  INDEX `商品所属平台类别id外键`(`platform_goods_category_id`) USING BTREE,
  CONSTRAINT `商品所属商铺类别id外键` FOREIGN KEY (`shop_goods_category_id`) REFERENCES `t_shop_goods_category` (`shop_goods_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `商品所属平台类别id外键` FOREIGN KEY (`platform_goods_category_id`) REFERENCES `t_platform_goods_category` (`platform_goods_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `商铺id外键` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 225 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, 1, '云顶官方旗舰店', 7, 7, '红木质中国风古风创意礼物 精美限量版书签 夕情人节', '紫光檀', 100, 128.00, 'https://img10.360buyimg.com/n1/jfs/t8485/362/1492082332/301096/c6474e80/59ba4b47N1833841f.jpg', NULL, 0, 0, 0, 0, '2018-08-03 17:52:44', '2018-08-28 19:36:09');
INSERT INTO `t_goods` VALUES (2, 1, '云顶官方旗舰店', 7, 7, '凌美LAMY钢笔签字笔水笔Safari狩猎者男女商务办公礼品笔', 'F尖', 101, 209.00, 'https://img13.360buyimg.com/n5/s450x450_jfs/t5137/19/1668282377/157656/53fe3116/5912f208N8fa82c1b.jpg', NULL, 0, 1, 0, 0, '2018-08-03 19:10:02', '2018-08-28 16:29:49');
INSERT INTO `t_goods` VALUES (3, 1, '云顶官方旗舰店', 7, 7, '凌美LAMY钢笔墨水签字笔钢笔水笔 专用50ml非碳素墨水黑色狩猎者恒星通用 德国原装进口文具', '黑色', 100, 69.00, 'https://img14.360buyimg.com/n5/s450x450_jfs/t2791/362/1338321164/205768/609dd157/573b3607N8fe46ee9.jpg', NULL, 0, 0, 0, 0, '2018-08-03 19:15:11', '2018-08-03 19:15:21');
INSERT INTO `t_goods` VALUES (4, 1, '云顶官方旗舰店', 7, 7, '得力(deli)0359金属钢质耐用办公订书机套装（订书机+订书钉+起钉器）', '通用', 100, 15.50, 'https://img13.360buyimg.com/n1/s450x450_jfs/t3124/340/6188740607/237654/7a692d55/58a104f6Nc78a2a7f.jpg', NULL, 0, 0, 0, 0, '2018-08-03 19:17:56', '2018-08-03 19:18:02');
INSERT INTO `t_goods` VALUES (5, 1, '云顶官方旗舰店', 7, 7, '得力(deli)6本B5/60页牛皮纸软抄本笔记本子 缝线文具记事本33387', '通用', 100, 28.00, 'https://img12.360buyimg.com/n1/s450x450_jfs/t17716/175/1121445480/317126/10c8149c/5abc3f07N0fb5f129.jpg', NULL, 0, 0, 0, 0, '2018-08-03 19:21:07', '2018-08-03 19:21:14');
INSERT INTO `t_goods` VALUES (6, 1, '云顶官方旗舰店', 7, 7, '小米（MI） 米家金属笔 中性笔0.5mm油墨笔水笔学生文具', '通用', 98, 24.90, 'https://img12.360buyimg.com/n1/s450x450_jfs/t14860/116/2090661952/37035/3ff9e5f1/5a66ce31Na25721c9.jpg', NULL, 2, 0, 0, 0, '2018-08-03 19:23:17', '2018-08-27 23:24:18');
INSERT INTO `t_goods` VALUES (7, 1, '云顶官方旗舰店', 7, 7, '2本 Python编程从入门到实践+Python核心编程 第3版 零基础编程入门书籍', '通用', 100, 188.00, 'https://img10.360buyimg.com/n1/jfs/t22012/216/2048029989/209943/83609280/5b45bd8bN27fd35b4.jpg', NULL, 0, 0, 0, 0, '2018-08-04 08:11:27', '2018-08-04 08:11:31');
INSERT INTO `t_goods` VALUES (8, 1, '云顶官方旗舰店', 7, 7, 'C Primer Plus 第6版 中文版', '通用', 100, 78.00, 'https://img12.360buyimg.com/n1/jfs/t18727/11/537625758/296118/938d791f/5a93c36fN44a747c0.jpg', NULL, 0, 0, 0, 0, '2018-08-04 08:14:13', '2018-08-04 08:14:14');
INSERT INTO `t_goods` VALUES (9, 1, '云顶官方旗舰店', 7, 7, '算法导论（原书第3版）/计算机科学丛书 ', '通用', 100, 102.40, 'https://img10.360buyimg.com/n1/g9/M03/10/0C/rBEHalDFX10IAAAAAAMtdd_bKwEAADM6gOC6NoAAy2N265.jpg', NULL, 0, 0, 0, 0, '2018-08-04 08:16:57', '2018-08-04 08:16:59');
INSERT INTO `t_goods` VALUES (10, 1, '云顶官方旗舰店', 7, 7, '得力（deli）7144 502强力胶（8g） 单支装', '8g', 100, 2.00, 'https://img11.360buyimg.com/n1/s450x450_jfs/t7267/75/301945691/136525/5adc5ef9/59915c31Nab0644f0.jpg', NULL, 0, 0, 0, 0, '2018-08-04 10:11:57', '2018-08-04 10:12:02');
INSERT INTO `t_goods` VALUES (11, 1, '云顶官方旗舰店', 7, 7, '得力（deli）18880 强吸力桌面吸尘器/清洁助手 键盘除尘/迷你便携 ', '浅绿', 100, 29.90, 'https://img11.360buyimg.com/n1/s450x450_jfs/t23326/229/721969964/288360/cdf1cf8/5b3ed984N1facf322.jpg', NULL, 0, 0, 0, 0, '2018-08-04 10:14:27', '2018-08-04 10:14:28');
INSERT INTO `t_goods` VALUES (12, 1, '云顶官方旗舰店', 7, 7, '华杰A4加厚塑料档案盒收纳盒档案袋办公用品学生文具耐用型魔术粘扣文件盒资料盒档案盒 H828', 'A4', 100, 66.00, 'https://img14.360buyimg.com/n1/jfs/t17950/339/833020072/452769/f8b8bec5/5aaa342eNfc4b1ced.jpg', NULL, 0, 0, 0, 0, '2018-08-04 10:19:51', '2018-08-04 10:19:53');
INSERT INTO `t_goods` VALUES (13, 1, '云顶官方旗舰店', 7, 7, '齐心（COMIX）A5 122张优品商务笔记本子/记事本/日记本 黑色 办公文具C5902', 'A5', 100, 11.50, 'https://img11.360buyimg.com/n1/s450x450_jfs/t2740/293/3162983987/215697/f52f5509/57830761N92e0eb65.jpg', NULL, 0, 0, 0, 0, '2018-08-04 10:30:59', '2018-08-04 10:31:00');
INSERT INTO `t_goods` VALUES (14, 1, '云顶官方旗舰店', 7, 7, '编程珠玑（第2版 修订版）', '通用', 100, 32.80, 'https://img14.360buyimg.com/n1/jfs/t733/236/713204451/71588/7e62c286/54d332abN7e74cb0b.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:32:34', '2018-08-04 11:32:39');
INSERT INTO `t_goods` VALUES (15, 1, '云顶官方旗舰店', 7, 7, 'Spring实战（第4版）', '通用', 100, 74.80, 'https://img10.360buyimg.com/n1/jfs/t17503/334/529692453/390518/89ee87cb/5a93c36fNa6a46a43.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:34:33', '2018-08-04 11:34:35');
INSERT INTO `t_goods` VALUES (16, 1, '云顶官方旗舰店', 7, 7, 'Python编程 从入门到实践', '通用', 100, 74.80, 'http://img14.360buyimg.com/n1/jfs/t17953/201/1450663539/451183/3262b8de/5acb3627N8191c867.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:38:46', '2018-08-04 11:38:48');
INSERT INTO `t_goods` VALUES (17, 1, '云顶官方旗舰店', 7, 7, 'JavaScript权威指南（第6版）', '通用', 100, 110.50, 'http://img11.360buyimg.com/n1/jfs/t2047/63/2033152973/398005/5ea9044e/56ea5e6aN38c0f2b8.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:41:31', '2018-08-04 11:41:35');
INSERT INTO `t_goods` VALUES (18, 1, '云顶官方旗舰店', 7, 7, '平凡的世界：全三册', '通用', 100, 81.00, 'http://img12.360buyimg.com/n1/jfs/t5587/350/4618116432/255954/cd4c1951/59521501N4c19726f.jpg', NULL, 3, 0, 0, 0, '2018-08-04 11:45:15', '2018-08-26 09:03:47');
INSERT INTO `t_goods` VALUES (19, 1, '云顶官方旗舰店', 7, 7, '月亮与六便士（新版！未删节插图珍藏版，荣获波比小说奖诗人译本，夺得2017豆瓣阅读图书桂冠！）', '通用', 100, 29.90, 'http://img11.360buyimg.com/n1/jfs/t17326/349/2562551684/188787/a64aa1c/5afbde1cNaa625c8d.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:47:05', '2018-08-04 11:47:07');
INSERT INTO `t_goods` VALUES (20, 1, '云顶官方旗舰店', 7, 7, '人性的弱点全集（完整全译本）', '通用', 100, 37.40, 'http://img13.360buyimg.com/n1/jfs/t5146/84/950518396/276698/7ccbe391/59098365N7aa37021.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:48:48', '2018-08-04 11:48:50');
INSERT INTO `t_goods` VALUES (21, 1, '云顶官方旗舰店', 8, 8, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', '230ml', 90, 1199.00, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', NULL, 20, 1, 0, 0, '2018-08-04 11:51:36', '2018-08-28 16:29:49');
INSERT INTO `t_goods` VALUES (22, 1, '云顶官方旗舰店', 8, 8, 'SK-II\"小灯泡\"30ml美白保湿精华露护肤套装', '30ml', 100, 1110.00, 'https://img13.360buyimg.com/n1/jfs/t20989/21/1452820745/104418/5d8006a5/5b2a0aa8N990d5bb0.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:53:06', '2018-08-04 11:53:07');
INSERT INTO `t_goods` VALUES (23, 1, '云顶官方旗舰店', 8, 8, '迪奥（Dior）烈艳蓝金唇膏-哑光系列999 3.5g（迪奥口红 迪奥999 显色持久 传奇红）', '999#', 100, 300.00, 'https://img13.360buyimg.com/n1/jfs/t19198/297/962642447/95301/4dda0146/5ab49e31N9811560d.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:55:03', '2018-08-04 11:55:04');
INSERT INTO `t_goods` VALUES (24, 1, '云顶官方旗舰店', 8, 8, '玛亚科布（MARC JACOBS） 莫杰女士香水小雏菊 粉色清甜雏菊75ml', '75ml', 100, 400.00, 'https://img12.360buyimg.com/n1/jfs/t15610/255/2668650562/261088/ed7d75e2/5ab8563cNbe8be9b2.jpg', NULL, 0, 0, 0, 0, '2018-08-04 11:57:56', '2018-08-04 11:57:58');
INSERT INTO `t_goods` VALUES (25, 1, '云顶官方旗舰店', 8, 8, '欧舒丹（L\'OCCITANE） 润肤护手霜 套装 玫瑰皇后+乳木果+樱花30ml', '套装', 100, 168.00, 'https://img10.360buyimg.com/n5/s450x450_jfs/t16441/45/2746055864/171596/f5305848/5ab9fe64Na417d7c2.jpg', NULL, 1, 0, 0, 0, '2018-08-04 11:59:39', '2018-08-26 09:04:02');
INSERT INTO `t_goods` VALUES (26, 1, '云顶官方旗舰店', 8, 8, 'Estee Lauder雅诗兰黛粉底液', '30ml', 100, 420.00, 'https://img12.360buyimg.com/n1/jfs/t22378/267/1041530294/259794/4ab78c80/5b1f2d96N3343288d.jpg', NULL, 0, 0, 0, 0, '2018-08-04 12:01:41', '2018-08-04 12:01:57');
INSERT INTO `t_goods` VALUES (27, 1, '云顶官方旗舰店', 8, 8, '安热沙（Anessa）水能户外防晒乳60ml（安全 耐晒 防晒霜 小金瓶 防水防汗 资生堂防晒', '60ml', 100, 208.00, 'https://img12.360buyimg.com/n1/jfs/t23209/327/559050774/225325/d14335ba/5b34ab94N1166abe9.jpg', NULL, 0, 0, 0, 0, '2018-08-04 14:05:18', '2018-08-04 14:05:20');
INSERT INTO `t_goods` VALUES (28, 1, '云顶官方旗舰店', 8, 8, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 'R250', 100, 400.00, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', NULL, 4, 0, 0, 0, '2018-08-04 14:07:47', '2018-08-26 09:03:54');
INSERT INTO `t_goods` VALUES (29, 1, '云顶官方旗舰店', 8, 8, '日本雪肌精SEKKISEI大容量黄金水乳活力礼盒', '套装', 100, 610.00, 'https://img13.360buyimg.com/n1/jfs/t18238/207/1975470423/192091/90da8cff/5ae04c6cN7b47ba91.jpg', NULL, 0, 0, 0, 0, '2018-08-04 14:10:23', '2018-08-04 14:10:25');
INSERT INTO `t_goods` VALUES (30, 1, '云顶官方旗舰店', 8, 8, '雅诗兰黛（Estee Lauder）花漾倾慕口红唇膏 3.5g 420#13 表姐豆沙色', '420#13', 100, 270.00, 'https://img14.360buyimg.com/n5/s450x450_jfs/t19978/259/2593520638/63397/7827a59f/5b485c89Nd96a2ae4.jpg', NULL, 0, 0, 0, 0, '2018-08-04 14:15:49', '2018-08-04 14:15:51');
INSERT INTO `t_goods` VALUES (31, 1, '云顶官方旗舰店', 6, 6, '三只松鼠 氧气吐司800g 整箱装 早餐吐司面包 夹心面包炼奶三明治 ', '800g', 100, 36.00, 'https://img11.360buyimg.com/n1/jfs/t24019/138/1406463538/199103/96769c7b/5b5fd75bN58703ec4.jpg', NULL, 0, 0, 0, 0, '2018-08-04 14:18:08', '2018-08-04 14:18:10');
INSERT INTO `t_goods` VALUES (32, 1, '云顶官方旗舰店', 6, 6, '蒙牛 纯甄 常温酸牛奶 200g*12 礼盒装', '礼盒装', 100, 52.90, 'https://img12.360buyimg.com/n1/jfs/t17353/351/2037214723/146436/26f27461/5ae2e39dNcb2b7780.jpg', NULL, 0, 0, 0, 0, '2018-08-04 14:25:25', '2018-08-04 14:25:35');
INSERT INTO `t_goods` VALUES (33, 1, '云顶官方旗舰店', 6, 6, '三只松鼠碧根果225g坚果零食干果核桃长寿果碧更果', '225g', 100, 20.00, 'https://img.alicdn.com/imgextra/i4/725677994/TB2vmzWHL9TBuNjy1zbXXXpepXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:18:02', '2018-08-05 10:18:10');
INSERT INTO `t_goods` VALUES (34, 1, '云顶官方旗舰店', 6, 6, '亿滋奥利奥夹心饼干原味349g独立装12小包装零食', '349g', 100, 13.00, 'https://img.alicdn.com/imgextra/i4/725677994/TB2AU1hogvD8KJjy0FlXXagBFXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:22:04', '2018-08-05 10:22:56');
INSERT INTO `t_goods` VALUES (35, 1, '云顶官方旗舰店', 6, 6, '日本进口白色恋人黑白混合巧克力夹心饼干24枚伴手礼', '套装', 100, 169.00, 'https://img.alicdn.com/imgextra/i3/725677994/TB2RPb8n9YH8KJjSspdXXcRgVXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:24:26', '2018-08-05 10:24:35');
INSERT INTO `t_goods` VALUES (36, 1, '云顶官方旗舰店', 6, 6, '千业吐司面包1Kg半切片三明治早餐蛋糕炼奶乳夹心整箱的零食品叶', '1kg', 100, 42.80, 'https://img.alicdn.com/imgextra/i3/725677994/TB2deVZXhuTBuNkHFNRXXc9qpXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:26:38', '2018-08-05 10:26:45');
INSERT INTO `t_goods` VALUES (37, 1, '云顶官方旗舰店', 6, 6, '澳洲进口德运全脂青少年成人冲饮牛奶粉1kg新鲜', '1kg', 100, 69.00, 'https://img.alicdn.com/imgextra/i2/725677994/TB2is_XeMjN8KJjSZFkXXaboXXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:28:58', '2018-08-05 10:29:02');
INSERT INTO `t_goods` VALUES (38, 1, '云顶官方旗舰店', 6, 6, '百草味华夫饼1000g早餐糕点零食手撕面包休闲蛋糕小吃', '1kg', 100, 32.90, 'https://img.alicdn.com/imgextra/i3/725677994/TB2z.1lgBjTBKNjSZFDXXbVgVXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:31:24', '2018-08-05 10:31:31');
INSERT INTO `t_goods` VALUES (39, 1, '云顶官方旗舰店', 6, 6, '卫龙辣条亲嘴烧混合装300g混搭网红小零食儿时湖南重庆麻辣大刀肉', '300g', 100, 19.90, 'https://img.alicdn.com/imgextra/i1/725677994/TB2r1kLG1ySBuNjy1zdXXXPxFXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:32:54', '2018-08-05 10:32:58');
INSERT INTO `t_goods` VALUES (40, 1, '云顶官方旗舰店', 6, 6, '光明莫斯利安原味酸奶200g*24盒家庭装巴士杀菌', '套装', 100, 84.90, 'https://img.alicdn.com/imgextra/i3/725677994/TB2u3ZuiiCYBuNkSnaVXXcMsVXa_!!725677994-0-sm.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:36:04', '2018-08-05 10:36:09');
INSERT INTO `t_goods` VALUES (41, 1, '云顶官方旗舰店', 5, 5, '阿迪达斯官方adidas 三叶草 POD-S3.1 男子 经典鞋 B37366', '40', 100, 1099.00, 'https://img.alicdn.com/imgextra/i1/446338500/TB2YX9cG1uSBuNjSsplXXbe8pXa_!!446338500-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:39:04', '2018-08-05 10:39:11');
INSERT INTO `t_goods` VALUES (42, 1, '云顶官方旗舰店', 5, 5, 'CONVERSE匡威官方 All Star Dainty 轻便薄底 537213C', '40', 100, 358.00, 'https://img.alicdn.com/imgextra/i3/1046707508/TB1wKqAlmYTBKNjSZKbXXXJ8pXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:42:08', '2018-08-05 10:42:14');
INSERT INTO `t_goods` VALUES (43, 1, '云顶官方旗舰店', 5, 5, 'Nike Air Jordan 1 Bred Toe AJ1乔1 黑红脚趾 男鞋 ', '40', 100, 4109.00, 'https://img.alicdn.com/imgextra/i2/2914266524/TB2c2HcaSBYBeNjy0FeXXbnmFXa_!!2914266524.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:44:13', '2018-08-05 10:44:19');
INSERT INTO `t_goods` VALUES (44, 1, '云顶官方旗舰店', 5, 5, 'Under Armour 安德玛 UA女子 Whisperlight 运动训练T恤', 'M', 100, 349.00, 'https://img.alicdn.com/imgextra/i4/1940724523/TB2Mh0jGHSYBuNjSspiXXXNzpXa_!!1940724523-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:46:23', '2018-08-05 10:46:29');
INSERT INTO `t_goods` VALUES (45, 1, '云顶官方旗舰店', 5, 5, '美国ICON爱康跑步机PETL79716/sport7.0折叠静音智能进口品牌健', '通用', 100, 4999.00, 'https://img.alicdn.com/imgextra/i4/776029167/TB1j2llg3oQMeJjy1XaXXcSsFXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:55:13', '2018-08-05 10:55:15');
INSERT INTO `t_goods` VALUES (46, 1, '云顶官方旗舰店', 5, 5, '美国lifespan莱仕邦 椭圆机室内 家用磁控超静音太空漫步机EL15\n磁控阻力', '通用', 100, 2355.00, 'https://img.alicdn.com/imgextra/i3/2138238418/TB2t40rarEF6uJjSZFOXXXUvVXa_!!2138238418.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 10:57:02', '2018-08-05 10:57:05');
INSERT INTO `t_goods` VALUES (47, 1, '云顶官方旗舰店', 5, 5, '李宁羽毛球拍双拍2支初学业余初级全碳素羽毛球轻便耐打健身', '2支', 100, 299.00, 'https://img.alicdn.com/imgextra/i2/2752862235/TB1I..NEYSYBuNjSspiXXXNzpXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:20:22', '2018-08-05 14:20:26');
INSERT INTO `t_goods` VALUES (48, 1, '云顶官方旗舰店', 5, 5, '李宁泳镜男女游泳眼镜高清防雾近视大框度数成人儿童防水潜水装备', '通用', 100, 59.00, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/bao/uploaded/i3/2420035235/TB2JtHhIY1YBuNjSszeXXablFXa_!!2420035235.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:23:06', '2018-08-05 14:23:14');
INSERT INTO `t_goods` VALUES (49, 1, '云顶官方旗舰店', 5, 5, 'Nike 耐克官方 NIKE PRO 女子短袖训练上衣', 'M', 100, 199.00, 'https://img.alicdn.com/imgextra/i1/890482188/TB2BDOSv7SWBuNjSszdXXbeSpXa_!!890482188.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:25:12', '2018-08-05 14:25:15');
INSERT INTO `t_goods` VALUES (50, 1, '云顶官方旗舰店', 5, 5, 'Fitbit Classic Alta 腕带', '大号', 100, 1024.00, 'https://img.alicdn.com/imgextra/i4/2900954418/TB28vMoGxSYBuNjSsphXXbGvVXa_!!2900954418.png_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:28:08', '2018-08-05 14:28:13');
INSERT INTO `t_goods` VALUES (51, 1, '云顶官方旗舰店', 4, 4, '小米体重秤智能家用婴儿称重成人健康减肥称精准迷你人体电子秤', '通用', 100, 119.00, 'https://img.alicdn.com/imgextra/i2/1714128138/TB1uht1fNWYBuNjy1zkXXXGGpXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:30:29', '2018-08-05 14:30:35');
INSERT INTO `t_goods` VALUES (52, 1, '云顶官方旗舰店', 4, 4, 'Apple/苹果 iPhone X', '256g', 100, 9605.00, 'https://img.alicdn.com/imgextra/i4/1917047079/TB2ZpydmPihSKJjy0FeXXbJtpXa_!!1917047079.png_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:36:59', '2018-08-05 14:37:05');
INSERT INTO `t_goods` VALUES (53, 1, '云顶官方旗舰店', 4, 4, '【享6期免息赠移动电源】Huawei/华为 P20 全面屏刘海屏徕卡双摄官方正品旗舰智能手机', '6g+128g', 100, 3788.00, 'https://img.alicdn.com/imgextra/i4/2838892713/O1CN011VuayQGwF94GHgy_!!2838892713.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-05 14:44:35', '2018-08-05 14:44:40');
INSERT INTO `t_goods` VALUES (54, 1, '云顶官方旗舰店', 4, 4, 'Xiaomi/小米 小米小钢炮蓝牙音箱2 手机电脑户外无线迷你便携音响', '通用', 100, 129.00, 'https://img.alicdn.com/imgextra/i1/1714128138/TB24VomlVXXXXbLXXXXXXXXXXXX_!!1714128138.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-06 15:30:00', '2018-08-06 15:30:06');
INSERT INTO `t_goods` VALUES (55, 1, '云顶官方旗舰店', 4, 4, 'TP-LINK 路由器 无线家用穿墙高速wifi 穿墙王 ', '通用', 100, 119.90, 'https://img.alicdn.com/imgextra/i1/1040450854/TB2efillbYI8KJjy0FaXXbAiVXa_!!1040450854.jpg_430x430q90.jpg', NULL, 3, 0, 0, 0, '2018-08-06 15:34:59', '2018-08-26 09:03:54');
INSERT INTO `t_goods` VALUES (56, 1, '云顶官方旗舰店', 4, 4, 'Apple/苹果 AirPods', '通用', 100, 1276.00, 'https://img.alicdn.com/imgextra/i1/TB1.6cyNXXXXXbjXFXXtQkr9pXX_042310.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-06 15:36:32', '2018-08-06 15:36:40');
INSERT INTO `t_goods` VALUES (57, 1, '云顶官方旗舰店', 4, 4, 'ROMOSS/罗马仕 sense6 20000M毫安充电宝 正品手机通用移动电源', '20000mA', 100, 89.00, 'https://img.alicdn.com/imgextra/i3/897609396/TB2tsEYIr9YBuNjy0FgXXcxcXXa_!!897609396-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-06 15:39:41', '2018-08-06 15:39:47');
INSERT INTO `t_goods` VALUES (58, 1, '云顶官方旗舰店', 4, 4, 'Sony/索尼 头戴式无线蓝牙降噪耳机索尼1000xm2二代主动降噪耳机手机无线耳机头戴式', '黑色', 100, 2699.00, 'https://img.alicdn.com/imgextra/i1/1956576107/TB261SFXVLM8KJjSZFBXXXJHVXa_!!1956576107.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-06 15:40:46', '2018-08-06 15:40:51');
INSERT INTO `t_goods` VALUES (59, 1, '云顶官方旗舰店', 4, 4, 'Sony/索尼 无线蓝牙运动耳机入耳后挂式\n官方正品 运动伴侣 一年包换 极速发货', '蓝色', 100, 429.00, 'https://img.alicdn.com/imgextra/i3/1956576107/TB2oSGzwrXlpuFjSszfXXcSGXXa_!!1956576107.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-06 15:47:05', '2018-08-06 15:47:11');
INSERT INTO `t_goods` VALUES (60, 1, '云顶官方旗舰店', 4, 4, '罗技G610电竞游戏吃鸡机械键盘cherry樱桃茶轴红轴青轴背光绝地求生神器 ', 'G610', 100, 499.00, 'https://img.alicdn.com/imgextra/i2/2863054326/TB1idUSyY1YBuNjSszhXXcUsFXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-06 15:50:37', '2018-08-06 15:50:45');
INSERT INTO `t_goods` VALUES (61, 1, '云顶官方旗舰店', 1, 3, '玻璃瓶3件套玻璃密封罐泡百香果柠檬蜂蜜瓶果酱罐子酵素瓶储物罐', '套装', 100, 29.90, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i1/885693446/TB21Q3PIKSSBuNjy0FlXXbBpVXa_!!885693446.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:03:59', '2018-08-07 08:04:07');
INSERT INTO `t_goods` VALUES (62, 1, '云顶官方旗舰店', 1, 1, '苏泊尔真不锈铁锅无涂层不生锈炒锅燃气明火专用家用精铁熟铁锅', '通用', 99, 299.00, 'https://img.alicdn.com/imgextra/i2/699994102/TB2ZiKLpgDD8KJjy0FdXXcjvXXa_!!699994102.jpg_430x430q90.jpg', NULL, 1, 0, 0, 0, '2018-08-07 08:06:36', '2018-08-30 08:44:24');
INSERT INTO `t_goods` VALUES (63, 1, '云顶官方旗舰店', 1, 1, '九阳榨汁机家用原汁机炸水果汁机全自动果蔬多功能小型汁渣分离v5', '通用', 100, 469.00, 'https://img.alicdn.com/imgextra/i1/415670368/TB2aZOmdNaK.eBjSZFAXXczFXXa_!!415670368.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:10:40', '2018-08-07 08:10:43');
INSERT INTO `t_goods` VALUES (64, 1, '云顶官方旗舰店', 3, 3, '冰箱收纳盒长方形抽屉式鸡蛋盒食品冷冻盒厨房收纳保鲜塑料储物盒', '通用', 100, 29.80, 'https://img.alicdn.com/imgextra/i4/326141025/TB2PyEZaHT8F1Jjy0FgXXX3fpXa_!!326141025.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:15:45', '2018-08-07 08:15:50');
INSERT INTO `t_goods` VALUES (65, 1, '云顶官方旗舰店', 3, 3, '水星家纺四件套全棉纯棉特价床单四件套夏季1.8米床床上四件套', '套装', 100, 299.00, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/92042735/TB24b2el_CWBKNjSZFtXXaC3FXa_!!92042735.jpg_430x430q90.jpg', NULL, 2, 0, 0, 0, '2018-08-07 08:17:49', '2018-08-26 09:04:02');
INSERT INTO `t_goods` VALUES (66, 1, '云顶官方旗舰店', 3, 3, 'BANANAUNDER蕉下莲町防晒小黑伞折叠晴雨伞女防紫外线太阳遮阳伞', '通用', 100, 199.00, 'https://img.alicdn.com/imgextra/i1/1652864050/TB2.zyAoFOWBuNjy0FiXXXFxVXa_!!1652864050.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:23:45', '2018-08-07 08:23:48');
INSERT INTO `t_goods` VALUES (67, 1, '云顶官方旗舰店', 3, 3, 'ohsunny防晒口罩女夏薄款透气可清洗易呼吸露鼻防紫外线黑色口罩', '通用', 100, 39.00, 'https://img.alicdn.com/imgextra/i2/3351172141/TB2jlWjX4TI8KJjSspiXXbM4FXa_!!3351172141.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:26:25', '2018-08-07 08:26:29');
INSERT INTO `t_goods` VALUES (68, 1, '云顶官方旗舰店', 3, 3, '海飞丝正品洗发水丝质柔滑去屑止痒洗发露男女士洗头膏套装500*3', '套装', 100, 99.90, 'https://img.alicdn.com/imgextra/i4/217101303/TB2PbAqJkOWBuNjSsppXXXPgpXa_!!217101303.png_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:28:48', '2018-08-07 08:28:54');
INSERT INTO `t_goods` VALUES (69, 1, '云顶官方旗舰店', 3, 3, '云南白药牙膏留兰香型180g减轻牙龈出血去牙渍去口臭口气清新', '180g', 100, 28.30, 'https://img.alicdn.com/imgextra/i1/725677994/TB2VuotdRsmBKNjSZFsXXaXSVXa_!!725677994.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:30:19', '2018-08-07 08:30:23');
INSERT INTO `t_goods` VALUES (70, 1, '云顶官方旗舰店', 3, 3, '天猫魔盒 3S 语音智能网络电视机顶盒子高清播放器家用WIFI', '通用', 100, 249.00, 'https://img.alicdn.com/imgextra/i2/1862759827/TB1jBNHJbSYBuNjSspfXXcZCpXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 08:32:48', '2018-08-07 08:32:54');
INSERT INTO `t_goods` VALUES (71, 1, '云顶官方旗舰店', 2, 2, 'Vero Moda2018秋季新款仙女风网纱拼接七分袖连衣裙|31837C522', 'M', 100, 899.00, 'https://img.alicdn.com/imgextra/i3/420567757/TB2QEeeHeuSBuNjSsziXXbq8pXa-420567757.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:06:57', '2018-08-19 10:25:24');
INSERT INTO `t_goods` VALUES (72, 1, '云顶官方旗舰店', 2, 2, 'JucyJudy官方旗舰店官网2018秋不规则笑脸牛仔半身长裙女', 'M', 99, 598.00, 'https://img.alicdn.com/imgextra/i1/2645780884/TB2RYU8w9tYBeNjSspkXXbU8VXa_!!2645780884.jpg_430x430q90.jpg', NULL, 1, 0, 0, 0, '2018-08-07 14:08:28', '2018-08-28 19:39:34');
INSERT INTO `t_goods` VALUES (73, 1, '云顶官方旗舰店', 2, 2, '预售Lily2018秋新款飘逸仙气系飘带领条纹荷叶袖衬衫', 'M', 100, 329.00, 'https://img.alicdn.com/imgextra/i4/1031105204/TB2ji_lxXOWBuNjy0FiXXXFxVXa-1031105204.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:09:58', '2018-08-07 14:10:02');
INSERT INTO `t_goods` VALUES (74, 1, '云顶官方旗舰店', 2, 2, 'ZARA 新款 TRF 女装 破洞装饰高腰牛仔短裤 ', '27', 100, 199.00, 'https://img.alicdn.com/imgextra/i4/2228361831/TB2CNZumbZnBKNjSZFKXXcGOVXa_!!2228361831-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:10:57', '2018-08-07 14:11:00');
INSERT INTO `t_goods` VALUES (75, 1, '云顶官方旗舰店', 2, 2, '女装 高腰棉质大摆裙 412872 优衣库UNIQLO', 'M', 100, 199.00, 'https://img.alicdn.com/imgextra/i2/196993935/TB1uaoVaNtnkeRjSZSgXXXAuXXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:12:22', '2018-08-07 14:12:27');
INSERT INTO `t_goods` VALUES (76, 1, '云顶官方旗舰店', 2, 2, '芬腾男士睡衣夏季纯棉短袖长裤格子印花开衫针织全棉套装家居服', 'L', 100, 139.00, 'https://img.alicdn.com/imgextra/i3/665542280/TB2EQW.ir4npuFjSZFmXXXl4FXa_!!665542280.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:14:00', '2018-08-07 14:14:03');
INSERT INTO `t_goods` VALUES (77, 1, '云顶官方旗舰店', 2, 2, '太平鸟男装 牛仔夹克男秋新品仙鹤刺绣外套浅色夹克衫日韩外套潮', 'L', 100, 539.00, 'https://img.alicdn.com/imgextra/i2/173275708/TB2Icd2zA9WBuNjSspeXXaz5VXa_!!173275708-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:16:25', '2018-08-07 14:16:31');
INSERT INTO `t_goods` VALUES (78, 1, '云顶官方旗舰店', 2, 2, '男装 便携式连帽外套 409313 优衣库UNIQLO', 'L', 100, 249.00, 'https://img.alicdn.com/imgextra/i2/196993935/TB2KrtlG29TBuNjy0FcXXbeiFXa_!!196993935-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:17:59', '2018-08-30 08:35:59');
INSERT INTO `t_goods` VALUES (79, 1, '云顶官方旗舰店', 2, 2, '马克华菲牛仔外套男士2018秋季新款字母印花翻领韩版夹克上衣', 'L', 100, 538.00, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/94399436/TB2haxOAyOYBuNjSsD4XXbSkFXa_!!94399436.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:19:00', '2018-08-07 14:19:05');
INSERT INTO `t_goods` VALUES (80, 1, '云顶官方旗舰店', 2, 2, '英爵伦 夏季休闲裤男 短裤男士简约青年直筒五分中裤纯色马裤5分', 'L', 100, 88.00, 'https://img.alicdn.com/imgextra/i2/1860270913/TB18jt5IkSWBuNjSszdXXbeSpXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-07 14:20:29', '2018-08-07 14:20:33');
INSERT INTO `t_goods` VALUES (81, 1, '云顶官方旗舰店', 1, 1, '美的电热水壶304不锈钢开水壶家用自动断电大容量电壶电热烧水壶', '通用', 100, 79.00, 'https://img.alicdn.com/imgextra/i2/2207593630/TB21j4pawMPMeJjy1XbXXcwxVXa_!!2207593630.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:25:39', '2018-08-08 08:25:43');
INSERT INTO `t_goods` VALUES (82, 1, '云顶官方旗舰店', 3, 3, '抖音热门同款黑科技创意厨房用品家居用品日常生活实用小百货东西', '通用', 100, 9.80, 'https://img.alicdn.com/imgextra/i4/TB1.ODURXXXXXaUXpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:27:04', '2018-08-08 08:27:10');
INSERT INTO `t_goods` VALUES (83, 1, '云顶官方旗舰店', 3, 3, '百易特肩无痕衣架成人防滑衣服架晾衣架家用衣撑塑料大衣架子挂钩', '通用', 100, 19.90, 'https://img.alicdn.com/imgextra/i1/869924570/TB1rSAQG49YBuNjy0FfXXXIsVXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:29:00', '2018-08-08 08:29:04');
INSERT INTO `t_goods` VALUES (84, 1, '云顶官方旗舰店', 1, 1, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', '通用', 100, 189.00, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:31:07', '2018-08-29 20:06:46');
INSERT INTO `t_goods` VALUES (85, 1, '云顶官方旗舰店', 1, 1, '科沃斯地宝DN55扫地机器人吸尘器智能家用超薄全自动洗擦地机拖地', '通用', 100, 2599.00, 'https://img.alicdn.com/imgextra/i4/420722466/O1CN011U5T993EqzcHWva_!!420722466.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:34:52', '2018-08-08 08:34:56');
INSERT INTO `t_goods` VALUES (86, 1, '云顶官方旗舰店', 1, 1, '美的电风扇遥控落地扇家用学生宿舍转页静音定时摇头立式台式电扇', '通用', 100, 219.00, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/1744401399/O1CN011MCmXloBM1jS7jr_!!1744401399.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:36:42', '2018-08-08 08:36:45');
INSERT INTO `t_goods` VALUES (87, 1, '云顶官方旗舰店', 1, 1, '小熊电炖锅陶瓷紫砂煮粥锅煲隔水炖盅全自动养生汤锅电砂锅大容量', '通用', 100, 339.00, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i1/667690233/O1CN011DakjF6uh4LIfAd_!!667690233.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:38:16', '2018-08-08 08:38:19');
INSERT INTO `t_goods` VALUES (88, 1, '云顶官方旗舰店', 1, 1, '小米净水器厨下式厨房自来水RO反渗透直饮纯水机家用净水机', '通用', 100, 1899.00, 'https://img.alicdn.com/imgextra/i3/1714128138/TB2jrjCl8mWBuNkSndVXXcsApXa_!!1714128138.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:39:36', '2018-08-08 08:39:43');
INSERT INTO `t_goods` VALUES (89, 1, '云顶官方旗舰店', 1, 1, '飞利浦电动牙刷充电器HX6100', 'HX6100', 100, 48.00, 'https://img.alicdn.com/imgextra/i4/720795394/TB1GwViFKuSBuNjSsziXXbq8pXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:41:42', '2018-08-08 08:41:48');
INSERT INTO `t_goods` VALUES (90, 1, '云顶官方旗舰店', 1, 1, '小熊煮蛋器自动断电家用迷你蒸蛋器双层炖蛋蒸蛋羹不锈钢定时神', '通用', 100, 98.00, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i3/1695868749/TB2kh2veMZC2uNjSZFnXXaxZpXa_!!1695868749.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-08 08:43:22', '2018-08-08 08:43:28');
INSERT INTO `t_goods` VALUES (91, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 易烊千玺同款小王子75周年限量音乐水晶球 预售', '限量预售', 100, 879.99, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2EYB.GY5YBuNjSspoXXbeNFXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 6, 1, 0, 0, '2018-08-15 08:43:00', '2018-08-24 20:12:02');
INSERT INTO `t_goods` VALUES (92, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 音乐水晶球永生花玫瑰 易烊千玺同款七夕礼物 ', '千玺同款', 100, 1314.00, 'https://img.alicdn.com/imgextra/i4/2397897588/TB2mXiOIFOWBuNjy0FiXXXFxVXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-15 08:43:00', '2018-08-15 08:43:00');
INSERT INTO `t_goods` VALUES (93, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 千玺同款音乐水晶球巨型永生花玫瑰 预售815 ', '预售', 100, 520.00, 'https://img.alicdn.com/imgextra/i1/2397897588/TB2zfJhXyGezuRkSO8gXXaanFXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-15 08:43:00', '2018-08-15 08:43:00');
INSERT INTO `t_goods` VALUES (94, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 小王子75周年限量枪炮玫瑰16支 七夕鲜花礼盒装 ', '礼盒装', 100, 1599.99, 'https://img.alicdn.com/imgextra/i1/2397897588/TB2NYW9IH1YBuNjSszhXXcUsFXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 4, 1, 0, 0, '2018-08-15 08:43:00', '2018-08-24 20:12:17');
INSERT INTO `t_goods` VALUES (95, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 千玺同款骨瓷马克杯 带茶漏个性水杯七夕礼物', '绿色新骨瓷马克杯', 100, 148.99, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2OfueIKuSBuNjSsplXXbe8pXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 4, 1, 0, 0, '2018-08-15 08:43:00', '2018-08-26 15:45:06');
INSERT INTO `t_goods` VALUES (96, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 千玺同款骨瓷马克杯 带茶漏个性水杯七夕礼物', '粉色新骨瓷马克杯', 100, 148.99, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2OfueIKuSBuNjSsplXXbe8pXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-15 08:43:00', '2018-08-15 08:43:00');
INSERT INTO `t_goods` VALUES (97, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 千玺同款告白兔永生花拎包 七夕礼物兔子花盒 赠易烊千玺兔子卡片 ', '礼盒装', 100, 679.99, 'https://img.alicdn.com/imgextra/i3/2397897588/TB29L6kbjrguuRjy0FeXXXcbFXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-15 08:43:00', '2018-08-15 08:43:00');
INSERT INTO `t_goods` VALUES (98, 1, '云顶官方旗舰店', 2, 2, 'THEBEAST/野兽派 小王子系列永生花礼盒 钻石项链礼盒七夕礼物', '礼盒装', 100, 2699.00, 'https://img.alicdn.com/imgextra/i1/2397897588/TB25oKpoIIrBKNjSZK9XXagoVXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 2, 1, 0, 0, '2018-08-15 08:43:00', '2018-08-24 20:11:58');
INSERT INTO `t_goods` VALUES (99, 1, '云顶官方旗舰店', 5, 5, 'adidas neo阿迪达斯中性套头衫 严肃进来严肃出去运动上衣DW7426', 'L', 100, 399.00, 'https://img30.360buyimg.com/popWaterMark/jfs/t23494/10/918127515/110590/be945681/5b496bbaN5b6f2408.jpg', NULL, 0, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-17 08:12:00');
INSERT INTO `t_goods` VALUES (100, 1, '云顶官方旗舰店', 5, 5, 'adidas neo阿迪达斯中性套头衫 严肃进来严肃出去运动上衣DW7426', 'XL', 100, 399.00, 'https://img30.360buyimg.com/popWaterMark/jfs/t23494/10/918127515/110590/be945681/5b496bbaN5b6f2408.jpg', NULL, 9, 2, 0, 0, '2018-08-17 08:11:00', '2018-08-24 21:52:10');
INSERT INTO `t_goods` VALUES (101, 1, '云顶官方旗舰店', 5, 5, 'adidas neo阿迪达斯中性套头衫 严肃进来严肃出去运动上衣DW7426', 'M', 100, 399.00, 'https://img30.360buyimg.com/popWaterMark/jfs/t23494/10/918127515/110590/be945681/5b496bbaN5b6f2408.jpg', NULL, 0, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-17 08:12:00');
INSERT INTO `t_goods` VALUES (102, 1, '云顶官方旗舰店', 3, 3, 'THE BEAST/野兽派 易烊千玺同款 花草香薰精油散香器 七夕礼物', '千玺同款', 100, 199.00, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2YEAFoZj_B1NjSZFHXXaDWpXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', NULL, 1, 0, 0, 0, '2018-08-17 15:35:57', '2018-08-24 10:50:17');
INSERT INTO `t_goods` VALUES (103, 1, '云顶官方旗舰店', 4, 4, 'HUAWEI nova 3 6GB+64GB 全网通版', '蓝楹紫', 100, 2799.00, 'https://res.vmallres.com/pimages//product/6901443250394/800_800_1531368379226mp.jpg', 'https://i.loli.net/2018/08/19/5b78c83749708.jpg', 9, 2, 0, 0, '2018-08-18 21:13:00', '2018-08-29 23:15:02');
INSERT INTO `t_goods` VALUES (104, 1, '云顶官方旗舰店', 4, 4, 'HUAWEI nova 3 6GB+64GB 全网通版', '亮黑色', 99, 2799.00, 'https://res.vmallres.com/pimages//product/6901443250363/800_800_1531368425114mp.jpg', 'https://i.loli.net/2018/08/19/5b78c83749708.jpg', 5, 1, 0, 0, '2018-08-18 21:13:00', '2018-08-30 09:14:57');
INSERT INTO `t_goods` VALUES (105, 1, '云顶官方旗舰店', 4, 4, 'HUAWEI nova 3 6GB+64GB 全网通版', '浅艾兰', 97, 2799.00, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 'https://i.loli.net/2018/08/19/5b78c83749708.jpg', 4, 1, 0, 0, '2018-08-18 21:13:00', '2018-08-30 09:16:20');
INSERT INTO `t_goods` VALUES (106, 1, '云顶官方旗舰店', 4, 4, 'HUAWEI nova 3 6GB+128GB 全网通版', '星耀版 樱草金', 99, 2999.00, 'https://res.vmallres.com/pimages//product/2601010048806/800_800_1531924326964mp.jpg', 'https://i.loli.net/2018/08/19/5b78c83749708.jpg', 3, 0, 0, 0, '2018-08-18 21:13:00', '2018-08-28 09:19:57');
INSERT INTO `t_goods` VALUES (107, 1, '云顶官方旗舰店', 4, 4, 'HUAWEI nova 3 6GB+128GB 全网通版', '相思红', 94, 2999.00, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 'https://i.loli.net/2018/08/19/5b78c83749708.jpg', 6, 1, 0, 0, '2018-08-18 21:13:00', '2018-08-29 21:05:06');
INSERT INTO `t_goods` VALUES (108, 1, '云顶官方旗舰店', 8, 8, 'LAMER The Moisturizing Cream 精华面霜', '30ml', 100, 1450.00, 'https://www.lamer.com.cn/media/export/images/products/lrg/20RP01_lg.jpg', '', 0, 0, 0, 0, '2018-08-18 21:13:00', '2018-08-26 09:00:19');
INSERT INTO `t_goods` VALUES (109, 1, '云顶官方旗舰店', 8, 8, 'LAMER The Moisturizing Cream 精华面霜', '15ml', 100, 760.00, 'https://www.lamer.com.cn/media/export/images/products/lrg/20RP01_lg.jpg', '', 0, 0, 0, 0, '2018-08-18 21:13:00', '2018-08-24 20:27:48');
INSERT INTO `t_goods` VALUES (110, 1, '云顶官方旗舰店', 8, 8, 'LAMER 海蓝之谜经典精华乳霜', '100ml', 100, 3900.00, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', '', 10, 1, 0, 0, '2018-08-18 21:13:00', '2018-08-24 20:27:48');
INSERT INTO `t_goods` VALUES (111, 1, '云顶官方旗舰店', 8, 8, 'LAMER 海蓝之谜经典精华乳霜', '30ml', 100, 1450.00, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', '', 12, 2, 0, 0, '2018-08-18 21:13:00', '2018-08-24 20:27:48');
INSERT INTO `t_goods` VALUES (112, 1, '云顶官方旗舰店', 8, 8, 'LAMER 海蓝之谜经典精华乳霜', '15ml', 100, 760.00, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', '', 0, 1, 0, 0, '2018-08-18 21:13:00', '2018-08-26 08:57:04');
INSERT INTO `t_goods` VALUES (113, 1, '云顶官方旗舰店', 7, 7, '《从你的全世界路过》 张嘉佳', '通用', 100, 18.00, 'http://img3m2.ddimg.cn/34/15/23353342-2_w_7.jpg', '', 0, 0, 0, 0, '2018-08-18 21:13:00', '2018-08-26 08:57:04');
INSERT INTO `t_goods` VALUES (114, 1, '云顶官方旗舰店', 2, 2, '【德国品牌】Max.Mece 夏季睡衣女短袖两件套冰丝绸缎睡衣休闲韩版家居服套装', 'M', 100, 199.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21010/85/1127055981/135892/ba9e6a70/5b1ff8b2N7ec26b0a.jpg', NULL, 0, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-26 08:57:04');
INSERT INTO `t_goods` VALUES (115, 1, '云顶官方旗舰店', 2, 2, '【德国品牌】Max.Mece 夏季睡衣女短袖两件套冰丝绸缎睡衣休闲韩版家居服套装配发带 紫色', 'S', 100, 199.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21010/85/1127055981/135892/ba9e6a70/5b1ff8b2N7ec26b0a.jpg', NULL, 0, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-26 08:57:03');
INSERT INTO `t_goods` VALUES (116, 1, '云顶官方旗舰店', 2, 2, 'CHOW TAI FOOK x WANGKAI 定制款爱之星辰18K金钻石项链 40cm', '40cm', 100, 5899.00, 'https://m.360buyimg.com/mobilecms/s750x750_jfs/t24457/221/2186534122/86286/664010f2/5b76e805N05f6ccc8.jpg!q80.dpg', NULL, 0, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-26 08:57:03');
INSERT INTO `t_goods` VALUES (117, 1, '云顶官方旗舰店', 4, 4, '卡西欧 LINE FRIENDS | CASIO 联名系列女表运动防水手表', '通用', 100, 1090.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t22390/180/2567352096/504791/ab981e60/5b5eb38aN5b637c5a.jpg', 'https://i.loli.net/2018/08/19/5b78d9b07562a.jpg', 2, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-28 16:17:55');
INSERT INTO `t_goods` VALUES (118, 1, '云顶官方旗舰店', 6, 6, '新品上市 雀巢（Nestle）咖啡1+2原味速溶微研磨咖啡饮品100条1500g ', '1500g', 99, 112.00, 'https://img10.360buyimg.com/n1/jfs/t22405/41/2461238360/386634/e1760c1/5b580f43Nab2f0073.jpg', NULL, 4, 2, 0, 0, '2018-08-17 08:11:00', '2018-08-29 06:59:21');
INSERT INTO `t_goods` VALUES (119, 1, '云顶官方旗舰店', 8, 8, '兰蔻（lancome）新款兰蔻菁纯柔润唇釉唇膏漆光唇釉 134#樱桃红', '134#', 100, 299.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23413/146/1301003408/33221/8b0cb110/5b598c8eN3da82167.jpg', NULL, 1, 1, 0, 0, '2018-08-17 08:11:00', '2018-08-26 15:06:03');
INSERT INTO `t_goods` VALUES (120, 1, '云顶官方旗舰店', 3, 3, '网易严选 喷塑便携运动水杯', '落沙黄', 100, 129.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25720/171/6744192/154348/59ec0c84/5b62ac9dNdac8178a.png', NULL, 3, 1, 0, 0, '2018-08-17 08:11:00', '2018-08-24 20:10:43');
INSERT INTO `t_goods` VALUES (121, 1, '云顶官方旗舰店', 3, 3, '网易严选 喷塑便携运动水杯', '山湖蓝', 100, 129.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t22936/91/1559206775/189834/400a7c10/5b62ac9dN2d1d7c58.png', NULL, 1, 1, 0, 0, '2018-08-17 08:11:00', '2018-08-24 20:09:58');
INSERT INTO `t_goods` VALUES (122, 1, '云顶官方旗舰店', 3, 3, '网易严选 喷塑便携运动水杯', '森林绿', 100, 129.00, 'https://img11.360buyimg.com/n1/jfs/t23977/199/1530977339/155077/2c342d51/5b62ac9dNea3b93b6.png', NULL, 3, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-24 19:52:21');
INSERT INTO `t_goods` VALUES (123, 1, '云顶官方旗舰店', 4, 4, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', '粉色', 94, 999.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 'https://i.loli.net/2018/08/19/5b78d99310f05.jpg', 7, 2, 0, 0, '2018-08-17 08:11:00', '2018-08-28 19:18:11');
INSERT INTO `t_goods` VALUES (124, 1, '云顶官方旗舰店', 4, 4, 'Apple iPhone 8 (A1863) 64GB 金色 移动联通电信4G手机 ', '64G', 100, 4399.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t9085/22/907696059/71305/93f88c62/59b85847N20776d8e.jpg', NULL, 0, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-17 08:12:00');
INSERT INTO `t_goods` VALUES (125, 1, '云顶官方旗舰店', 3, 3, '咪曦 茶具茶壶茶杯茶具套装哥窑功夫茶具开片陶瓷套装商务中秋礼品可定制', '套装', 99, 49.90, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23311/74/1121293374/194834/23760e99/5b51516dNf9c640c0.jpg', NULL, 1, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-28 19:26:05');
INSERT INTO `t_goods` VALUES (126, 1, '云顶官方旗舰店', 8, 8, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', '10片/盒', 97, 129.00, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', NULL, 3, 0, 0, 0, '2018-08-17 08:11:00', '2018-08-29 21:05:06');
INSERT INTO `t_goods` VALUES (127, 1, '云顶官方旗舰店', 8, 8, 'Avalon 阿瓦隆 薰衣草滋润柔顺洗发水 325ml', '325ml', 99, 9.90, 'https://img12.360buyimg.com/n5/s450x450_jfs/t17035/342/2627121013/297674/952d2fa7/5afe7044Na75c969b.jpg', NULL, 1, 0, 0, 0, '2018-08-17 10:47:00', '2018-08-28 09:17:59');
INSERT INTO `t_goods` VALUES (128, 1, '云顶官方旗舰店', 2, 2, 'The North Face/北面男卫衣外套连帽开衫纯色抓绒美国直邮tnf150', 'L', 99, 1764.00, 'https://img.alicdn.com/imgextra/i4/1785908005/TB1Soakc7fb_uJjSsrbXXb6bVXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 1, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-28 09:19:57');
INSERT INTO `t_goods` VALUES (129, 1, '云顶官方旗舰店', 2, 2, 'The North Face/北面男卫衣外套连帽开衫纯色抓绒美国直邮tnf150', 'XL', 100, 1764.00, 'https://img.alicdn.com/imgextra/i4/1785908005/TB1Soakc7fb_uJjSsrbXXb6bVXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (130, 1, '云顶官方旗舰店', 2, 2, 'The North Face/北面男卫衣外套连帽开衫纯色抓绒美国直邮tnf150', 'XXL', 100, 1764.00, 'https://img.alicdn.com/imgextra/i4/1785908005/TB1Soakc7fb_uJjSsrbXXb6bVXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (131, 1, '云顶官方旗舰店', 2, 2, 'Jimmy Choo/周仰杰 ROMY85黑色羊皮经典百搭尖头女士高跟鞋 ', '37', 100, 4990.00, 'https://img.alicdn.com/imgextra/i3/2670830951/TB2yVeGgRTH8KJjy0FiXXcRsXXa-2670830951.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (132, 1, '云顶官方旗舰店', 2, 2, 'Jimmy Choo/周仰杰 ROMY85黑色羊皮经典百搭尖头女士高跟鞋 ', '38', 100, 4990.00, 'https://img.alicdn.com/imgextra/i3/2670830951/TB2yVeGgRTH8KJjy0FiXXcRsXXa-2670830951.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (133, 1, '云顶官方旗舰店', 2, 2, 'Jimmy Choo/周仰杰 ROMY85黑色羊皮经典百搭尖头女士高跟鞋 ', '39', 100, 4990.00, 'https://img.alicdn.com/imgextra/i3/2670830951/TB2yVeGgRTH8KJjy0FiXXcRsXXa-2670830951.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (134, 1, '云顶官方旗舰店', 2, 2, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 'L', 94, 488.00, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', NULL, 6, 1, 0, 0, '2018-08-21 08:11:00', '2018-08-30 08:36:23');
INSERT INTO `t_goods` VALUES (135, 1, '云顶官方旗舰店', 2, 2, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 'XL', 100, 488.00, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', NULL, 1, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (136, 1, '云顶官方旗舰店', 2, 2, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 'XXL', 99, 488.00, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', NULL, 3, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-28 19:39:34');
INSERT INTO `t_goods` VALUES (137, 1, '云顶官方旗舰店', 4, 4, '富士（FUJIFILM）INSTAX 一次成像相机 MINI25相机 白色 ', '白色', 100, 699.00, 'https://img13.360buyimg.com/n1/s450x450_jfs/t2611/251/2644635268/106180/420c1e96/576cf3c0N925362b0.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-29 21:05:06');
INSERT INTO `t_goods` VALUES (138, 1, '云顶官方旗舰店', 8, 8, '太阳芦荟社太阳社玻尿酸保湿原液 10ml/支 高效保湿锁水（cosme直供 大赏推荐 日本原装进口）', '10ml', 100, 45.00, 'https://img13.360buyimg.com/n5/s450x450_jfs/t19282/114/1124543108/137470/9c873272/5abc62caN2a3721e3.jpg', NULL, 0, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-21 08:12:00');
INSERT INTO `t_goods` VALUES (139, 1, '云顶官方旗舰店', 4, 4, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', ' 4G 128GSSD+500G ', 94, 4599.00, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', NULL, 6, 0, 0, 0, '2018-08-21 08:11:00', '2018-08-29 19:30:45');
INSERT INTO `t_goods` VALUES (140, 1, '云顶官方旗舰店', 2, 2, 'HEFANG Jewelry/何方珠宝吃豆人耳线耳环 纯银女网红简约耳坠耳饰', '紫色', 100, 499.00, 'https://img.alicdn.com/imgextra/i3/2895013616/TB2ZgaLqeuSBuNjy1XcXXcYjFXa_!!2895013616.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 15:51:18', '2018-08-21 15:51:20');
INSERT INTO `t_goods` VALUES (141, 1, '云顶官方旗舰店', 2, 2, 'HEFANG Jewelry/何方珠宝吃豆人耳线耳环 纯银女网红简约耳坠耳饰', '橙色', 100, 499.00, 'https://img.alicdn.com/imgextra/i3/2895013616/TB2ZgaLqeuSBuNjy1XcXXcYjFXa_!!2895013616.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 15:51:26', '2018-08-21 15:51:28');
INSERT INTO `t_goods` VALUES (142, 1, '云顶官方旗舰店', 2, 2, 'HEFANG Jewelry/何方珠宝吃豆人耳线耳环 纯银女网红简约耳坠耳饰', '蓝色', 100, 499.00, 'https://img.alicdn.com/imgextra/TB1.A8nh_dYBeNkSmLyL6RfnVXa_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 15:52:59', '2018-08-21 15:53:01');
INSERT INTO `t_goods` VALUES (143, 1, '云顶官方旗舰店', 2, 2, 'HEFANG Jewelry/何方珠宝云朵锁骨链 纯银女ins短项链吊坠颈链', 'F', 100, 980.00, 'https://img.alicdn.com/imgextra/i2/2895013616/TB1rNl4dgoQMeJjy0FoXXcShVXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 15:11:00', '2018-08-21 15:12:00');
INSERT INTO `t_goods` VALUES (144, 1, '云顶官方旗舰店', 2, 2, 'HEFANG Jewelry/何方珠宝 彩虹项链 925纯银女简约锁骨链吊坠颈链', 'F', 100, 1580.00, 'https://img.alicdn.com/imgextra/i4/2895013616/TB1h8lxfDwKL1JjSZFgXXb6aVXa_!!0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-21 15:11:00', '2018-08-21 15:12:00');
INSERT INTO `t_goods` VALUES (145, 1, '云顶官方旗舰店', 8, 8, '沙宣洗发水套装修护水养500ml*2+护发素400ml送洗200ml补充装 易烊千玺同款', '保湿修护 蓬松百变', 100, 112.90, 'https://img14.360buyimg.com/n1/jfs/t23338/353/2188116172/340427/ed2e1c48/5b7668f7N8cb07711.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (146, 1, '云顶官方旗舰店', 6, 6, '越南进口 中原G7三合一速溶咖啡1600g (16gx100条）', '16gx100条', 100, 69.90, 'https://img14.360buyimg.com/n0/jfs/t2932/279/759495863/243388/5086177e/57677d33Ned8dbfd5.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (147, 1, '云顶官方旗舰店', 5, 5, 'KAPPA卡帕串标情侣男女运动卫衣休闲套头帽衫2018新品|K08X2MT68D 黑色-990', 'M', 100, 459.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25105/100/409800703/202123/3ad1398d/5b6ea233N0c8d4b7a.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (148, 1, '云顶官方旗舰店', 5, 5, 'KAPPA卡帕串标情侣男女运动卫衣休闲套头帽衫2018新品|K08X2MT68D 黑色-990', 'L', 100, 459.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25105/100/409800703/202123/3ad1398d/5b6ea233N0c8d4b7a.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (149, 1, '云顶官方旗舰店', 5, 5, 'KAPPA卡帕串标情侣男女运动卫衣休闲套头帽衫2018新品|K08X2MT68D 黑色-990', 'XL', 100, 459.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25105/100/409800703/202123/3ad1398d/5b6ea233N0c8d4b7a.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (150, 1, '云顶官方旗舰店', 2, 2, 'PORTS宝姿女装2018秋季新品简约印花女夹克外套SV9J011DPP011 BLACK PRT', '6', 100, 3899.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23152/104/808356106/142555/b36c3717/5b44747eN7772452f.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (151, 1, '云顶官方旗舰店', 2, 2, 'PORTS宝姿女装2018秋季新品简约印花女夹克外套SV9J011DPP011 BLACK PRT', '4', 100, 3899.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23152/104/808356106/142555/b36c3717/5b44747eN7772452f.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (152, 1, '云顶官方旗舰店', 6, 6, '百草味 心机月饼60gx2只 奶黄果仁中秋礼盒零食早餐美食特产小吃 果仁臻心味', '120g', 100, 9.90, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23866/89/1037498259/454580/6484c1b/5b4c5ba5Nc2ee412e.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (153, 1, '云顶官方旗舰店', 6, 6, '蜜桔 1kg装 新鲜水果', '1kg', 100, 16.90, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25636/65/27463528/420173/75cb9464/5b62dcdaN69649742.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (154, 1, '云顶官方旗舰店', 3, 3, '高露洁（Colgate）大胆爱 限量版 爱心牙膏套装 130g×2（红色爱心片片，表白礼物） ', '260g', 100, 59.90, 'https://img14.360buyimg.com/n1/s546x546_jfs/t18448/280/25433125/167381/80f40813/5a58539bNbb8553b2.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (155, 1, '云顶官方旗舰店', 4, 4, '锤子（smartisan） 坚果Pro2手机 纯白色 全网通 6+128G ', '6G+128G ', 100, 2499.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t19330/180/2329733856/135650/a98f36ed/5af039c3Nb72bf629.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (156, 1, '云顶官方旗舰店', 5, 5, '门罗Munro2.0电动车 哈雷复古电动摩托车 智能锂电电瓶车 成人两轮电动代步自行车', '皓月白', 100, 12399.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t19498/305/2667794416/459423/7d3b290/5b078463N94ad06de.png', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (157, 1, '云顶官方旗舰店', 3, 3, '磁悬浮地球仪 办公桌室摆件书房 教师节礼物 工艺品商务礼品 居家装饰创意桌面摆件', '4寸O形黑色自转', 100, 146.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t4705/146/4006743365/444928/d90a2dd2/5908140cNf5a4677e.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (158, 1, '云顶官方旗舰店', 3, 3, '小米（MI）螺丝刀米家wiha精修螺丝刀套装 小米米家螺丝刀套装 小米螺丝刀套装 ', '通用', 100, 95.90, 'https://img14.360buyimg.com/n1/s546x546_jfs/t18943/1/524858632/166760/da5adf10/5a961260N445b5f36.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (159, 1, '云顶官方旗舰店', 8, 8, 'Tom Ford 汤姆福特TF口红BOYS限量mini迷你黑管唇膏 迷你白管girls2g*50只', '限量礼盒套装预定', 100, 19999.00, 'https://img14.360buyimg.com/n5/s450x450_jfs/t3247/55/559516378/129360/1c3b9b7b/57ba9b42N5b9af3f1.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (160, 1, '云顶官方旗舰店', 8, 8, '蒂佳婷（Dr.Jart+）维生素活颜亮白霜50g(V7素颜霜 提亮均匀肤色 改善暗沉肌肤）', '50g', 99, 329.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t2806/215/1539410544/105289/51badc6d/5742827cN0fe0357b.jpg', 'https://i.loli.net/2018/08/27/5b83e118cf8ec.jpg', 1, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-29 18:49:42');
INSERT INTO `t_goods` VALUES (161, 1, '云顶官方旗舰店', 1, 1, '索尼（SONY）电视 KD-55X9000F 55英寸 4K超高清 智能液晶平板电视 黑色', '精锐光控Pro增强版', 100, 7299.00, 'https://i.loli.net/2018/08/26/5b8245efe68d4.png', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (162, 1, '云顶官方旗舰店', 2, 2, 'JDX | MOSCHINO京东跨界联名合作款套头长袖卫衣 A 1704 粉红', 'S', 100, 725.00, 'https://img14.360buyimg.com/n5/s450x450_jfs/t10966/43/2281585976/314538/7fe78b1d/59f30440Nb389dd31.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (163, 1, '云顶官方旗舰店', 2, 2, 'JDX | MOSCHINO京东跨界联名合作款套头长袖卫衣 A 1704 粉红', 'M', 100, 725.00, 'https://img14.360buyimg.com/n5/s450x450_jfs/t10966/43/2281585976/314538/7fe78b1d/59f30440Nb389dd31.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (164, 1, '云顶官方旗舰店', 2, 2, 'JDX | MOSCHINO京东跨界联名合作款套头长袖卫衣 A 1704 粉红', 'L', 100, 725.00, 'https://img14.360buyimg.com/n5/s450x450_jfs/t10966/43/2281585976/314538/7fe78b1d/59f30440Nb389dd31.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (165, 1, '云顶官方旗舰店', 3, 3, '富安娜家纺 提花四件套床上用品1.8米双人床上四件套欧式 秋日诗语', '1.8m(6英尺)', 100, 725.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t24967/92/583377150/233158/bf71ade0/5b74cddbN92fd43fa.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (166, 1, '云顶官方旗舰店', 5, 5, 'Adidas阿迪达斯NEO易烊千玺同款短袖男18秋新款运动休闲透气短袖T恤', 'M', 100, 199.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21001/75/1641723414/52550/c846014c/5b2fa190N7aef80e4.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (167, 1, '云顶官方旗舰店', 5, 5, 'Adidas阿迪达斯NEO易烊千玺同款短袖男18秋新款运动休闲透气短袖T恤', 'L', 100, 199.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21001/75/1641723414/52550/c846014c/5b2fa190N7aef80e4.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (168, 1, '云顶官方旗舰店', 5, 5, 'Adidas阿迪达斯NEO易烊千玺同款短袖男18秋新款运动休闲透气短袖T恤', 'XL', 100, 199.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21001/75/1641723414/52550/c846014c/5b2fa190N7aef80e4.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (169, 1, '云顶官方旗舰店', 6, 6, '良品铺子 每日坚果 混合干果核桃 中秋礼盒送礼零食大礼包30包', '750g', 100, 138.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t26101/365/315048065/411816/ca0373bb/5b6bb92eN39aa5164.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (170, 1, '云顶官方旗舰店', 7, 7, '派克（PARKER）钢笔/签字笔IM系列 丽雅黑色金夹+墨水礼盒套装商务办公礼品', '礼盒装', 100, 588.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t1405/116/1278899353/90084/a4dbf7fa/55ed5bc7Ndcf14901.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (171, 1, '云顶官方旗舰店', 4, 4, '索尼（SONY）智能蓝牙音箱 LF-S80D（白色） ', '白色', 100, 1899.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t10300/100/1315392557/167578/4eb216ca/59df2dd7N7c0882f4.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-29 21:05:06');
INSERT INTO `t_goods` VALUES (172, 1, '云顶官方旗舰店', 4, 4, '索尼（SONY）ILCE-7M2K 全画幅微单相机标准套机（约2430万有效像素 a7M2K）', '28-70mm镜头', 100, 8479.00, 'https://img14.360buyimg.com/n1/s546x546_jfs/t20149/237/18309204/153818/58a7d8de/5af566afN7e8bf13d.jpg', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (173, 1, '云顶官方旗舰店', 4, 4, '13 英寸 MacBook Pro 深空灰色 2.7GHz 四核第八代i7 处理器 触控栏和触控ID', '16GB 2133MHz LPDDR3 内存 + 2TB 固态硬盘', 100, 29148.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/m/bp/mbp13touch/space/mbp13touch-space-select-201807_GEO_CN?wid=904&hei=840&fmt=jpeg&qlt=95&.v=1530049934565', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (174, 1, '云顶官方旗舰店', 4, 4, '15 英寸 MacBook Pro 深空灰色 2.9GHz 六核第八代i9 处理器 触控栏和触控ID', '32GB 2400MHz LPDDR4 内存 + 4TB 固态硬盘', 100, 51352.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/m/bp/mbp15touch/space/mbp15touch-space-select-201807_GEO_CN?wid=904&hei=840&fmt=jpeg&qlt=95&.v=1530049952457', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (175, 1, '云顶官方旗舰店', 4, 4, '15 英寸 MacBook Pro 深空灰色 2.9GHz 六核第八代i9 处理器 触控栏和触控ID', '32GB 2400MHz LPDDR4 内存 + 2TB 固态硬盘', 100, 36292.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/m/bp/mbp15touch/space/mbp15touch-space-select-201807_GEO_CN?wid=904&hei=840&fmt=jpeg&qlt=95&.v=1530049952457', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (176, 1, '云顶官方旗舰店', 4, 4, '15 英寸 MacBook Pro 深空灰色 2.9GHz 六核第八代i9 处理器 触控栏和触控ID', '32GB 2400MHz LPDDR4 内存 + 1TB 固态硬盘', 100, 30268.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/m/bp/mbp15touch/space/mbp15touch-space-select-201807_GEO_CN?wid=904&hei=840&fmt=jpeg&qlt=95&.v=1530049952457', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (177, 1, '云顶官方旗舰店', 4, 4, '15 英寸 MacBook Pro 深空灰色 2.9GHz 六核第八代i9 处理器 触控栏和触控ID', '32GB 2400MHz LPDDR4 内存 + 512GB 固态硬盘', 100, 27256.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/m/bp/mbp15touch/space/mbp15touch-space-select-201807_GEO_CN?wid=904&hei=840&fmt=jpeg&qlt=95&.v=1530049952457', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (178, 1, '云顶官方旗舰店', 4, 4, 'iMac Pro 3.0GHz 十核 Intel Xeon W 处理器 4.5GHz', '128GB 内存+2TB 存储容量', 100, 73788.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ma/imacpro/27/imacpro-27-retina-config-hero?wid=412&hei=352&fmt=jpeg&qlt=95&.v=1512501389796', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (179, 1, '云顶官方旗舰店', 4, 4, 'iMac Pro 2.3GHz 十八核 Intel Xeon W 处理器 4.3GHz', '128GB 内存+2TB 存储容量', 100, 85836.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ma/imacpro/27/imacpro-27-retina-config-hero?wid=412&hei=352&fmt=jpeg&qlt=95&.v=1512501389796', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (180, 1, '云顶官方旗舰店', 4, 4, 'iMac Pro 2.3GHz 十八核 Intel Xeon W 处理器 4.3GHz', '128GB 内存+4TB 存储容量', 100, 100896.00, 'https://store.storeimages.cdn-apple.com/8755/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ma/imacpro/27/imacpro-27-retina-config-hero?wid=412&hei=352&fmt=jpeg&qlt=95&.v=1512501389796', NULL, 0, 0, 0, 0, '2018-08-26 15:11:00', '2018-08-26 15:12:00');
INSERT INTO `t_goods` VALUES (181, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE Chuck Taylor All StarxHello Kitty 162947C', '39', 100, 469.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2AvAfFpOWBuNjy0FiXXXFxVXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (182, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE Chuck Taylor All StarxHello Kitty 162947C', '40.5', 100, 469.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2AvAfFpOWBuNjy0FiXXXFxVXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (183, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE Chuck Taylor All StarxHello Kitty 162944C', '35', 100, 499.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2gB7CFuySBuNjy1zdXXXPxFXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (184, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE Chuck Taylor All StarxHello Kitty 162944C', '39.5', 100, 499.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2gB7CFuySBuNjy1zdXXXPxFXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (185, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE Chuck 70 x Hello Kitty 高帮帆布鞋 162936C ', '36', 100, 699.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2v46OuhuTBuNkHFNRXXc9qpXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 'https://img.alicdn.com/imgextra/i4/1046707508/TB212qiBntYBeNjy1XdXXXXyVXa_!!1046707508.jpg', 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (186, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE Chuck 70 x Hello Kitty 高帮帆布鞋 162936C ', '37', 100, 699.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2v46OuhuTBuNkHFNRXXc9qpXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 'https://img.alicdn.com/imgextra/i4/1046707508/TB212qiBntYBeNjy1XdXXXXyVXa_!!1046707508.jpg', 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (187, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮印花板鞋 162939C', '粉色 37', 100, 639.00, 'https://img.alicdn.com/imgextra/i1/1046707508/TB2fCvmFuGSBuNjSspbXXciipXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (188, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮印花板鞋 162939C', '粉色 38', 100, 639.00, 'https://img.alicdn.com/imgextra/i1/1046707508/TB2fCvmFuGSBuNjSspbXXciipXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (189, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮印花板鞋 162939C', '黑色 37', 100, 639.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2kMx5xhuTBuNkHFNRXXc9qpXa_!!1046707508.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (190, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮印花板鞋 162939C', '黑色 38', 100, 639.00, 'https://img.alicdn.com/imgextra/i2/1046707508/TB2kMx5xhuTBuNkHFNRXXc9qpXa_!!1046707508.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (191, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮板鞋 162937C ', '35', 99, 699.00, 'https://img.alicdn.com/imgextra/i4/1046707508/TB2RGSGCQSWBuNjSszdXXbeSpXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 'https://img.alicdn.com/imgextra/i2/1046707508/TB2fvyiBntYBeNjy1XdXXXXyVXa_!!1046707508.jpg', 1, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-28 19:26:05');
INSERT INTO `t_goods` VALUES (192, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮板鞋 162937C ', '36', 100, 699.00, 'https://img.alicdn.com/imgextra/i4/1046707508/TB2RGSGCQSWBuNjSszdXXbeSpXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 'https://img.alicdn.com/imgextra/i2/1046707508/TB2fvyiBntYBeNjy1XdXXXXyVXa_!!1046707508.jpg', 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (193, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 Converse x Hello Kitty T恤 女款 10008210', 'S', 100, 299.00, 'https://img.alicdn.com/imgextra/i3/1046707508/TB26OaeIruWBuNjSszgXXb8jVXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 'https://img.alicdn.com/imgextra/i3/1046707508/TB2_HrCGVmWBuNjSspdXXbugXXa_!!1046707508.jpg', 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (194, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 Converse x Hello Kitty T恤 女款 10008210', 'M', 100, 299.00, 'https://img.alicdn.com/imgextra/i3/1046707508/TB26OaeIruWBuNjSszgXXb8jVXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 'https://img.alicdn.com/imgextra/i3/1046707508/TB2_HrCGVmWBuNjSspdXXbugXXa_!!1046707508.jpg', 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (195, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 Converse x Hello Kitty 短T 女款 10008703', 'S', 100, 269.00, 'https://img.alicdn.com/imgextra/i3/1046707508/TB2EYGgIx1YBuNjy1zcXXbNcXXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (196, 1, '云顶官方旗舰店', 2, 2, 'CONVERSE匡威官方 Converse x Hello Kitty 短T 女款 10008703', 'M', 100, 269.00, 'https://img.alicdn.com/imgextra/i3/1046707508/TB2EYGgIx1YBuNjy1zcXXbNcXXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (197, 1, '云顶官方旗舰店', 2, 2, 'KAPPA卡帕 BANDA串标女款卫衣休闲开衫外套 2018新品|K0862WK68M', '红色 M', 100, 699.00, 'https://img.alicdn.com/imgextra/i2/285492195/TB2D5IWqZUrBKNjSZPxXXX00pXa_!!285492195-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (198, 1, '云顶官方旗舰店', 2, 2, 'KAPPA卡帕 BANDA串标女款卫衣休闲开衫外套 2018新品|K0862WK68M', '红色 L', 100, 699.00, 'https://img.alicdn.com/imgextra/i2/285492195/TB2D5IWqZUrBKNjSZPxXXX00pXa_!!285492195-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (199, 1, '云顶官方旗舰店', 2, 2, 'KAPPA卡帕 BANDA串标女款卫衣休闲开衫外套 2018新品|K0862WK68M', '粉色 M', 100, 699.00, 'https://img.alicdn.com/imgextra/i1/285492195/TB22Pn7riMnBKNjSZFzXXc_qVXa_!!285492195.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (200, 1, '云顶官方旗舰店', 2, 2, 'KAPPA卡帕 BANDA串标女款卫衣休闲开衫外套 2018新品|K0862WK68M', '粉色 L', 100, 699.00, 'https://img.alicdn.com/imgextra/i1/285492195/TB22Pn7riMnBKNjSZFzXXc_qVXa_!!285492195.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (201, 1, '云顶官方旗舰店', 2, 2, 'KAPPA卡帕 BANDA串标女款卫衣休闲开衫外套 2018新品|K0862WK68M', '蓝色 M', 100, 699.00, 'https://img.alicdn.com/imgextra/i2/285492195/TB2Sz_NrljTBKNjSZFNXXasFXXa_!!285492195.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (202, 1, '云顶官方旗舰店', 2, 2, 'KAPPA卡帕 BANDA串标女款卫衣休闲开衫外套 2018新品|K0862WK68M', '蓝色 L', 100, 699.00, 'https://img.alicdn.com/imgextra/i2/285492195/TB2Sz_NrljTBKNjSZFNXXasFXXa_!!285492195.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (203, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 桃粉色', '36', 100, 535.00, 'https://img.alicdn.com/imgextra/i8/TB1g3JVDFmWBuNjSspdoQPugXXa_040752.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (204, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 桃粉色', '37', 100, 535.00, 'https://img.alicdn.com/imgextra/i8/TB1g3JVDFmWBuNjSspdoQPugXXa_040752.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (205, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 深棕色', '36', 100, 535.00, 'https://img.alicdn.com/imgextra/i5/TB1oaJgJL1TBuNjy0FjwIyjyXXa_013233.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (206, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 深棕色', '37', 100, 535.00, 'https://img.alicdn.com/imgextra/i5/TB1oaJgJL1TBuNjy0FjwIyjyXXa_013233.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (207, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 蓝色', '36', 100, 535.00, 'https://img.alicdn.com/imgextra/i3/746866993/TB2ZnEzoCMmBKNjSZTEXXasKpXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (208, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 蓝色', '37', 100, 535.00, 'https://img.alicdn.com/imgextra/i3/746866993/TB2ZnEzoCMmBKNjSZTEXXasKpXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (209, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 粉色', '36', 100, 535.00, 'https://img.alicdn.com/imgextra/i5/TB1gnWVn5MnBKNjSZFo2s6OSFXa_034729.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (210, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38G1U64/U5Z/U5Y/U60 粉色', '37', 100, 535.00, 'https://img.alicdn.com/imgextra/i5/TB1gnWVn5MnBKNjSZFo2s6OSFXa_034729.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (211, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季桃粉色情侣款帆布鞋|VN0A38EMU5Y/VN0A38EMU64 ', '36', 100, 436.00, 'https://img.alicdn.com/imgextra/i4/746866993/TB28sPLIL1TBuNjy0FjXXajyXXa_!!746866993-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (212, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季桃粉色情侣款帆布鞋|VN0A38EMU5Y/VN0A38EMU64 ', '37', 100, 436.00, 'https://img.alicdn.com/imgextra/i4/746866993/TB28sPLIL1TBuNjy0FjXXajyXXa_!!746866993-0-item_pic.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (213, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季酒红色情侣款帆布鞋|VN0A38EMU5Y/VN0A38EMU64 ', '36', 100, 436.00, 'https://img.alicdn.com/imgextra/i2/746866993/TB2.WkBIGmWBuNjy1XaXXXCbXXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (214, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季酒红色情侣款帆布鞋|VN0A38EMU5Y/VN0A38EMU64 ', '37', 100, 436.00, 'https://img.alicdn.com/imgextra/i2/746866993/TB2.WkBIGmWBuNjy1XaXXXCbXXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (215, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38GEU64/U60/U5Y 酒红色', '36', 100, 565.00, 'https://img.alicdn.com/imgextra/i6/TB1G8nPDTlYBeNjSszc2snwhFXa_040207.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (216, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38GEU64/U60/U5Y 酒红色', '37', 100, 565.00, 'https://img.alicdn.com/imgextra/i6/TB1G8nPDTlYBeNjSszc2snwhFXa_040207.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (217, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38GEU64/U60/U5Y 蓝色', '36', 100, 565.00, 'https://img.alicdn.com/imgextra/i3/746866993/TB20kHDoHZnBKNjSZFhXXc.oXXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (218, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38GEU64/U60/U5Y 蓝色', '37', 100, 565.00, 'https://img.alicdn.com/imgextra/i3/746866993/TB20kHDoHZnBKNjSZFhXXc.oXXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (219, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38GEU64/U60/U5Y 粉色', '36', 100, 565.00, 'https://img.alicdn.com/imgextra/i2/746866993/TB2eGDloRsmBKNjSZFsXXaXSVXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (220, 1, '云顶官方旗舰店', 2, 2, 'Vans/范斯秋季情侣款板鞋休闲鞋帆布鞋|VN0A38GEU64/U60/U5Y 粉色', '37', 100, 565.00, 'https://img.alicdn.com/imgextra/i2/746866993/TB2eGDloRsmBKNjSZFsXXaXSVXa_!!746866993.jpg_430x430q90.jpg', NULL, 0, 0, 0, 0, '2018-08-27 15:11:00', '2018-08-27 15:12:00');
INSERT INTO `t_goods` VALUES (221, 1, '云顶官方旗舰店', 3, 3, '迈巴赫S级 2017款 S 400 4MATIC', '37', 100, 1190000.00, 'http://img5.taoche.cn/26/b0c95570-0218123vqu.jpg', NULL, 0, 0, 0, 0, '2018-08-29 15:11:00', '2018-08-29 15:12:00');
INSERT INTO `t_goods` VALUES (222, 1, '云顶官方旗舰店', 3, 3, 'Porsche 911 Targa 4S', '黑色', 100, 1638000.00, 'https://files1.porsche.com/filestore/image/multimedia/none/991-2nd-ta4s-modelimage-sideshot/model/ca3da2b5-d7ec-11e6-a122-0019999cd470;sG/porsche-model.png', NULL, 0, 0, 0, 0, '2018-08-29 15:11:00', '2018-08-29 15:12:00');
INSERT INTO `t_goods` VALUES (223, 1, '云顶官方旗舰店', 3, 3, 'Porsche 911 Targa 4', '蓝色', 100, 1448000.00, 'https://files1.porsche.com/filestore/image/multimedia/none/991-2nd-ta4-modelimage-sideshot/model/5e635b77-d7ec-11e6-a122-0019999cd470;sG/porsche-model.png', NULL, 0, 0, 0, 0, '2018-08-29 15:11:00', '2018-08-29 15:12:00');
INSERT INTO `t_goods` VALUES (224, 1, '云顶官方旗舰店', 3, 3, 'Porsche 911 GT2 RS', '银色', 99, 3898000.00, 'https://files1.porsche.com/filestore/image/multimedia/none/991-2nd-gt2-rs-modelimage-sideshot/model/ed0a4842-451d-11e7-bfe2-0019999cd470;sG/porsche-model.png', NULL, 1, 0, 0, 0, '2018-08-29 15:11:00', '2018-08-30 09:36:55');

-- ----------------------------
-- Table structure for t_identifying_code
-- ----------------------------
DROP TABLE IF EXISTS `t_identifying_code`;
CREATE TABLE `t_identifying_code`  (
  `code_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '验证码 id',
  `login_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验证码',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '验证码 状态 0-可用(默认)，-1-不可用',
  `created_at` datetime(0) NOT NULL COMMENT '验证码 创建时间',
  PRIMARY KEY (`code_id`) USING BTREE,
  INDEX `验证码-登录名外键`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_identifying_code
-- ----------------------------
INSERT INTO `t_identifying_code` VALUES (8, '15110487332', '266ED1', -1, '2018-08-08 08:52:13');
INSERT INTO `t_identifying_code` VALUES (9, '15110487332', '0D080E', -1, '2018-08-09 11:57:56');
INSERT INTO `t_identifying_code` VALUES (10, '18634312616', '4A8D61', -1, '2018-08-11 10:40:38');
INSERT INTO `t_identifying_code` VALUES (11, '17635357422', '06DC41', -1, '2018-08-11 10:44:44');
INSERT INTO `t_identifying_code` VALUES (12, '17635357422', 'D630EA', -1, '2018-08-11 10:46:10');
INSERT INTO `t_identifying_code` VALUES (13, '17635357422', 'DC2DF0', -1, '2018-08-11 10:48:40');
INSERT INTO `t_identifying_code` VALUES (14, '17635357422', 'B5661C', -1, '2018-08-11 10:49:59');
INSERT INTO `t_identifying_code` VALUES (15, '18634312616', '999999', -1, '2018-08-12 17:14:54');
INSERT INTO `t_identifying_code` VALUES (16, '13333333333', '652FD6', -1, '2018-08-11 14:39:16');
INSERT INTO `t_identifying_code` VALUES (17, '13333333333', '230145', -1, '2018-08-11 14:50:47');
INSERT INTO `t_identifying_code` VALUES (18, '17635357422', '65A4C5', -1, '2018-08-11 15:20:13');
INSERT INTO `t_identifying_code` VALUES (19, '17635357422', 'CEA850', -1, '2018-08-11 15:23:07');
INSERT INTO `t_identifying_code` VALUES (20, '15534445737', '855742', -1, '2018-08-11 15:57:12');
INSERT INTO `t_identifying_code` VALUES (21, '15534445737', '587033', -1, '2018-08-11 16:03:18');
INSERT INTO `t_identifying_code` VALUES (22, '17635357422', '164347', -1, '2018-08-11 17:07:59');
INSERT INTO `t_identifying_code` VALUES (23, '15534445737', '430927', -1, '2018-08-11 17:08:58');
INSERT INTO `t_identifying_code` VALUES (24, '17635357422', '289799', -1, '2018-08-11 19:12:18');
INSERT INTO `t_identifying_code` VALUES (25, '15534445737', '198998', -1, '2018-08-11 19:18:58');
INSERT INTO `t_identifying_code` VALUES (26, '15534445737', '177207', -1, '2018-08-11 19:35:58');
INSERT INTO `t_identifying_code` VALUES (27, '15534445737', '205079', -1, '2018-08-11 19:39:37');
INSERT INTO `t_identifying_code` VALUES (28, '15534445737', '125249', -1, '2018-08-11 19:50:30');
INSERT INTO `t_identifying_code` VALUES (29, '17635357422', '111111', -1, '2018-08-15 11:49:16');
INSERT INTO `t_identifying_code` VALUES (30, '15534445737', '144346', -1, '2018-08-15 11:49:04');
INSERT INTO `t_identifying_code` VALUES (31, '15534445737', '446519', -1, '2018-08-15 11:50:38');
INSERT INTO `t_identifying_code` VALUES (32, '15534445737', '146804', -1, '2018-08-15 12:01:11');
INSERT INTO `t_identifying_code` VALUES (33, '15534445737', '170018', -1, '2018-08-16 17:39:50');
INSERT INTO `t_identifying_code` VALUES (34, '15534445737', '725574', -1, '2018-08-16 17:48:32');
INSERT INTO `t_identifying_code` VALUES (35, '15534445737', '348042', -1, '2018-08-16 18:02:54');
INSERT INTO `t_identifying_code` VALUES (36, '15534445737', '535916', -1, '2018-08-16 18:04:39');
INSERT INTO `t_identifying_code` VALUES (37, '15534445737', '400769', -1, '2018-08-16 18:51:57');
INSERT INTO `t_identifying_code` VALUES (38, '15534445737', '147194', -1, '2018-08-17 07:50:21');
INSERT INTO `t_identifying_code` VALUES (39, '15534445737', '779439', -1, '2018-08-17 08:04:41');
INSERT INTO `t_identifying_code` VALUES (40, '15534445737', '111111', -1, '2018-08-18 08:43:32');
INSERT INTO `t_identifying_code` VALUES (41, '15534445737', '991277', -1, '2018-08-27 14:59:23');
INSERT INTO `t_identifying_code` VALUES (42, '13834028005', '734101', -1, '2018-08-27 19:46:04');
INSERT INTO `t_identifying_code` VALUES (43, '13834028005', '503027', -1, '2018-08-27 19:47:22');
INSERT INTO `t_identifying_code` VALUES (44, '15537775737', '567982', 0, '2018-08-27 19:54:42');
INSERT INTO `t_identifying_code` VALUES (45, '15534445737', '933752', -1, '2018-08-27 19:56:10');

-- ----------------------------
-- Table structure for t_login
-- ----------------------------
DROP TABLE IF EXISTS `t_login`;
CREATE TABLE `t_login`  (
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '登陆 对应用户id',
  `login_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆 用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆 密码',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '登陆 状态：-1-不可用，0-可用(默认)',
  `created_at` datetime(0) NOT NULL COMMENT '登陆 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '登陆 更新时间',
  INDEX `登陆-用户id 外键`(`user_id`) USING BTREE,
  INDEX `login_name`(`login_name`) USING BTREE,
  CONSTRAINT `登陆-用户id 外键` FOREIGN KEY (`user_id`) REFERENCES `t_user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_login
-- ----------------------------
INSERT INTO `t_login` VALUES (1, '11111111111', '123456', 0, '2018-08-05 19:35:41', '2018-08-05 19:35:43');
INSERT INTO `t_login` VALUES (2, '22222222222', '123456', 0, '2018-08-06 15:25:17', '2018-08-06 15:25:19');
INSERT INTO `t_login` VALUES (19, '15110487332', '123456', 0, '2018-08-09 11:58:34', '2018-08-11 14:20:40');
INSERT INTO `t_login` VALUES (24, '17635357422', '123456', 0, '2018-08-11 15:45:33', '2018-08-15 11:49:21');
INSERT INTO `t_login` VALUES (37, '13834028005', '123456', 0, '2018-08-27 19:47:58', '2018-08-27 19:47:58');
INSERT INTO `t_login` VALUES (39, '13152876992', '123456', 0, '2018-08-28 09:18:36', '2018-08-28 09:18:36');
INSERT INTO `t_login` VALUES (41, '15534444911', '123456', 0, '2018-08-28 09:30:53', '2018-08-28 09:30:53');
INSERT INTO `t_login` VALUES (44, '15835966774', '123456', 0, '2018-08-29 19:48:16', '2018-08-29 19:48:16');
INSERT INTO `t_login` VALUES (48, '15534445737', '123456', 0, '2018-08-29 23:10:25', '2018-08-29 23:10:25');
INSERT INTO `t_login` VALUES (49, '18634312616', '990616', 0, '2018-08-30 09:08:17', '2018-08-30 09:08:17');
INSERT INTO `t_login` VALUES (50, '17603444963', 'asd123...', 0, '2018-08-30 16:46:52', '2018-08-30 16:46:52');

-- ----------------------------
-- Table structure for t_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_order_goods`;
CREATE TABLE `t_order_goods`  (
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `goods_id` int(10) UNSIGNED NOT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `goods_num` int(10) UNSIGNED NOT NULL COMMENT '商品数量',
  `goods_pic` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品配图',
  `unit_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '商品成交时单价',
  `total_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '商品成交时总价',
  `state` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态：0-待评价(默认)，1-已评价',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  INDEX `订单id外键`(`order_id`) USING BTREE,
  INDEX `商品id外键`(`goods_id`) USING BTREE,
  CONSTRAINT `商品id外键` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_goods
-- ----------------------------
INSERT INTO `t_order_goods` VALUES ('ef605dc481', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 1, '2018-08-20 10:49:41', '2018-08-24 10:57:33');
INSERT INTO `t_order_goods` VALUES ('ef605dc481', 2, '凌美LAMY钢笔签字笔水笔Safari狩猎者男女商务办公礼品笔', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t5137/19/1668282377/157656/53fe3116/5912f208N8fa82c1b.jpg', 209.00, 209.00, 1, '2018-08-20 10:49:42', '2018-08-24 10:57:45');
INSERT INTO `t_order_goods` VALUES ('89ed22dc29', 100, 'adidas neo阿迪达斯中性套头衫 严肃进来严肃出去运动上衣DW7426', 3, 'https://img30.360buyimg.com/popWaterMark/jfs/t23494/10/918127515/110590/be945681/5b496bbaN5b6f2408.jpg', 399.00, 1197.00, 1, '2018-08-21 10:50:17', '2018-08-24 21:52:10');
INSERT INTO `t_order_goods` VALUES ('89ed22dc29', 102, 'THE BEAST/野兽派 易烊千玺同款 花草香薰精油散香器 七夕礼物', 1, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2YEAFoZj_B1NjSZFHXXaDWpXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', 199.00, 199.00, 0, '2018-08-21 10:50:17', '2018-08-24 10:50:17');
INSERT INTO `t_order_goods` VALUES ('107607aa86', 104, 'HUAWEI nova 3 6GB+64GB 全网通版', 3, 'https://res.vmallres.com/pimages//product/6901443250363/800_800_1531368425114mp.jpg', 2799.00, 8397.00, 0, '2018-08-22 10:50:55', '2018-08-24 10:50:55');
INSERT INTO `t_order_goods` VALUES ('107607aa86', 106, 'HUAWEI nova 3 6GB+128GB 全网通版', 2, 'https://res.vmallres.com/pimages//product/2601010048806/800_800_1531924326964mp.jpg', 2999.00, 5998.00, 0, '2018-08-22 10:50:55', '2018-08-24 10:50:55');
INSERT INTO `t_order_goods` VALUES ('16a2dc4b22', 91, 'THE BEAST/野兽派 易烊千玺同款小王子75周年限量音乐水晶球 预售', 3, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2EYB.GY5YBuNjSspoXXbeNFXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', 879.99, 2639.97, 1, '2018-08-23 10:52:13', '2018-08-24 20:12:02');
INSERT INTO `t_order_goods` VALUES ('16a2dc4b22', 94, 'THE BEAST/野兽派 小王子75周年限量枪炮玫瑰16支 七夕鲜花礼盒装 ', 2, 'https://img.alicdn.com/imgextra/i1/2397897588/TB2NYW9IH1YBuNjSszhXXcUsFXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', 1599.99, 3199.98, 1, '2018-08-23 10:52:13', '2018-08-24 20:12:17');
INSERT INTO `t_order_goods` VALUES ('16a2dc4b22', 95, 'THE BEAST/野兽派 千玺同款骨瓷马克杯 带茶漏个性水杯七夕礼物', 4, 'https://img.alicdn.com/imgextra/i3/2397897588/TB2OfueIKuSBuNjSsplXXbe8pXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', 148.99, 595.96, 1, '2018-08-23 10:52:13', '2018-08-26 15:45:06');
INSERT INTO `t_order_goods` VALUES ('16a2dc4b22', 98, 'THEBEAST/野兽派 小王子系列永生花礼盒 钻石项链礼盒七夕礼物', 1, 'https://img.alicdn.com/imgextra/i1/2397897588/TB25oKpoIIrBKNjSZK9XXagoVXa_!!2397897588-0-item_pic.jpg_430x430q90.jpg', 2699.00, 2699.00, 1, '2018-08-23 10:52:13', '2018-08-24 20:11:58');
INSERT INTO `t_order_goods` VALUES ('12de15c3b9', 111, 'LAMER 海蓝之谜经典精华乳霜', 3, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', 1450.00, 4350.00, 0, '2018-08-24 10:53:22', '2018-08-24 10:53:22');
INSERT INTO `t_order_goods` VALUES ('12de15c3b9', 112, 'LAMER 海蓝之谜经典精华乳霜', 2, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', 760.00, 1520.00, 1, '2018-08-24 10:53:22', '2018-08-24 21:50:01');
INSERT INTO `t_order_goods` VALUES ('12de15c3b9', 118, '新品上市 雀巢（Nestle）咖啡1+2原味速溶微研磨咖啡饮品100条1500g ', 1, 'https://img10.360buyimg.com/n1/jfs/t22405/41/2461238360/386634/e1760c1/5b580f43Nab2f0073.jpg', 112.00, 112.00, 1, '2018-08-24 10:53:22', '2018-08-29 06:59:20');
INSERT INTO `t_order_goods` VALUES ('f7f638e3aa', 103, 'HUAWEI nova 3 6GB+64GB 全网通版', 3, 'https://res.vmallres.com/pimages//product/6901443250394/800_800_1531368379226mp.jpg', 2799.00, 8397.00, 1, '2018-08-24 10:54:21', '2018-08-24 10:58:38');
INSERT INTO `t_order_goods` VALUES ('f7f638e3aa', 104, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250363/800_800_1531368425114mp.jpg', 2799.00, 2799.00, 1, '2018-08-24 10:54:21', '2018-08-24 21:46:10');
INSERT INTO `t_order_goods` VALUES ('f7f638e3aa', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 1, '2018-08-24 10:54:21', '2018-08-24 21:46:33');
INSERT INTO `t_order_goods` VALUES ('20180824075014280', 106, 'HUAWEI nova 3 6GB+128GB 全网通版', 3, 'https://res.vmallres.com/pimages//product/2601010048806/800_800_1531924326964mp.jpg', 2999.00, 8997.00, 0, '2018-08-24 19:50:14', '2018-08-24 19:50:14');
INSERT INTO `t_order_goods` VALUES ('20180824075014280', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 2999.00, 0, '2018-08-24 19:50:15', '2018-08-24 19:50:15');
INSERT INTO `t_order_goods` VALUES ('20180824075014280', 108, 'LAMER The Moisturizing Cream 精华面霜', 1, 'https://www.lamer.com.cn/media/export/images/products/lrg/20RP01_lg.jpg', 1450.00, 1450.00, 0, '2018-08-24 19:50:15', '2018-08-24 19:50:15');
INSERT INTO `t_order_goods` VALUES ('20180824075036245', 109, 'LAMER The Moisturizing Cream 精华面霜', 3, 'https://www.lamer.com.cn/media/export/images/products/lrg/20RP01_lg.jpg', 760.00, 2280.00, 0, '2018-08-24 19:50:37', '2018-08-24 19:50:37');
INSERT INTO `t_order_goods` VALUES ('20180824075036245', 110, 'LAMER 海蓝之谜经典精华乳霜', 1, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', 3900.00, 3900.00, 0, '2018-08-24 19:50:37', '2018-08-24 19:50:37');
INSERT INTO `t_order_goods` VALUES ('20180824075036245', 111, 'LAMER 海蓝之谜经典精华乳霜', 1, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', 1450.00, 1450.00, 0, '2018-08-24 19:50:37', '2018-08-24 19:50:37');
INSERT INTO `t_order_goods` VALUES ('2018082407505826', 112, 'LAMER 海蓝之谜经典精华乳霜', 3, 'https://www.lamer.com.cn/media/export/images/products/lrg/2WHL01_lg.jpg', 760.00, 2280.00, 0, '2018-08-24 19:50:58', '2018-08-24 19:50:58');
INSERT INTO `t_order_goods` VALUES ('2018082407505826', 113, '《从你的全世界路过》 张嘉佳', 1, 'http://img3m2.ddimg.cn/34/15/23353342-2_w_7.jpg', 18.00, 18.00, 0, '2018-08-24 19:50:59', '2018-08-24 19:50:59');
INSERT INTO `t_order_goods` VALUES ('2018082407505826', 114, '【德国品牌】Max.Mece 夏季睡衣女短袖两件套冰丝绸缎睡衣休闲韩版家居服套装', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21010/85/1127055981/135892/ba9e6a70/5b1ff8b2N7ec26b0a.jpg', 199.00, 199.00, 0, '2018-08-24 19:50:59', '2018-08-24 19:50:59');
INSERT INTO `t_order_goods` VALUES ('20180824075124296', 115, '【德国品牌】Max.Mece 夏季睡衣女短袖两件套冰丝绸缎睡衣休闲韩版家居服套装配发带 紫色', 3, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21010/85/1127055981/135892/ba9e6a70/5b1ff8b2N7ec26b0a.jpg', 199.00, 597.00, 0, '2018-08-24 19:51:25', '2018-08-24 19:51:25');
INSERT INTO `t_order_goods` VALUES ('20180824075124296', 116, 'CHOW TAI FOOK x WANGKAI 定制款爱之星辰18K金钻石项链 40cm', 1, 'https://m.360buyimg.com/mobilecms/s750x750_jfs/t24457/221/2186534122/86286/664010f2/5b76e805N05f6ccc8.jpg!q80.dpg', 5899.00, 5899.00, 0, '2018-08-24 19:51:25', '2018-08-24 19:51:25');
INSERT INTO `t_order_goods` VALUES ('20180824075124296', 117, '卡西欧 LINE FRIENDS | CASIO 联名系列女表运动防水手表', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t22390/180/2567352096/504791/ab981e60/5b5eb38aN5b637c5a.jpg', 1090.00, 1090.00, 0, '2018-08-24 19:51:25', '2018-08-24 19:51:25');
INSERT INTO `t_order_goods` VALUES ('20180824075145285', 118, '新品上市 雀巢（Nestle）咖啡1+2原味速溶微研磨咖啡饮品100条1500g ', 3, 'https://img10.360buyimg.com/n1/jfs/t22405/41/2461238360/386634/e1760c1/5b580f43Nab2f0073.jpg', 112.00, 336.00, 1, '2018-08-24 19:51:46', '2018-08-24 21:51:07');
INSERT INTO `t_order_goods` VALUES ('20180824075145285', 119, '兰蔻（lancome）新款兰蔻菁纯柔润唇釉唇膏漆光唇釉 134#樱桃红', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23413/146/1301003408/33221/8b0cb110/5b598c8eN3da82167.jpg', 299.00, 299.00, 1, '2018-08-24 19:51:46', '2018-08-26 15:06:03');
INSERT INTO `t_order_goods` VALUES ('20180824075205210', 120, '网易严选 喷塑便携运动水杯', 3, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25720/171/6744192/154348/59ec0c84/5b62ac9dNdac8178a.png', 129.00, 387.00, 1, '2018-08-24 19:52:06', '2018-08-24 20:10:43');
INSERT INTO `t_order_goods` VALUES ('20180824075205210', 121, '网易严选 喷塑便携运动水杯', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t22936/91/1559206775/189834/400a7c10/5b62ac9dN2d1d7c58.png', 129.00, 129.00, 1, '2018-08-24 19:52:06', '2018-08-24 20:09:58');
INSERT INTO `t_order_goods` VALUES ('20180824075220254', 122, '网易严选 喷塑便携运动水杯', 3, 'https://img11.360buyimg.com/n1/jfs/t23977/199/1530977339/155077/2c342d51/5b62ac9dNea3b93b6.png', 129.00, 387.00, 0, '2018-08-24 19:52:20', '2018-08-24 19:52:20');
INSERT INTO `t_order_goods` VALUES ('20180824075220254', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 1, '2018-08-24 19:52:21', '2018-08-24 19:55:58');
INSERT INTO `t_order_goods` VALUES ('20180825053057272', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-25 17:30:57', '2018-08-25 17:30:57');
INSERT INTO `t_order_goods` VALUES ('20180825053057272', 2, '凌美LAMY钢笔签字笔水笔Safari狩猎者男女商务办公礼品笔', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t5137/19/1668282377/157656/53fe3116/5912f208N8fa82c1b.jpg', 209.00, 209.00, 0, '2018-08-25 17:30:58', '2018-08-25 17:30:58');
INSERT INTO `t_order_goods` VALUES ('20180825053851286', 28, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 1, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', 400.00, 400.00, 0, '2018-08-25 17:38:52', '2018-08-25 17:38:52');
INSERT INTO `t_order_goods` VALUES ('2018082505400229', 28, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 4, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', 400.00, 1600.00, 0, '2018-08-25 17:40:03', '2018-08-25 17:40:03');
INSERT INTO `t_order_goods` VALUES ('20180826075159228', 65, '水星家纺四件套全棉纯棉特价床单四件套夏季1.8米床床上四件套', 1, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/92042735/TB24b2el_CWBKNjSZFtXXaC3FXa_!!92042735.jpg_430x430q90.jpg', 299.00, 299.00, 0, '2018-08-26 07:52:00', '2018-08-26 07:52:00');
INSERT INTO `t_order_goods` VALUES ('20180826075159228', 25, '欧舒丹（L\'OCCITANE） 润肤护手霜 套装 玫瑰皇后+乳木果+樱花30ml', 2, 'https://img10.360buyimg.com/n5/s450x450_jfs/t16441/45/2746055864/171596/f5305848/5ab9fe64Na417d7c2.jpg', 168.00, 336.00, 0, '2018-08-26 07:52:00', '2018-08-26 07:52:00');
INSERT INTO `t_order_goods` VALUES ('20180826090339282', 18, '平凡的世界：全三册', 1, 'http://img12.360buyimg.com/n1/jfs/t5587/350/4618116432/255954/cd4c1951/59521501N4c19726f.jpg', 81.00, 81.00, 0, '2018-08-26 09:03:39', '2018-08-26 09:03:39');
INSERT INTO `t_order_goods` VALUES ('20180826090339282', 28, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 1, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', 400.00, 400.00, 0, '2018-08-26 09:03:39', '2018-08-26 09:03:39');
INSERT INTO `t_order_goods` VALUES ('20180826090342249', 18, '平凡的世界：全三册', 1, 'http://img12.360buyimg.com/n1/jfs/t5587/350/4618116432/255954/cd4c1951/59521501N4c19726f.jpg', 81.00, 81.00, 0, '2018-08-26 09:03:42', '2018-08-26 09:03:42');
INSERT INTO `t_order_goods` VALUES ('20180826090342249', 28, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 1, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', 400.00, 400.00, 0, '2018-08-26 09:03:42', '2018-08-26 09:03:42');
INSERT INTO `t_order_goods` VALUES ('20180826090342249', 55, 'TP-LINK 路由器 无线家用穿墙高速wifi 穿墙王 ', 1, 'https://img.alicdn.com/imgextra/i1/1040450854/TB2efillbYI8KJjy0FaXXbAiVXa_!!1040450854.jpg_430x430q90.jpg', 119.90, 119.90, 0, '2018-08-26 09:03:42', '2018-08-26 09:03:42');
INSERT INTO `t_order_goods` VALUES ('20180826090347281', 18, '平凡的世界：全三册', 1, 'http://img12.360buyimg.com/n1/jfs/t5587/350/4618116432/255954/cd4c1951/59521501N4c19726f.jpg', 81.00, 81.00, 0, '2018-08-26 09:03:47', '2018-08-26 09:03:47');
INSERT INTO `t_order_goods` VALUES ('20180826090347281', 65, '水星家纺四件套全棉纯棉特价床单四件套夏季1.8米床床上四件套', 1, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/92042735/TB24b2el_CWBKNjSZFtXXaC3FXa_!!92042735.jpg_430x430q90.jpg', 299.00, 299.00, 0, '2018-08-26 09:03:47', '2018-08-26 09:03:47');
INSERT INTO `t_order_goods` VALUES ('20180826090347281', 28, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 1, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', 400.00, 400.00, 0, '2018-08-26 09:03:47', '2018-08-26 09:03:47');
INSERT INTO `t_order_goods` VALUES ('20180826090347281', 55, 'TP-LINK 路由器 无线家用穿墙高速wifi 穿墙王 ', 1, 'https://img.alicdn.com/imgextra/i1/1040450854/TB2efillbYI8KJjy0FaXXbAiVXa_!!1040450854.jpg_430x430q90.jpg', 119.90, 119.90, 0, '2018-08-26 09:03:47', '2018-08-26 09:03:47');
INSERT INTO `t_order_goods` VALUES ('20180826090353225', 28, '玫珂菲（MAKE UP FOR EVER） 双用水粉霜 防水 粉底液 全新 R250', 1, 'https://img11.360buyimg.com/n1/jfs/t18787/227/2717234963/38664/3778eba9/5b06c8ceNc54c1d04.jpg', 400.00, 400.00, 0, '2018-08-26 09:03:54', '2018-08-26 09:03:54');
INSERT INTO `t_order_goods` VALUES ('20180826090353225', 55, 'TP-LINK 路由器 无线家用穿墙高速wifi 穿墙王 ', 1, 'https://img.alicdn.com/imgextra/i1/1040450854/TB2efillbYI8KJjy0FaXXbAiVXa_!!1040450854.jpg_430x430q90.jpg', 119.90, 119.90, 0, '2018-08-26 09:03:54', '2018-08-26 09:03:54');
INSERT INTO `t_order_goods` VALUES ('20180826090401277', 65, '水星家纺四件套全棉纯棉特价床单四件套夏季1.8米床床上四件套', 1, 'https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/92042735/TB24b2el_CWBKNjSZFtXXaC3FXa_!!92042735.jpg_430x430q90.jpg', 299.00, 299.00, 0, '2018-08-26 09:04:02', '2018-08-26 09:04:02');
INSERT INTO `t_order_goods` VALUES ('20180826090401277', 25, '欧舒丹（L\'OCCITANE） 润肤护手霜 套装 玫瑰皇后+乳木果+樱花30ml', 1, 'https://img10.360buyimg.com/n5/s450x450_jfs/t16441/45/2746055864/171596/f5305848/5ab9fe64Na417d7c2.jpg', 168.00, 168.00, 0, '2018-08-26 09:04:02', '2018-08-26 09:04:02');
INSERT INTO `t_order_goods` VALUES ('2018082605085023', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 17:08:50', '2018-08-26 17:08:50');
INSERT INTO `t_order_goods` VALUES ('20180826051346249', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 17:13:46', '2018-08-26 17:13:46');
INSERT INTO `t_order_goods` VALUES ('20180826053011216', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 17:30:11', '2018-08-26 17:30:11');
INSERT INTO `t_order_goods` VALUES ('20180826053234247', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 17:32:35', '2018-08-26 17:32:35');
INSERT INTO `t_order_goods` VALUES ('20180826054950292', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 17:49:51', '2018-08-26 17:49:51');
INSERT INTO `t_order_goods` VALUES ('20180826055006236', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 17:50:07', '2018-08-26 17:50:07');
INSERT INTO `t_order_goods` VALUES ('20180826083208256', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 20:32:09', '2018-08-26 20:32:09');
INSERT INTO `t_order_goods` VALUES ('20180826083837282', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 20:38:37', '2018-08-26 20:38:37');
INSERT INTO `t_order_goods` VALUES ('20180826085341226', 21, '日版 SKII 精华露 神仙水 补水保湿修护肌肤230ml', 10, 'https://img13.360buyimg.com/n5/s450x450_jfs/t20035/76/1792779616/49899/a97c337d/5b3d7f99N9c162d14.jpg', 1199.00, 11990.00, 0, '2018-08-26 20:53:42', '2018-08-26 20:53:42');
INSERT INTO `t_order_goods` VALUES ('20180827103006225', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006225', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006284', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006284', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006291', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006291', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006216', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006216', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:30:08', '2018-08-27 10:30:08');
INSERT INTO `t_order_goods` VALUES ('20180827103006238', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 10:30:07', '2018-08-27 10:30:07');
INSERT INTO `t_order_goods` VALUES ('20180827103006238', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:30:08', '2018-08-27 10:30:08');
INSERT INTO `t_order_goods` VALUES ('20180827103145226', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 1, '2018-08-27 10:31:45', '2018-08-30 08:36:23');
INSERT INTO `t_order_goods` VALUES ('20180827103145226', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:31:45', '2018-08-27 10:31:45');
INSERT INTO `t_order_goods` VALUES ('20180827103706240', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 10:37:06', '2018-08-27 10:37:06');
INSERT INTO `t_order_goods` VALUES ('20180827103706240', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 10:37:06', '2018-08-27 10:37:06');
INSERT INTO `t_order_goods` VALUES ('20180827104158250', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 10:41:58', '2018-08-27 10:41:58');
INSERT INTO `t_order_goods` VALUES ('20180827112252267', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 11:22:52', '2018-08-27 11:22:52');
INSERT INTO `t_order_goods` VALUES ('20180827112252267', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 11:22:52', '2018-08-27 11:22:52');
INSERT INTO `t_order_goods` VALUES ('20180827112621280', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 11:26:22', '2018-08-27 11:26:22');
INSERT INTO `t_order_goods` VALUES ('20180827112621280', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 11:26:22', '2018-08-27 11:26:22');
INSERT INTO `t_order_goods` VALUES ('20180827112725230', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-27 11:27:25', '2018-08-27 11:27:25');
INSERT INTO `t_order_goods` VALUES ('20180827112725230', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 11:27:25', '2018-08-27 11:27:25');
INSERT INTO `t_order_goods` VALUES ('20180827112844216', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-27 11:28:44', '2018-08-27 11:28:44');
INSERT INTO `t_order_goods` VALUES ('20180827112844216', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 11:28:44', '2018-08-27 11:28:44');
INSERT INTO `t_order_goods` VALUES ('20180827113023268', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-27 11:30:24', '2018-08-27 11:30:24');
INSERT INTO `t_order_goods` VALUES ('20180827113023268', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 11:30:24', '2018-08-27 11:30:24');
INSERT INTO `t_order_goods` VALUES ('2018082711524723', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 2999.00, 0, '2018-08-27 11:52:47', '2018-08-27 11:52:47');
INSERT INTO `t_order_goods` VALUES ('2018082711524723', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-27 11:52:47', '2018-08-27 11:52:47');
INSERT INTO `t_order_goods` VALUES ('20180827120303224', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-27 12:03:03', '2018-08-27 12:03:03');
INSERT INTO `t_order_goods` VALUES ('20180827120303224', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 12:03:04', '2018-08-27 12:03:04');
INSERT INTO `t_order_goods` VALUES ('2018082704472327', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-27 16:47:23', '2018-08-27 16:47:23');
INSERT INTO `t_order_goods` VALUES ('2018082704472327', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-27 16:47:24', '2018-08-27 16:47:24');
INSERT INTO `t_order_goods` VALUES ('2018082709243721', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 3, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 8997.00, 1, '2018-08-27 21:24:38', '2018-08-28 16:28:17');
INSERT INTO `t_order_goods` VALUES ('2018082709243721', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 1, '2018-08-27 21:24:38', '2018-08-28 16:28:53');
INSERT INTO `t_order_goods` VALUES ('20180827112418281', 6, '小米（MI） 米家金属笔 中性笔0.5mm油墨笔水笔学生文具', 2, 'https://img12.360buyimg.com/n1/s450x450_jfs/t14860/116/2090661952/37035/3ff9e5f1/5a66ce31Na25721c9.jpg', 24.90, 49.80, 0, '2018-08-27 23:24:18', '2018-08-27 23:24:18');
INSERT INTO `t_order_goods` VALUES ('20180828074756247', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-28 07:47:56', '2018-08-28 07:47:56');
INSERT INTO `t_order_goods` VALUES ('20180828074756247', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-28 07:47:57', '2018-08-28 07:47:57');
INSERT INTO `t_order_goods` VALUES ('20180828075032234', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-28 07:50:32', '2018-08-28 07:50:32');
INSERT INTO `t_order_goods` VALUES ('20180828075032234', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-28 07:50:32', '2018-08-28 07:50:32');
INSERT INTO `t_order_goods` VALUES ('20180828075116224', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-28 07:51:16', '2018-08-28 07:51:16');
INSERT INTO `t_order_goods` VALUES ('20180828075116224', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-28 07:51:16', '2018-08-28 07:51:16');
INSERT INTO `t_order_goods` VALUES ('20180828075149214', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-28 07:51:49', '2018-08-28 07:51:49');
INSERT INTO `t_order_goods` VALUES ('20180828075149214', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-28 07:51:49', '2018-08-28 07:51:49');
INSERT INTO `t_order_goods` VALUES ('20180828075240287', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 07:52:41', '2018-08-28 07:52:41');
INSERT INTO `t_order_goods` VALUES ('20180828075240287', 84, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', 1, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', 189.00, 189.00, 0, '2018-08-28 07:52:41', '2018-08-28 07:52:41');
INSERT INTO `t_order_goods` VALUES ('20180828075258266', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 07:52:58', '2018-08-28 07:52:58');
INSERT INTO `t_order_goods` VALUES ('20180828075258266', 84, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', 1, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', 189.00, 189.00, 0, '2018-08-28 07:52:58', '2018-08-28 07:52:58');
INSERT INTO `t_order_goods` VALUES ('20180828075309223', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 07:53:10', '2018-08-28 07:53:10');
INSERT INTO `t_order_goods` VALUES ('20180828075309223', 84, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', 1, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', 189.00, 189.00, 0, '2018-08-28 07:53:10', '2018-08-28 07:53:10');
INSERT INTO `t_order_goods` VALUES ('20180828080531295', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 08:05:32', '2018-08-28 08:05:32');
INSERT INTO `t_order_goods` VALUES ('20180828080531295', 84, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', 1, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', 189.00, 189.00, 0, '2018-08-28 08:05:32', '2018-08-28 08:05:32');
INSERT INTO `t_order_goods` VALUES ('20180828081137241', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 08:11:38', '2018-08-28 08:11:38');
INSERT INTO `t_order_goods` VALUES ('20180828081137241', 84, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', 1, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', 189.00, 189.00, 0, '2018-08-28 08:11:38', '2018-08-28 08:11:38');
INSERT INTO `t_order_goods` VALUES ('20180828083342244', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-28 08:33:42', '2018-08-28 08:33:42');
INSERT INTO `t_order_goods` VALUES ('20180828085013275', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-28 08:50:14', '2018-08-28 08:50:14');
INSERT INTO `t_order_goods` VALUES ('20180828085021275', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-28 08:50:22', '2018-08-28 08:50:22');
INSERT INTO `t_order_goods` VALUES ('2018082808523622', 123, '佳能（Canon）联手小猪佩奇(PEPPA PIG) SELPHY CP1300 炫飞照片打印机 ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t17896/150/2286166272/200195/901be0f9/5aefe295N75399990.jpg', 999.00, 999.00, 0, '2018-08-28 08:52:37', '2018-08-28 08:52:37');
INSERT INTO `t_order_goods` VALUES ('20180828085625219', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 2999.00, 0, '2018-08-28 08:56:25', '2018-08-28 08:56:25');
INSERT INTO `t_order_goods` VALUES ('20180828085625219', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 08:56:25', '2018-08-28 08:56:25');
INSERT INTO `t_order_goods` VALUES ('20180828090114266', 117, '卡西欧 LINE FRIENDS | CASIO 联名系列女表运动防水手表', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t22390/180/2567352096/504791/ab981e60/5b5eb38aN5b637c5a.jpg', 1090.00, 1090.00, 0, '2018-08-28 09:01:15', '2018-08-28 09:01:15');
INSERT INTO `t_order_goods` VALUES ('20180828090858292', 137, '富士（FUJIFILM）INSTAX 一次成像相机 MINI25相机 白色 ', 1, 'https://img13.360buyimg.com/n1/s450x450_jfs/t2611/251/2644635268/106180/420c1e96/576cf3c0N925362b0.jpg', 699.00, 699.00, 0, '2018-08-28 09:08:58', '2018-08-28 09:08:58');
INSERT INTO `t_order_goods` VALUES ('20180828091758278', 127, 'Avalon 阿瓦隆 薰衣草滋润柔顺洗发水 325ml', 1, 'https://img12.360buyimg.com/n5/s450x450_jfs/t17035/342/2627121013/297674/952d2fa7/5afe7044Na75c969b.jpg', 9.90, 9.90, 0, '2018-08-28 09:17:59', '2018-08-28 09:17:59');
INSERT INTO `t_order_goods` VALUES ('201808280919563954', 128, 'The North Face/北面男卫衣外套连帽开衫纯色抓绒美国直邮tnf150', 1, 'https://img.alicdn.com/imgextra/i4/1785908005/TB1Soakc7fb_uJjSsrbXXb6bVXa_!!0-item_pic.jpg_430x430q90.jpg', 1764.00, 1764.00, 0, '2018-08-28 09:19:57', '2018-08-28 09:19:57');
INSERT INTO `t_order_goods` VALUES ('201808280919563954', 106, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/2601010048806/800_800_1531924326964mp.jpg', 2999.00, 2999.00, 0, '2018-08-28 09:19:57', '2018-08-28 09:19:57');
INSERT INTO `t_order_goods` VALUES ('20180828092226275', 84, '苏泊尔电饭煲锅小迷你型家用官方智能6旗舰店5正品4全自动1-2-3人', 1, 'https://img.alicdn.com/imgextra/i3/2088151283/TB2YQIwb6fguuRjSspkXXXchpXa_!!2088151283-0-item_pic.jpg_430x430q90.jpg', 189.00, 189.00, 0, '2018-08-28 09:22:27', '2018-08-28 09:22:27');
INSERT INTO `t_order_goods` VALUES ('20180828092233231', 117, '卡西欧 LINE FRIENDS | CASIO 联名系列女表运动防水手表', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t22390/180/2567352096/504791/ab981e60/5b5eb38aN5b637c5a.jpg', 1090.00, 1090.00, 0, '2018-08-28 09:22:34', '2018-08-28 09:22:34');
INSERT INTO `t_order_goods` VALUES ('20180828092413270', 137, '富士（FUJIFILM）INSTAX 一次成像相机 MINI25相机 白色 ', 1, 'https://img13.360buyimg.com/n1/s450x450_jfs/t2611/251/2644635268/106180/420c1e96/576cf3c0N925362b0.jpg', 699.00, 699.00, 0, '2018-08-28 09:24:13', '2018-08-28 09:24:13');
INSERT INTO `t_order_goods` VALUES ('20180828072604268', 125, '咪曦 茶具茶壶茶杯茶具套装哥窑功夫茶具开片陶瓷套装商务中秋礼品可定制', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t23311/74/1121293374/194834/23760e99/5b51516dNf9c640c0.jpg', 49.90, 49.90, 0, '2018-08-28 19:26:05', '2018-08-28 19:26:05');
INSERT INTO `t_order_goods` VALUES ('20180828072604268', 118, '新品上市 雀巢（Nestle）咖啡1+2原味速溶微研磨咖啡饮品100条1500g ', 1, 'https://img10.360buyimg.com/n1/jfs/t22405/41/2461238360/386634/e1760c1/5b580f43Nab2f0073.jpg', 112.00, 112.00, 0, '2018-08-28 19:26:05', '2018-08-28 19:26:05');
INSERT INTO `t_order_goods` VALUES ('20180828072604268', 191, 'CONVERSE匡威官方 One Star x Hello Kitty 低帮板鞋 162937C ', 1, 'https://img.alicdn.com/imgextra/i4/1046707508/TB2RGSGCQSWBuNjSszdXXbeSpXa_!!1046707508-0-item_pic.jpg_430x430q90.jpg', 699.00, 699.00, 0, '2018-08-28 19:26:05', '2018-08-28 19:26:05');
INSERT INTO `t_order_goods` VALUES ('20180828073933242', 136, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-28 19:39:34', '2018-08-28 19:39:34');
INSERT INTO `t_order_goods` VALUES ('20180828073933242', 72, 'JucyJudy官方旗舰店官网2018秋不规则笑脸牛仔半身长裙女', 1, 'https://img.alicdn.com/imgextra/i1/2645780884/TB2RYU8w9tYBeNjSspkXXbU8VXa_!!2645780884.jpg_430x430q90.jpg', 598.00, 598.00, 0, '2018-08-28 19:39:34', '2018-08-28 19:39:34');
INSERT INTO `t_order_goods` VALUES ('20180828113450235', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 2999.00, 0, '2018-08-28 23:34:50', '2018-08-28 23:34:50');
INSERT INTO `t_order_goods` VALUES ('20180828113450235', 137, '富士（FUJIFILM）INSTAX 一次成像相机 MINI25相机 白色 ', 1, 'https://img13.360buyimg.com/n1/s450x450_jfs/t2611/251/2644635268/106180/420c1e96/576cf3c0N925362b0.jpg', 699.00, 699.00, 0, '2018-08-28 23:34:50', '2018-08-28 23:34:50');
INSERT INTO `t_order_goods` VALUES ('20180828113450235', 126, '韩国进口 春雨(papa recipe)蜂蜜面膜 补水保湿舒缓滋润无添加面膜 敏感肌可用', 1, 'https://img13.360buyimg.com/n5/s450x450_jfs/t17836/160/2110688937/226116/f0e6f6d4/5ae40641N14b22aee.jpg', 129.00, 129.00, 0, '2018-08-28 23:34:50', '2018-08-28 23:34:50');
INSERT INTO `t_order_goods` VALUES ('20180828113450235', 171, '索尼（SONY）智能蓝牙音箱 LF-S80D（白色） ', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t10300/100/1315392557/167578/4eb216ca/59df2dd7N7c0882f4.jpg', 1899.00, 1899.00, 0, '2018-08-28 23:34:51', '2018-08-28 23:34:51');
INSERT INTO `t_order_goods` VALUES ('20180829064941297', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 2999.00, 0, '2018-08-29 18:49:41', '2018-08-29 18:49:41');
INSERT INTO `t_order_goods` VALUES ('20180829064941297', 160, '蒂佳婷（Dr.Jart+）维生素活颜亮白霜50g(V7素颜霜 提亮均匀肤色 改善暗沉肌肤）', 1, 'https://img14.360buyimg.com/n1/s546x546_jfs/t2806/215/1539410544/105289/51badc6d/5742827cN0fe0357b.jpg', 329.00, 329.00, 0, '2018-08-29 18:49:42', '2018-08-29 18:49:42');
INSERT INTO `t_order_goods` VALUES ('20180829070415271', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 19:04:16', '2018-08-29 19:04:16');
INSERT INTO `t_order_goods` VALUES ('20180829070415271', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-29 19:04:16', '2018-08-29 19:04:16');
INSERT INTO `t_order_goods` VALUES ('20180829070531279', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 19:05:32', '2018-08-29 19:05:32');
INSERT INTO `t_order_goods` VALUES ('20180829070531279', 134, 'KM男装商务休闲翻领风衣2018秋冬新品男士深蓝免烫涂层品质外套男 ', 1, 'https://img.alicdn.com/imgextra/i3/2091311303/TB2keCmAiOYBuNjSsD4XXbSkFXa_!!2091311303.jpg_430x430q90.jpg', 488.00, 488.00, 0, '2018-08-29 19:05:32', '2018-08-29 19:05:32');
INSERT INTO `t_order_goods` VALUES ('20180829073045212', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 19:30:45', '2018-08-29 19:30:45');
INSERT INTO `t_order_goods` VALUES ('20180829073045212', 139, '戴尔DELL成就 14英寸轻薄笔记本电脑(i5-7200U 指纹识别)', 1, 'https://img11.360buyimg.com/n1/s450x450_jfs/t22861/111/1028776637/204024/81c8c75a/5b4daa3bN7971a6dc.jpg', 4599.00, 4599.00, 0, '2018-08-29 19:30:45', '2018-08-29 19:30:45');
INSERT INTO `t_order_goods` VALUES ('20180829075213281', 107, 'HUAWEI nova 3 6GB+128GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250585/800_800_1534209941879mp.jpg', 2999.00, 2999.00, 0, '2018-08-29 19:52:13', '2018-08-29 19:52:13');
INSERT INTO `t_order_goods` VALUES ('201808291109294777', 103, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250394/800_800_1531368379226mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 23:09:30', '2018-08-29 23:09:30');
INSERT INTO `t_order_goods` VALUES ('201808291109294777', 104, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250363/800_800_1531368425114mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 23:09:30', '2018-08-29 23:09:30');
INSERT INTO `t_order_goods` VALUES ('201808291109294777', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 23:09:30', '2018-08-29 23:09:30');
INSERT INTO `t_order_goods` VALUES ('201808291112274731', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 2, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 5598.00, 0, '2018-08-29 23:12:28', '2018-08-29 23:12:28');
INSERT INTO `t_order_goods` VALUES ('201808291114444784', 103, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250394/800_800_1531368379226mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 23:14:45', '2018-08-29 23:14:45');
INSERT INTO `t_order_goods` VALUES ('201808291114444784', 104, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250363/800_800_1531368425114mp.jpg', 2799.00, 2799.00, 0, '2018-08-29 23:14:45', '2018-08-29 23:14:45');
INSERT INTO `t_order_goods` VALUES ('201808291114444784', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 2, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 5598.00, 0, '2018-08-29 23:14:45', '2018-08-29 23:14:45');
INSERT INTO `t_order_goods` VALUES ('20180830083527294', 62, '苏泊尔真不锈铁锅无涂层不生锈炒锅燃气明火专用家用精铁熟铁锅', 0, 'https://img.alicdn.com/imgextra/i2/699994102/TB2ZiKLpgDD8KJjy0FdXXcjvXXa_!!699994102.jpg_430x430q90.jpg', 299.00, 0.00, 0, '2018-08-30 08:35:28', '2018-08-30 08:35:28');
INSERT INTO `t_order_goods` VALUES ('20180830083527294', 78, '男装 便携式连帽外套 409313 优衣库UNIQLO', 0, 'https://img.alicdn.com/imgextra/i2/196993935/TB2KrtlG29TBuNjy0FcXXbeiFXa_!!196993935-0-item_pic.jpg_430x430q90.jpg', 249.00, 0.00, 0, '2018-08-30 08:35:28', '2018-08-30 08:35:28');
INSERT INTO `t_order_goods` VALUES ('20180830084424265', 62, '苏泊尔真不锈铁锅无涂层不生锈炒锅燃气明火专用家用精铁熟铁锅', 1, 'https://img.alicdn.com/imgextra/i2/699994102/TB2ZiKLpgDD8KJjy0FdXXcjvXXa_!!699994102.jpg_430x430q90.jpg', 299.00, 299.00, 0, '2018-08-30 08:44:24', '2018-08-30 08:44:24');
INSERT INTO `t_order_goods` VALUES ('20180830091457496', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 0, '2018-08-30 09:14:57', '2018-08-30 09:14:57');
INSERT INTO `t_order_goods` VALUES ('20180830091457496', 104, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250363/800_800_1531368425114mp.jpg', 2799.00, 2799.00, 0, '2018-08-30 09:14:57', '2018-08-30 09:14:57');
INSERT INTO `t_order_goods` VALUES ('201808300916064989', 105, 'HUAWEI nova 3 6GB+64GB 全网通版', 1, 'https://res.vmallres.com/pimages//product/6901443250370/800_800_1532415028213mp.jpg', 2799.00, 2799.00, 0, '2018-08-30 09:16:06', '2018-08-30 09:16:06');
INSERT INTO `t_order_goods` VALUES ('20180830093654282', 224, 'Porsche 911 GT2 RS', 1, 'https://files1.porsche.com/filestore/image/multimedia/none/991-2nd-gt2-rs-modelimage-sideshot/model/ed0a4842-451d-11e7-bfe2-0019999cd470;sG/porsche-model.png', 3898000.00, 3898000.00, 0, '2018-08-30 09:36:55', '2018-08-30 09:36:55');

-- ----------------------------
-- Table structure for t_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_order_info`;
CREATE TABLE `t_order_info`  (
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单 id',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '订单 用户id',
  `shop_id` int(10) UNSIGNED NOT NULL COMMENT '店铺id',
  `shop_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺名称',
  `total_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '订单 商品总价',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单 收货地址',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单 收货电话 ',
  `consignee` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单 收获人姓名',
  `alipay_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单 支付宝交易号',
  `tracking_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝交易号',
  `express_company` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家备注',
  `state` int(1) NULL DEFAULT 0 COMMENT '订单 状态：-1-删除，0-待付款(默认)，1-已付款，2-已发货，3-已收货',
  `created_at` datetime(0) NOT NULL COMMENT '订单 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '订单 更新时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id外键`(`user_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `user_id外键` FOREIGN KEY (`user_id`) REFERENCES `t_user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_info
-- ----------------------------
INSERT INTO `t_order_info` VALUES ('107607aa86', 2, 1, '云顶官方旗舰店', 14395.00, '海王星', '120', '胡大斌', NULL, NULL, NULL, '会影响我开奔驰的视角吗', 0, '2018-08-22 10:50:55', '2018-08-24 10:50:55');
INSERT INTO `t_order_info` VALUES ('12de15c3b9', 2, 1, '云顶官方旗舰店', 5982.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭二心', '11111111111', '1234567890', '顺丰速运', '易烊千玺呀冲呀', 3, '2018-08-24 10:53:22', '2018-08-26 08:57:23');
INSERT INTO `t_order_info` VALUES ('16a2dc4b22', 2, 1, '云顶官方旗舰店', 9134.91, '天上人间', '121', '郭一心', '22222222222', '2345678901', '韵达快递', '易烊千玺冲冲冲', 3, '2018-08-23 10:52:13', '2018-08-26 09:00:02');
INSERT INTO `t_order_info` VALUES ('20180824075014280', 2, 1, '云顶官方旗舰店', 13446.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭大心', NULL, NULL, NULL, '易烊千玺peace and love', 0, '2018-08-24 19:50:15', '2018-08-26 09:00:19');
INSERT INTO `t_order_info` VALUES ('20180824075036245', 2, 1, '云顶官方旗舰店', 7630.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭中心', NULL, NULL, NULL, '易烊千玺love', 0, '2018-08-24 19:50:37', '2018-08-24 20:27:48');
INSERT INTO `t_order_info` VALUES ('2018082407505826', 2, 1, '云顶官方旗舰店', 2497.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭三心', '66666666666', NULL, NULL, '易烊千玺peace', 1, '2018-08-24 19:50:59', '2018-08-26 08:57:04');
INSERT INTO `t_order_info` VALUES ('20180824075124296', 2, 1, '云顶官方旗舰店', 7586.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭四心', '77777777777', NULL, NULL, '易烊千玺likeu', 1, '2018-08-24 19:51:25', '2018-08-26 08:57:03');
INSERT INTO `t_order_info` VALUES ('20180824075145285', 2, 1, '云顶官方旗舰店', 635.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭五心', '88888888888', '6789012345', '苏宁快递', '易烊千玺嚯嚯嚯', 3, '2018-08-24 19:51:46', '2018-08-26 11:50:39');
INSERT INTO `t_order_info` VALUES ('20180824075205210', 2, 1, '云顶官方旗舰店', 516.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭六心', '99999999999', '7890123456', '申通快递', '易烊千玺啧啧啧', 3, '2018-08-24 19:52:06', '2018-08-26 08:57:00');
INSERT INTO `t_order_info` VALUES ('20180824075220254', 2, 1, '云顶官方旗舰店', 1386.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭七心', '00000000000', '8901234567', '中通快递', '易烊千玺嘤嘤嘤', 3, '2018-08-24 19:52:21', '2018-08-24 21:52:21');
INSERT INTO `t_order_info` VALUES ('20180825053057272', 2, 1, '云顶官方旗舰店', 12199.00, '冥王星', '118', '胡国斌', NULL, NULL, NULL, '会影响我开法拉利的视角吗', -1, '2018-08-25 17:30:58', '2018-08-28 16:29:49');
INSERT INTO `t_order_info` VALUES ('2018082605085023', 2, 1, '云顶官方旗舰店', 11990.00, '冥王星', '118', '胡国斌', '2018082721001004610500197772', NULL, NULL, '会影响我开法拉利的视角吗', 1, '2018-08-26 17:08:51', '2018-08-27 15:10:33');
INSERT INTO `t_order_info` VALUES ('20180826075159228', 2, 1, '云顶官方旗舰店', 635.00, '火星', '17635357422', '啦啦啦', '2018082721001004610500197780', NULL, NULL, '啦啦啦', 1, '2018-08-26 07:52:00', '2018-08-27 15:38:39');
INSERT INTO `t_order_info` VALUES ('20180826083208256', 2, 1, '云顶官方旗舰店', 11990.00, '冥王星', '118', '胡国斌', '11111111111', '2342342342', '快递', '会影响我开法拉利的视角吗', 3, '2018-08-26 20:32:09', '2018-08-29 20:06:42');
INSERT INTO `t_order_info` VALUES ('2018082704472327', 2, 1, '云顶官方旗舰店', 4728.00, '123', '123', '123', NULL, NULL, NULL, '123', -1, '2018-08-27 16:47:24', '2018-08-28 19:18:02');
INSERT INTO `t_order_info` VALUES ('2018082709243721', 2, 1, '云顶官方旗舰店', 9996.00, '做哪些改变需', '15534446789', '上次的发个红包吧', '2018082721001004910500171078', '123123123', '快递', '知道不葛笑', -1, '2018-08-27 21:24:38', '2018-08-29 21:19:50');
INSERT INTO `t_order_info` VALUES ('20180827103006291', 2, 1, '云顶官方旗舰店', 1487.00, '月球', '12645235845', '哈哈哈', '11111111111', '2342342342', '快递', '小心着陆', 3, '2018-08-27 10:30:08', '2018-08-30 08:36:28');
INSERT INTO `t_order_info` VALUES ('20180827103145226', 2, 1, '云顶官方旗舰店', 1487.00, '月球', '5564561', '爱神的箭奥斯卡了的', '11111111111', '2342342342', '快递', '着陆小小', 3, '2018-08-27 10:31:45', '2018-08-29 20:07:04');
INSERT INTO `t_order_info` VALUES ('20180827103706240', 2, 1, '云顶官方旗舰店', 5598.00, '月球', '456456115564', '啊啊啊', NULL, NULL, NULL, '', -1, '2018-08-27 10:37:06', '2018-08-28 16:29:48');
INSERT INTO `t_order_info` VALUES ('20180827112418281', 2, 1, '云顶官方旗舰店', 49.80, '地球', '18634312616', '齐语冰斌', '2018082721001004610200695766', NULL, NULL, '快发货！', 1, '2018-08-27 23:24:18', '2018-08-27 23:25:34');
INSERT INTO `t_order_info` VALUES ('20180827112621280', 2, 1, '云顶官方旗舰店', 5598.00, 'ddd', 'ccc', 'aaa', '2018082921001004910500172057', NULL, NULL, '', 1, '2018-08-27 11:26:22', '2018-08-29 21:44:37');
INSERT INTO `t_order_info` VALUES ('20180827112844216', 2, 1, '云顶官方旗舰店', 5598.00, 'bbb', '5645646414', 'aaa', NULL, NULL, NULL, '', -1, '2018-08-27 11:28:45', '2018-08-28 19:18:11');
INSERT INTO `t_order_info` VALUES ('2018082711524723', 2, 1, '云顶官方旗舰店', 3128.00, 'bbb', '1534864531538', 'aaa', NULL, NULL, NULL, '', -1, '2018-08-27 11:52:47', '2018-08-28 23:34:05');
INSERT INTO `t_order_info` VALUES ('20180827120303224', 2, 1, '云顶官方旗舰店', 4728.00, 'ddddd', '1564864564', 'aaaaa', NULL, NULL, NULL, '', 0, '2018-08-27 12:03:04', '2018-08-27 12:03:04');
INSERT INTO `t_order_info` VALUES ('20180828072604268', 2, 1, '云顶官方旗舰店', 860.90, '太原理工大学', '18634312616', '齐语冰', '2018082821001004610500197898', NULL, NULL, '', 1, '2018-08-28 19:26:05', '2018-08-28 19:26:20');
INSERT INTO `t_order_info` VALUES ('20180828073933242', 2, 1, '云顶官方旗舰店', 1086.00, '理工大', '110', '程宥霖', NULL, NULL, NULL, '快点发货', 0, '2018-08-28 19:39:34', '2018-08-28 19:39:34');
INSERT INTO `t_order_info` VALUES ('20180828074756247', 2, 1, '云顶官方旗舰店', 4728.00, '火星', '1234567891', '啊啊啊', NULL, NULL, NULL, '', -1, '2018-08-28 07:47:57', '2018-08-28 16:29:42');
INSERT INTO `t_order_info` VALUES ('20180828075032234', 2, 1, '云顶官方旗舰店', 4728.00, '地球', '110', 'qyb', NULL, NULL, NULL, '', -1, '2018-08-28 07:50:32', '2018-08-28 16:29:41');
INSERT INTO `t_order_info` VALUES ('20180828075116224', 2, 1, '云顶官方旗舰店', 4728.00, 'qwer', '110', 'qyb', NULL, NULL, NULL, '', -1, '2018-08-28 07:51:16', '2018-08-28 16:29:39');
INSERT INTO `t_order_info` VALUES ('20180828075149214', 2, 1, '云顶官方旗舰店', 4728.00, 'qwer', '110', 'qyb', NULL, NULL, NULL, '', -1, '2018-08-28 07:51:49', '2018-08-28 16:29:38');
INSERT INTO `t_order_info` VALUES ('20180828075240287', 2, 1, '云顶官方旗舰店', 677.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 07:52:41', '2018-08-28 16:29:33');
INSERT INTO `t_order_info` VALUES ('20180828075258266', 2, 1, '云顶官方旗舰店', 677.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 07:52:58', '2018-08-28 16:29:59');
INSERT INTO `t_order_info` VALUES ('20180828075309223', 2, 1, '云顶官方旗舰店', 677.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 07:53:10', '2018-08-28 16:29:57');
INSERT INTO `t_order_info` VALUES ('20180828080531295', 2, 1, '云顶官方旗舰店', 677.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 08:05:32', '2018-08-28 19:02:59');
INSERT INTO `t_order_info` VALUES ('20180828081137241', 2, 1, '云顶官方旗舰店', 677.00, 'qwer', 'qwer', 'qwer', '2018082821001004610200697576', NULL, NULL, '', -1, '2018-08-28 08:11:38', '2018-08-29 20:06:46');
INSERT INTO `t_order_info` VALUES ('20180828083342244', 2, 1, '云顶官方旗舰店', 4599.00, 'dsfss', 'fsdfsdfsd', 'saaa', '2018082821001004610500197897', NULL, NULL, '', -1, '2018-08-28 08:33:42', '2018-08-28 19:17:50');
INSERT INTO `t_order_info` VALUES ('20180828085013275', 2, 1, '云顶官方旗舰店', 129.00, 'dasasasdasda', 'asdasdasdasda', 'ghaaaa', NULL, NULL, NULL, '', 0, '2018-08-28 08:50:14', '2018-08-28 08:50:14');
INSERT INTO `t_order_info` VALUES ('20180828085021275', 2, 1, '云顶官方旗舰店', 4599.00, 'dasasasdasda', 'asdasdasdasda', 'ghaaaa', '2018082821001004910500171079', NULL, NULL, '', -1, '2018-08-28 08:50:22', '2018-08-28 19:17:54');
INSERT INTO `t_order_info` VALUES ('2018082808523622', 2, 1, '云顶官方旗舰店', 999.00, 'asdasdsad', 'asdasd', 'wedasd', '2018082821001004910200674385', NULL, NULL, '', 1, '2018-08-28 08:52:37', '2018-08-28 08:52:52');
INSERT INTO `t_order_info` VALUES ('20180828085625219', 2, 1, '云顶官方旗舰店', 3487.00, 'dfsdfsdf', 'sdfsdfsdfs', 'sdfsfsdf', '2018082821001004910200674386', NULL, NULL, '', 1, '2018-08-28 08:56:25', '2018-08-28 08:56:51');
INSERT INTO `t_order_info` VALUES ('20180828090114266', 2, 1, '云顶官方旗舰店', 1090.00, 'ssss', 'ddd', 'aaa', '2018082821001004910500171080', NULL, NULL, '', -1, '2018-08-28 09:01:15', '2018-08-28 09:09:56');
INSERT INTO `t_order_info` VALUES ('20180828090858292', 2, 1, '云顶官方旗舰店', 699.00, '大叔大婶多', '达到', '啊啊啊2', '2018082821001004910200674389', NULL, NULL, '', -1, '2018-08-28 09:08:58', '2018-08-28 09:09:54');
INSERT INTO `t_order_info` VALUES ('20180828091758278', 2, 1, '云顶官方旗舰店', 9.90, 'XZCZX', 'ZXCXZ', 'CZXC', '2018082821001004610200699118', NULL, NULL, 'XZCZ', 1, '2018-08-28 09:17:59', '2018-08-28 19:14:28');
INSERT INTO `t_order_info` VALUES ('201808280919563954', 39, 1, '云顶官方旗舰店', 4763.00, 'XXXXXXX', 'XXXXXXX', '小云云', NULL, NULL, NULL, 'XXXXXXXX', 0, '2018-08-28 09:19:57', '2018-08-28 09:19:57');
INSERT INTO `t_order_info` VALUES ('20180828092226275', 2, 1, '云顶官方旗舰店', 189.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 09:22:27', '2018-08-28 19:02:56');
INSERT INTO `t_order_info` VALUES ('20180828092233231', 2, 1, '云顶官方旗舰店', 1090.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 09:22:34', '2018-08-28 16:17:55');
INSERT INTO `t_order_info` VALUES ('20180828092413270', 2, 1, '云顶官方旗舰店', 699.00, 'qwer', 'qwer', 'qwer', NULL, NULL, NULL, '', -1, '2018-08-28 09:24:13', '2018-08-28 16:17:46');
INSERT INTO `t_order_info` VALUES ('20180828113450235', 2, 1, '云顶官方旗舰店', 5726.00, '吱吱吱', '123465658153184', '哈哈哈', NULL, NULL, NULL, '', -1, '2018-08-28 23:34:51', '2018-08-29 21:05:06');
INSERT INTO `t_order_info` VALUES ('20180829064941297', 2, 1, '云顶官方旗舰店', 3328.00, '地球', '18634312616', 'qyb', '2018082921001004610200699850', NULL, NULL, '', 1, '2018-08-29 18:49:42', '2018-08-29 18:50:20');
INSERT INTO `t_order_info` VALUES ('20180829070415271', 2, 1, '云顶官方旗舰店', 3287.00, 'diqiu', '18634312616', 'qyb', NULL, NULL, NULL, '', -1, '2018-08-29 19:04:16', '2018-08-29 19:04:50');
INSERT INTO `t_order_info` VALUES ('20180829070531279', 2, 1, '云顶官方旗舰店', 3287.00, '地球', '18634312616', 'qyb', '2018082921001004610500199041', NULL, NULL, '', 1, '2018-08-29 19:05:32', '2018-08-29 19:05:49');
INSERT INTO `t_order_info` VALUES ('20180829073045212', 2, 1, '云顶官方旗舰店', 7398.00, '789', '13546386889', '78', '2018082921001004610200699851', NULL, NULL, '789', 1, '2018-08-29 19:30:45', '2018-08-29 19:31:40');
INSERT INTO `t_order_info` VALUES ('20180829075213281', 2, 1, '云顶官方旗舰店', 2999.00, '123', '13152876992', '4123', NULL, NULL, NULL, '123', 0, '2018-08-29 19:52:14', '2018-08-29 19:52:14');
INSERT INTO `t_order_info` VALUES ('201808291109294777', 47, 1, '云顶官方旗舰店', 8397.00, '云顶', '18634312616', '小云云', '2018082921001004610200702484', NULL, NULL, '快发货', -1, '2018-08-29 23:09:30', '2018-08-29 23:11:03');
INSERT INTO `t_order_info` VALUES ('201808291112274731', 47, 1, '云顶官方旗舰店', 5598.00, '太原理工大学云顶书院', '18634312616', '小云云', '2018082921001004610200702486', NULL, NULL, '快发货呀', -1, '2018-08-29 23:12:28', '2018-08-29 23:12:48');
INSERT INTO `t_order_info` VALUES ('201808291114444784', 47, 1, '云顶官方旗舰店', 11196.00, '太原理工大学云顶书院', '18634312616', '小云云', '2018082921001004610500199042', NULL, NULL, '快发货呀', -1, '2018-08-29 23:14:45', '2018-08-29 23:15:02');
INSERT INTO `t_order_info` VALUES ('20180830083527294', 2, 1, '云顶官方旗舰店', 0.00, '123', '13152876992', '123', NULL, NULL, NULL, '123', -1, '2018-08-30 08:35:28', '2018-08-30 08:35:59');
INSERT INTO `t_order_info` VALUES ('20180830084424265', 2, 1, '云顶官方旗舰店', 299.00, '太原理工大学', '15534448217', '小云云', '2018083021001004910500172061', NULL, NULL, '', 1, '2018-08-30 08:44:24', '2018-08-30 12:15:22');
INSERT INTO `t_order_info` VALUES ('20180830091457496', 49, 1, '云顶官方旗舰店', 5598.00, '云顶书院', '18634312616', '潘大伟', '2018083021001004610500199046', NULL, NULL, '', 1, '2018-08-30 09:14:57', '2018-08-30 09:15:19');
INSERT INTO `t_order_info` VALUES ('201808300916064989', 49, 1, '云顶官方旗舰店', 2799.00, 'qwer', '18634312616', '齐语冰', NULL, NULL, NULL, '', -1, '2018-08-30 09:16:06', '2018-08-30 09:16:20');
INSERT INTO `t_order_info` VALUES ('20180830093654282', 2, 1, '云顶官方旗舰店', 3898000.00, '山西省晋中市榆次区太原理工大学明向校区', '15534444911', '郭怡馨', '2018083021001004610200702489', NULL, NULL, '买买买', 1, '2018-08-30 09:36:55', '2018-08-30 09:40:07');
INSERT INTO `t_order_info` VALUES ('89ed22dc29', 2, 1, '云顶官方旗舰店', 1396.00, '冥王星', '119', '胡小斌', '33333333333', '3456789012', '圆通快递', '会影响我开法拉利的视角吗', 3, '2018-08-21 10:50:18', '2018-08-26 09:00:06');
INSERT INTO `t_order_info` VALUES ('ef605dc481', 2, 1, '云顶官方旗舰店', 12199.00, '冥王星', '118', '胡国斌', '44444444444', '4567890123', '百世汇通', '会影响我开法拉利的视角吗', 3, '2018-08-20 10:49:42', '2018-08-24 10:49:42');
INSERT INTO `t_order_info` VALUES ('f7f638e3aa', 2, 1, '云顶官方旗舰店', 13995.00, '山西省晋中市榆次区太原理工大学西门', '15534444911', '郭小心', '55555555555', '5678901234', 'EMS', '易烊千玺冲啊冲', 3, '2018-08-24 10:54:22', '2018-08-25 23:28:31');

-- ----------------------------
-- Table structure for t_platform_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_goods_category`;
CREATE TABLE `t_platform_goods_category`  (
  `platform_goods_category_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品所属平台类别 id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品所属平台类别 名称',
  `created_at` datetime(0) NOT NULL COMMENT '商品所属平台类别 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '商品所属平台类别 更新时间',
  `goods_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品所属平台类别 商品数量',
  PRIMARY KEY (`platform_goods_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_platform_goods_category
-- ----------------------------
INSERT INTO `t_platform_goods_category` VALUES (1, '电器', '2018-08-03 16:53:15', '2018-08-03 16:54:10', 12);
INSERT INTO `t_platform_goods_category` VALUES (2, '服饰', '2018-08-03 16:58:49', '2018-08-03 16:58:51', 33);
INSERT INTO `t_platform_goods_category` VALUES (3, '生活', '2018-08-03 16:59:14', '2018-08-03 16:59:16', 25);
INSERT INTO `t_platform_goods_category` VALUES (4, '电子', '2018-08-03 16:59:24', '2018-08-03 16:59:26', 21);
INSERT INTO `t_platform_goods_category` VALUES (5, '运动', '2018-08-03 16:59:44', '2018-08-03 16:59:45', 20);
INSERT INTO `t_platform_goods_category` VALUES (6, '食品', '2018-08-03 17:00:29', '2018-08-03 17:00:32', 15);
INSERT INTO `t_platform_goods_category` VALUES (7, '文具图书', '2018-08-03 17:41:41', '2018-08-03 17:41:43', 22);
INSERT INTO `t_platform_goods_category` VALUES (8, '护肤美妆', '2018-08-03 19:26:18', '2018-08-03 19:26:20', 22);

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop`  (
  `shop_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '店铺 id',
  `logo` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'https://i.loli.net/2018/08/18/5b778f98ab299.png' COMMENT '店铺 logo',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '店铺 所属用户id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺 名称',
  `sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺 销量',
  `collection_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺 被收藏数量',
  `state` int(1) NOT NULL DEFAULT -1 COMMENT '店铺 状态：-1：封禁 0：正常(默认)',
  `created_at` datetime(0) NOT NULL COMMENT '店铺 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '店铺 更新时间',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '店铺 主页HTML',
  PRIMARY KEY (`shop_id`) USING BTREE,
  INDEX `用户id外键`(`user_id`) USING BTREE,
  CONSTRAINT `用户id外键` FOREIGN KEY (`user_id`) REFERENCES `t_user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES (1, 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 1, '云顶官方旗舰店', 2701, 20000, 0, '2018-08-03 15:05:44', '2018-08-30 09:36:55', '<html>');

-- ----------------------------
-- Table structure for t_shop_content
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_content`;
CREATE TABLE `t_shop_content`  (
  `content_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺文章 id',
  `shop_id` int(11) NOT NULL COMMENT '店铺文章 店铺id',
  `url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺文章 商品id',
  `title1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺文章 标题1',
  `title2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺文章 标题2',
  `title3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺文章 标题3',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺文章 图片',
  `type` int(1) NOT NULL COMMENT '店铺文章 文章类型 0-轮播图 1-精选好物 2-精选好物长图 3-类目一览 4-新品首发',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '店铺文章 状态 -1-删除 0-正常',
  PRIMARY KEY (`content_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_shop_content
-- ----------------------------
INSERT INTO `t_shop_content` VALUES (1, 1, '/goodsdetail/107', NULL, NULL, NULL, 'https://i.loli.net/2018/08/19/5b78cb5474b8b.jpg', 0, 0);
INSERT INTO `t_shop_content` VALUES (2, 1, '/goodsdetail/145', NULL, NULL, NULL, 'https://i.loli.net/2018/08/26/5b820745f05d1.jpg', 0, 0);
INSERT INTO `t_shop_content` VALUES (3, 1, '/goodsdetail/191', NULL, NULL, NULL, 'https://i.loli.net/2018/08/27/5b83c35910d2b.jpg', 0, 0);
INSERT INTO `t_shop_content` VALUES (4, 1, '/goodsdetail/197', NULL, NULL, NULL, 'https://gdp.alicdn.com/imgextra/i4/285492195/O1CN011S5LobZuHC1yI9Z_!!285492195.jpg', 0, 0);
INSERT INTO `t_shop_content` VALUES (5, 1, '/goodsdetail/193', NULL, NULL, NULL, 'https://i.loli.net/2018/08/27/5b83c38d41725.jpg', 0, 0);
INSERT INTO `t_shop_content` VALUES (6, 1, '/search/VANS帆布鞋', NULL, NULL, NULL, 'https://i.loli.net/2018/08/27/5b83cc1c22f8d.png', 0, 0);
INSERT INTO `t_shop_content` VALUES (7, 1, '/goodsdetail/153', '热卖生鲜', '精选蜜桔 皮薄多汁', NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25636/65/27463528/420173/75cb9464/5b62dcdaN69649742.jpg', 1, 0);
INSERT INTO `t_shop_content` VALUES (8, 1, '/goodsdetail/154', '热卖个护', '一支跟你说爱的牙膏', NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t18448/280/25433125/167381/80f40813/5a58539bNbb8553b2.jpg', 1, 0);
INSERT INTO `t_shop_content` VALUES (9, 1, '/goodsdetail/155', '热卖机型', '漂亮的不像实力派', NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t19330/180/2329733856/135650/a98f36ed/5af039c3Nb72bf629.jpg', 1, 0);
INSERT INTO `t_shop_content` VALUES (10, 1, '/goodsdetail/156', '精选好车', '追风八月 “摩”轰火烈', NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t19498/305/2667794416/459423/7d3b290/5b078463N94ad06de.png', 1, 0);
INSERT INTO `t_shop_content` VALUES (11, 1, '/goodsdetail/157', '热卖好礼', '魔幻悬浮 创意精彩', NULL, 'https://i.loli.net/2018/08/27/5b83e298d24d6.jpg', 1, 0);
INSERT INTO `t_shop_content` VALUES (12, 1, '/goodsdetail/159', '热卖彩妆', 'TF限量迷你口红套装', NULL, 'https://img14.360buyimg.com/n5/s450x450_jfs/t2986/365/2207632773/129360/1c3b9b7b/57a08b8eNe04203de.jpg', 1, 0);
INSERT INTO `t_shop_content` VALUES (13, 1, '/goodsdetail/158', '热卖工具', '德国工艺 铝合金外壳', NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t18943/1/524858632/166760/da5adf10/5a961260N445b5f36.jpg', 1, 0);
INSERT INTO `t_shop_content` VALUES (14, 1, '/goodsdetail/160', '热卖面霜', '提亮肤色 改善肌肤', NULL, 'https://i.loli.net/2018/08/26/5b822717a57d4.jpg', 2, 0);
INSERT INTO `t_shop_content` VALUES (15, 1, '/goodsdetail/161', '电器', NULL, NULL, 'https://i.loli.net/2018/08/26/5b8245efe68d4.png', 3, 0);
INSERT INTO `t_shop_content` VALUES (16, 1, '/goodsdetail/162', '服饰', NULL, NULL, 'https://i.loli.net/2018/08/26/5b824784cbe09.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (17, 1, '/goodsdetail/165', '生活', NULL, NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t24967/92/583377150/233158/bf71ade0/5b74cddbN92fd43fa.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (18, 1, '/goodsdetail/107', '手机', NULL, NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t25957/316/473618774/176642/e7887cd2/5b70f566N4556014e.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (19, 1, '/goodsdetail/166', '运动', NULL, NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t21001/75/1641723414/52550/c846014c/5b2fa190N7aef80e4.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (20, 1, '/goodsdetail/169', '食品', NULL, NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t26101/365/315048065/411816/ca0373bb/5b6bb92eN39aa5164.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (21, 1, '/goodsdetail/170', '文具', NULL, NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t1405/116/1278899353/90084/a4dbf7fa/55ed5bc7Ndcf14901.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (22, 1, '/goodsdetail/22', '美妆', NULL, NULL, 'https://img14.360buyimg.com/n1/s546x546_jfs/t24406/294/2138486943/113792/266f2c33/5b768b17N2f73bc7c.jpg', 3, 0);
INSERT INTO `t_shop_content` VALUES (23, 1, '/goodsdetail/185', '新品上市', 'CONVERSE x Hello Kitty', '立即购买', 'https://i.loli.net/2018/08/27/5b83c12b1340d.jpg', 4, 0);

-- ----------------------------
-- Table structure for t_shop_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_goods_category`;
CREATE TABLE `t_shop_goods_category`  (
  `shop_goods_category_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品所属店铺类别 id',
  `shop_id` int(10) UNSIGNED NOT NULL COMMENT '商品所属店铺类别 所属店铺id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品所属店铺类别 名称',
  `created_at` datetime(0) NOT NULL COMMENT '商品所属店铺类别 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '商品所属店铺类别 更新时间',
  `goods_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品所属店铺类别 商品数量',
  PRIMARY KEY (`shop_goods_category_id`) USING BTREE,
  INDEX `shop_id外键`(`shop_id`) USING BTREE,
  CONSTRAINT `shop_id外键` FOREIGN KEY (`shop_id`) REFERENCES `t_shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_shop_goods_category
-- ----------------------------
INSERT INTO `t_shop_goods_category` VALUES (1, 1, '电器', '2018-08-03 16:54:10', '2018-08-03 17:03:49', 12);
INSERT INTO `t_shop_goods_category` VALUES (2, 1, '服饰', '2018-08-03 17:04:14', '2018-08-03 17:04:15', 33);
INSERT INTO `t_shop_goods_category` VALUES (3, 1, '生活', '2018-08-03 17:04:57', '2018-08-03 17:04:59', 25);
INSERT INTO `t_shop_goods_category` VALUES (4, 1, '电子', '2018-08-03 17:05:30', '2018-08-03 17:05:33', 21);
INSERT INTO `t_shop_goods_category` VALUES (5, 1, '运动', '2018-08-03 17:06:14', '2018-08-03 17:06:15', 20);
INSERT INTO `t_shop_goods_category` VALUES (6, 1, '食品', '2018-08-03 17:24:00', '2018-08-03 17:24:02', 15);
INSERT INTO `t_shop_goods_category` VALUES (7, 1, '文具图书', '2018-08-03 17:41:58', '2018-08-03 17:42:04', 22);
INSERT INTO `t_shop_goods_category` VALUES (8, 1, '护肤美妆', '2018-08-03 19:25:54', '2018-08-03 19:25:59', 22);

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户 id',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '云顶电商平台用户' COMMENT '用户 昵称',
  `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'https://i.loli.net/2018/08/18/5b778f98ab299.png' COMMENT '用户 头像',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '用户 性别：0-保密，1-男，2-女',
  `birthday` date NULL DEFAULT NULL COMMENT '用户 生日',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户 手机号',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户 邮箱地址',
  `xp` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户 用户经验值',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '用户 状态：-1 封禁，0-正常买家(默认) ，1-买家+卖家',
  `created_at` datetime(0) NOT NULL COMMENT '用户 创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '用户 更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES (1, '云顶官方号', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-03 14:53:22', '2018-08-19 10:48:03');
INSERT INTO `t_user_info` VALUES (2, '测试买家号', 'https://i.loli.net/2018/08/30/5b87536c38e1d.png', 0, '2018-08-29', '22222222222', '123', 0, 0, '2018-08-06 15:23:26', '2018-08-30 10:16:13');
INSERT INTO `t_user_info` VALUES (19, NULL, 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-09 11:58:34', '2018-08-09 11:58:34');
INSERT INTO `t_user_info` VALUES (20, NULL, 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-11 14:21:48', '2018-08-11 14:21:48');
INSERT INTO `t_user_info` VALUES (21, NULL, 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-11 14:32:03', '2018-08-11 14:32:03');
INSERT INTO `t_user_info` VALUES (24, 'halftion', 'https://i.loli.net/2018/08/23/5b7e747b15543.png', 1, '1998-05-04', '17635357422', 'halftion@163.com', 0, 0, '2018-08-11 15:46:00', '2018-08-23 18:31:42');
INSERT INTO `t_user_info` VALUES (37, '期待能', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-27 19:47:58', '2018-08-27 19:47:58');
INSERT INTO `t_user_info` VALUES (39, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-28 09:18:36', '2018-08-28 09:18:36');
INSERT INTO `t_user_info` VALUES (40, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-28 09:20:37', '2018-08-28 09:20:37');
INSERT INTO `t_user_info` VALUES (41, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-28 09:30:53', '2018-08-28 09:30:53');
INSERT INTO `t_user_info` VALUES (42, 'qyb', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 19:36:06', '2018-08-29 19:36:06');
INSERT INTO `t_user_info` VALUES (43, '哈哈哈', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 19:44:14', '2018-08-29 19:44:14');
INSERT INTO `t_user_info` VALUES (44, '啦啦啦', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 19:48:16', '2018-08-29 19:48:16');
INSERT INTO `t_user_info` VALUES (45, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 21:22:42', '2018-08-29 21:22:42');
INSERT INTO `t_user_info` VALUES (46, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 21:25:09', '2018-08-29 21:25:09');
INSERT INTO `t_user_info` VALUES (47, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 22:51:28', '2018-08-29 22:51:28');
INSERT INTO `t_user_info` VALUES (48, 'halftion', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-29 23:10:25', '2018-08-29 23:10:25');
INSERT INTO `t_user_info` VALUES (49, '小云云', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 1, '2018-08-30', '18634312616', '123@qq.com', 0, 0, '2018-08-30 09:08:17', '2018-08-30 10:20:17');
INSERT INTO `t_user_info` VALUES (50, 'leeyf', 'https://i.loli.net/2018/08/18/5b778f98ab299.png', 0, NULL, NULL, NULL, 0, 0, '2018-08-30 16:46:52', '2018-08-30 16:46:52');

SET FOREIGN_KEY_CHECKS = 1;
