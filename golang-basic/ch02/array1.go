package main

import "fmt"

func main() {
	var arr1 [4][2]int
	arr2 := [4][2]int{{10, 11}, {20, 22}}
	fmt.Printf("out1: %v\n", arr2)

	arr1[0][1] = 1
	arr1[1] = [2]int{1, 2}
	fmt.Printf("out2: %v\n", arr1)

	foo(&[1e6]int{0: 1})

	// slice
	slice1 := []string{"1", "2"}
	slice2 := []string{99: ""}
	fmt.Println("out4: ", slice1)
	fmt.Println("out5: ", slice2[0] == slice2[99])
	var slice3 = make([]int, 3, 5)
	fmt.Println("out6: ", slice3)
	// empty slice
	var slice4 = make([]int, 0)
	fmt.Println("out7: ", slice4)
	// nil slice
	var slice5 []int
	fmt.Println("out8: ", slice5)
	// copy slice
	slice3[0] = 1
	slice3[1] = 2
	slice3[2] = 3
	slice6 := slice3[1:4]
	fmt.Println("out9: ", slice6)
	// index out of range
	// fmt.Println("out9: ", slice3[3])
}

func foo(array *[1e6]int) {
	fmt.Println("out3: ", array[0])
}
