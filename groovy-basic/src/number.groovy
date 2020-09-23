/**
 * Number
 *
 * @author xingguan.wzt* @date 2020/09/23
 */

// 动态数据类型，找到能容纳数字的最小类型
// 整型默认是Integer
def a = 1
assert a instanceof Integer

def b = Integer.MAX_VALUE + 1
assert b == -2147483648

def c = 2147483648
assert c instanceof Long

// infinite precision
def d = 9223372036854775808
assert d instanceof BigInteger

int xInt = 0b101
assert xInt == 5

byte xByte = 0b01111111
assert xByte == 127 as Byte

// 强转 用byte大小空间存放 丢失精度 补码
byte xByte2 = 0b11111111
assert xByte2 == -1 as Byte

// 补码
byte xByte3 = 0b10000000
assert xByte3 == -128 as Byte

byte xByte4 = -0b1
assert xByte4 == -1 as Byte

// 浮点型 默认 BigDecimal
println(1.0 instanceof Double) // false
println(1.0 instanceof Float) // false
println(1.0 instanceof BigDecimal) // true
println(1.0d instanceof Double) // true

assert 1e2 == 100
assert 1e2 instanceof BigDecimal
assert 1.0 == 1 // true
assert 1e4 == (10000 as Integer)
assert 20_000 instanceof Integer

// 参数为 double 或者 float的方法可以接受类型为 BigDecimal的常量
static def acceptDouble(double a) {
    assert a instanceof Double
}

acceptDouble(1e2)

assert (1d / 1) instanceof Double
assert (1f / 1) instanceof Double
assert (1 / 1) instanceof BigDecimal
assert 1.0g.precision() == 2
assert 5.intdiv(4) == 1
println(5 / 4)

//  precision为有效数字长度 scale为小数点右边数字位数
// 2数相除 precision为2个操作数最大精度+10
// scale 为Max(a的scale, b的scale, 10)
println(1 / 3)
assert 100.000.scale() == 3
println(1.00000000000 / 3)
println(100 / 3)

// TODO 指数数据类型转换过于复杂 先不写了
// @see https://groovy-lang.org/syntax.html#power_operator

// 非bool值转bool
def boolVal1 = 1 as boolean
def boolVal2 = -1 as boolean
println(boolVal1) // true
println(boolVal2) // true
println(0 as boolean) // false