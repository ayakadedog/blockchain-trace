-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: echarts
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `aid` int DEFAULT NULL,
  `uid` int DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`aid`, `uid`, `name`, `address`, `phone`) VALUES (1,3,'猫猫','快乐星球1号','13435970888'),(4,3,'阳光男孩','阿卡姆精神病院','13435970333'),(5,1,'猫猫','猫猫家','2315465254'),(6,2,'快乐吃手手','快乐家族','12358748445');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookID` smallint unsigned DEFAULT NULL,
  `bookNumber` int DEFAULT NULL,
  `bookName` varchar(40) DEFAULT NULL,
  `author` varchar(40) DEFAULT NULL,
  `press` varchar(40) DEFAULT NULL,
  `cover` varchar(1000) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `type` varchar(12) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`bookID`, `bookNumber`, `bookName`, `author`, `press`, `cover`, `price`, `type`, `content`, `stock`, `id`) VALUES (1,978702017,'神圣婚姻','徐坤','人民文学出版社','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',46.6,'小说','这是一部直面新时代现场的长篇小说。徐坤以宏阔的视野、锐利的笔触，直接进入新时代历经剧变、纷繁复杂的现场',10,1753345646831878146),(2,978752021,'王朝之痒','坏土豆','中国大百科全书出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',39,'历史','历史大v坏土豆首部作品！每个王朝都有自己的疥癣之疾，一旦挠破，血流成河。一本书读懂王朝内幕，洞悉历史的底层逻辑！翻开这本书，读懂困扰你多年的历史难题！限量3000套明信片，随机发货',4,1753345646831878146),(3,978711559,'手机变单反','卷毛佟','人民邮电出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',49,'艺术','卷毛佟手机摄影书籍入门教程，拍照技巧后期修图短视频创作，卷毛佟手机摄影技巧倾情奉献，带你解锁手机摄影隐藏功能；从前期到后期，用手机记录美好生活！附赠教学视频',5,1753345646831878146),(4,978755909,'万万没想到','杰罗姆?卡米尔','新疆青少年出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',108,'趣味读物','不看到最后，不知道结局！法国幽默推理绘本，沉浸式烧脑体验，培养孩子的思辨力和推理能力！',4,1753345646831878146),(5,978757360,'狂飙','徐纪周?朱俊懿?白文君','青岛出版社','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',34,'小说','官方授权无删减版，书中更赠神秘剧照！真实还原扫黑除恶第一线，以横跨20年的群像叙事方式全景式地展现时代变迁下的黑白较量与复杂人性。',4,1753345646831878146),(6,978754116,'通俗小说','仁科','四川文艺出版社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',36.8,'小说','仁科新书，当当专享演出票根书签，“城中村存在主义文学”蟑螂一般的生命力，蝴蝶一样的诗意',5,1753345646831878146),(7,978755965,'无人知晓','田中雪莱','北京联合出版有限公司','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',33,'小说','豆瓣电影评分9.1，是枝裕和ZUI高分代表作。根据真实事件改编，故事就像一根刺，深深扎进你我内心。',5,1753345646831878146),(8,572602696,'没药花园案件：罪恶追踪','没药花园?著,博集天卷?出品','湖南文艺出版社','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',24,'小说','百万粉丝追更的悬疑品牌【没药花园】真实案件系列书第二部！搜集公开的庭审资料和媒体报道，重新讲述10个轰动的真实案件。追踪真实罪恶，重启案件现场。',4,1753345646831878146),(9,702012013,'那不勒斯四部曲','[意]埃莱娜·费兰特','人民文学出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',150,'小说','童书妈妈三川玲推荐！当当独家函套版！我的天才女友+新名字的故事+离开的,留下的+失踪的孩子。“我的整个生命，只是一场为了提升社会地位的低俗斗争”—— 埃莱娜 费兰',5,1753345646831878146),(10,870201669,'如雪如山','张天翼','人民文学出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',45.8,'小说','《如雪如山》是青年实力作家张天翼女性现实题材小说集。作者以敏感善察的心思和细腻锋利的笔触，通过七位女性主人公的人生断面，拼凑出一部女性视角下的“人世间”的故事',5,1753345646831878146),(11,853218256,'关于告别的一切','路内','上海文艺出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',58.5,'小说','1985——2019，一对父子破碎的爱情之旅，一场被时代催迫的人生悲喜剧，又一部典型的“路式”小说',5,1753345646831878146),(12,870201722,'亲爱的蜂蜜','笛安','人民文学出版社','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',46.6,'小说','孩子就是世界的温情谜语，这些谜语中也藏有答案 爱，就是不怕，笛安给这个坚硬世界的一点温柔 没有轰轰烈烈的爱恋，有的是过尽千帆的珍重 即使有各自的过往，只要遇见，便是新的序章。',5,1753345646831878146),(13,875531172,'天干物燥，小心鬼狐：中国鬼狐妖物百谭','蒲松龄、袁枚原著，黄凤娇编译','巴蜀书社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',29.9,'小说','【赠百妖谱+印签】天干物燥小心鬼狐 蒲松龄袁牧著100篇中国鬼狐奇谭 聊斋志异子不语阅微草堂笔记搜神记山海经古典文学志怪小说',4,1753345646831878146),(14,875596475,'请照顾好我妈妈','[韩]申京淑著 ;?薛舟,?徐丽红译','北京联合出版有限公司','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',34.5,'小说','她为家人奉献了一生，却没有人了解她是谁。缔造300万册畅销奇迹的韩国文学神话，获第五届英仕曼亚洲文学奖。和《你好，李焕英》一样，以子女的视角重新认识母亲。',5,1753345646831878146),(15,875492770,'果果的婚事','未夕?著；星文文化?出品','长江出版社','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',24.9,'小说','心向往之的幸福生活，感同身受的婚姻真相，这是果果的婚事，也是发生在我们每一人身上的日常悲欢。',4,1753345646831878146),(16,875169167,'民调局异闻录','耳东水寿','华龄出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',109.9,'小说','腾讯热播动画《民调局异闻录》原著小说！悬疑大神耳东水寿作品！一个专门调查各种离奇、神秘事件的组织以及他们所面对的世界！南派三叔、辰东、莲蓬、庚新 激赏推荐！',5,1753345646831878146),(17,871213842,'手机摄影技法大全','先锋影像','电子工业出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',9.9,'艺术','帮你解决所有拍摄难题，相信自己！一部手机，你可以拍出令大家赞叹的大片。人像、街拍、风光、建筑、花卉、美食常用题材一网打尽，让你掌握摄影构图、用光的理论引来无数人的点赞。',5,1753345646831878146),(18,872011896,'实战表演','刘天池，果麦文化?出品','天津人民出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',29.9,'艺术','提问题、给方法、讲案例，切实解决表演困惑。比《演员自我修养》更适合当下演员的困境。让演员通过专业有效的训练，在艺术道路上成为更好的自己；让大众通过习得表演的能力，在人生之路上成就更好的自己。果麦出品',3,1753345646831878146),(19,871030639,'歌唱入门图解','周咏梅','人民音乐出版社','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',26,'艺术','人人能懂的歌唱课',5,1753345646831878146),(20,875115760,'如果人生是一张精选辑——网易云音乐乐评','网易云音乐','人民日报出版社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',39.8,'艺术','有趣的灵魂在这里相遇',5,1753345646831878146),(21,875339703,'编剧的艺术','[匈]拉约什·埃格里，译者?陈磊，果麦文化?出品','浙江文艺出版社','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',29.5,'艺术','好剧本有三大条件：一个清晰的前提，具备三维度的角色，升级和有预示的冲突。职业编剧的宝藏工具书，2023经典重译。果麦出品',4,1753345646831878146),(22,871080478,'图说中国绘画史','〔美〕高居翰（James?Cahill）','生活.读书.新知三联书店','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',80.2,'艺术','100幅精选画作，呈现一部中国绘画的历史，高居翰教授的早年成名之作。',5,1753345646831878146),(23,875217489,'西部往事：莱昂内谈电影','塞尔吉奥·莱昂内','中信出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',58.5,'艺术','意大利国宝级导演莱昂内与十五年挚友对谈录',5,1753345646831878146),(24,872201297,'现代画家、雕塑家和建筑师传','〔意〕乔凡尼·彼得罗·贝洛里著?吴忌译','四川人民出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',120.9,'艺术','16、17世纪著名艺术家的传记',5,1753345646831878146),(25,295130071,'一个人的意大利[精装大本]','须臾?著绘','中国轻工业出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',49,'艺术','新锐青年插画师须臾个人作品集。在明亮的笔触与温暖的故事里，带你穿梭中世纪的古老城市， 领略意大利饱满的生活方式。',5,1753345646831878146),(26,295130212,'去剑桥学插画[精装大本]','温艾凝?著绘','中国轻工业出版社','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',49,'艺术','青年绘本艺术家温艾凝随笔速写插画集。在童话般纯粹美好的色彩里，感受浓郁的英伦时光。',5,1753345646831878146),(27,875520388,'别再问我什么是嘻哈②','[日]?长谷川町藏?大和田俊之?著 ，耳田?译','上海社会科学院出版社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',46.7,'艺术','万众期待的嘻哈入门续集终于登场',5,1753345646831878146),(28,875520306,'别再问我什么是嘻哈①','[日]?长谷川町藏，大和田俊之?著 ，耳田?译','上海社会科学院出版社','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',46.7,'艺术','一部划时代的嘻哈入门书',5,1753345646831878146),(29,875501802,'和唐朝诗人握个手','急脚大师?著?捧读文化?出品','南方出版社','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',29,'历史','赠唐朝科举知识折页。爆款图书《和古代学霸握个手》作者新作。幽默的文字搭配50多幅漫画，剧场感十足。像看一场唐朝诗人生活脱口秀。',4,1753345646831878146),(30,875086784,'历史的温度','张玮','中信出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',51,'历史','历史书上的某个名字、某段话，它的背后到底隐藏了什么？',5,1753345646831878146),(31,875722435,'权力的面孔 一幅中央帝国的权力图像','张宏杰?著，磨铁文化?出品','浙江教育出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',56.1,'历史','知名历史学者张宏杰继《曾国藩传》后通俗讲史力作。一幅中央帝国的权力图像，以心理分析式的讲史，剖析复杂人性，解读中国传统社会潜规则、权力运作法则和背后的行为逻辑',5,1753345646831878146),(32,875598531,'千面宋人：传世书信里的士大夫','仇春霞','广西师范大学出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',64.8,'历史','一个卸了妆的宋朝将会是怎样？一本书窥探宋人笔迹里的密码。这不是史书里被后人记载的历史，而是他们亲笔写下的历史。走进60余位名士、120余封高清珍贵传世书信背后那些关于帝王将相、忠臣贼子、贩夫走卒的故事',4,1753345646831878146),(33,875217100,'历史的温度4','张玮','中信出版社','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',51,'历史','那些执念与信念、理想与梦想',5,1753345646831878146),(34,875455726,'殷商六百年','柯胜雨','天地出版社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',63.3,'历史','殷周革命与青铜王朝的兴衰（跨越夏商周三代的文明演化史。生动再现贞人群体、妇好、伊尹等殷商历史人物。）全景讲述华夏文明起源时期的历史细节，带你回到那个神秘的殷商时代。',4,1753345646831878146),(35,875217506,'世界这么大，带你去看看','林楚方','中信出版社','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',34,'历史','带你看懂不容错过的45个世界知名地标',0,1753345646831878146),(36,875411659,'神奇的北魏','张小泱?著，酷威文化?出品','四川文艺出版社','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',24,'历史','《百家讲坛》主讲人纪连海作序推荐！秦汉文明之余韵，隋唐盛世之先声，中国历史上DI一个大混血时代。要读懂中国，必读南北朝；要读懂南北朝，必读北魏。酷威文化',5,1753345646831878146),(37,875225165,'应解人间不自由','谭伯牛','九州出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',36,'历史','知名历史学者谭伯牛的“民国爱情观察手记” 动荡乱世是民国，风花雪月也是民国 林徽因&徐志摩&金岳霖、沈从文&张兆和 胡适&曹诚英&陆小曼、郁达夫&王映霞 萧红&萧军&端木蕻良、顾维钧&黄蕙兰',5,1753345646831878146),(38,875598500,'故事里的中国1：吴越春秋','公孙策?广雅出品','广西师范大学出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',43.5,'历史','以经典史书为底本，讲述楚吴越三国恩仇。着眼历史人物的成败因果和心理活动，讲出大历史的大视野和寓言的教育性、小说的人情味，在历史故事中找寻国人的文化基因。广雅出品',5,1753345646831878146),(39,875598506,'故事里的中国2：楚汉传奇','公孙策?广雅出品','广西师范大学出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',51,'历史','以经典史书为底本，讲述项羽刘邦的千古对决。着眼历史人物的成败因果和心理活动，讲出大历史大视野和寓言的教育性、小说的人情味，在历史故事中找寻国人文化基因。广雅出品',5,1753345646831878146),(40,875016193,'乌丢丢的奇遇','金波?著，眉睫?赏析','天天出版社有限责任公司','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',15,'趣味读物','国际安徒生奖候选人金波代表作，彩插美绘版，特邀儿童文学研究专家眉睫撰写赏析文字，常青教授撰写导读，引导孩子正确阅读）有一定阅读能力的孩子都能读懂的经典儿童文学',5,1753345646831878146),(41,875559144,'为孩子解答西游记里的不解之谜（全二册）','六神磊磊；读客文化?出品','河南文艺出版社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',64,'趣味读物','著名自媒体作家六神磊磊专为孩子打造的《西游记》 深入解读《西游记》中每一个不解之谜背后的天文地理、文史典故，让孩子秒变传统文化小达人！随书附赠西游解秘刮刮卡一套！读客熊猫君出品',5,1753345646831878146),(42,875016194,'“下次开船”港','严文井','天天出版社有限责任公司','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',11.3,'趣味读物','语言生动幽默，故事构思巧妙，极具想象力，且富于哲理，被誉为新中国童话园地里一朵“绽开的鲜花”',5,1753345646831878146),(43,875516230,'四季森林：全4册','大卫·奥热罗','山东友谊出版社','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',90,'趣味读物','风靡法国的治愈系绘本，画风媲美经典动画电影。以森林为舞台，季节为幕布，个性鲜明的动物居民为主角，上演了一场场跌宕起伏、真挚感人的精彩大戏。走进小森林，感受大世界，在风霜雨雪中向善生长，在四季流转间诗意',5,1753345646831878146),(44,875726099,'神探迈克狐·神秘组织篇（套装6册）','多多罗/著绘；小博集出品','湖南文艺出版社','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',89.4,'趣味读物','1600万家长和孩子都喜欢的中国优质原创少儿互动科学侦探故事，图书畅销100万册，音频播放量超10亿次。燃烧大脑，用科学知识破解侦探难题。孙正凡王轶美等科普作家推荐',5,1753345646831878146),(45,875763202,'这就是数学','米莱童书','北京理工大学出版社','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',100,'趣味读物','中考必备漫画科普书，有趣有料点燃孩子数学热情；一线名师审读指导；聚焦数学核心素养培养，基础与应用全覆盖；小学数学重点难点填坑指南，用漫画还原抽象思维过程，建立抽象思维能力。',4,1753345646831878146),(46,875562671,'蔡皋经典中国绘本·珍藏礼盒','蔡皋；小博集出品；','湖南少年儿童出版社','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',164,'趣味读物','“国宝级”绘本画家、中国首位“金苹果奖”得主蔡皋代表作，让孩子在饱含情感的图画故事中学会勇敢与超越，感受爱与善良的力量，入选小学生分级阅读书目，曹文轩、金波、大J等27位专家联袂推荐',5,1753345646831878146),(47,875589158,'我爱阅读桥梁书·分级阅读版（第二辑）','金波、张秋生、冰波等','少年儿童出版社','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',100,'趣味读物','6-7岁孩子适读，通过科学的分级设置，实现让孩子从亲子阅读过渡到独立阅读功能，解决孩子不会独立阅读问题的桥梁书（海豚传媒出品）',4,1753345646831878146),(48,875763183,'超级工程驾到','米莱童书','北京理工大学出版社','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',100,'趣味读物','8大超级工程试卷上出镜率高；对超级工程的建造方式、技术难点、社会意义都做了细致的讲解；动感场景+超强代入感体验，工程学思维贯穿始终。',5,1753345646831878146),(49,202302010,'温妮女巫驾到','劳拉·欧文','中信出版社','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',136.5,'趣味读物','英国超人气作者、绘者联手打造，全球900万家庭都在看，穿越时空主题，更刺激的冒险，帮孩子解决不自信、容易放弃等成长难题',5,1753345646831878146),(50,870201735,'芝麻开门','祁智','人民文学出版社','http://localhost:8080/api/upload/芝麻开门.jpg',31.1,'趣味读物','《芝麻开门》属于“中国儿童文学经典书系”，作者祁智是著名儿童文学作家，本书是他的第一部长篇校园小说，畅销二十余年，曾获中宣部“五个一工程”奖、全国优秀少儿图书奖、全国优秀畅销书奖等',4,1753345646831878146),(54,1,'1','1','1','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',1,'保健品','123',0,1753345646831878146),(57,1,'1','1','1','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',1,'保健品','123',0,1753345646831878146),(58,1,'1','1','1','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',1,'保健品','1',0,1753345646831878146),(59,1,'123','123','123','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',1,'保健品','123',0,1753345646831878146),(60,1,'asd','asd','asd','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',1,'保健品','asd',0,1753345646831878146),(61,1,'薯片','500g','开袋即食','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',1,'保健品','开袋速吃',0,1753345646831878146),(62,1,'1','1','1','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',1,'保健品','123',0,1753345646831878146),(63,1,'1','1','1','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',1,'保健品','1',0,1753345646831878146),(64,1,'123','123','123','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',1,'保健品','123',0,1753345646831878146),(65,1,'asd','asd','asd','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',1,'保健品','asd',0,1753345646831878146),(66,1,'薯片','500g','开袋即食','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',1,'保健品','开袋速吃',0,1753345646831878146),(NULL,1,'薯片','450 克','适合作为零食','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',11,'水果','新鲜出炉',0,1),(NULL,1,'1','1','1','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',11,'水果','123',0,1751537986566361089),(NULL,1,'1','1','1','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp',11,'水果','1',0,1751897246655594497),(NULL,1,'123','123','123','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp',11,'水果','123',0,1753054078375845889),(71,1,'asd','asd','asd','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp',11,'水果','asd',0,1753103937153814530),(72,1,'薯片','500g','开袋即食','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp',11,'水果','开袋速吃',0,1753345646831878146),(73,1,'11','11','aa','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7',11,'水果','aa',0,1754876022402351106);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cid` int DEFAULT NULL,
  `uid` int DEFAULT NULL,
  `bookID` smallint unsigned DEFAULT NULL,
  `count` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`cid`, `uid`, `bookID`, `count`) VALUES (1,3,30,2),(5,3,31,1),(6,4,33,1),(7,4,34,1),(8,2,7,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `lng` varchar(50) DEFAULT NULL,
  `lat` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`, `name`, `type`, `username`, `password`, `lng`, `lat`, `location`) VALUES (1,'种植基地A','1','A','111111',NULL,NULL,NULL),(2,'加工工厂B','2','B','111111',NULL,NULL,'湖北省 宜昌市 兴山县\n 新街街道');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `uid` int DEFAULT NULL,
  `account` varchar(225) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `identity` int DEFAULT NULL,
  `uname` varchar(40) DEFAULT NULL,
  `himg` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
INSERT INTO `consumer` (`uid`, `account`, `password`, `identity`, `uname`, `himg`) VALUES (1,'admin','123',1,'管理员','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2'),(2,'123456','123456',2,'快乐吃手手','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2'),(3,'741852963','741852963',2,'猫猫','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2'),(4,'963852741','963852741',2,'小黄人','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2');
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `good` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL COMMENT '作物id',
  `user_id` bigint DEFAULT NULL COMMENT '加工人id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '加工人名字',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名',
  `store` varchar(50) DEFAULT NULL COMMENT '存储方法',
  `time` varchar(40) DEFAULT NULL COMMENT '生产耗时',
  `recommended` varchar(100) DEFAULT NULL COMMENT '使用推荐',
  `weight` varchar(50) DEFAULT NULL COMMENT '产品重量',
  `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  `status` int DEFAULT NULL COMMENT '商品状态',
  `location` varchar(50) DEFAULT NULL COMMENT '生产位置',
  `picture` varchar(50) DEFAULT NULL COMMENT '产品图片',
  `type` varchar(50) DEFAULT NULL COMMENT '产品类型',
  `price` decimal(10,2) DEFAULT NULL COMMENT '售价',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1754876022402351107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` (`id`, `task_id`, `product_id`, `user_id`, `user_name`, `phone`, `name`, `store`, `time`, `recommended`, `weight`, `remarks`, `status`, `location`, `picture`, `type`, `price`) VALUES (1,1,1,3,'加工仔A','18362336555','薯片','干燥存储','2 小时','适合作为零食','450 克','新鲜出炉',1,'加工第一车间','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00),(1751537986566361089,2,1734900668539613186,3,'加工仔A','18362336557','1','1','1','1','1','123',1,'1','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00),(1751897246655594497,1751896151191470081,2,3,'加工仔A','18362336557','1','1','1','1','1','1',1,'1','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00),(1753054078375845889,100,1,3,'加工仔A','18362336557','123','123','123','123','123','123',1,'123','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00),(1753103937153814530,100,1,3,'加工仔A','18362336557','asd','asd','asd','asd','asd','asd',1,'asd','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00),(1753345646831878146,1753345158971408385,1753342629269225474,3,'加工仔A','18362336557','薯片','密封储存','10小时','开袋即食','500g','开袋速吃',1,'第三车间','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00),(1754876022402351106,12,1753342629269225474,3,'加工仔A','18362336557','11','11','11','aa','11','aa',1,'11','https://i1.hdslb.com/bfs/archive/88766522c1a87d826','水果',11.00);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '农户Id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '农户名称',
  `company_id` bigint DEFAULT NULL COMMENT '公司Id',
  `name` varchar(50) NOT NULL COMMENT '农产品名称',
  `description` varchar(100) DEFAULT NULL COMMENT '备注 描述',
  `type` varchar(50) DEFAULT NULL COMMENT '产品类型',
  `arrival_date` datetime DEFAULT NULL COMMENT '到达时间',
  `status` int DEFAULT NULL COMMENT '0代表已经被送了 1代表还在商家手里',
  `user_telephone` varchar(50) DEFAULT NULL COMMENT '农户联系方式',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753342629269225475 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` (`id`, `user_id`, `user_name`, `company_id`, `name`, `description`, `type`, `arrival_date`, `status`, `user_telephone`) VALUES (1,1,'种植户A',1,'土豆','有机土豆','蔬菜','2024-01-28 22:00:32',1,'18362336555'),(2,1,'种植户A',1,'test1','test1','水果','2024-01-29 16:28:16',0,'18362336557'),(1734900668539613186,1,'张三',1,'苹果','新鲜水果','水果','2024-01-24 22:24:56',0,'123-456-7890'),(1734900668539613187,1,'张三',1,'苹果1','新鲜水果','水果','2024-01-24 22:24:56',1,'123-456-7890'),(1734900668539613188,1,'王五',1,'鸡蛋','农家鸡蛋','畜产品','2024-01-26 15:04:45',0,'555-123-4567'),(1753342629269225474,1,'种植户A',1,'土豆','就是个土豆','蔬菜','2024-02-02 17:04:07',0,'18362336557');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderID` char(15) DEFAULT NULL,
  `uid` int DEFAULT NULL,
  `aid` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `paymentWay` varchar(25) DEFAULT NULL,
  `deliverWay` varchar(25) DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `orderState` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`orderID`, `uid`, `aid`, `total`, `paymentWay`, `deliverWay`, `orderTime`, `orderState`) VALUES ('202303258609',3,1,171.6,'微信','普通快递','2023-03-25 23:34:21','配送中'),('20230519773',2,6,401.8,'微信','普通快递','2023-05-19 10:06:37','待确认'),('202305199218',1,5,246.7,'微信','特快专递','2023-05-19 09:57:42','待确认'),('202305209669',3,1,250.8,'微信','普通快递','2023-05-20 14:01:03','待确认');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '农户Id',
  `name` varchar(50) NOT NULL COMMENT '农产品名称',
  `description` varchar(100) DEFAULT NULL COMMENT '备注 描述\n',
  `production_location` varchar(100) DEFAULT NULL COMMENT '种植地点',
  `production_method` varchar(100) DEFAULT NULL COMMENT '种植方式',
  `type` varchar(50) DEFAULT NULL COMMENT '产品类型',
  `product_date` datetime DEFAULT NULL COMMENT '种植时间',
  `status` int DEFAULT NULL COMMENT '0代表已经被送了\n1代表还在农户手里',
  `user_name` varchar(50) DEFAULT NULL COMMENT '农户名称',
  `user_telephone` varchar(50) DEFAULT NULL COMMENT '农户联系方式',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753342629269225475 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `user_id`, `name`, `description`, `production_location`, `production_method`, `type`, `product_date`, `status`, `user_name`, `user_telephone`) VALUES (1,1,'土豆','有机土豆','一号试验田','大田种植','蔬菜','2024-01-28 21:47:23',1,'种植户A','18362336557'),(1734900668539613186,1,'苹果','新鲜水果','果园A','有机种植','水果','2024-01-20 08:00:00',1,'张三','123-456-7890'),(1734900668539613187,1,'大白菜','有机蔬菜','菜地B','自然生长','蔬菜','2024-01-21 10:30:00',1,'李四','987-654-3210'),(1734900668539613188,1,'鸡蛋','农家鸡蛋','养鸡场C','放养','畜产品','2024-01-22 12:45:00',1,'王五','555-123-4567'),(1734900668539613190,1,'小麦','有机小麦','农田E','耕种','谷物','2023-01-24 16:00:00',1,'刘七','444-555-6666'),(1753342629269225474,1,'土豆','就是个土豆','二号试验田','有机种植','蔬菜','2024-02-06 00:00:00',0,'种植户A','18362336557');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_record`
--

DROP TABLE IF EXISTS `product_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL COMMENT '农产品id',
  `user_id` bigint DEFAULT NULL COMMENT '农户id\n',
  `temperature` varchar(40) DEFAULT NULL COMMENT '温度\n',
  `illumination` varchar(100) DEFAULT NULL COMMENT '光照情况',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `picture` varchar(255) DEFAULT NULL COMMENT '记录图片',
  `water` varchar(40) DEFAULT NULL COMMENT '水分',
  `growth_situation` varchar(100) DEFAULT NULL COMMENT '生长情况',
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753342859951751171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_record`
--

LOCK TABLES `product_record` WRITE;
/*!40000 ALTER TABLE `product_record` DISABLE KEYS */;
INSERT INTO `product_record` (`id`, `product_id`, `user_id`, `temperature`, `illumination`, `remark`, `picture`, `water`, `growth_situation`, `record_time`) VALUES (1,1734900668539613190,1,'24°C','充足的阳光','喷洒了农药','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','41%','生长良好','2024-01-20 17:30:00'),(2,1734900668539613190,1,'29°C','适度的阳光','收获了一些农产品','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','33%','强壮','2024-01-20 18:15:00'),(3,1734900668539613190,1,'25°C','充足的阳光','生长良好','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','40%','健康','2024-01-20 08:30:00'),(4,1734900668539613190,1,'22°C','适度的阳光','未观察到害虫','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','45%','稳定','2024-01-20 09:15:00'),(5,1734900668539613187,1,'28°C','高强度阳光','今天施肥','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','35%','茁壮成长','2024-01-20 10:00:00'),(6,1734900668539613187,1,'20°C','低强度阳光','一些叶子发黄','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','50%','生长缓慢','2024-01-20 11:45:00'),(7,1734900668539613187,1,'26°C','充足的阳光','修剪了枝条','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','38%','稳健成长','2024-01-20 13:30:00'),(8,1734900668539613186,1,'23°C','适度的阳光','充足浇水','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','42%','健康','2024-01-20 14:15:00'),(9,1734900668539613186,1,'27°C','高强度阳光','未收到问题报告','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','37%','生机勃勃','2024-01-20 15:00:00'),(10,1734900668539613186,1,'21°C','低强度阳光','发现新的嫩芽','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','48%','稳定','2024-01-20 16:45:00'),(11,1,1,'22°C','低强度阳光','马铃薯发芽期','https://th.bing.com/th/id/OIP.Fl3D7KWp0z9NPqoG4Epe3QHaE7?w=246&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7','48%','生长良好','2024-01-28 21:49:54'),(12,1,1,'26°C','高强度阳光','马铃薯幼苗期','https://pic3.zhimg.com/80/v2-deec9e31682304b01f3a8f6aa65c0892_720w.webp','33%','稳定','2024-01-28 21:52:21'),(13,1,1,'32°C','充足的阳光','马铃薯块茎形成期','https://pic1.zhimg.com/80/v2-3a0520e265e0f410e203b7b14a22acd0_720w.webp','37%','稳健成长','2024-01-28 21:52:54'),(14,1,1,'29°C','低强度阳光','马铃薯块茎膨大期','https://pic3.zhimg.com/80/v2-d02eff1c38d3a0fead918befdaf5ea3e_720w.webp','35%','茁壮成长','2024-01-28 21:54:54'),(15,1,1,'21°C','适度的阳光','马铃薯淀粉积累期','https://pic2.zhimg.com/80/v2-2e335524c97a40f6ef99860382f1b1d1_720w.webp','50%','生长缓慢','2024-01-28 21:55:54'),(16,1,1,'20°C','高强度阳光','马铃薯块茎休眠期','https://th.bing.com/th/id/OIP.6FUrx1LZx8W7mff9i82omwHaE8?w=301&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7','38%','生机勃勃','2024-01-28 21:57:54'),(1751881890109095938,2,1,'test1','光照充足','test1','https://sky-take-out003.oss-cn-beijing.aliyuncs.com/6b9cb73c-7efa-4f01-a856-190cb2ba7e22.jpg','水分过剩','生长迅速','2024-01-29 16:16:06'),(1751882085530107906,2,1,'test12','光照充足','test1','https://sky-take-out003.oss-cn-beijing.aliyuncs.com/6b9cb73c-7efa-4f01-a856-190cb2ba7e22.jpg','水分过剩','生长迅速','2024-01-29 16:16:52'),(1753067604238741506,1753067311396630530,1,'123','光照充足','123','https://sky-take-out003.oss-cn-beijing.aliyuncs.com/79c111d8-fcce-4d1d-9219-2314637b1115.jpg','水分过剩','生长迅速','2024-02-01 22:47:42'),(1753342859951751170,1753342629269225474,1,'22°C','光照充足','马铃薯发芽期','https://sky-take-out003.oss-cn-beijing.aliyuncs.com/39219b71-f44c-4aa4-aaae-e5895532c94b.jpg','水分过剩','生长迅速','2024-02-02 17:01:28');
/*!40000 ALTER TABLE `product_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommend`
--

DROP TABLE IF EXISTS `recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recommend` (
  `bookID` smallint unsigned DEFAULT NULL,
  `bookName` varchar(40) DEFAULT NULL,
  `author` varchar(40) DEFAULT NULL,
  `cover` varchar(1000) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommend`
--

LOCK TABLES `recommend` WRITE;
/*!40000 ALTER TABLE `recommend` DISABLE KEYS */;
INSERT INTO `recommend` (`bookID`, `bookName`, `author`, `cover`, `price`) VALUES (5,'狂飙','徐纪周?朱俊懿?白文君','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',34),(8,'没药花园案件：罪恶追踪','没药花园?著,博集天卷?出品','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',24),(9,'那不勒斯四部曲','[意]埃莱娜·费兰特','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',150),(14,'请照顾好我妈妈','[韩]申京淑著 ;?薛舟,?徐丽红译','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2',34.5);
/*!40000 ALTER TABLE `recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relate`
--

DROP TABLE IF EXISTS `relate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relate` (
  `uid` int DEFAULT NULL,
  `bookID` smallint unsigned DEFAULT NULL,
  `interest` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relate`
--

LOCK TABLES `relate` WRITE;
/*!40000 ALTER TABLE `relate` DISABLE KEYS */;
INSERT INTO `relate` (`uid`, `bookID`, `interest`) VALUES (1,5,1),(1,13,2),(1,14,1),(1,18,1),(1,8,2),(1,2,1),(1,45,2),(1,6,1),(1,34,2),(1,21,2),(2,5,2),(2,4,2),(2,2,2),(2,47,2),(2,50,2),(2,14,1),(2,31,1),(2,43,1),(2,15,2),(2,32,2),(3,1,2),(3,2,2),(3,32,1),(3,21,1),(3,4,2),(3,42,1),(3,14,2),(3,5,2),(3,7,1),(3,9,1),(3,3,2),(3,6,2),(3,13,1),(3,8,1),(3,18,2),(3,30,1),(3,33,1),(1,1,1),(1,3,1),(1,4,1),(1,24,1),(1,31,1),(1,42,1),(1,47,1),(1,46,1),(1,33,1),(1,30,1),(3,29,2),(3,31,1),(4,33,1),(4,34,1),(2,7,1);
/*!40000 ALTER TABLE `relate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '农作物id 全部用农作物id',
  `company_id` bigint DEFAULT NULL COMMENT '原料厂Id',
  `company_name` varchar(50) DEFAULT NULL COMMENT '原料厂名字',
  `name` varchar(50) NOT NULL COMMENT '作物名称',
  `type` varchar(50) DEFAULT NULL COMMENT '作物类型',
  `arrival_date` datetime DEFAULT NULL COMMENT '到达时间',
  `status` int DEFAULT NULL COMMENT '加工状态 0代表未加工 1表示在加工 2加工完成',
  `farmer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753342629269225475 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`id`, `company_id`, `company_name`, `name`, `type`, `arrival_date`, `status`, `farmer_id`) VALUES (1,2,'种植基地A','苹果','水果','2024-01-26 17:01:49',2,NULL),(2,2,'种植基地A','test1','水果','2024-01-29 16:31:41',2,NULL),(1734900668539613186,2,'种植基地A','苹果','水果','2024-01-26 17:01:48',2,NULL),(1753342629269225474,2,'种植基地A','土豆','蔬菜','2024-02-02 17:09:17',2,NULL);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suborders`
--

DROP TABLE IF EXISTS `suborders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suborders` (
  `suborderID` int unsigned DEFAULT NULL,
  `bookID` smallint unsigned DEFAULT NULL,
  `quantity` smallint DEFAULT NULL,
  `orderID` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suborders`
--

LOCK TABLES `suborders` WRITE;
/*!40000 ALTER TABLE `suborders` DISABLE KEYS */;
INSERT INTO `suborders` (`suborderID`, `bookID`, `quantity`, `orderID`) VALUES (1,6,2,'202303258609'),(2,3,2,'202303258609'),(3,13,1,'202305199218'),(4,8,1,'202305199218'),(5,45,1,'202305199218'),(6,34,1,'202305199218'),(7,21,1,'202305199218'),(8,5,1,'20230519773'),(9,4,1,'20230519773'),(10,2,1,'20230519773'),(11,47,1,'20230519773'),(12,50,1,'20230519773'),(13,15,1,'20230519773'),(14,32,1,'20230519773'),(16,18,2,'202305209669'),(17,29,1,'202305209669');
/*!40000 ALTER TABLE `suborders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '加工仔id',
  `record_time` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL COMMENT '任务名字',
  `status` int DEFAULT NULL COMMENT '0 未工作\n1 在工作\n2 完成工作',
  `company_id` bigint DEFAULT NULL COMMENT '加工厂id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753345317633540099 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` (`id`, `product_id`, `user_id`, `record_time`, `remarks`, `task_name`, `status`, `company_id`) VALUES (1,1,3,'2024-01-28 23:10:03','注意加工环境','加工成薯片',2,2),(2,1734900668539613186,3,'2024-01-27 23:10:03','123','123',2,2),(12,1753342629269225474,3,'2025-02-02 17:10:36','注意加工环境','加工成薯片',2,2),(100,1,3,'2025-01-28 23:10:03','注意加工环境','加工成薯片',2,2),(1751261289778548738,1734900668539613186,3,'2024-01-27 23:10:03','123','123',2,2),(1751896151191470081,2,3,'2024-01-29 17:12:46','123','123',2,2),(1753345158971408385,1753342629269225474,3,'2024-02-02 17:10:36','注意加工环境','加工成薯片',2,2);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_record`
--

DROP TABLE IF EXISTS `task_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '加工仔id',
  `company_id` bigint DEFAULT NULL COMMENT '加工厂id',
  `record_time` datetime DEFAULT NULL COMMENT '加工时间',
  `remarks` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL COMMENT '任务名字',
  `content` varchar(50) DEFAULT NULL COMMENT 'content',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753345503546064899 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_record`
--

LOCK TABLES `task_record` WRITE;
/*!40000 ALTER TABLE `task_record` DISABLE KEYS */;
INSERT INTO `task_record` (`id`, `task_id`, `product_id`, `user_id`, `company_id`, `record_time`, `remarks`, `task_name`, `content`) VALUES (1,1,1,3,2,'2024-01-28 22:04:47','清洗并去除泥土。削去土豆表面的皮','加工成薯片','去皮和清洗'),(2,1,1,3,2,'2024-01-28 22:04:48','将土豆切成薄片','加工成薯片','切片'),(3,1,1,3,2,'2024-01-28 22:04:49','将土豆片浸泡在冷水中，目的是去除土豆表面的淀粉','加工成薯片','浸泡土豆片'),(4,1,1,3,2,'2024-01-28 22:04:50','将切好并浸泡过的土豆片放入油炸锅中，用油炸至金黄色。油温的控制是关键，通常在 170°C 到 190°C 之间。','加工成薯片','炸土豆片'),(5,1,1,3,2,'2024-01-28 22:04:51','放在纸巾上沥去多余的油分','加工成薯片','沥油'),(6,1,1,3,2,'2024-01-28 22:04:52','根据口味需求添加盐','加工成薯片','调味'),(1751454211887128510,1751261289778548738,NULL,3,2,'2024-01-28 11:56:39',NULL,'123','123'),(1751454211887128578,1751261289778548738,NULL,3,2,'2024-01-28 11:56:39',NULL,'123','123'),(1751454211887128579,1751261289778548738,NULL,3,2,'2024-01-28 11:56:39',NULL,'123','123'),(1751505566794313730,1751261289778548738,NULL,3,2,'2024-01-28 15:20:21',NULL,'123','123'),(1751896661772484610,1751896151191470081,NULL,3,2,'2024-01-29 17:14:47',NULL,'123','1'),(1751896987653128193,1751896151191470081,NULL,3,2,'2024-01-29 17:16:05',NULL,'123','123'),(1753052818075574273,100,1,3,2,'2024-02-01 21:48:54','123','加工成薯片','123'),(1753054030518837250,100,1,3,2,'2024-02-01 21:53:46','123','加工成薯片','123'),(1753103885136056321,100,1,3,2,'2024-02-02 01:11:51','asd','加工成薯片','asd'),(1753345503546064898,1753345158971408385,1753342629269225474,3,2,'2024-02-02 17:11:57','洗土豆','加工成薯片','洗土豆');
/*!40000 ALTER TABLE `task_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '运输仔id',
  `record_time` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '0未接受\n1送货中\n2送货完成',
  `farmer_id` bigint DEFAULT NULL COMMENT '农户id \n虽然写的是农户id\n但是实际上存的是账户id\n之后通过账户id去查询level\n判断公司 然后起点数据无需传入 \n',
  `name` varchar(40) DEFAULT NULL COMMENT '通知者名字',
  `lng` varchar(50) DEFAULT NULL,
  `lat` varchar(50) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL COMMENT '运输到达目的地',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1749751834301722656 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
INSERT INTO `transport` (`id`, `product_id`, `user_id`, `record_time`, `remarks`, `status`, `farmer_id`, `name`, `lng`, `lat`, `company_id`) VALUES (1,1,5,'2024-01-28 21:57:08','尽快送到',2,1,'种植户A','110.8217','31.3137',1),(2,1734900668539613187,5,'2024-01-22 13:44:57','白菜',1,1,'admin','110.8217','31.3137',NULL),(100,1,5,'2025-01-28 21:57:08','尽快送到',2,1,'种植户A','110.8217','31.3137',1),(1749751834301722634,1734900668539613190,5,'2024-01-23 19:29:07','',2,1,'种植户A','110.8217','31.3137',NULL),(1749751834301722636,1734900668539613188,5,'2024-01-24 13:58:21','',2,1,'种植户A','-123.438353','39.5500194',NULL),(1749751834301722642,1734900668539613188,5,'2024-01-26 15:22:41','123',2,2,'原料厂A','113.26627','23.1317099',NULL),(1749751834301722643,1734900668539613188,5,'2024-01-26 15:22:41','123',0,2,'原料厂A','113.26627','23.1317099',NULL),(1749751834301722644,1734900668539613186,5,'2024-01-26 16:59:55','',2,2,'原料厂A','113.26627','23.1317099',NULL),(1749751834301722645,1734900668539613186,5,'2024-01-26 16:59:55','',0,2,'原料厂A','113.26627','23.1317099',NULL),(1749751834301722646,173490066853961318,5,'2024-01-26 17:00:05','',2,2,'原料厂A','113.26627','23.1317099',2),(1749751834301722650,2,5,'2024-01-29 16:18:22','test1',2,1,'种植户A','-123.438353','39.5500194',NULL),(1749751834301722651,2,5,'2024-01-29 16:18:22','test1',0,1,'种植户A','-123.438353','39.5500194',NULL),(1749751834301722652,2,5,'2024-01-29 16:29:07','test1',0,2,'原料厂A','-123.438353','39.5500194',2),(1749751834301722653,2,5,'2024-01-29 16:29:07','test1',2,2,'原料厂A','-123.438353','39.5500194',2),(1749751834301722654,1753342629269225474,5,'2024-02-02 17:01:45','快点送',2,1,'种植户A','-123.438353','39.5500194',NULL),(1749751834301722655,1753342629269225474,5,'2024-02-02 17:08:42','送去加工',2,2,'原料厂A','-123.438353','39.5500194',2);
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport_record`
--

DROP TABLE IF EXISTS `transport_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '运输员id',
  `transport_id` bigint DEFAULT NULL,
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  `lng` varchar(50) DEFAULT NULL,
  `lat` varchar(50) DEFAULT NULL,
  `location_info` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1753343497930555394 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport_record`
--

LOCK TABLES `transport_record` WRITE;
/*!40000 ALTER TABLE `transport_record` DISABLE KEYS */;
INSERT INTO `transport_record` (`id`, `product_id`, `user_id`, `transport_id`, `record_time`, `lng`, `lat`, `location_info`) VALUES (1,1,5,1,'2024-01-28 21:58:28','110.8217','31.3137','湖北省 宜昌市 兴山县'),(2,1,5,1,'2024-01-28 21:58:32','111.8217','32.3137','湖北省 襄阳市 老河口市'),(3,1,5,1,'2024-01-28 21:59:28','112.8217','33.3137','河南省 南阳市 方城县'),(4,1,5,1,'2024-01-28 22:58:28','113.8217','34.3137','河南省 许昌市 长葛市'),(1749751834301722634,1734900668539613190,1,NULL,'2024-01-23 19:29:07','-123.438353','39.5500194',NULL),(1749751834301722635,1734900668539613190,5,1749751834301722635,'2024-01-23 19:29:07','110.8217','31.3137',NULL),(1749751834301722637,1734900668539613188,5,1749751834301722637,'2024-01-24 13:58:21','-123.438353','39.5500194',NULL),(1749751834301722639,1734900668539613186,5,1749751834301722639,'2024-01-26 15:06:07','113.26627','23.1317099',NULL),(1749751834301722641,1734900668539613187,5,1749751834301722641,'2024-01-26 15:08:23','113.26627','23.1317099',NULL),(1749751834301722643,1734900668539613188,5,1749751834301722643,'2024-01-26 15:22:41','113.26627','23.1317099',NULL),(1749751834301722645,1734900668539613186,NULL,1749751834301722645,'2024-01-26 16:59:55','113.26627','23.1317099',NULL),(1749751834301722647,1734900668539613186,NULL,1749751834301722647,'2024-01-26 17:00:05','113.26627','23.1317099',NULL),(1749751834301722653,2,5,1749751834301722653,'2024-01-29 16:29:07','-123.438353','39.5500194','California Mendocino '),(1749751834301722654,1753342629269225474,5,1749751834301722654,'2024-02-02 17:01:45','-123.438353','39.5500194','California Mendocino '),(1749751834301722655,1753342629269225474,5,1749751834301722655,'2024-02-02 17:08:42','-123.438353','39.5500194','California Mendocino '),(1751884674749448193,2,5,1749751834301722650,'2024-01-29 16:27:10','-123.438353','39.5500194','湖北省 襄阳市 老河口市'),(1751884680126545922,2,5,1749751834301722650,'2024-01-29 16:27:11','-123.438353','39.5500194','河南省 南阳市 方城县'),(1751884684710920194,2,5,1749751834301722650,'2024-01-29 16:27:12','-123.438353','39.5500194','河南省 许昌市 长葛市'),(1751885802237083649,2,5,NULL,'2024-01-29 16:31:38','-123.438353','39.5500194','California Mendocino '),(1753034528485928961,1,5,100,'2024-02-01 20:36:16','-123.438353','39.5500194','California Mendocino '),(1753343265025048577,1,5,100,'2024-02-02 17:03:05','-123.438353','39.5500194','California Mendocino '),(1753343385783255041,1753342629269225474,5,1749751834301722654,'2024-02-02 17:03:33','-123.438353','39.5500194','California Mendocino '),(1753343497930555393,1753342629269225474,5,1749751834301722654,'2024-02-02 17:04:00','-123.438353','39.5500194','California Mendocino ');
/*!40000 ALTER TABLE `transport_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(255) DEFAULT NULL,
  `introduction` text,
  `roles` varchar(20) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `update_time` int DEFAULT NULL,
  `level` int DEFAULT NULL COMMENT '0 农户\n1 原料厂\n2 加工仔\n3 加工厂\n4 运输仔\n5 客户',
  `work` int DEFAULT NULL COMMENT '0工作中\n1休假中\n2能干活的',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1748924320339763202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `username`, `password`, `phone`, `sex`, `number`, `avatar`, `introduction`, `roles`, `status`, `create_time`, `company_id`, `update_time`, `level`, `work`) VALUES (1,'种植户A','admin','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','employeeA',1,'2023-12-12 08:11:10',1,NULL,0,2),(2,'原料厂A','admin2','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','material',1,'2023-12-12 08:11:10',1,NULL,1,2),(3,'加工仔A','admin3','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','employeeB',1,'2023-12-12 08:11:10',2,NULL,2,2),(4,'加工厂A','admin4','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','processing',1,'2023-12-12 08:11:10',2,NULL,3,2),(5,'运输仔A','admin5','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','transport',1,'2023-12-12 08:11:10',1,NULL,4,2),(6,'运输仔A2.0','admin7','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','transport',1,'2023-12-12 08:11:10',1,NULL,4,2),(8,'A','admin1','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','employeeA',1,'2023-12-12 08:11:10',1,NULL,0,2),(9,'运输仔B','admin6','111111','18362336557','1','32028220020828023X','https://th.bing.com/th?id=OIP.sAiCiGYrRluulMkTFBaFIQHaHU&w=251&h=248&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','这是一段自我介绍','transport',1,'2023-12-12 08:11:10',2,NULL,4,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 22:51:06
