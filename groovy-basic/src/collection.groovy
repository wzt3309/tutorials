/**
 * List, Array, Map
 *
 * @author xingguan.wzt* @date 2020/09/23
 */

def letters = ['a', 'b', 'c', 'd']

assert letters[-1] == 'd'
assert letters[-2] == 'c'

letters << 'e'
assert letters[4] == 'e'

assert letters[1, 3] == ['b', 'd']
assert letters[1..3] == ['b', 'c', 'd']

def multi = [[0, 1], [2, 3]]
assert multi[0][-1] == 1

// 使用 array 必须显示申明
def a = [1, 2, 3] as int[]

// array 不允许使用 << 追加
//a << 4

def colors = [red: "red", green: "green"]
println(colors['red'])
// 属性取值法, 属性key = string
println(colors.red)

assert colors instanceof LinkedHashMap

assert colors.unknown == null

// 默认是使用 字符串作为 key
def key = 'name'
def person1 = [key: "Bob"]
assert person1.containsKey('key')

// 使用变量作为 key 下标方式 为直接取下标代表值作为key
def person2 = [(key): "Bob"]
assert person2.containsKey(key)
println(person2[key])

// 可以使用数字作为key 但是只能用下标方式取值
def aMap = [1: '222']
println(aMap[1])

def bMap = [1.0: "222"]
println(bMap[1.0])