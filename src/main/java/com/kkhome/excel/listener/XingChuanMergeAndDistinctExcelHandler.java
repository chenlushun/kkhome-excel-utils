package com.kkhome.excel.listener;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.kkhome.excel.utils.BCConvert;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 订单发货合并工具
 * 1、根据订单号为维度进行合并
 * 2、合并列要展示商品番号，个数和枚数相乘
 * 3、遇到商品番号相同，做合并
 * <p>
 * 合并结果
 * 394274-20201110-00000740    ｜  【m1-02】黑4枚+【m1-03】白6枚
 */
public class XingChuanMergeAndDistinctExcelHandler extends MergeAndDistinctExcelHandler implements ExcelHandler {


    private final static String codeMsg = "【%s】";

    /**
     * 分割
     */
    public static final String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    /**
     * title 的 数字分割
     */
    public static final String regEx = "[^0-9]";

    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {
        Map<String, List<Item>> map = new HashMap<>();
        for (int i = 1; i < stringList.size(); i++) {
            String s = stringList.get(i);
            s = s.replaceAll("\"", "");
            String[] rows = s.split(csvSplitBy);
            // 订单号
            String orderId = rows[0];
            if (!orderId.contains("-") || orderId.split("-").length != 3) {
                continue;
            }
            // 货号
            String code = rows[1];
            String title = "";
            List<Item> value = map.get(orderId);
            if (rows.length > 3) {
                title = BCConvert.qj2bj(rows[3]);
            }
            Item item = new Item();
            item.setCode(code);
            if (StringUtils.isEmpty(title)) {
                item.setColor("");
                item.setNumber(Integer.parseInt(rows[2].replace("\"", "")));
                item.setUnit("個");
            } else {
                Context context = new Context();
                int number = analysisNumber(title, context);
                if (title.contains("色セット")) {
                    item.setColor("Sx");
                    item.setNumber(Integer.parseInt(rows[2].replace("\"", "")));
                } else if (context.getType() == 1) {
                    item.setColor("");
                    item.setTitle(title.split(":")[1] + " x ");
                    item.setNumber(Integer.parseInt(rows[2].replace("\"", "")));
                    item.setUnit("個");
                } else {
                    String[] texts = title.split(":");
                    Preconditions.checkArgument(texts.length == 2, title + ":颜色异常");
                    // 构建商品
                    item.setColor(texts[1].replace("枚", "").substring(0, texts[1].length() - 2));
                    item.setNumber(number * Integer.parseInt(rows[2].replace("\"", "")));
                    item.setUnit("枚");
                }
            }
            // 第一次添加
            if (null == value) {
                map.put(orderId, Lists.newArrayList(item));
            }
            // 有重复值的时候合并
            else {
                value.add(item);
                map.put(orderId, value);
            }
        }
        Map<String, String> mapResult = new HashMap<>();
        map.forEach((orderId, itemList) -> {
            // code,message
            Map<String, List<Item>> items = new HashMap<>();
            for (Item item : itemList) {
                List<Item> itemList1 = items.get(item.getCode());
                if (CollectionUtils.isEmpty(itemList1)) {
                    itemList1 = new ArrayList<>();
                }
                itemList1.add(item);
                items.put(item.getCode(), itemList1);
            }
            StringBuilder builder = new StringBuilder();
            items.forEach((newcode, newitemList) -> {
                if (builder.length() > 0) {
                    builder.append(" + ");
                }

                builder.append(String.format(codeMsg, newcode));
                for (int i = 0; i < newitemList.size(); i++) {
                    Item item = newitemList.get(i);
                    if (i > 0) {
                        builder.append(" + ");
                    }
                    builder.append(item.getTitle()).
                            append(item.getColor()).
                            append(item.getNumber()).
                            append(item.getUnit());

                }

            });
            mapResult.put(orderId, builder.toString());

        });


        List<String> head = new ArrayList<>();
        head.add("注文番号");
        head.add("merge");
        result.add(head);

        mapResult.forEach((k, v) -> {
            List<String> rows = new ArrayList<>();
            rows.add(k);
            rows.add(v);
            result.add(rows);
        });
    }

    @Override
    public void doHandler() {
        super.doHandler();
    }

    public static int analysisNumber(String text, Context context) {
        try {
            // 没有枚的情况下，不参与计算
            if (!text.contains("枚")) {
                throw new Exception();
            }
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(text);
            int result = Integer.parseInt(m.replaceAll("").trim());
            Preconditions.checkArgument(result > 0 && result <= 9, "枚数超出异常，请检查，对应的商品是：" + text);
            context.setType(0);
            return result;
        } catch (Exception ignored) {
            context.setType(1);
        }
        return -1;
    }

    @Data
    private static class Item {

        // 货号
        private String code;

        // 颜色
        private String color;

        // 个数
        private Integer number;

        // 单位
        private String unit = "";

        private String title = "";
    }

    @Data
    public static class Context {
        /**
         * type=0 普通需要计算的情况
         * type=1 无数字的情况,多个数字,情况，相连
         * 例如：ms-12 X 2
         * 例如：s104 X 4
         */
        private int type;
    }
}
