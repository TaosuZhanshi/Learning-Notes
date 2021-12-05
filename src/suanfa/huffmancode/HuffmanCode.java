package suanfa.huffmancode;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        List<Node> nodeList = getNodes(contentBytes);
        Node root = createHuffmanTree(nodeList);
        System.out.println("前序排列的huffman tree结果为");
        preOrder(root);

        Map<Byte, String> huffmanCodes1 = getCodes(root);
        System.out.println("生成的huffman编码表：" + huffmanCodes1);

        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes1);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));

        byte[] huffmanCodeBytes2 = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes2));

        byte[] decode = huffmanDecode(huffmanCodeBytes2, huffmanCodes1);
        System.out.println("解码后 ~~~ " + new String(decode));



    }

    //输出部分
    //得到字符串数组的List转型
    public static List<Node> getNodes(byte[] bytes) {
        //构造Node ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //利用Map将bytes中的byte计数整理好。
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //将整理好Map的值放入到ArrayList中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getValue(), entry.getKey()));
        }

        return nodes;

    }

    //创建新的霍夫曼树，并返回其根节点
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //每进行一次huffman排序，则进行一次排序
            Collections.sort(nodes);
            //提取最小的两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.weight + rightNode.weight, null);

            parent.leftNode = leftNode;
            parent.rightNode = rightNode;

            //删除掉两个已调用的节点，加入新的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //前序遍历
    public static void preOrder(Node node) {
        if (node == null) {
            System.out.println("遍历的树为空");
        } else {
            node.preOrder();
        }
    }

    //生成huffman编码
    //思路：
    //1. 将霍夫曼编码对放置在一个Map中
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2. 拼接路径的容器用于拼接最后的结果
    static StringBuilder stringBuilder = new StringBuilder();

    public static void getHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);

        stringBuilder1.append(code);

        //如果节点的data是null，说明其定有两个子节点连接
        if (node.data == null) {
            getHuffmanCodes(node.leftNode, "0", stringBuilder1);
            getHuffmanCodes(node.rightNode, "1", stringBuilder1);
        }
        //如果节点的data不为null，则说明已到达叶节点，那么这里则直接输出叶节点的huffman编码
        else {
            huffmanCodes.put(node.data, stringBuilder1.toString());
        }
    }

    //简化后的huffman编码
    private static Map<Byte, String> getCodes(Node root)
    {
        if(root == null){
            System.out.println("树为空");
            return null;
        }
        else {
            getHuffmanCodes(root, "", stringBuilder);
            return huffmanCodes;
        }
    }

    //传入字符串数组，生成赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //先利用huffman编码将bytes转成huffman编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        //统计返回byte[] huffmanCodeBytes 长度
        int len = (stringBuilder.length() + 7) / 8;

        //创建压缩后的byte数组
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;//记录索引
        for(int i = 0; i < stringBuilder.length(); i += 8){
            String strByte;
            if(i + 8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }
            else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodesBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index ++;
        }
        return  huffmanCodesBytes;
    }

    //使用一个方法将前面的所有方法封装
    private static byte[] huffmanZip(byte[] bytes){

        //先生成一个存放描述语句每个字符复杂度的List中
        List<Node> nodeList = getNodes(bytes);

        //生成相应的huffmantree
        Node root = createHuffmanTree(nodeList);

        //生成huffmantree对应的每个叶节点的huffman编码
        Map<Byte, String> huffmanCodes1 = getCodes(root);

        //生成输入的huffman编码过后的byte数组
        byte[] huffmanCodeBytes = zip(bytes,huffmanCodes1);

        return huffmanCodeBytes;
    }


    //接收部分

    //对接收的单个字节进行转换成字符串
    private static String byteToStr(boolean flag, byte b){
        int temp = b;

        //对于byte[]中间的一些正数，需要用 | 来补零，以免造成误差
        if(flag){
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);

        if(flag){
            //在byte[]中所有的byte都需要八位填充，这也是前面补零的原因
            return str.substring(str.length() - 8);
        }
        else {
            //如果是最后一位，则直接返回str，当然，这里没有考虑到最后一位是负数的情况，以及最后一个byte开头不为0的情况
            return str;
        }
    }

    //将传入的由二进制数组成的byte[]组转换为字符的byte[]组，当然还需要传入huffman编码对应的Map
    private static byte[] huffmanDecode(byte[] bytes, Map<Byte, String> huffmanMap){

        Map<String, Byte> map = new HashMap<>();
        //先将Map进行反转
        for(Map.Entry<Byte, String> entry : huffmanMap.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        StringBuilder stringBuilder = new StringBuilder();
        //再将输入的bytes数组依次提取出他的所有的值
        for(int i = 0; i < bytes.length; i ++){

            byte b = bytes[i];
            boolean flag = !( i == bytes.length - 1);
            stringBuilder.append(byteToStr(flag, b));
        }

        System.out.println(stringBuilder);


        //然后将依次识别stringBuilder中的值，对照huffman编码对应的Map进行解码
        List<Byte> byteList = new ArrayList<Byte>();
        for(int i = 0; i < stringBuilder.length(); ){
            int index = 1;
            String str;
            Byte b;

            while(true){
                str = stringBuilder.substring(i, i + index);
                b = map.get(str);
                if(b == null){
                    index ++;
                }
                else {
                    break;
                }
            }
            byteList.add(b);
            i += index;
        }

        //解码好的内容放入到结果byte[]中
        byte[] decode = new byte[byteList.size()];
        for(int i = 0; i < byteList.size(); i ++){
            decode[i] = byteList.get(i);
        }
        return decode;
    }

}


class Node implements Comparable<Node>
{
    public int weight;
    public Byte data;
    public Node leftNode;
    public Node rightNode;

    public Node(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.leftNode != null)
        {
            this.leftNode.preOrder();
        }
        if(this.rightNode != null)
        {
            this.rightNode.preOrder();
        }
    }

}