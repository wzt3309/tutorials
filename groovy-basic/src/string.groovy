/**
 * String
 *
 * @author xingguan.wzt
 * @date 2020/09/23
 */

// 多行字符串
def multiStr = '''first
second
third'''
println(multiStr)

// 对 \n 进行转义
def multiOneLineStr = '''first \
second \
third
'''
println(multiOneLineStr)

// 通过转义打印特殊字符 \b: backspace
println('test\b')

// \u+4位16进制数 unicode字符
println('Euro currency: \u20ac')

// ""中如果无插值 则不是GString
println("abc" instanceof GString) // false

// aMap.1 输出 [1:1234].1
// aMap.x 变成获取 aMap的属性x
def aMap = [:]
aMap.'1' = '1234'
println("a=$aMap.1")
println("a=$aMap.x")

// 转义 $ 避免插值计算
println("\$name")
println("\${name}")

// 闭包插值, 只允许无参和单个参数, 参数为 StringWriter
println("closure: ${->3}")
println("closure: ${w -> w << "test"}")

// 闭包插值 lazy eval
def num = 1
def lazyGStr = "${->num}"
assert "1" == "$lazyGStr"
println("$lazyGStr")
num = 2
assert "2" == "$lazyGStr"
println("$lazyGStr")

// false false true GString 不是不可变类，不要用它作为 Map 的 key
println("${1}" === "${1}")
println("1" === "${1}")
println("1" === "1")

// """xxx""" 与"" 一样效果，可以跨行
def name = 'Jone'
println("""first \
${name}' book is "not used" \u20ac12
last line""")

// 不需要进行额外的转义（除了 '/'），支持插值
def pattern = /.*foo.* \t\n\r \/ ${'\\'}/
println(pattern)
def aName = 'test'
println(/$aName*.?/)

// dollar slashy
println($/
$ test
\/\/
$\ $\ $\$$
te \\
'\'
$ ${'\\'}
$/
//
$$
$$$
\u20ac
/$)

// 只能通过显示申明 char
char a1 = 'A'
def a2 = 'A' as char
def a3 = (char) 'A'