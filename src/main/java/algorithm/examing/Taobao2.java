package algorithm.examing;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * //评测题目: 已知有商品的成交消息(message中可知itemId、itemTitle、userId、userName、tradeTime)，
 * // 要求设计并实现存入和读取两个方法，使得前端在进入商品详情页后，展示5分钟内最近完成购买的10条记录。
 * // (xxx用户最近xxx秒前购买了xxx商品), 单机版，存储可用内存类数据结构
 *
 * @ClassName: Taobao2
 * @Author: jicheng
 * @CreateDate: 2020/1/9 8:16 PM
 */
public class Taobao2 {

    private class TradeMessage {

        private Integer itemId;

        private String itemTitle;

        private Integer userId;

        private String userName;

        private Date tradeTime;

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Date getTradeTime() {
            return tradeTime;
        }

        public void setTradeTime(Date tradeTime) {
            this.tradeTime = tradeTime;
        }

    }

    private static final int ITEM_NUM = 2;

    private static final long MINUTS = 5L;

    private PriorityQueue<TradeMessage> priorityQueue;

    private ReentrantLock lock = new ReentrantLock();

    public Taobao2() {
        this.priorityQueue = new PriorityQueue<>((v1, v2) -> {
            if (Objects.isNull(v1) || Objects.isNull(v2)) {
                return 0;
            }
            return (v1.getTradeTime().compareTo(v2.getTradeTime()));
        });
    }


    public void insertTradeInfo(TradeMessage tradeMessage) {
        checkObjIsNull(tradeMessage);
        checkPropertyIsNull(tradeMessage);
        save(tradeMessage);
    }

    public List<String> getTradeInfo() {
        TradeMessage[] tradeMessages = this.priorityQueue.toArray(new TradeMessage[0]);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime4FiveMin = now.minusMinutes(MINUTS);
        Date fivePre = Date.from(localDateTime4FiveMin.atZone(ZoneId.systemDefault()).toInstant());


        return Stream.of(tradeMessages)
                .filter(value -> value.getTradeTime().compareTo(fivePre) > 0)
                .map(value -> value.getUserName() + "用户最近" +
                        +getSecond(now, value) + "秒前购买了" +
                        value.getItemTitle() + "商品")
                .collect(Collectors.toList());
    }

    private long getSecond(LocalDateTime now, TradeMessage value) {
        long nowSecond = (Timestamp.valueOf(now).getTime() - value.getTradeTime().getTime()) / 1000;
        if (nowSecond < 1) {
            return 1;
        }
        return nowSecond;
    }

    private LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDateTime();
    }

    private void save(TradeMessage tradeMessage) {
        System.out.println(tradeMessage.getTradeTime().getTime());
        this.lock.lock();
        try {

            TradeMessage peek = priorityQueue.peek();
            if (peek != null && tradeMessage.getTradeTime().compareTo(peek.getTradeTime()) < 0) {
                System.out.println(peek.getTradeTime().getTime());
                throw new RuntimeException("已经过期的数据");
            }
            if (priorityQueue.size() >= ITEM_NUM) {
                this.priorityQueue.poll();
                //TODO 不能往外抛 throw new RuntimeException("已经超过限制");
            }
            this.priorityQueue.offer(tradeMessage);
        } finally {
            this.lock.unlock();
        }

    }

    private void checkObjIsNull(TradeMessage tradeMessage) {
        if (Objects.isNull(tradeMessage)) {
            throw new RuntimeException("不能为空");
        }
    }

    private void checkPropertyIsNull(TradeMessage tradeMessage) {
        if (Objects.isNull(tradeMessage.getTradeTime())) {
            tradeMessage.setTradeTime(new Date());
        }
        if (Objects.isNull(tradeMessage.getItemId())) {
            throw new RuntimeException("交易商品id不能为空");
        }
        if (Objects.isNull(tradeMessage.getUserId())) {
            throw new RuntimeException("用户id不能空");
        }
    }


    public static void main(String[] args) {
        Taobao2 taobao2 = new Taobao2();
        TradeMessage tradeMessage = taobao2.new TradeMessage();
        tradeMessage.setTradeTime(new Date());
        tradeMessage.setItemId(1);
        tradeMessage.setUserId(11);

        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TradeMessage tradeMessage2 = taobao2.new TradeMessage();
        tradeMessage2.setTradeTime(new Date());
        tradeMessage2.setItemId(2);
        tradeMessage2.setUserId(22);


        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TradeMessage tradeMessage3 = taobao2.new TradeMessage();
        tradeMessage3.setTradeTime(new Date());
        tradeMessage3.setItemId(3);
        tradeMessage3.setUserId(33);


        taobao2.insertTradeInfo(tradeMessage);
        taobao2.insertTradeInfo(tradeMessage2);
        taobao2.insertTradeInfo(tradeMessage3);

        System.out.println(taobao2.priorityQueue.peek());
    }
}
