/**
 *
 * @author xingguan.wzt* @date 2020/09/24
 */

// 标签语法, 只是为了可读性更强
given:
def x = 1
def y = 2
when:
def z = x + y
then:
assert z == 3

// 未申明 def var Type 的变量是 Script 绑定的
p = 1

// 动态推断类型可以多次赋不同类型的值
x = 1
println x

x = new Date()
println x

x = -3.1499392
println x

x = false
println x

x = "Hi"
println x

// 多赋值
def (a, b, c) = [10, 20, 'foo']
assert a == 10 && b == 20 && c == 'foo'

def (_, month, year) = "24th Sep 2020".split()
assert _ == "24th" && month == "Sep" && year == '2020'

// 上溢 & 下溢
(a, b, c) = [1, 2]
assert a == 1 && b == 2 && c == null

(a, b) = [1, 2, 3]
assert a == 1 && b == 2

println a.is(1) // true
println "11".is("11") // true



