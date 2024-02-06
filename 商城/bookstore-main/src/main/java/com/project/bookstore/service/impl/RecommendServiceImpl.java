package com.project.bookstore.service.impl;

import com.project.bookstore.mapper.BookMapper;
import com.project.bookstore.mapper.RecommendMapper;
import com.project.bookstore.pojo.Book;
import com.project.bookstore.pojo.R;
import com.project.bookstore.pojo.Recommend;
import com.project.bookstore.pojo.RelateDTO;
import com.project.bookstore.service.RecommendService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    RecommendMapper recommendMapper;

    /**
     * 基于用户的协同推荐算法实现
     *
     * @param uid
     * @return
     */
    @Override
    public R userCF(int uid) {
        try {
            List<RelateDTO> relateList = recommendMapper.listRelate();
            List<Book> bookList = bookMapper.showBook();
            List<Book> itemList = userCfRecommend(uid, relateList, bookList);
            if (itemList.size() == 0) {
                return R.FAIL("推荐失败");
            } else if (itemList.size() > 4) {
                return R.SUCCESS(itemList.subList(0, 4), "获取推荐书籍");
            }
            return R.SUCCESS(itemList, "获取推荐书籍");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }


    /**
     * 基于物品的协同推荐算法实现
     *
     * @param bookID
     * @return
     */
    @Override
    public R itemCF(int bookID) {
        try {
            List<RelateDTO> relateList = recommendMapper.listRelate();
            List<Book> bookList = bookMapper.showBook();
            List<Book> itemList = itemCfRecommend(bookID, relateList, bookList);
            if (itemList == null || itemList.size() == 0) {
                return R.SUCCESS(bookMapper.rand(), "获取推荐书籍");
            } else if (itemList.size() > 4) {
                return R.SUCCESS(itemList.subList(0, 4), "获取推荐书籍");
            }
            return R.SUCCESS(itemList, "获取推荐书籍");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R listRecommend() {
        try {
            List<Recommend> recommendList = recommendMapper.listRecommend();
            if (recommendList.size() == 0) {
                return R.FAIL("没有获取到默认推荐书籍");
            }
            return R.SUCCESS(recommendList, "获取到默认推荐书籍");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R insertRecommend(Integer bookID, String bookName, String author, String cover, Double price) {
        try {
            int i = recommendMapper.insertRecommend(bookID, bookName, author, cover, price);
            if (i == 0) {
                return R.FAIL("增加推荐书籍失败！");
            }
            return R.SUCCESS("增加推荐书籍成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }

    @Override
    public R deleteRecommend(Integer bookID) {
        try {
            int i = recommendMapper.deleteRecommend(bookID);
            if (i == 0) {
                return R.FAIL("删除推荐书籍失败！");
            }
            return R.SUCCESS("删除推荐书籍成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("数据库出错");
        }
    }


    /**
     * 基于用户推荐，输出推荐商品
     *
     * @param uid
     * @param relateList
     * @param bookList
     * @return
     */

    public List<Book> userCfRecommend(int uid, List<RelateDTO> relateList, List<Book> bookList) {
        List<Integer> recommendations = UserCFrecommend(uid, relateList);
        return bookList.stream().filter(e -> recommendations.contains(e.getBookID())).collect(Collectors.toList());
    }

    /**
     * 基于物品推荐，输出推荐商品
     *
     * @param bookID
     * @param relateList
     * @param bookList
     * @return
     */
    public List<Book> itemCfRecommend(int bookID, List<RelateDTO> relateList, List<Book> bookList) {
        List<Integer> recommendations = ItemCFrecommend(bookID, relateList);
        if (recommendations == null || recommendations.isEmpty()) {
            return null;
        }
        return bookList.stream().filter(e -> recommendations.contains(e.getBookID())).collect(Collectors.toList());
    }

    /**
     * 通过计算皮尔森指数找出最邻近邻居
     *
     * @param key
     * @param map
     * @param type
     * @return
     */
    public Map<Integer, Double> computeNeighbor(Integer key, Map<Integer, List<RelateDTO>> map, int type) {
        Map<Integer, Double> distMap = new TreeMap<>();
        List<RelateDTO> userItems = map.get(key);
        if (userItems == null || userItems.isEmpty()) {
            return null;
        }
        map.forEach((k, v) -> {
            //排除此用户
            if (!k.equals(key)) {
                //关系系数
                double coefficient = relateDist(v, userItems, type);
                //关系距离
                double distance = Math.abs(coefficient);
                distMap.put(k, distance);
            }
        });
        return distMap;
    }

    /**
     * 构建矩阵
     *
     * @param xList
     * @param yList
     * @param type
     * @return
     */

    private double relateDist(List<RelateDTO> xList, List<RelateDTO> yList, int type) {
        List<Integer> xs = Lists.newArrayList();
        List<Integer> ys = Lists.newArrayList();
        xList.forEach(x -> {
            yList.forEach(y -> {
                if (type == 0) {
                    if (Objects.equals(x.getBookID(), y.getBookID())) {
                        xs.add(x.getInterest());
                        ys.add(y.getInterest());
                    }
                } else {
                    if (Objects.equals(x.getUid(), y.getUid())) {
                        xs.add(x.getInterest());
                        ys.add(y.getInterest());
                    }
                }
            });
        });
        return getRelate(xs, ys);
    }

    /**
     * 计算皮尔森指数
     *
     * @param xs
     * @param ys
     * @return
     */
    public double getRelate(List<Integer> xs, List<Integer> ys) {
        int n = xs.size();
        //至少有两个元素
        if (n < 2) {
            return 0D;
        }
        double Ex = xs.stream().mapToDouble(x -> x).sum();
        double Ey = ys.stream().mapToDouble(y -> y).sum();
        double Ex2 = xs.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = ys.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> xs.get(i) * ys.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) {
            return 0D;
        }
        return numerator / denominator;
    }

    /**
     * 将关系表按物品进行分组
     *
     * @param itemId
     * @param list
     * @return
     */
    public List<Integer> ItemCFrecommend(Integer itemId, List<RelateDTO> list) {
        //按物品分组
        Map<Integer, List<RelateDTO>> itemMap = list.stream().collect(Collectors.groupingBy(RelateDTO::getBookID));
        //获取其他物品与当前物品的关系值
        Map<Integer, Double> itemDisMap = computeNeighbor(itemId, itemMap, 1);
        if (itemDisMap == null || itemDisMap.isEmpty()) {
            return null;
        }
        //获取关系最近物品
        double maxValue = Collections.max(itemDisMap.values());
        return itemDisMap.entrySet().stream().filter(e -> e.getValue() == maxValue).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * 将关系表按用户进行分组
     *
     * @param userId
     * @param list
     * @return
     */
    public List<Integer> UserCFrecommend(Integer userId, List<RelateDTO> list) {
        //按用户分组
        Map<Integer, List<RelateDTO>> userMap = list.stream().collect(Collectors.groupingBy(RelateDTO::getUid));
        //获取其他用户与当前用户的关系值
        Map<Integer, Double> userDisMap = computeNeighbor(userId, userMap, 0);
        //获取关系最近的用户
        double maxValue = Collections.max(userDisMap.values());
        Set<Integer> userIds = userDisMap.entrySet().stream().filter(e -> e.getValue() == maxValue).map(Map.Entry::getKey).collect(Collectors.toSet());
        //取关系最近的用户
        Integer nearestUserId = userIds.stream().findAny().orElse(null);
        if (nearestUserId == null) {
            return Collections.emptyList();
        }
        //最近邻用户书籍列表
        List<Integer> neighborItems = userMap.get(nearestUserId).stream().map(RelateDTO::getBookID).collect(Collectors.toList());
        //指定用户书籍列表
        List<Integer> userItems = userMap.get(userId).stream().map(RelateDTO::getBookID).collect(Collectors.toList());
        //找到最近邻看过，但是该用户没看过的书籍
        neighborItems.removeAll(userItems);
        return neighborItems;
    }
}
