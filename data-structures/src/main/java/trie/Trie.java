package trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: DoubleW2w
 * @description: 字典树
 * @date: 2023/12/8 17:41
 * @project: hello-java-algo
 */
public class Trie {

    /**
     * 节点
     */
    public final static TrieNode wordsTree = new TrieNode();

    /**
     * 新增一个单词和单词注释
     * @param words 单词
     * @param explain 单词注释
     */
    public void insert(String words, String explain) {
        TrieNode root = wordsTree;
        char[] chars = words.toCharArray();
        for (char c : chars) {
            // - a 从 0 开始，参考 ASCII 码表
            int idx = c - 'a';
            if (root.slot[idx] == null) {
                root.slot[idx] = new TrieNode();
            }
            root = root.slot[idx];
            root.c = c;
            root.prefix++;
        }
        // 单词的注释说明信息
        root.explain = explain;
        // 循环拆解单词后标记
        root.isWord = true;
    }

    /**
     * 根据前缀进行搜索
     * @param prefix
     * @return
     */
    public List<String> searchPrefix(String prefix) {
        TrieNode root = wordsTree;
        char[] chars = prefix.toCharArray();
        StringBuilder cache = new StringBuilder();
        // 精准匹配：根据前置精准查找
        for (char c : chars) {
            int idx = c - 'a';
            // 匹配为空
            if (idx > root.slot.length || idx < 0 || root.slot[idx] == null) {
                return Collections.emptyList();
            }
            cache.append(c);
            root = root.slot[idx];
        }
        // 模糊匹配：根据前缀的最后一个单词，递归遍历所有的单词
        ArrayList<String> list = new ArrayList<>();
        if (root.prefix != 0) {
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
                    char c = (char) (i + 'a');
                    collect(root.slot[i], String.valueOf(cache) + c, list, 15);
                    if (list.size() >= 15) {
                        return list;
                    }
                }
            }
        }
        return list;
    }
}
