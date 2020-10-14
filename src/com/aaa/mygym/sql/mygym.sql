/*
 Navicat Premium Data Transfer

 Source Server         : mysql57
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : mygym

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 14/10/2020 14:43:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardId` int(20) NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `amount` double(11, 0) NULL DEFAULT 1000,
  `credit` int(11) NULL DEFAULT 1000 COMMENT '卡积分',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(0:正常 1:挂失)',
  `staffId` int(20) NULL DEFAULT NULL,
  `levelId` int(11) NULL DEFAULT NULL COMMENT '会员等级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员卡' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES (1, 100001, 1090001, 13822, 1620, 1, 20190001, 4);
INSERT INTO `card` VALUES (2, 100002, 1090002, 34847, 5600, 1, 20190001, 4);
INSERT INTO `card` VALUES (3, 100003, 1090003, 44277, 4500, 1, 20190001, 4);
INSERT INTO `card` VALUES (4, 100004, 1090004, 337140, 4700, 1, 20190001, 4);
INSERT INTO `card` VALUES (5, 100005, 1090005, 138560, 8500, 1, 20190001, 4);
INSERT INTO `card` VALUES (6, 100006, 1090006, 73140, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (7, 100007, 1090007, 31000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (8, 100008, 1090008, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (9, 100009, 1090009, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (10, 100010, 1090010, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (11, 100011, 1090011, 3500, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (12, 100012, 1090012, 1410, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (13, 100013, 1090013, 1180, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (14, 100014, 1090014, 1180, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (15, 100015, 1090015, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (16, 100016, 1090016, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (17, 100017, 1090017, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (18, 100018, 1090018, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (19, 100019, 1090019, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (20, 100020, 1090020, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (21, 100021, 1090021, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (22, 100022, 1090022, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (23, 100023, 1090023, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (24, 100024, 1090024, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (25, 100025, 1090025, 1000, 1000, 1, 20190001, 3);
INSERT INTO `card` VALUES (47, 999, 999, 998868, 1000, 1, 20190001, 4);
INSERT INTO `card` VALUES (55, 100026, 1090026, 1000, 1000, 1, 20190001, 4);
INSERT INTO `card` VALUES (59, 100027, 1090027, 100000, 100000, 1, 20190001, 5);
INSERT INTO `card` VALUES (73, 100028, 1090028, 1325, 1000, 1, 20190001, 3);

-- ----------------------------
-- Table structure for cardtype
-- ----------------------------
DROP TABLE IF EXISTS `cardtype`;
CREATE TABLE `cardtype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rank` int(11) NULL DEFAULT NULL COMMENT '升级所需积分',
  `status` int(11) NULL DEFAULT NULL,
  `coefficient` double(3, 2) NULL DEFAULT NULL COMMENT '消费折扣系数',
  `level` int(11) NULL DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户等级\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cardtype
-- ----------------------------
INSERT INTO `cardtype` VALUES (1, '普通会员', 0, 1, 1.00, 1);
INSERT INTO `cardtype` VALUES (2, '青铜会员', 100, 1, 1.00, 2);
INSERT INTO `cardtype` VALUES (3, '白银会员', 1000, 1, 1.00, 3);
INSERT INTO `cardtype` VALUES (4, '黄金会员', 10000, 1, 0.98, 4);
INSERT INTO `cardtype` VALUES (5, '铂金会员', 100000, 1, 0.90, 5);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(0:禁用  1：启用)',
  `momo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '饮料', 1, '喝的');
INSERT INTO `category` VALUES (2, '营养品', 1, '补的');
INSERT INTO `category` VALUES (3, '服务商品', 1, '玩的');
INSERT INTO `category` VALUES (4, '零食', 1, '吃的');
INSERT INTO `category` VALUES (10, '奥里给', 0, '服务你，没道理');
INSERT INTO `category` VALUES (11, '吃了某', 1, '常规问候');
INSERT INTO `category` VALUES (12, '玩的', 1, '球');
INSERT INTO `category` VALUES (13, '新增商品分类', 1, '新增商品分类修改');
INSERT INTO `category` VALUES (14, '营养类', 1, '营养品');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0 COMMENT '状态(0:在售 1:下架)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型(1:普通商品  0: 服务商品)',
  `unitId` int(11) NULL DEFAULT NULL COMMENT '单位',
  `price` double(11, 2) NULL DEFAULT NULL,
  `categoryId` int(1) NULL DEFAULT NULL COMMENT '类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表(单位表，类别表)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '1001', '可乐', '289', 1, 1, 1, 3.00, 1);
INSERT INTO `goods` VALUES (2, '1002', '蛋白粉', '6', 1, 1, 2, 20.00, 2);
INSERT INTO `goods` VALUES (3, '1003', '牛奶', '86', 2, 1, 1, 5.00, 3);
INSERT INTO `goods` VALUES (4, '1004', '巧克力', '6', 1, 0, 1, 3.50, 1);
INSERT INTO `goods` VALUES (5, '1005', '瑜伽', '96', 1, 0, 3, 200.00, 3);
INSERT INTO `goods` VALUES (12, '1012', '酸奶', '197', 1, 1, 6, 5.00, 3);
INSERT INTO `goods` VALUES (13, '1013', '新增商品名称', '99', 1, 0, 7, 10.00, 13);
INSERT INTO `goods` VALUES (14, '1014', '新增商品', '10', 1, 0, 6, 5.00, 13);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告新闻的内容',
  `status` int(11) NULL DEFAULT NULL,
  `staffId` int(255) NULL DEFAULT NULL COMMENT '员工id',
  `createdTime` date NULL DEFAULT NULL COMMENT '新闻开始时间',
  `endTime` date NULL DEFAULT NULL COMMENT '新闻结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告新闻表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '看体育与健康监测结果报告全文！我国首次发布分学科义务教育质量监测结果.', '教育部基础教育质量监测中心日前发布了《2018年国家义务教育质量监测——体育与健康监测结果报告》，这是我国首次发布分学科的义务教育质量监测结果。体育与健康监测结果显示：四年级和八年级学生发育整体较好，但存在学生肥胖率、视力不良检出率较高的情况。', 1, 20190001, '2019-06-01', '2019-06-02');
INSERT INTO `news` VALUES (2, '中国体育新闻工作者协会40年成绩斐然 优秀体育记者受到褒奖', '新华社北京11月21日电（郁思辉）21日，为纪念中国体育新闻工作者协会成立40周年，由中国体育新闻工作者协会主办的体育新闻事业成就展及研讨会在北京体育大学举行。\r\n当日下午，本次纪念活动启动仪式在北体大教学楼前举行。国家体育总局宣传司副司长、中国体育新闻工作者协会副主席曹康致辞。\r\n随后，庆祝中国体育新闻工作者协会成立40周年研讨会举行。北京体育大学党委副书记邢尚杰致辞。来自体育界、体育新闻界、北京冬奥组委的代表做了主题演讲和研讨交流。\r\n21日晚，在北体大体育馆内举行了相关文体展演，并分别为从事体育新闻工作20年、30年、40年以及获新兴媒体奖的体育新闻工作者代表举行了颁奖仪式。中国体育新闻工作者协会主席、国家体育总局宣传司司长涂晓东致辞。\r\n  此次展览在北京体育大学校园内设置了长达80米的展墙以及14个室内展柜，展览将持续至12月5日。\r\n本次活动由北京圈内圈外文化发展有限公司承办，恒源祥集团为活动独家合作伙伴。', 1, 20190002, '2019-11-21', '2019-11-22');
INSERT INTO `news` VALUES (3, '萧华：支持詹皇对莫雷事件发声 不说话也是个人自由', '勒布朗-詹姆斯批评莫雷在中国事务上不负责任乱说话的表态，在美国引起强烈反响，被很多人指摘。最近，NBA总裁亚当-萧华站出来，表达了他对勒布朗言论的支持态度。当地时间周一，萧华接受了ESPN的采访，表示支持勒布朗对于莫雷事件的回复，“我认为这些NBA球员，拿勒布朗为例，他在过去改变美国人生活方面有着杰出的记录可查，被要求对于一个困难的外国话题发表评论。我再说一遍，这里有自由表达的权利，他应该说出自己的感受。”\n对于在这件事情上选择回避和不发声的球员，萧华也表示支持，“言论自由也意味着不说话的自由。就像我经常对要面对国内话题的球员说的那样，如果有些事情你不知道，或者你感觉恢复起来不舒服，那么不说话也没问题。', 1, 20190003, '2019-11-22', '2019-12-25');
INSERT INTO `news` VALUES (4, '中国确定将举办2021年世俱杯 FIFA周四宣布', '本月24日，国际足联理事会第十一次会议将会在上海举行，会上将探讨2021年世俱杯主办权问题。《纽约时报》如今提前爆料称，中国将会承办2021年世俱杯。该报称FIFA已经提前做出了这一决定，只等在上海正式官宣。\r\n在目前赛制下，世俱杯由各大洲冠军球队参加，比赛时间为每年12月中下旬。国际足联将会对世俱杯进行改革，赛制从每年一届改为四年一届，参赛球队则扩充到24队。比赛时间，会由12月调整到六七月份。改制后的首届世俱杯，将会在2021年6月17日至7月4日之间在中国举行。', 1, 20190004, '2019-10-24', '2019-10-24');
INSERT INTO `news` VALUES (5, '亚洲骄傲！孙兴慜首次入围金球奖30人 韩国史上第3', '《法国足球》公布了2019年金球奖的30人候选名单，效力于英超热刺的韩国球星孙兴慜首次入围。孙兴慜因此成为了史上第三位入围金球奖候选名单的韩国球员，而此前两位韩国前辈在评选时都没能获得选票。\r\n韩国足球历史上，前两位入围金球奖候选名单的分别是薛琦铉（2002年）和朴智星（2005年）。薛琦铉曾在安德莱赫特、狼队、雷丁、富勒姆等俱乐部效力，他是首位在欧冠取得进球的韩国球员。在2002年韩日世界杯上，薛琦铉随韩国杀进半决赛，他在对意大利的比赛中曾打进扳平比分的进球，这也为薛琦铉入围金球奖添加了重重的砝码。', 1, 20190005, '2019-04-01', '2019-04-02');
INSERT INTO `news` VALUES (22, '震惊！一男子竟...', '今日，广东一男子继“葛优瘫”后以“广东步”走红网络', 0, 20190001, '2019-12-11', '2019-12-18');
INSERT INTO `news` VALUES (23, '新增公告标题', '新增公告修改', 0, 20190001, '2019-12-06', '2019-12-09');
INSERT INTO `news` VALUES (24, '新增公告标题', '新增的公告新闻', 1, 20190001, '2019-12-11', '2019-12-28');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(200) NULL DEFAULT NULL COMMENT '订单编号',
  `cardId` int(11) NULL DEFAULT NULL COMMENT '卡id',
  `cardType` int(11) NULL DEFAULT NULL COMMENT '卡类型',
  `price` double(11, 2) NULL DEFAULT NULL COMMENT '应付金额',
  `pay` double NULL DEFAULT NULL COMMENT '实付金额',
  `credit` int(11) NULL DEFAULT NULL COMMENT '商品积分',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态（0未核验 1已核验）',
  `momo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `createdTime` datetime(0) NULL DEFAULT NULL COMMENT '消费日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单账单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 100001, 100001, 3, 200.00, 200, 1, 1, '', '2019-12-01 00:00:00');
INSERT INTO `order` VALUES (3, 100003, 100008, 3, 0.00, 0, 4, 0, '', '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (4, 100004, 100004, 3, 25.00, 25, 3, 1, '', '2019-02-22 00:00:00');
INSERT INTO `order` VALUES (5, 100005, 100025, 3, 12.00, 12, 20, 1, '', '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (6, 100006, 100030, 3, 3.00, 3, 3, 0, NULL, '2019-02-22 00:00:00');
INSERT INTO `order` VALUES (8, 100008, 100008, 3, 3.00, 3, 3, 1, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (9, 100009, 100014, 3, 5.00, 5, 5, 1, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (10, 100010, 100001, 3, 20.00, 20, 30, 1, NULL, '2019-02-20 00:00:00');
INSERT INTO `order` VALUES (11, 100011, 100008, 3, 5.00, 5, 5, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (12, 100012, 100009, 3, 3.00, 3, 3, 0, NULL, '2019-02-22 00:00:00');
INSERT INTO `order` VALUES (13, 100013, 100010, 3, 5.00, 5, 5, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (14, 100014, 100013, 3, 3.00, 3, 3, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (15, 100015, 100004, 3, 5.00, 5, 5, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (16, 100016, 100001, 3, 3.00, 3, 3, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (17, 100017, 100003, 3, 5.00, 5, 5, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (18, 100018, 100025, 3, 3.00, 3, 3, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (19, 100019, 100004, 3, 5.00, 5, 5, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (20, 100020, 100021, 3, 3.00, 3, 3, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (21, 100021, 100022, 3, 5.00, 5, 5, 0, NULL, '2019-11-29 00:00:00');
INSERT INTO `order` VALUES (22, 100022, 100015, 3, 3.00, 3, 3, 0, NULL, '2019-06-22 00:00:00');
INSERT INTO `order` VALUES (23, 100023, 100018, 3, 5.00, 5, 5, 0, NULL, '2019-05-22 00:00:00');
INSERT INTO `order` VALUES (24, 100024, 100021, 3, 3.00, 3, 3, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (25, 100025, 100044, 3, 5.00, 5, 5, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (26, 100026, 100002, 3, 3.00, 3, 3, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (27, 100027, 100007, 3, 5.00, 5, 5, 0, NULL, '2019-02-22 00:00:00');
INSERT INTO `order` VALUES (28, 100028, 100004, 3, 3.00, 3, 3, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (29, 100029, 100026, 3, 6.00, 6, 6, 0, NULL, '2019-11-26 00:00:00');
INSERT INTO `order` VALUES (30, 100030, 100039, 3, 5.00, 5, 5, 0, NULL, '2019-04-22 00:00:00');
INSERT INTO `order` VALUES (31, 100031, 100046, 3, 3.00, 3, 3, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (32, 100032, 100037, 3, 5.00, 5, 5, 0, NULL, '2019-10-22 00:00:00');
INSERT INTO `order` VALUES (33, 100033, 100022, 3, 3.00, 3, 3, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (34, 100034, 100014, 3, 5.00, 5, 5, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (35, 100035, 100012, 3, 3.00, 3, 3, 0, NULL, '2019-06-22 00:00:00');
INSERT INTO `order` VALUES (36, 100036, 100004, 3, 5.00, 5, 5, 0, NULL, '2019-09-22 00:00:00');
INSERT INTO `order` VALUES (37, 100037, 100039, 3, 3.00, 3, 3, 0, NULL, '2019-11-22 00:00:00');
INSERT INTO `order` VALUES (38, 100098, 100004, 3, 5.00, 5, 5, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (39, 100039, 100004, 3, 3.00, 3, 3, 0, NULL, '2019-11-29 00:00:00');
INSERT INTO `order` VALUES (40, 100040, 100005, 3, 5.00, 5, 5, 0, NULL, '2019-08-29 00:00:00');
INSERT INTO `order` VALUES (41, 100041, 100028, 3, 3.00, 3, 3, 0, NULL, '2019-07-22 00:00:00');
INSERT INTO `order` VALUES (42, 100042, 100002, 3, 5.00, 5, 5, 0, NULL, '2019-11-29 00:00:00');
INSERT INTO `order` VALUES (43, 100043, 100004, 3, 3.00, 3, 3, 1, NULL, '2019-11-29 00:00:00');
INSERT INTO `order` VALUES (44, 100044, 100006, 3, 5.00, 5, 5, 0, NULL, '2019-11-29 00:00:00');
INSERT INTO `order` VALUES (45, 100045, 100005, 3, 50.00, 50, 50, 0, '', '2019-11-29 13:43:59');
INSERT INTO `order` VALUES (46, 100046, 100003, 3, 25.00, 50, 50, 0, '赵兴', '2019-06-29 13:44:02');
INSERT INTO `order` VALUES (95, 46, 100028, 3, 0.00, 1000, 1000, 1, '开始开始办卡', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (96, 56541179, 999, NULL, 203.50, 300, NULL, 1, '', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (97, 56541180, 100030, 3, 0.00, 1000, 1000, 1, '斯柯达复活节办卡', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (98, 56902400, 999, NULL, 203.50, 300, NULL, 1, '', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (99, 56902401, 100031, 2, 0.00, 100, 100, 1, '啊手办卡', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (100, 56902402, 100028, 3, 0.00, 1000, 1000, 1, '测试办卡', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (101, 56902403, 100029, 5, 0.00, 100000, 100000, 1, '会员登记完整测试办卡', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (102, 8564930, 100029, NULL, 200.00, 200, NULL, 1, '', '2019-12-05 00:00:00');
INSERT INTO `order` VALUES (103, 4641919, 999, NULL, 10.00, 12, NULL, 0, '商品消费', '2019-12-06 00:00:00');
INSERT INTO `order` VALUES (104, 5058413, 100002, NULL, 3.00, 3, NULL, 1, '', '2019-12-06 00:00:00');
INSERT INTO `order` VALUES (105, 5058414, 100030, 3, 0.00, 1000, 1000, 1, '会员姓名游客办卡', '2019-12-06 00:00:00');
INSERT INTO `order` VALUES (106, 5300746, 100030, NULL, 200.00, 200, NULL, 1, '', '2019-12-06 00:00:00');
INSERT INTO `order` VALUES (107, 890473, 0, 0, 40.00, 40, NULL, 1, '', '2019-12-06 00:00:00');
INSERT INTO `order` VALUES (108, 890474, 100028, 3, 0.00, 1000, 1000, 1, '登记新会员办卡', '2019-12-09 00:00:00');
INSERT INTO `order` VALUES (109, 9840385, 100028, NULL, 5.00, 5, NULL, 1, '', '2019-12-09 00:00:00');
INSERT INTO `order` VALUES (110, 9931289, 999, NULL, 5.00, 20, NULL, 1, '', '2019-12-09 00:00:00');

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NULL DEFAULT NULL,
  `goodsId` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `goodsNumber` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `goodsCredit` int(11) NULL DEFAULT NULL COMMENT '单个商品积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES (1, 100001, 10001, 5, 5);
INSERT INTO `orderinfo` VALUES (2, 100002, 10002, 6, 6);

-- ----------------------------
-- Table structure for rechargerecord
-- ----------------------------
DROP TABLE IF EXISTS `rechargerecord`;
CREATE TABLE `rechargerecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardId` int(11) NULL DEFAULT NULL,
  `rechargeAmount` double NULL DEFAULT NULL COMMENT '充值金额',
  `afterAmount` double NULL DEFAULT NULL COMMENT '充值后卡余额',
  `beforeAmount` double NULL DEFAULT NULL COMMENT '充值前卡余额',
  `ruleId` int(11) NULL DEFAULT NULL COMMENT '充值规则',
  `createdTime` datetime(0) NULL DEFAULT NULL COMMENT '充值时间',
  `staffId` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `momo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充值记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rechargerecord
-- ----------------------------
INSERT INTO `rechargerecord` VALUES (1, 100001, 1000, 3220, 4740, 1, '2019-09-11 10:13:22', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (2, 100001, 2000, 4740, 7260, 1, '2019-12-07 10:16:49', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (3, 100001, 4000, 7260, 11780, 1, '2019-12-22 10:17:54', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (4, 100002, 10000, 5600, 16120, 1, '2019-08-13 10:19:02', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (5, 100002, 10000, 16120, 26640, 1, '2019-06-01 10:19:56', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (6, 100002, 10000, 26640, 37160, 1, '2019-06-04 10:26:51', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (7, 100003, 8000, 5120, 13640, 1, '2019-05-16 10:28:18', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (8, 100003, 10000, 13640, 24160, 1, '2019-04-20 10:29:04', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (9, 100003, 20000, 24160, 44680, 1, '2019-02-13 10:30:02', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (10, 100004, 50000, 6500, 57020, 1, '2019-01-22 10:31:11', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (11, 100005, 60000, 8500, 69020, 1, '2019-06-20 10:32:36', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (12, 100004, 25000, 57020, 82540, 1, '2019-12-01 10:36:01', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (13, 100001, 100, 11781, 11780, 1, '2019-07-01 17:29:14', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (14, 100004, 100, 82541, 82540, 1, '2019-12-03 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (15, 100006, 100, 2521, 2520, 1, '2019-12-03 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (16, 100001, 10, 12404, 12400, 4, '2019-12-03 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (17, 100001, 1000, 12398, 12395, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (18, 100001, 25, 13478, 13475, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (19, 100001, 100, 13583, 13580, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (20, 100001, 20, 13763, 13760, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (21, 100001, 20, 13863, 13860, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (22, 100011, 1000, 1005, 1000, 5, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (23, 100012, 50, 1003, 1000, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (24, 100012, 100, 1133, 1130, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (25, 100013, 100, 1003, 1000, 3, '2019-12-04 00:00:00', 20190004, NULL);
INSERT INTO `rechargerecord` VALUES (26, 100012, 10, 1310, 1310, 0, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (27, 100012, 10, 1323, 1320, 3, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (28, 100006, 20000, 3146, 3140, 6, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (29, 100006, 10000, 43146, 43140, 6, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (30, 100007, 10000, 1006, 1000, 6, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (31, 100035, 100, 100003, 100000, 3, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (32, 999, 100, 999400, 999400, 0, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (33, 100029, 100000, 1, 0, 1, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (34, 100029, 100, 100000, 100000, 0, '2019-12-05 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (35, 100002, 100, 34734, 34727, 7, '2019-12-06 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (36, 100030, 1000, 1, 0, 1, '2019-12-06 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (37, 100014, 100, 1003, 1000, 3, '2019-12-06 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (38, 100028, 1000, 1, 0, 1, '2019-12-09 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (39, 100028, 100, 1003, 1000, 3, '2019-12-09 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (40, 100028, 100, 1188, 1180, 8, '2019-12-09 00:00:00', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (41, 100004, 83160, 4355, 4353, 2, '2019-12-30 22:30:31', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (42, 100004, 167820, 326, 324, 2, '2019-12-30 22:31:02', 20190001, NULL);
INSERT INTO `rechargerecord` VALUES (43, 100005, 69020, 322, 321, 1, '2019-12-31 08:26:11', 20190001, NULL);

-- ----------------------------
-- Table structure for rechargerule
-- ----------------------------
DROP TABLE IF EXISTS `rechargerule`;
CREATE TABLE `rechargerule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充值规则名字',
  `coefficient` double(3, 2) NULL DEFAULT NULL COMMENT '充值系数',
  `createdTime` date NULL DEFAULT NULL COMMENT '开始时间',
  `endTime` date NULL DEFAULT NULL COMMENT '结束时间',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `startMoney` int(255) NULL DEFAULT NULL COMMENT '起充金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充值规则' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rechargerule
-- ----------------------------
INSERT INTO `rechargerule` VALUES (1, '冲100送520', 5.20, '2019-11-26', '2019-11-30', 1, 100);
INSERT INTO `rechargerule` VALUES (2, '冲200送1500', 7.50, '2019-11-29', '2019-11-30', 1, 200);
INSERT INTO `rechargerule` VALUES (3, '冲100送80', 0.80, '2019-12-02', '2019-12-28', 1, 100);
INSERT INTO `rechargerule` VALUES (4, '冲10 块送5块', 0.50, '2019-12-03', '2019-12-20', 0, 10);
INSERT INTO `rechargerule` VALUES (5, '充1000送500', 0.50, '2019-12-04', '2019-12-31', 1, 1000);
INSERT INTO `rechargerule` VALUES (6, '充10000送10000', 1.00, '2019-12-05', '2019-12-30', 0, 10000);
INSERT INTO `rechargerule` VALUES (7, '充值规则测试冲100送20', 0.20, '2019-12-06', '2019-12-21', 1, 100);
INSERT INTO `rechargerule` VALUES (8, '冲100送50', 0.50, '2019-12-09', '2019-12-31', 1, 100);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `resource_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `identity` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一标示',
  `momo` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `pid` int(9) NULL DEFAULT NULL COMMENT '父类id',
  `status` int(20) NULL DEFAULT NULL COMMENT '状态',
  `sort` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '排序',
  `resource_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '资源类型',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '权限管理', NULL, 'role', '权限管理', NULL, 0, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (2, '商品管理', NULL, 'goods', '商品管理', NULL, 0, 1, '2', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (3, '会员管理', NULL, 'member', '会员管理', NULL, 0, 1, '3', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (4, '充值规则管理', NULL, 'recharge', '充值规则管理', NULL, 0, 1, '4', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (5, '消费收银', NULL, 'order', '消费收银', NULL, 0, 1, '5', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (6, '公告新闻管理', '', 'news', '公告新闻管理', NULL, 0, 1, '6', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (7, '统计', NULL, 'count', '统计', NULL, 0, 1, '8', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (8, '权限管理', NULL, 'role1', '权限管理', NULL, 1, 0, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (9, '商品列表', '/goods/goodsList.html', 'goods1', '商品列表', NULL, 2, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (10, '商品增加', '/goods/addGoods.html', 'goods2', '商品增加', NULL, 2, 1, '2', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (11, '商品分类', '/category/categoryList.html', 'goods3', '商品分类', NULL, 2, 1, '3', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (12, '会员登记', '/user/userAdd.html', 'member1', '会员登记', NULL, 3, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (13, '会员列表', '/user/user.html', 'member2', '会员列表', NULL, 3, 1, '2', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (14, '会员充值', '/card/userCardRecharge.html', 'member3', '会员充值', NULL, 3, 1, '3', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (15, '会员刷卡', '/card/userSlotCard.html', 'member4', '会员刷卡', NULL, 3, 1, '4', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (16, '充值规则', '/rule/ruleList.html', 'recharge1', '充值规则', NULL, 4, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (17, '商品消费', '/oder/Consumer.html', 'order1', '商品消费', NULL, 5, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (18, '消费订单', '/oder/oderList.html', 'order2', '消费订单', NULL, 5, 1, '2', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (19, '公告/新闻列表', '/news/newMain.html', 'news1', '公告/新闻列表', NULL, 6, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (20, '充值统计', '/census/rechargeCensus.html', 'count1', '充值统计', NULL, 7, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (21, '销售统计', '/census/orderCensus.html', 'count2', '销售统计', NULL, 7, 1, '2', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (22, '会员增长趋势', '/census/userCensus.html', 'count3', '会员增长趋势', NULL, 7, 1, '3', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (25, '员工管理', NULL, 'staff', '员工管理', NULL, 0, 1, '7', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (26, '职位管理', '/role/roleList.html', 'role2', '职位管理', NULL, 1, 1, '1', NULL, '2019-11-20 16:06:45');
INSERT INTO `resource` VALUES (27, '员工列表', '/staff/staffList.html', 'staff2', '员工列表', NULL, 25, 1, '2', NULL, '2019-11-20 16:06:45');

-- ----------------------------
-- Table structure for resource_role
-- ----------------------------
DROP TABLE IF EXISTS `resource_role`;
CREATE TABLE `resource_role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '角色资源id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  `role_id` bigint(19) NOT NULL DEFAULT 1 COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 403 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resource_role
-- ----------------------------
INSERT INTO `resource_role` VALUES (264, 2, 3);
INSERT INTO `resource_role` VALUES (265, 9, 3);
INSERT INTO `resource_role` VALUES (266, 10, 3);
INSERT INTO `resource_role` VALUES (267, 11, 3);
INSERT INTO `resource_role` VALUES (268, 3, 3);
INSERT INTO `resource_role` VALUES (269, 12, 3);
INSERT INTO `resource_role` VALUES (270, 13, 3);
INSERT INTO `resource_role` VALUES (271, 14, 3);
INSERT INTO `resource_role` VALUES (272, 15, 3);
INSERT INTO `resource_role` VALUES (273, 4, 3);
INSERT INTO `resource_role` VALUES (274, 16, 3);
INSERT INTO `resource_role` VALUES (275, 7, 3);
INSERT INTO `resource_role` VALUES (276, 20, 3);
INSERT INTO `resource_role` VALUES (277, 21, 3);
INSERT INTO `resource_role` VALUES (278, 22, 3);
INSERT INTO `resource_role` VALUES (279, 2, 5);
INSERT INTO `resource_role` VALUES (280, 9, 5);
INSERT INTO `resource_role` VALUES (281, 10, 5);
INSERT INTO `resource_role` VALUES (282, 11, 5);
INSERT INTO `resource_role` VALUES (283, 3, 5);
INSERT INTO `resource_role` VALUES (284, 12, 5);
INSERT INTO `resource_role` VALUES (285, 13, 5);
INSERT INTO `resource_role` VALUES (286, 14, 5);
INSERT INTO `resource_role` VALUES (287, 15, 5);
INSERT INTO `resource_role` VALUES (288, 4, 5);
INSERT INTO `resource_role` VALUES (289, 16, 5);
INSERT INTO `resource_role` VALUES (290, 5, 5);
INSERT INTO `resource_role` VALUES (291, 17, 5);
INSERT INTO `resource_role` VALUES (292, 18, 5);
INSERT INTO `resource_role` VALUES (293, 6, 5);
INSERT INTO `resource_role` VALUES (294, 19, 5);
INSERT INTO `resource_role` VALUES (295, 7, 5);
INSERT INTO `resource_role` VALUES (296, 20, 5);
INSERT INTO `resource_role` VALUES (297, 21, 5);
INSERT INTO `resource_role` VALUES (298, 22, 5);
INSERT INTO `resource_role` VALUES (299, 25, 5);
INSERT INTO `resource_role` VALUES (300, 27, 5);
INSERT INTO `resource_role` VALUES (312, 2, 4);
INSERT INTO `resource_role` VALUES (313, 9, 4);
INSERT INTO `resource_role` VALUES (314, 10, 4);
INSERT INTO `resource_role` VALUES (315, 11, 4);
INSERT INTO `resource_role` VALUES (316, 5, 4);
INSERT INTO `resource_role` VALUES (317, 17, 4);
INSERT INTO `resource_role` VALUES (318, 18, 4);
INSERT INTO `resource_role` VALUES (319, 7, 4);
INSERT INTO `resource_role` VALUES (320, 20, 4);
INSERT INTO `resource_role` VALUES (321, 21, 4);
INSERT INTO `resource_role` VALUES (322, 22, 4);
INSERT INTO `resource_role` VALUES (323, 1, 6);
INSERT INTO `resource_role` VALUES (324, 26, 6);
INSERT INTO `resource_role` VALUES (325, 3, 6);
INSERT INTO `resource_role` VALUES (326, 12, 6);
INSERT INTO `resource_role` VALUES (327, 13, 6);
INSERT INTO `resource_role` VALUES (328, 14, 6);
INSERT INTO `resource_role` VALUES (329, 15, 6);
INSERT INTO `resource_role` VALUES (330, 25, 6);
INSERT INTO `resource_role` VALUES (331, 27, 6);
INSERT INTO `resource_role` VALUES (352, 1, 1);
INSERT INTO `resource_role` VALUES (353, 26, 1);
INSERT INTO `resource_role` VALUES (354, 2, 1);
INSERT INTO `resource_role` VALUES (355, 9, 1);
INSERT INTO `resource_role` VALUES (356, 10, 1);
INSERT INTO `resource_role` VALUES (357, 11, 1);
INSERT INTO `resource_role` VALUES (358, 3, 1);
INSERT INTO `resource_role` VALUES (359, 12, 1);
INSERT INTO `resource_role` VALUES (360, 13, 1);
INSERT INTO `resource_role` VALUES (361, 14, 1);
INSERT INTO `resource_role` VALUES (362, 15, 1);
INSERT INTO `resource_role` VALUES (363, 4, 1);
INSERT INTO `resource_role` VALUES (364, 16, 1);
INSERT INTO `resource_role` VALUES (365, 5, 1);
INSERT INTO `resource_role` VALUES (366, 17, 1);
INSERT INTO `resource_role` VALUES (367, 18, 1);
INSERT INTO `resource_role` VALUES (368, 6, 1);
INSERT INTO `resource_role` VALUES (369, 19, 1);
INSERT INTO `resource_role` VALUES (370, 7, 1);
INSERT INTO `resource_role` VALUES (371, 20, 1);
INSERT INTO `resource_role` VALUES (372, 21, 1);
INSERT INTO `resource_role` VALUES (373, 22, 1);
INSERT INTO `resource_role` VALUES (374, 25, 1);
INSERT INTO `resource_role` VALUES (375, 27, 1);
INSERT INTO `resource_role` VALUES (376, 2, 2);
INSERT INTO `resource_role` VALUES (377, 9, 2);
INSERT INTO `resource_role` VALUES (378, 10, 2);
INSERT INTO `resource_role` VALUES (379, 11, 2);
INSERT INTO `resource_role` VALUES (380, 5, 2);
INSERT INTO `resource_role` VALUES (381, 17, 2);
INSERT INTO `resource_role` VALUES (382, 18, 2);
INSERT INTO `resource_role` VALUES (383, 1, 7);
INSERT INTO `resource_role` VALUES (384, 26, 7);
INSERT INTO `resource_role` VALUES (385, 2, 7);
INSERT INTO `resource_role` VALUES (386, 9, 7);
INSERT INTO `resource_role` VALUES (387, 10, 7);
INSERT INTO `resource_role` VALUES (388, 11, 7);
INSERT INTO `resource_role` VALUES (389, 3, 7);
INSERT INTO `resource_role` VALUES (390, 12, 7);
INSERT INTO `resource_role` VALUES (391, 13, 7);
INSERT INTO `resource_role` VALUES (392, 14, 7);
INSERT INTO `resource_role` VALUES (393, 15, 7);
INSERT INTO `resource_role` VALUES (394, 4, 7);
INSERT INTO `resource_role` VALUES (395, 16, 7);
INSERT INTO `resource_role` VALUES (396, 5, 7);
INSERT INTO `resource_role` VALUES (397, 17, 7);
INSERT INTO `resource_role` VALUES (398, 18, 7);
INSERT INTO `resource_role` VALUES (399, 6, 7);
INSERT INTO `resource_role` VALUES (400, 19, 7);
INSERT INTO `resource_role` VALUES (401, 25, 7);
INSERT INTO `resource_role` VALUES (402, 27, 7);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `grade` int(15) NULL DEFAULT NULL COMMENT '角色等级',
  `description` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int(5) NULL DEFAULT NULL COMMENT '角色状态(1启用 0禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 1, '管理员', 1);
INSERT INTO `role` VALUES (2, '吧台', 2, '小卖部', 1);
INSERT INTO `role` VALUES (3, '前台', 3, '前台', 1);
INSERT INTO `role` VALUES (4, '收银员', 4, '收银员', 1);
INSERT INTO `role` VALUES (5, '店长', 5, '店长', 1);
INSERT INTO `role` VALUES (7, '新增职位', NULL, '新增的职位', 1);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffId` int(11) NULL DEFAULT NULL COMMENT '员工ID',
  `staffName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工电话',
  `idCard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工身份证号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工地址',
  `createdTime` date NULL DEFAULT NULL COMMENT '入职日期',
  `status` int(11) NULL DEFAULT NULL COMMENT '员工状态(1.在职 2.离职 3.黑名单)',
  `roleId` int(11) NULL DEFAULT NULL,
  `momo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `enable` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 20190001, '无所不能的鲁老板', '123123', '13338666666', '411622199812121313', '123123', '2019-11-20', 1, 1, '', 1);
INSERT INTO `staff` VALUES (2, 20190002, '一知半解的康老板', '123123', '13526724768', '410183199808047033', '123123', '2019-11-20', 1, 3, '', 1);
INSERT INTO `staff` VALUES (3, 20190003, '邱金', '123123', '15560313872', '410182199703036031', '河南郑州', '2019-11-20', 1, 2, '', 1);
INSERT INTO `staff` VALUES (4, 20190004, '王星华', '123123', '15560313421', '410182198504061564', '河南郑州', '2019-11-20', 1, 2, '', 1);
INSERT INTO `staff` VALUES (81, 20190005, '测试员工', '123123', '13526724768', '410183199808047033', '员工地址', '2019-12-09', 1, 7, '新增职位', NULL);

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(0:禁用  1：启用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '单位表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of unit
-- ----------------------------
INSERT INTO `unit` VALUES (1, '瓶', 1);
INSERT INTO `unit` VALUES (2, '桶', 1);
INSERT INTO `unit` VALUES (3, '节', 1);
INSERT INTO `unit` VALUES (4, '支', 1);
INSERT INTO `unit` VALUES (5, '件', 0);
INSERT INTO `unit` VALUES (6, '双', 1);
INSERT INTO `unit` VALUES (7, '个', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `status` int(1) NULL DEFAULT NULL COMMENT '用户状态',
  `idCard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户身份证号码',
  `birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(1) NULL DEFAULT NULL COMMENT '用户性别(1男2女)',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地区',
  `createdTime` datetime(0) NULL DEFAULT NULL,
  `cardId` int(11) NOT NULL,
  `momo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `staffId` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1090001, '王鹏程', '13148285409', 1, '412722199811117313', '1111', 1, '河南郑州', '河南郑州', '2019-11-22 00:00:00', 100001, NULL, NULL);
INSERT INTO `user` VALUES (2, 1090002, '鲁天乐', '13141112222', 1, '410622199804255843', '0425', 1, '河南郑州', '河南郑州', '2019-06-22 00:00:00', 100002, NULL, NULL);
INSERT INTO `user` VALUES (3, 1090003, '史博士', '15538631462', 1, '412722199805064561', '0506', 1, '河南郑州', '河南郑州', '2019-11-22 00:00:00', 100003, NULL, NULL);
INSERT INTO `user` VALUES (4, 1090004, '李文艺', '计划里', 1, '411222199611043638', '1104', 1, '河南郑州', '河南郑州', '2019-06-30 00:00:00', 100004, NULL, NULL);
INSERT INTO `user` VALUES (5, 1090005, '赵六', '18538384381', 1, '410622199006084512', '0608', 1, '河南郑州', '河南郑州', '2019-12-05 00:00:00', 100005, NULL, NULL);
INSERT INTO `user` VALUES (6, 1090006, '冯秋燕', '16613142556', 1, '413711199406075412', '0607', 1, '河南郑州', '河南郑州', '2019-11-22 00:00:00', 100006, NULL, NULL);
INSERT INTO `user` VALUES (7, 1090007, '唐悠悠', '15538656567', 1, '410722198501095423', '0109', 1, '郑州金水区', '河南郑州', '2019-11-13 00:00:00', 100007, NULL, NULL);
INSERT INTO `user` VALUES (8, 1090007, '刘金', '13193606668', 1, '410722199511115467', '1111', 1, '郑州金水区', '河南郑州', '2019-11-14 00:00:00', 100008, NULL, NULL);
INSERT INTO `user` VALUES (9, 1090009, '王八蛋', '17773177777', 2, '411633199511308524', '1130', 1, '郑州金水区', '河南郑州', '2019-11-26 00:00:00', 100009, NULL, NULL);
INSERT INTO `user` VALUES (10, 1090010, '文章', '15196541586', 1, '411652198407121543', NULL, 1, '河南郑州', '河南郑州', '2019-11-27 00:00:00', 100010, NULL, NULL);
INSERT INTO `user` VALUES (11, 1090011, '武大郎', '15538656567', 1, '411111222444466664', NULL, 1, '河南郑州', '河南郑州', '2019-03-20 00:00:00', 100011, NULL, NULL);
INSERT INTO `user` VALUES (12, 1090012, '郑朝山', '13255421654', 1, '410102199003072735', NULL, 1, '郑州金水区', '河南郑州', '2019-10-21 00:00:00', 100012, NULL, NULL);
INSERT INTO `user` VALUES (13, 1090013, '张锋', '15132541568', 1, '410102199003075530', NULL, 1, '郑州金水区', '河南郑州', '2019-07-27 00:00:00', 100013, NULL, NULL);
INSERT INTO `user` VALUES (14, 1090014, '刘益智', '13215471563', 1, '410102199003077712', NULL, 1, '郑州金水区', '河南郑州', '2019-11-26 00:00:00', 100014, NULL, NULL);
INSERT INTO `user` VALUES (15, 1090015, '侯静', '18532568462', 1, '410102199003073017', NULL, 1, '郑州金水区', '河南郑州', '2019-03-20 00:00:00', 100015, NULL, NULL);
INSERT INTO `user` VALUES (16, 1090016, '田帅', '15715369853', 2, '410102199003073578', NULL, 1, '郑州金水区', '河南郑州', '2019-03-20 00:00:00', 100016, NULL, NULL);
INSERT INTO `user` VALUES (17, 1090017, '刘军', '13615856324', 1, '410102199003073615', NULL, 1, '郑州金水区', '河南郑州', '2019-11-05 00:00:00', 100017, NULL, NULL);
INSERT INTO `user` VALUES (18, 1090018, '马昂', '17765821364', 1, '410102199003074693', NULL, 1, '郑州金水区', '河南郑州', '2019-03-20 00:00:00', 100018, NULL, NULL);
INSERT INTO `user` VALUES (19, 1090019, '邓树', '17136943582', 1, '410102199003073690', NULL, 1, '郑州金水区', '河南郑州', '2019-07-27 00:00:00', 100019, NULL, NULL);
INSERT INTO `user` VALUES (20, 1090020, '党晨', '15935261257', 1, '410102199003079275', NULL, 1, '郑州金水区', '河南郑州', '2019-11-27 00:00:00', 100020, NULL, NULL);
INSERT INTO `user` VALUES (21, 1090021, '孙一发', '15248632587', 1, '410102199003078838', NULL, 1, '郑州金水区', '河南郑州', '2019-07-27 00:00:00', 100021, NULL, NULL);
INSERT INTO `user` VALUES (22, 1090022, '王乾坤', '13146324581', 2, '410102199504081564', NULL, 1, '郑州金水区', '郑州金水区', '2019-03-20 00:00:00', 100022, NULL, NULL);
INSERT INTO `user` VALUES (23, 1090023, '远离', '13215846258', 1, '410102199306021543', NULL, 1, '郑州金水区', '河南郑州', '2019-11-14 00:00:00', 100023, NULL, NULL);
INSERT INTO `user` VALUES (24, 1090024, '李丽', '13315843658', 1, '410102199610151324', NULL, 1, '郑州金水区', '河南郑州', '2019-11-27 00:00:00', 100024, NULL, NULL);
INSERT INTO `user` VALUES (25, 1090025, '李志', '15939413584', 1, '411712199812241658', NULL, 1, '河南郑州', '郑州金水区', '2019-03-20 00:00:00', 100025, NULL, NULL);
INSERT INTO `user` VALUES (47, 999, '游客卡', '111', 1, '111', NULL, 1, '游客卡', '游客卡', '2019-12-05 19:39:19', 999, NULL, NULL);
INSERT INTO `user` VALUES (55, 1090026, '王小二', '13526724768', 1, '410183199808047033', NULL, 1, '河南郑州', '郑州二七区', '2019-12-05 22:02:40', 100026, NULL, NULL);
INSERT INTO `user` VALUES (64, 1090027, '开始', '13526724768', 1, '410183199808047033', NULL, 0, '123123', NULL, '2019-12-05 00:00:00', 100027, '123123', NULL);
INSERT INTO `user` VALUES (73, 1090028, '登记新会员', '13526724768', 1, '410183199808047011', NULL, 1, '二七区', NULL, '2019-12-09 00:00:00', 100028, '新增会员', NULL);

SET FOREIGN_KEY_CHECKS = 1;
