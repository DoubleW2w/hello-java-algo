### 简述字典树的数据结构

字典树又称「单词查找树」或者「数字树」，一种用于搜索的树。专门用于存储动态集或键为字符串的关联数组。

1. 每个节点代表一个字符。
2. 从根节点到任何一个节点，路径上经过的字符连接起来就是该节点所代表的字符串。
3. 每个节点可以包含多个子节点，每个子节点代表不同的字符。
4. 在树中的每个节点的所有子孙都有一样的前缀，即这个节点所对应的字符串。而根节点对应空字符串。

### 叙述你怎么来实现一个字典树

字典树字母的存放有26个，数字树的话就是10个数字。也就是说在实现过程中，每个节点的分支都有对应的槽位来存放可能出现的情况。

### 字典树的实际业务场景举例
- 自动完成和搜索建议：通过将「搜索关键字」构建成字典树，可以快速地查找以用户输入为前缀的所有可能搜索词汇。
- 拼写检查与纠正：通过将正确的单词构建成字典树，可以在用户输入错误拼写时，快速地找到可能的正确拼写建议
- IP路由表：可以帮助路由器快速匹配目标 IP 地址，以确定下一跳路由

### 字典树的存入和检索的时间复杂度
- 对于要插入的字符串，需要从根节点开始，逐个字符进行插入，插入的时间复杂度 $O(n)$ 与字符串长度成正比。(
  其中 n 是字符串长度)

- 对于检索操作也需要从根节点开始，逐个字符进行查找。查询的时间复杂度同样与查询的字符串长度成正比，即
  $O(n)$ (其中 n 是字符串长度)。