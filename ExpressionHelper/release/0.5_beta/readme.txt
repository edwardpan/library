
IK表达式解析器帮助工具包，简化了解析器的使用，并包含了自定义函数。

依赖：
IKExpression2.1.2.jar
slf4j-api-1.6.1.jar

更新日志：
0.5_beta
1、使用slf4j类库作为主日志库。

0.4_beta
1、修改表达式标识符，由以前的“{”和“}”改变为“[EXP]”和“[/EXP]”，解决“{”特殊字符的问题
2、修改从字符串中抽取表达式的方法，使用字符串分析，抛弃正则表达式的方式
3、增加用于处理浮点数四舍五入的函数

0.3_beta
1、删除函数：$dateToNewFormatAny
2、增加函数：$stringToDate   将字符串的日期时间解析为对象
3、去除单例模式，解决单例模式时变量值混乱的问题
4、为所有的字符串函数添加了调试日志
5、为表达式解析器帮助类主运行类添加了调试日志

0.2_beta
1、增加任意旧格式日期变换为新格式的函数、字符串中的字母转小写函数、字符串中的字母转大写函数