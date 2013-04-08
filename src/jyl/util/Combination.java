package jyl.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

/*
 * 返回组合
 */
public class Combination {
	
    private ArrayList<String> combList = new ArrayList<String>();

    public void mn(String[] array, int n) {
        int m = array.length;
        // 创建一个位 set,并设置其大小为m
        BitSet bs = new BitSet(m);
        // 默认情况下，set 中所有位的初始值都是false,这里循环将其默认值都修改为true,只修改前n位
        for (int i = 0; i < n; i++) {
            bs.set(i, true);
        }
        do {
            printAll(array, bs);
        } while (moveNext(bs, m));
    }

    /**
     * 输出生成的组合结果
     * 
     * @param array 数组
     * @param bs 数组元素是否显示的标志位集合
     */
    private void printAll(String[] array, BitSet bs) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            // 在mn方法操作中，只将前n位的值修改为true，所以这里只有前n位通过if判断,这里在bitSet中存的是数组的下标
            if (bs.get(i)) {
                sb.append(array[i]).append(',');
            }
        }
         //设置length属性，去掉最后多加的一个','
         sb.setLength(sb.length() - 1);
         combList.add(sb.toString());
    }

    /**
     * 1、start 第一个true片段的起始位，end截止位 2、把第一个true片段都置false
     * 3、数组从0下标起始到以第一个true片段元素数量减一为下标的位置都置true 4、把第一个true片段end截止位置true
     * 
     * @param bs 数组是否显示的标志位
     * @param m  数组长度
     * @return boolean 是否还有其他组合
     */
    private boolean moveNext(BitSet bs, int m) {
        int start = -1;
        // 该操作是获得第一个为true的bitset的下标,其值给start
        while (start < m) {
            // 先++是因为初始给的值为-1
            if (bs.get(++start)) {
                break;
            }
        }
        // 如果bitset中没有一个true那么完成while后start已经达最大,直接返回false
        if (start >= m) {
            return false;
        }
        // 从第一个为true的位置作为开始和结束位置
        int end = start;
        // 依次走，找到第一个为false的位置下标,其值给end
        while (end < m) {
            if (!bs.get(++end)) {
                break;
            }
        }
        // 如果走到最后都没有找到false,直接返回false
        if (end >= m) {
            return false;
        }
        for (int i = start; i < end; i++) {
            bs.set(i, false);
        }
        for (int i = 0; i < end - start - 1; i++) {
            // BitSet的set()方法：:将指定索引处的位设置为 true
            bs.set(i);
        }
        bs.set(end);
        return true;
    }

    public static void main(String[] args) throws Exception {
      //  Combination comb = new Combination();
        Date begin=new Date();
        //20Q的42个参数
        //String[] strArr=new String[] { "1", "0", "2", "8", "4", "17", "2", "7", "8", "5", "3", "1", "0", "1", "1", "1", "1", "49", "2", "9", "13", "1", "0", "0", "7", "0", "7", "7", "7", "2", "29", "0", "9", "11", "5", "2", "19", "0", "9", "10", "7", "0"};
        //72个参数的时候
     //   String[] strArr=new String[] { "1", "0", "2", "8", "4", "17", "2", "7", "8", "5", "3", "1", "0", "1", "1", "1", "1", "49", "2", "9", "13", "1", "0", "0", "7", "0", "7", "7", "7", "2", "29", "0", "9", "11", "5", "2", "19", "0", "9", "10", "7", "0","1", "0", "2", "8", "4", "17", "2", "7", "8", "5", "3", "1", "0", "1", "1", "1", "1", "49", "2", "9", "13", "1", "0", "0", "7", "0", "7", "7", "7", "2"};
        //设置为4，表示做的是取4个数来排列
        String[] strArr=new String[] { "1", "3","4","2","6", "5", "8","9","10"};
		/*
		 * resumeItems.put(1, "自我评价"); resumeItems.put(3, "工作经验");
		 * resumeItems.put(4, "教育背景"); resumeItems.put(2, "求职意向");
		 * resumeItems.put(6, "语言能力"); resumeItems.put(5, "培训经历");
		 * resumeItems.put(8, "证书"); resumeItems.put(9, "上传照片");
		 * resumeItems.put(10,"其它信息");
		 */
     //   Integer[] strArr=new Integer[] { 1, 0,10};
        ArrayList<String> total  = new   ArrayList<String>();
        for(int i = 0;i < strArr.length;i++){
        	 Combination comb = new Combination();
        comb.mn(strArr, i+1);
        ArrayList<String> combList1 =  comb.getCombList();
        total.addAll(combList1);
      
        }
        for(String str : total)
        {
       	 System.out.println("str:"+str);
        }
        System.out.println("组合总数:"+total.size());
        // 输出计算后的排列结果
//        for (int i = 0; i < comb.getCombList().size(); i++) {
//              System.out.println(comb.getCombList().get(i));
//        }
        Date end=new Date();
       
        long time=end.getTime()-begin.getTime();
        System.out.println("运算总时间为:"+time/1000+"秒"+time%1000);
     //   System.out.println("得到的排列的总数量为："+comb.getCombList().size());
    }

    public ArrayList<String> getCombList() {
        return combList;
    }

    
}


